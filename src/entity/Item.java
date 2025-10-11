package entity;
import java.awt.Color;

import engine.GameState;
import engine.DrawManager.SpriteType;
import java.util.Random;

public class Item extends Entity {
    public enum ItemType {
        MultiShot(0),
        Atkspeed(0),
        Penetrate(0),
        Explode(2),
        Slow(10),
        Stop(10),
        Push(5),
        Shield(5),
        Heal(5);

        private final int weight;

        ItemType(final int weight) {
            this.weight = weight;
        }

        public int getWeight() {
            return this.weight;
        }

        private static final ItemType[] VALUES = values();
        private static final int SIZE = VALUES.length;
        private static final Random RANDOM = new Random();

        private static final int TOTAL_WEIGHT;

        static {
            int sum = 0;
            for (ItemType type : VALUES) {
                sum += type.weight;
            }
            TOTAL_WEIGHT = sum;
        }

        /**
         * 가중치에 기반하여 랜덤 ItemType을 반환합니다.
         *
         * @return 가중치에 따라 선택된 ItemType
         */

        public static ItemType selectItemType() {
            int randomWeight = RANDOM.nextInt(TOTAL_WEIGHT);
            int cumulativeWeight = 0;

            for (ItemType type : VALUES) {
                cumulativeWeight += type.weight;

                if (randomWeight < cumulativeWeight) {
                    return type;
                }
            }
            return VALUES[0];
        }
    }

    private int speed;
    private ItemType itemType;
    public Item(final int positionX, final int positionY, final int speed, final ItemType itemType) {
        super(positionX, positionY, 5 * 2, 5 * 2, Color.WHITE);
        this.speed = speed;
        this.itemType = itemType;
        this.color = Color.WHITE;
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
                this.color = Color.RED;
                this.spriteType = SpriteType.Item_Explode;
                break;
            case Slow:
                this.color = Color.BLUE;
                this.spriteType = SpriteType.Item_Slow;
                break;
            case Stop:
                this.color = Color.BLUE;
                this.spriteType = SpriteType.Item_Stop;
                break;
            case Push:
                this.color = Color.BLUE;
                this.spriteType = SpriteType.Item_Push;
                break;
            case Shield:
                this.color = Color.CYAN;
                this.spriteType = SpriteType.Item_Shield;
                break;
            case Heal:
                this.color = Color.GREEN;
                this.spriteType = SpriteType.Item_Heal;
                break;
        }
    }

    public final void update(){
        this.positionY += this.speed;
    }

    public final void setSpeed(final int speed) {
        this.speed = speed;
    }

    public final int getSpeed() {
        return this.speed;
    }

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