package com.dragonjeet.tankstars.powerup;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.dragonjeet.tankstars.exception.TankDeadException;
import com.dragonjeet.tankstars.tank.Tank;

public class ExtraHealthPowerUp extends Tank implements PowerUp {
    private final int healthBonus;
    private TextureRegion image;

    public ExtraHealthPowerUp(int healthBonus) {
//        this.image = new TextureRegion(new Texture("powerup/extra_health.png"));
        this.healthBonus = healthBonus;
    }

    public int getHealthBonus() {
        return healthBonus;
    }

    @Override
    public TextureRegion getImage() {
        return this.image;
    }

    @Override
    public void applyPowerUpTo(Tank tank) {
//        try {
//            tank.setHealth(tank.getHealth() + this.healthBonus);
//        }
//        catch (InvalidHealthException ignored) {
//            // Log error
//        }
    }
}