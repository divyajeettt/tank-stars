package com.dragonjeet.tankstars.misc;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.dragonjeet.tankstars.menu.MainMenu;
import com.dragonjeet.tankstars.tank.Tank;


public class TankStars extends Game {
	public SpriteBatch batch;
	private Tank tank1, tank2;
	private Ground ground;

	public void create() {
		batch = new SpriteBatch();
		ground = new Ground(Gdx.graphics.getWidth());
		this.setScreen(new MainMenu(this));
	}

	public Tank getTank1() {
		return tank1;
	}

	public Tank getTank2() {
		return tank2;
	}

	public void setTank1(Tank tank1) {
		this.tank1 = tank1;
	}

	public void setTank2(Tank tank2) {
		this.tank2 = tank2;
	}

	public Ground getGround() {
		return ground;
	}

	public void render() {
		super.render();
	}
}
