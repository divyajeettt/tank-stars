package com.dragonjeet.tankstars;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.*;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.utils.Align;
import com.badlogic.gdx.utils.viewport.ScreenViewport;


public class Menu implements Screen {
    final TankStars game;
    protected Stage stage;
    protected Texture background;
    protected Skin skin;

    public Menu(final TankStars game) {
        skin = new Skin(Gdx.files.internal("orangepeelui/uiskin.json"));
        this.game = game;
        background = new Texture("background.png");
        stage = new Stage(new ScreenViewport());
        Gdx.input.setInputProcessor(stage);
    }

    void setUi(Table ui) {
        ui.setFillParent(true);
        stage.addActor(ui);
    }

    @Override
    public void render(float d) {
        game.batch.begin();
        game.batch.draw(background, 0,0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        game.batch.end();
        stage.act(d);
        stage.draw();
    }

    @Override
    public void show() {}

    @Override
    public void resize (int width,int height) {}

    @Override
    public void pause() {}
    @Override
    public void resume() {}

    @Override
    public void hide() {}

    @Override
    public void dispose() {
        stage.dispose();
        background.dispose();
    }
}


class MainMenu extends Menu {
    public MainMenu(final TankStars game) {
        super(game);
        Table table = new Table();

        // Adding image to fill the background
        Texture backgroundTexture = new Texture(Gdx.files.internal("homescreen.png"));
        TextureRegion backgroundTextureRegion = new TextureRegion(backgroundTexture);
        TextureRegionDrawable backgroundTextureRegionDrawable = new TextureRegionDrawable(backgroundTextureRegion);
        Image background = new Image(backgroundTextureRegionDrawable);
        background.setFillParent(true);
        background.setZIndex(0);
        stage.addActor(background);

        ImageButton newGame = new ImageButton(
            new TextureRegionDrawable(new TextureRegion(new Texture(Gdx.files.internal("startUp.png")))),
            new TextureRegionDrawable(new TextureRegion(new Texture(Gdx.files.internal("startDown.png"))))
        );
        newGame.setPosition(
            Gdx.graphics.getWidth()/2f - newGame.getWidth()/2,
            Gdx.graphics.getHeight()/2f
        );
        stage.addActor(newGame);

        newGame.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                game.setScreen(new SelectionMenu(game));
                dispose();
            }
        });

        ImageButton loadGame = new ImageButton(
            new TextureRegionDrawable(new TextureRegion(new Texture(Gdx.files.internal("loadUp.png")))),
            new TextureRegionDrawable(new TextureRegion(new Texture(Gdx.files.internal("loadDown.png"))))
        );
        loadGame.setPosition(
            Gdx.graphics.getWidth()/2f - loadGame.getWidth()/2,
            Gdx.graphics.getHeight()/2f - 2*loadGame.getHeight()
        );

        loadGame.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                game.setScreen(new LoadMenu(game));
                dispose();
            }
        });

        stage.addActor(loadGame);

        ImageButton exitGame = new ImageButton(
            new TextureRegionDrawable(new TextureRegion(new Texture(Gdx.files.internal("exitUp.png")))),
            new TextureRegionDrawable(new TextureRegion(new Texture(Gdx.files.internal("exitDown.png"))))
        );
        exitGame.setPosition(
            Gdx.graphics.getWidth()/2f - exitGame.getWidth()/2,
            Gdx.graphics.getHeight()/2f - 4*exitGame.getHeight()
        );
        stage.addActor(exitGame);

        exitGame.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                Gdx.app.exit();
            }
        });
    }
}


class SelectionMenu extends Menu {
    private TextureRegion selectedTankImage;

    public SelectionMenu(final TankStars game) {
        super(game);

        background = new Texture(Gdx.files.internal("selectionMenu.png"));

        selectedTankImage = new TextureRegion(new Texture(Gdx.files.internal("tank-1.png")));
        game.getTank1().setImage(new TextureRegion(selectedTankImage), false);
        game.getTank2().setImage(new TextureRegion(selectedTankImage), true);

        Table table = new Table();
        table.setFillParent(true);
        stage.addActor(table);

        table.add().grow();
        Table childTable = new Table();
        table.add(childTable).grow();

        // Do something to fix the positioning of the tank buttons
        // I have added them to a table like before

        ImageButton playButton = new ImageButton(
            new TextureRegionDrawable(new TextureRegion(new Texture(Gdx.files.internal("playUp.png")))),
            new TextureRegionDrawable(new TextureRegion(new Texture(Gdx.files.internal("playDown.png"))))
        );

        playButton.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                MainScreen mainScreen = new MainScreen(game);
                game.setScreen(mainScreen);
                dispose();
            }
        });

        TextureRegionDrawable tankImage1 = new TextureRegionDrawable(new TextureRegion(new Texture("tank-1.png")));
        Button tank1 = new Button(tankImage1, tankImage1);
        tank1.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                selectedTankImage = new TextureRegion(new Texture("tank-1.png"));
                game.getTank1().setImage(new TextureRegion(selectedTankImage), false);
                game.getTank2().setImage(new TextureRegion(selectedTankImage), true);
            }
        });

        TextureRegionDrawable tankImage2 = new TextureRegionDrawable(new TextureRegion(new Texture("tank-2.png")));
        Button tank2 = new Button(tankImage2, tankImage2);
        tank2.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                selectedTankImage = new TextureRegion(new Texture("tank-2.png"));
                game.getTank1().setImage(new TextureRegion(selectedTankImage), false);
                game.getTank2().setImage(new TextureRegion(selectedTankImage), true);
            }
        });

        TextureRegionDrawable tankImage3 = new TextureRegionDrawable(new TextureRegion(new Texture("tank-3.png")));
        Button tank3 = new Button(tankImage3, tankImage3);
        tank3.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                selectedTankImage = new TextureRegion(new Texture("tank-3.png"));
                game.getTank1().setImage(new TextureRegion(selectedTankImage), false);
                game.getTank2().setImage(new TextureRegion(selectedTankImage), true);
            }
        });

        Button invisibleButton = new Button(skin);
        invisibleButton.setColor(0.5f, 0.5f, 0.5f, 0f);
        for (int i=0; i < 10; i++) {
            table.row();
            table.add(invisibleButton);
        }

        table.columnDefaults(0).width(Gdx.graphics.getWidth()/3f);

        table.add(tank1).width(Value.percentWidth(1f, playButton)).height(Value.percentHeight(1f, playButton));
        table.add(tank2).width(Value.percentWidth(1f, playButton)).height(Value.percentHeight(1f, playButton));
        table.add(tank3).width(Value.percentWidth(1f, playButton)).height(Value.percentHeight(1f, playButton));

        table.row();
        table.add(playButton).expand().align(Align.center).colspan(3);

        setUi(table);
    }

    public void drawSelectedTank(SpriteBatch batch, TextureRegion tankImage) {
        batch.draw(
            tankImage, 150, 180, 0, 0,
            tankImage.getRegionWidth(), tankImage.getRegionHeight(), 2, 2, 0
        );
    }

    @Override
    public void render(float d) {
        game.batch.begin();
        game.batch.draw(background, 0,0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        drawSelectedTank(game.batch, selectedTankImage);
        game.batch.end();
        stage.act(d);
        stage.draw();
    }
}


class PauseMenu extends Menu {
    public PauseMenu(final TankStars game) {
        super(game);
        Table table = new Table();
        table.setFillParent(true);
        stage.addActor(table);

        ImageButton resume = new ImageButton(
            new TextureRegionDrawable(new TextureRegion(new Texture(Gdx.files.internal("resumeUp.png")))),
            new TextureRegionDrawable(new TextureRegion(new Texture(Gdx.files.internal("resumeDown.png"))))
        );
        resume.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                game.setScreen(new MainScreen(game));
                dispose();
            }
        });

        ImageButton save = new ImageButton(
            new TextureRegionDrawable(new TextureRegion(new Texture(Gdx.files.internal("saveUp.png")))),
            new TextureRegionDrawable(new TextureRegion(new Texture(Gdx.files.internal("saveDown.png"))))
        );

        save.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                game.setScreen(new SaveMenu(game));
                dispose();
            }
        });

        ImageButton returnHome = new ImageButton(
            new TextureRegionDrawable(new TextureRegion(new Texture(Gdx.files.internal("returnHomeUp.png")))),
            new TextureRegionDrawable(new TextureRegion(new Texture(Gdx.files.internal("returnHomeDown.png"))))
        );

        returnHome.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                game.setScreen(new MainMenu(game));
                dispose();
            }
        });

        table.row();
        table.add(resume).pad(20);
        table.row();
        table.add(save).pad(20);
        table.row();
        table.add(returnHome).pad(20);
        setUi(table);
    }
}


class LoadMenu extends Menu {

    LoadMenu(final TankStars game) {
        super(game);
        Table table = new Table();
        table.add(new Image(new Texture(Gdx.files.internal("loadGame.png")))).align(Align.center);
        table.row();

        // Hard Coding 4 buttons for now
        // This list will need to show the filled slots later
        for (int i=0; i < 4; i++) {
            table.add(new ImageButton(
                new TextureRegionDrawable(new TextureRegion(new Texture(Gdx.files.internal("emptySlotUp.png")))),
                new TextureRegionDrawable(new TextureRegion(new Texture(Gdx.files.internal("emptySlotDown.png"))))
            )).growX().pad(10);
            table.row();
        }

        Button goBack = new Button(
            new TextureRegionDrawable(new TextureRegion(new Texture(Gdx.files.internal("goBackUp.png")))),
            new TextureRegionDrawable(new TextureRegion(new Texture(Gdx.files.internal("goBackDown.png"))))
        );

        goBack.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                game.setScreen(new MainMenu(game));
                dispose();
            }
        });

        table.add(goBack).pad(50);
        setUi(table);
    }
}


class SaveMenu extends Menu {
    SaveMenu(final TankStars game) {
        super(game);
        Table table = new Table();
        table.add(new Image(new Texture(Gdx.files.internal("saveGame.png")))).align(Align.center);
        table.row();

        // Hard Coding 4 buttons for now
        // This will need to be changed into a manual list of 4 buttons with the dates & times of save // "name" of saved game
        for (int i=0; i < 4; i++) {
            table.add(new ImageButton(
                new TextureRegionDrawable(new TextureRegion(new Texture(Gdx.files.internal("emptySlotUp.png")))),
                new TextureRegionDrawable(new TextureRegion(new Texture(Gdx.files.internal("emptySlotDown.png"))))
            )).growX().pad(10);
            table.row();
        }

        Button goBack = new Button(
            new TextureRegionDrawable(new TextureRegion(new Texture(Gdx.files.internal("goBackUp.png")))),
            new TextureRegionDrawable(new TextureRegion(new Texture(Gdx.files.internal("goBackDown.png"))))
        );

        goBack.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                game.setScreen(new PauseMenu(game));
                dispose();
            }
        });

        table.add(goBack).pad(50);
        setUi(table);
    }
}
