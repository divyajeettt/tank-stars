package com.dragonjeet.tankstars.powerup;

import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.dragonjeet.tankstars.exception.InvalidFuelException;
import com.dragonjeet.tankstars.exception.InvalidHealthException;
import com.dragonjeet.tankstars.tank.Tank;

public class ExtraFuelPowerUp implements PowerUp {
    private final int fuelBonus;
    private TextureRegion image;

    public ExtraFuelPowerUp(int fuelBonus) {
        this.fuelBonus = fuelBonus;
    }

    public int getFuelBonus() {
        return fuelBonus;
    }

    @Override
    public TextureRegion getImage() {
        return image;
    }

    @Override
    public void applyPowerUpTo(Tank tank) {
        try {
            tank.setFuel(tank.getFuel() + fuelBonus);
        }
        catch (InvalidFuelException ignored){
            // Log the error
        }
    }
}
