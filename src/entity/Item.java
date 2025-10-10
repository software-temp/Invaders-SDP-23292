package entity;

import java.awt.Color;

/**
 * Implements a generic item that falls from the screen.
 */
public class Item extends Entity {

    /** Speed of the item, positive is down. */
    private int speed;
    /** Type of the item. */
    private ItemType itemType;

    /**
     * Enum for the different types of items.
     */
    public enum ItemType {
        /** A shield that protects the player. */
        INVINCIBLE,
        /** An item that gives the player one extra life. */
        HEAL_PACK
    }

    /**
     * Constructor, establishes the item's properties.
     *
     * @param positionX
     *            Initial position of the item in the X axis.
     * @param positionY
     *            Initial position of the item in the Y axis.
     * @param itemType
     *            Type of the item.
     */
    public Item(final int positionX, final int positionY, final ItemType itemType) {
        super(positionX, positionY, 8 * 2, 8 * 2, Color.ORANGE);
        this.speed = 2; // Set a default falling speed
        this.itemType = itemType;
        // setSprite() will be implemented later.
    }

    /**
     * Updates the item's position.
     */
    public final void update() {
        this.positionY += this.speed;
    }

    /**
     * Getter for the item's type.
     *
     * @return Type of the item.
     */
    public final ItemType getItemType() {
        return this.itemType;
    }
}