package com.example;

import java.sql.SQLException;
import java.util.Scanner;

import com.example.repository.TaskRepository;
import com.example.use_cases.CreateTask;
import com.example.use_cases.FindTask;
import com.example.use_cases.UpdateTask;

public class Instructions {
    private Scanner scanner;
    private TaskRepository taskRepository;

    public Instructions() throws SQLException {
        this.scanner = new Scanner(System.in);
        this.taskRepository = new TaskRepository();
    }

    public void execute() throws SQLException{
        int input = getInstruction();
        goToUseCase(input);
        askIfWantToContinueOrFinish();
    }

    private int getInstruction(){
        String instructions = 
            "O que deseja fazer?\n"+
            "1) Criar tarefa;\n"+
            "2) Atualizar tarefa;\n"+
            "3) Listar tarefas;\n";
        System.out.println(instructions);
        int input = scanner.nextInt();
        scanner.nextLine();
        return input;
    }

    private void goToUseCase(int input) throws SQLException {
        switch(input){
            case 1: {
                new CreateTask(scanner, taskRepository).execute();
                break;
                }
            case 2: {
                new UpdateTask(scanner, taskRepository).execute();
                break;
                }
            default: {
                new FindTask(scanner, taskRepository).execute();
                break;
            }
        }
    }

    private void askIfWantToContinueOrFinish() throws SQLException {
        System.out.println("Deseja continuar? (0 - não; 1 - sim)\n");
        int input = scanner.nextInt();
        scanner.nextLine();
        if (input == 1) {
            execute();
            return;
        }
        finish();
    }

    private void finish(){
        System.out.println("Até a próxima!\n");
        scanner.close();
    }
}
