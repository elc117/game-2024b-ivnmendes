package com.paradigmas.game.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.GlyphLayout;
import com.paradigmas.game.Main;
import com.paradigmas.game.ui.Button;
import com.paradigmas.game.ui.ButtonAction;

import static com.paradigmas.game.utils.FontType.TITLE;
import static com.paradigmas.game.utils.ScreenType.MAIN_SCREEN;

public class AboutScreen extends SuperScreen {
    public AboutScreen(Main game, String backgroundTexturePath, String backgroundMusicPath) {
        super(game, backgroundTexturePath, backgroundMusicPath);

        String text = "<-";
        ButtonAction action = () -> super.game.getScreenManager().showScreen(MAIN_SCREEN);
        Button button = new Button(
            super.game,
            text,
            super.getWorldWidth() - 1,
            0,
            1.3f,
            1f,
            action
        );

        super.addButton(button);
    }

    @Override
    public void draw(float delta) {
        GlyphLayout layout = new GlyphLayout(
            super.game.getFontHashMap().get(TITLE),
            "Sobre"
        );
        float textWidth = layout.width;
        super.game.getFontHashMap().get(TITLE).draw(
            super.game.getBatch(),
            "Sobre",
            (worldWidth - textWidth) / 2,
            worldHeight
        );
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

    public void dispose() {
        super.dispose();
    }

}
