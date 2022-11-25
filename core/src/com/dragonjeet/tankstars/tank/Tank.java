package com.dragonjeet.tankstars.tank;

import com.dragonjeet.tankstars.attack.AttackType;
import com.dragonjeet.tankstars.attack.Bullet;
import com.dragonjeet.tankstars.exception.InvalidFuelException;
import com.dragonjeet.tankstars.exception.InvalidHealthException;
import com.dragonjeet.tankstars.exception.TankOutOfScreenException;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import com.dragonjeet.tankstars.misc.Ground;

import java.lang.Math;

public class Tank {
    private int x, y;
    private final boolean flipped;
    private final int height, width;
    private final Rectangle hitbox;
    private final Ground ground;
    private int attackAngle;                     // angle of muzzle
    private int attackPower;                     // power of attack
    private int fuel;                            // will reset to 'maxFuel' full every turn
    private int health;
    protected int maxHealth;
    protected int xVelocity, yVelocity;
    protected AttackType defaultAttack;
    protected TextureRegion image;
    protected int maxAttackPower;
    protected int maxFuel;

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
        // different images, moving speeds, healths, fuelTanks, and attackDamages
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

    public void setXVelocity(int xVelocity) {
        this.xVelocity = xVelocity;
    }

    public int getHealth() {
        return health;
    }

    public void setHealth(int health) throws InvalidHealthException {
        if (health < this.maxHealth) {
            this.health = health;
        } else if (health > maxHealth) {
            this.health = maxHealth;
        } else {
            throw new InvalidHealthException("Health cannot be set to a negative value");
        }
    }

    public int getDamage() {
        // return damage of default attack
        return (int) (1);
    }

    public void setDamage(int damage) {
        // set damage of the next attack
    }

    public float getAngle() {
        return ((float) Math.atan((ground.getHeight(x+width) - ground.getHeight(x))/width) * 180 / (float) Math.PI);
    }

    public int getFuel() {
        return fuel;
    }

    public void setFuel(int fuel) throws InvalidFuelException {
        if (fuel < this.maxFuel) {
            this.fuel = fuel;
        } else if (fuel > maxFuel) {
            this.fuel = maxFuel;
        } else {
            throw new InvalidFuelException("Fuel cannot be set to a negative value");
        }
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
        this.fuel = this.maxFuel;
    }

    public void consumeFuel(int fuel) {
        this.fuel -= fuel;
    }

    public void shoot(Bullet bullet) {}

    public void decreaseHealth(int damage) {
        this.health -= damage;
    }
}