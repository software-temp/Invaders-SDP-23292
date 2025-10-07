package entity;

import engine.GameState;
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
    /**
     * 확산탄 레벨 (0 = 미구매, 1~3 = 강화 단계)
     */
    private static int spreadShotLevel = 0;

    /**
     * 확산탄 최대 레벨
     */
    private static final int MAX_SPREAD_SHOT_LEVEL = 3;

    /**
     * 레벨별 발사 총알 개수 (레벨 0~5)
     */
    private static final int[] SPREAD_SHOT_BULLETS = {1, 2, 3, 4};

    /**
     * 레벨별 총알 간격 (픽셀)
     */
    private static final int[] SPREAD_SHOT_SPACING = {0, 8, 6, 4};


    // ==================== 연사력 아이템 ====================
    /**
     * 연사력 레벨 (0 = 미구매, 1~5 = 강화 단계)
     */
    private static int rapidFireLevel = 0;

    /**
     * 연사력 최대 레벨
     */
    private static final int MAX_RAPID_FIRE_LEVEL = 5;

    /**
     * 기본 발사 간격 (밀리초)
     */
    private static final int BASE_SHOOTING_INTERVAL = 750;

    /**
     * 레벨별 발사 간격 감소율 (%)
     */
    private static final int[] RAPID_FIRE_REDUCTION = {0, 10, 20, 30, 40, 50};


    // ==================== 총알 속도 아이템 ====================
    /**
     * 총알 속도 레벨 (0 = 미구매, 1~5 = 강화 단계)
     */
    private static int bulletSpeedLevel = 0;

    /**
     * 총알 속도 최대 레벨
     */
    private static final int MAX_BULLET_SPEED_LEVEL = 5;

    /**
     * 기본 총알 속도
     */
    private static final int BASE_BULLET_SPEED = 6;

    /**
     * 레벨별 속도 증가
     */
    private static final int[] BULLET_SPEED_BONUS = {0, 1, 2, 3, 4, 5};


    // ==================== 이동 속도 아이템 ====================
    /**
     * 이동 속도 레벨 (0 = 미구매, 1~3 = 강화 단계)
     */
    private static int movementSpeedLevel = 0;

    /**
     * 이동 속도 최대 레벨
     */
    private static final int MAX_MOVEMENT_SPEED_LEVEL = 3;

    /**
     * 기본 이동 속도
     */
    private static final int BASE_MOVEMENT_SPEED = 2;

    /**
     * 레벨별 속도 증가
     */
    private static final int[] MOVEMENT_SPEED_BONUS = {0, 1, 2, 3};


    // ==================== 추가 생명 아이템 ====================
    /**
     * 추가 생명 개수
     */
    private static int extraLives = 0;

    /**
     * 최대 추가 생명
     */
    private static final int MAX_EXTRA_LIVES = 3;


    /**
     * Private 생성자 - 이 클래스는 인스턴스를 만들지 않음
     * static 메서드로만 사용
     */
    private Item() {
    }


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


    // ==================== 연사력 관련 메서드 ====================

    /**
     * 연사력 레벨 설정 (상점 구매 시 호출)
     *
     * @param level 설정할 레벨 (0~5)
     * @return 설정 성공 여부
     */
    public static boolean setRapidFireLevel(int level) {
        if (level < 0 || level > MAX_RAPID_FIRE_LEVEL) {
            return false;
        }
        rapidFireLevel = level;
        return true;
    }

    /**
     * 현재 연사력 레벨 반환
     *
     * @return 현재 레벨 (0~5)
     */
    public static int getRapidFireLevel() {
        return rapidFireLevel;
    }

    /**
     * 현재 발사 간격 반환 (밀리초)
     *
     * @return 발사 간격
     */
    public static int getShootingInterval() {
        int reduction = RAPID_FIRE_REDUCTION[rapidFireLevel];
        return BASE_SHOOTING_INTERVAL * (100 - reduction) / 100;
    }


    // ==================== 총알 속도 관련 메서드 ====================

    /**
     * 총알 속도 레벨 설정 (상점 구매 시 호출)
     *
     * @param level 설정할 레벨 (0~5)
     * @return 설정 성공 여부
     */
    public static boolean setBulletSpeedLevel(int level) {
        if (level < 0 || level > MAX_BULLET_SPEED_LEVEL) {
            return false;
        }
        bulletSpeedLevel = level;
        return true;
    }

    /**
     * 현재 총알 속도 레벨 반환
     *
     * @return 현재 레벨 (0~5)
     */
    public static int getBulletSpeedLevel() {
        return bulletSpeedLevel;
    }

    /**
     * 현재 총알 속도 반환
     *
     * @return 총알 속도 (음수 = 위로 발사)
     */
    public static int getBulletSpeed() {
        return -(BASE_BULLET_SPEED + BULLET_SPEED_BONUS[bulletSpeedLevel]);
    }


    // ==================== 이동 속도 관련 메서드 ====================

    /**
     * 이동 속도 레벨 설정 (상점 구매 시 호출)
     *
     * @param level 설정할 레벨 (0~5)
     * @return 설정 성공 여부
     */
    public static boolean setMovementSpeedLevel(int level) {
        if (level < 0 || level > MAX_MOVEMENT_SPEED_LEVEL) {
            return false;
        }
        movementSpeedLevel = level;
        return true;
    }

    /**
     * 현재 이동 속도 레벨 반환
     *
     * @return 현재 레벨 (0~5)
     */
    public static int getMovementSpeedLevel() {
        return movementSpeedLevel;
    }

    /**
     * 현재 이동 속도 반환
     *
     * @return 이동 속도
     */
    public static int getMovementSpeed() {
            return BASE_MOVEMENT_SPEED + MOVEMENT_SPEED_BONUS[movementSpeedLevel];
    }


    // ==================== 추가 생명 관련 메서드 ====================

    /**
     * 추가 생명 설정 (상점 구매 시 호출)
     *
     * @param lives 추가할 생명 개수 (0~3)
     * @return 설정 성공 여부
     */
    public static boolean setExtraLives(int lives) {
        if (lives < 1 || lives >= MAX_EXTRA_LIVES) {
            extraLives++;
            return false;
        }
        extraLives = lives;
        return true;
    }

    /**
     * 추가 생명 개수 반환
     *
     * @return 추가 생명 개수
     */
    public static int getExtraLives() {

            return extraLives;
    }


    // ==================== 전체 초기화 ====================

    /**
     * 모든 아이템을 초기화 (테스트용 또는 게임 리셋 시)
     */
    public static void resetAllItems() {
        spreadShotLevel = 0;
        rapidFireLevel = 0;
        bulletSpeedLevel = 0;
        movementSpeedLevel = 0;
        extraLives = 0;
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
        status.append("Rapid Fire Level: ").append(rapidFireLevel)
                .append(" (Interval: ").append(getShootingInterval()).append("ms)\n");
        status.append("Bullet Speed Level: ").append(bulletSpeedLevel)
                .append(" (Speed: ").append(getBulletSpeed()).append(")\n");
        status.append("Movement Speed Level: ").append(movementSpeedLevel)
                .append(" (Speed: ").append(getMovementSpeed()).append(")\n");
        status.append("Extra Lives: ").append(extraLives).append("\n");
        return status.toString();
    }

    /**
     * 테스트용 - 모든 아이템을 최대 레벨로 설정
     */
    public static void setMaxLevelForTesting() {
        spreadShotLevel = MAX_SPREAD_SHOT_LEVEL;
        rapidFireLevel = MAX_RAPID_FIRE_LEVEL;
        bulletSpeedLevel = MAX_BULLET_SPEED_LEVEL;
        movementSpeedLevel = MAX_MOVEMENT_SPEED_LEVEL;
        extraLives = MAX_EXTRA_LIVES;
    }
}