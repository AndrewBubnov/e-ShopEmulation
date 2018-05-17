package it.dan.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import it.dan.model.Client;

public class ClientDAO {

    public static void save(Client client){

        String sql = "INSERT INTO client(login, password, first_name, second_name) VALUES(?,?,?,?)";

        try (Connection connection = ConnectionToDB.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, client.getLogin());
            statement.setString(3, client.getFirstName());
            statement.setString(2, client.getPassword());
            statement.setString(4, client.getSecondName());

            statement.executeUpdate();
        }
        catch ( SQLException e ){
            e.printStackTrace();
        }
    }

    public static boolean isClient(String clientId){
        ResultSet rSet = null;
        boolean f = true;
        try (Connection connection = ConnectionToDB.getConnection();
             PreparedStatement statement = connection.prepareStatement("SELECT * FROM client WHERE login='"+clientId+"'")){
            rSet = statement.executeQuery();
            if (!rSet.next()) f = false;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return f;
    }

    public static Client get(String login){
        Client client = new Client();
        ResultSet rSet = null;

        String sql = "SELECT * FROM client WHERE login='"+login+"'";

        try (Connection connection = ConnectionToDB.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql);){
            rSet = statement.executeQuery();
            while(rSet.next()){
                client.setLogin(rSet.getString("login"));
                client.setPassword(rSet.getString(2));
                client.setFirstName(rSet.getString("first_name"));
                client.setSecondName(rSet.getString(4));
            }
        }
        catch ( SQLException e ){
            e.printStackTrace();
        }
        return client;
    }

    public static void update(Client client){

        String sql = "UPDATE client SET password=?, first_name=?, second_name=? WHERE login=?";

        try (Connection connection = ConnectionToDB.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql);){
            statement.setString(4, client.getLogin());
            statement.setString(2, client.getFirstName());
            statement.setString(1, client.getPassword());
            statement.setString(3, client.getSecondName());

            statement.executeUpdate();
        }
        catch (SQLException e){
            e.printStackTrace();
        }
    }

    public static void delete(String login){

        String sql = "DELETE FROM client WHERE login=?";

        try (Connection connection = ConnectionToDB.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql);){
            statement.setString(1, login);
            statement.executeUpdate();
        }
        catch (SQLException e){
            e.printStackTrace();
        }
    }
}