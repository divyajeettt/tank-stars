package com.dragonjeet.tankstars.powerup;

import com.dragonjeet.tankstars.misc.Ground;
import com.dragonjeet.tankstars.tank.Tank;


public class ExtraFuelPowerUp extends PowerUp {
    private final int fuelBonus;

    public ExtraFuelPowerUp(int x, Ground ground) {
        super(x, ground);
        this.fuelBonus = 10;
    }

    @Override
    public void applyPowerUpTo(Tank tank) {
        tank.setMaxFuel(tank.getMaxFuel() + this.fuelBonus);
        try {
            tank.setFuel(tank.getMaxFuel());
        }
        catch (Exception e) {
            System.out.println("Error: " + e);
        }
    }
}