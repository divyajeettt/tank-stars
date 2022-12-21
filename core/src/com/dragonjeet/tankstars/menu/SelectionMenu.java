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
import com.dragonjeet.tankstars.misc.TankStars;
import com.dragonjeet.tankstars.misc.BattleScreen;
import com.dragonjeet.tankstars.tank.Tank1;
import com.dragonjeet.tankstars.tank.Tank2;
import com.dragonjeet.tankstars.tank.Tank3;

public class SelectionMenu extends Menu {
    private int player;                 // player=1 => Player-1's turn, player=0 => Player-2's turn; player ^= 1 after every iteration
    private TextureRegion playerText;
    private TextureRegion selectedTankImage;
    private TextureRegion selectedTankName;
    private Table table;
    private Table childTable;
    private Button tank1, tank2, tank3;
    private ImageButton actionButton;   // for player 1, this will be "Next" button, for player 2, this will be "Play" button

    public SelectionMenu(final TankStars game) {
        super(game);

        background = new Texture(Gdx.files.internal("backgrounds/selectionMenu.png"));
        player = 1;
        playerText = new TextureRegion(new Texture(Gdx.files.internal("text/player-1.png")));

        setTable();
        selectTank(1);  // default selected tank
        setActionButton();
        setTankButtons();
        placeObjects();
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

        game.batch.draw(
            playerText, 1370, 790, 0, 0,
            playerText.getRegionWidth(), playerText.getRegionHeight(), 1.4f, 1.4f, 0
        );


        game.batch.end();
        stage.act(d);
        stage.draw();
    }

    private void setTable() {
        this.table = new Table();
        table.setFillParent(true);
        stage.addActor(table);
        table.add().grow();
        table.add().grow();
        this.childTable = new Table();
        table.add(childTable).expandY();
    }

    private void selectTank(int num) {
        selectedTankImage = new TextureRegion(new Texture(Gdx.files.internal("tanks/tank-"+num+"/full.png")));
        selectedTankName = new TextureRegion(new Texture(Gdx.files.internal("tanks/tank-"+num+"/name.png")));
        if (num == 1) {
            game.setTank(new Tank1(0, game.getGround(), (player != 1)), player);
        } else if (num == 2) {
            game.setTank(new Tank2(0, game.getGround(), (player != 1)), player);
        } else {
            game.setTank(new Tank3(0, game.getGround(), (player != 1)), player);
        }
    }

    private TextureRegionDrawable getTankImage(int num) {
        return new TextureRegionDrawable(new TextureRegion(new Texture("tanks/tank-"+num+"/full.png")));
    }

    private void setTankButtons() {
        TextureRegionDrawable tankImage1 = getTankImage(1);
        this.tank1 = new Button(tankImage1, tankImage1);
        tank1.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                selectTank(1);
            }
        });

        TextureRegionDrawable tankImage2 = getTankImage(2);
        this.tank2 = new Button(tankImage2, tankImage2);
        tank2.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                selectTank(2);
            }
        });

        TextureRegionDrawable tankImage3 = getTankImage(3);
        this.tank3 = new Button(tankImage3, tankImage3);
        tank3.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                selectTank(3);
            }
        });
    }

    private void setActionButton() {
        this.actionButton = new ImageButton(
            new TextureRegionDrawable(new TextureRegion(new Texture(Gdx.files.internal("buttons/up/next.png")))),
            new TextureRegionDrawable(new TextureRegion(new Texture(Gdx.files.internal("buttons/down/next.png"))))
        );

        actionButton.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                if (player == 1) {
                    player ^= 1;
                    playerText = new TextureRegion(new Texture(Gdx.files.internal("text/player-2.png")));
                    actionButton.getStyle().imageUp = new TextureRegionDrawable(new TextureRegion(new Texture(Gdx.files.internal("buttons/up/play.png"))));
                    actionButton.getStyle().imageDown = new TextureRegionDrawable(new TextureRegion(new Texture(Gdx.files.internal("buttons/down/play.png"))));
                    selectTank(1);
                    setTankButtons();
                } else {
                    BattleScreen battleScreen = new BattleScreen(game);
                    game.setScreen(battleScreen);
                    dispose();
                }
            }
        });
    }

    private void placeObjects() {
        // Do something to fix the positioning of the tank buttons
        // I have added them to a table like before

        table.add(actionButton).expand().align(Align.bottom).padBottom(50);
        table.columnDefaults(0).width(Gdx.graphics.getWidth() / 3f);

        childTable.add(tank1).width(Value.percentWidth(1f, actionButton)).height(Value.percentHeight(1f, actionButton)).expandY().align(Align.bottom).padRight(20);
        childTable.add(tank2).width(Value.percentWidth(1f, actionButton)).height(Value.percentHeight(1f, actionButton)).expandY().align(Align.bottom);
        childTable.add(tank3).width(Value.percentWidth(1f, actionButton)).height(Value.percentHeight(1f, actionButton)).expandY().align(Align.bottom).padLeft(20);

        childTable.row();
        childTable.add(actionButton).expand().align(Align.bottom).colspan(3).padTop(160);

        setUi(table);
    }
}
