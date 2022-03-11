/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package duyvtt.listener;

import duyvtt.common.Constants;
import duyvtt.utils.PropertiesFileUtils;
import java.io.IOException;
import java.util.Properties;
import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;

/**
 * Web application lifecycle listener.
 *
 * @author DELL
 */
public class ContextListener implements ServletContextListener {
    private final Logger LOGGER = Logger.getLogger(ContextListener.class);
    private void configLog4j(ServletContext context) {
        String log4jConfigFile 
                = context.getInitParameter(Constants.InitParam.LOG4J);
        String fullPath
                = context.getRealPath("/") + log4jConfigFile;

        System.setProperty("PATH", context.getRealPath("/"));
        PropertyConfigurator.configure(fullPath);
    }

    private void loadSiteMapsFile(ServletContext context) {
        try {
            String siteMapLocation
                    = context.getInitParameter(Constants.InitParam.SITEMAPS);
            Properties siteMapProperty
                    = PropertiesFileUtils.getProperties(context, siteMapLocation);
            context.setAttribute("SITE_MAP",
                    siteMapProperty);
        } catch (IOException ex) {
            LOGGER.error(ex);
        }
    }

    private void loadAuthenticationFile(ServletContext context) {
        try {
            String authenticationLocation
                    = context.getInitParameter(Constants.InitParam.AUTHENTICATION);
            Properties authenticationProperty
                    = PropertiesFileUtils.getProperties(context, authenticationLocation);
            context.setAttribute("AUTHENTICATION_LIST", authenticationProperty);
        } catch (IOException ex) {
            LOGGER.error(ex);
        }
    }

    private void loadAdminAuthenticationFile(ServletContext context){
        try {
            String adminAuthenticationLocation
                    = context.getInitParameter(Constants.InitParam.ADMIN_AUTHOR);
            Properties adminAuthenticationProperty
                    = PropertiesFileUtils.getProperties(context, adminAuthenticationLocation);              
            context.setAttribute("ADMIN_AUTHENTICATION_LIST", adminAuthenticationProperty);
        } catch (IOException ex) {
            LOGGER.error(ex);
        }
    }
    
    private void loadUserAuthenticationFile(ServletContext context){
        try {
            String userAuthenticationLocation
                    = context.getInitParameter(Constants.InitParam.USER_AUTHOR);
            Properties userAuthenticationProperty
                    = PropertiesFileUtils.getProperties(context, userAuthenticationLocation);
            context.setAttribute("USER_AUTHENTICATION_LIST",
                    userAuthenticationProperty);
        } catch (IOException ex) {
            LOGGER.error(ex);
        }
    }
    @Override
    public void contextInitialized(ServletContextEvent sce) {
        ServletContext context = sce.getServletContext();
        configLog4j(context);
        loadSiteMapsFile(context);
        loadAuthenticationFile(context);
        loadAdminAuthenticationFile(context);
        loadUserAuthenticationFile(context);
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {

    }
}
