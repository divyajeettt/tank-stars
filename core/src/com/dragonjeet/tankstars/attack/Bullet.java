package com.dragonjeet.tankstars.attack;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.Texture;
import com.dragonjeet.tankstars.misc.Ground;
import com.dragonjeet.tankstars.tank.Tank;

public class Bullet {
    private double x,y,xVelocity, yVelocity;
    private final int fullDamage;
    private final TextureRegion texture;

    public Bullet(double x, double y, double speed, double initialAngle, int fullDamage) {
        this.x = x;
        this.y = y;
        this.xVelocity = speed * Math.cos(initialAngle);
        this.yVelocity = speed * Math.sin(initialAngle);
        this.fullDamage = fullDamage;
        texture = new TextureRegion(new Texture("bullets/default.png"));

    }

    public int getBulletHeight() {
        return texture.getRegionHeight();
    }

    public int getBulletWidth() {
        return texture.getRegionWidth();
    }

    public double getAngle() {
        return Math.atan2(yVelocity, xVelocity)*180/Math.PI;
    }

    public boolean move(Ground ground) {
        // move bullet
        x += xVelocity;
        y += yVelocity;
        if (x < 0 || x > ground.getWidth()) {
            // hit wall
            return true;
        }
         //gravity
        yVelocity -= 0.02;
        if (y <= ground.getHeight((int) x)) {
            // hit ground
            return true;
        }
        return false;
    }


    public void draw(SpriteBatch batch) {
        batch.draw(texture, (float) x, (float) y, getBulletWidth()/2f, getBulletHeight()/2f, getBulletWidth(), getBulletHeight(), 1, 1, (float) getAngle());
    }

    public boolean isSpecial() {
        return false;
    }

    public void attack(Tank tank) {
        // attack tank
        // calculate distance from tank and pass to dealDamageTo()
    }

    public void dealDamageTo(Tank tank, int distanceFromTank) {
        // deal damage to tank
    }
}
