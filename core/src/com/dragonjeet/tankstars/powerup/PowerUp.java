package com.dragonjeet.tankstars.powerup;

import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.dragonjeet.tankstars.tank.Tank;

public interface PowerUp {
    public TextureRegion getImage();
    public void applyPowerUpTo(Tank tank);
}
