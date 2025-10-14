package screen;

import java.awt.event.KeyEvent;
import java.awt.Color;
import java.util.List;
import java.util.ArrayList;
import java.util.Random;

import engine.Cooldown;
import engine.Core;
import engine.DrawManager.SpriteType;
import entity.Entity;
import entity.SoundButton;

/**
 * Implements the title screen.
 * 
 * @author <a href="mailto:RobertoIA1987@gmail.com">Roberto Izquierdo Amo</a>
 * 
 */
public class TitleScreen extends Screen {

	/**
	 * A simple class to represent a star for the animated background.
	 */
	public static class Star {
		private float x;
		private float y;
		private float speed;

		public Star(float x, float y, float speed) {
			this.x = x;
			this.y = y;
			this.speed = speed;
		}

		public int getX() {
			return (int) x;
		}

		public int getY() {
			return (int) y;
		}

		public float getFloatX() {
			return x;
		}

		public float getFloatY() {
			return y;
		}

		public float getSpeed() {
			return speed;
		}

		public void setX(float x) {
			this.x = x;
		}

		public void setY(float y) {
			this.y = y;
		}
	}

	/**
	 * A simple class to represent a background enemy.
	 */
	private static class BackgroundEnemy extends Entity {
		private int speed;

		public BackgroundEnemy(int positionX, int positionY, int speed, SpriteType spriteType) {
			super(positionX, positionY, 12 * 2, 8 * 2, Color.WHITE);
			this.speed = speed;
			this.spriteType = spriteType;
		}

		public int getSpeed() {
			return speed;
		}
	}

	/** Milliseconds between changes in user selection. */
	private static final int SELECTION_TIME = 200;
	/** Number of stars in the background. */
	private static final int NUM_STARS = 150;
	/** Milliseconds between enemy spawns. */
	private static final int ENEMY_SPAWN_COOLDOWN = 2000;
	/** Probability of an enemy spawning. */
	private static final double ENEMY_SPAWN_CHANCE = 0.3;
	
	/** Time between changes in user selection. */
	private Cooldown selectionCooldown;
	/** Cooldown for enemy spawning. */
	private Cooldown enemySpawnCooldown;

	/** List of stars for the background animation. */
	private List<Star> stars;
	/** List of background enemies. */
	private List<Entity> backgroundEnemies;

	/** Sound button on/off object. */
	private SoundButton soundButton;

	/** Horizontal direction of star movement. */
	private int starDirectionX;
	/** Vertical direction of star movement. */
	private int starDirectionY;

	/** Random number generator. */
    private Random random;

	/**
	 * Constructor, establishes the properties of the screen.
	 * 
	 * @param width
	 *            Screen width.
	 * @param height
	 *            Screen height.
	 * @param fps
	 *            Frames per second, frame rate at which the game is run.
	 */
	public TitleScreen(final int width, final int height, final int fps) {
		super(width, height, fps);

		// Defaults to play.
		this.returnCode = 2;
		this.soundButton = new SoundButton(0, 0);
		this.selectionCooldown = Core.getCooldown(SELECTION_TIME);
		this.enemySpawnCooldown = Core.getCooldown(ENEMY_SPAWN_COOLDOWN);
		this.selectionCooldown.reset();
		this.enemySpawnCooldown.reset();

		this.random = new Random();
		this.stars = new ArrayList<Star>();
		for (int i = 0; i < NUM_STARS; i++) {
			float speed = (float) (Math.random() * 2.5 + 0.5);
			this.stars.add(new Star((float) (Math.random() * width),
					(float) (Math.random() * height), speed));
		}

		this.backgroundEnemies = new ArrayList<Entity>();

		// Initialize star direction (downwards)
		this.starDirectionX = 0;
		this.starDirectionY = 1;
	}

	/**
	 * Getter for the stars list.
	 * @return List of stars.
	 */
	public final List<Star> getStars() {
		return this.stars;
	}

	/**
	 * Starts the action.
	 * 
	 * @return Next screen code.
	 */
	public final int run() {
		super.run();

		return this.returnCode;
	}

	/**
	 * Updates the elements on screen and checks for events.
	 */
	protected final void update() {
		super.update();

		// Animate stars
		for (Star star : this.stars) {
			star.setX(star.getFloatX() + this.starDirectionX * star.getSpeed());
			star.setY(star.getFloatY() + this.starDirectionY * star.getSpeed());

			// Screen wrapping with randomization
			if (star.getFloatX() < 0) {
				star.setX(this.getWidth());
				star.setY((float) (Math.random() * this.getHeight()));
			} else if (star.getFloatX() > this.getWidth()) {
				star.setX(0);
				star.setY((float) (Math.random() * this.getHeight()));
			}

			if (star.getFloatY() < 0) {
				star.setY(this.getHeight());
				star.setX((float) (Math.random() * this.getWidth()));
			} else if (star.getFloatY() > this.getHeight()) {
				star.setY(0);
				star.setX((float) (Math.random() * this.getWidth()));
			}
		}

		// Spawn and move background enemies
		if (this.enemySpawnCooldown.checkFinished()) {
			this.enemySpawnCooldown.reset();
			if (Math.random() < ENEMY_SPAWN_CHANCE) {
				SpriteType[] enemyTypes = { SpriteType.EnemyShipA1, SpriteType.EnemyShipB1, SpriteType.EnemyShipC1 };
				SpriteType randomEnemyType = enemyTypes[random.nextInt(enemyTypes.length)];
				int randomY = (int) (Math.random() * this.getHeight() * 0.8 + this.getHeight() * 0.1);
				int speed = random.nextInt(2) + 1;
				this.backgroundEnemies.add(new BackgroundEnemy(-20, randomY, speed, randomEnemyType));
			}
		}

		java.util.Iterator<Entity> iterator = this.backgroundEnemies.iterator();
		while (iterator.hasNext()) {
			BackgroundEnemy enemy = (BackgroundEnemy) iterator.next();
			enemy.setPositionX(enemy.getPositionX() + enemy.getSpeed());
			if (enemy.getPositionX() > this.getWidth()) {
				iterator.remove();
			}
		}

		draw();
		if (this.selectionCooldown.checkFinished()
				&& this.inputDelay.checkFinished()) {
			if (inputManager.isKeyDown(KeyEvent.VK_UP)
					|| inputManager.isKeyDown(KeyEvent.VK_W)) {
				previousMenuItem();
				this.selectionCooldown.reset();
			}
			if (inputManager.isKeyDown(KeyEvent.VK_DOWN)
					|| inputManager.isKeyDown(KeyEvent.VK_S)) {
				nextMenuItem();
				this.selectionCooldown.reset();
			}
			if (inputManager.isKeyDown(KeyEvent.VK_SPACE)){
				if (this.returnCode != 5) {
					this.isRunning = false;
				} else if (this.returnCode == 5) {
					this.soundButton.changeSoundState();
					// TODO : Sound setting.

					this.selectionCooldown.reset();
				}
			}
			if (inputManager.isKeyDown(KeyEvent.VK_RIGHT)
					|| inputManager.isKeyDown(KeyEvent.VK_D)) {
				this.returnCode = 5;
				this.soundButton.setColor(Color.GREEN);
				this.selectionCooldown.reset();
			}
			if (this.returnCode == 5 && inputManager.isKeyDown(KeyEvent.VK_LEFT)
					|| inputManager.isKeyDown(KeyEvent.VK_A)) {
				this.returnCode = 4;
				this.soundButton.setColor(Color.WHITE);
				this.selectionCooldown.reset();
			}
		}
	}

	/**
	 * Shifts the focus to the next menu item.
	 */
	private void nextMenuItem() {
		if (this.returnCode == 4)
			this.returnCode = 0;
		else if (this.returnCode == 0)
			this.returnCode = 2;
		else if (this.returnCode == 5) {
			this.soundButton.setColor(Color.WHITE);
			this.returnCode = 0;
		} 
		else
			this.returnCode++;

		// Rotate starfield clockwise
		final int centerX = this.getWidth() / 2;
		final int centerY = this.getHeight() / 2;
		for (Star star : this.stars) {
			float relX = star.getFloatX() - centerX;
			float relY = star.getFloatY() - centerY;
			star.setX(relY + centerX);
			star.setY(-relX + centerY);
		}

		// Rotate direction vector clockwise
		int tempDirX = this.starDirectionX;
		this.starDirectionX = this.starDirectionY;
		this.starDirectionY = -tempDirX;
	}

	/**
	 * Shifts the focus to the previous menu item.
	 */
	private void previousMenuItem() {
		if (this.returnCode == 0)
			this.returnCode = 4;
		else if (this.returnCode == 2)
			this.returnCode = 0;
		else if (this.returnCode == 5) {
			this.soundButton.setColor(Color.WHITE);
			this.returnCode = 3;
		}
		else
			this.returnCode--;

		// Rotate starfield counter-clockwise
		final int centerX = this.getWidth() / 2;
		final int centerY = this.getHeight() / 2;
		for (Star star : this.stars) {
			float relX = star.getFloatX() - centerX;
			float relY = star.getFloatY() - centerY;
			star.setX(-relY + centerX);
			star.setY(relX + centerY);
		}

		// Rotate direction vector counter-clockwise
		int tempDirX = this.starDirectionX;
		this.starDirectionX = -this.starDirectionY;
		this.starDirectionY = tempDirX;
	}

	/**
	 * Draws the elements associated with the screen.
	 */
	private void draw() {
		drawManager.initDrawing(this);

		// Draw stars
		drawManager.drawStars(this, this.stars);

		// Draw background enemies
		for (Entity enemy : this.backgroundEnemies) {
			drawManager.drawEntity(enemy, enemy.getPositionX(), enemy.getPositionY());
		}

		drawManager.drawTitle(this);
		drawManager.drawMenu(this, this.returnCode);
		drawManager.drawEntity(this.soundButton, this.width * 4 / 5 - 16,
				this.height * 4 / 5 - 16);

		drawManager.completeDrawing(this);
	}

	/**
	 * Getter for the sound state.
	 * @return isSoundOn of the sound button.
	 */
	public boolean getIsSoundOn() {
		return this.soundButton.getIsSoundOn();
	}
}
