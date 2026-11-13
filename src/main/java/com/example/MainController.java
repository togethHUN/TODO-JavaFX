package com.example;

import java.util.List;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextArea;

public class MainController {

    @FXML
    private TextArea editField;

    @FXML
    private ListView<String> todoList;

    @FXML
    private Label statusLabel;

    @FXML 
    void initialize() {
        List<String> todos = Store.readFile();
        if (todos != null) {
            todoList.getItems().addAll(todos);
        }

        
        todoList.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue != null) {
                editField.setText(newValue);
            }
        });
    }

    @FXML
    void onClickAddButton(ActionEvent event) {
        
        String newTodo = editField.getText().trim();
        
        if (!newTodo.isEmpty()) {
            
            newTodo = newTodo.replace("\n", " ");
            todoList.getItems().add(newTodo);
            Store.appendTodoToFile(newTodo);
            editField.clear();
            statusLabel.setText("");
        }else{
            statusLabel.setText("Üres a szerkesztő mező");
        }
    }

    @FXML
    void onClickModifyButton(ActionEvent event) {
        int selectedIndex = todoList.getSelectionModel().getSelectedIndex();
        String modifiedTodo = editField.getText().trim();

        
        if (selectedIndex != -1 && !modifiedTodo.isEmpty()) {
            modifiedTodo = modifiedTodo.replace("\n", " ");
            todoList.getItems().set(selectedIndex, modifiedTodo);
            Store.writeTodos(todoList.getItems());
            editField.clear();
            todoList.getSelectionModel().clearSelection();
        }
    }

    @FXML
    void onClickDelButton(ActionEvent event) {
        int selectedIndex = todoList.getSelectionModel().getSelectedIndex();

        
        if (selectedIndex != -1) {
            todoList.getItems().remove(selectedIndex);
            Store.writeTodos(todoList.getItems());
            editField.clear();
            todoList.getSelectionModel().clearSelection();
        }


    }
}
