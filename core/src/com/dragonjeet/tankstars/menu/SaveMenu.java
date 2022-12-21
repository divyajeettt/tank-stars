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
import com.dragonjeet.tankstars.misc.GameState;
import com.dragonjeet.tankstars.misc.TankStars;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;

public class SaveMenu extends Menu {
    private Table table;
    private Button slot1, slot2, slot3, slot4;

    public SaveMenu(final TankStars game) {
        super(game);
        table = new Table();
        table.add(new Image(new Texture(Gdx.files.internal("text/save.png")))).align(Align.center);
        table.row();

        addSlots();

        Button goBack = new Button(
            new TextureRegionDrawable(new TextureRegion(new Texture(Gdx.files.internal("buttons/up/goBack.png")))),
            new TextureRegionDrawable(new TextureRegion(new Texture(Gdx.files.internal("buttons/down/goBack.png"))))
        );

        goBack.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                game.setScreen(new PauseMenu(game));
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

    private ImageButton makeOccupiedSlot() {
        return new ImageButton(
            new TextureRegionDrawable(new TextureRegion(new Texture(Gdx.files.internal("buttons/up/slotOccupied.png")))),
            new TextureRegionDrawable(new TextureRegion(new Texture(Gdx.files.internal("buttons/down/slotOccupied.png"))))
        );
    }

    private void addExistingSlot(int num) {
        if (num == 1)
            slot1 = makeOccupiedSlot();
        else if (num == 2)
            slot2 = makeOccupiedSlot();
        else if (num == 3)
            slot3 = makeOccupiedSlot();
        else
            slot4 = makeOccupiedSlot();
    }

    private void addEmptySlot(final int num) {
        final Button button = new ImageButton(
            new TextureRegionDrawable(new TextureRegion(new Texture(Gdx.files.internal("buttons/up/emptySlot.png")))),
            new TextureRegionDrawable(new TextureRegion(new Texture(Gdx.files.internal("buttons/down/emptySlot.png"))))
        );

        button.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                // save game on that slot
                ObjectOutputStream out;
                try {
                    out = new ObjectOutputStream(Files.newOutputStream(Paths.get("game-" + num + ".dat")));
                    out.writeObject(new GameState(game));
                    out.close();
                }
                catch (IOException e) {
                    Gdx.app.log("TankStars", e.getMessage());
                }

                // change the button to occupied
                if (num == 1) {
                    slot1 = makeOccupiedSlot();
                    button.remove();
                    table.add(slot1).growX().pad(10);
                } else if (num == 2) {
                    slot2 = makeOccupiedSlot();
                    button.remove();
                    table.add(slot2).growX().pad(10);
                } else if (num == 3) {
                    slot3 = makeOccupiedSlot();
                    button.remove();
                    table.add(slot3).growX().pad(10);
                } else {
                    slot4 = makeOccupiedSlot();
                    button.remove();
                    table.add(slot4).growX().pad(10);
                }

                game.setScreen(new PauseMenu(game));
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