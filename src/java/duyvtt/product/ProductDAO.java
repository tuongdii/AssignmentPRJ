/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package duyvtt.product;

import duyvtt.utils.DBUtils;
import java.io.Serializable;
import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.naming.NamingException;

/**
 *
 * @author DELL
 */
public class ProductDAO implements Serializable{

    public List<ProductDTO> getProductList()
            throws SQLException, NamingException {
        Connection con = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        List<ProductDTO> listProduct = null;
        try {
            con = DBUtils.makeConnection();
            if (con != null) {
                String sql = "SELECT id, name, price, description, quantity "
                        + "FROM Product";
                stm = con.prepareStatement(sql);
                rs = stm.executeQuery();
                while (rs.next()) {
                    String id = rs.getString("id");
                    String name = rs.getNString("name");
                    BigDecimal price = rs.getBigDecimal("price");
                    String description = rs.getNString("description");
                    int quantity = rs.getInt("quantity");

                    ProductDTO dto = new ProductDTO(id, name, price, description, quantity);
                    if (listProduct == null) {
                        listProduct = new ArrayList<>();
                    }
                    listProduct.add(dto);
                }
            }
        } finally {
            if (rs != null) {
                rs.close();
            }
            if (stm != null) {
                stm.close();
            }
            if (con != null) {
                con.close();
            }
        }
        return listProduct;
    }
    public ProductDTO getProductByID(String id) 
            throws SQLException, NamingException{
        Connection con = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        try{
            con = DBUtils.makeConnection();
            if (con != null){
                String sql = "SELECT name, price, description, quantity "
                        + "FROM Product "
                        + "WHERE id = ?";
                stm = con.prepareStatement(sql);
                stm.setString(1, id);
                rs = stm.executeQuery();
                if (rs.next()){
                    String name = rs.getNString("name");
                    BigDecimal price = rs.getBigDecimal("price");
                    String description = rs.getNString("description");
                    int quantity = rs.getInt("quantity");
                    ProductDTO dto = new ProductDTO(id, name, price, description, quantity);
                    return dto;
                }
            }
        }finally{
            if (rs != null){
                rs.close();
            }
            if (stm != null){
                stm.close();
            }
            if (con != null){
                con.close();
            }
        }
        return null;
    }
}
