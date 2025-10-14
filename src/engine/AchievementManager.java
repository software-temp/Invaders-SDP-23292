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

    private AchievementManager() {
        achievements = new ArrayList<>();
        achievements.add(new Achievement("New here?", "Die 5 times"));
        achievements.add(new Achievement("Beginner", "Clear level 1"));
        achievements.add(new Achievement("Intermediate", "Clear level 3"));
        achievements.add(new Achievement("Boss Slayer", "Defeat a boss"));
        achievements.add(new Achievement("Mr. Greedy", "Have more than 2000 coins"));
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
}
