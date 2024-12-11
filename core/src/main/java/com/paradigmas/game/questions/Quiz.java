package com.paradigmas.game.questions;

import java.util.ArrayList;
import java.util.Random;

public class Quiz {
    private final ArrayList<Question> questions;

    public Quiz() {
        questions = new ArrayList<>();
    }

    public void addQuestion(Question question) {
        questions.add(question);
    }

    public Question getQuestion() {
        Random random = new Random();

        int index = random.nextInt(questions.size());
        Question question = questions.get(index);
        questions.remove(index);

        return question;
    }

    public ArrayList<Question> getQuestions() {
        return questions;
    }
}
