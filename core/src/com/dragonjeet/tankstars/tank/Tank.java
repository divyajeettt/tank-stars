package com.dragonjeet.tankstars.tank;

import com.dragonjeet.tankstars.attack.Bullet;
import com.dragonjeet.tankstars.exception.InvalidFuelException;
import com.dragonjeet.tankstars.exception.InvalidHealthException;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.dragonjeet.tankstars.misc.Ground;

import java.lang.Math;

public abstract class Tank {
    protected final boolean flipped;
    protected int x;
    protected Vector2 aim;                   // angle of muzzle
    protected Ground ground;
    protected TextureRegion image, body, turret;
    protected int xVelocity, yVelocity, health, fuel;
    protected final int maxHealth;
    protected final int maxAttackPower=4;
    protected final int maxFuel;
    protected static int scalingFactor = 4;        //Bigger scalingFactor -> smaller tank

    public abstract void draw(SpriteBatch batch);

    public Tank(int x, Ground ground, boolean flipped, int maxHealth, int maxAttackPower, int maxFuel) {
        // for the time being, the only diff between tanks is their image
        // this will be extended to inheritance into 3 children, each of which should have
        // different images, moving speeds, healths, fuelTanks, and attackDamages
        this.x = x;
        this.ground = ground;
        this.flipped = flipped;
        this.aim = new Vector2();
        // go into Tank1,2,3 and change according to the tanks' stats in the game
        this.maxHealth = maxHealth;
        this.maxFuel = maxFuel;
        this.health = this.maxHealth;
    }

    public int getHeight() {
        return ((body == null) ? image.getRegionHeight() : body.getRegionHeight()/scalingFactor);
    }

    public int getWidth() {
        return ((body == null) ? image.getRegionWidth() : body.getRegionWidth()/scalingFactor);
    }

    public int getTurretHeight() {
        return turret.getRegionHeight() / scalingFactor;
    }

    public int getTurretWidth() {
        return turret.getRegionWidth() / scalingFactor;
    }

    public int getMaxHealth() {
        return this.maxHealth;
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

    public int getY(){
        return ((int) ground.getHeight(x + getWidth() / 2));
    }

    public void setGround(Ground ground) {
        this.ground = ground;
    }

    public void move() {
        if (x+xVelocity > 1 && x+xVelocity < ground.getWidth()-getWidth()-1) {
            x += xVelocity;
        }
    }

    public void setXVelocity(int xVelocity) {
        this.xVelocity = xVelocity;
    }

    public int getHealth() {
        return this.health;
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

    protected int getTurretBaseX() {
        return x+getWidth()/2;
    }
    
    protected int getTurretBaseY(){
        return getY() + getHeight();
    }

    public float getAngle(){
        return (
            (float) Math.atan((ground.getHeight(x+getWidth()) - ground.getHeight(x))/getWidth()) * 180 /
            (float) Math.PI) + 180f*(flipped ? 1 : 0)
        ;
    }

    public int getFuel() {
        return this.fuel;
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

    public void aimAt(float percentX, float percentY) {
        if (flipped) {
            percentX *= -1;
            percentY *= -1;
        }
        Vector2 target = new Vector2(percentX, percentY);
        target.scl(this.maxAttackPower);
        aim.add(target.sub(aim).scl(0.1f));
    }

    public float getAttackAngle() {
        return aim.angleRad();
    }

    public float getAttackPower() {
        return aim.len();
    }

    public void resetFuel(int fuel) {
        this.fuel = this.maxFuel;
    }

    public void consumeFuel(int fuel) {
        this.fuel -= fuel;
    }

    public Bullet shoot() {
        return new Bullet(getTurretBaseX(),getTurretBaseY(),getAttackPower(),getAttackAngle()+Math.PI*(flipped ? 1:0),100);
    }

    public void decreaseHealth(int damage) {
        this.health -= damage;
    }
}