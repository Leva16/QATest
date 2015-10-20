package com.qatest;

import java.util.Collections;
import java.util.List;
import java.util.Random;

public class Questions {

    private List<String> questions;
    private List<String[]> answers;
    private List<String> rigthAnswer;
    private static final long seed = System.nanoTime();

    Questions (List<String> questions, List<String[]> answers, List<String> rigthAnswer) {

        Collections.shuffle(questions, new Random(seed));
        Collections.shuffle(answers, new Random(seed));
        Collections.shuffle(rigthAnswer, new Random(seed));
        shuffleAnswer(answers);

        this.questions = questions;
        this.answers = answers;
        this.rigthAnswer = rigthAnswer;
    }

    public void shuffleAnswer(List<String[]> list) {
        int index;
        String temp;
        Random random = new Random();
        for (String[] s : list) {
            for ( int i = s.length - 1; i > 0; i--) {
                index = random.nextInt(i + 1);
                temp = s[index];
                s[index] = s[i];
                s[i] = temp;
            }
        }
    }


    public List<String> getQuestions() {
        return questions;
    }

    public List<String[]> getAnswers() {
        return answers;
    }

    public List<String> getRigthAnswer() {
        return rigthAnswer;
    }
}
