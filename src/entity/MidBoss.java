package entity;

import java.awt.*;

/**
 * Implements a game middle boss entity.
 *
 * @author <a href="developer.ksb@gmail.com">Seungbeom Kim</a>
 *
 */
public abstract class MidBoss extends Entity implements BossEntity {

    /**
     * Constructor, establishes the entity's generic properties.
     *
     * @param positionX Initial position of the entity in the X axis.
     * @param positionY Initial position of the entity in the Y axis.
     * @param width     Width of the entity.
     * @param height    Height of the entity.
     * @param color     Color of the entity.
     */
    public MidBoss(int positionX, int positionY, int width, int height, Color color) {
        super(positionX, positionY, width, height, color);
    }
}
