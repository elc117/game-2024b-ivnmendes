package com.paradigmas.game.screens;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.GlyphLayout;
import com.badlogic.gdx.utils.ScreenUtils;
import com.paradigmas.game.Main;

import static com.paradigmas.game.utils.FontType.TITLE;

public class AboutScreen extends SuperScreen {
    public AboutScreen(Main game, String backgroundTexturePath, String backgroundMusicPath) {
        super(game, backgroundTexturePath, backgroundMusicPath);
    }

    @Override
    public void draw(float delta) {
        ScreenUtils.clear(Color.BLACK);
        super.game.getViewport().apply();
        super.game.getBatch().setProjectionMatrix(
            super.game.getViewport().getCamera().combined
        );

        super.game.getBatch().begin();

        GlyphLayout layout = new GlyphLayout(
            super.game.getFontHashMap().get(TITLE),
            "About"
        );
        float textWidth = layout.width;
        float textHeight = layout.height;

        super.game.getFontHashMap().get(TITLE).draw(
            super.game.getBatch(),
            "About",
            (worldWidth - textWidth) / 2,
            (worldHeight + textHeight) / 2 + 1.3f
        );

        super.game.getBatch().end();
    }

    @Override
    public void logic(float delta) {

    }

    @Override
    public void show() {

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

    public void dispose() {
        super.dispose();
    }

}
