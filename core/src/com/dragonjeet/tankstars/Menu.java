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
        TextButton newGame = new TextButton("New Game", skin);

        newGame.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                game.setScreen(new SelectionMenu(game));
                dispose();
            }
        });

        table.add(newGame).expand().align(Align.center);

        table.row();

        TextButton resumeGame = new TextButton("Resume Game",skin);
        table.add(resumeGame).expand().align(Align.center);

        table.row();

        TextButton exitGame = new TextButton("Exit game", skin);
        table.add(exitGame).expand().align(Align.center);
        setUi(table);
    }
}

class SelectionMenu extends Menu {
    public SelectionMenu(final TankStars game) {
        super(game);
        Table table = new Table();
        table.setFillParent(true);
        stage.addActor(table);

        table.add().grow();
        Table childTable = new Table();
        table.add(childTable).grow();

        Label choose = new Label("Choose Tank",skin);
        childTable.add(choose).colspan(3).expand().align(Align.center);

        ImageButton tank1 = new ImageButton(skin);
        ImageButton tank2 = new ImageButton(skin);
        ImageButton tank3 = new ImageButton(skin);
        childTable.row();
        childTable.add(tank1).expand().align(Align.right);
        childTable.add(tank2).expand();
        childTable.add(tank3).expand().align(Align.left);
        childTable.row();

        TextureRegionDrawable newGameDrawable = new TextureRegionDrawable(new TextureRegion(new Texture("start.png")));
        Button newGame = new Button(newGameDrawable,newGameDrawable);

        newGame.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                game.setScreen(new MainScreen(game));
                dispose();
            }
        });

        childTable.add(newGame).expand().align(Align.center).colspan(3);
        setUi(table);
    }
}

class PauseMenu extends Menu {
    public PauseMenu(final TankStars game) {
        super(game);
        Table table = new Table();
        table.setFillParent(true);
        stage.addActor(table);

        TextureRegionDrawable resumeDrawable = new TextureRegionDrawable(new TextureRegion(new Texture("resume.png")));
        Button resume = new Button(resumeDrawable,resumeDrawable);

        resume.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                game.setScreen(new MainScreen(game));
                dispose();
            }
        });

        TextureRegionDrawable saveDrawable = new TextureRegionDrawable(new TextureRegion(new Texture("save.png")));
        Button save = new Button(saveDrawable,saveDrawable);

        TextureRegionDrawable exitDrawable = new TextureRegionDrawable(new TextureRegion(new Texture("exit.png")));
        Button exit = new Button(exitDrawable,exitDrawable);
        exit.addListener(new ChangeListener() {
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
        table.add(exit).pad(20);
        setUi(table);
    }
}