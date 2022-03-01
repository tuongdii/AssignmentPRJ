/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package duyvtt.orderDetail;

import java.io.Serializable;
import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

/**
 *
 * @author DELL
 */
public class OrderDetailDAO implements Serializable{
    Connection con;

    public OrderDetailDAO(Connection con) {
        this.con = con;
    }
    
    public boolean insertOrderDetail(int orderId, List<OrderDetailDTO> orderDetailList) 
            throws SQLException{
        PreparedStatement stm = null;
        int row = 0;
        try{
            if (con != null){
                String sql = "INSERT INTO OrderDetail (ordersId, productId, price, quantity, total) "
                        + "VALUES (?, ?, ?, ?, ?)";
                stm = con.prepareStatement(sql);
                stm.setInt(1, orderId);
                for (OrderDetailDTO dto : orderDetailList) {
                    stm.setString(2, dto.getProductId());
                    stm.setBigDecimal(3, dto.getPrice());
                    stm.setInt(4, dto.getQuantity());
                    stm.setBigDecimal(5, dto.getTotal());
                    row += stm.executeUpdate();
                }
                if(row == orderDetailList.size()){
                    return true;
                }
            }
        }finally{
            if(stm != null){
                stm.close();
            }
        }
        return false;
    }
}
