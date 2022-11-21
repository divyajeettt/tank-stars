package com.dragonjeet.tankstars;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Texture;
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
    public void show() {

    }

    @Override
    public void resize (int width,int height) {
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
                game.setScreen(new SavedMenu(game));
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
    public SelectionMenu(final TankStars game) {
        super(game);

        background = new Texture(Gdx.files.internal("selectionMenu.png"));

        Table table = new Table();
        table.setFillParent(true);
        stage.addActor(table);
        table.add().grow();
        Table childTable = new Table();
        table.add(childTable).grow();

        Label choose = new Label("Choose Tank", skin);
        childTable.add(choose).colspan(3).expand().align(Align.center);
        // Do Something to remove the label and fix the positioning of the tank buttons

        TextureRegionDrawable tankImage1 = new TextureRegionDrawable(new TextureRegion(new Texture("tank-1.png")));
        TextureRegionDrawable tankImage2 = new TextureRegionDrawable(new TextureRegion(new Texture("tank-2.png")));
        TextureRegionDrawable tankImage3 = new TextureRegionDrawable(new TextureRegion(new Texture("tank-3.png")));
        Button tank1 = new Button(tankImage1, tankImage1);
        Button tank2 = new Button(tankImage2, tankImage2);
        Button tank3 = new Button(tankImage3, tankImage3);

        childTable.row();
        childTable.add(tank1).expand().align(Align.right);
        childTable.add(tank2).expand();
        childTable.add(tank3).expand().align(Align.left);
        childTable.row();

        ImageButton playButton = new ImageButton(
            new TextureRegionDrawable(new TextureRegion(new Texture(Gdx.files.internal("playUp.png")))),
            new TextureRegionDrawable(new TextureRegion(new Texture(Gdx.files.internal("playDown.png"))))
        );

        playButton.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                game.setScreen(new MainScreen(game));
                dispose();
            }
        });

        childTable.add(playButton).expand().align(Align.center).colspan(3);
        setUi(table);
    }

    @Override
    public void render(float d) {
        game.batch.begin();
        game.batch.draw(background, 0,0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
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
                // MAKE A CHANGE HERE: don't create a new mainscreen, but resume the previous one
                // This can possibly be done by overloading the constructor wherein you can pass the current "Ground"
                // as a parameter
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
                game.setScreen(new LoadMenu(game));
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

class SavedMenu extends Menu {

    SavedMenu(final TankStars game) {
        super(game);
        Table table = new Table();
        table.add(new Label("List of saved games",skin)).align(Align.center);
        table.row();
        table.add(new TextButton("Empty slot",skin)).growX().pad(10);
        table.row();
        table.add(new TextButton("Empty slot",skin)).growX().pad(10);
        table.row();
        table.add(new TextButton("Empty slot",skin)).growX().pad(10);
        table.row();
        table.add(new TextButton("Empty slot",skin)).growX().pad(10);
        table.row();

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

class LoadMenu extends Menu {

    LoadMenu(final TankStars game) {
        super(game);
        Table table = new Table();
        table.add(new Label("Save your game",skin)).align(Align.center);
        table.row();
        table.add(new TextButton("Empty slot",skin)).growX().pad(10);
        table.row();
        table.add(new TextButton("Empty slot",skin)).growX().pad(10);
        table.row();
        table.add(new TextButton("Empty slot",skin)).growX().pad(10);
        table.row();
        table.add(new TextButton("Empty slot",skin)).growX().pad(10);
        table.row();

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
