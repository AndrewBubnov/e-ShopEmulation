package it.dan;

import it.dan.dao.ClientDAO;
import it.dan.dao.OrderDAO;
import it.dan.model.Client;
import it.dan.model.Order;
import java.util.HashMap;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        Order order = new Order();
        Scanner scanner = new Scanner(System.in);
        System.out.println("Please enter the client login:");
        order.setClientId(scanner.nextLine());
        order.setCart(cart());
        order.setOrderId(OrderDAO.lastPlaced() + 1);
        add(order);
        System.out.println(order);
    }

    public static void add(Order order){
        if (ClientDAO.isClient(order.getClientId())) OrderDAO.addExisting(order);
        else {
            Client client = new Client();
            System.out.println("You need to register a new customer first");
            System.out.println("Please enter password for registry:");
            Scanner scanner = new Scanner(System.in);
            String password = "";
            while(true) {
                password = scanner.nextLine();
                System.out.println("Please enter password one more time:");
                String password1 = scanner.nextLine();
                if (password.equals(password1))break;
                System.out.println("Entered passwords do not match. Please enter password for registry:");
            }
            System.out.println("Please enter first name:");
            String firstName = scanner.nextLine();
            if (firstName.equals("")) firstName = "John";
            System.out.println("Please enter second name:");
            String secondName = scanner.nextLine();
            if (secondName.equals("")) secondName = "Doe";
            client.setLogin(order.getClientId());
            client.setPassword(password);
            client.setFirstName(firstName);
            client.setSecondName(secondName);
            ClientDAO.save(client);
            OrderDAO.addExisting(order);
        }
    }


    public static HashMap<String, Integer> cart(){
        HashMap<String, Integer> map = new HashMap<>();
        Scanner scanner = new Scanner(System.in);
        System.out.println("Please enter the common number of product items you need:");
        int number = scanner.nextInt();
        for (int i = 0; i < number; i++) {
            String item_id;
            while (true) {
                System.out.println("Please enter a " + (i + 1) + " item id:");
                String s = scanner.next();
                if (s.equals("VU0101") || s.equals("VU0102")){
                    item_id = s;
                    break;
                }
                System.out.println("Unfortunatelly an incorrect item id was entered");
            }
            System.out.println("Please enter an amount of the item:");
            Integer amount = scanner.nextInt();
            map.put(item_id, amount);
        }
        return map;
    }
}
