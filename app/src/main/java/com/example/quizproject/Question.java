package com.example.quizproject;

public class Question {

    private int qNumber;
    private String question;
    private String answer;
    private String option1;
    private String option2;
    private String option3;
    private String option4;
    private String[] answerArray;
    private String typeQuestion;

    public Question(int qNumber, String question, String answer, String option1, String option2, String option3, String option4, String typeQuestion) {
        this.qNumber = qNumber;
        this.question = question;
        this.answer = answer;
        this.option1 = option1;
        this.option2 = option2;
        this.option3 = option3;
        this.option4 = option4;
        this.typeQuestion = typeQuestion;
    }
    //make constructor with array answer.

    public Question(int qNumber, String question, String option1, String option2, String option3, String option4, String typeQuestion) {
        this.qNumber = qNumber;
        this.question = question;
        this.option1 = option1;
        this.option2 = option2;
        this.option3 = option3;
        this.option4 = option4;
        this.typeQuestion = typeQuestion;
    }

    public String[] getAnswerArray() {
        return answerArray;
    }

    public void setAnswerArray(String[] answerArray) {
        this.answerArray = answerArray;
    }

    public Question(int qNumber, String question, String answer, String typeQuestion) {
        this.qNumber = qNumber;
        this.question = question;
        this.answer = answer;
        this.typeQuestion = typeQuestion;
    }

    public Question(int qNumber, String question, String answer, String option1, String option2, String typeQuestion) {
        this.qNumber = qNumber;
        this.question = question;
        this.answer = answer;
        this.option1 = option1;
        this.option2 = option2;
        this.typeQuestion = typeQuestion;
    }

    public int getqNumber() {
        return qNumber;
    }

    public void setqNumber(int qNumber) {
        this.qNumber = qNumber;
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }

    public String getOption1() {
        return option1;
    }

    public void setOption1(String option1) {
        this.option1 = option1;
    }

    public String getOption2() {
        return option2;
    }

    public void setOption2(String option2) {
        this.option2 = option2;
    }

    public String getOption3() {
        return option3;
    }

    public void setOption3(String option3) {
        this.option3 = option3;
    }

    public String getOption4() {
        return option4;
    }

    public void setOption4(String option4) {
        this.option4 = option4;
    }

    public String getTypeQuestion() {
        return typeQuestion;
    }

    public void setTypeQuestion(String typeQuestion) {
        this.typeQuestion = typeQuestion;
    }

    @Override
    public String toString() {
        return "Question{" +
                "qNumber=" + qNumber +
                ", question='" + question + '\'' +
                ", answer='" + answer + '\'' +
                ", option1='" + option1 + '\'' +
                ", option2='" + option2 + '\'' +
                ", option3='" + option3 + '\'' +
                ", option4='" + option4 + '\'' +
                ", typeQuestion='" + typeQuestion + '\'' +
                '}';
    }
}
