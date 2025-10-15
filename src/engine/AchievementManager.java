package engine;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class AchievementManager {
    private static AchievementManager instance;
    private List<Achievement> achievements;
    private int shotsFired = 0;
    private int shotsHit = 0;
    private boolean firstKillUnlocked = false;
    private boolean sniperUnlocked = false;
    private boolean survivorUnlocked = false;

    private AchievementManager() {
        achievements = new ArrayList<>();
        achievements.add(new Achievement("Beginner", "Clear level 1"));
        achievements.add(new Achievement("Intermediate", "Clear level 3"));
        achievements.add(new Achievement("Boss Slayer", "Defeat a boss"));
        achievements.add(new Achievement("Mr. Greedy", "Have more than 2000 coins"));
        achievements.add(new Achievement("First Blood", "Defeat your first enemy"));
        achievements.add(new Achievement("Bear Grylls", "Survive for 60 seconds"));
        achievements.add(new Achievement("Bad Sniper", "Under 80% accuracy"));
    }

    public static AchievementManager getInstance() {
        if (instance == null) {
            instance = new AchievementManager();
        }
        return instance;
    }

    public List<Achievement> getAchievements() {
        return achievements;
    }

    public void unlockAchievement(String name) {
        for (Achievement achievement : achievements) {
            if (achievement.getName().equals(name) && !achievement.isUnlocked()) {
                achievement.unlock();
                break;
            }
        }
    }


    public void onEnemyDefeated() {
        if (!firstKillUnlocked) {
            unlockAchievement("First Blood");
            firstKillUnlocked = true;
        }

        shotsHit++;

        if (!sniperUnlocked && shotsFired > 5) {
            double accuracy = (shotsHit / (double) shotsFired) * 100.0;
            if (accuracy <= 80.0) {
                unlockAchievement("Bad Sniper");
                sniperUnlocked = true;
            }
        }
    }

    public void onTimeElapsedSeconds(int elapsedSeconds) {
        if (!survivorUnlocked && elapsedSeconds >= 60) {
            unlockAchievement("Bear Grylls");
            survivorUnlocked = true;
        }
    }

    public void onShotFired() {
        shotsFired++;
    }

}
