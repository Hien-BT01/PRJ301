/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package shopping.product;

/**
 *
 * @author HP
 */
public class VegetableDTO {
    private String id;
    private String name;
    private String image;
    private float price;
    private int quantity;
    private String category;

    public VegetableDTO() {
    }

    public VegetableDTO(String id, String name, String image, float price, int quantity) {
        this.id = id;
        this.name = name;
        this.image = image;
        this.price = price;
        this.quantity = quantity;
    }

    public VegetableDTO(String name, String image, float price, int quantity, String category) {
        this.name = name;
        this.image = image;
        this.price = price;
        this.quantity = quantity;
        this.category = category;
    }

    public VegetableDTO(String id, String name, String image, float price, int quantity, String category) {
        this.id = id;
        this.name = name;
        this.image = image;
        this.price = price;
        this.quantity = quantity;
        this.category = category;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getImage() {
        return image;
    }

    public float getPrice() {
        return price;
    }

    public int getQuantity() {
        return quantity;
    }

    public String getCategory() {
        return category;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public void setCategory(String category) {
        this.category = category;
    }
    
}
