package com.dragonjeet.tankstars;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.Value;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.utils.Align;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.utils.viewport.ScreenViewport;

public class TankStars extends ApplicationAdapter {

	private Stage stage;
	private Button menuButton;
	private Skin skin;
	private Table root;

	@Override
	public void create () {
		stage = new Stage(new ScreenViewport());
		skin = new Skin(Gdx.files.internal("orangepeelui/uiskin.json"));
		Gdx.input.setInputProcessor(stage);

		root = new Table();
		root.setFillParent(true);
		stage.addActor(root);

		Table table = new Table();
		root.add(table).growX().align(Align.top).colspan(4);
		menuButton = new Button(skin);
		table.add(menuButton).align(Align.top | Align.left).padTop(10).expandX();

		Button healthBar1 = new Button(skin);
		table.add(healthBar1).align(Align.top).padTop(10).width(Value.percentWidth(7f, menuButton));
		
		Button vs = new Button(skin);
		table.add(vs).align(Align.top).pad(10).width(Value.percentWidth(1f, menuButton));

		Button healthBar2 = new Button(skin);
		table.add(healthBar2).align(Align.top).padTop(10).width(Value.percentWidth(7f, menuButton));

		Button invisButton = new Button(skin);
		invisButton.setColor(0.5f, 0.5f, 0.5f, 0f);
		table.add(invisButton).align(Align.top | Align.right).padTop(10).width(Value.percentWidth(1f, menuButton)).expandX();

		root.row();

		Button fuelBar = new Button(skin);
		root.add(fuelBar).align(Align.left).padLeft(30).padTop(80).expandX().width(Value.percentWidth(4f, menuButton)).height(Value.percentHeight(0.5f,menuButton)).expandY();
		
		Button selector = new Button(skin);
		root.add(selector).width(Value.percentWidth(2f, menuButton)).height(Value.percentHeight(1.5f,menuButton)).padTop(80).expandY();

		Button fireButton = new Button(skin);
		root.add(fireButton).align(Align.left).padLeft(5).padTop(80).width(Value.percentWidth(2f, menuButton)).height(Value.percentHeight(1.5f,menuButton)).expandY();

		root.add().expandX().width(Value.percentWidth(1f,fuelBar)).expandY();




		root.row();
	}

	@Override
	public void render () {
		Gdx.gl.glClearColor(0.9f, 0.9f, 0.9f, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		stage.act();
		stage.draw();
	}
	

	@Override
	public void resize (int width, int height) {
		stage.getViewport().update(width, height, true);
	}

	@Override
	public void dispose () {
		skin.dispose();
		stage.dispose();
	}
}
