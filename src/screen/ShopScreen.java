package screen;

import java.awt.event.KeyEvent;

import engine.Core;
import engine.DrawManager;

public class ShopScreen extends Screen {

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
    public ShopScreen(final int width, final int height, final int fps) {
        super(width, height, fps);

        // Defaults to returning to the title screen.
        this.returnCode = 1;
    }

    /**
     * Starts the action.
     * 
     * @return Next screen code.
     */
    @Override
    public final int run() {
        super.run();

        return this.returnCode;
    }

    /**
     * Updates the elements on screen and checks for events.
     */
    @Override
    protected void update() {
        super.update();

        draw();
        if (this.inputManager.isKeyDown(KeyEvent.VK_SPACE) && this.inputDelay.checkFinished()) {
            this.isRunning = false;
        }
    }

    /**
     * Draws the elements associated with the screen.
     */
    private void draw() {
        drawManager.initDrawing(this);

        drawManager.drawCenteredBigString(this, "SHOP", this.height / 2 - 40);
        drawManager.drawCenteredRegularString(this, "This is the shop.", this.height / 2);
        drawManager.drawCenteredRegularString(this, "Press Space to return", this.height / 2 + 40);

        drawManager.completeDrawing(this);
    }
}
