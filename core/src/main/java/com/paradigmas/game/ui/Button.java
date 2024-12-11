package com.paradigmas.game.ui;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton.TextButtonStyle;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.utils.Align;
import com.paradigmas.game.ParadigmersAdventure;
import com.paradigmas.game.utils.LoadAssets;

import static com.paradigmas.game.utils.FontType.*;

public class Button {
    private final TextButton button;
    Texture upTexture;
    Texture downTexture;
    Texture hoverTexture;

    public Button(ParadigmersAdventure game, String buttonText, float posX, float posY, float buttonWidth, float buttonHeight, ButtonAction action, int alignment) {
        upTexture = LoadAssets.loadTexture("ui/button.png");
        downTexture = LoadAssets.loadTexture("ui/button-pressed.png");
        hoverTexture = LoadAssets.loadTexture("ui/button-over.png");

        TextureRegionDrawable upDrawable = new TextureRegionDrawable(upTexture);
        TextureRegionDrawable downDrawable = new TextureRegionDrawable(downTexture);
        TextureRegionDrawable hoverDrawable = new TextureRegionDrawable(hoverTexture);

        TextButtonStyle textButtonStyle = new TextButtonStyle();
        textButtonStyle.font = game.getFontHashMap().get(BUTTON);
        textButtonStyle.up = upDrawable;
        textButtonStyle.down = downDrawable;
        textButtonStyle.over = hoverDrawable;

        button = new TextButton(buttonText, textButtonStyle);

        button.setPosition(posX, posY);
        button.setSize(buttonWidth, buttonHeight);
        button.getLabel().setAlignment(alignment);

        button.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                action.execute();
            }
        });
    }

    public void dispose() {
        upTexture.dispose();
        downTexture.dispose();
        hoverTexture.dispose();
    }

    public TextButton getButton() {
        return button;
    }
}
