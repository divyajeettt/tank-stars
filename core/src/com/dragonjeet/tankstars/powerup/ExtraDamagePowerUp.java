package com.dragonjeet.tankstars.powerup;

import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.dragonjeet.tankstars.tank.Tank;

public class ExtraDamagePowerUp implements PowerUp {
    private final int damageBonus;
    private TextureRegion image;

    public ExtraDamagePowerUp(int damageBonus) {
        this.damageBonus = damageBonus;
    }

    public int getDamageBonus() {
        return damageBonus;
    }

    @Override
    public TextureRegion getImage() {
        return image;
    }

    @Override
    public void applyPowerUpTo(Tank tank) {
        tank.setDamage(tank.getDamage() + damageBonus);
    }
}
