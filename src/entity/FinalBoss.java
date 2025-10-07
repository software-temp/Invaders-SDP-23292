package entity;

import engine.DrawManager;
import engine.Cooldown;
import engine.Core;
import screen.GameScreen;

import java.awt.*;

public class FinalBoss extends Entity{

    public int healPoint;
    private int Hp;
    private final int pointValue;
    private boolean isDestroy;
    /** for move pattern **/
    private int zigDirection = 1;
    /** for move pattern **/
    private boolean goingDown = true;

    private Cooldown animationCooldown;
    private Cooldown shootCooldown;
    private final GameScreen screen;
    /** basic attribute of final boss **/
    public FinalBoss(int positionX, int positionY, GameScreen screen){

        super(positionX,positionY,100,80, Color.RED);
        this.screen = screen;
        this.healPoint = 20;
        this.Hp = healPoint;
        this.pointValue = 1000;
        this.spriteType = DrawManager.SpriteType.EnemyShipSpecial;
        this.isDestroy = false;

        this.animationCooldown = Core.getCooldown(500);
        this.shootCooldown = Core.getCooldown(1000);
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
        shoot();
    }

    /** decrease boss' healpoint **/
    public void takeDamage(int damage){
        this.healPoint -= damage;
        if(this.healPoint <= 0){
            this.destroy();
        }
    }

    public int getPointValue(){
        return this.pointValue;
    }

    /** movement pattern of final boss **/
    public void movePattern(){
        if(this.healPoint > this.Hp/2){
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
    public void shoot(){
        if(this.shootCooldown.checkFinished()){
            this.shootCooldown.reset();
            // 총알 객체 생성 로직
        }
    }

    /** flag final boss' destroy **/
    public void destroy(){
        if(!this.isDestroy){
            this.isDestroy = true;
            this.spriteType = DrawManager.SpriteType.Explosion;
        }
    }

    /** check final boss' destroy **/
    public boolean isDestroyed(){
        return this.isDestroy;
    }
}
