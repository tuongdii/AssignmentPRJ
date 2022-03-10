/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package duyvtt.cart;

/**
 *
 * @author DELL
 */
public class NotEnoughQuantityException extends Exception{

    public NotEnoughQuantityException() {
    }

    public NotEnoughQuantityException(String message) {
        super(message);
    }
    
}
