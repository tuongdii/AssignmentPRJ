/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package duyvtt.orders;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 *
 * @author DELL
 */
public class OrdersDTO implements Serializable{
    private int id;
    private String fullname;
    private BigDecimal total;

    public OrdersDTO(int id, String fullname, BigDecimal total) {
        this.id = id;
        this.fullname = fullname;
        this.total = total;
    }

    public OrdersDTO(String fullname, BigDecimal total) {
        this.fullname = fullname;
        this.total = total;
    }

    /**
     * @return the id
     */
    public int getId() {
        return id;
    }

    /**
     * @param id the id to set
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * @return the fullname
     */
    public String getFullname() {
        return fullname;
    }

    /**
     * @param fullname the fullname to set
     */
    public void setFullname(String fullname) {
        this.fullname = fullname;
    }

    /**
     * @return the total
     */
    public BigDecimal getTotal() {
        return total;
    }

    /**
     * @param total the total to set
     */
    public void setTotal(BigDecimal total) {
        this.total = total;
    }
    
}
