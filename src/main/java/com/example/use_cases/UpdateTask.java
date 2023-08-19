package com.example.use_cases;

import java.sql.SQLException;
import java.util.Scanner;

import com.example.model.Task;
import com.example.model.Task.TaskStatusEnum;
import com.example.repository.TaskRepository;

public class UpdateTask {
    private Scanner scanner;
    private TaskRepository taskRepository;

    public UpdateTask(Scanner scanner, TaskRepository taskRepository){
        this.scanner = scanner;
        this.taskRepository = taskRepository;
    }


    public void execute() throws SQLException{
        int id = getId();
        Task task = getTaskById(id);
        boolean isForward = getIsForward();
        boolean movedSuccessfully = moveTask(isForward, task);
        if (!movedSuccessfully) return;
        taskRepository.updateStatus(id, task.getStatus());
    }

    private int getId(){
        System.out.println("Qual o id da atividade?\n");
        int input = scanner.nextInt();
        scanner.nextLine();
        return input;
    }


    private Task getTaskById(int id) throws SQLException{
        return taskRepository.findById(id);
    }

    private boolean getIsForward(){
        System.out.println("0 - Avançar ou 1 - Regredir?\n");
        int input = scanner.nextInt();
        scanner.nextLine();
        return input == 0;
    }

    private boolean moveTask(boolean isForward, Task task){
        TaskStatusEnum currentStatus = task.getStatus();
        if (isForward) task.moveForward();
        if (!isForward) task.moveBackward();
        return currentStatus != task.getStatus();
    }



}
