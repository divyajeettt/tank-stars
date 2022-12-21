package com.dragonjeet.tankstars.misc;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.*;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.scenes.scene2d.utils.Drawable;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.utils.Align;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.dragonjeet.tankstars.exception.FuelExhaustedException;
import com.dragonjeet.tankstars.exception.TankDeadException;
import com.dragonjeet.tankstars.exception.TankOutOfScreenException;
import com.dragonjeet.tankstars.menu.PauseMenu;
import com.dragonjeet.tankstars.menu.VictoryMenu;
import java.io.Serializable;

public class BattleScreen implements Screen, Serializable {
    private int width, height;
    private final Stage stage;
    private final Skin skin;
    private final Texture background;
    private final OrthographicCamera camera;
    private final Viewport viewport;
    private final ShapeRenderer renderer;
    private Button pauseButton, vs;
    private ImageButton fireButton;
    private Touchpad moveTouchpad, aimTouchpad;
    public final TankStars game;
    private boolean drawAimLine = false;
    protected ProgressBar healthBar1, healthBar2, fuelBar;

    public BattleScreen(final TankStars game) {
        width = Gdx.graphics.getWidth();
        height = Gdx.graphics.getHeight();
        this.game = game;
        renderer = new ShapeRenderer();
        background = new Texture("backgrounds/main.png");

        camera = new OrthographicCamera(width, height);
        camera.setToOrtho(false, width, height);
        camera.update();

        viewport = new FitViewport(width, height, camera);
        stage = new Stage(viewport);
        skin = new Skin(Gdx.files.internal("orangepeelui/uiskin.json"));
        Gdx.input.setInputProcessor(stage);

        Table root = new Table();
        root.setFillParent(true);
        stage.addActor(root);

        Table table = new Table();
        root.add(table).growX().align(Align.top).colspan(4);

        makePauseButton();
        Value buttonHeight = Value.percentHeight(1f, pauseButton);
        Value buttonWidth = Value.percentWidth(1f, pauseButton);
        table.add(pauseButton).align(Align.top | Align.left).padTop(10).expandX().width(buttonWidth).height(buttonHeight).padLeft(10);

        makeHealthBars();
        table.add(healthBar1).align(Align.top).padTop(10).width(Value.percentWidth(7f, pauseButton)).height(Value.percentHeight(1f, pauseButton));
        table.add(vs).align(Align.top).pad(10).width(Value.percentWidth(1f, pauseButton)).height(buttonHeight);
        table.add(healthBar2).align(Align.top).padTop(10).width(Value.percentWidth(7f, pauseButton)).height(Value.percentHeight(1f, pauseButton));

        Button invisibleButton = new Button(skin);
        invisibleButton.setColor(0.5f, 0.5f, 0.5f, 0f);
        table.add(invisibleButton).align(Align.top | Align.right).padTop(10).width(Value.percentWidth(1f, pauseButton)).expandX();

        root.row();

        makeFuelBar();
        root.add(fuelBar).align(Align.center).padLeft(30).padTop(80).expandX().width(Value.percentWidth(4f, pauseButton)).height(Value.percentHeight(0.5f, pauseButton)).expandY();

        Button selector = new Button(
            new TextureRegionDrawable(new TextureRegion(new Texture(Gdx.files.internal("buttons/up/selector.png")))),
            new TextureRegionDrawable(new TextureRegion(new Texture(Gdx.files.internal("buttons/down/selector.png"))))
        );
        root.add(selector).width(Value.percentWidth(2f, pauseButton)).height(Value.percentHeight(1.5f, pauseButton)).padTop(80).expandY().align(Align.right);

        makeFireButton();
        root.add(fireButton).padLeft(5).padTop(80).width(Value.percentWidth(2f, pauseButton)).height(Value.percentHeight(1.5f, pauseButton)).expandY().padLeft(5).align(Align.left);
        root.add().expandX().width(Value.percentWidth(1f, fuelBar)).expandY();

        makeTouchpads();
        root.row();
        root.add(moveTouchpad).align(Align.center).expandY();
        root.add().expandX().width(Value.percentWidth(1f, pauseButton)).expandY();
        root.add(aimTouchpad).expandX().colspan(4);
    }

    @Override
    public void render(float d) {
        healthBar1.setValue(game.getTank1().getHealth());
        healthBar2.setValue(game.getTank2().getHealth());
        fuelBar.setValue((game.getCurrentTank().getFuel()/(1f*game.getCurrentTank().getMaxFuel())) * 100);

        Gdx.gl.glClearColor(0.9f, 0.9f, 0.9f, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        camera.update();
        viewport.update(width, height);
        game.batch.setProjectionMatrix(stage.getViewport().getCamera().combined);
        game.batch.begin();

        game.batch.draw(background, 0, 0, width, height);
        renderMovement();
        game.batch.end();
        renderer.setProjectionMatrix(stage.getViewport().getCamera().combined);
        renderGround();
        stage.act();
        stage.draw();
    }

    @Override
    public void resize(int width, int height) {
        this.width = width;
        this.height = height;
        stage.getViewport().update(width, height, true);
        camera.setToOrtho(false, width, height);
        camera.update();
    }

    private void renderMovement() {
        if (game.getCanMove()) {
            try {
                game.getTank1().move();
                game.getTank2().move();
            }
            catch (FuelExhaustedException | TankOutOfScreenException e) {
                Gdx.app.log("TankStars", e.getMessage());
            }
            if (drawAimLine) game.getCurrentTank().drawAim(game.batch);
        }
        else {
            game.getBullet().draw(game.batch);
            try {
                game.setCanMove(game.getBullet().move(game.getGround(), game.getCurrentTank()));
            }
            catch (TankDeadException e) {
                // other tank wins
                game.batch.end();
                game.setScreen(new VictoryMenu(game));
                return;
            }
        }
        game.getTank1().draw(game.batch);
        game.getTank2().draw(game.batch);
    }

    private void renderGround() {
        renderer.setColor(0.4f, 0.06f, 0.06f, 1f);
        renderer.setProjectionMatrix(stage.getViewport().getCamera().combined);
        renderer.begin(ShapeRenderer.ShapeType.Filled);
        game.getGround().draw(renderer);
        renderer.end();

        renderer.setColor(0.69f, 0.1f, 0.1f, 1f);
        renderer.setProjectionMatrix(stage.getViewport().getCamera().combined);
        renderer.begin(ShapeRenderer.ShapeType.Filled);
        game.getGround().drawBoundary(renderer);
        renderer.end();
    }

    private void makePauseButton() {
        pauseButton = new Button(skin);
        Button.ButtonStyle style = new Button.ButtonStyle();
        style.up = new TextureRegionDrawable(new TextureRegion(new Texture(Gdx.files.internal("buttons/up/pause.png"))));
        style.down = new TextureRegionDrawable(new TextureRegion(new Texture(Gdx.files.internal("buttons/down/pause.png"))));
        pauseButton.setStyle(style);

        pauseButton.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                game.setScreen(new PauseMenu(game));
                dispose();
            }
        });
    }

    private void makeHealthBars() {
        healthBar1 = new HealthBar(0, game.getTank1().getMaxHealth(), 1, false, skin);
        healthBar1.setValue(game.getTank1().getHealth());

        vs = new Button(skin);
        Drawable vsDraw = new TextureRegionDrawable(new TextureRegion(new Texture(Gdx.files.internal("misc/vs.png"))));
        Button.ButtonStyle vsStyle = new Button.ButtonStyle();
        vsStyle.down = vsStyle.up = vsDraw;
        vs.setStyle(vsStyle);

        healthBar2 = new HealthBar(0, game.getTank2().getMaxHealth(), 1, false, skin);
        healthBar2.setValue(game.getTank2().getHealth());
    }

    private void makeFuelBar() {
        fuelBar = new ProgressBar(0, game.getCurrentTank().getMaxFuel(), 1, false, skin);
        fuelBar.setValue(game.getCurrentTank().getFuel());
    }

    private void makeFireButton() {
        fireButton = new ImageButton(
            new TextureRegionDrawable(new TextureRegion(new Texture(Gdx.files.internal("buttons/up/fire.png")))),
            new TextureRegionDrawable(new TextureRegion(new Texture(Gdx.files.internal("buttons/down/fire.png"))))
        );

        fireButton.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                if (game.getCanMove()) {
                    game.setCanMove(false);
                    game.setBullet(game.getCurrentTank().shoot());
                    game.nextTurn();
                    drawAimLine = false;
                }
            }
        });
    }

    private void makeTouchpads() {
        Touchpad.TouchpadStyle touchpadStyle = new Touchpad.TouchpadStyle();
        touchpadStyle.knob = new TextureRegionDrawable(new TextureRegion(new Texture(Gdx.files.internal("misc/aim.png"))));
        touchpadStyle.background = new TextureRegionDrawable(new TextureRegion(new Texture(Gdx.files.internal("misc/transparent.png"))));

        moveTouchpad = new Touchpad(0, touchpadStyle);
        moveTouchpad.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                if (moveTouchpad.getKnobPercentX() > 0) {
                    game.getCurrentTank().setXVelocity(1);
                } else if (moveTouchpad.getKnobPercentX() < 0) {
                    game.getCurrentTank().setXVelocity(-1);
                } else {
                    game.getCurrentTank().setXVelocity(0);
                }
            }
        });

        aimTouchpad = new Touchpad(0, touchpadStyle);
        aimTouchpad.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                game.getCurrentTank().aimAt(aimTouchpad.getKnobPercentX(), aimTouchpad.getKnobPercentY());
            }
        });

        aimTouchpad.addListener(new InputListener() {
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                drawAimLine = true;
                return true;
            }

            @Override
            public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
                drawAimLine = false;
            }
        });
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
    public void dispose() {
        skin.dispose();
        stage.dispose();
        background.dispose();
    }
}