package com.paradigmas.game.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.Array;
import com.paradigmas.game.Main;
import com.paradigmas.game.ui.Button;
import com.paradigmas.game.utils.LoadAssets;

import java.util.ArrayList;

public abstract class SuperScreen implements Screen {
    protected final Main game;
    protected Texture backgroundTexture;
    protected Music backgroundMusic;
    protected ArrayList<Button> buttons;
    protected Stage stage;

    protected float worldWidth;
    protected float worldHeight;

    public SuperScreen(final Main game, String backgroundTexturePath, String backgroundMusicPath) {
        worldWidth = game.getViewport().getWorldWidth();
        worldHeight = game.getViewport().getWorldHeight();

        stage = new Stage(game.getViewport());

        this.game = game;

        this.backgroundTexture = LoadAssets.loadTexture(backgroundTexturePath);

        this.backgroundMusic = LoadAssets.loadMusic(backgroundMusicPath);

        buttons = new ArrayList<>();
    }

    @Override
    public void render(float delta) {
        logic(delta);
        draw(delta);
    }

    public abstract void draw(float delta);

    public abstract void logic(float delta);

    @Override
    public void resize(int width, int height) {
        worldWidth = game.getViewport().getWorldWidth();
        worldHeight = game.getViewport().getWorldHeight();

        game.getViewport().update(width, height, true);
        stage.getViewport().update(width, height, true);
    }

    public void addButton (Button button) {
        buttons.add(button);
        stage.addActor(button.getButton());
    }

    // Remove todos os botões a partir de firstButton
    public void removeButtons(int firstButton) {
        Array<Actor> buttonActors = stage.getActors();
        buttonActors.removeRange(firstButton, buttonActors.size - 1);
        for (int i = firstButton; i < buttons.size(); i++) {
            Button button = buttons.remove(i);
            button.dispose();
        }
    }

    @Override
    public void dispose() {
        if (backgroundTexture != null) backgroundTexture.dispose();
        if (backgroundMusic != null) backgroundMusic.dispose();

        for(Button b : buttons) {
            b.dispose();
        }
    }

    public Main getGame() {
        return game;
    }

    public Texture getBackgroundTexture() {
        return backgroundTexture;
    }

    public Music getBackgroundMusic() {
        return backgroundMusic;
    }

    public ArrayList<Button> getButtons() {
        return buttons;
    }

    public Stage getStage() {
        return stage;
    }

    public float getWorldWidth() {
        return worldWidth;
    }

    public float getWorldHeight() {
        return worldHeight;
    }

    public void setWorldHeight(float worldHeight) {
        this.worldHeight = worldHeight;
    }

    public void setWorldWidth(float worldWidth) {
        this.worldWidth = worldWidth;
    }
}

