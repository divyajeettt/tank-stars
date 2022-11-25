package com.dragonjeet.tankstars;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Rectangle;
import java.lang.Math;


public class Tank {
    private int x, y;
    private int xVelocity, yVelocity;
    private final int height, width;
    private final Rectangle hitbox;
    private final Ground ground;
    private TextureRegion image;

    public Tank(int x, int y,int height, int width, Ground ground) {
        this.x = x;
        this.y = y;
        this.height = height;
        this.width = width;
        this.ground = ground;
        hitbox = new Rectangle(x, y, width, height);
    }

    public void setImage(TextureRegion image, boolean flip) {
        this.image = image;
        this.image.flip(flip, false);
    }

    public void draw(SpriteBatch batch) {
        batch.draw(image, x, getY(), width/2f, height/2f, width, height, 1, 1, getAngle());
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return ((int) ground.getHeight(x+width/2));
    }

    public void move() {
        x += xVelocity;
    }

    public int getXVelocity() {
        return xVelocity;
    }

    public void setXVelocity(int xVelocity) {
        this.xVelocity = xVelocity;
    }

    public int getYVelocity() {
        return yVelocity;
    }

    public void setYVelocity(int yVelocity) {
        this.yVelocity = yVelocity;
    }

    public float getAngle() {
        return ((float) Math.atan((ground.getHeight(x+width) - ground.getHeight(x))/width) * 180 / (float) Math.PI);
    }

    public int getHeight() {
        return height;
    }

    public int getWidth() {
        return width;
    }    
}