package com.paradigmas.game.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.utils.Align;

import com.paradigmas.game.ParadigmersAdventure;
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

public class BattleScreen extends SuperScreen {
    private final Quiz quiz;
    private final Enemy enemy;
    private final float lifeDecayRateParadigmer;
    private final float lifeDecayRateEnemy;
    private final Paradigmer paradigmer;
    private final Sprite uiTextSprite;
    private final Sprite uiLifeBarSprite;
    private final Sprite[] uiLifeBarChunkSprite = new Sprite[3];
    private final Sprite uiQuestionSprite;
    private final String backgroundTexturePath;

    private BattleStatus status;
    private boolean isCorrect;
    private boolean hasProcessedTouch;
    private float currentLifeEnemy;
    private float currentLifeParadigmer;
    private float lifeDecayTimeEnemy;
    private float lifeDecayTimeParadigmer;
    private float timePassed;
    private float timeText;
    private int actualAnswer;
    private int currentCharIndex;
    private Question actualQuestion;


    public BattleScreen(ParadigmersAdventure game, String backgroundTexturePath, String backgroundMusicPath, Quiz quiz, Enemy enemy, Paradigmer paradigmer) {
        super(game, backgroundTexturePath, backgroundMusicPath);
        String[] lifeBarChunkSpriteColors = { "green", "red", "yellow" };
        for (int i = 0; i < 3; i++) {
            String path = "sprites/" + lifeBarChunkSpriteColors[i] + "Life.png";
            uiLifeBarChunkSprite[i] = LoadAssets.loadSprite(path, 0.2f, 0.17f);
        }
        uiTextSprite = LoadAssets.loadSprite("sprites/uiMenu.png", 17f, 3f);
        uiLifeBarSprite = LoadAssets.loadSprite("sprites/lifeBar.png", 5f, 2f);
        uiQuestionSprite = LoadAssets.loadSprite("sprites/questionSelection.png", 8f, 4f);

        this.backgroundTexturePath = backgroundTexturePath;
        this.enemy = enemy;
        this.paradigmer = paradigmer;
        this.quiz = quiz;

        actualAnswer = 0;
        currentCharIndex = 0;

        currentLifeEnemy = enemy.getLife();
        currentLifeParadigmer = paradigmer.getLife();
        lifeDecayTimeEnemy = 0f;
        lifeDecayTimeParadigmer = 0f;
        lifeDecayRateParadigmer = 0.05f;
        lifeDecayRateEnemy = 0.03f;

        status = DRAWING_QUESTION;
        actualQuestion = quiz.getQuestion();

        String text = "Pause";
        ButtonAction action = () -> game.getScreenManager().showScreen(PAUSE_SCREEN);
        Button button = new Button(
            game,
            text,
            getWorldWidth() - 1f,
            getWorldHeight() - 1f,
            1.3f,
            1f,
            action
        );

        addButton(button);

        initializeButtons();

    }

    @Override
    public void draw(float delta) {
        //sprite inimigo
        enemy.getSprite().setPosition(worldWidth / 2f + 0.5f, worldHeight / 2f - 0.9f);
        enemy.getSprite().draw(game.getBatch());

        //sprite paradigmer
        paradigmer.getSprite().setPosition(worldWidth / 2f - 5.5f, worldHeight / 2f - 3.5f);
        paradigmer.getSprite().draw(game.getBatch());

        //barra de vida do inimigo
        drawEnemyLifeBar(delta);

        //Barra de vida do jogador
        drawPlayerLifeBar(delta);

        drawUiQuestion(delta);
    }

    private void drawEnemyLifeBar(float delta) {
        int color;
        if (currentLifeEnemy > 100) {
            color = 0;
        } else if (currentLifeEnemy > 50) {
            color = 2;
        } else {
            color = 1;
        }

        if (enemy.getLife() < currentLifeEnemy) {
            lifeDecayTimeEnemy += delta;
            if (lifeDecayTimeEnemy >= lifeDecayRateEnemy) {
                currentLifeEnemy = Math.max(enemy.getLife(), currentLifeEnemy - 1);
                lifeDecayTimeEnemy = 0f;
            }
        } else {
            currentLifeEnemy = enemy.getLife();
        }

        int nLife = Math.min(22, 22 * (int) currentLifeEnemy / 200);

        uiLifeBarSprite.setPosition(0f, worldHeight - 2f);
        uiLifeBarSprite.draw(game.getBatch());
        game.getFontHashMap().get(TEXT_BATTLE).draw(
            game.getBatch(),
            enemy.getName(),
            0.5f,
            worldHeight - 0.5f
        );
        float xOffset = .0f;
        for(int i = 0; i < nLife; i++, xOffset += 0.1f) {
            uiLifeBarChunkSprite[color].setPosition(2f + xOffset, worldHeight - 1.17f);
            uiLifeBarChunkSprite[color].draw(game.getBatch());
        }
    }

    private void drawPlayerLifeBar(float delta) {
        int color;
        if (currentLifeParadigmer > 50) {
            color = 0;
        } else if (currentLifeParadigmer > 25) {
            color = 2;
        } else {
            color = 1;
        }

        if (paradigmer.getLife() < currentLifeParadigmer) {
            lifeDecayTimeParadigmer += delta;
            if (lifeDecayTimeParadigmer >= lifeDecayRateParadigmer) {
                currentLifeParadigmer = Math.max(paradigmer.getLife(), currentLifeParadigmer - 1);
                lifeDecayTimeParadigmer = 0f;
            }
        } else {
            currentLifeParadigmer = paradigmer.getLife();
        }

        int nLife = Math.min(22, 22 * (int) currentLifeParadigmer / 100);

        uiLifeBarSprite.setPosition(worldWidth - 4.6f, 2.9f);
        uiLifeBarSprite.draw(game.getBatch());
        game.getFontHashMap().get(TEXT_BATTLE).draw(
            game.getBatch(),
            paradigmer.getName(),
            worldWidth - 4.1f,
            4.4f
        );
        String playLifeString = (int) currentLifeParadigmer + "/100";
        game.getFontHashMap().get(TEXT_BATTLE).draw(
            game.getBatch(),
            playLifeString,
            worldWidth - 2.5f,
            3.57f
        );

        float xOffset = .0f;
        for(int i = 0; i < nLife; i++, xOffset += 0.1f) {
            uiLifeBarChunkSprite[color].setPosition(worldWidth - 2.6f + xOffset, 3.73f);
            uiLifeBarChunkSprite[color].draw(game.getBatch());
        }
    }

    private void drawUiQuestion(float delta) {
        //caixa onde o texto fica
        uiTextSprite.setPosition(-0.4f, -0.4f);
        uiTextSprite.draw(game.getBatch());
        if (status == DRAWING_QUESTION || status == WAITING_ANSWER) {
            //caixa de respostas
            uiQuestionSprite.setPosition(worldWidth - 7.69f, -0.6f);
            uiQuestionSprite.draw(game.getBatch());
        }
        String textToDraw;
        switch (status) {
            case DRAWING_QUESTION:
                textToDraw = actualQuestion.getQuestion();
                break;
            case WAITING_ANSWER:
                char letter = (char) ('A' + actualAnswer);
                textToDraw = letter + ") " + actualQuestion.getAnswer(actualAnswer);
                break;
            case ANSWERED:
            case WAITING_NEXT_QUESTION:
                if (isCorrect) {
                    textToDraw = "Resposta correta! " + paradigmer.getConsecutiveHits() + " acertos consecutivos!";
                    break;
                }
                textToDraw = "Resposta errada!";
                break;
            default:
                textToDraw = "Erro desconhecido!";
                break;
        }
        textToDraw = breakText(textToDraw, delta);
        float x = 0.5f;
        float y = 2f;
        int alignment = Align.left;
        game.getFontHashMap().get(TEXT_QUESTION).draw(
            game.getBatch(),
            textToDraw,
            x,
            y,
            7.5f,
            alignment,
            true
        );
    }

    @Override
    public void logic(float delta) {
        switch (status) {
            case DRAWING_QUESTION:
                if (Gdx.input.isTouched() && !hasProcessedTouch) {
                    hasProcessedTouch = true;
                    status = WAITING_ANSWER;
                    currentCharIndex = 0;
                } else if (!Gdx.input.isTouched()) {
                    hasProcessedTouch = false;
                }
                break;

            case WAITING_ANSWER:
                if (Gdx.input.isTouched() && !hasProcessedTouch) {
                    hasProcessedTouch = true;
                    if (actualAnswer < 3) {
                        actualAnswer++;
                        currentCharIndex = 0;
                    } else {
                        status = DRAWING_QUESTION;
                        actualAnswer = 0;
                        currentCharIndex = actualQuestion.getQuestion().length();
                    }
                } else if (!Gdx.input.isTouched()) {
                    hasProcessedTouch = false;
                }
                break;

            case ANSWERED:
                currentCharIndex = 0;
                removeButtons(1); // Remove todos os botões de responder questão
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

                if (timePassed >= 3f || (Gdx.input.isTouched() && !hasProcessedTouch)) {
                    hasProcessedTouch = true;
                    actualQuestion = quiz.getQuestion();
                    initializeButtons();
                    currentCharIndex = 0;
                    actualAnswer = 0;
                    status = DRAWING_QUESTION;
                } else if (!Gdx.input.isTouched()) {
                    hasProcessedTouch = false;
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
        ScreenManager screenManager = game.getScreenManager();
        HashMap<ScreenType, SuperScreen> screens = screenManager.getScreens();
        screens.remove(END_SCREEN);

        backgroundMusic.stop();

        String backgroundMusicPath = "sounds/victoryMusic.mp3";
        EndGameScreen endGameScreen = new EndGameScreen(game, backgroundTexturePath, backgroundMusicPath, result);
        screens.put(END_SCREEN, endGameScreen);
        screenManager.showScreen(END_SCREEN);
    }

    @Override
    public void show() {
        super.show();
        backgroundMusic.play();
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
        uiQuestionSprite.getTexture().dispose();
        for (Sprite uiLifeBarChunkSprite : uiLifeBarChunkSprite) {
            uiLifeBarChunkSprite.getTexture().dispose();
        }
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
        String[] options = {"A)", "B)", "C)", "D)"};
        for (int i = 0; i < 2; i++) {
            float buttonDistanceX = 0;

            for (int j = 0; j < 2; j++) {
                //gambiarra para a lambda expression aceitar a variavel
                int option = cont;
                ButtonAction action = () -> questionAnswered(option);

                Button button = new Button(
                    game,
                    options[cont],
                    worldWidth - 7f + buttonDistanceX,
                    1.7f - buttonDistanceY,
                    3f,
                    1f,
                    action
                );

                addButton(button);
                buttonDistanceX += 3.7f;
                cont++;
            }
            buttonDistanceY += 1.5f;
        }
    }
}
