package com.dragonjeet.tankstars;


import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.ui.*;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.scenes.scene2d.utils.Drawable;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.utils.Align;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.OrthographicCamera;



class HealthBar extends ProgressBar {

	private	float height, width;
	public HealthBar(float min, float max, float stepSize, boolean vertical, Skin skin) {
		super(min, max, stepSize, vertical, skin);

	}

	void setCustomWidth(float width) {
		this.width = width;
	}

	void setCustomHeight(float height) {
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

public class TankStars extends Game {

	SpriteBatch batch;

	public void create() {
		batch = new SpriteBatch();
		this.setScreen(new MainMenu(this));
	}

	public void render() {
		super.render();
	}
}




class MainScreen implements Screen {

	private Stage stage;
	private Button menuButton;
	private Skin skin;
	private Table root;
	private Texture background;
	private OrthographicCamera camera;
	private Viewport viewport;
	private int width,height;

	final TankStars game;
	private Ground ground;
	private ShapeRenderer renderer;
	MainScreen (final TankStars game) {
	//	width = Gdx.graphics.getWidth();
	//	height = Gdx.graphics.getHeight();
		width = 1366;
		height = 768;
		this.game = game;
		width = Gdx.graphics.getWidth();
		height = Gdx.graphics.getHeight();

		ground = new Ground(width);
		renderer = new ShapeRenderer();
		background = new Texture("background.png");

		camera = new OrthographicCamera(width, height);
		camera.setToOrtho(false, width,height);
		camera.update();

		viewport = new FitViewport(width, height, camera);
		stage = new Stage(viewport);
		skin = new Skin(Gdx.files.internal("orangepeelui/uiskin.json"));
		Gdx.input.setInputProcessor(stage);

		root = new Table();
		root.setFillParent(true);
		stage.addActor(root);

		Table table = new Table();
		root.add(table).growX().align(Align.top).colspan(4);


		Drawable menuDraw = new TextureRegionDrawable(new TextureRegion(new Texture(Gdx.files.internal("menu.png"))));
		menuButton = new Button(skin);
		Value buttonHeight = Value.percentHeight(1f,menuButton);
		Value buttonWidth = Value.percentWidth(1f, menuButton);
		Button.ButtonStyle style = new Button.ButtonStyle();
		style.up = menuDraw;
		style.down = menuDraw;
		menuButton.setStyle(style);
		menuButton.addListener(new ChangeListener() {
			@Override
			public void changed(ChangeEvent event, Actor actor) {
				game.setScreen(new PauseMenu(game));
				dispose();
			}
		});	
		table.add(menuButton).align(Align.top | Align.left).padTop(10).expandX().width(buttonWidth).height(buttonHeight).padLeft(10);

		HealthBar healthBar1 = new HealthBar(0, 100, 1, false, skin);
		healthBar1.setCustomWidth((Value.percentWidth(7f,menuButton)).get());
		healthBar1.setCustomHeight((Value.percentHeight(1f,menuButton)).get());
		table.add(healthBar1).align(Align.top).padTop(10).width(Value.percentWidth(7f, menuButton)).height(Value.percentHeight(1f, menuButton));
		healthBar1.setValue(100);

		Button vs = new Button(skin);

		Drawable vsDraw = new TextureRegionDrawable(new TextureRegion(new Texture(Gdx.files.internal("vs.png"))));
		Button.ButtonStyle vsStyle = new Button.ButtonStyle();
		vsStyle.down = vsDraw;
		vsStyle.up = vsDraw;
		vs.setStyle(vsStyle);
		table.add(vs).align(Align.top).pad(10).width(Value.percentWidth(1f, menuButton)).height(buttonHeight);

		HealthBar healthBar2 = new HealthBar(0, 100, 1, false, skin);
		healthBar2.setCustomWidth((Value.percentWidth(7f,menuButton)).get());
		healthBar2.setCustomHeight((Value.percentHeight(2f,menuButton)).get());
		table.add(healthBar2).align(Align.top).padTop(10).width(Value.percentWidth(7f, menuButton)).height(Value.percentHeight(1f, menuButton));
		healthBar2.setValue(100);

		Button invisButton = new Button(skin);
		invisButton.setColor(0.5f, 0.5f, 0.5f, 0f);
		table.add(invisButton).align(Align.top | Align.right).padTop(10).width(Value.percentWidth(1f, menuButton)).expandX();

		root.row();

		Button fuelBar = new Button(skin);
		root.add(fuelBar).align(Align.left).padLeft(30).padTop(80).expandX().width(Value.percentWidth(4f, menuButton)).height(Value.percentHeight(0.5f,menuButton)).expandY();
		
		Button selector = new Button(skin);
		root.add(selector).width(Value.percentWidth(2f, menuButton)).height(Value.percentHeight(1.5f,menuButton)).padTop(80).expandY();

//		Drawable fireDrawable = new TextureRegionDrawable(new TextureRegion(new Texture(Gdx.files.internal("fire.png"))));
//		Button fireButton = new Button(fireDrawable,fireDrawable);

		ImageButton fireButton = new ImageButton(
			new TextureRegionDrawable(new TextureRegion(new Texture(Gdx.files.internal("fireUp.png")))),
			new TextureRegionDrawable(new TextureRegion(new Texture(Gdx.files.internal("fireDown.png"))))
		);

		root.add(fireButton).align(Align.left).padLeft(5).padTop(80).width(Value.percentWidth(2f, menuButton)).height(Value.percentHeight(1.5f,menuButton)).expandY();
		root.add().expandX().width(Value.percentWidth(1f,fuelBar)).expandY();

		root.row();

		Button leftButton = new Button(skin);
		root.add(leftButton).align(Align.right).width(Value.percentWidth(1.2f, menuButton)).height(Value.percentHeight(1f,menuButton)).expandY();

		Button rightButton = new Button(skin);
		root.add(rightButton).align(Align.left).padLeft(5).width(Value.percentWidth(1.2f, menuButton)).height(Value.percentHeight(1f,menuButton)).expandY();

		root.add().expandX().width(Value.percentWidth(1f,menuButton)).expandY();

		Touchpad.TouchpadStyle touchpadStyle = new Touchpad.TouchpadStyle();
		touchpadStyle.knob = new TextureRegionDrawable(new TextureRegion(new Texture(Gdx.files.internal("aim.png"))));
		touchpadStyle.background = new TextureRegionDrawable(new TextureRegion(new Texture(Gdx.files.internal("transparent.png"))));
		Touchpad touchpad = new Touchpad(0, touchpadStyle);
		root.add(touchpad).expandX().colspan(4);
	}

	@Override
	public void render (float d) {
		//Log screen width and height
		Gdx.gl.glClearColor(0.9f, 0.9f, 0.9f, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		camera.update();
		viewport.update(width, height);
		game.batch.setProjectionMatrix(stage.getViewport().getCamera().combined);
		game.batch.begin();
		game.batch.draw(background, 0, 0, width, height);
		game.batch.end();

		renderer.setColor(0f,1f,0f,1f);
		renderer.setProjectionMatrix(stage.getViewport().getCamera().combined);
		renderer.begin(ShapeRenderer.ShapeType.Filled);
		ground.draw(renderer);
		renderer.end();

		stage.act();
		stage.draw();
	}
	

	@Override
	public void resize (int width, int height) {
		this.width = width;
		this.height = height;
		stage.getViewport().update(width, height, true);
		camera.setToOrtho(false,width,height);
		camera.update();
	}

	@Override
	public void show() {

	}


	@Override
	public void pause() {

	}
	@Override
	public void resume() {

	}

	@Override
	public void hide() {

	}
	@Override
	public void dispose () {
		skin.dispose();
		stage.dispose();
		background.dispose();
	}
}
