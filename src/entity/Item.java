package entity;
import java.awt.Color;
import engine.DrawManager.SpriteType;

/**
 * Implement all items that appearing in the game.
 */
public class Item extends Entity{
    public static enum Passive {
        SPEED_UP, SHIELD;
    }
    private Passive passive;


    public final Passive getPassive() {
        return this.passive;
    }


}
