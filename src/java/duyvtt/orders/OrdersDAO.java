/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package duyvtt.orders;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author DELL
 */
public class OrdersDAO implements Serializable{
    Connection con;
    public OrdersDAO(Connection con) {
      this.con = con;
    }
    

    public int insertOrder(String fullname) throws SQLException {
        PreparedStatement stm = null;
        ResultSet rs = null;
        try {
            if (con != null) {
            String sql = "INSERT INTO Orders(fullname, total) "
                    + "OUTPUT INSERTED.id "
                    + "VALUES (?, ?)";
            stm = con.prepareStatement(sql);
            stm.setNString(1, fullname);
            stm.setInt(2, 0);
            rs = stm.executeQuery();
            if(rs.next()){
                int id = rs.getInt("id");
                return id;
            }
            }
        } finally {
            if (rs != null) {
                rs.close();
            }
            if (stm != null) {
                stm.close();
            }
        }
        return -1;
    }
}
