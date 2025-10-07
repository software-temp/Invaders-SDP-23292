package entity;

import java.util.*;
import java.util.logging.Logger;

import screen.Screen;
import engine.Cooldown;
import engine.Core;
import engine.DrawManager;
import engine.GameSettings;

/**
 * Groups enemy ships into a formation that moves together.
 *
 * @author <a href="mailto:RobertoIA1987@gmail.com">Roberto Izquierdo Amo</a>
 *
 */
public class EnemyShipSpecialFormation implements Iterable<EnemyShip> {

    /**
     * Lateral speed of the formation.
     */
    private static final int X_SPEED = 6;
    /**
     * Proportion of differences between shooting times.
     */
    private static final double SHOOTING_VARIANCE = .2;
    /**
     * Margin on the sides of the screen.
     */
    private static final int SIDE_MARGIN = 20;

    /**
     * DrawManager instance.
     */
    private DrawManager drawManager;
    /**
     * Application logger.
     */
    private Logger logger;
    /**
     * Screen to draw ships on.
     */
    private Screen screen;

    private EnemyShip enemyShipSpecial;
    /**
     * Minimum time between shots.
     */
    private Cooldown shootingCooldown;
    /**
     * Time between shots.
     */
    private int shootingInterval;
    /**
     * Variance in the time between shots.
     */
    private int shootingVariance;
    /**
     * Current direction the formation is moving on.
     */
    private Direction currentDirection;

    /**
     * Width of one ship.
     */
    private int shipWidth;
    /**
     * Height of one ship.
     */
    private int shipHeight;
    /**
     * List of ships that are able to shoot.
     */
    private List<EnemyShip> shooters;
    /**
     * Directions the formation can move.
     */
    private enum Direction {
        /**
         * Movement to the right side of the screen.
         */
        RIGHT,
        /**
         * Movement to the left side of the screen.
         */
        LEFT,
        /**
         * Movement to the bottom of the screen.
         */
        DOWN
    }


    // Cooldown Field
    private Cooldown enemyShipSpecialCooldown;
    private Cooldown enemyShipSpecialExplosionCooldown;

    ;

    /**
     * Constructor, sets the initial conditions.
     *
     * @param gameSettings Current game settings.
     */
    public EnemyShipSpecialFormation(final GameSettings gameSettings, Cooldown cooldown, Cooldown explosionCooldown) {
        this.drawManager = Core.getDrawManager();
        this.logger = Core.getLogger();
        this.currentDirection = Direction.RIGHT;
        this.shootingInterval = gameSettings.getShootingFrecuency();
        this.shootingVariance = (int) (gameSettings.getShootingFrecuency()
                * SHOOTING_VARIANCE);

        // Initial : create Special Enemy
        this.enemyShipSpecial = new EnemyShip();
        // special enemy information: width & height
        this.shipWidth = this.enemyShipSpecial.getWidth();
        this.shipHeight = this.enemyShipSpecial.getHeight();
        // special enemy cooldown
        this.enemyShipSpecialCooldown = cooldown;
        this.enemyShipSpecialExplosionCooldown = explosionCooldown;

        cooldown.reset();
    }

    /**
     * Associates the formation to a given screen.
     *
     * @param newScreen Screen to attach.
     */
    public final void attach(final Screen newScreen) {
        screen = newScreen;
    }

    /**
     * Draws every component of the formation.
     */
    public final void draw() {
        if (this.enemyShipSpecial != null)
            drawManager.drawEntity(this.enemyShipSpecial,
                    this.enemyShipSpecial.getPositionX(),
                    this.enemyShipSpecial.getPositionY());

    }

    /**
     * Updates the position of the ships.
     */
    public final void update() {
        // TO DO 2. Shoot feature
        if (this.shootingCooldown == null) {
            this.shootingCooldown = Core.getVariableCooldown(shootingInterval,
                    shootingVariance);
            this.shootingCooldown.reset();
        }

        // initial : move option X,Y
        int movementX = 0;
        int movementY = 0;

        /** special moving logic **/
        if(this.enemyShipSpecial != null) {
            // left & right possible move point
            boolean isAtRightSide = this.enemyShipSpecial.getPositionX() + this.shipWidth >= screen.getWidth() - SIDE_MARGIN;
            boolean isAtLeftSide = this.enemyShipSpecial.getPositionX() <= SIDE_MARGIN;

            // TO DO: feature 2 Horizontal move
//            boolean isAtBottom = positionY
//                    + this.height > screen.getHeight() - BOTTOM_MARGIN;
//            boolean isAtHorizontalAltitude = positionY % DESCENT_DISTANCE == 0;

            // moving logic
            if (!this.enemyShipSpecial.isDestroyed()) {
                if (currentDirection == Direction.LEFT) {
                    if (isAtLeftSide)
                        currentDirection = Direction.RIGHT;
                    this.logger.info("Formation now moving right 4");
                } else {
                    if (isAtRightSide) {
                        currentDirection = Direction.LEFT;
                        this.logger.info("Formation now moving left 6");
                    }
                }

                // change moving direction
                if (currentDirection == Direction.RIGHT)
                    movementX = X_SPEED;
                else if (currentDirection == Direction.LEFT)
                    movementX = -X_SPEED;
                enemyShipSpecial.move(movementX, movementY);

            } else if (this.enemyShipSpecialExplosionCooldown.checkFinished())
                this.enemyShipSpecial = null;
        }

        // recreate special enemy by CoolDown
        if (this.enemyShipSpecialCooldown.checkFinished()) {
            this.enemyShipSpecial = new EnemyShip();
            this.enemyShipSpecialCooldown.reset();
            this.logger.info("A special ship appears");
        }
    }

        /**
         * Shoots a bullet downwards. TO DO:feature 2
         *
         * @param bullets
         *            Bullets set to add the bullet being shot.
         */
//        public final void shoot ( final Set<Bullet> bullets){
//            // For now, only ships in the bottom row are able to shoot.
//            int index = (int) (Math.random() * this.shooters.size());
//            EnemyShip shooter = this.shooters.get(index);
//
//            if (this.shootingCooldown.checkFinished()) {
//                this.shootingCooldown.reset();
//                bullets.add(BulletPool.getBullet(shooter.getPositionX()
//                        + shooter.width / 2, shooter.getPositionY(), BULLET_SPEED));
//            }
//        }


        /**
         * Destroys a ship.
         *
         *            Ship to be destroyed.
         */
        public final void destroy (){
            this.enemyShipSpecial.destroy();
            this.enemyShipSpecialCooldown.reset();
        }

        /**
         * Returns an iterator over the ships in the formation.
         *
         * @return Iterator over the enemy ships.
         */
        @Override
        public final Iterator<EnemyShip> iterator() {
            return Collections.singleton(enemyShipSpecial).iterator();
        }

}