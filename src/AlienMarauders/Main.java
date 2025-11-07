package AlienMarauders;

import AlienMarauders.Controller;
import AlienMarauders.Model;
import AlienMarauders.View;
import javafx.application.Application;
import javafx.stage.Stage;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) {
        Model model = new Model();
        View view = new View(model);
        Controller controller = new Controller(model, view);

        primaryStage.setTitle("Alien Marauders");
        primaryStage.setScene(view.getScene());
        primaryStage.show();
        System.out.println(getClass().getResource("/AlienMarauders/Myndir/space.png"));

        controller.showMainMenu();
    }

    public static void main(String[] args) {
        launch(args);
    }
}