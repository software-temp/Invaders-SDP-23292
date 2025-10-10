package entity;

import engine.DrawManager;

import java.awt.*;

/**
 * Omega - Middle Boss
 */
public class OmegaBoss extends MidBoss {
    /**
     * public abstract class MidBoss extends Entity implements BossEntity
     * Constructor, establishes the boss entity's generic properties.
     *
     * @param positionX Initial position of the boss entity in the X axis.
     * @param positionY Initial position of the boss entity in the Y axis.
     * @param width     Width of the boss entity.
     * @param height    Height of the boss entity.
     * @param color     Color of the boss entity.
     */
    public OmegaBoss(int positionX, int positionY, int width, int height, Color color) {
        super(positionX, positionY, width, height, color);
        this.healPoint=10;
        this.maxHp=healPoint;
        this.pointValue=500;
        this.spriteType= DrawManager.SpriteType.EnemyShipSpecial;
    }

    @Override
    public void move(int distanceX, int distanceY) {

    }

    @Override
    public void destroy() {

    }

    @Override
    public void takeDamage(int damage) {

    }

    @Override
    public void update() {

    }

    @Override
    public void draw(DrawManager drawManager) {

    }
}
