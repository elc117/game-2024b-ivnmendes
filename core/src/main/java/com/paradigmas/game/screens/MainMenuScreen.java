package com.paradigmas.game.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.GlyphLayout;
import com.badlogic.gdx.utils.ScreenUtils;
import com.paradigmas.game.Main;
import com.paradigmas.game.ui.Button;
import com.paradigmas.game.ui.ButtonAction;

public class MainMenuScreen extends SuperScreen {
    public MainMenuScreen(Main game, String backgroundTexturePath, String backgroundMusicPath) {
        super(game, backgroundTexturePath, backgroundMusicPath);

        String[] textButtons = {"Jogar", "Sobre", "Sair"};
        ButtonAction action = () -> {
            System.out.println("Clicado!!!!");
        };

        float buttonDistance = 0;
        for (int i = 0; i < 3; i++) {
            Button button = new Button(game, textButtons[i], super.worldWidth / 2, super.worldHeight / 2 + buttonDistance, action);
            super.addButton(button);
            System.out.printf("x:%f\ny:%f\n", super.worldWidth / 2, super.worldHeight / 2 + buttonDistance);
            buttonDistance += 40;
        }
    }

    @Override
    public void show() {
        backgroundMusic.play();
        Gdx.input.setInputProcessor(super.stage);
    }

    @Override
    public void draw(float delta) {
        ScreenUtils.clear(Color.BLACK);

        game.getViewport().apply();
        game.getBatch().setProjectionMatrix(game.getViewport().getCamera().combined);

        game.getBatch().begin();

        game.getBatch().draw(backgroundTexture, 0, 0, worldWidth, worldHeight);

        BitmapFont font = game.getFont();
        GlyphLayout layout = new GlyphLayout(font, "Hello world!!!");
        float textWidth = layout.width;
        float textHeight = layout.height;
        game.getFont().draw(game.getBatch(), "Hello world!!!", (worldWidth - textWidth) / 2, (worldHeight + textHeight) / 2);

        game.getBatch().end();

        stage.act(Math.min(Gdx.graphics.getDeltaTime(), 1 / 30f));
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
