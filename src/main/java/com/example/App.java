package com.example;

import java.sql.SQLException;

/**
 * Hello world!
 *
 */
public class App 
{

    public static void main( String[] args )
    {
        try {
        new Instructions().execute();
        } catch (SQLException e ){
            e.printStackTrace();
        }
    }
}
