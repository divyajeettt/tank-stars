package com.dragonjeet.tankstars.misc;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.*;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.scenes.scene2d.utils.Drawable;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.utils.Align;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.dragonjeet.tankstars.exception.TankOutOfScreenException;
import com.dragonjeet.tankstars.menu.PauseMenu;

public class BattleScreen implements Screen {
    private int width, height;
    private final Stage stage;
    private final Skin skin;
    private final Texture background;
    private final OrthographicCamera camera;
    private final Viewport viewport;
    private final ShapeRenderer renderer;
    public final TankStars game;
    public int numAirDrops;

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

        Button pauseButton = new Button(skin);
        Value buttonHeight = Value.percentHeight(1f, pauseButton);
        Value buttonWidth = Value.percentWidth(1f, pauseButton);
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

        table.add(pauseButton).align(Align.top | Align.left).padTop(10).expandX().width(buttonWidth).height(buttonHeight).padLeft(10);

        HealthBar healthBar1 = new HealthBar(0, 100, 1, false, skin);
        healthBar1.setCustomWidth((Value.percentWidth(7f, pauseButton)).get());
        healthBar1.setCustomHeight((Value.percentHeight(1f, pauseButton)).get());
        table.add(healthBar1).align(Align.top).padTop(10).width(Value.percentWidth(7f, pauseButton)).height(Value.percentHeight(1f, pauseButton));
        healthBar1.setValue(100);

        Button vs = new Button(skin);

        Drawable vsDraw = new TextureRegionDrawable(new TextureRegion(new Texture(Gdx.files.internal("misc/vs.png"))));
        Button.ButtonStyle vsStyle = new Button.ButtonStyle();
        vsStyle.down = vsDraw;
        vsStyle.up = vsDraw;
        vs.setStyle(vsStyle);
        table.add(vs).align(Align.top).pad(10).width(Value.percentWidth(1f, pauseButton)).height(buttonHeight);

        HealthBar healthBar2 = new HealthBar(0, 100, 1, false, skin);
        healthBar2.setCustomWidth((Value.percentWidth(7f, pauseButton)).get());
        healthBar2.setCustomHeight((Value.percentHeight(2f, pauseButton)).get());
        table.add(healthBar2).align(Align.top).padTop(10).width(Value.percentWidth(7f, pauseButton)).height(Value.percentHeight(1f, pauseButton));
        healthBar2.setValue(100);

        Button invisButton = new Button(skin);
        invisButton.setColor(0.5f, 0.5f, 0.5f, 0f);
        table.add(invisButton).align(Align.top | Align.right).padTop(10).width(Value.percentWidth(1f, pauseButton)).expandX();

        root.row();

        Button fuelBar = new TextButton("Fuel", skin);
        root.add(fuelBar).align(Align.left).padLeft(30).padTop(80).expandX().width(Value.percentWidth(4f, pauseButton)).height(Value.percentHeight(0.5f, pauseButton)).expandY();

        Button selector = new Button(
                new TextureRegionDrawable(new TextureRegion(new Texture(Gdx.files.internal("buttons/up/selector.png")))),
                new TextureRegionDrawable(new TextureRegion(new Texture(Gdx.files.internal("buttons/down/selector.png"))))
        );
        root.add(selector).width(Value.percentWidth(2f, pauseButton)).height(Value.percentHeight(1.5f, pauseButton)).padTop(80).expandY().align(Align.right);

        ImageButton fireButton = new ImageButton(
            new TextureRegionDrawable(new TextureRegion(new Texture(Gdx.files.internal("buttons/up/fire.png")))),
            new TextureRegionDrawable(new TextureRegion(new Texture(Gdx.files.internal("buttons/down/fire.png"))))
        );

        root.add(fireButton).padLeft(5).padTop(80).width(Value.percentWidth(2f, pauseButton)).height(Value.percentHeight(1.5f, pauseButton)).expandY().padLeft(5).align(Align.left);
        root.add().expandX().width(Value.percentWidth(1f, fuelBar)).expandY();

        root.row();

        Touchpad.TouchpadStyle moveStyle = new Touchpad.TouchpadStyle();
        moveStyle.knob = new TextureRegionDrawable(new TextureRegion(new Texture(Gdx.files.internal("misc/aim.png"))));
        moveStyle.background = new TextureRegionDrawable(new TextureRegion(new Texture(Gdx.files.internal("misc/transparent.png"))));
        final Touchpad moveButton = new Touchpad(0, moveStyle);
        moveButton.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                if (moveButton.getKnobPercentX() > 0) {
                    game.getTank1().setXVelocity(1);
                } else if (moveButton.getKnobPercentX() < 0) {
                    game.getTank1().setXVelocity(-1);
                } else {
                    game.getTank1().setXVelocity(0);
                }
            }
        });
        root.add(moveButton).align(Align.left).expandY();
        root.add().expandX().width(Value.percentWidth(1f, pauseButton)).expandY();

        // this touchpad will be converted to aim-angle-change touchpad
        Touchpad.TouchpadStyle touchpadStyle = new Touchpad.TouchpadStyle();
        touchpadStyle.knob = new TextureRegionDrawable(new TextureRegion(new Texture(Gdx.files.internal("misc/aim.png"))));
        touchpadStyle.background = new TextureRegionDrawable(new TextureRegion(new Texture(Gdx.files.internal("misc/transparent.png"))));
        final Touchpad touchpad = new Touchpad(0, touchpadStyle);
        touchpad.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                if (touchpad.getKnobPercentX() > 0) {
                    game.getTank2().setXVelocity(1);
                } else if (touchpad.getKnobPercentX() < 0) {
                    game.getTank2().setXVelocity(-1);
                } else {
                    game.getTank2().setXVelocity(0);
                }
            }
        });

        root.add(touchpad).expandX().colspan(4);
    }

    @Override
    public void render(float d) {
        //Log screen width and height
        Gdx.gl.glClearColor(0.9f, 0.9f, 0.9f, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        camera.update();
        viewport.update(width, height);
        game.batch.setProjectionMatrix(stage.getViewport().getCamera().combined);
        game.batch.begin();
        game.batch.draw(background, 0, 0, width, height);

        try {
            game.getTank1().draw(game.batch);
            game.getTank1().move();
        }
        catch (TankOutOfScreenException ex) {
            game.getTank1().setXVelocity(0);
        }

        try {
            game.getTank2().draw(game.batch);
            game.getTank2().move();
        }
        catch (TankOutOfScreenException ex) {
            game.getTank2().setXVelocity(0);
        }

        game.batch.end();

        renderer.setProjectionMatrix(stage.getViewport().getCamera().combined);

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
