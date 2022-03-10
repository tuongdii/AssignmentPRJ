/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package duyvtt.orders;

import java.io.Serializable;

/**
 *
 * @author DELL
 */
public class OrdersInsertError implements Serializable{
    private String fullNameLengthErr;
    private String notEnoughQuantityErr;

    public OrdersInsertError() {
    }

    /**
     * @return the fullNameLengthErr
     */
    public String getFullNameLengthErr() {
        return fullNameLengthErr;
    }

    /**
     * @param fullNameLengthErr the fullNameLengthErr to set
     */
    public void setFullNameLengthErr(String fullNameLengthErr) {
        this.fullNameLengthErr = fullNameLengthErr;
    }

    /**
     * @return the notEnoughQuantityErr
     */
    public String getNotEnoughQuantityErr() {
        return notEnoughQuantityErr;
    }

    /**
     * @param notEnoughQuantityErr the notEnoughQuantityErr to set
     */
    public void setNotEnoughQuantityErr(String notEnoughQuantityErr) {
        this.notEnoughQuantityErr = notEnoughQuantityErr;
    }
    
    
    
}
