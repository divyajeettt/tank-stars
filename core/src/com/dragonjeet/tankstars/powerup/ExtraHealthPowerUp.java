package com.dragonjeet.tankstars.powerup;

import com.dragonjeet.tankstars.misc.Ground;
import com.dragonjeet.tankstars.tank.Tank;

public class ExtraHealthPowerUp extends PowerUp {
    private final int healthBonus;
 
    public ExtraHealthPowerUp(int x, Ground ground) {
        super(x, ground);
        this.healthBonus = 10;
    }

    private int min (int a, int b) {
        return a < b ? a : b;
    }

    @Override
    public void applyPowerUpTo(Tank tank) {
        try {
            tank.setHealth(min(tank.getHealth() + this.healthBonus, tank.getMaxHealth()));
        } catch (Exception e) {
            System.out.println("Error: " + e);
        }
    }
}