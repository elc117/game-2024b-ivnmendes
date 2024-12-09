package com.paradigmas.game.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.utils.Align;
import com.badlogic.gdx.utils.ScreenUtils;
import com.paradigmas.game.Main;
import com.paradigmas.game.entities.Enemy;
import com.paradigmas.game.entities.Paradigmer;
import com.paradigmas.game.questions.Question;
import com.paradigmas.game.questions.Quiz;
import com.paradigmas.game.ui.Button;
import com.paradigmas.game.ui.ButtonAction;
import com.paradigmas.game.utils.BattleStatus;
import com.paradigmas.game.utils.LoadAssets;
import com.paradigmas.game.utils.ScreenType;

import java.util.HashMap;

import static com.paradigmas.game.utils.FontType.*;
import static com.paradigmas.game.utils.ScreenType.*;
import static com.paradigmas.game.utils.BattleStatus.*;
import static java.util.Collections.min;

public class BattleScreen extends SuperScreen {
    private final Quiz quiz;
    private final Enemy enemy;
    private final Paradigmer paradigmer;
    private final Sprite uiTextSprite;
    private final Sprite uiLifeBarSprite;
    private final Sprite[] uiLifeBarChunkSprite = new Sprite[3];
    private final Sprite uiQuestionSprite;
    private BattleStatus status;
    private Question actualQuestion;
    private boolean isCorrect;
    private float timePassed;
    private float timeText;
    private int currentCharIndex;

    public BattleScreen(Main game, String backgroundTexturePath, String backgroundMusicPath, Quiz quiz, Enemy enemy, Paradigmer paradigmer) {
        super(game, backgroundTexturePath, backgroundMusicPath);
        String[] lifeBarChunkSpriteColors = { "green", "red", "yellow" };
        for (int i = 0; i < 3; i++) {
            String path = "sprites/" + lifeBarChunkSpriteColors[i] + "Life.png";
            uiLifeBarChunkSprite[i] = LoadAssets.loadSprite(path, 0.2f, 0.17f);
        }
        uiTextSprite = LoadAssets.loadSprite("sprites/uiMenu.png", 17, 3);
        uiLifeBarSprite = LoadAssets.loadSprite("sprites/lifeBar.png", 5, 2);
        uiQuestionSprite = LoadAssets.loadSprite("sprites/questionSelection.png", 8, 4);
        this.quiz = quiz;
        this.enemy = enemy;
        this.paradigmer = paradigmer;
        currentCharIndex = 0;

        status = WAITING_ANSWER;
        actualQuestion = quiz.getQuestion();

        String text = "Pause";
        ButtonAction action = () -> super.game.getScreenManager().showScreen(PAUSE_SCREEN);
        Button button = new Button(
            super.game,
            text,
            super.getWorldWidth() - 1,
            super.getWorldHeight() - 1,
            1.3f,
            1f,
            action
        );

        super.addButton(button);

        initializeButtons();
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

        //sprite inimigo
        enemy.getSprite().setPosition(super.worldWidth/2 + 0.5f, super.worldHeight/2 - 0.9f);
        enemy.getSprite().draw(game.getBatch());

        //sprite paradigmer
        paradigmer.getSprite().setPosition(super.worldWidth/2 - 5.5f, super.worldHeight/2 - 3.5f);
        paradigmer.getSprite().draw(game.getBatch());

        //caixa onde o texto fica
        uiTextSprite.setPosition(-0.4f, -0.4f);
        uiTextSprite.draw(game.getBatch());

        //barra de vida do inimigo
        drawEnemyLifeBar();

        //Barra de vida do jogador
        drawPlayerLifeBar();

        if (status == WAITING_ANSWER) {
            //caixa de respostas
            uiQuestionSprite.setPosition(super.worldWidth - 7.69f, -0.6f);
            uiQuestionSprite.draw(game.getBatch());
        }

        //talvez tenha ficado meio confuso
        String correctAnswer = "Resposta correta! " + paradigmer.getConsecutiveHits() + " acertos consecutivos!";
        String textToDraw = (status == WAITING_ANSWER) ?
            actualQuestion.getQuestion()
            :
            (isCorrect) ?
                correctAnswer
                :
                "Resposta errada!";
        textToDraw = breakText(textToDraw, delta);
        float x = 0.5f;
        float y = 2f;
        int alignment = Align.left;
        super.game.getFontHashMap().get(TEXT_QUESTION).draw(
            super.game.getBatch(),
            textToDraw,
            x,
            y,
            7.5f,
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

    private void drawEnemyLifeBar() {
        int color;
        if (enemy.getLife() > 100) {
            color = 0;
        } else if (enemy.getLife() > 50) {
            color = 2;
        } else {
            color = 1;
        }

        int nLife = Math.min(22, 22 * enemy.getLife() / 200);

        uiLifeBarSprite.setPosition(0, super.worldHeight - 2);
        uiLifeBarSprite.draw(game.getBatch());
        super.game.getFontHashMap().get(TEXT_BATTLE).draw(
            super.game.getBatch(),
            enemy.getName(),
            0.5f,
            super.worldHeight - 0.5f
        );
        float xOffset = .0f;
        for(int i = 0; i < nLife; i++, xOffset += 0.1f) {
            uiLifeBarChunkSprite[color].setPosition(2f + xOffset, super.worldHeight - 1.17f);
            uiLifeBarChunkSprite[color].draw(game.getBatch());
        }
    }

    private void drawPlayerLifeBar() {
        int color;
        if (paradigmer.getLife() > 50) {
            color = 0;
        } else if (paradigmer.getLife() > 25) {
            color = 2;
        } else {
            color = 1;
        }

        int nLife = Math.min(22, 22 * paradigmer.getLife() / 100);

        uiLifeBarSprite.setPosition(super.worldWidth - 4.6f, 2.9f);
        uiLifeBarSprite.draw(game.getBatch());
        super.game.getFontHashMap().get(TEXT_BATTLE).draw(
            super.game.getBatch(),
            paradigmer.getName(),
            super.worldWidth - 4.1f,
            4.4f
        );
        String playLifeString = paradigmer.getLife() + "/100";
        super.game.getFontHashMap().get(TEXT_BATTLE).draw(
            super.game.getBatch(),
            playLifeString,
            super.worldWidth - 2.5f,
            3.57f
        );
        float xOffset = .0f;
        for(int i = 0; i < nLife; i++, xOffset += 0.1f) {
            uiLifeBarChunkSprite[color].setPosition(super.worldWidth - 2.6f + xOffset, 3.73f);
            uiLifeBarChunkSprite[color].draw(game.getBatch());
        }
    }

    @Override
    public void logic(float delta) {
        switch (status) {
            case ANSWERED:
                currentCharIndex = 0;
                super.removeButtons(1); //Remove todos os botões de responder questão
                if (isCorrect) {
                    paradigmer.hit();
                    enemy.causeDamage(paradigmer.getConsecutiveHits());
                } else {
                    paradigmer.miss();
                    paradigmer.causeDamage();
                }

                timePassed += delta;

                status = WAITING_NEXT_QUESTION;
                break;
            case WAITING_NEXT_QUESTION:
                timePassed += delta;

                if (timePassed >= 3f || Gdx.input.isTouched()) {
                    actualQuestion = quiz.getQuestion();
                    initializeButtons();
                    currentCharIndex = 0;
                    status = WAITING_ANSWER;
                }
                break;
        }

        if (paradigmer.getLife() == 0) {
            setEndGameScreen(false);
        } else if (enemy.getLife() == 0) {
            setEndGameScreen(true);
        }
    }

    private void setEndGameScreen(boolean result) {
        ScreenManager screenManager = super.game.getScreenManager();
        HashMap<ScreenType, SuperScreen> screens = screenManager.getScreens();
        screens.remove(END_SCREEN);

        String backgroundTexturePath = "images/battleBackground.jpg";
        String backgroundMusicPath = "sounds/backgroundMusic.mp3";
        EndGameScreen endGameScreen = new EndGameScreen(super.game, backgroundTexturePath, backgroundMusicPath, result);
        screens.put(END_SCREEN, endGameScreen);
        screenManager.showScreen(END_SCREEN);
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

    @Override
    public void dispose() {
        super.dispose();
        uiTextSprite.getTexture().dispose();
        uiLifeBarSprite.getTexture().dispose();
    }

    private String breakText(String text, float delta) {
        timeText += delta;

        float timePerChar = 0.03f;
        if (timeText >= timePerChar) {
            timeText = 0f;
            if (currentCharIndex < text.length()) {
                currentCharIndex++;
            }
        }

        return text.substring(0, currentCharIndex);
    }

    private void questionAnswered(int option) {
        isCorrect = actualQuestion.answerQuestion(option);
        status = ANSWERED;
        timePassed = 0f;
    }

    private void initializeButtons() {
        float buttonDistanceY = 0;
        int cont = 0;
        for (int i = 0; i < 2; i++) {
            float buttonDistanceX = 0;

            for (int j = 0; j < 2; j++) {
                int option = cont + 1;
                ButtonAction action = () -> questionAnswered(option - 1);

                Button button = new Button(
                    super.game,
                    actualQuestion.getAnswer(cont),
                    super.worldWidth - 7f + buttonDistanceX,
                    1.7f - buttonDistanceY,
                    3f,
                    1f,
                    action
                );

                super.addButton(button);
                buttonDistanceX += 3.7f;
                cont++;
            }
            buttonDistanceY += 1.5f;
        }
    }

    public Quiz getQuiz() {
        return quiz;
    }

    public Enemy getEnemy() {
        return enemy;
    }

    public Paradigmer getParadigmer() {
        return paradigmer;
    }

    public Sprite getUiTextSprite() {
        return uiTextSprite;
    }

    public Sprite getUiLifeBarSprite() {
        return uiLifeBarSprite;
    }

    public Sprite getUiQuestionSprite() {
        return uiQuestionSprite;
    }

    public BattleStatus getStatus() {
        return status;
    }

    public Question getActualQuestion() {
        return actualQuestion;
    }

    public boolean isCorrect() {
        return isCorrect;
    }

    public float getTimePassed() {
        return timePassed;
    }

    public float getTimeText() {
        return timeText;
    }

    public int getCurrentCharIndex() {
        return currentCharIndex;
    }
}
