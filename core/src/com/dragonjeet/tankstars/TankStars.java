package com.dragonjeet.tankstars;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.dragonjeet.tankstars.menu.MainMenu;


public class TankStars extends Game {
	public SpriteBatch batch;
	private Tank tank1, tank2;
	private Ground ground;

	public void create() {
		batch = new SpriteBatch();
		ground = new Ground(Gdx.graphics.getWidth());
		tank1 = new Tank(100, 100, 59, 100, ground);
		tank2 = new Tank(1000, 2000, 59, 100, ground);
		this.setScreen(new MainMenu(this));
	}

	public Tank getTank1() {
		return tank1;
	}

	public Tank getTank2() {
		return tank2;
	}

	public Ground getGround() {
		return ground;
	}

	public void render() {
		super.render();
	}
}
