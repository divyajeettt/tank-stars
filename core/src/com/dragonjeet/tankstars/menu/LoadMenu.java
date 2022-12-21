package com.dragonjeet.tankstars.menu;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.utils.Align;
import com.dragonjeet.tankstars.misc.BattleScreen;
import com.dragonjeet.tankstars.misc.GameState;
import com.dragonjeet.tankstars.misc.TankStars;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;

public class LoadMenu extends Menu {
    private Table table;
    private Button slot1, slot2, slot3, slot4;

    public LoadMenu(final TankStars game) {
        super(game);

        background = new Texture(Gdx.files.internal("backgrounds/load.png"));

        table = new Table();
        table.add(new Image(new Texture(Gdx.files.internal("text/load.png")))).align(Align.center);
        table.row();

        addSlots();

        Button goBack = new Button(
            new TextureRegionDrawable(new TextureRegion(new Texture(Gdx.files.internal("buttons/up/goBack.png")))),
            new TextureRegionDrawable(new TextureRegion(new Texture(Gdx.files.internal("buttons/down/goBack.png"))))
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

    private void addSlots() {
        for (int i=0; i < 4; i++) {
            File f = new File("game-" + (i+1) + ".dat");
            if (f.exists()) {
                addExistingSlot(i + 1);
            } else {
                addEmptySlot(i + 1);
            }
        }

        Button[] slots = {slot1, slot2, slot3, slot4};
        for (Button slot: slots) {
            table.add(slot).growX().pad(10);
            table.row();
        }
    }

    private ImageButton makeEmptySlot() {
        return new ImageButton(
            new TextureRegionDrawable(new TextureRegion(new Texture(Gdx.files.internal("buttons/up/emptySlot.png")))),
            new TextureRegionDrawable(new TextureRegion(new Texture(Gdx.files.internal("buttons/down/emptySlot.png"))))
        );
    }

    private void addEmptySlot(int num) {
        if (num == 1)
            slot1 = makeEmptySlot();
        else if (num == 2)
            slot2 = makeEmptySlot();
        else if (num == 3)
            slot3 = makeEmptySlot();
        else
            slot4 = makeEmptySlot();
    }

    private void addExistingSlot(final int num) {
        final Button button = new ImageButton(
            new TextureRegionDrawable(new TextureRegion(new Texture(Gdx.files.internal("buttons/up/loadSlot"+num+".png")))),
            new TextureRegionDrawable(new TextureRegion(new Texture(Gdx.files.internal("buttons/down/loadSlot"+num+".png"))))
        );

        button.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                ObjectInputStream in;
                GameState loadedGame;
                try {
                    in = new ObjectInputStream(Files.newInputStream(Paths.get("game-" + num + ".dat")));
                    loadedGame = (GameState) in.readObject();
                    in.close();
                } catch (IOException | ClassNotFoundException e) {
                    Gdx.app.log("TankStars", e.getMessage());
                    return;
                }
                game.setGame(loadedGame);
                game.setScreen(new BattleScreen(game));
                dispose();
            }
        });

        if (num == 1)
            slot1 = button;
        else if (num == 2)
            slot2 = button;
        else if (num == 3)
            slot3 = button;
        else
            slot4 = button;
    }
}
