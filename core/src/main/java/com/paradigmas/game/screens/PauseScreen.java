package com.paradigmas.game.screens;

import com.badlogic.gdx.graphics.g2d.GlyphLayout;

import com.badlogic.gdx.utils.Align;
import com.paradigmas.game.ParadigmersAdventure;
import com.paradigmas.game.ui.Button;
import com.paradigmas.game.ui.ButtonAction;
import com.paradigmas.game.utils.ScreenType;

import static com.paradigmas.game.utils.FontType.*;
import static com.paradigmas.game.utils.ScreenType.*;

public class PauseScreen extends SuperScreen {
    public PauseScreen(ParadigmersAdventure game, String backgroundTexturePath, String backgroundMusicPath) {
        super(game, backgroundTexturePath, backgroundMusicPath);

        ButtonAction[] actions = {
            () -> changeScreen(BATTLE_SCREEN),
            () -> changeScreen(MAIN_SCREEN)
        };

        String[] textButtons = {"Continuar", "Menu"};

        float buttonDistance = 0;
        for (int i = 0; i < 2; i++) {
            Button button = new Button(
                game,
                textButtons[i],
                worldWidth / 2f - 1f,
                worldHeight / 2f + buttonDistance,
                2f,
                1f,
                actions[i],
                Align.center
            );
            addButton(button);
            buttonDistance -= 1.5f;
        }
    }

    @Override
    public void draw(float delta) {
        GlyphLayout layout = new GlyphLayout(game.getFontHashMap().get(TITLE), "Pausado");
        float textWidth = layout.width;
        float textHeight = layout.height;
        game.getFontHashMap().get(TITLE).draw(
            game.getBatch(),
            "Pausado",
            (worldWidth - textWidth) / 2,
            (worldHeight + textHeight) / 2 + 1.9f
        );
    }

    public void changeScreen(ScreenType screenType) {
        if (screenType == MAIN_SCREEN) {
            game.getScreenManager().getScreen(BATTLE_SCREEN).getBackgroundMusic().stop();
            game.getScreenManager().showScreen(screenType);
            return;
        }

        game.getScreenManager().showScreen(screenType);
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
