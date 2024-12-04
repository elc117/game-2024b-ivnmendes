package com.paradigmas.game.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.paradigmas.game.Main;
import com.paradigmas.game.ui.Button;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;

@Getter
public abstract class SuperScreen implements Screen {
    protected final Main game;
    protected Texture backgroundTexture;
    protected Music backgroundMusic;
    protected ArrayList<Button> Buttons;
    protected Stage stage;

    @Setter protected float worldWidth;
    @Setter protected float worldHeight;

    public SuperScreen(final Main game, String backgroundTexturePath, String backgroundMusicPath) {
        worldWidth = game.getViewport().getWorldWidth();
        worldHeight = game.getViewport().getWorldHeight();

        stage = new Stage();

        this.game = game;

        try {
            this.backgroundTexture = new Texture(backgroundTexturePath);
        } catch (Exception e) {
            throw new RuntimeException("Erro ao carregar textura: " + backgroundTexturePath, e);
        }

        try {
            this.backgroundMusic = Gdx.audio.newMusic(Gdx.files.internal(backgroundMusicPath));
            backgroundMusic.setLooping(true);
            backgroundMusic.setVolume(0.5F);
        } catch (Exception e) {
            throw new RuntimeException("Erro ao carregar música: " + backgroundMusicPath, e);
        }

        Buttons = new ArrayList<>();
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
        game.getViewport().update(width, height, true);
    }

    public void addButton (Button button) {
        Buttons.add(button);
        stage.addActor(button.getButton());
    }

    @Override
    public void dispose() {
        if (backgroundTexture != null) backgroundTexture.dispose();
        if (backgroundMusic != null) backgroundMusic.dispose();

        for(Button b : Buttons) {
            b.dispose();
        }
    }

}

