package entity;

/**
 * Manages the in-game item (enhancement) system.
 * This is a temporary implementation focusing on functionality.
 *
 * Currently implemented: MultiShot, Bullet Speed
 *
 * Example usage:
 * Item.setMultiShotLevel(2);  // Purchase level 2 in the shop
 */
public class ShopItem {

    // ==================== MultiShot Item ====================

    /** Spread Shot level (0 = not purchased, 1-3 = enhancement levels) */
    private static int multiShotLevel = 0;

    /** Maximum Spread Shot level */
    private static final int MAX_MULTI_SHOT_LEVEL = 3;

    /** Number of bullets fired per level */
    private static final int[] MULTI_SHOT_BULLETS = {1, 2, 3, 4};

    /** Spacing between bullets per level (in pixels) */
    private static final int[] MULTI_SHOT_SPACING = {0, 10, 8, 5};

    // ==================== Bullet Speed Item ====================

    /** Bullet Speed level (0 = not purchased, 1-3 = enhancement levels) */
    private static int bulletSpeedLevel = 0;

    /** Maximum Bullet Speed level */
    private static final int MAX_BULLET_SPEED_LEVEL = 3;

    /** Bullet speed value per level */
    private static final int[] BULLET_SPEED_VALUES = {-6, -8, -10, -12};

    /**
     * Private constructor - this class should not be instantiated.
     * It is intended to be used only with static methods.
     */
    private ShopItem() {

    }


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
     * Checks if the Spread Shot is active.
     *
     * @return true if the level is 1 or higher, false otherwise.
     */
    public static boolean isMultiShotActive() {
        return multiShotLevel > 0;
    }

    // ==================== Bullet Speed Methods ====================

    /**
     * Sets the Bullet Speed level (called upon purchase from a shop).
     *
     * @param level The level to set (0-3).
     * @return true if the level was set successfully, false otherwise.
     */
    public static boolean setBulletSpeedLevel(int level) {
        if (level < 0 || level > MAX_BULLET_SPEED_LEVEL) {
            return false;
        }
        bulletSpeedLevel = level;
        return true;
    }

    /**
     * Returns the current Bullet Speed level.
     *
     * @return The current level (0-3).
     */
    //
    public static int getBulletSpeedLevel() {
        return bulletSpeedLevel;
    }

    /**
     * Returns the bullet speed for the current enhancement level.
     *
     * @return The bullet speed.
     */
    public static int getBulletSpeed() {
        return BULLET_SPEED_VALUES[bulletSpeedLevel];
    }

    // ==================== Utility Methods ====================

    /**
     * Resets all items (for testing or game reset).
     */
    public static void resetAllItems() {
        multiShotLevel = 0;
        bulletSpeedLevel = 0;
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
                .append("px)\n");
        status.append("Bullet Speed Level: ").append(bulletSpeedLevel)
                .append(" (Speed: ").append(getBulletSpeed())
                .append("px/)\n");
        return status.toString();
    }

    /**
     * For testing - sets the Spread Shot to its maximum level.
     */
    public static void setMaxLevelForTesting() {
        multiShotLevel = MAX_MULTI_SHOT_LEVEL;
        bulletSpeedLevel = MAX_BULLET_SPEED_LEVEL;
    }
}