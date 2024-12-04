package com.paradigmas.game.ui;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton.TextButtonStyle;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.paradigmas.game.Main;
import com.paradigmas.game.utils.LoadAssets;
import lombok.Getter;

import static com.paradigmas.game.utils.FontType.*;

public class Button {
    @Getter private final TextButton button;
    private final Skin skin;
    private final TextureAtlas buttonAtlas;

    public Button(Main game, String buttonText, float posX, float posY, float buttonWidth, float buttonHeight, ButtonAction action) {
        Stage stage = new Stage(game.getViewport());
        Gdx.input.setInputProcessor(stage);

        skin = LoadAssets.loadSkin("ui/uiskin.json");
        buttonAtlas = LoadAssets.loadAtlas("ui/uiskin.atlas");
        skin.addRegions(buttonAtlas);

        TextButtonStyle textButtonStyle = new TextButtonStyle();
        textButtonStyle.font = game.getFontHashMap().get(BUTTON);
        textButtonStyle.up = skin.getDrawable("default-rect");  // Padrão da skin
        textButtonStyle.down = skin.getDrawable("default-rect");  // Padrão da skin
        textButtonStyle.checked = skin.getDrawable("default-rect");  // Padrão da skin

        button = new TextButton(buttonText, textButtonStyle);

        button.setPosition(posX, posY);
        button.setSize(buttonWidth, buttonHeight);

        button.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                action.execute();
            }
        });

        stage.addActor(button);
    }

    public void dispose() {
        skin.dispose();
        buttonAtlas.dispose();
    }
}
