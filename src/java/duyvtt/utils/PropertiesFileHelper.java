/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package duyvtt.utils;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;
import javax.servlet.ServletContext;
import org.apache.log4j.Logger;

/**
 *
 * @author DELL
 */
public class PropertiesFileHelper {
    private final static Logger LOGGER4J = Logger.getLogger(PropertiesFileHelper.class);
    public static Properties getProperties(ServletContext context, String fileRelativePath){
        InputStream input = context.getResourceAsStream(fileRelativePath);
        Properties prop = null;
        try{
            prop = new Properties();
            prop.load(input);
        }catch(IOException ex){

            LOGGER4J.error(ex);
        }
        return prop;
    }
}
