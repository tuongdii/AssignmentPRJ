/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package duyvtt.cart;

import java.io.Serializable;

/**
 *
 * @author DELL
 */
public class CartUpdateQuantityError implements Serializable{
    private String quantityInvalid;
    private String notEnoughQuantity;

    public CartUpdateQuantityError() {
    }

    /**
     * @return the quantityInvalid
     */
    public String getQuantityInvalid() {
        return quantityInvalid;
    }

    /**
     * @param quantityInvalid the quantityInvalid to set
     */
    public void setQuantityInvalid(String quantityInvalid) {
        this.quantityInvalid = quantityInvalid;
    }

    /**
     * @return the notEnoughQuantity
     */
    public String getNotEnoughQuantity() {
        return notEnoughQuantity;
    }

    /**
     * @param notEnoughQuantity the notEnoughQuantity to set
     */
    public void setNotEnoughQuantity(String notEnoughQuantity) {
        this.notEnoughQuantity = notEnoughQuantity;
    }
    
}
