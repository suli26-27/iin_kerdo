package com.example.models;

import java.util.List;

public class Answer {
    String name;
    List<Integer> answerList;

    public Answer() {
        name = "névtelen";
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public List<Integer> getAnswerList() {
        return answerList;
    }
    public void setAnswerList(List<Integer> answerList) {
        this.answerList = answerList;
    }

    
}
