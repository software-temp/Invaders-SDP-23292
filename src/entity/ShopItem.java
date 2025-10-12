package entity;

/**
 * Manages the in-game item (enhancement) system.
 * This is a temporary implementation focusing on functionality.
 *
 * Currently implemented: Spread Shot
 *
 * Example usage:
 * Item.setMultiShotLevel(2);  // Purchase level 2 in the shop
 * int bulletCount = Item.getMultiShotBulletCount();  // Returns the number of bullets to fire
 */
public class ShopItem {

    // ==================== MultiShot Item ====================

    /** MultiShot level (0 = not purchased, 1-3 = enhancement levels) */
    private static int multiShotLevel = 0;

    /** Maximum MultiShot level */
    private static final int MAX_MULTI_SHOT_LEVEL = 3;

    /** Number of bullets fired per level */
    private static final int[] MULTI_SHOT_BULLETS = {1, 2, 3, 4};

    /** Spacing between bullets per level (in pixels) */
    private static final int[] MULTI_SHOT_SPACING = {0, 10, 8, 5};


    /**
     * Private constructor - this class should not be instantiated.
     * It is intended to be used only with static methods.
     */
    private ShopItem() {
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


    //===================== penetration Item =====================

    /** penetration levle (0 = not purchased, 1~5 = enhancement levels) */
    private static int penetrationLevel = 0;

    /** maximum penetration level */
    private static final int MAX_PENETRATION_LEVEL = 2;

    /** penetration count */
    private static final int[] PENETRATION_COUNT = {0,1,2};



    // ==================== MultiShot Methods ====================

    /**
     * Sets the MultiShot level (called upon purchase from a shop).
     *
     * @param level The level to set (0-3).
     * @return true if the level was set successfully, false otherwise.
     */
    public static boolean setMultiShotLevel(int level) {
        if (level < 0 || level > MAX_MULTI_SHOT_LEVEL) {
            return false;
        }
        multiShotLevel = level;
        return true;
    }

    /**
     * Returns the current MultiShot level.
     *
     * @return The current level (0-3).
     */
    public static int getMultiShotLevel() {
        return multiShotLevel;
    }

    /**
     * Returns the number of bullets to fire for the MultiShot.
     *
     * @return The number of bullets (1-4).
     */
    public static int getMultiShotBulletCount() {
        return MULTI_SHOT_BULLETS[multiShotLevel];
    }

    /**
     * Returns the spacing for MultiShot bullets.
     *
     * @return The spacing between bullets in pixels.
     */
    public static int getMultiShotSpacing() {
        return MULTI_SHOT_SPACING[multiShotLevel];
    }

    /**
     * Checks if the MultiShot is active.
     *
     * @return true if the level is 1 or higher, false otherwise.
     */
    public static boolean isMultiShotActive() {
        return multiShotLevel > 0;
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


    //===================== Penetration Methods ================

    /**
     * Set Penetration Level
     *
     * @param level The level to set (0-2).
     * @return True if the level was set successfully, false otherwise.
     */
    public static boolean setPenetrationLevel(int level) {
        if (level < 0 || level > MAX_PENETRATION_LEVEL) {
            return false;
        }
        penetrationLevel = level;
        return true;
    }


    /**
     * Returns the current rapid fire level.
     *
     * @return The current level (0-2).
     */
    public static int getPenetrationLevel() {
        return penetrationLevel;
    }

    /**
     * return Penetration count
     *
     * @return Penetration count (0 = cannot penetrate, 1~2 = can penetrate)
     */
    public static int getPenetrationCount() {
        return PENETRATION_COUNT[penetrationLevel];
    }

    /**
     * Checks if penetration is enabled.
     *
     * @return true if the level is 1 or higher, false otherwise.
     */
    public static boolean isPenetrationActive() {
        return penetrationLevel > 0;
    }


    // ==================== Utility Methods ====================

    /**
     * Resets all items (for testing or game reset).
     */
    public static void resetAllItems() {
        multiShotLevel = 0;
        rapidFireLevel = 0;
        penetrationLevel = 0;
    }

    /**
     * Returns the current status of items (for debugging purposes).
     *
     * @return A string representing the item status.
     */
    public static String getItemStatus() {
        StringBuilder status = new StringBuilder();
        status.append("=== Item Status ===\n");
        status.append("MultiShot Level: ").append(multiShotLevel)
                .append(" (Bullets: ").append(getMultiShotBulletCount())
                .append(", Spacing: ").append(getMultiShotSpacing())
                .append(")\n");
        status.append("Rapid Fire Level: ").append(rapidFireLevel)
                .append(" (Interval: ").append(getShootingInterval())
                .append(")\n");
        status.append("Penetration Level: ").append(penetrationLevel)
                .append(" (Max Penetration Count: ").append(getPenetrationCount())
                .append(")\n");
        return status.toString();
    }

    /**
     * For testing - sets the Spread Shot to its maximum level.
     */
    public static void setMaxLevelForTesting() {
        multiShotLevel = MAX_MULTI_SHOT_LEVEL;
        rapidFireLevel = MAX_RAPID_FIRE_LEVEL;
        penetrationLevel = MAX_RAPID_FIRE_LEVEL;
    }
}