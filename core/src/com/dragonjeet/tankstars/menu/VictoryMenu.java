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
import com.badlogic.gdx.utils.Align;
import com.dragonjeet.tankstars.misc.BattleScreen;
import com.dragonjeet.tankstars.misc.TankStars;

public class VictoryMenu extends Menu {
    public VictoryMenu(final TankStars game) {
        super(game);
        int winner = (game.getCurrentTank()==game.getTank1() ? 2 : 1);

        Table table = new Table();
        table.setFillParent(true);
        stage.addActor(table);

        table.add(new Image(new Texture(Gdx.files.internal("text/winner"+winner+".png")))).align(Align.center);
        table.row();

        ImageButton playAgain = new ImageButton(
            new TextureRegionDrawable(new TextureRegion(new Texture(Gdx.files.internal("buttons/up/playAgain.png")))),
            new TextureRegionDrawable(new TextureRegion(new Texture(Gdx.files.internal("buttons/down/playAgain.png"))))
        );
        playAgain.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                game.setScreen(new MainMenu(game));
                dispose();
            }
        });

        ImageButton returnHome = new ImageButton(
                new TextureRegionDrawable(new TextureRegion(new Texture(Gdx.files.internal("buttons/up/returnHome.png")))),
                new TextureRegionDrawable(new TextureRegion(new Texture(Gdx.files.internal("buttons/down/returnHome.png"))))
        );

        returnHome.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                game.setScreen(new MainMenu(game));
                dispose();
            }
        });

        ImageButton exitGame = new ImageButton(
                new TextureRegionDrawable(new TextureRegion(new Texture(Gdx.files.internal("buttons/up/exit.png")))),
                new TextureRegionDrawable(new TextureRegion(new Texture(Gdx.files.internal("buttons/down/exit.png"))))
        );

        exitGame.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                Gdx.app.exit();
            }
        });

        table.row();
        table.add(playAgain).pad(20);
        table.row();
        table.add(returnHome).pad(20);
        table.row();
        table.add(exitGame).pad(20);
        setUi(table);
    }
}