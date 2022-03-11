/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package duyvtt.filter;


import duyvtt.common.Constants;
import duyvtt.registration.RegistrationDTO;
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
public class AuthorizationFilter implements Filter {

    private final Logger LOGGER = Logger.getLogger(AuthorizationFilter.class);
    private static final boolean debug = true;

    // The filter configuration object we are associated with.  If
    // this value is null, this filter instance is not currently
    // configured. 
    private FilterConfig filterConfig = null;

    public AuthorizationFilter() {
    }

    public void doFilter(ServletRequest request, ServletResponse response,
            FilterChain chain)
            throws IOException, ServletException {
        HttpServletRequest httpRequest = (HttpServletRequest) request;
        HttpServletResponse httpResponse = (HttpServletResponse) response;

        ServletContext context = httpRequest.getServletContext();
        HttpSession session = httpRequest.getSession(false);
        
        boolean existedAuthenticatedUser = session != null && session.getAttribute("USER") != null;
        //get resource name
        String resouce = httpRequest.getServletPath().substring(1);
        
        //get admin authentication properties
        Properties adminAuthProperties
                = (Properties) context.getAttribute("ADMIN_AUTHENTICATION_LIST");

        //get user authentication properties
        Properties userAuthProperties
                = (Properties) context.getAttribute("USER_AUTHENTICATION_LIST");

        //check resource role authentication
        if (adminAuthProperties.getProperty(resouce) != null) {
            if (existedAuthenticatedUser) {
                RegistrationDTO user = (RegistrationDTO) session.getAttribute("USER");
                boolean role = user.isRole();
                if (role) {
                    chain.doFilter(request, response);
                } else {
                    httpResponse.sendError(403);
                }
            }
        } else if (userAuthProperties.getProperty(resouce) != null) {
            if (existedAuthenticatedUser) {
                RegistrationDTO user = (RegistrationDTO) session.getAttribute("USER");
                boolean role = user.isRole();
                if (!role) {
                    chain.doFilter(request, response);
                } else {
                    httpResponse.sendError(HttpServletResponse.SC_FORBIDDEN);
                }
            }
        } else if ("login".equals(resouce)) {
            if (session != null && session.getAttribute("USER") != null) {
                RegistrationDTO user = (RegistrationDTO) session.getAttribute("USER");
                boolean role = user.isRole();
                if (!role) {
                    httpResponse.sendRedirect(Constants.FilterFeature.SEARCH_PAGE_USER);
                } else {
                    httpResponse.sendRedirect(Constants.FilterFeature.SEARCH_PAGE_ADMIN);
                }
            }else{
                chain.doFilter(request, response);
            }
        } else {
            chain.doFilter(request, response);
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
                LOGGER.info("RoleAuthenticationFilter:Initializing filter");
            }
        }
    }

}
