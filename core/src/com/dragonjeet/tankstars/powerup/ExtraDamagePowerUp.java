package com.dragonjeet.tankstars.powerup;

import com.dragonjeet.tankstars.misc.Ground;
import com.dragonjeet.tankstars.tank.Tank;

public class ExtraDamagePowerUp extends PowerUp {
    private final float damageBonus;

    public ExtraDamagePowerUp(int x, Ground ground) {
        super(x,ground);
        damageBonus = 0.3f;
    }

    @Override
    public void applyPowerUpTo(Tank tank) {
        tank.setMaxAttackPower(tank.getMaxAttackPower() + damageBonus);
    }
}
