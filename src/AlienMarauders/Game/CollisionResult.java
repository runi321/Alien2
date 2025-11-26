package AlienMarauders.Game;

import java.util.HashSet;
import java.util.Set;

public class CollisionResult {
        boolean playerHit;
        boolean enemyAtBottom;
        int scoreDelta;
        Set<Integer> enemyIndicesToKill = new HashSet<>();
        Set<Integer> shotIndicesToKill = new HashSet<>();
    }


