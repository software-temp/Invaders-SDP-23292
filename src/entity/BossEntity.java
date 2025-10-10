package entity;

import engine.DrawManager;
/**
 * Defines the contract for all boss entities that appear in the game.
 * Includes methods related to the boss's lifecycle, interaction, and movement.
 */
public interface Boss {

    /** Updates the boss's state every frame. */
    void update();

    /** Handles the logic when the boss takes damage. */
    void takeDamage(int damage);

    /** Returns the current healpoint of the boss. */
    int getHealPoint();

    /** Returns the score value awarded for defeating the boss. */
    int getPointValue();

    /** Move boss */
    void move();

    /** Draws the boss on the screen. */
    void draw(DrawManager drawManager);

    /** Handles cleanup logic when the boss is destroyed. */
    void destroy();

    /** Checks if the boss has been destroyed. */
    boolean isDestroyed();
}