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

import java.util.concurrent.TransferQueue;

public class SelectionMenu extends Menu {
    private TextureRegion selectedTankImage;
    private TextureRegion selectedTankName;

    public SelectionMenu(final TankStars game) {
        super(game);

        background = new Texture(Gdx.files.internal("selectionMenu.png"));

        selectedTankImage = new TextureRegion(new Texture(Gdx.files.internal("tank-1.png")));
        game.getTank1().setImage(new TextureRegion(selectedTankImage));
        game.getTank2().setImage(new TextureRegion(selectedTankImage));
        selectedTankName = new TextureRegion(new Texture(Gdx.files.internal("Tiger.png")));

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

        Button invisibleButton = new Button(skin);
        invisibleButton.setColor(0.5f, 0.5f, 0.5f, 0f);
        for (int i = 0; i < 10; i++) {
            table.row();
            table.add(invisibleButton);
        }

        table.columnDefaults(0).width(Gdx.graphics.getWidth() / 3f);

        table.add(tank1).width(Value.percentWidth(1f, playButton)).height(Value.percentHeight(1f, playButton)).align(Align.right);
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
        game.batch.draw(background, 0, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        drawSelectedTank(game.batch, selectedTankImage);
        game.batch.end();
        stage.act(d);
        stage.draw();
    }
}
