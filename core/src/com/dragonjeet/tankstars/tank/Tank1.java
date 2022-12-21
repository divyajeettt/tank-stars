package com.dragonjeet.tankstars.tank;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.dragonjeet.tankstars.misc.Ground;

public class Tank1 extends Tank {
    public Tank1(int x, Ground ground, boolean flipped) {
        super(x, ground, flipped, 100, 2.5f, 80);
        setBody(new TextureRegion(new Texture("tanks/tank-1/body.png")));
        setTurret(new TextureRegion(new Texture("tanks/tank-1/turret.png")));
        this.health = this.maxHealth;
    }

    public Tank1(int x, Ground ground) {
        // for Testing purposes
        super(x, ground, false, 100, 2.5f, 80);
        this.health = this.maxHealth;
    }

    @Override
    public void draw(SpriteBatch batch) {
        batch.draw(turret, x+getWidth()/2f + 10*(flipped ? -2 : 1), getY()+getHeight()-getTurretHeight(), 5*(flipped ? 1 : -1), 0, getTurretWidth(), getTurretHeight(), 1, 1, getAngle()+aim.angleDeg());
        batch.draw(body, x, getY(), getWidth()/2f, getHeight()/2f, getWidth(), getHeight(), 1, 1, getAngle());
    }
}
