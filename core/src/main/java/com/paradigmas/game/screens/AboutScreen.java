package com.paradigmas.game.screens;

import com.badlogic.gdx.graphics.g2d.GlyphLayout;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.utils.Align;
import com.paradigmas.game.ParadigmersAdventure;
import com.paradigmas.game.ui.Button;
import com.paradigmas.game.ui.ButtonAction;
import com.paradigmas.game.utils.LoadAssets;

import static com.paradigmas.game.utils.FontType.*;
import static com.paradigmas.game.utils.ScreenType.MAIN_SCREEN;

public class AboutScreen extends SuperScreen {
    private final Sprite textBackground;
    public AboutScreen(ParadigmersAdventure game, String backgroundTexturePath, String backgroundMusicPath) {
        super(game, backgroundTexturePath, backgroundMusicPath);

        textBackground = LoadAssets.loadSprite("images/aboutTextBackground.png", 16f, 9f);
        textBackground.setColor(1f, 1f, 1f, 0.8f);
        String text = "<-";
        ButtonAction action = () -> game.getScreenManager().showScreen(MAIN_SCREEN);
        Button button = new Button(
            game,
            text,
            super.getWorldWidth() - 1.3f,
            0f,
            1.3f,
            1f,
            action,
            Align.center
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

        textBackground.setPosition(0, 0);
        textBackground.draw(game.getBatch());

        String text = "Como um bom paradigmer, voce decide se aventurar por pontos turisticos na regiao. Durante suas aventuras, voce acaba encontrando seres hostis que podem ser derrotados apenas com o seu conhecimento sobre os lugares visitados, boa sorte!";
        float x = 0.5f;
        float y = worldHeight / 2f + 2.9f;
        float targetWidth = worldWidth - 1f;
        int alignment = Align.left;
        game.getFontHashMap().get(TEXT).draw(
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
