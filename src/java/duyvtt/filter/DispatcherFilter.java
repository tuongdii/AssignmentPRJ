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
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.log4j.Logger;

/**
 *
 * @author DELL
 */
public class DispatcherFilter implements Filter {

    private final Logger LOGGER = Logger.getLogger(DispatcherFilter.class);
    private static final boolean debug = true;

    // The filter configuration object we are associated with.  If
    // this value is null, this filter instance is not currently
    // configured. 
    private FilterConfig filterConfig = null;

    public DispatcherFilter() {
    }

    public void doFilter(ServletRequest request, ServletResponse response,
            FilterChain chain)
            throws IOException, ServletException {
        HttpServletRequest httpRequest = (HttpServletRequest) request;
        HttpServletResponse httpResponse = (HttpServletResponse) response;
        //set header for response object
        httpResponse.setContentType("text/html;charset=UTF-8");
        httpResponse.setHeader("Cache-control", "no-cache, no-store");
        httpRequest.setCharacterEncoding("UTF-8");

        String url = null;
        //get siteMap form Application Scope
        ServletContext context = httpRequest.getServletContext();
        Properties siteMapProp = (Properties) context.getAttribute("SITE_MAP");

        String resource = httpRequest.getServletPath().substring(1);

        url = siteMapProp.getProperty(resource);
        if (url != null) {
            RequestDispatcher rd = httpRequest.getRequestDispatcher(url);
            rd.forward(request, response);
        } else {
            httpResponse.sendError(httpResponse.SC_NOT_FOUND);

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
                LOGGER.info("DispatcherFilter:Initializing filter");
            }
        }
    }

}
