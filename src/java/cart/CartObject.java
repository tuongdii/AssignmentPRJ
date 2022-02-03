/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cart;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author DELL
 */
public class CartObject implements Serializable{
    private Map<String, Integer> items;

    public Map<String, Integer> getItems() {
        return items;
    }

    
    public void addItemToCart (String id){
        //1. Check item's id is existed
        if (id == null){
            return;
        }
        if(id.trim().isEmpty()){
            return;
        }
        //2. when item is existed, checking existed items
        if(this.items == null){
            this.items = new HashMap<>();
        }
        //3. items has existed, checking exiest id
        int quantity = 1;
        if(this.items.containsKey(id)){
            quantity = this.items.get(id) + 1;
        }
        //4. Update item
        this.items.put(id, quantity);
    }
    
    public void removeItemFromCart(String id){
        //1. Check exist items
        if(this.items == null){
            return;
        }
        //when items has existed, check existed id
        if(this.items.containsKey(id)){
            this.items.remove(id);
            if (this.items.isEmpty()){
                this.items = null;
            }
        }
    }
}
