package com.paradigmas.game.questions;

import com.badlogic.gdx.utils.Null;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Question {
    private String question;
    private String[] answers;
    private int correctAnswer;

    public Question(String question, String[] answers, int correctAnswer) {
        this.question = question;
        this.answers = answers;
        this.correctAnswer = correctAnswer;
    }

    public boolean answerQuestion(int answer) {
        return answer == correctAnswer;
    }

    public String getAnswer(int answer) {
        if (answer >= 0 && answer < 4) {
            return answers[answer];
        }

        return null;
    }
}
