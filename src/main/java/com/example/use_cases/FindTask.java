package com.example.use_cases;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Scanner;

import com.example.model.Task;
import com.example.model.Task.TaskStatusEnum;
import com.example.repository.TaskRepository;

public class FindTask {
    private Scanner scanner;
    private TaskRepository taskRepository;

    public FindTask(Scanner scanner, TaskRepository taskRepository){
        this.scanner = scanner;
        this.taskRepository = taskRepository;
    }

    public void execute() throws SQLException {
        boolean isToFetchAll = getIsToFetchAll();
        if (isToFetchAll) {
            findAll();
            return;
        }
        TaskStatusEnum status = getStatus();
        findByStatus(status);
    }

    private boolean getIsToFetchAll(){
        String instruction = "O que deseja buscar?\n" +
                            "0 - Todas as atividades;\n" +
                            "1 - Atividades de uma categoria;"; 
        System.out.println(instruction);
        int input = scanner.nextInt();
        scanner.nextLine();
        if (input == 0)return true;
        return false;
    }

    private void findAll() throws SQLException {
        ArrayList<Task> tasks = taskRepository.findAll();
        printAll(tasks);
    }

    private TaskStatusEnum getStatus(){
        String instruction = "Qual status?\n" +
                            "0 - PARA FAZER;\n" +
                            "1 - TRABALHANDO;\n" +
                            "2 - ESPERANDO;\n" +
                            "3 - FEITO;\n";

        System.out.println(instruction);
        int input = scanner.nextInt();
        scanner.nextLine();
        return TaskStatusEnum.values()[input];
    }

    private void findByStatus(TaskStatusEnum status) throws SQLException {
        ArrayList<Task> tasks = taskRepository.findByStatus(status);
        printAll(tasks);
    }

    private void printAll(ArrayList<Task> tasks){
        for (Task task : tasks){
            System.out.println(task.toString()); 
        }
    }


}
