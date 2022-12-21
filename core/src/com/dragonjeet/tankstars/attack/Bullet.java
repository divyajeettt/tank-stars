package com.dragonjeet.tankstars.attack;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.Texture;
import com.dragonjeet.tankstars.exception.TankDeadException;
import com.dragonjeet.tankstars.misc.Ground;
import com.dragonjeet.tankstars.tank.Tank;
import java.io.Serializable;

public class Bullet implements Serializable {
    private double x, y, xVelocity, yVelocity;
    private final int fullDamage;
    private final TextureRegion texture;

    public Bullet(double x, double y, double power, double initialAngle, int fullDamage) {
        this.x = x;
        this.y = y;
        this.xVelocity = power * Math.cos(initialAngle);
        this.yVelocity = power * Math.sin(initialAngle);
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

    public boolean move(Ground ground, Tank tank) throws TankDeadException {
        // move bullet
        x += xVelocity;
        y += yVelocity;
        yVelocity -= 0.02;     // gravity

        if (x < 0 || x > ground.getWidth()) return true; // wall hit

        if (y <= ground.getHeight((int) x)) {
            ground.mutilate((int) x, fullDamage);   // ground hit
            if (tank.getX() < x && tank.getX() + tank.getWidth() > x) {
                // tank hit
                dealDamageTo(tank);
                tank.recoil(xVelocity);
            }
            return true;
        }
        return false;
    }

    public void draw(SpriteBatch batch) {
        batch.draw(texture, (float) x, (float) y, getBulletWidth()/2f, getBulletHeight()/2f, getBulletWidth(), getBulletHeight(), 1, 1, (float) getAngle());
    }

    private void dealDamageTo(Tank tank) throws TankDeadException {
        // scale the damage based on the distance from the center of the tank
        tank.decreaseHealth((int) (fullDamage * (1 - Math.abs(tank.getX() + tank.getWidth()/2d - x) / (tank.getWidth()/2f))));
    }
}
