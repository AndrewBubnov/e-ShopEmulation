package it.dan.model;

import java.util.HashMap;
import java.util.Map;

        public class Order {
            private Integer orderId;
            private HashMap<String, Integer> cart;
            private String clientId;

        public void setCart(HashMap<String, Integer> cart) {
            this.cart = cart;
        }

        public Integer getOrderId(){

            return orderId;
        }

        public void setOrderId(Integer orderId){
            this.orderId = orderId;
        }

        public String getClientId(){
            return clientId;
        }

        public void setClientId(String clientId){
            this.clientId = clientId;
        }

    public HashMap<String, Integer> getCart() {
        return cart;
    }

    @Override
        public String toString(){
            String s = "";
            String result = "Order number: " + orderId + "\n" + "Client: " + clientId + "\n";
            for (Map.Entry<String, Integer> entry : cart.entrySet()) {
               s =  "Product article: \"" + entry.getKey() + "\"\t\t" + "Amount: " + entry.getValue();
               result = result + s + "\n";
            }
            return result;
        }
    }