package entity;

import engine.DrawManager;

import java.awt.*;
import java.util.HashSet;
import java.util.Set;

public class BossBullet extends Entity{
    private int dx;
    private int dy;
    private static Set<BossBullet> bossBullets = new HashSet<>();

    public BossBullet(int x, int y, int dx, int dy, int width, int height) {
        super(x, y, width, height, Color.YELLOW);
        this.dx = dx;
        this.dy = dy;
        bossBullets.add(this);
        this.spriteType = DrawManager.SpriteType.EnemyBullet;
    }

    public void update() {
        this.positionX += this.dx;
        this.positionY += this.dy;
    }

    public boolean isOffScreen(int screenWidth, int screenHeight) {
        return positionX < 0 || positionX > screenWidth ||
                positionY < 0 || positionY > screenHeight;
    }

    public boolean collidesWith(Entity other) { return this.getPositionX() < other.getPositionX() + other.getWidth() && this.getPositionX() + this.getWidth() > other.getPositionX() && this.getPositionY() < other.getPositionY() + other.getHeight() && this.getPositionY() + this.getHeight() > other.getPositionY(); }

    public static Set<BossBullet> getBossBullets() {
        return bossBullets;
    }


}
