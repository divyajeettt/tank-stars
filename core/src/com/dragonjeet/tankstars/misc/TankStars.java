package com.dragonjeet.tankstars.misc;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.dragonjeet.tankstars.exception.FuelExhaustedException;
import com.dragonjeet.tankstars.exception.TankOutOfScreenException;
import com.dragonjeet.tankstars.menu.MainMenu;
import com.dragonjeet.tankstars.powerup.ExtraDamagePowerUp;
import com.dragonjeet.tankstars.powerup.ExtraFuelPowerUp;
import com.dragonjeet.tankstars.powerup.PowerUp;
import com.dragonjeet.tankstars.powerup.ShieldPowerUp;
import com.dragonjeet.tankstars.tank.Tank;
import com.dragonjeet.tankstars.attack.Bullet;
import java.io.Serializable;
import java.util.Random;

public class TankStars extends Game implements Serializable {
	public SpriteBatch batch;
	private Tank tank1, tank2;
	private Ground ground;
	private boolean canMove;
	private Bullet bullet;
	private int turn;
	private PowerUp powerUp;
	private static Random random = new Random();

	public void create() {
		batch = new SpriteBatch();
		this.setScreen(new MainMenu(this));
		canMove = true;
		this.bullet = null;
		turn = 0;
		this.powerUp = null;
	}

	public Tank getCurrentTank() {
		return (turn==0) ? tank1 : tank2;
	}

	public void nextTurn() {
		turn = 1 - turn;
		if (powerUp == null) {
			int dice = random.nextInt(100);
			if (dice < 5) powerUp = new ExtraDamagePowerUp(10);
			else if (dice < 10) powerUp = new ShieldPowerUp(10);
			else if (dice < 15) powerUp = new ExtraFuelPowerUp(10);
		}
		try {
			tank1.setFuel(tank1.getMaxFuel());
			tank2.setFuel(tank2.getMaxFuel());
		} catch (FuelExhaustedException e) {
			Gdx.app.log("TankStars", e.getMessage());
		}
	}

	public PowerUp getPowerUp() {
		return powerUp;
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
		try {
			if (player == 1) {
				tank.setX(200);
				this.tank1 = tank;
			} else {
				tank.setX(ground.getWidth() - tank.getWidth() - 200);
				this.tank2 = tank;
			}
		} catch (TankOutOfScreenException e) {
			Gdx.app.log("TankStars", e.getMessage());
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

	public void setNewGround() {
		this.ground = new Ground(Gdx.graphics.getWidth());
	}

	public void render() {
		super.render();
	}
}