package com.dragonjeet.tankstars.tank;

import com.dragonjeet.tankstars.attack.AttackType;
import com.dragonjeet.tankstars.attack.Bullet;
import com.dragonjeet.tankstars.exception.InvalidFuelException;
import com.dragonjeet.tankstars.exception.InvalidHealthException;
import com.dragonjeet.tankstars.exception.TankOutOfScreenException;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.dragonjeet.tankstars.misc.Ground;

import java.lang.Math;

public abstract class Tank {
    protected int x;
    protected final boolean flipped;
    protected final Ground ground;
    protected float attackAngle;                   // angle of muzzle
    protected AttackType defaultAttack;
    protected TextureRegion image, body, turret;
    protected int maxAttackPower, maxFuel, xVelocity, yVelocity, maxHealth, health, fuel, attackPower;
    protected static int scalingFactor = 4;        //Bigger scalingFactor -> smaller tank

    public abstract void draw(SpriteBatch batch) throws TankOutOfScreenException;

    public Tank(int x, Ground ground, boolean flipped) {
        // for the time being, the only diff between tanks is their image
        // this will be extended to inheritance into 3 children, each of which should have
        // different images, moving speeds, healths, fuelTanks, and attackDamages
        this.x = x;
        this.ground = ground;
        this.flipped = flipped;
        this.health = 100;
    }

    public int getHeight() {
        if (body == null) return image.getRegionHeight();
        return body.getRegionHeight()/scalingFactor;
    }

    public int getWidth() {
        if (body == null) return image.getRegionWidth();
        return body.getRegionWidth() / scalingFactor;
    }

    public int getTurretHeight() {
        return turret.getRegionHeight() / scalingFactor;
    }

    public int getTurretWidth() {
        return turret.getRegionWidth() / scalingFactor;
    }

    public void setBody(TextureRegion body) {
        this.body = body;
        this.body.flip(false, this.flipped);
    }

    public void setTurret(TextureRegion turret) {
        this.turret = turret;
        this.turret.flip(false, this.flipped);
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getX() {
        return x;
    }

    public int getY() throws TankOutOfScreenException {
        return ((int) ground.getHeight(x + getWidth() / 2));
    }

    public void move() throws TankOutOfScreenException {
        if (x+xVelocity > 1 && x+xVelocity < ground.getWidth()-getWidth()-1) {
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
        return 1;
    }

    public void setDamage(int damage) {
        // set damage of the next attack
    }

    public float getAngle() throws TankOutOfScreenException {
        return (
            (float) Math.atan((ground.getHeight(x+getWidth()) - ground.getHeight(x))/getWidth()) * 180 /
            (float) Math.PI) + 180f*(flipped ? 1 : 0)
        ;
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