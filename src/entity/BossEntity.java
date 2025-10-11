package entity;

import engine.DrawManager;
/**
 * Defines the contract for all boss entities that appear in the game.
 * Includes methods related to the boss's lifecycle, interaction, and movement.
 */
public interface BossEntity {

	/**
	 * Getter for the current healPoint of the boss.
	 *
	 * @return Value of the healPoint
	 */
	int getHealPoint();

	/**
	 * Getter for the score bonus if this ship is destroyed.
	 *
	 * @return Value of the ship.
	 */
	int getPointValue();

	/**
	 * Moves the ship the specified distance.
	 *
	 * @param distanceX
	 *            Distance to move in the X axis.
	 * @param distanceY
	 *            Distance to move in the Y axis.
	 */
	void move(int distanceX, int distanceY);

	/**
	 * Handles cleanup logic when the boss is destroyed.
	 */
	void destroy();

	/**
	 * Checks if the boss has been destroyed.
	 *
	 * @return if the boss is destroyed
	 */
	boolean isDestroyed();

	/**
	 * Handles the logic when the boss takes damage.
	 *
	 * @param damage
	 *          Damage the boss gets.
	 */
	void takeDamage(int damage);

	/**
	 * Updates attributes, mainly used for animation purposes.
	 */
	void update();

	/**
	 * Draws the boss on the screen.
	 */
	void draw(DrawManager drawManager);
}