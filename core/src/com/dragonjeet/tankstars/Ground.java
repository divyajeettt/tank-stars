package com.dragonjeet.tankstars;

import java.util.ArrayList;
import java.lang.Math;
import java.util.Random;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.Gdx;

class Ground {
    private ArrayList<Double> heights;
    private int width;
    Ground(int width) {
        this.width = width;
        heights = new ArrayList<Double>();
        for(int i = 0; i < width; i++) {
            heights.add(0.0);
        }
        randomize();
    }

    void randomize() {
        Random rng = new Random();
        double scalingFactor = ((double)Gdx.graphics.getHeight() )/768f;
        for (int i = 0; i < 4;i++) {
            double mean = 400f*scalingFactor - rng.nextInt(300);
            double freq = 1f / (200 - rng.nextInt(100));
            double amp = 100f*scalingFactor - rng.nextInt(90);
            double shift = 10*rng.nextDouble();
            for (int j = 0;j<width;j++) {
                heights.set(j, heights.get(j) + amp*Math.sin(j*freq + shift) + mean);
            }
        }
        for (int j = 0;j<width;j++) {
            heights.set(j, heights.get(j)/4);
        }
    }

    void draw(ShapeRenderer shapeRenderer) {
        for(int i = 0; i < width; i++) {
            shapeRenderer.rect(i, 0, 1, heights.get(i).floatValue());
        }
    }

    double getHeight(int x) {
        return heights.get(x);
    }

}