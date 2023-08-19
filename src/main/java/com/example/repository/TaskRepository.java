package com.example.repository;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import com.example.model.Task;
import com.example.model.Task.TaskStatusEnum;

public class TaskRepository  implements AutoCloseable{
    private String url = "jdbc:mysql://localhost:3306/KANBAN";
    private String user = "root";    
    private String password = "adminadmin";

    private Connection connection;

    public TaskRepository() throws SQLException{
        this.connection = DriverManager.getConnection(url, user, password);
    }

    public void create(Task task) throws SQLException{
        String query = "INSERT INTO Task (title, status) VALUES (?, ?)";
    
        PreparedStatement preparedStatement = connection.prepareStatement(query);
        preparedStatement.setString(1, task.getTitle());
        preparedStatement.setString(2, task.getStatus().toString());

        int result = preparedStatement.executeUpdate();
        if (result > 0) System.out.println("Task criada com sucesso!");
    }

    public void updateStatus(int id, TaskStatusEnum status) throws SQLException{
        String query = "UPDATE Task SET status = ? WHERE id = ?";

        PreparedStatement preparedStatement = connection.prepareStatement(query);
        preparedStatement.setString(1, status.toString());
        preparedStatement.setInt(2, id);

        int result = preparedStatement.executeUpdate();
        if (result > 0) System.out.println("Task atualizada com sucesso!");
    }

    public Task findById(int id) throws SQLException {
        String query = "SELECT * FROM Task WHERE id = ?";

        PreparedStatement preparedStatement = connection.prepareStatement(query);
        
        preparedStatement.setInt(1, id);

        ResultSet resultSet = preparedStatement.executeQuery();
        if (!resultSet.next()) return null;
        Task task = new Task();
        task.setId(resultSet.getInt("id"));
        task.setTitle(resultSet.getString("title"));
        task.setStatus(TaskStatusEnum.valueOf(resultSet.getString("status")));

        preparedStatement.close();

        return task;
    }

    public ArrayList<Task> findAll() throws SQLException {
        String query = "SELECT * FROM Task";
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery(query);
        return convertToArrayList(resultSet);
    } 

    public ArrayList<Task> findByStatus(TaskStatusEnum status) throws SQLException {
        String query =  "SELECT * FROM Task WHERE status = ?";
        PreparedStatement preparedStatement = connection.prepareStatement(query);
        preparedStatement.setString(1, status.toString());
        ResultSet resultSet = preparedStatement.executeQuery();
        return convertToArrayList(resultSet);
    }

    private ArrayList<Task> convertToArrayList(ResultSet resultSet) throws SQLException {
        ArrayList<Task> result = new ArrayList<>();

        while(resultSet.next()){
            Task task = new Task();
            task.setId(resultSet.getInt("id"));
            task.setTitle(resultSet.getString("title"));
            task.setStatus(TaskStatusEnum.valueOf(resultSet.getString("status")));
            result.add(task);
        }

        return result;
    }

    public void close() throws SQLException{
        connection.close();
    }
}
