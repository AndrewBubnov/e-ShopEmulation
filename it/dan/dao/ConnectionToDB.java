package it.dan.dao;

import java.sql.*;

public class ConnectionToDB {
    private static final String DB_URL = "jdbc:postgresql://localhost:5432/e-shop";
    private static final String USERNAME = "postgres";
    private static final String USER_PASS = "acnot_0175";

    protected static Connection getConnection(){

        Connection connection = null;

        try
        {
            connection = DriverManager.getConnection(DB_URL, USERNAME, USER_PASS);
        }
        catch ( SQLException e )
        {
            e.printStackTrace();
        }

        return connection;
    }

    protected static void closeConnection(PreparedStatement statement, Connection connection){
        try
        {
            statement.close();
        }
        catch ( SQLException e )
        {
            e.printStackTrace();
        }

        try
        {
            connection.close();
        }
        catch ( SQLException e )
        {
            e.printStackTrace();
        }
    }

    public static void closeConnection(ResultSet rSet, PreparedStatement statement, Connection connection){
        try
        {
            rSet.close();
        }
        catch ( SQLException e )
        {
            e.printStackTrace();
        }

        try
        {
            statement.close();
        }
        catch ( SQLException e )
        {
            e.printStackTrace();
        }

        try
        {
            connection.close();
        }
        catch ( SQLException e )
        {
            e.printStackTrace();
        }
    }
}