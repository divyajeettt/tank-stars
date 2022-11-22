package com.dragonjeet.tankstars;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import java.lang.Math;

class Tank {
    private int x, y, height, width;
    private int xVelocity, yVelocity;
    private Rectangle hitbox;
    private TextureRegion image;
    private Ground ground;

    Tank(int x, int y,int height, int width, Ground ground) {
        this.x = x;
        this.y = y;
        this.height = height;
        this.width = width;
        this.ground = ground;
        hitbox = new Rectangle(x, y, width, height);
        image = new TextureRegion(new Texture("tank-1.png"));
    }

    Tank(int x, int y,int height, int width, Ground ground, boolean flip) {
        this.x = x;
        this.y = y;
        this.xVelocity = 0;
        this.yVelocity = 0;
        this.height = height;
        this.width = width;
        this.ground = ground;
        hitbox = new Rectangle(x, y, width, height);
        image = new TextureRegion(new Texture("tank-1.png"));
        if (flip) {
            image.flip(true, false);
        }
    }

    public void setImage(TextureRegion image, boolean flip) {
        this.image = image;
        if (flip) {
            this.image.flip(true, false);
        }
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