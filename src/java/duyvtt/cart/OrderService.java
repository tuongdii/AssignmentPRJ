/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package duyvtt.cart;

import duyvtt.orderDetail.OrderDetailDAO;
import duyvtt.orderDetail.OrderDetailDTO;
import duyvtt.orders.OrdersDAO;
import duyvtt.utils.DBUtils;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import javax.naming.NamingException;

/**
 *
 * @author DELL
 */
public class OrderService {
    public boolean checkoutService(String fullname, List<OrderDetailDTO> orderDetailList) 
            throws SQLException, NamingException 
            {
        Connection con = null;
        try{
            con = DBUtils.makeConnection();
            if (con != null){
                //turn off auto commit
                con.setAutoCommit(false);
                
                //pass connection to OrdersDAO and OrderDetailDAO
                OrdersDAO orderDao = new OrdersDAO(con);
                OrderDetailDAO orderDetailDao = new OrderDetailDAO(con);
                
                //insert order
                int orderId = orderDao.insertOrder(fullname);
                if (orderId > 0){
                    //inser order detail
                    boolean result = orderDetailDao.insertOrderDetail(orderId, orderDetailList);
                    if(result){
                        con.commit();
                        return true;
                    }else{
                        con.rollback();
                    }
                }else{
                    con.rollback();
                }
            }
        }catch(SQLException ex){
            con.rollback();
            throw ex;
        }finally{
            if (con != null){
                con.close();
            }
        } 
        return false;
    }
}
