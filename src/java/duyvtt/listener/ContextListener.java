/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package duyvtt.listener;

import duyvtt.utils.PropertiesFileHelper;
import java.util.Properties;
import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import org.apache.log4j.PropertyConfigurator;

/**
 * Web application lifecycle listener.
 *
 * @author DELL
 */
public class ContextListener implements ServletContextListener {

    private void configLog4j(ServletContext context) {
        String log4jConfigFile = context.getInitParameter("LOG4J_PROPERTIES_FILE_LOCATION");
        String fullPath
                = context.getRealPath("/") + log4jConfigFile;

        System.setProperty("PATH", context.getRealPath("/"));
        PropertyConfigurator.configure(fullPath);
    }

    private void loadSiteMapsFile(ServletContext context) {
        String siteMapLocation = context.getInitParameter("SITEMAPS_PROPERTIES_FILE_LOCATION");
        Properties siteMapProperty
                = PropertiesFileHelper.getProperties(context, siteMapLocation);
        context.setAttribute("SITE_MAP", siteMapProperty);
    }

    private void loadAuthenticationFile(ServletContext context) {
        String authenticationLocation = context.getInitParameter("AUTHENTICATION_PROPERTIES_FILE_LOCATION");
        Properties authenticationProperty
                = PropertiesFileHelper.getProperties(context, authenticationLocation);
        context.setAttribute("AUTHENTICATION_LIST", authenticationProperty);
    }

    @Override
    public void contextInitialized(ServletContextEvent sce) {
        ServletContext context = sce.getServletContext();
        configLog4j(context);
        loadSiteMapsFile(context);
        loadAuthenticationFile(context);
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {

    }
}
