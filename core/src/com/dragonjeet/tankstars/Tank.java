package com.dragonjeet.tankstars;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import java.lang.Math;

class Tank {
    private int x, y, height, width;
    int xVelocity, yVelocity;
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

    void draw(SpriteBatch batch) {
        batch.draw(image, x, getY(), width/2,height/2,width,height,1,1,getAngle());
    }

    int getX() {
        return x;
    }
    int getY() {
        return (int) ground.getHeight(x+width/2);
    }

    void move() {
        x+=xVelocity;
    }

    float getAngle() {
        return (float) Math.atan((ground.getHeight(x+width) - ground.getHeight(x))/width) * 180 / (float) Math.PI;
    }
    int getHeight() {
        return height;
    }
    int getWidth() {
        return width;
    }    
}