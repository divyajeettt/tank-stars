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
import com.dragonjeet.tankstars.misc.MainScreen;
import com.dragonjeet.tankstars.misc.TankStars;

public class SelectionMenu extends Menu {
    private TextureRegion selectedTankImage;
    private TextureRegion selectedTankName;

    public SelectionMenu(final TankStars game) {
        super(game);

        background = new Texture(Gdx.files.internal("selectionMenu.png"));

        selectedTankImage = new TextureRegion(new Texture(Gdx.files.internal("tank-1.png")));
        selectedTankName = new TextureRegion(new Texture(Gdx.files.internal("Tiger.png")));
        game.getTank1().setImage(new TextureRegion(selectedTankImage));
        game.getTank2().setImage(new TextureRegion(selectedTankImage));

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
                selectedTankName = new TextureRegion(new Texture(Gdx.files.internal("Tiger.png")));
                game.getTank1().setImage(new TextureRegion(selectedTankImage));
                game.getTank2().setImage(new TextureRegion(selectedTankImage));
            }
        });

        TextureRegionDrawable tankImage2 = new TextureRegionDrawable(new TextureRegion(new Texture("tank-2.png")));
        Button tank2 = new Button(tankImage2, tankImage2);
        tank2.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                selectedTankImage = new TextureRegion(new Texture("tank-2.png"));
                selectedTankName = new TextureRegion(new Texture(Gdx.files.internal("Abrams.png")));
                game.getTank1().setImage(new TextureRegion(selectedTankImage));
                game.getTank2().setImage(new TextureRegion(selectedTankImage));
            }
        });

        TextureRegionDrawable tankImage3 = new TextureRegionDrawable(new TextureRegion(new Texture("tank-3.png")));
        Button tank3 = new Button(tankImage3, tankImage3);
        tank3.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                selectedTankImage = new TextureRegion(new Texture("tank-3.png"));
                selectedTankName = new TextureRegion(new Texture(Gdx.files.internal("Buratino.png")));
                game.getTank1().setImage(new TextureRegion(selectedTankImage));
                game.getTank2().setImage(new TextureRegion(selectedTankImage));
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
