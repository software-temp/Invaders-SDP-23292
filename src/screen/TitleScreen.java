package screen;

import java.awt.event.KeyEvent;
import java.awt.Color;
import java.util.List;
import java.util.ArrayList;

import engine.Cooldown;
import engine.Core;
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
		private int x;
		private int y;

		public Star(int x, int y) {
			this.x = x;
			this.y = y;
		}

		public int getX() {
			return x;
		}

		public int getY() {
			return y;
		}

		public void setY(int y) {
			this.y = y;
		}
		
		public void setX(int x) {
			this.x = x;
		}
	}

	/** Milliseconds between changes in user selection. */
	private static final int SELECTION_TIME = 200;
	/** Number of stars in the background. */
	private static final int NUM_STARS = 150;
	
	/** Time between changes in user selection. */
	private Cooldown selectionCooldown;

	/** List of stars for the background animation. */
	private List<Star> stars;

	/** Sound button on/off object. */
	private SoundButton soundButton;

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
		this.selectionCooldown.reset();

		// Initialize stars for background
		this.stars = new ArrayList<Star>();
		for (int i = 0; i < NUM_STARS; i++) {
			this.stars.add(new Star((int) (Math.random() * width),
					(int) (Math.random() * height)));
		}
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
			star.setY(star.getY() + 1);
			if (star.getY() > this.getHeight()) {
				star.setY(0);
				star.setX((int) (Math.random() * this.getWidth()));
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
	}

	/**
	 * Draws the elements associated with the screen.
	 */
	private void draw() {
		drawManager.initDrawing(this);

		// Draw stars
		drawManager.drawStars(this, this.stars);

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