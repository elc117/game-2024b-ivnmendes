package com.paradigmas.game.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.GlyphLayout;
import com.badlogic.gdx.utils.Align;
import com.badlogic.gdx.utils.ScreenUtils;
import com.paradigmas.game.Main;
import com.paradigmas.game.ui.Button;
import com.paradigmas.game.ui.ButtonAction;
import com.paradigmas.game.utils.ScreenType;

import static com.paradigmas.game.utils.FontType.TITLE;
import static com.paradigmas.game.utils.ScreenType.*;

public class MainMenuScreen extends SuperScreen {
    public MainMenuScreen(Main game, String backgroundTexturePath, String backgroundMusicPath) {
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
                super.game,
                textButtons[i],
                super.worldWidth / 2 - 1f,
                super.worldHeight / 2 + buttonDistance,
                2f,
                1f,
                action[i]
            );
            super.addButton(button);
            buttonDistance -= 1.5f;
        }

    }

    @Override
    public void draw(float delta) {
        String text = "Paradigmer's Adventure";
        GlyphLayout layout = new GlyphLayout(
            super.game.getFontHashMap().get(TITLE),
            text
        );
        float textWidth = layout.width;
        float textHeight = layout.height;
        float x = (worldWidth - textWidth) / 2 + 2.7f;
        float y = (worldHeight + textHeight) / 2 + 3f;
        float targetWidth = super.worldWidth;
        int alignment = Align.center;
        super.game.getFontHashMap().get(TITLE).draw(
            super.game.getBatch(),
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
        super.backgroundMusic.play();
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

    @Override
    public void dispose() {
        super.dispose();
    }
}
