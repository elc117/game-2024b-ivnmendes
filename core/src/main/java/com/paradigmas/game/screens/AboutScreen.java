package com.paradigmas.game.screens;

import com.badlogic.gdx.graphics.g2d.GlyphLayout;

import com.paradigmas.game.ParadigmersAdventure;
import com.paradigmas.game.ui.Button;
import com.paradigmas.game.ui.ButtonAction;

import static com.paradigmas.game.utils.FontType.TITLE;
import static com.paradigmas.game.utils.ScreenType.MAIN_SCREEN;

public class AboutScreen extends SuperScreen {
    public AboutScreen(ParadigmersAdventure game, String backgroundTexturePath, String backgroundMusicPath) {
        super(game, backgroundTexturePath, backgroundMusicPath);

        String text = "<-";
        ButtonAction action = () -> game.getScreenManager().showScreen(MAIN_SCREEN);
        Button button = new Button(
            game,
            text,
            getWorldWidth() - 1,
            0,
            1.3f,
            1f,
            action
        );

        addButton(button);
    }

    @Override
    public void draw(float delta) {
        GlyphLayout layout = new GlyphLayout(
            game.getFontHashMap().get(TITLE),
            "Sobre"
        );
        float textWidth = layout.width;
        game.getFontHashMap().get(TITLE).draw(
            game.getBatch(),
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
}
