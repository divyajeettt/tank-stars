package com.dragonjeet.tankstars.tank;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.dragonjeet.tankstars.attack.AttackType;
import com.dragonjeet.tankstars.misc.Ground;

public class Tank3 extends Tank {
    int maxHealth;
    protected AttackType defaultAttack;
    protected int maxAttackPower;
    protected int maxFuel;

    public Tank3(int x, Ground ground, boolean flipped) {
        super(x, ground, flipped);
        body = new TextureRegion(new Texture("tanks/tank-3/body.png"));
        turret = new TextureRegion(new Texture("tanks/tank-3/turret.png"));
    }
}
