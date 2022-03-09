/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package duyvtt.filter;

import java.io.IOException;
import java.util.Properties;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.apache.log4j.Logger;

/**
 *
 * @author DELL
 */
public class AuthenticationFilter implements Filter {

    private final Logger LOGGER = Logger.getLogger(AuthenticationFilter.class);
    private static final boolean debug = true;

    private FilterConfig filterConfig = null;

    public AuthenticationFilter() {
    }

    public void doFilter(ServletRequest request, ServletResponse response,
            FilterChain chain) {
        HttpServletRequest httpRequest = (HttpServletRequest) request;
        HttpServletResponse httpResponse = (HttpServletResponse) response;
        try {

            //get authentication properties
            ServletContext context = httpRequest.getServletContext();
            Properties authProperties = (Properties) context.getAttribute("AUTHENTICATION_LIST");

            HttpSession session = httpRequest.getSession(false);
            //get resource name 
            String resource = httpRequest.getServletPath().substring(1);
            //check resource authentication
            String rule = (String) authProperties.getProperty(resource);
            if (rule != null && rule.equals("restricted")) {
                if (session == null || session.getAttribute("USER") == null) {
                    httpResponse.sendRedirect("login");
                } else {
                    chain.doFilter(request, response);
                }
            } else {
                chain.doFilter(request, response);
            }
        } catch (IOException ex) {
            LOGGER.info(ex);
        } catch (ServletException ex) {
            LOGGER.info(ex);
        }

    }

    /**
     * Return the filter configuration object for this filter.
     */
    public FilterConfig getFilterConfig() {
        return (this.filterConfig);
    }

    /**
     * Set the filter configuration object for this filter.
     *
     * @param filterConfig The filter configuration object
     */
    public void setFilterConfig(FilterConfig filterConfig) {
        this.filterConfig = filterConfig;
    }

    /**
     * Destroy method for this filter
     */
    public void destroy() {
    }

    /**
     * Init method for this filter
     */
    public void init(FilterConfig filterConfig) {
        this.filterConfig = filterConfig;
        if (filterConfig != null) {
            if (debug) {
                LOGGER.info("AuthenticationFilter:Initializing filter");
            }
        }
    }
}
