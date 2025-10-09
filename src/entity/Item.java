package entity;

import entity.EnemyShip;
import entity.EnemyShipFormation;

public class Item {

    private static long freezeEndTime = 0;

    public static void PushbackItem(EnemyShipFormation enemyShipFormation, int distanceY) {
        if (enemyShipFormation == null) {
            return;
        }

        // 편대 내 모든 적 함선을 일제히 뒤로 밀어냄
        for (EnemyShip enemy : enemyShipFormation) {
            if (enemy != null && !enemy.isDestroyed()) {
                // backmove 메서드를 사용하여 위로 이동
                enemy.move(0,-distanceY);
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