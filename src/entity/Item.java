package entity;

public class Item {

    private static long freezeEndTime = 0;

    /**
     * enemy push
     * @param enemyShipFormation
     * @param distanceY
     */
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
     * Freeze Item : all enemy ship never move except special enemy.
     *
     * @param durationMillis 정지 지속 시간 (밀리초)
     */
    public static void applyTimeFreezeItem(int durationMillis) {
        // 현재 시간 + 지속시간 = 종료 시간
        freezeEndTime = System.currentTimeMillis() + durationMillis;
    }

    /**
     * check If Freeze item is activated
     *
     * @return If returning true, don't move all enemy ship except special enemy
     */
    public static boolean isTimeFreezeActive() {
        if (freezeEndTime > 0 && System.currentTimeMillis() < freezeEndTime) {
            return true;
        }
        if (freezeEndTime > 0 && System.currentTimeMillis() >= freezeEndTime) {
            freezeEndTime = 0;
        }
        return false;
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