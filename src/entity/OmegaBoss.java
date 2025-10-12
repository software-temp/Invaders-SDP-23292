package entity;

import engine.DrawManager;
import engine.Cooldown;
import engine.Core;
import java.awt.*;

/**
 * Omega - Middle Boss
 */
public class OmegaBoss extends MidBoss {

	/** Initial position in the x-axis. */
	private static final int INIT_POS_X = 224;
	/** Initial position in the y-axis. */
	private static final int INIT_POS_Y = 50;
	/** Width of the Boss */
	private static final int BOSS_WIDTH = 32;
	/** Height of the Boss */
	private static final int BOSS_HEIGHT = 14;
	/** Current horizontal movement direction. true for right, false for left. */
	private boolean isRight = true;
	/** Current vertical movement direction. true for down, false for up. */
	private boolean isDown = true;

	/**
	 * Constructor, establishes the boss entity's generic properties.
	 *
	 * @param color     Color of the boss entity.
	 */
	public OmegaBoss(Color color) {
		super(INIT_POS_X, INIT_POS_Y, BOSS_WIDTH, BOSS_HEIGHT, color);
		this.logger = Core.getLogger();
		this.healPoint=10;
		this.maxHp=healPoint;
		this.pointValue=500;
		this.spriteType= DrawManager.SpriteType.EnemyShipSpecial;

		this.logger.info("OMEGA : Initializing Boss OMEGA");
		this.logger.info("OMEGA : move using the default pattern");
	}

	/** move simple */
	@Override
	public void move(int distanceX, int distanceY) {
		this.positionX += distanceX;
		this.positionY += distanceY;
	}

	/**
	 * Executes the appropriate move pattern based on the boss's health status.
	 * Calls {@link #patternFirst()} if health is greater than half, otherwise
	 * Calls {@link #patternSecond()}.
	 *
	 * @see #patternFirst()
	 * @see #patternSecond()
	 */
	private void movePatterns(){
		if(this.pattern!=2 && this.healPoint < this.maxHp/2){
			this.pattern=2;
			this.color=Color.magenta;
			logger.info("OMEGA : move using second pattern");
		}

		switch(pattern){
			case 1:
				this.patternFirst();
				break;
			case 2:
				this.patternSecond();
				break;
		}
	}

	/**
	 * The boss's phase first pattern, which makes it move from side to side across the screen.
	 * @see #move(int, int)
	 */
	private void patternFirst(){
		int dx = this.isRight ? 1 : -1;
		this.move(dx, 0);

		if (this.positionX <= 0) {
			this.isRight = true;
		} else if (this.positionX + this.width >= screen.getWidth()) {
			this.isRight = false;
		}
	}

	/**
	 * The boss's phase Second pattern, which combines horizontal and vertical movement
	 * Horizontally, it patrols from side to side at a faster speed than in {@link #patternFirst()}.
	 * @see #move(int, int)
	 */
	private void patternSecond(){
		int dx = this.isRight ? 3 : -3;
		int dy = this.isDown ? 1 : -1;

		this.move(dx, dy);

		if (this.positionX <= 0) {
			this.positionX = 0;
			this.isRight = true;
		} else if (this.positionX + this.width >= screen.getWidth()) {
			this.positionX = screen.getWidth() - this.width;
			this.isRight = false;
		}

		if (this.positionY <= INIT_POS_Y) {
			this.positionY = INIT_POS_Y;
			this.isDown = true;
		} else if (this.positionY + this.height >= screen.getHeight()) {
			this.positionY = screen.getHeight() - this.height;
			this.isDown = false;
		}
	}

	/** Marks the entity as destroyed and changes its sprite to an explosion. */
	@Override
	public void destroy() {
		this.isDestroyed = true;
		this.spriteType = DrawManager.SpriteType.Explosion;
		this.logger.info("OMEGA : Boss OMEGA destroyed!");
	}

	/**
	 * Reduces health and destroys the entity if it drops to zero or below.
	 *
	 * @param damage The amount of damage to inflict.
	 */
	@Override
	public void takeDamage(int damage) {
		this.healPoint -= damage;
	}

	/**
	 * Updates the entity's state for the current game frame.
	 * This method is called on every tick of the game loop and is responsible for
	 * executing the boss's movement patterns.
	 */
	@Override
	public void update() {
		this.movePatterns();
	}

	/** Renders the entity at its current position using the provided DrawManager. */
	@Override
	public void draw(DrawManager drawManager) {
		drawManager.drawEntity(this, this.positionX, this.positionY);
	}
}
