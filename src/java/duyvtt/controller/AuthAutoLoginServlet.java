/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package duyvtt.controller;

import duyvtt.registration.RegistrationDAO;
import duyvtt.registration.RegistrationDTO;
import duyvtt.common.Constants;
import duyvtt.utils.SecurityUtils;
import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.sql.SQLException;
import javax.naming.NamingException;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.apache.log4j.Logger;

/**
 *
 * @author DELL
 */
public class AuthAutoLoginServlet extends HttpServlet {

    private final Logger LOGGER = Logger.getLogger(AuthAutoLoginServlet.class);

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String url = Constants.AutoLoginFeature.LOGIN_PAGE;

        boolean foundError = false;
        try {
            //1. Get Coolies form request
            Cookie[] cookies = request.getCookies();
            if (cookies != null) {

                //2. Traverse all cookies to check authentication
                for (Cookie cookie : cookies) {

                    //3. Get username and password form name value
                    String username = cookie.getName();
                    String password = cookie.getValue();
                    String hashedPassword = SecurityUtils.hashString(password);

                    //4. call DAO to check authentication
                    RegistrationDAO dao = new RegistrationDAO();
                    RegistrationDTO result = dao.checkLogin(username, hashedPassword);
                    if (result != null) {
                        HttpSession session = request.getSession();
                        session.setAttribute("USER", result);
                        if (result.isRole()) {
                            url = Constants.AutoLoginFeature.SEARCH_PAGE_ADMIN;
                        } else {
                            url = Constants.AutoLoginFeature.SEARCH_PAGE_USER;
                        }
                        break;
                    }//end authentication is successfully checked
                }//end for traverse cookies
            }//end cookies is existes
        } catch (SQLException | NamingException | NoSuchAlgorithmException ex) {
            LOGGER.error(ex);
            foundError = true;
        } finally {
            if (!foundError) {
                response.sendRedirect(url);
            } else {
                response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            }
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
