package com.dragonjeet.tankstars;

import com.badlogic.gdx.scenes.scene2d.ui.ProgressBar;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;

public class HealthBar extends ProgressBar {
    private float height, width;

    public HealthBar(float min, float max, float stepSize, boolean vertical, Skin skin) {
        super(min, max, stepSize, vertical, skin);
    }

    public void setCustomWidth(float width) {
        this.width = width;
    }

    public void setCustomHeight(float height) {
        this.height = height;
    }

    @Override
    public float getPrefWidth() {
        return width;
    }

    @Override
    public float getPrefHeight() {
        return height;
    }
}
