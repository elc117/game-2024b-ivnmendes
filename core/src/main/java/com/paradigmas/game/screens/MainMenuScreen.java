package com.paradigmas.game.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.GlyphLayout;
import com.badlogic.gdx.utils.Align;

import com.paradigmas.game.ParadigmersAdventure;
import com.paradigmas.game.ui.Button;
import com.paradigmas.game.ui.ButtonAction;

import static com.paradigmas.game.utils.FontType.TITLE;
import static com.paradigmas.game.utils.ScreenType.*;

public class MainMenuScreen extends SuperScreen {
    public MainMenuScreen(ParadigmersAdventure game, String backgroundTexturePath, String backgroundMusicPath) {
        super(game, backgroundTexturePath, backgroundMusicPath);
        String[] textButtons = {"Jogar", "Sobre", "Sair"};
        ButtonAction[] action = {
            () -> game.getScreenManager().showScreen(SELECT_SCREEN),
            () -> game.getScreenManager().showScreen(ABOUT_SCREEN),
            () -> Gdx.app.exit()
        };

        float buttonDistance = 0;
        for (int i = 0; i < 3; i++) {
            Button button = new Button(
                game,
                textButtons[i],
                worldWidth / 2f - 1f,
                worldHeight / 2f + buttonDistance,
                2f,
                1f,
                action[i],
                Align.center
            );
            addButton(button);
            buttonDistance -= 1.5f;
        }

    }

    @Override
    public void draw(float delta) {
        String text = "Paradigmer's Adventure";
        GlyphLayout layout = new GlyphLayout(
            game.getFontHashMap().get(TITLE),
            text
        );
        float textWidth = layout.width;
        float textHeight = layout.height;
        float x = (worldWidth - textWidth) / 2f + 2.7f;
        float y = (worldHeight + textHeight) / 2f + 3f;
        float targetWidth = worldWidth;
        int alignment = Align.center;
        game.getFontHashMap().get(TITLE).draw(
            game.getBatch(),
            text,
            x,
            y,
            targetWidth,
            alignment,
            true
        );
    }

    @Override
    public void show() {
        super.show();
        backgroundMusic.play();
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
