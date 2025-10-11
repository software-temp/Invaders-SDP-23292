package entity;

import engine.DrawManager;
import engine.Cooldown;
import engine.Core;
import java.awt.*;
import screen.GameScreen;

/**
 * Omega - Middle Boss
 */
public class OmegaBoss extends MidBoss {

    /** for move pattern */
    private boolean isRight = true;
    /** for move pattern */
    private boolean isDown = true;
    private final GameScreen screen;
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
    public OmegaBoss(int positionX, int positionY, int width, int height, GameScreen screen, Color color) {
        super(positionX, positionY, width, height, screen, color);
        this.screen = screen;
        this.logger = Core.getLogger();
        this.healPoint=10;
        this.maxHp=healPoint;
        this.pointValue=500;
        this.spriteType= DrawManager.SpriteType.EnemyShipSpecial;

        this.logger.info("OMEGA : Initializing Boss OMEGA");
        this.logger.info("OMEGA : move using the default pattern");
    }

    /** move simple */
    @Override
    public void move(int distanceX, int distanceY) {
        this.positionX += distanceX;
        this.positionY += distanceY;
    }

    /**
     * Executes the appropriate move pattern based on the boss's health status.
     * Calls {@link #patternFirst()} if health is greater than half, otherwise
     * Calls {@link #patternSecond()}.
     *
     * @see #patternFirst()
     * @see #patternSecond()
     */
    private void movePatterns(){
        if(this.healPoint > this.maxHp/2){
            this.patternFirst();
        }else{
            this.patternSecond();
        }
    }

    /**
     * The boss's phase first pattern, which makes it move from side to side across the screen.
     * @see #move(int, int)
     */
    private void patternFirst(){
        if(this.isRight){
            if(this.positionX + 1 >= screen.getWidth() - this.width){
                this.isRight = false;
            }else{
                this.move(1,0);
            }
        }else{
            if(this.positionX - 1 <= 0){
                this.isRight = true;
                this.move(1,0);
            }else {
                this.move(-1,0);
            }
        }
    }

    /**
     * The boss's phase Second pattern, which combines horizontal and vertical movement
     * Horizontally, it patrols from side to side at a faster speed than in {@link #patternFirst()}.
     * @see #move(int, int)
     */
    private void patternSecond(){
        if(this.isRight){
            if(this.positionX + 3 >= screen.getWidth() - this.width){
                this.isRight = false;
            }else{
                this.move(3,0);
            }
        }else{
            if(this.positionX - 3 <= 0){
                this.isRight = true;
                this.move(3,0);
            }else {
                this.move(-3,0);
            }
        }
        if(this.isDown){
            if(this.positionY + 1 >= screen.getHeight() - this.height){
                this.isDown = false;
                this.move(0,-1);
            }else{
                this.move(0,1);
            }
        }else{
            if(this.positionY - 1 <= 50){
                this.isDown = true;
            }else{
                this.move(0,-1);
            }
        }
    }

    /** Marks the entity as destroyed and changes its sprite to an explosion. */
    @Override
    public void destroy() {
        if(!this.isDestroyed){
            this.isDestroyed = true;
            this.spriteType = DrawManager.SpriteType.Explosion;
            this.logger.info("OMEGA : Boss OMEGA destroyed!");
        }
    }

    /**
     * Reduces health and destroys the entity if it drops to zero or below.
     *
     * @param damage The amount of damage to inflict.
     */
    @Override
    public void takeDamage(int damage) {
        this.healPoint -= damage;
        if(this.healPoint <= 0 ){
            this.destroy();
        }
    }

    /**
     * Updates the entity's state for the current game frame.
     * This method is called on every tick of the game loop and is responsible for
     * executing the boss's movement patterns.
     */
    @Override
    public void update() {
        this.movePatterns();
    }

    /** Renders the entity at its current position using the provided DrawManager. */
    @Override
    public void draw(DrawManager drawManager) {
        drawManager.drawEntity(this, this.positionX, this.positionY);
    }
}
