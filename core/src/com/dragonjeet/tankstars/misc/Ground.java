package com.dragonjeet.tankstars.misc;

import java.io.Serializable;
import java.util.ArrayList;
import java.lang.Math;
import java.util.Random;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.Gdx;


public class Ground implements Serializable {
    private ArrayList<Double> heights;
    private ArrayList<Double> boundary;
    private final int width;

    public Ground(int width) {
        this.width = width;
        heights = new ArrayList<>();
        boundary = new ArrayList<>();
        for(int i = 0; i < width; i++) {
            heights.add(0.0);
            boundary.add(0.0);
        }
        randomize();
    }

    public void randomize() {
        Random rng = new Random();
        double scalingFactor = ((double) Gdx.graphics.getHeight()) / 768f;

        for (int i=0; i < 4; i++) {
            double mean = 400f*scalingFactor - rng.nextInt(300);
            double freq = 1f / (200 - rng.nextInt(100));
            double amp = 100f*scalingFactor - rng.nextInt(90);
            double shift = 10*rng.nextDouble();
            for (int j=0; j < width; j++) {
                heights.set(j, getHeight(j) + amp*Math.sin(j*freq + shift) + mean);
            }
        }
        for (int i=0; i < width; i++) {
            heights.set(i, heights.get(i)/4);
        }
        updateBoundary();
    }

    public void draw(ShapeRenderer shapeRenderer) {
        for(int i = 0; i < width; i++) {
            shapeRenderer.rect(i, 0, 1, heights.get(i).floatValue());
        }
    }

    public void drawBoundary(ShapeRenderer shapeRenderer) {
        for(int i = 0; i < width; i++) {
            shapeRenderer.rect(i, 0, 1, boundary.get(i).floatValue());
        }
    }

    private void updateBoundary() {
        for (int i=0; i < width; i++) {
            boundary.set(i, getHeight(i) - 8f);
        }
    }

    public int getWidth() {
        return width;
    }

    public double getHeight(int x) {
        return heights.get(x);
    }

    public void mutilate(int x, int damage) {
        for (int i = x - damage; i < x + damage; i++) {
            if (i >= 0 && i < width) {
                double distance = Math.abs(i - x);
                double newHeight = getHeight(i) - (damage - distance);
                if (newHeight < 0) newHeight = 0;
                heights.set(i, newHeight);
            }
        }

        for (int i = 0; i < width; i++) {
            int distance = Math.abs(x - i);
            if (distance <= damage) {
                heights.set(i, getHeight(i) - damage * (damage - distance) / damage);
                if (getHeight(i) < 0) heights.set(i, 0.0);
            }
        }
        updateBoundary();
    }
}