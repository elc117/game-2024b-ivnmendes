package com.paradigmas.game.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.GlyphLayout;
import com.badlogic.gdx.utils.ScreenUtils;
import com.paradigmas.game.Main;
import com.paradigmas.game.ui.Button;
import com.paradigmas.game.ui.ButtonAction;

import static com.paradigmas.game.utils.FontType.TITLE;

public class MainMenuScreen extends SuperScreen {
    private String teste = "Hello World";
    public MainMenuScreen(Main game, String backgroundTexturePath, String backgroundMusicPath) {
        super(game, backgroundTexturePath, backgroundMusicPath);

        String[] textButtons = {"Jogar", "Sobre", "Sair"};
        ButtonAction[] action = {
            () -> super.game.setScreen(new GameScreen(game, backgroundTexturePath, backgroundMusicPath)),
            () -> super.game.setScreen(new AboutScreen(game, backgroundTexturePath, backgroundMusicPath)),
            () -> Gdx.app.exit()
        };

        float buttonDistance = 0;
        for (int i = 0; i < 3; i++) {
            Button button = new Button(
                game,
                textButtons[i],
                super.worldWidth / 2 + 400 ,
                super.worldHeight / 2 + buttonDistance + 400,
                200,
                100,
                action[i]
            );
            super.addButton(button);
            buttonDistance -= 150;
        }

    }

    @Override
    public void show() {
        super.backgroundMusic.play();
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
            (worldHeight + textHeight) / 2 + 1.3f
        );

        super.game.getBatch().end();

        stage.act(
            Math.min(
                Gdx.graphics.getDeltaTime(), 1 / 30f
            )
        );
        stage.draw();
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
