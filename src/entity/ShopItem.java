package entity;

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
    // ==================== Spread Shot Methods ====================

    /**
     * Sets the Spread Shot level (called upon purchase from a shop).
=======
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

    /** penetration level (0 = not purchased, 1~2 = enhancement levels) */
    private static int penetrationLevel = 0;

    /** maximum penetration level */
    private static final int MAX_PENETRATION_LEVEL = 2;

    /** penetration count */
    private static final int[] PENETRATION_COUNT = {0,1,2};

    //===================== ShipSpeed Item =====================

    private static final int MAX_SHIP_SPEED_LEVEL = 5;

    /** Ship speed per level */
    private static final int[] SHIP_SPEED = {0, 5, 10, 15, 20, 25};

    /** Ship Speed Increase Per Level (%)*/
    private static int SHIPSPEEDLEVEL = 0;



    // ==================== MultiShot Methods ====================

    /**
     * Sets the MultiShot level (called upon purchase from a shop).
>>>>>>> lieutenant
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

    //===================== ShipSpeed Methods ================
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

    // ==================== Utility Methods ====================

    /**
     * Resets all items (for testing or game reset).
     */
    public static void resetAllItems() {
        multiShotLevel = 0;
        rapidFireLevel = 0;
        penetrationLevel = 0;
        SHIPSPEEDLEVEL = 0;
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
        status.append("Ship Speed Level: ").append(SHIPSPEEDLEVEL);
        return status.toString();
    }


    public static void setMaxLevelForTesting() {
        multiShotLevel = MAX_MULTI_SHOT_LEVEL;
        rapidFireLevel = MAX_RAPID_FIRE_LEVEL;
        penetrationLevel = MAX_RAPID_FIRE_LEVEL;
        SHIPSPEEDLEVEL = MAX_SHIP_SPEED_LEVEL;
    }

}