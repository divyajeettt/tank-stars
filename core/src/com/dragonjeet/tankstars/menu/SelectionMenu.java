package com.dragonjeet.tankstars.menu;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.Value;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.utils.Align;
import com.dragonjeet.tankstars.misc.BattleScreen;
import com.dragonjeet.tankstars.misc.TankStars;

public class SelectionMenu extends Menu {
    private TextureRegion selectedTankImage;
    private TextureRegion selectedTankName;

    public SelectionMenu(final TankStars game) {
        super(game);

        background = new Texture(Gdx.files.internal("backgrounds/selectionMenu.png"));
        selectedTankImage = new TextureRegion(new Texture(Gdx.files.internal("tanks/tank-1/full.png")));
        selectedTankName = new TextureRegion(new Texture(Gdx.files.internal("tanks/tank-1/name.png")));

        game.setTank1( new com.dragonjeet.tankstars.tank.Tank1(100,game.getGround(),false));
        game.setTank2( new com.dragonjeet.tankstars.tank.Tank1(600,game.getGround(),true));

        Table table = new Table();
        table.setFillParent(true);
        stage.addActor(table);

        table.add().grow();
        table.add().grow();
        Table childTable = new Table();
        table.add(childTable).expandY();

        // Do something to fix the positioning of the tank buttons
        // I have added them to a table like before

        ImageButton playButton = new ImageButton(
            new TextureRegionDrawable(new TextureRegion(new Texture(Gdx.files.internal("buttons/up/play.png")))),
            new TextureRegionDrawable(new TextureRegion(new Texture(Gdx.files.internal("buttons/down/play.png"))))
        );

        playButton.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                BattleScreen battleScreen = new BattleScreen(game);
                game.setScreen(battleScreen);
                dispose();
            }
        });

        TextureRegionDrawable tankImage1 = new TextureRegionDrawable(new TextureRegion(new Texture("tanks/tank-1/full.png")));
        Button tank1 = new Button(tankImage1, tankImage1);
        tank1.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                selectedTankImage = new TextureRegion(new Texture("tanks/tank-1/full.png"));
                selectedTankName = new TextureRegion(new Texture(Gdx.files.internal("tanks/tank-1/name.png")));
                game.setTank1( new com.dragonjeet.tankstars.tank.Tank1(100,game.getGround(),false));
                game.setTank2( new com.dragonjeet.tankstars.tank.Tank1(100,game.getGround(),true));
            }
        });

        TextureRegionDrawable tankImage2 = new TextureRegionDrawable(new TextureRegion(new Texture("tanks/tank-2/full.png")));
        Button tank2 = new Button(tankImage2, tankImage2);
        tank2.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                selectedTankImage = new TextureRegion(new Texture("tanks/tank-2/full.png"));
                selectedTankName = new TextureRegion(new Texture(Gdx.files.internal("tanks/tank-2/name.png")));
                game.setTank1( new com.dragonjeet.tankstars.tank.Tank2(100,0,50,30,game.getGround(),false));
                game.setTank2( new com.dragonjeet.tankstars.tank.Tank2(100,0,50,30,game.getGround(),true));
            }
        });

        TextureRegionDrawable tankImage3 = new TextureRegionDrawable(new TextureRegion(new Texture("tanks/tank-3/full.png")));
        Button tank3 = new Button(tankImage3, tankImage3);
        tank3.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                selectedTankImage = new TextureRegion(new Texture("tanks/tank-3/full.png"));
                selectedTankName = new TextureRegion(new Texture(Gdx.files.internal("tanks/tank-3/name.png")));
                game.setTank1( new com.dragonjeet.tankstars.tank.Tank3(100,0,50,30,game.getGround(),false));
                game.setTank2( new com.dragonjeet.tankstars.tank.Tank3(100,0,50,30,game.getGround(),true));
            }
        });

        table.columnDefaults(0).width(Gdx.graphics.getWidth() / 3f);

        childTable.add(tank1).width(Value.percentWidth(1f, playButton)).height(Value.percentHeight(1f, playButton)).expandY().align(Align.bottom).padRight(20);
        childTable.add(tank2).width(Value.percentWidth(1f, playButton)).height(Value.percentHeight(1f, playButton)).expandY().align(Align.bottom);
        childTable.add(tank3).width(Value.percentWidth(1f, playButton)).height(Value.percentHeight(1f, playButton)).expandY().align(Align.bottom).padLeft(20);

        childTable.row();
        childTable.add(playButton).expand().align(Align.bottom).colspan(3).padTop(160);

        setUi(table);
    }

    public void drawSelectedTank(SpriteBatch batch, TextureRegion tankImage, TextureRegion tankName) {
        batch.draw(
            tankImage, 150, 180, 0, 0,
            tankImage.getRegionWidth(), tankImage.getRegionHeight(), 2, 2, 0
        );

        batch.draw(
            tankName, 250, 700, 0, 0,
            tankName.getRegionWidth(), tankName.getRegionHeight(), 1.4f, 1.4f, 0
        );
    }

    @Override
    public void render(float d) {
        game.batch.begin();
        game.batch.draw(background, 0, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        drawSelectedTank(game.batch, selectedTankImage, selectedTankName);
        game.batch.end();
        stage.act(d);
        stage.draw();
    }
}
