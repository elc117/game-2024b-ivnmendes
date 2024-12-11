package com.paradigmas.game.screens;

import com.badlogic.gdx.graphics.g2d.GlyphLayout;
import com.badlogic.gdx.utils.Align;

import com.paradigmas.game.ParadigmersAdventure;
import com.paradigmas.game.ui.Button;
import com.paradigmas.game.ui.ButtonAction;
import com.paradigmas.game.utils.ScreenType;

import static com.paradigmas.game.utils.FontType.TITLE;
import static com.paradigmas.game.utils.ScreenType.MAIN_SCREEN;

public class EndGameScreen extends SuperScreen {
    private final boolean result;
    public EndGameScreen(ParadigmersAdventure game, String backgroundTexturePath, String backgroundMusicPath, boolean result) {
        super(game, backgroundTexturePath, backgroundMusicPath);

        this.result = result;

        String text = "menu";
        ButtonAction action = () -> changeScreen(MAIN_SCREEN);
        Button button = new Button(
            game,
            text,
            getWorldWidth() / 2f - 1.2f,
            getWorldHeight() / 2f - 3f,
            2f,
            1f,
            action
        );

        addButton(button);
    }

    public void draw(float delta) {
        String resultText = (result) ? "Parabens, voce venceu!" : "Voce perdeu, mais sorte da proxima vez";
        float xOffset = (result) ? 4f : 12f;
        GlyphLayout layout = new GlyphLayout(
            game.getFontHashMap().get(TITLE),
            resultText
        );
        float textWidth = layout.width;
        float textHeight = layout.height;
        float x = (worldWidth - textWidth) / 2f + xOffset;
        float y = (worldHeight + textHeight) / 2f + 3.5f;
        float targetWidth = 12.5f;
        int alignment = Align.center;
        game.getFontHashMap().get(TITLE).draw(
            game.getBatch(),
            resultText,
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
        if (result) {
            backgroundMusic.play();
        }
    }

    public void changeScreen(ScreenType screenType) {
        if (result) {
            backgroundMusic.stop();
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
