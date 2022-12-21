package com.dragonjeet.tankstars.powerup;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.dragonjeet.tankstars.misc.Ground;
import com.dragonjeet.tankstars.tank.Tank;

public abstract class PowerUp extends Tank {
    public PowerUp(int x, Ground ground) {
        super(x, ground, false, 0, 0, 0);
        setBody(new TextureRegion(new Texture("bullets/powerup.png")));
    }
    public void draw(SpriteBatch batch) {
        batch.draw(body, x, getY(), getWidth()/2f, getHeight()/2f, getWidth(), getHeight(), 1, 1, getAngle());
    }

    public abstract void applyPowerUpTo(Tank tank);
}
