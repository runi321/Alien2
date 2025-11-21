package AlienMarauders.Game;

import AlienMarauders.Controller;
import AlienMarauders.Model;
import AlienMarauders.Game.entities.Enemy;
import AlienMarauders.Game.entities.Player;
import AlienMarauders.Game.entities.PlayerShot;
import javafx.animation.AnimationTimer;
import javafx.scene.canvas.Canvas;
import javafx.scene.image.Image;
import AlienMarauders.Game.movement.MovementStrategy;
import AlienMarauders.Game.movement.NoMovementStrategy;
import AlienMarauders.Game.movement.MoveDownStrategy;
import AlienMarauders.Game.movement.ZigZagMovementStrategy;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Random;
import java.util.Set;
import java.util.concurrent.*;

public class GameController {

    private final Model model;
    private final Gameview view;
    private final Controller rootController;

    // Game state
    private Player player;
    private final ArrayList<Enemy> enemies = new ArrayList<>();
    private final ArrayList<PlayerShot> shots = new ArrayList<>();
    private final Score score = new Score();

    private MovementStrategy movementStrategy;
    private double speedMultiplier = 1.0;

    // Concurrency: one worker thread for collision checks
    private final ExecutorService executor =
            Executors.newSingleThreadExecutor();

    private AnimationTimer gameLoop;
    private boolean firstFrame = true;

    public GameController(Model model, Gameview view, Controller rootController) {
        this.model = model;
        this.view = view;
        this.rootController = rootController;

        setupGame();
        attachHandlers();
    }

    /* ---------------------- setup ---------------------- */

    private void setupGame() {
        // Create player
        Image playerImage = new Image(
                getClass().getResource("/AlienMarauders/Myndir/player.png").toExternalForm());

        player = new Player(
                Gameview.WIDTH / 2.0 - 25,
                Gameview.HEIGHT - 80,
                50, 50,
                playerImage
        );

        // First wave of enemies
        spawnEnemies();

        // Keyboard input
        initializeKeyBindings(view.getCanvas(), player);

        // Game loop
        gameLoop = new AnimationTimer() {
            long lastNanoTime = 0;

            @Override
            public void handle(long now) {
                if (firstFrame) {
                    lastNanoTime = now;
                    firstFrame = false;
                    return;
                }
                long elapsedMs = (now - lastNanoTime) / 1_000_000;
                lastNanoTime = now;

                double dt = elapsedMs;

                // 1) Move entities
                player.move(dt);
                movementStrategy.moveEnemies(enemies, dt);
                for (PlayerShot shot : shots) {
                    shot.move(dt);
                }
                // mark off-screen shots as dead
                for (PlayerShot shot : shots) {
                    if (shot.getPositionY() + shot.getHeight() < 0) {
                        shot.kill();
                    }
                }

                // 2) Collision checks in worker thread
                Future<CollisionResult> future =
                        executor.submit(this::doCollisionChecks);

                CollisionResult r;
                try {
                    r = future.get(); // wait for worker (still counts as concurrency)
                } catch (InterruptedException | ExecutionException e) {
                    e.printStackTrace();
                    stopGame();
                    rootController.showMainMenu();
                    return;
                }

                // 3) Apply collision result on FX thread
                if (r.playerHit || r.enemyAtBottom) {
                    stopGame();
                    rootController.showMainMenu();
                    return;
                }

                // kill enemies and shots hit by bullets
                for (int i : r.enemyIndicesToKill) {
                    if (i >= 0 && i < enemies.size()) {
                        enemies.get(i).kill();
                    }
                }
                for (int j : r.shotIndicesToKill) {
                    if (j >= 0 && j < shots.size()) {
                        shots.get(j).kill();
                    }
                }

                if (r.scoreDelta != 0) {
                    score.updateScore(r.scoreDelta);
                }

                enemies.removeIf(e -> !e.isAlive());
                shots.removeIf(s -> !s.isAlive());

                // 4) All enemies dead -> new faster wave
                if (enemies.isEmpty()) {
                    speedMultiplier *= 1.15;
                    spawnEnemies();
                }

                // 5) Render
                var gc = view.getGraphicsContext();
                gc.clearRect(0, 0, Gameview.WIDTH, Gameview.HEIGHT);

                player.render(gc);
                for (Enemy e : enemies) e.render(gc);
                for (PlayerShot s : shots) s.render(gc);
                score.render(gc);
            }

            // --- runs in worker thread, no JavaFX calls here
            private CollisionResult doCollisionChecks() {
                CollisionResult r = new CollisionResult();

                for (int i = 0; i < enemies.size(); i++) {
                    Enemy enemy = enemies.get(i);
                    if (!enemy.isAlive()) continue;

                    // player vs enemy
                    if (CollisionDetection.Aabb(player, enemy)) {
                        r.playerHit = true;
                    }

                    // enemy reaches bottom
                    if (enemy.getPositionY() + enemy.getHeight() >= Gameview.HEIGHT) {
                        r.enemyAtBottom = true;
                    }

                    // shots vs enemy
                    for (int j = 0; j < shots.size(); j++) {
                        PlayerShot shot = shots.get(j);
                        if (!shot.isAlive()) continue;

                        if (CollisionDetection.Aabb(shot, enemy)) {
                            r.enemyIndicesToKill.add(i);
                            r.shotIndicesToKill.add(j);
                            r.scoreDelta += 10; // 10 points per enemy
                        }
                    }
                }
                return r;
            }
        };
    }

    /* --------- small helper type returned by worker ---------- */

    private static class CollisionResult {
        boolean playerHit;
        boolean enemyAtBottom;
        int scoreDelta;
        Set<Integer> enemyIndicesToKill = new HashSet<>();
        Set<Integer> shotIndicesToKill = new HashSet<>();
    }

    /* ----------------- enemies & strategy ----------------- */

    private void spawnEnemies() {
        enemies.clear();

        Image enemySheet = new Image(
                getClass().getResource("/AlienMarauders/Myndir/greenmonster.png").toExternalForm());

        // Very simple "row" formation
        int cols = 6;
        double startX = 60;
        double spacingX = 80;
        double y = 60;

        for (int i = 0; i < cols; i++) {
            double x = startX + i * spacingX;
            enemies.add(new Enemy(x, y, 40, 40, enemySheet, 2));
        }

        // pick strategy randomly: no movement / down / zigzag
        Random rand = new Random();
        int choice = rand.nextInt(3);
        switch (choice) {
            case 0 -> movementStrategy = new NoMovementStrategy();
            case 1 -> movementStrategy = new MoveDownStrategy();
            default -> movementStrategy = new ZigZagMovementStrategy();
        }
        movementStrategy.setSpeedMultiplier(speedMultiplier);
    }

    /* ----------------- input & buttons ----------------- */

    private void initializeKeyBindings(Canvas canvas, Player player) {
        canvas.setFocusTraversable(true);

        canvas.setOnKeyPressed(evt -> {
            switch (evt.getCode()) {
                case A, LEFT  -> player.movingLeft(true);
                case D, RIGHT -> player.movingRight(true);
                case W, UP    -> player.movingUp(true);
                case S, DOWN  -> player.movingDown(true);
                case SPACE    -> shoot();
                case ESCAPE   -> {
                    stopGame();
                    rootController.showMainMenu();
                }
                default -> { }
            }
        });

        canvas.setOnKeyReleased(evt -> {
            switch (evt.getCode()) {
                case A, LEFT  -> player.movingLeft(false);
                case D, RIGHT -> player.movingRight(false);
                case W, UP    -> player.movingUp(false);
                case S, DOWN  -> player.movingDown(false);
                default -> { }
            }
        });
    }

    private void shoot() {
        double x = player.getPositionX() + player.getWidth() / 2.0 - 2;
        double y = player.getPositionY() - 12;
        shots.add(new PlayerShot(x, y));
    }

    private void attachHandlers() {
        view.getBackBtn().setOnAction(e -> {
            stopGame();
            rootController.showMainMenu();
        });
    }

    /* ----------------- lifecycle ----------------- */

    public void startGame() {
        firstFrame = true;
        gameLoop.start();
        view.getCanvas().requestFocus();
    }

    public void stopGame() {
        if (gameLoop != null) {
            gameLoop.stop();
        }
    }

    // Optional: call when app closes to clean up the thread
    public void shutdownExecutor() {
        executor.shutdownNow();
    }
}