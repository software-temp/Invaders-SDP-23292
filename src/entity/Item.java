package entity;
import java.awt.Color;

import engine.GameState;
import engine.DrawManager.SpriteType;
import java.util.Random;

public class Item extends Entity {
    public enum ItemType {

        /** An item that makes enemy to slow down. */
        SLOWDOWN,
        /** A shield that protects the player. */
        INVINCIBLE,
        /** An item that gives the player one extra life. */
        HEAL_PACK,
        PUSH,
        STOP,
        /** An item that destroys all enemies on screen. */
        EXPLODE;

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
            case STOP:
                this.spriteType = SpriteType.Item_Stop;
                break;
            case PUSH:
                this.spriteType = SpriteType.Item_Push;
                break;
            case SLOWDOWN:
                this.spriteType = SpriteType.Item_Slow;
                break;
            case INVINCIBLE:
                this.spriteType = SpriteType.Item_Shield;
                break;
            case HEAL_PACK:
                this.spriteType = SpriteType.Item_Heal;
                break;
            case EXPLODE:
                this.spriteType = SpriteType.Item_Explode;
                break;
        }
    }

    private static long freezeEndTime = 0;

    /**
     * enemy push
     * @param enemyShipFormation
     * @param distanceY
     */
    public static void PushbackItem(EnemyShipFormation enemyShipFormation, int distanceY) {
        if (enemyShipFormation == null) {
            return;
        }

        // All enemyship push
        for (EnemyShip enemy : enemyShipFormation) {
            if (enemy != null && !enemy.isDestroyed()) {
                enemy.move(0, -distanceY);
            }
        }
    }

    /**
     * Freeze Item : all enemy ship never move except special enemy.
     *
     * @param durationMillis
     *                  Freeze duration Time
     */
    public static void applyTimeFreezeItem(int durationMillis) {
        // current Time + duration Time = End Time
        freezeEndTime = System.currentTimeMillis() + durationMillis;
    }

    /**
     * check If Freeze item is activated
     *
     * @return If returning true, don't move all enemy ship except special enemy
     */
    public static boolean isTimeFreezeActive() {
        if (freezeEndTime > 0 && System.currentTimeMillis() < freezeEndTime) {
            return true;
        }
        if (freezeEndTime > 0 && System.currentTimeMillis() >= freezeEndTime) {
            freezeEndTime = 0;
        }
        return false;
    }
/**
 * Manages the in-game item (enhancement) system.
 * This is a temporary implementation focusing on functionality.
 *
 * Currently implemented: Spread Shot
 *
 * Example usage:
 * Item.setSpreadShotLevel(2);  // Purchase level 2 in the shop
 * int bulletCount = Item.getSpreadShotBulletCount();  // Returns the number of bullets to fire
 */
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
}