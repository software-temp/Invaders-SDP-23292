package engine;

// 필요한 클래스들을 import
import java.util.logging.Logger;
import engine.Core;
import engine.DrawManager;
import engine.Cooldown;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 * Core 클래스의 정적 메소드들을 테스트합니다.
 */
class CoreTest {

    @Test
    @DisplayName("getLogger() should return a non-null Logger instance")
    void testGetLogger_shouldReturnNonNull() {
        // Core.getLogger()가 null이 아닌 Logger 객체를 반환하는지 확인
        Logger logger = Core.getLogger();
        assertNotNull(logger, "Logger 인턴스는 null이 아니어야 합니다.");
        System.out.println("Logger 인스턴스 테스트 통과!");
    }

    @Test
    @DisplayName("getDrawManager() should return a non-null DrawManager instance")
    void testGetDrawManager_shouldReturnNonNull() {
        // Core.getDrawManager()가 null이 아닌 DrawManager 객체를 반환하는지 확인
        DrawManager drawManager = Core.getDrawManager();
        assertNotNull(drawManager, "DrawManager 인스턴스는 null이 아니어야 합니다.");
        System.out.println("DrawManager 인스턴스 테스트 통과!");
    }

    @Test
    @DisplayName("getCooldown() should return a non-null Cooldown instance")
    void testGetCooldown_shouldReturnNonNull() {
        // Core.getCooldown()이 null이 아닌 Cooldown 객체를 생성하는지 확인
        Cooldown cooldown = Core.getCooldown(100); // 100ms 쿨다운
        assertNotNull(cooldown, "Cooldown 인스턴스는 null이 아니어야 합니다.");
        System.out.println("Cooldown 인스턴스 생성 테스트 통과!");
    }
}