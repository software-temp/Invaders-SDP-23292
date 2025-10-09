package entity;

import entity.EnemyShip;
import entity.EnemyShipFormation;

public class Item {

    private static long freezeEndTime = 0;
    // ==================== Spread Shot Item ====================

    /**
     * Spread Shot level (0 = not purchased, 1-3 = enhancement levels)
     */
    private static int spreadShotLevel = 0;

    /**
     * Maximum Spread Shot level
     */
    private static final int MAX_SPREAD_SHOT_LEVEL = 3;

    /**
     * Number of bullets fired per level
     */
    private static final int[] SPREAD_SHOT_BULLETS = {1, 2, 3, 4};

    /**
     * Spacing between bullets per level (in pixels)
     */
    private static final int[] SPREAD_SHOT_SPACING = {0, 10, 8, 5};


    /**
     * Private constructor - this class should not be instantiated.
     * It is intended to be used only with static methods.
     */
    private Item() {
    }


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
        return status.toString();
    }

    /**
     * For testing - sets the Spread Shot to its maximum level.
     */
    public static void setMaxLevelForTesting() {
        spreadShotLevel = MAX_SPREAD_SHOT_LEVEL;
    }

    public static void PushbackItem(EnemyShipFormation enemyShipFormation, int distanceY) {
        if (enemyShipFormation == null) {
            return;
        }

        // 편대 내 모든 적 함선을 일제히 뒤로 밀어냄
        for (EnemyShip enemy : enemyShipFormation) {
            if (enemy != null && !enemy.isDestroyed()) {
                // backmove 메서드를 사용하여 위로 이동
                enemy.move(0, -distanceY);
            }
        }
    }

    /**
     * 타임프리즈 아이템: 모든 적 함선을 일정 시간 동안 일시정지 시킵니다
     *
     * @param durationMillis 정지 지속 시간 (밀리초)
     */
    public static void applyTimeFreezeItem(int durationMillis) {
        // 현재 시간 + 지속시간 = 종료 시간
        freezeEndTime = System.currentTimeMillis() + durationMillis;
    }

    /**
     * 현재 타임프리즈가 활성화되어 있는지 확인
     *
     * @return true면 타임프리즈 활성화 중 (적들이 움직이면 안됨)
     */
    public static boolean isTimeFreezeActive() {
        if (freezeEndTime > 0 && System.currentTimeMillis() < freezeEndTime) {
            return true;  // 아직 정지 시간이 남음
        }
        if (freezeEndTime > 0 && System.currentTimeMillis() >= freezeEndTime) {
            freezeEndTime = 0;  // 시간 끝남, 리셋
        }
        return false;  // 정지 아님
    }
}
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