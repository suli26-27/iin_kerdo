package com.example.models;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class FileDataSource implements DataSource {

    String fileName = "tasks.csv";

    @Override
    public List<Task> readData() {
        try {
            return tryReadData();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            return null;
        }
    }

    private List<Task> tryReadData() throws FileNotFoundException {
        List<Task> taskList = new ArrayList<>();        
        File file = new File(fileName);
        try (Scanner scanner = new Scanner(file, "utf-8")) {
            scanner.nextLine();
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                taskList.add(makeTask(line));
            }
        }
        return taskList;
    }

    private Task makeTask(String line) {
        String[] parts = line.split(":");
        Task task = new Task();
        task.setId(parts[0]);
        task.setQuestion(parts[1]);
        task.setAnswer1(parts[2]);
        task.setAnswer2(parts[3]);
        task.setAnswer3(parts[4]);
        task.setAnswer4(parts[5]);
        task.setCorrect(parts[6]);
        return task;
    }

}
