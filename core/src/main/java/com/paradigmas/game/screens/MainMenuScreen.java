package com.paradigmas.game.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.GlyphLayout;
import com.badlogic.gdx.utils.ScreenUtils;
import com.paradigmas.game.Main;
import com.paradigmas.game.ui.Button;
import com.paradigmas.game.ui.ButtonAction;

import static com.paradigmas.game.utils.FontType.TITLE;
import static com.paradigmas.game.utils.ScreenType.*;

public class MainMenuScreen extends SuperScreen {
    private String teste = "Hello World";
    public MainMenuScreen(Main game, String backgroundTexturePath, String backgroundMusicPath) {
        super(game, backgroundTexturePath, backgroundMusicPath);

        String[] textButtons = {"Jogar", "Sobre", "Sair"};
        ButtonAction[] action = {
            () -> super.game.getScreenManager().showScreen(SELECT_SCREEN),
            () -> super.game.getScreenManager().showScreen(ABOUT_SCREEN),
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
    public void show() {
//        super.backgroundMusic.play();
        Gdx.input.setInputProcessor(super.stage);
    }

    @Override
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

        GlyphLayout layout = new GlyphLayout(
            super.game.getFontHashMap().get(TITLE),
            teste
        );
        float textWidth = layout.width;
        float textHeight = layout.height;

        super.game.getFontHashMap().get(TITLE).draw(
            super.game.getBatch(),
            teste,
            (worldWidth - textWidth) / 2,
            (worldHeight + textHeight) / 2 + 2f
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
