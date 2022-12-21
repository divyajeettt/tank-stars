package com.dragonjeet.tankstars.powerup;

import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.dragonjeet.tankstars.tank.Tank;

public class ShieldPowerUp extends Tank implements PowerUp {
    private final int shieldBonus;
    private TextureRegion image;

    public ShieldPowerUp(int shieldBonus) {
        this.shieldBonus = shieldBonus;
    }

    public int getShieldBonus() {
        return shieldBonus;
    }

    @Override
    public TextureRegion getImage() {
        return image;
    }

    @Override
    public void applyPowerUpTo(Tank tank) {
        // do something that protects the tank against damage
        // tank.setShield(tank.getShield() + shieldBonus);
    }
}
