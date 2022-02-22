/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package duyvtt.cart;

import duyvtt.product.ProductDAO;
import duyvtt.product.ProductDTO;
import java.io.Serializable;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.naming.NamingException;

/**
 *
 * @author DELL
 */
public class CartObject implements Serializable {

    private Map<ProductDTO, Integer> items;

    public Map<ProductDTO, Integer> getItems() {
        return items;
    }

    public void addItemToCart(ProductDTO dto) {
        //1. Check item is existed
        if (dto == null) {
            return;
        }

        //2. when item is existed, checking existed items
        if (this.items == null) {
            this.items = new HashMap<>();
        }
        //3. items has existed, checking exiest id
        int quantity = 1;
        if (this.items.containsKey(dto)) {
            quantity = this.items.get(dto) + 1;
        }
        //4. Update item
        this.items.put(dto, quantity);
    }

    public void removeItemFromCart(ProductDTO dto) {
        //1. Check exist items
        if (this.items == null) {
            return;
        }
        //when items has existed, check existed id
        if (this.items.containsKey(dto)) {
            this.items.remove(dto);
            if (this.items.isEmpty()) {
                this.items = null;
            }
        }
    }

    public int getItemQuantity(ProductDTO dto) {
       
        if (dto == null){
            return 0;
        }
        //check existed this.items
        if (this.items == null) {
            return 0;
        }
        //check dto (with sku) existed in this.items
        if (this.items.containsKey(dto)) {
            return this.items.get(dto);
        }
        return 0;
    }

}
