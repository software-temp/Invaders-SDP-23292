package engine;
import java.util.HashMap;
import java.util.Map;
public class LevelScore {
    private final Map<Integer, Integer> levelSTimes = new HashMap<>();

    public LevelScore() {
        // 조정가능
        levelSTimes.put(1, 180);
        levelSTimes.put(2, 150);
        levelSTimes.put(3, 120);
        levelSTimes.put(4, 100);
        levelSTimes.put(5, 90);
        levelSTimes.put(6, 85);
        levelSTimes.put(7, 80);
        levelSTimes.put(8, 75);
        levelSTimes.put(9, 70);
        levelSTimes.put(10, 65);
    }
    private int calculateTimeBonus(int level, int finishTimeInSeconds) {
        Integer sTime = levelSTimes.get(level);
        if (sTime == null) return 0;
        if (finishTimeInSeconds <= sTime)
            return 300;
        if (finishTimeInSeconds <= sTime + 30)
            return 200;
        if (finishTimeInSeconds <= sTime + 60)
            return 100;
        return 0;
    }

    public int calculateFinalScore(int baseScore, int level, int finishTimeInSeconds) {
        int timeBonus = calculateTimeBonus(level, finishTimeInSeconds);
        return baseScore + timeBonus;
    }
}
