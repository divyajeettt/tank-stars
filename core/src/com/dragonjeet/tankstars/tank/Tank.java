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
    protected int x, y;
    protected final boolean flipped;
    protected int height, width;
    protected final Ground ground;
    protected float attackAngle;                   // angle of muzzle
    protected int attackPower;                     // power of attack
    protected int fuel;                            // will reset to 'maxFuel' full every turn
    protected int health;
    protected int maxHealth;
    protected int xVelocity, yVelocity;
    protected AttackType defaultAttack;
    protected TextureRegion image, body, turret;
    protected int maxAttackPower;
    protected int maxFuel;

    public Tank(int x , Ground ground, boolean flipped) {
        this.x = x;
        this.ground = ground;
        this.flipped = flipped;
        this.health = 100;
    }

    public int getHeight() {
        if (body == null) return image.getRegionHeight();
        return body.getRegionHeight();
    }

    public int getWidth() {
        if (body == null) return image.getRegionWidth();
        return body.getRegionWidth();
    }

    public void setImage(TextureRegion image) {
        // for the time being, the only diff between tanks is their image
        // this will be extended to inheritance into 3 children, each of which should have
        // different images, moving speeds, healths, fuelTanks, and attackDamages
        this.image = image;
        this.image.flip(this.flipped, false);
    }

    public void setBody(TextureRegion body) {
        this.body = body;
        this.body.flip(this.flipped, false);
    }

    public void setTurret(TextureRegion turret) {
        this.turret = turret;
        this.turret.flip(this.flipped, false);
    }

    public void draw(SpriteBatch batch) throws TankOutOfScreenException {
        batch.draw(body, x, getY(), getWidth()/2f, getHeight()/2f, getWidth(), getHeight(), 1, 1, getAngle());
        batch.draw(turret, x+getWidth()/2f, getY()+getHeight(), 0, 0, turret.getRegionWidth(), turret.getRegionHeight(), 1, 1, getAngle()+attackAngle);
    }

    public int getX() {
        return x;
    }

    public int getY() throws TankOutOfScreenException {
        return ((int) ground.getHeight(x + width / 2));
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

    public float getAngle() throws TankOutOfScreenException {
        return ((float) Math.atan((ground.getHeight(x+getWidth()) - ground.getHeight(x))/getWidth()) * 180 / (float) Math.PI);
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

    public void setAttackAngle(float attackAngle) {
        this.attackAngle = attackAngle;
    }

    public float getAttackAngle() {
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