package com.dragonjeet.tankstars.tank;

import com.dragonjeet.tankstars.attack.AttackType;
import com.dragonjeet.tankstars.misc.Ground;
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
}
