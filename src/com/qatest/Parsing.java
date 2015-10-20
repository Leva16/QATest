package com.qatest;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Parsing  {

    private String pathToFile;
    private BufferedReader br;
    private StringBuilder sb = new StringBuilder();
    private List<String> qaArray = new ArrayList<>();
    private List<String> questions = new ArrayList<>();
    private List<String[]> answers = new ArrayList<>();
    private List<String> rightAnswer = new ArrayList<>();

    Parsing (String pathToFile) {
        this.pathToFile = pathToFile;
    }


    public void getFileAndParse() {
        try {
            br = new BufferedReader(new FileReader(pathToFile));
            String line = br.readLine();
            while (line != null) {
                sb.append(line).append("\n");
                line = br.readLine();
            }
            String file = sb.toString();
            file = file.substring(file.indexOf("\n") + 2);
            for (int i = 0; i < 80; i++) {
                String string = file.substring(file.indexOf("QUESTION"), file.indexOf("Answer") + 11);
                qaArray.add(string);
                file = file.substring(string.length());
            }
            String tmp;
            for (int i = 0; i < qaArray.size(); i++) {
                questions.add(qaArray.get(i).substring(qaArray.get(i).indexOf("QUESTION") + 11, qaArray.get(i).indexOf("A.  ")).trim());
                String[] answerArray = new String[4];
                answerArray[0] = qaArray.get(i).substring(qaArray.get(i).indexOf("A.  "), qaArray.get(i).indexOf("B.  ")).trim();
                answerArray[1] = qaArray.get(i).substring(qaArray.get(i).indexOf("B.  "), qaArray.get(i).indexOf("C.  ")).trim();
                answerArray[2] = qaArray.get(i).substring(qaArray.get(i).indexOf("C.  "), qaArray.get(i).indexOf("D.  ")).trim();
                answerArray[3] = qaArray.get(i).substring(qaArray.get(i).indexOf("D.  "), qaArray.get(i).indexOf("Answer")).trim();
                tmp = qaArray.get(i).substring(qaArray.get(i).indexOf("Answer"), qaArray.get(i).indexOf("Answer") + 9);
                tmp = tmp.substring(tmp.length() - 1).trim();

                for (int j = 0; j < answerArray.length; j++) {
                    if (answerArray[j].charAt(0) == tmp.charAt(0)) {
                        tmp = answerArray[j];
                    }
                }

                answerArray[0] = qaArray.get(i).substring(qaArray.get(i).indexOf("A.  ") + 2, qaArray.get(i).indexOf("B.  ")).trim();
                answerArray[1] = qaArray.get(i).substring(qaArray.get(i).indexOf("B.  ") + 2, qaArray.get(i).indexOf("C.  ")).trim();
                answerArray[2] = qaArray.get(i).substring(qaArray.get(i).indexOf("C.  ") + 2, qaArray.get(i).indexOf("D.  ")).trim();
                answerArray[3] = qaArray.get(i).substring(qaArray.get(i).indexOf("D.  ") + 2, qaArray.get(i).indexOf("Answer")).trim();

                answers.add(answerArray);
                rightAnswer.add(tmp.substring(tmp.indexOf(" ") + 2).trim());
            }


        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            br.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public List<String> getQuestions() {
        return questions;
    }

    public List<String[]> getAnswers() {
        return answers;
    }

    public List<String> getRightAnswer() {
        return rightAnswer;
    }
}