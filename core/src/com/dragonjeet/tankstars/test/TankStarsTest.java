package com.dragonjeet.tankstars.test;

import com.badlogic.gdx.Gdx;
import com.dragonjeet.tankstars.exception.FuelExhaustedException;
import com.dragonjeet.tankstars.exception.TankDeadException;
import com.dragonjeet.tankstars.exception.TankOutOfScreenException;
import com.dragonjeet.tankstars.misc.Ground;
import com.dragonjeet.tankstars.tank.Tank;
import com.dragonjeet.tankstars.tank.Tank1;
import org.junit.BeforeClass;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class TankStarsTest {
    private static final int TIMEOUT = 5000;
    private static Ground ground;
    private static Tank tank;

    @BeforeClass
    public static void setUp() {
        ground = new Ground(1000);     // a sample Ground
        tank = new Tank1(15, ground);     // a sample Tank
    }

    @Test(expected = TankDeadException.class)
    public void testTankDead1() throws TankDeadException {
        tank.setHealth(-1);
    }

    @Test(expected = TankDeadException.class)
    public void testTankDead2() throws TankDeadException {
        tank.decreaseHealth(tank.getMaxHealth() * 2);
    }

    @Test(expected = FuelExhaustedException.class)
    public void testFuelExhausted() throws FuelExhaustedException {
        tank.consumeFuel(tank.getFuel() * 2);
    }

    @Test(expected = TankOutOfScreenException.class)
    public void testTankOutOfScreen1() throws TankOutOfScreenException {
        tank.setX(ground.getWidth() * 2);
    }

    @Test(expected = TankOutOfScreenException.class)
    public void testTankOutOfScreen2() throws TankOutOfScreenException {
        tank.setX(-10);
    }

    @Test(timeout = TIMEOUT)
    public void testTankMove() throws TankOutOfScreenException, FuelExhaustedException {
        tank.setXVelocity(10);
        tank.move();
        assertTrue(tank.getFuel() < tank.getMaxFuel());
    }

    @Test(timeout = TIMEOUT)
    public void testTankMove2() throws TankOutOfScreenException, FuelExhaustedException {
        tank.setXVelocity(0);
        tank.move();
        assertEquals(tank.getFuel(), tank.getMaxFuel());
    }
}
