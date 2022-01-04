/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package shopping.product;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class CartDTO {

    private Map<String, VegetableDTO> cart;
    private float totalPrice;

    public CartDTO() {
        totalPrice = 0;
    }

    public CartDTO(Map<String, VegetableDTO> cart) {
        this.cart = cart;
    }

    public void setCart(Map<String, VegetableDTO> cart) {
        this.cart = cart;
    }

    public Map<String, VegetableDTO> getCart() {
        return cart;
    }

    public int getSize() {
        return cart.size();
    }

    public float getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(float totalPrice) {
        this.totalPrice = totalPrice;
    }

    public boolean add(VegetableDTO vegetable) {
        boolean check = false;
        try {
            if (this.cart == null) {
                this.cart = new HashMap<>();
            }
            if (this.cart.containsKey(vegetable.getId())) {
                int currentQuantity = this.cart.get(vegetable.getId()).getQuantity();
                vegetable.setQuantity(currentQuantity + vegetable.getQuantity());
            }
            this.cart.put(vegetable.getId(), vegetable);
            totalPrice += (vegetable.getPrice() * vegetable.getQuantity());
            check = true;
        } catch (Exception event) {
            event.printStackTrace();
        }
        return check;
    }

    public boolean delete(VegetableDTO product) {
        boolean check = false;
        try {
            if (this.cart != null) {
                if (this.cart.containsKey(product.getId())) {
                    totalPrice -= (product.getPrice() * product.getQuantity());
                    this.cart.remove(product.getId());
                    check = true;
                }
            }
        } catch (Exception event) {
            event.printStackTrace();
        }
        return check;
    }

    public Set<String> getKetSet() {
        return cart.keySet();
    }

    public boolean update(String id, VegetableDTO vegetable, int quantity) {
        boolean check = false;
        try {
            if (this.cart != null) {
                if (this.cart.containsKey(id)) {
                    if (vegetable.getQuantity() > quantity) {
                        totalPrice -= ((vegetable.getQuantity() - quantity) * vegetable.getPrice());
                    } else if (quantity > vegetable.getQuantity()) {
                        totalPrice += ((quantity - vegetable.getQuantity()) * vegetable.getPrice());
                    }
                    vegetable.setQuantity(quantity);
                    this.cart.replace(id, vegetable);
                    check = true;
                }
            }
        } catch (Exception event) {
            event.printStackTrace();
        }
        return check;
    }
}
