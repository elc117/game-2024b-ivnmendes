package com.paradigmas.game.ui;

import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton.TextButtonStyle;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.paradigmas.game.Main;
import com.paradigmas.game.utils.LoadAssets;

import static com.paradigmas.game.utils.FontType.*;

public class Button {
    private final TextButton button;
    private final Skin skin;
    private final TextureAtlas buttonAtlas;

    public Button(Main game, String buttonText, float posX, float posY, float buttonWidth, float buttonHeight, ButtonAction action) {
        skin = LoadAssets.loadSkin("ui/uiskin.json");
        buttonAtlas = LoadAssets.loadAtlas("ui/uiskin.atlas");
        skin.addRegions(buttonAtlas);

        TextButtonStyle textButtonStyle = new TextButtonStyle();
        textButtonStyle.font = game.getFontHashMap().get(BUTTON);
        textButtonStyle.up = skin.getDrawable("default-rect");
        textButtonStyle.down = skin.getDrawable("default-rect");
        textButtonStyle.checked = skin.getDrawable("default-rect");

        button = new TextButton(buttonText, textButtonStyle);

        button.setPosition(posX, posY);
        button.setSize(buttonWidth, buttonHeight);

        button.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                action.execute();
            }
        });
    }

    public void dispose() {
        skin.dispose();
        buttonAtlas.dispose();
    }

    public TextButton getButton() {
        return button;
    }
}
