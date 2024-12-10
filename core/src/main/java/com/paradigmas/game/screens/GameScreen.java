package com.paradigmas.game.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.GlyphLayout;
import com.badlogic.gdx.utils.Align;
import com.badlogic.gdx.utils.ScreenUtils;
import com.paradigmas.game.Main;
import com.paradigmas.game.entities.Enemy;
import com.paradigmas.game.entities.Paradigmer;
import com.paradigmas.game.ui.ButtonAction;
import com.paradigmas.game.ui.Button;
import com.paradigmas.game.utils.QuizGenerator;
import com.paradigmas.game.utils.ScreenType;

import java.util.HashMap;

import static com.paradigmas.game.utils.FontType.TITLE;
import static com.paradigmas.game.utils.ScreenType.*;

public class GameScreen extends SuperScreen {
    public GameScreen(Main game, String backgroundTexturePath, String backgroundMusicPath) {
        super(game, backgroundTexturePath, backgroundMusicPath);

        String[] level = { "Geoparque Quarta-Colonia", "Geaoparque Cacapava", "Jardim Botanico" };
        float buttonDistance = 0;
        for (int i = 0; i < 3; i++) {
            int option = i;
            String battleBackgroundPath = "backgrounds/battleBackground" + i + ".png";
            ButtonAction action = () -> setBattleScreen(battleBackgroundPath, backgroundMusicPath, option);
            Button button = new Button(
                super.game,
                level[i],
                1f,
                super.worldHeight / 2 + buttonDistance,
                5.2f,
                1f,
                action
            );

            super.addButton(button);
            buttonDistance -= 1.5f;
        }

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
        ScreenUtils.clear(Color.BLACK);
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

        String text = "Selecione uma fase:";
        GlyphLayout layout = new GlyphLayout(
            super.game.getFontHashMap().get(TITLE),
            text
        );
        float textWidth = layout.width;
        float textHeight = layout.height;
        float x = (worldWidth - textWidth) / 2 + 2;
        float y = (worldHeight + textHeight) / 2 + 3.5f;
        float targetWidth = super.worldWidth;
        int alignment = Align.left;
        super.game.getFontHashMap().get(TITLE).draw(
            super.game.getBatch(),
            text,
            x,
            y,
            targetWidth,
            alignment,
            true
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
    public void show() {
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

    public void dispose() {
        super.dispose();
    }

    private void setBattleScreen(String backgroundTexturePath, String backgroundMusicPath, int option) {
        ScreenManager screenManager = super.game.getScreenManager();
        HashMap<ScreenType, SuperScreen> screens = screenManager.getScreens();
        screens.remove(BATTLE_SCREEN);

        String[] enemySpritePath = {"sprites/dinosaur.gif", "sprites/guardian.gif", "sprites/plantMonster.png"};
        String[] enemyName = {"Dinossauro", "Golem", "Planta monstro"};

        Enemy enemy = new Enemy(enemyName[option], enemySpritePath[option]);
        Paradigmer paradigmer = new Paradigmer("Paradigmer", "sprites/paradigmer.png");
        BattleScreen battleScreen = new BattleScreen(super.game, backgroundTexturePath, backgroundMusicPath, QuizGenerator.quizGenerate(option), enemy, paradigmer);
        screens.put(BATTLE_SCREEN, battleScreen);
        screenManager.showScreen(BATTLE_SCREEN);
    }
}
