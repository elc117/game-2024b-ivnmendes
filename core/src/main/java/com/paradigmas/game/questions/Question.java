package com.paradigmas.game.questions;

import com.badlogic.gdx.utils.Null;

public class Question {
    private String question;
    private String[] options;
    private int correctAnswer;

    public Question(String question, String[] options, int correctAnswer) {
        this.question = question;
        this.options = options;
        this.correctAnswer = correctAnswer;
    }

    public Question() {
        this.question = "";
        this.options = new String[4];
        this.correctAnswer = 1;
    }

    public boolean answerQuestion(int answer) {
        return answer == correctAnswer;
    }

    public String getAnswer(int answer) {
        if (answer >= 0 && answer < 4) {
            return options[answer];
        }

        return null;
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public String[] getOptions() {
        return options;
    }

    public void setAnswers(String[] answers) {
        this.options = answers;
    }

    public int getCorrectAnswer() {
        return correctAnswer;
    }

    public void setCorrectAnswer(int correctAnswer) {
        this.correctAnswer = correctAnswer;
    }
}
