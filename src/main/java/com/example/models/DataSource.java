package com.example.models;

import java.util.List;

public interface DataSource {
    public List<Task> readData();
    public void writeAnswers(Answer answer);
}
