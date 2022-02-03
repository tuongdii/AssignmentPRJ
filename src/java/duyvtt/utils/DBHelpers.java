/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package duyvtt.utils;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

/**
 *
 * @author DELL
 */
public class DBHelpers implements Serializable{
    public static Connection makeConnection()
        throws /*ClassNotFoundException,*/ SQLException, NamingException{
        //1. get current system file
        Context context = new InitialContext();
        //2. get conatiner context
        Context tomcatContext = (Context)context.lookup("java:comp/env");
        //3. get Datasourse
        DataSource ds = (DataSource) tomcatContext.lookup("DSBlink");
        //get connection
        Connection con = ds.getConnection();
        return con;     
//        //1.Load Driver
//        Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
//        //2.Create Connection String
//        String url = "jdbc:sqlserver://localhost:1433;databaseName=LOGIN";
//        //3. Open Connection
//        Connection con = DriverManager.getConnection(url, "sa", "20122001");
//        return con;
          
    }   
    
}
