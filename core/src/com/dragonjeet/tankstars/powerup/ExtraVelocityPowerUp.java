package com.dragonjeet.tankstars.powerup;

import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.dragonjeet.tankstars.tank.Tank;

public class ExtraVelocityPowerUp implements PowerUp {
    private final int velocityBonus;
    private TextureRegion image;

    public ExtraVelocityPowerUp(int velocityBonus) {
        this.velocityBonus = velocityBonus;
    }

    public int getVelocityBonus() {
        return velocityBonus;
    }

    @Override
    public TextureRegion getImage() {
        return image;
    }

    @Override
    public void applyPowerUpTo(Tank tank) {
//        tank.setVelocity(tank.getVelocity() + velocityBonus);
    }
}
