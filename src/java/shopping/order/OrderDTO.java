/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package shopping.order;

/**
 *
 * @author HP
 */
public class OrderDTO {
    private String orderID;
    private String userName;
    private String date;
    private float total;

    public OrderDTO(String orderID, String userName, String date, float total) {
        this.orderID = orderID;
        this.userName = userName;
        this.date = date;
        this.total = total;
    }

    public String getOrderID() {
        return orderID;
    }

    public String getUserName() {
        return userName;
    }

    public String getDate() {
        return date;
    }

    public float getTotal() {
        return total;
    }
     
}
