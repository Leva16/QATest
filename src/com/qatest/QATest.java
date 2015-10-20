package com.qatest;

import java.io.*;
import java.util.*;

public class QATest {
    public static void main(String argv[]) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
//        System.out.println("Enter a path to txt file: ");
//        Parsing parsing = new Parsing(br.readLine());
        Parsing parsing = new Parsing("/home/leva/Templates/CQ_Exam_Questions1.txt");
        parsing.getFileAndParse();

        Questions questions = new Questions(parsing.getQuestions(), parsing.getAnswers(), parsing.getRightAnswer());

        String[] va = new String[]{"A", "B", "C", "D"};
        int count = 1;
        int correct = 0;
        String ch;
        List<String> a = new ArrayList<>();
        List<String> r = new ArrayList<>();
        while (count != questions.getQuestions().size() + 1) {
//        while (count != 3) {
            System.out.println();
            System.out.println("Question " + count + ":");
            System.out.println(questions.getQuestions().get(count - 1));
            System.out.println();
            for (int i = 0; i < questions.getAnswers().get(count - 1).length; i++) {
                System.out.println(va[i] + ". " + questions.getAnswers().get(count - 1)[i]);
            }
            System.out.print("Enter answer: ");
            ch = br.readLine().toUpperCase();
            for (int i = 0; i < va.length; i++) {
                if (ch.equals(va[i])) {
                    if (questions.getAnswers().get(count - 1)[i].equals(questions.getRigthAnswer().get(count - 1))) {
                        correct++;
                        a.add(questions.getRigthAnswer().get(count - 1));
                        r.add(questions.getRigthAnswer().get(count - 1));
                    } else {
                        a.add(questions.getAnswers().get(count - 1)[i]);
                        r.add(questions.getRigthAnswer().get(count - 1));
                    }
                }
            }
            count++;
        }
        br.close();

        for (int i = 0; i < a.size(); i++) {
            System.out.println("--------------------------------------------");
            System.out.println("On a question: " + questions.getQuestions().get(i));
            System.out.println("Your answer: " + a.get(i));
            System.out.println("The right answer is: " + r.get(i));
            System.out.println();
        }
        if (correct > 70) {
            System.out.println("You have answered on " + correct + " questions.");
            System.out.println("Test passed.");
        } else {
            System.out.println("You have answered on " + correct + " questions.");
            System.out.println("Test NOT passed.");
        }
    }
}