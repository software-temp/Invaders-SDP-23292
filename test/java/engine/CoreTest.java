package engine;

// Import necessary classes
import java.util.logging.Logger;
import engine.Core;
import engine.DrawManager;
import engine.Cooldown;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 * Tests the static methods of the Core class.
 */
class CoreTest {

    @Test
    @DisplayName("getLogger() should return a non-null Logger instance")
    void testGetLogger_shouldReturnNonNull() {
        // Verify that Core.getLogger() returns a non-null Logger object
        Logger logger = Core.getLogger();
        assertNotNull(logger, "Logger instance should not be null.");
        System.out.println("Logger instance test passed!");
    }

    @Test
    @DisplayName("getDrawManager() should return a non-null DrawManager instance")
    void testGetDrawManager_shouldReturnNonNull() {
        // Verify that Core.getDrawManager() returns a non-null DrawManager object
        DrawManager drawManager = Core.getDrawManager();
        assertNotNull(drawManager, "DrawManager instance should not be null.");
        System.out.println("DrawManager instance test passed!");
    }

    @Test
    @DisplayName("getCooldown() should return a non-null Cooldown instance")
    void testGetCooldown_shouldReturnNonNull() {
        // Verify that Core.getCooldown() creates a non-null Cooldown object
        Cooldown cooldown = Core.getCooldown(100); // 100ms cooldown
        assertNotNull(cooldown, "Cooldown instance should not be null.");
        System.out.println("Cooldown instance creation test passed!");
    }
}