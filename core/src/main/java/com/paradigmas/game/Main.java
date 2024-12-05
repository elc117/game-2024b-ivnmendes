package com.paradigmas.game;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.paradigmas.game.screens.*;
import com.paradigmas.game.utils.FontType;
import com.paradigmas.game.utils.LoadAssets;

import static com.paradigmas.game.utils.ScreenType.*;
import lombok.Getter;

import java.util.HashMap;

import static com.paradigmas.game.utils.FontType.*;

@Getter
public class Main extends Game {
    private SpriteBatch batch;
    private FitViewport viewport;
    private HashMap<FontType, BitmapFont> fontHashMap;
    private ScreenManager screenManager;

    @Override
    public void create() {
        screenManager = new ScreenManager(this);

        batch = new SpriteBatch();

        viewport = new FitViewport(16, 9);

        fontHashMap = new HashMap<>();
        initializeFonts(fontHashMap);

        createScreens(screenManager);

        screenManager.showScreen(MAIN_SCREEN);

    }

    private void createScreens(ScreenManager screenManager) {
        screenManager.addScreen(
            MAIN_SCREEN,
            new MainMenuScreen(
                this,
                "images/background.jpg",
                "sounds/backgroundMusic.mp3"
            )
        );

        screenManager.addScreen(
            SELECT_SCREEN,
            new GameScreen(
                this,
                "images/background.jpg",
                "sounds/backgroundMusic.mp3"
            )
        );

        screenManager.addScreen(
            ABOUT_SCREEN,
            new AboutScreen(
                this,
                "images/background.jpg",
                "sounds/backgroundMusic.mp3"
            )
        );

        screenManager.addScreen(
            PAUSE_SCREEN,
            new PauseScreen(
                this,
                "images/background.jpg",
                "sounds/backgroundMusic.mp3"
            )
        );
    }

    private void initializeFonts(HashMap<FontType, BitmapFont> fontHashMap) {
        String path = "font/font.TTF";
        FontType[] fontTypes = FontType.values();
        int[] sizes = {20, 100, 20};

        // Inicializa as fontes e seta a escala para se adequar ao vieport
        for (int i = 0; i < 3; i++) {
            fontHashMap.put(fontTypes[i], LoadAssets.loadFont(path, sizes[i]));
            fontHashMap.get(fontTypes[i]).setUseIntegerPositions(false);
            fontHashMap.get(fontTypes[i]).getData().setScale(
                viewport.getWorldHeight() / Gdx.graphics.getHeight()
            );
        }
    }

    public void render() {
        super.render();
    }

    public void dispose() {
        batch.dispose();
        fontHashMap.forEach((key, value) -> {
            value.dispose();
        });
    }
}
