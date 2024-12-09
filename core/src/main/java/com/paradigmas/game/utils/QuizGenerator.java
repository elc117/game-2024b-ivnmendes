package com.paradigmas.game.utils;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;
import com.google.gson.Gson;
import com.paradigmas.game.questions.Quiz;

public abstract class QuizGenerator {
    public static Quiz quizGenerate(int option) {
        Gson gson = new Gson();

        String path = "questions/quiz-" + option + ".json";

        FileHandle fileHandle = Gdx.files.internal(path);
        if (!fileHandle.exists()) {
            throw new RuntimeException("Arquivo não encontrado: " + path);
        }

        String json = fileHandle.readString();
        return gson.fromJson(json, Quiz.class);
    }
}
