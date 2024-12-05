package com.paradigmas.game.questions;

import lombok.Getter;

import java.util.ArrayList;
import java.util.Random;

@Getter
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
}
