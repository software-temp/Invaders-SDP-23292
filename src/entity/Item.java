package entity;

/**
 * Manages the in-game item (enhancement) system.
 * This is a temporary implementation focusing on functionality.
 *
 * Currently implemented: Spread Shot
 *
 * Example usage:
 * Item.setSpreadShotLevel(2);  // Purchase level 2 in the shop
 * int bulletCount = Item.getSpreadShotBulletCount();  // Returns the number of bullets to fire
 */
public class Item {

    // ==================== Spread Shot Item ====================

    /** Spread Shot level (0 = not purchased, 1-3 = enhancement levels) */
    private static int spreadShotLevel = 0;

    /** Maximum Spread Shot level */
    private static final int MAX_SPREAD_SHOT_LEVEL = 3;

    /** Number of bullets fired per level */
    private static final int[] SPREAD_SHOT_BULLETS = {1, 2, 3, 4};

    /** Spacing between bullets per level (in pixels) */
    private static final int[] SPREAD_SHOT_SPACING = {0, 10, 8, 5};


    /**
     * Private constructor - this class should not be instantiated.
     * It is intended to be used only with static methods.
     */
    private Item() {
    }
    //==================== Rapid Fire Item =======================

    /** Rapid Fire lever (0 = not purchased, 1~5 = enhancement levels)*/
    private static int rapidFireLevel = 0;

    /** maximum Rapid Fire level */
    private static final int MAX_RAPID_FIRE_LEVEL = 5;

    /** Base Shooting Interval */
    private static final int BASE_SHOOTING_INTERVAL = 750;

    /** Rapid Fire Reduction Per Level (%)*/
    private static final int[] RAPID_FIRE_REDUCTION ={0, 5, 10, 15, 20, 30};



    // ==================== Spread Shot Methods ====================

    /**
     * Sets the Spread Shot level (called upon purchase from a shop).
     *
     * @param level The level to set (0-3).
     * @return true if the level was set successfully, false otherwise.
     */
    public static boolean setSpreadShotLevel(int level) {
        if (level < 0 || level > MAX_SPREAD_SHOT_LEVEL) {
            return false;
        }
        spreadShotLevel = level;
        return true;
    }

    /**
     * Returns the current Spread Shot level.
     *
     * @return The current level (0-3).
     */
    public static int getSpreadShotLevel() {
        return spreadShotLevel;
    }

    /**
     * Returns the number of bullets to fire for the Spread Shot.
     *
     * @return The number of bullets (1-4).
     */
    public static int getSpreadShotBulletCount() {
        return SPREAD_SHOT_BULLETS[spreadShotLevel];
    }

    /**
     * Returns the spacing for Spread Shot bullets.
     *
     * @return The spacing between bullets in pixels.
     */
    public static int getSpreadShotSpacing() {
        return SPREAD_SHOT_SPACING[spreadShotLevel];
    }

    /**
     * Checks if the Spread Shot is active.
     *
     * @return true if the level is 1 or higher, false otherwise.
     */
    public static boolean isSpreadShotActive() {
        return spreadShotLevel > 0;
    }

    //==================== Rapid Fire Methods ====================

    /**
     * Sets rapid fire level.
     *
     * @param level The level to set (0-5).
     * @return True if the level was set successfully, false otherwise.
     */
    public static boolean setRapidFireLevel(int level) {
        if (level < 0 || level > MAX_RAPID_FIRE_LEVEL) {
            return false;
        }
        rapidFireLevel = level;
        return true;
    }

    /**
     * Returns the current rapid fire level.
     *
     * @return The current level (0-5).
     */
    public static int getRapidFireLevel() {
        return rapidFireLevel;
    }

    /**
     * Returns the current shooting interval.
     *
     * @return The shooting interval.
     */
    public static int getShootingInterval() {
        int reduction = RAPID_FIRE_REDUCTION[rapidFireLevel];
        return BASE_SHOOTING_INTERVAL * (100 - reduction) / 100;
    }




    // ==================== Utility Methods ====================

    /**
     * Resets all items (for testing or game reset).
     */
    public static void resetAllItems() {
        spreadShotLevel = 0;
        rapidFireLevel = 0;
    }

    /**
     * Returns the current status of items (for debugging purposes).
     *
     * @return A string representing the item status.
     */
    public static String getItemStatus() {
        StringBuilder status = new StringBuilder();
        status.append("=== Item Status ===\n");
        status.append("Spread Shot Level: ").append(spreadShotLevel)
                .append(" (Bullets: ").append(getSpreadShotBulletCount())
                .append(", Spacing: ").append(getSpreadShotSpacing());
        status.append("Rapid Fire Level: ").append(rapidFireLevel)
                .append(" (Interval: ").append(getShootingInterval());
        return status.toString();
    }

    /**
     * For testing - sets the Spread Shot to its maximum level.
     */
    public static void setMaxLevelForTesting() {
        spreadShotLevel = MAX_SPREAD_SHOT_LEVEL;
        rapidFireLevel = MAX_RAPID_FIRE_LEVEL;
    }
}