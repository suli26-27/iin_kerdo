package com.example.controllers;

import java.util.ArrayList;
import java.util.List;

import com.example.models.Answer;
import com.example.models.FileDataSource;
import com.example.models.Task;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;

public class MainController {

    @FXML
    private RadioButton answer1Radio;

    @FXML
    private RadioButton answer2Radio;

    @FXML
    private RadioButton answer3Radio;

    @FXML
    private RadioButton answer4Radio;

    @FXML
    private ToggleGroup answers;

    @FXML
    private TextField idField;

    @FXML
    private TextField questionField;

    @FXML
    private TextField nameField;

    List<Task> taskList;
    int actualIndex = 0;
    List<Integer> answerList = new ArrayList<>();
    FileDataSource file = new FileDataSource();

    @FXML
    void initialize() {
        System.out.println("Betölt...");
        loadData();
        setDefaultState();
        answerList.add(1);
        answerList.add(1);
        answerList.add(1);
    }

    void loadData() {        
        taskList = file.readData();
        loadTask(0);
    }

    void loadTask(int index) {
        Task task = taskList.get(index);
        idField.setText(task.getId());
        questionField.setText(task.getQuestion());
        answer1Radio.setText(task.getAnswer1());
        answer2Radio.setText(task.getAnswer2());
        answer3Radio.setText(task.getAnswer3());
        answer4Radio.setText(task.getAnswer4());
    }

    @FXML
    void onClickNextButton(ActionEvent event) {
        startNextLoad();
    }

    void startNextLoad() {
        if(actualIndex < taskList.size() -1 ) {
            answerList.set(actualIndex, getSelectedNum());
            actualIndex++;
            loadTask(actualIndex);
            setActualState();
        }
    }

    void setDefaultState() {
        this.answer1Radio.setSelected(true);
    }

    @FXML
    void onClickPreviousButton(ActionEvent event) {
        if(actualIndex > 0) {
            answerList.set(actualIndex, getSelectedNum());
            actualIndex--;
            loadTask(actualIndex);
            setActualState();
        }
    }

    @FXML
    void onClickSaveButton(ActionEvent event) {
        startSave();
    }

    void startSave() {
        answerList.set(actualIndex, getSelectedNum());
        System.out.println(answerList);
        Answer answer = new Answer();
        if(!nameField.getText().isEmpty()) {
            answer.setName(nameField.getText());
        }
        
        answer.setAnswerList(answerList);
        file.writeAnswers(answer);        
    }

    int getSelectedNum() {
        int num = 0;
        if(answer1Radio.isSelected()) {
            num = 1;
        }else if(answer2Radio.isSelected()) {
            num = 2;
        }else if(answer3Radio.isSelected()) {
            num = 3;
        }else if(answer4Radio.isSelected()) {
            num = 4;
        }
        return num;
    }

    void setActualState() {
        int num = answerList.get(actualIndex);
        if( num == 1) {
            answer1Radio.setSelected(true);
        }else if(num == 2) {
            answer2Radio.setSelected(true);
        }else if(num == 3) {
            answer3Radio.setSelected(true);
        }else if(num == 4) {
            answer4Radio.setSelected(true);
        }
    }
}
