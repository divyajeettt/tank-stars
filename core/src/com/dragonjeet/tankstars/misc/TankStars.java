package com.dragonjeet.tankstars.misc;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.dragonjeet.tankstars.menu.MainMenu;
import com.dragonjeet.tankstars.tank.Tank;
import com.dragonjeet.tankstars.attack.Bullet;
import java.io.Serializable;

public class TankStars extends Game implements Serializable {
	public SpriteBatch batch;
	private Tank tank1, tank2;
	private Ground ground;
	private boolean canMove;
	private Bullet bullet;
	private int turn;

	public void create() {
		batch = new SpriteBatch();
		ground = new Ground(Gdx.graphics.getWidth());
		this.setScreen(new MainMenu(this));
		canMove = true;
		this.bullet = null;
		turn = 0;
	}

	public Tank getCurrentTank() {
		return (turn==0) ? tank1 : tank2;
	}

	public void nextTurn() {
		turn = (turn==0) ? 1 : 0;
		tank1.setFuel(100);
		tank2.setFuel(100);
	}

	public void setCanMove(boolean canMove) {
		this.canMove = canMove;
	}

	public boolean getCanMove() {
		return canMove;
	}

	public Tank getTank1() {
		return tank1;
	}

	public Tank getTank2() {
		return tank2;
	}

	public void setTank(Tank tank, int player) {
		if (player == 1) {
			tank.setX(100);
			this.tank1 = tank;
		} else {
			tank.setX(600);
			this.tank2 = tank;
		}
	}

	public Ground getGround() {
		return ground;
	}

	public Bullet getBullet() {
		return bullet;
	}

	public void setBullet(Bullet bullet) {
		this.bullet = bullet;
	}

	public void render() {
		super.render();
	}
}