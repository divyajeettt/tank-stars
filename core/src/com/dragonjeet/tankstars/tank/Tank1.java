package com.dragonjeet.tankstars.tank;

import com.dragonjeet.tankstars.attack.AttackType;
import com.dragonjeet.tankstars.misc.Ground;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class Tank1 extends Tank {
    int maxHealth;
    protected AttackType defaultAttack;
    protected int maxAttackPower;
    protected int maxFuel;

    public Tank1(int x, Ground ground, boolean flipped) {
        super(x, ground, flipped);
        body = new TextureRegion(new Texture("tanks/tank-1/body.png"));
        turret = new TextureRegion(new Texture("tanks/tank-1/turret.png"));
    }

    @Override
    public void draw(SpriteBatch batch) {
        batch.draw(body, x, getY(), getWidth()/2f, getHeight()/2f, getWidth(), getHeight(), 1, 1, getAngle());
        batch.draw(turret, x+getWidth()/2, getY()+getHeight(), 0, 0, turret.getRegionWidth(), turret.getRegionHeight(), 1, 1, getAngle()+attackAngle);
    }

}
