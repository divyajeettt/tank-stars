package com.dragonjeet.tankstars.powerup;

import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Rectangle;
import com.dragonjeet.tankstars.misc.Ground;
import com.dragonjeet.tankstars.tank.Tank;

import java.lang.Math;

public class AirDrop {
    // AirDrops are gifts that are dropped at max once per player's turn randomly from the sky
    // AirDrops provide different special powerups

    private int x, y;
    private final int height, width;
    private final Rectangle hitbox;
    private TextureRegion image;
    private PowerUp powerUp;
    private int powerUpType;
    // 0 = extra health, 1 = fuel, 2 = get a SpecialAttack, 3 = sheild, 4 = extra attack power, 5 = two attacks in a row etc.

    public AirDrop(int x, int y, int height, int width) {
        this.x = x;
        this.y = y;
        this.height = height;
        this.width = width;
        hitbox = new Rectangle(x, y, width, height);
    }

    public int getX(Tank tank) {
        // Incorrect implementation
        // Need to implement a random x near the tank
        return (int) (Math.random() * 1000);
    }

    public int getY() {
        return 0;
    }

    public void fall() {
        // decrease y coordinate
    }

    public TextureRegion getImage() {
        return this.image;
    }

    public void applyPowerUpTo(Tank tank) {
        // apply powerup to tank
        // must select one of few powerups randomly
    }

    public void destroy(Ground ground) {
        // the AirDrop will be destroyed as soon as it touches the Ground and no one picks it up
        // calculate distance from ground at the index [this.x] and destroy the AirDrop
    }
}
