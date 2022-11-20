package com.dragonjeet.tankstars;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.utils.Align;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.badlogic.gdx.scenes.scene2d.ui.Button;

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
        TextButton newGame = new TextButton("New Game", skin);

        newGame.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                game.setScreen(new MainScreen(game));
            }
        });

        table.add(newGame).expand().align(Align.center);

        table.row();

        TextButton resumeGame = new TextButton("Resume Game",skin);
        table.add(resumeGame).expand().align(Align.center);

        table.row();

        TextButton exitGame = new TextButton("Exit game", skin);
        table.add(resumeGame).expand().align(Align.center);
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
