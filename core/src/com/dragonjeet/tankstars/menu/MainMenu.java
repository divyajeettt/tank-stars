package com.dragonjeet.tankstars.menu;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.dragonjeet.tankstars.misc.TankStars;

public class MainMenu extends Menu {
    public MainMenu(final TankStars game) {
        super(game);
//        Table table = new Table();

        // Adding image to fill the background
        Texture backgroundTexture = new Texture(Gdx.files.internal("backgrounds/home.png"));
        TextureRegion backgroundTextureRegion = new TextureRegion(backgroundTexture);
        TextureRegionDrawable backgroundTextureRegionDrawable = new TextureRegionDrawable(backgroundTextureRegion);
        Image background = new Image(backgroundTextureRegionDrawable);
        background.setFillParent(true);
        background.setZIndex(0);
        stage.addActor(background);

        ImageButton newGame = new ImageButton(
            new TextureRegionDrawable(new TextureRegion(new Texture(Gdx.files.internal("buttons/up/start.png")))),
            new TextureRegionDrawable(new TextureRegion(new Texture(Gdx.files.internal("buttons/down/start.png"))))
        );
        newGame.setPosition(
            Gdx.graphics.getWidth() / 2f - newGame.getWidth() / 2,
            Gdx.graphics.getHeight() / 2f + newGame.getHeight() / 2
        );
        stage.addActor(newGame);

        newGame.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                game.setNewGround();
                game.setScreen(new SelectionMenu(game));
                dispose();
            }
        });

        ImageButton loadGame = new ImageButton(
            new TextureRegionDrawable(new TextureRegion(new Texture(Gdx.files.internal("buttons/up/load.png")))),
            new TextureRegionDrawable(new TextureRegion(new Texture(Gdx.files.internal("buttons/down/load.png"))))
        );
        loadGame.setPosition(
            Gdx.graphics.getWidth() / 2f - loadGame.getWidth() / 2,
            Gdx.graphics.getHeight() / 2f - 1.1f*loadGame.getHeight()
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
            new TextureRegionDrawable(new TextureRegion(new Texture(Gdx.files.internal("buttons/up/exit.png")))),
            new TextureRegionDrawable(new TextureRegion(new Texture(Gdx.files.internal("buttons/down/exit.png"))))
        );
        exitGame.setPosition(
            Gdx.graphics.getWidth() / 2f - exitGame.getWidth() / 2,
            Gdx.graphics.getHeight() / 2f - 2.71f * exitGame.getHeight()
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