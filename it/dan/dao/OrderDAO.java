package it.dan.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;
import it.dan.model.Order;

public class OrderDAO {

    public static void addExisting(Order order) {

        String sql = "INSERT INTO public.order (order_id, item_id, amount, client_id) VALUES (?,?,?,?)";

        try (Connection connection = ConnectionToDB.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            int orderNumber = 0;
            for (Map.Entry<String, Integer> entry : order.getCart().entrySet()) {
                orderNumber = lastPlaced() + 1;
                statement.setInt(1, orderNumber);
                statement.setString(2, entry.getKey());
                statement.setInt(3, entry.getValue());
                statement.setString(4, order.getClientId());
                statement.executeUpdate();
            }
            System.out.println("Order number " + orderNumber + " successfully placed.");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static int lastPlaced(){

        ResultSet rSet1 = null;

        String s = "SELECT * FROM public.order ORDER by order_id DESC LIMIT 1";
        int result = 0;
        try (Connection connection = ConnectionToDB.getConnection();
             PreparedStatement statement1 = connection.prepareStatement(s)){
            rSet1 = statement1.executeQuery();
            rSet1.next();
            result = rSet1.getInt("order_id");
        }
        catch ( SQLException e ){
            e.printStackTrace();
        }
        return result;
    }

    public static String get(Integer orderId){
        Order order = new Order();
        ResultSet rSet = null;

        if (orderId > lastPlaced()) return "Order number "+ orderId + " has not been placed yet";
        else {
            String sql = "SELECT * FROM public.order WHERE order_id='" + orderId + "'";

            try (Connection connection = ConnectionToDB.getConnection();
                 PreparedStatement statement = connection.prepareStatement(sql);){
                rSet = statement.executeQuery();
                while (rSet.next()) {
                    order.setOrderId(rSet.getInt("order_id"));
                    order.setClientId(rSet.getString("client_id"));
                    String item_id = rSet.getString("item_id");
                    Integer amount = rSet.getInt("amount");
                    HashMap<String, Integer> map = new HashMap<>();
                    map.put(item_id, amount);
                    order.setCart(map);
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
            return order.toString();
        }
    }

    public static void update(Order order){

        String sql = "UPDATE public.order SET item_id=?, amount=? client_id=? WHERE article_id=?";

        try (Connection connection = ConnectionToDB.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql);) {

            for (Map.Entry<String, Integer> entry : order.getCart().entrySet()) {
                statement.setString(2, entry.getKey());
                statement.setInt(3, entry.getValue());
                statement.setString(3, order.getClientId());
                statement.setInt(4, order.getOrderId());
                statement.executeUpdate();
            }
        }
        catch (SQLException e){
            e.printStackTrace();
        }
    }

    public static void delete(Integer orderId){

        String sql = "DELETE FROM public.order WHERE order_id=?";

        try (Connection connection = ConnectionToDB.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql);) {
            statement.setInt(1, orderId);
            statement.executeUpdate();
        }
        catch ( SQLException e ){
            e.printStackTrace();
        }
    }
}