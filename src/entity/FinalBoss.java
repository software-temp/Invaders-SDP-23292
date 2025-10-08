package entity;

import engine.DrawManager;
import engine.Cooldown;
import engine.Core;
import screen.GameScreen;

import java.awt.*;

public class FinalBoss extends Entity{

    private int healPoint;
    private int maxHp;
    private final int pointValue;
    private boolean isDestroyed;
    /** for move pattern **/
    private int zigDirection = 1;
    /** for move pattern **/
    private boolean goingDown = true;

    private Cooldown animationCooldown;
    private Cooldown shootCooldown1;
    private Cooldown shootCooldown2;
    private Cooldown shootCooldown3;
    private static int random_x;
    private final GameScreen screen;
    /** basic attribute of final boss **/
    public FinalBoss(int positionX, int positionY, GameScreen screen){

        super(positionX,positionY,100,80, Color.RED);
        this.screen = screen;
        this.healPoint = 20;
        this.maxHp = healPoint;
        this.pointValue = 1000;
        this.spriteType = DrawManager.SpriteType.EnemyShipSpecial;
        this.isDestroyed = false;

        this.animationCooldown = Core.getCooldown(500);
        this.shootCooldown1 = Core.getCooldown(5000);
        this.shootCooldown2 = Core.getCooldown(400);
        this.shootCooldown3 = Core.getCooldown(100);
    }

    /** for vibrant moving with final boss **/
    /** final boss spritetype is the same with special enemy and enemyshipA, because final boss spritetype have not yet implemented **/
    /** becasue final boss is single object, moving and shooting pattern are included in update methods **/
    public void update(){
        if(this.animationCooldown.checkFinished()){
            this.animationCooldown.reset();

            if(this.spriteType == DrawManager.SpriteType.EnemyShipSpecial){
                this.spriteType = DrawManager.SpriteType.EnemyShipA1;
            } else{
                this.spriteType = DrawManager.SpriteType.EnemyShipSpecial;
            }
        }
        movePattern();
        shoot1();
        shoot2();
    }

    /** decrease boss' healpoint **/
    public void takeDamage(int damage){
        this.healPoint -= damage;
        if(this.healPoint <= 0){
            this.destroy();
        }
    }

    public int getHealPoint(){
        return this.healPoint;
    }
    public int getPointValue(){
        return this.pointValue;
    }

    /** movement pattern of final boss **/
    public void movePattern(){
        if(this.healPoint > this.maxHp/2){
            this.move(0,0);
        }
        else {
            this.moveZigzag(3,2);
        }
    }

    /** move zigzag **/
    public void moveZigzag(int zigSpeed, int vertSpeed){
        this.positionX += (this.zigDirection * zigSpeed);
        if(this.positionX <= 0 || this.positionX >= screen.getWidth()-this.width){
            this.zigDirection *= -1;
        }

        if(goingDown) {
            this.positionY += vertSpeed;
            if (this.positionY >= screen.getHeight()/2 - this.height) goingDown = false;
        }
        else {
            this.positionY -= vertSpeed;
            if(this.positionY <= 0) goingDown = true;
        }
    }

    /** move simple **/
    public void move(int distanceX, int distanceY){
        this.positionX += distanceX;
        this.positionY += distanceY;
    }

    /** not yet implemented **/
    public void shoot1(){
        if(this.shootCooldown1.checkFinished()){
            this.shootCooldown1.reset();
            // 총알 5방향 객체 생성 로직
            int arr[] = {0,1,-1,2,-2};
            for (int i : arr){
                new BossBullet(this.getPositionX() + this.getWidth() / 2 - 3,this.getPositionY() + this.getHeight(), i,4,6,10);
            }
        }
    }
    public void shoot2() {
        random_x = (int) (Math.random() * 450);
        if (this.shootCooldown2.checkFinished()) {
            this.shootCooldown2.reset();
            // 총알 일직선 객체 생성 로직
            new BossBullet(random_x, 1, 0, 2,6,10);
//            new BossBullet(bullet_a, 450, 0, -4,6,10);

        }
    }
    public void shoot3(int x, int y) {
        if (this.shootCooldown3.checkFinished()) {
            this.shootCooldown3.reset();
            // 총알 일직선 객체 생성 로직
            new BossBullet(1, 1, 0, 5,6,10);

        }
    }

    /** flag final boss' destroy **/
    public void destroy(){
        if(!this.isDestroyed){
            this.isDestroyed = true;
            this.spriteType = DrawManager.SpriteType.Explosion;
        }
    }

    /** check final boss' destroy **/
    public boolean isDestroyed(){
        return this.isDestroyed;
    }
}