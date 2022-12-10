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

    public Tank1(int x, int y, int height, int width, Ground ground, boolean flipped) {
        super(x, y, height, width, ground, flipped);
        body = new TextureRegion(new Texture("tanks/tank-1/body.png"));
        turret = new TextureRegion(new Texture("tanks/tank-1/turret.png"));
    }

    @Override
    public void draw(SpriteBatch batch) {
        batch.draw(body, x, getY(), width/2f, height/2f, width, height, 1, 1, getAngle());
        batch.draw(turret, x, getY()+body.getRegionHeight(), width/2f, height/2f, width, height, 1, 1, getAngle());
    }

}
