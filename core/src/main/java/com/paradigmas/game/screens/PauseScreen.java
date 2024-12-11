package com.paradigmas.game.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.GlyphLayout;
import com.badlogic.gdx.utils.ScreenUtils;
import com.paradigmas.game.Main;
import com.paradigmas.game.ui.Button;
import com.paradigmas.game.ui.ButtonAction;
import com.paradigmas.game.utils.ScreenType;

import static com.paradigmas.game.utils.FontType.*;
import static com.paradigmas.game.utils.ScreenType.*;

public class PauseScreen extends SuperScreen {
    public PauseScreen(Main game, String backgroundTexturePath, String backgroundMusicPath) {
        super(game, backgroundTexturePath, backgroundMusicPath);

        ButtonAction[] actions = {
            () -> changeScreen(BATTLE_SCREEN),
            () -> changeScreen(MAIN_SCREEN)
        };

        String[] textButtons = {"Continuar", "Menu"};

        float buttonDistance = 0;
        for (int i = 0; i < 2; i++) {
            Button button = new Button(
                super.game,
                textButtons[i],
                super.worldWidth / 2 - 1f,
                super.worldHeight / 2 + buttonDistance,
                2f,
                1f,
                actions[i]
            );
            super.addButton(button);
            buttonDistance -= 1.5f;
        }
    }

    @Override
    public void draw(float delta) {
        GlyphLayout layout = new GlyphLayout(super.game.getFontHashMap().get(TITLE), "Pausado");
        float textWidth = layout.width;
        float textHeight = layout.height;
        super.game.getFontHashMap().get(TITLE).draw(
            super.game.getBatch(),
            "Pausado",
            (worldWidth - textWidth) / 2,
            (worldHeight + textHeight) / 2 + 1.9f
        );
    }

    public void changeScreen(ScreenType screenType) {
        if (screenType == MAIN_SCREEN) {
            super.game.getScreenManager().getScreen(BATTLE_SCREEN).getBackgroundMusic().stop();
            super.game.getScreenManager().showScreen(screenType);
            return;
        }

        super.game.getScreenManager().showScreen(screenType);
    }

    @Override
    public void logic(float delta) {

    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void hide() {

    }
}
