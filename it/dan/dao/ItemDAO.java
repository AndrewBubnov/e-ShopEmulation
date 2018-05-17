package it.dan.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import it.dan.model.Item;

public class ItemDAO {

    public static void add(Item item) {
        if (get(item.getArticleId()) != null) System.out.println("Product with article " + item.getArticleId() + " already exists in DB");
        else {
            String sql = "INSERT INTO item(article_id, name, price) VALUES(?,?,?)";

            try (Connection connection = ConnectionToDB.getConnection();
                 PreparedStatement statement = connection.prepareStatement(sql);){
                statement.setString(1, item.getArticleId());
                statement.setString(2, item.getName());
                statement.setInt(3, item.getPrice());
                statement.executeUpdate();
                System.out.println("Article " + item.getArticleId() + " added to DB");
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public static Item get(String articleId){
        Item item = new Item();
        ResultSet rSet = null;

        String sql = "SELECT * FROM item WHERE article_id='"+articleId+"'";

        try (Connection connection = ConnectionToDB.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql);){
            rSet = statement.executeQuery();

            if (!rSet.next()) return null;
            else {
                while (rSet.next()) {
                    item.setName(rSet.getString("name"));
                    item.setPrice(rSet.getInt("price"));
                    item.setArticleId(rSet.getString("article_id"));
                }
            }
        }
        catch ( SQLException e )
        {
            e.printStackTrace();
        }
        return item;
    }

    public static void update(Item item){
        String sql = "UPDATE item SET name=?, price=? WHERE article_id=?";

        try (Connection connection = ConnectionToDB.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)){
            statement.setString(1, item.getName());
            statement.setInt(2, item.getPrice());
            statement.setString(3, item.getArticleId());

            statement.executeUpdate();
        }
        catch ( SQLException e )
        {
            e.printStackTrace();
        }
    }

    public static void delete(String articleId){

        String sql = "DELETE FROM item WHERE article_id=?";

        try (Connection connection = ConnectionToDB.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql);){
            statement.setString(1, articleId);
            statement.executeUpdate();
        }
        catch ( SQLException e )
        {
            e.printStackTrace();
        }
    }
}
