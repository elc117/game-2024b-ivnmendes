package com.paradigmas.game.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.GlyphLayout;
import com.badlogic.gdx.utils.Align;
import com.badlogic.gdx.utils.ScreenUtils;
import com.paradigmas.game.Main;
import com.paradigmas.game.ui.Button;
import com.paradigmas.game.ui.ButtonAction;

import static com.paradigmas.game.utils.FontType.TITLE;
import static com.paradigmas.game.utils.ScreenType.MAIN_SCREEN;

public class EndGameScreen extends SuperScreen {
    private final boolean result;
    public EndGameScreen(Main game, String backgroundTexturePath, String backgroundMusicPath, boolean result) {
        super(game, backgroundTexturePath, backgroundMusicPath);

        this.result = result;

        String text = "menu";
        ButtonAction action = () -> super.game.getScreenManager().showScreen(MAIN_SCREEN);
        Button button = new Button(
            super.game,
            text,
            super.getWorldWidth() / 2 - 1.2f,
            super.getWorldHeight() / 2 - 3f,
            2f,
            1f,
            action
        );

        super.addButton(button);
    }

    public void draw(float delta) {
        ScreenUtils.clear(Color.WHITE);
        super.game.getViewport().apply();
        super.game.getBatch().setProjectionMatrix(
            super.game.getViewport().getCamera().combined
        );

        super.game.getBatch().begin();

        super.game.getBatch().draw(
            backgroundTexture,
            0,
            0,
            worldWidth,
            worldHeight
        );

        String resultText = (result) ? "Parabens, voce venceu!" : "Voce perdeu, mais sorte da proxima vez";
        float xOffset = (result) ? 4 : 12;
        GlyphLayout layout = new GlyphLayout(
            super.game.getFontHashMap().get(TITLE),
            resultText
        );
        float textWidth = layout.width;
        float textHeight = layout.height;
        float x = (worldWidth - textWidth) / 2 + xOffset;
        float y = (worldHeight + textHeight) / 2 + 3.5f;
        float targetWidth = 12.5f;
        int alignment = Align.center;
        super.game.getFontHashMap().get(TITLE).draw(
            super.game.getBatch(),
            resultText,
            x,
            y,
            targetWidth,
            alignment,
            true
        );

        super.game.getBatch().end();

        super.stage.act(
            Math.min(
                Gdx.graphics.getDeltaTime(), 1 / 30f
            )
        );
        super.stage.draw();
    }

    @Override
    public void logic(float delta) {

    }

    @Override
    public void show() {
        Gdx.input.setInputProcessor(super.stage);
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
