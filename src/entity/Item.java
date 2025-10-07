package entity;

/**
 * 게임 내 아이템 시스템을 관리하는 클래스
 * 상점에서 구매한 강화 아이템들이 게임에 적용됩니다.
 *
 * 사용 예시(상점 구현 전 임시 방법):
 * Item.setSpreadShotLevel(2);  // 상점에서 2레벨 구매
 * int bulletCount = Item.getSpreadShotBulletCount();  // 발사할 총알 개수 반환
 */
public class Item {

    // ==================== 확산탄 아이템 ====================
    /** 확산탄 레벨 (0 = 미구매, 1~3 = 강화 단계) */
    private static int spreadShotLevel = 0;

    /** 확산탄 최대 레벨 */
    private static final int MAX_SPREAD_SHOT_LEVEL = 3;

    /** 레벨별 발사 총알 개수 (레벨 0~5) */
    private static final int[] SPREAD_SHOT_BULLETS = {1, 2, 3, 4};

    /** 레벨별 총알 간격 (픽셀) */
    private static final int[] SPREAD_SHOT_SPACING = {0, 8, 6, 4};



    // ==================== 확산탄 관련 메서드 ====================

    /**
     * 확산탄 레벨 설정 (상점 구매 시 호출)
     *
     * @param level 설정할 레벨 (0~5)
     * @return 설정 성공 여부
     */
    public static boolean setSpreadShotLevel(int level) {
        if (level < 0 || level > MAX_SPREAD_SHOT_LEVEL) {
            return false;
        }
        spreadShotLevel = level;
        return true;
    }

    /**
     * 현재 확산탄 레벨 반환
     *
     * @return 현재 레벨 (0~5)
     */
    public static int getSpreadShotLevel() {
        return spreadShotLevel;
    }

    /**
     * 확산탄으로 발사할 총알 개수 반환
     *
     * @return 총알 개수 (1~11)
     */
    public static int getSpreadShotBulletCount() {
        return SPREAD_SHOT_BULLETS[spreadShotLevel];
    }

    /**
     * 확산탄 총알 간격 반환
     *
     * @return 총알 간 픽셀 간격
     */
    public static int getSpreadShotSpacing() {
        return SPREAD_SHOT_SPACING[spreadShotLevel];
    }

    /**
     * 확산탄이 활성화되어 있는지 확인
     *
     * @return 레벨이 1 이상이면 true
     */
    public static boolean isSpreadShotActive() {
        return spreadShotLevel > 0;
    }


    // ==================== 전체 초기화 ====================

    /**
     * 모든 아이템을 초기화 (테스트용 또는 게임 리셋 시)
     */
    public static void resetAllItems() {
        spreadShotLevel = 0;
    }


    // ==================== 디버그/테스트 메서드 ====================

    /**
     * 현재 아이템 상태 출력 (디버그용)
     *
     * @return 아이템 상태 문자열
     */
    public static String getItemStatus() {
        StringBuilder status = new StringBuilder();
        status.append("=== Item Status ===\n");
        status.append("Spread Shot Level: ").append(spreadShotLevel)
                .append(" (Bullets: ").append(getSpreadShotBulletCount()).append(")\n");
        return status.toString();
    }

    /**
     * 테스트용 - 모든 아이템을 최대 레벨로 설정
     */
    public static void setMaxLevelForTesting() {
        spreadShotLevel = MAX_SPREAD_SHOT_LEVEL;
    }
}