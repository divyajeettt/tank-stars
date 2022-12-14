package com.dragonjeet.tankstars.tank;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.dragonjeet.tankstars.misc.Ground;

public class Tank2 extends Tank {
    public Tank2(int x, Ground ground, boolean flipped) {
        super(x, ground, flipped, 90, 2.5f, 120);
        setBody(new TextureRegion(new Texture("tanks/tank-2/body.png")));
        setTurret(new TextureRegion(new Texture("tanks/tank-2/turret.png")));
        this.health = this.maxHealth;
    }

    @Override
    public void draw(SpriteBatch batch) {
        batch.draw(turret, x+getWidth()/2f, getY()+getHeight()-getTurretHeight(), 0, 0, getTurretWidth(), getTurretHeight(), 1, 1, getAngle()+aim.angleDeg());
        batch.draw(body, x, getY(), getWidth()/2f, getHeight()/2f, getWidth(), getHeight(), 1, 1, getAngle());
    }
}