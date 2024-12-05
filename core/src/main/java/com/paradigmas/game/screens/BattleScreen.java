package com.paradigmas.game.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.GlyphLayout;
import com.badlogic.gdx.utils.Align;
import com.badlogic.gdx.utils.ScreenUtils;
import com.paradigmas.game.Main;
import com.paradigmas.game.ui.Button;
import com.paradigmas.game.ui.ButtonAction;
import lombok.Getter;
import lombok.Setter;

import static com.paradigmas.game.utils.FontType.*;
import static com.paradigmas.game.utils.ScreenType.*;

@Getter
@Setter
public class BattleScreen extends SuperScreen {
    private String title;
    public BattleScreen(Main game, String backgroundTexturePath, String backgroundMusicPath, String title) {
        super(game, backgroundTexturePath, backgroundMusicPath);
        this.title = title;

        String text = "Pause";
        ButtonAction action = () -> super.game.getScreenManager().showScreen(PAUSE_SCREEN);
        Button button = new Button(
            super.game,
            text,
            super.getWorldWidth() - 1,
            super.getWorldHeight() - 1,
            1.3f,
            1f,
            action
        );

        super.addButton(button);
    }

    @Override
    public void draw(float delta) {
        ScreenUtils.clear(Color.BLACK);
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

        drawTextMultiline(title, 6.5f);

        super.game.getBatch().end();

        super.stage.act(
            Math.min(
                Gdx.graphics.getDeltaTime(), 1 / 30f
            )
        );
        super.stage.draw();
    }

    private void drawTextMultiline(String text, float targetWidth) {
        GlyphLayout layout = new GlyphLayout(
            super.game.getFontHashMap().get(TITLE),
            text
        );
        float textWidth = layout.width;
        float textHeight = layout.height;
        float x = (worldWidth - textWidth) / 2 + 2;
        float y = (worldHeight + textHeight) / 2 + 2.1f;
        int alignment = Align.left;
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
