package com.paradigmas.game.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.GlyphLayout;
import com.badlogic.gdx.utils.ScreenUtils;
import com.paradigmas.game.Main;
import com.paradigmas.game.ui.Button;
import com.paradigmas.game.ui.ButtonAction;
import com.paradigmas.game.utils.ScreenType;

import static com.paradigmas.game.utils.FontType.*;
import static com.paradigmas.game.utils.ScreenType.*;

public class PauseScreen extends SuperScreen {
    public PauseScreen(Main game, String backgroundTexturePath, String backgroundMusicPath) {
        super(game, backgroundTexturePath, backgroundMusicPath);

        ButtonAction[] actions = {
            () -> super.game.getScreenManager().showScreen(BATTLE_SCREEN),
            () -> super.game.getScreenManager().showScreen(MAIN_SCREEN)
        };

        String[] textButtons = {"Continuar", "Menu"};

        float buttonDistance = 0;
        for (int i = 0; i < 2; i++) {
            Button button = new Button(
                super.game,
                textButtons[i],
                super.worldWidth / 2 - 1f,
                super.worldHeight / 2 + buttonDistance,
                2f,
                1f,
                actions[i]
            );
            super.addButton(button);
            buttonDistance -= 1.5f;
        }
    }

    @Override
    public void draw(float delta) {
        ScreenUtils.clear(Color.GRAY);

        super.game.getViewport().apply();
        super.game.getBatch().setProjectionMatrix(super.game.getViewport().getCamera().combined);

        super.game.getBatch().begin();

        GlyphLayout layout = new GlyphLayout(super.game.getFontHashMap().get(TITLE), "Pausado");
        float textWidth = layout.width;
        float textHeight = layout.height;
        super.game.getFontHashMap().get(TITLE).draw(
            super.game.getBatch(),
            "Pausado",
            (worldWidth - textWidth) / 2,
            (worldHeight + textHeight) / 2 + 1.9f
        );

        super.game.getBatch().end();

        super.stage.act(Math.min(Gdx.graphics.getDeltaTime(), 1 / 30f));
        super.stage.draw();
    }

    @Override
    public void logic(float delta) {

    }

    @Override
    public void show() {
        super.backgroundMusic.pause();
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
