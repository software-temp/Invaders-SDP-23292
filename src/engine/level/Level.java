package engine.level;

/**
 * Represents the settings for a single level.
 */
public class Level {

    private int level;
    private int formationWidth;
    private int formationHeight;
    private int baseSpeed;
    private int shootingFrecuency;

    // Getters for the properties

    public int getLevel() {
        return level;
    }

    public int getFormationWidth() {
        return formationWidth;
    }

    public int getFormationHeight() {
        return formationHeight;
    }

    public int getBaseSpeed() {
        return baseSpeed;
    }

    public int getShootingFrecuency() {
        return shootingFrecuency;
    }
}
