package entity;

public class ShopItem{
    /**
     * Spread Shot level (0 = not purchased, 1-3 = enhancement levels)
     */
    private static int spreadShotLevel = 0;

    /**
     * Maximum Spread Shot level
     */
    private static final int MAX_SPREAD_SHOT_LEVEL = 3;



    private static int SHIPSPEEDLEVEL = 0;


    private static final int MAX_SHIP_SPEED_LEVEL = 6;

    /**
     * Number of bullets fired per level
     */
    private static final int[] SPREAD_SHOT_BULLETS = {1, 2, 3, 4};

    /**
     * Spacing between bullets per level (in pixels)
     */
    private static final int[] SPREAD_SHOT_SPACING = {0, 10, 8, 5};

    /**
     * Ship speed increase rate per level
     */
    private static final int[] SHIP_SPEED = {0, 5, 10, 15, 20, 25};

    /**
     * Private constructor - this class should not be instantiated.
     * It is intended to be used only with static methods.
     */

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


    // ==================== Utility Methods ====================

    /**
     * Resets all items (for testing or game reset).
     */
    public static void resetAllItems() {
        spreadShotLevel = 0;
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
                .append(", Spacing: ").append(getSpreadShotSpacing())
                .append("px)\n");
        status.append("Ship Speed Level: ").append(SHIPSPEEDLEVEL);
        return status.toString();
    }

    /**
     * For testing - sets the Spread Shot to its maximum level.
     */
    public static void setMaxLevelForTesting() {
        spreadShotLevel = MAX_SPREAD_SHOT_LEVEL;
    }

    public static boolean setSHIPSPEED(int level){
        if (level < 0 || level > MAX_SHIP_SPEED_LEVEL) {
            return false;
        }
        SHIPSPEEDLEVEL = level;
        return true;
    }

    public static int getSHIPSpeedCOUNT() {
        return SHIP_SPEED[SHIPSPEEDLEVEL];
    }

}
