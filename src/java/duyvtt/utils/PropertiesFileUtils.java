/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package duyvtt.utils;

import java.io.IOException;
import java.io.InputStream;
import java.io.Serializable;
import java.util.Properties;
import javax.servlet.ServletContext;
import org.apache.log4j.Logger;

/**
 *
 * @author DELL
 */
public final class PropertiesFileUtils implements Serializable{

    private PropertiesFileUtils() {
    }
    
    public static Properties getProperties(ServletContext context, String fileRelativePath) 
            throws IOException{
        InputStream input = context.getResourceAsStream(fileRelativePath);
        Properties prop = prop = new Properties();
        prop.load(input);
        return prop;
    }
}
