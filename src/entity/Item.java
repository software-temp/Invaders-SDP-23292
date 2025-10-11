package entity;
import java.awt.Color;

import engine.GameState;
import engine.DrawManager.SpriteType;
import java.util.Random;

public class Item extends Entity {
    public enum ItemType {
        /** A shield that protects the player. */
        INVINCIBLE,
        /** An item that gives the player one extra life. */
        HEAL_PACK;

        /**
         *
         * ADD Here ITEM TYPE what you made.
         *
         * */

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

            /**
             * Add ITEMTYPE what you made.
             * EX)
             *case MultiShot:
             *     this.spriteType = SpriteType.Item_MultiShot;
             *     break;
             * case Atkspeed:
             *     this.spriteType = SpriteType.Item_Atkspeed;
             *     break; */
            case INVINCIBLE:
                this.spriteType = SpriteType.Item_Shield;
                break;
            case HEAL_PACK:
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

    /**
     * Check getting a slowdown item.
     *
     * @return if a slowdown item has been gotten.
     */
    public static boolean isGetSlowDown(){
        boolean isSlowDown = false;
        if(getItemStatus().equals("Slowdown")){ // if user get a slowdown item, change isSlowDown to True.
            return !isSlowDown;
        }
        else {
            return null;
        }
    }
}