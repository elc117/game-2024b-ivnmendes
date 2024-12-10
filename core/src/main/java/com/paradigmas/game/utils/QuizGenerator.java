package com.paradigmas.game.utils;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.utils.Json;
import com.badlogic.gdx.utils.JsonReader;
import com.badlogic.gdx.utils.JsonValue;
import com.paradigmas.game.questions.Question;
import com.paradigmas.game.questions.Quiz;

public abstract class QuizGenerator {
    public static Quiz quizGenerate(int option) {
        String path = "questions/quiz-" + option + ".json";
        Json json = new Json();

        FileHandle fileHandle = Gdx.files.internal(path);
        if (!fileHandle.exists()) {
            throw new RuntimeException("Arquivo não encontrado: " + path);
        }

        String jsonString = fileHandle.readString();

        JsonReader jsonReader = new JsonReader();
        JsonValue jsonValue = jsonReader.parse(jsonString);
        Quiz quiz = new Quiz();

        JsonValue questions = jsonValue.get("questions");
        for (JsonValue question : questions) {
            String questionText = question.getString("question");
            JsonValue optionsJson = question.get("options");
            String[] options = new String[optionsJson.size];
            for (int i = 0; i < optionsJson.size; i++) {
                options[i] = optionsJson.getString(i);
            }
            String correctAnswer = question.getString("correctAnswer");

            quiz.addQuestion(new Question(questionText, options, Integer.parseInt(correctAnswer)));
        }

        return quiz;
    }
}
