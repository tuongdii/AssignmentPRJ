/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package duyvtt.registration;

import duyvtt.utils.DBUtils;
import java.io.Serializable;
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
public class RegistrationDAO implements Serializable {

    public RegistrationDTO checkLogin(String username, String password)
            throws SQLException, NamingException {
        Connection con = null;
        PreparedStatement stm = null;
        ResultSet rs = null;

        try {
            //1. Connect DB
            con = DBUtils.makeConnection();
            //2. Create SQL Statement
            if (con != null) {
                //3. Create Statement to set SQL
                String sql = "Select username, lastname, isAdmin "
                        + "From Registration "
                        + "Where username = ? And password = ?";
                stm = con.prepareStatement(sql);
                stm.setString(1, username);
                stm.setString(2, password);
                //4. Execute Query              
                rs = stm.executeQuery();
                //5. Process
                if (rs.next()) {
                    String lastname = rs.getNString("lastname");
                    boolean role = rs.getBoolean("isAdmin");
                    RegistrationDTO dto = new RegistrationDTO(username, password, lastname, role);
                    return dto;
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
        return null;
    }
    private List<RegistrationDTO> accounts;

    public List<RegistrationDTO> getAccounts() {
        return accounts;
    }

    public void searchLastname(String searchValue)
            throws SQLException, NamingException {
        Connection con = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        try {
            //1. Connect DB
            con = DBUtils.makeConnection();
            //2. Create SQL Statement
            if (con != null) {
                //3. Create Statement to set SQL
                String sql = "Select username, password, lastname, isAdmin "
                        + "From Registration "
                        + "Where lastname Like ?";

                stm = con.prepareStatement(sql);
                stm.setString(1, "%" + searchValue + "%");
                rs = stm.executeQuery();
                //5. Process
                while (rs.next()) {
                    //get field/collum
                    String username = rs.getString("username");
                    String password = rs.getString("password");
                    String lastname = rs.getNString("lastname");
                    boolean role = rs.getBoolean("isAdmin");
                    //create DTO
                    RegistrationDTO dto = new RegistrationDTO(username, password, lastname, role);
                    //add to accounts list
                    if (this.accounts == null) {
                        this.accounts = new ArrayList<>();
                    }
                    this.accounts.add(dto);
                }//end rs has more than one record
            }//end if connection is existed

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
    }

    public boolean deleteAccount(String username)
            throws SQLException, NamingException {
        Connection con = null;
        PreparedStatement stm = null;

        try {
            //1. Connect DB
            con = DBUtils.makeConnection();
            //2. Create SQL Statement
            if (con != null) {
                //3. Create Statement to set SQL
                String sql = "Delete From Registration "
                        + "Where username = ?";
                stm = con.prepareStatement(sql);
                stm.setString(1, username);
                //4. Execute Query              
                int row = stm.executeUpdate();
                //5. Process
                if (row > 0) {
                    return true;
                }
            }

        } finally {
            if (stm != null) {
                stm.close();
            }
            if (con != null) {
                con.close();

            }
        }
        return false;
    }

    public boolean updateAccount(String username, String lastname, boolean isAdmin)
            throws SQLException, NamingException {
        Connection con = null;
        PreparedStatement stm = null;

        try {
            //1. Connect DB
            con = DBUtils.makeConnection();
            //2. Create SQL Statement
            if (con != null) {
                //3. Create Statement to set SQL
                String sql = "Update Registration "
                        + "Set lastname = ?, isAdmin = ? "
                        + "Where username = ?";
                stm = con.prepareStatement(sql);
                stm.setNString(1, lastname);
                stm.setBoolean(2, isAdmin);
                stm.setString(3, username);
                //4. Execute Query              
                int row = stm.executeUpdate();
                //5. Process
                if (row > 0) {
                    return true;
                }
            }

        } finally {
            if (stm != null) {
                stm.close();
            }
            if (con != null) {
                con.close();

            }
        }
        return false;
    }

    public boolean insertAccount(RegistrationDTO dto)
            throws SQLException, NamingException {
        Connection con = null;
        PreparedStatement stm = null;
        if (dto == null) {
            return false;
        }//end dto is not existed
        try {
            //1. Connect DB
            con = DBUtils.makeConnection();
            //2. Create SQL Statement
            if (con != null) {
                //3. Create Statement to set SQL
                String sql = "Insert Into Registration(username, password, lastname, isAdmin) "
                        + "Values(?, ?, ?, ?)";
                stm = con.prepareStatement(sql);
                stm.setString(1, dto.getUsername());
                stm.setString(2, dto.getPassword());
                stm.setNString(3, dto.getLastname());
                stm.setBoolean(4, dto.isRole());
                //4. Execute Query              
                int row = stm.executeUpdate();
                //5. Process
                if (row > 0) {
                    return true;
                }
            }

        } finally {
            if (stm != null) {
                stm.close();
            }
            if (con != null) {
                con.close();

            }
        }
        return false;
    }

    public boolean changePassword(String username, String newPassword)
            throws SQLException, NamingException {
        Connection con = null;
        PreparedStatement stm = null;
        try {
            con = DBUtils.makeConnection();
            if (con != null) {
                String sql = "UPDATE Registration "
                        + "SET password = ? "
                        + "WHERE username = ?";
                stm = con.prepareStatement(sql);
                stm.setString(1, newPassword);
                stm.setString(2, username);
                int row = stm.executeUpdate();
                if (row > 0) {
                    return true;
                }
            }
        } finally {
            if (stm != null) {
                stm.close();
            }
            if (con != null) {
                con.close();
            }
        }
        return false;

    }
}
