package com.dragonjeet.tankstars.misc;

import com.badlogic.gdx.scenes.scene2d.ui.ProgressBar;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import java.io.Serializable;

public class HealthBar extends ProgressBar implements Serializable {
    private float height, width;

    public HealthBar(float min, float max, float stepSize, boolean vertical, Skin skin) {
        super(min, max, stepSize, vertical, skin);
        setWidth(100);
        setHeight(50);
    }

    public void setCustomWidth(float width) {
        this.width = width;
    }

    public void setCustomHeight(float height) {
        this.height = height;
    }
}
