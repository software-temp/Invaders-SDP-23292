package entity;
import java.awt.Color;

import engine.GameState;
import engine.DrawManager.SpriteType;
import java.util.Random;

public class Item extends Entity {
    public enum ItemType {
        MultiShot,
        Atkspeed,
        Penetrate,
        Explode,
        Slow,
        Stop,
        Push,
        /** A shield that protects the player. */
        INVINCIBLE,
        /** An item that gives the player one extra life. */
        HEAL_PACK;

        private static final ItemType[] VALUES = values();
        private static final int SIZE = VALUES.length;
        private static final Random RANDOM = new Random();

        public static ItemType selectItemType() {
            return VALUES[RANDOM.nextInt(SIZE)];
        }
    }

    /** Speed of the item, positive is down. */
    private int speed;
    /** Type of the item. */
    private ItemType itemType;
    public Item(final int positionX, final int positionY, final int speed, final ItemType itemType) {
        super(positionX, positionY, 5 * 5, 5 * 5, Color.WHITE);
        this.speed = speed;
        this.itemType = itemType;

        setSprite();
    }

    public final void setSprite(){
        switch (this.itemType) {
            case MultiShot:
                this.spriteType = SpriteType.Item_MultiShot;
                break;
            case Atkspeed:
                this.spriteType = SpriteType.Item_Atkspeed;
                break;
            case Penetrate:
                this.spriteType = SpriteType.Item_Penetrate;
                break;
            case Explode:
                this.spriteType = SpriteType.Item_Explode;
                break;
            case Slow:
                this.spriteType = SpriteType.Item_Slow;
                break;
            case Stop:
                this.spriteType = SpriteType.Item_Stop;
                break;
            case Push:
                this.spriteType = SpriteType.Item_Push;
                break;
            case Shield:
                this.spriteType = SpriteType.Item_Shield;
                break;
            case Heal:
                this.spriteType = SpriteType.Item_Heal;
                break;
        }
    }

    /**
     * Updates the item's position.
     */
    public final void update() {
        this.positionY += this.speed;
    }

    public final void setSpeed(final int speed) {
        this.speed = speed;
    }

    public final int getSpeed() {
        return this.speed;
    }

    /**
     * Getter for the item's type.
     *
     * @return Type of the item.
     */
    public final ItemType getItemType() {
        return this.itemType;
    }

    public final void setItemType(final ItemType itemType) {
        this.itemType = itemType;
        this.setSprite();
    }
    public static ItemType getRandomItemType(final double proba) {
        if (Math.random() < proba){
            return ItemType.selectItemType();
        }
        else {
            return null;
        }
    }

    public void applyEffect(GameState gameState) {
        switch (this.itemType) {
            case MultiShot:
                break;
            case Atkspeed:
                break;
            case Penetrate:
                break;
            case Explode:
                break;
            case Slow:
                break;
            case Stop:
                break;
            case Push:
                break;
            case Shield:
                break;
            case Heal:
                break;
        }
    }
}