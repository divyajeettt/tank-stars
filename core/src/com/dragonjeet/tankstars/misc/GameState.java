package com.dragonjeet.tankstars.misc;

import com.dragonjeet.tankstars.attack.Bullet;
import com.dragonjeet.tankstars.exception.FuelExhaustedException;
import com.dragonjeet.tankstars.exception.TankDeadException;
import com.dragonjeet.tankstars.tank.Tank;
import com.dragonjeet.tankstars.tank.Tank1;
import com.dragonjeet.tankstars.tank.Tank2;
import com.dragonjeet.tankstars.tank.Tank3;
import java.io.Serializable;

public class GameState implements Serializable {
    private Ground ground;
    private boolean canMove;
    private int turn;

    private boolean tank1flipped, tank2flipped;
    private int tank1x, tank2x, tank1fuel, tank2fuel, tank1health, tank2health, tank1xVelocity, tank2xVelocity;
    private int tank1type, tank2type;
    private float tank1attackPower, tank2attackPower;

    private double bulletX, bulletY, bulletXVelocity, bulletYVelocity;
    private int bulletFullDamage;

    public GameState(TankStars game) {
        Tank tank1 = game.getTank1();
        Tank tank2 = game.getTank2();
        this.ground = game.getGround();
        this.canMove = game.getCanMove();
        this.turn = game.getTurn();

        tank1flipped = tank1.getFlipped();
        tank1x = tank1.getX();
        tank1health = tank1.getHealth();
        tank1fuel = tank1.getFuel();
        tank1attackPower = tank1.getAttackPower();
        tank1xVelocity = tank1.getXVelocity();

        if (tank1.getMaxHealth() == 100) {
            tank1type = 1;
        } else if (tank1.getMaxHealth() == 90) {
            tank1type = 2;
        } else {
            tank1type = 3;
        }

        // make new Vector2aim
        // make scalingFactor = 4

        tank2flipped = tank2.getFlipped();
        tank2x = tank2.getX();
        tank2health = tank2.getHealth();
        tank2fuel = tank2.getFuel();
        tank2attackPower = tank2.getAttackPower();
        tank2xVelocity = tank2.getXVelocity();

        if (tank2.getMaxHealth() == 100) {
            tank2type = 1;
        } else if (tank2.getMaxHealth() == 90) {
            tank2type = 2;
        } else {
            tank2type = 3;
        }

        Bullet bullet = game.getBullet();
        bulletX = bullet.getX();
        bulletY = bullet.getY();
        bulletXVelocity = bullet.getxVelocity();
        bulletYVelocity = bullet.getyVelocity();
        bulletFullDamage = bullet.getFullDamage();
    }

    public Tank getTank1() {
        Tank tank1;
        if (tank1type == 1) {
            tank1 = new Tank1(tank1x, ground, tank1flipped);
        } else if (tank1type == 2) {
            tank1 = new Tank2(tank1x, ground, tank1flipped);
        } else {
            tank1 = new Tank3(tank1x, ground, tank1flipped);
        }
        try {
            tank1.setFuel(tank1fuel);
            tank1.setHealth(tank1health);
        }
        catch (FuelExhaustedException | TankDeadException e) {
        }
        return tank1;
    }

    public Tank getTank2() {
        Tank tank2;
        if (tank2type == 1) {
            tank2 = new Tank1(tank2x, ground, tank2flipped);
        } else if (tank2type == 2) {
            tank2 = new Tank2(tank2x, ground, tank2flipped);
        } else {
            tank2 = new Tank3(tank2x, ground, tank2flipped);
        }
        try {
            tank2.setFuel(tank2fuel);
            tank2.setHealth(tank2health);
        }
        catch (FuelExhaustedException | TankDeadException e) {
        }
        return tank2;
    }

    public Ground getGround() {
        return ground;
    }

    public boolean getCanMove() {
        return canMove;
    }

    public Bullet getBullet() {
        return new Bullet(bulletX, bulletY, bulletXVelocity, bulletYVelocity, bulletFullDamage);
    }
}
