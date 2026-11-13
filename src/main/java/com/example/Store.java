package com.example;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Store {

    public final static String fileName = "todo.txt";

    
    public static void writeTodos(List<String> todos) {
        try {
            tryWriteTodos(todos);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void tryWriteTodos(List<String> todos) throws IOException {
        try (FileWriter fileWriter = new FileWriter(fileName, StandardCharsets.UTF_8)) {
            for (String todo : todos) {
                fileWriter.write(todo + "\n");
            }
        }
    }

    public static List<String> readFile() {
        try {
            return tryReadFile();
        } catch (IOException e) {
            e.printStackTrace();
            return new ArrayList<>();
        }
    }

    private static List<String> tryReadFile() throws IOException {
        File file = new File(fileName);
        List<String> todoList = new ArrayList<>();
        
        
        if (!file.exists()) {
            return todoList;
        }

        try (Scanner scanner = new Scanner(file, StandardCharsets.UTF_8)) {
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                if (!line.trim().isEmpty()) {
                    todoList.add(line);
                }
            }
        }
        return todoList;
    }

    public static void appendTodoToFile(String todo) {
        try {
            tryAppendTodoToFile(todo);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void tryAppendTodoToFile(String todo) throws IOException {
        try (FileWriter writer = new FileWriter(fileName, StandardCharsets.UTF_8, true)) {
            writer.write(todo + "\n");
        }
    }
}
