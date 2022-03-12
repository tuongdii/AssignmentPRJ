/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package duyvtt.orderDetail;
import java.io.Serializable;
import java.math.BigDecimal;
/**
 *
 * @author DELL
 */
public class OrderDetailDTO implements Serializable{
    private int id;
    private int ordersId;
    private String productId;
    private BigDecimal price;
    private int quantity;
    private BigDecimal total;

    public OrderDetailDTO(int id, int ordersId, String productId, BigDecimal price, int quantity, BigDecimal total) {
        this.id = id;
        this.ordersId = ordersId;
        this.productId = productId;
        this.price = price;
        this.quantity = quantity;
        this.total = total;
    }

    
    public OrderDetailDTO(String productId, BigDecimal price, int quantity, BigDecimal total) {
        this.productId = productId;
        this.price = price;
        this.quantity = quantity;
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
     * @return the ordersId
     */
    public int getOrdersId() {
        return ordersId;
    }

    /**
     * @param ordersId the ordersId to set
     */
    public void setOrdersId(int ordersId) {
        this.ordersId = ordersId;
    }

    /**
     * @return the productId
     */
    public String getProductId() {
        return productId;
    }

    /**
     * @param productId the productId to set
     */
    public void setProductId(String productId) {
        this.productId = productId;
    }

    /**
     * @return the price
     */
    public BigDecimal getPrice() {
        return price;
    }

    /**
     * @param price the price to set
     */
    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    /**
     * @return the quantity
     */
    public int getQuantity() {
        return quantity;
    }

    /**
     * @param quantity the quantity to set
     */
    public void setQuantity(int quantity) {
        this.quantity = quantity;
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
