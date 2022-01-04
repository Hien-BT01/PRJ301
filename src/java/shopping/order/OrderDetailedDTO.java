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
public class OrderDetailedDTO {

    private String detailID;
    private String orderID;
    private String productID;
    private float price;
    private int quantity;

    public OrderDetailedDTO(String detailID, String orderID, String productID, float price, int quantity) {
        this.detailID = detailID;
        this.orderID = orderID;
        this.productID = productID;
        this.price = price;
        this.quantity = quantity;
    }

    public String getDetailID() {
        return detailID;
    }

    public String getOrderID() {
        return orderID;
    }

    public String getProductID() {
        return productID;
    }

    public float getPrice() {
        return price;
    }

    public int getQuantity() {
        return quantity;
    }

}
