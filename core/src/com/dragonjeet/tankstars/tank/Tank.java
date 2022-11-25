package com.dragonjeet.tankstars.misc;

import com.dragonjeet.tankstars.attack.AttackType;
import com.dragonjeet.tankstars.attack.Bullet;
import com.dragonjeet.tankstars.exception.TankOutOfScreenException;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import com.dragonjeet.tankstars.misc.Ground;

import java.lang.Math;

public class Tank {
    private int x, y;
    private final boolean flipped;
    private int xVelocity, yVelocity;
    private final int height, width;
    private final Rectangle hitbox;
    private final Ground ground;
    private TextureRegion image;
    private int attackAngle;                     // angle of muzzle
    private int attackPower;                     // power of attack
    private int fuel;                            // will reset to full every turn
    private int health;
    private AttackType defaultAttack;

    public Tank(int x, int y,int height, int width, Ground ground, boolean flipped) {
        this.x = x;
        this.y = y;
        this.height = height;
        this.width = width;
        this.ground = ground;
        this.flipped = flipped;
        this.health = 100;
        hitbox = new Rectangle(x, y, width, height);
    }

    public void setImage(TextureRegion image) {
        // for the time being, the only diff between tanks is their image
        // this will be extended to inheritance into 3 children, each of which should have
        // different images, moving speeds, healths, and attackDamages
        this.image = image;
        this.image.flip(this.flipped, false);
    }

    public void draw(SpriteBatch batch) {
        batch.draw(image, x, getY(), width/2f, height/2f, width, height, 1, 1, getAngle());
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return ((int) ground.getHeight(x+width/2));
    }

    public void move() throws TankOutOfScreenException {
        if (x+xVelocity > 1 && x+xVelocity < ground.getWidth()-width-1) {
            x += xVelocity;
        } else {
            throw new TankOutOfScreenException("Tank is out of screen");
        }
        x += xVelocity;
    }

    public int getXVelocity() {
        return xVelocity;
    }

    public void setXVelocity(int xVelocity) {
        this.xVelocity = xVelocity;
    }

    public int getYVelocity() {
        return yVelocity;
    }

    public void setYVelocity(int yVelocity) {
        this.yVelocity = yVelocity;
    }

    public float getAngle() {
        return ((float) Math.atan((ground.getHeight(x+width) - ground.getHeight(x))/width) * 180 / (float) Math.PI);
    }

    public int getHeight() {
        return height;
    }

    public int getWidth() {
        return width;
    }    

    public Rectangle getHitbox() {
        return hitbox;
    }

    public void setAttackAngle(int attackAngle) {
        this.attackAngle = attackAngle;
    }

    public int getAttackAngle() {
        return attackAngle;
    }

    public void setAttackPower(int attackPower) {
        this.attackPower = attackPower;
    }

    public int getAttackPower() {
        return attackPower;
    }

    public void resetFuel(int fuel) {
        this.fuel = 100;
    }

    public void consumeFuel(int fuel) {
        this.fuel -= fuel;
    }

    public void shoot(Bullet bullet) {}

    public void decreaseHealth(int damage) {
        this.health -= damage;
    }
}