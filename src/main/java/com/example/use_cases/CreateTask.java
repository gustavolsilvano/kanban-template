package com.example.use_cases;

import java.sql.SQLException;
import java.util.Scanner;

import com.example.model.Task;
import com.example.model.Task.TaskStatusEnum;
import com.example.repository.TaskRepository;

public class CreateTask {
    private Scanner scanner;
    private TaskRepository taskRepository;

    public CreateTask(Scanner scanner, TaskRepository taskRepository){
        this.scanner = scanner;
        this.taskRepository = taskRepository;
    }

    public void execute() throws SQLException {
        String title = getTitle();
        create(title);
    }

    private String getTitle(){
        System.out.println("Qual o título da atividade?\n");
        return scanner.nextLine();
    }

    private void create(String title) throws SQLException{
        Task task = new Task();
        task.setStatus(TaskStatusEnum.PARA_FAZER);
        task.setTitle(title);
        taskRepository.create(task);
    }
}
