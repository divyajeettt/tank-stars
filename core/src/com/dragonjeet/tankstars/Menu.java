package com.dragonjeet.tankstars;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.*;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.utils.Align;
import com.badlogic.gdx.utils.viewport.ScreenViewport;

class MainMenu implements Screen {
    final TankStars game;
    private Stage stage;
    private Table table;
    public MainMenu(final TankStars game) {
        this.game = game;
        stage = new Stage(new ScreenViewport());
        Gdx.input.setInputProcessor(stage);
        table = new Table();
        table.setFillParent(true);
        stage.addActor(table);

        Skin skin = new Skin(Gdx.files.internal("orangepeelui/uiskin.json"));

        ImageButton newGame = new ImageButton(
            new TextureRegionDrawable(new TextureRegion(new Texture(Gdx.files.internal("startUp.png")))),
            new TextureRegionDrawable(new TextureRegion(new Texture(Gdx.files.internal("startDown.png"))))
        );

        newGame.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                game.setScreen(new SelectionMenu(game));
                dispose();
            }
        });

        table.add(newGame).expand().align(Align.center);
        table.row();

        ImageButton loadGame = new ImageButton(
            new TextureRegionDrawable(new TextureRegion(new Texture(Gdx.files.internal("loadUp.png")))),
            new TextureRegionDrawable(new TextureRegion(new Texture(Gdx.files.internal("loadDown.png"))))
        );

        table.add(loadGame).expand().align(Align.center);
        table.row();

        ImageButton exitGame = new ImageButton(
            new TextureRegionDrawable(new TextureRegion(new Texture(Gdx.files.internal("exitUp.png")))),
            new TextureRegionDrawable(new TextureRegion(new Texture(Gdx.files.internal("exitDown.png"))))
        );

        table.add(exitGame).expand().align(Align.center);
        table.row();
    }

    @Override
    public void render(float d) {
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
        game.dispose();
        stage.dispose();
    }

}
class SelectionMenu implements Screen {
    final TankStars game;
    private Stage stage;
    private Table table;
    public SelectionMenu(final TankStars game) {
        this.game = game;
        Skin skin = new Skin(Gdx.files.internal("orangepeelui/uiskin.json"));
        stage = new Stage(new ScreenViewport());
        Gdx.input.setInputProcessor(stage);
        table = new Table();
        table.setFillParent(true);
        stage.addActor(table);

        table.add().grow();
        Table childTable = new Table();
        table.add(childTable).grow();

        Label choose = new Label("Choose Tank",skin);
        childTable.add(choose).colspan(3).grow().align(Align.center);

        ImageButton tank1 = new ImageButton(skin);
        ImageButton tank2 = new ImageButton(skin);
        ImageButton tank3 = new ImageButton(skin);
        childTable.row();
        childTable.add(tank1).expand();
        childTable.add(tank2).expand();
        childTable.add(tank3).expand();
        childTable.row();

        TextButton newGame = new TextButton("Start Game", skin);

        newGame.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                game.setScreen(new MainScreen(game));
                dispose();
            }
        });

        childTable.add(newGame).expand().align(Align.center).colspan(3);
    }

    @Override
    public void render(float d) {
        Gdx.gl.glClearColor(1,0,0,1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
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
        game.dispose();
        stage.dispose();
    }

}
