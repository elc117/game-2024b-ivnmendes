package com.paradigmas.game.ui;

import com.paradigmas.game.questions.Question;
import com.paradigmas.game.questions.Quiz;

public abstract class QuizGenerator {
    //TODO: Suporte para gerador automatico a partir de arquivo

    public static Quiz quizGenerate(int option) {
        Quiz quiz = new Quiz();
        switch (option) {
            case 0:
                quiz.addQuestion(new Question("abcd2", new String[]{"a", "b", "c", "d"}, 2));
                quiz.addQuestion(new Question("abcad1", new String[]{"a", "b", "c", "d"}, 1));
                quiz.addQuestion(new Question("abcsdfd3", new String[]{"a", "b", "c", "d"}, 3));
                quiz.addQuestion(new Question("abcgfd2", new String[]{"a", "b", "c", "d"}, 2));
                quiz.addQuestion(new Question("abcrewd0", new String[]{"a", "b", "c", "d"}, 0));
                quiz.addQuestion(new Question("abcdgfds2", new String[]{"a", "b", "c", "d"}, 2));
                break;
            case 1:
                quiz.addQuestion(new Question("abcd2", new String[]{"a", "b", "c", "d"}, 2));
                quiz.addQuestion(new Question("abscad1", new String[]{"a", "b", "c", "d"}, 1));
                quiz.addQuestion(new Question("abcsdfd3", new String[]{"a", "b", "c", "d"}, 3));
                quiz.addQuestion(new Question("abcgfd2", new String[]{"a", "b", "c", "d"}, 2));
                quiz.addQuestion(new Question("abcrewd0", new String[]{"a", "b", "c", "d"}, 0));
                quiz.addQuestion(new Question("abcdgfds2", new String[]{"a", "b", "c", "d"}, 2));
                break;
            case 2:
                quiz.addQuestion(new Question("abcd2", new String[]{"a", "b", "c", "d"}, 2));
                quiz.addQuestion(new Question("abcad1", new String[]{"a", "b", "c", "d"}, 1));
                quiz.addQuestion(new Question("abcsdfd3", new String[]{"a", "b", "c", "d"}, 3));
                quiz.addQuestion(new Question("abcgfd2", new String[]{"a", "b", "c", "d"}, 2));
                quiz.addQuestion(new Question("abcreawd0", new String[]{"a", "b", "c", "d"}, 0));
                quiz.addQuestion(new Question("abcdgfds2", new String[]{"a", "b", "c", "d"}, 2));
                break;
            default:
                throw new RuntimeException("Opção de fase inválida");
        }

        return quiz;
    }
}
