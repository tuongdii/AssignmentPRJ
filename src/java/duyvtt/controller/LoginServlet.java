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
public class LoginServlet extends HttpServlet {

    private final Logger LOGGER = Logger.getLogger(LoginServlet.class);

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

        String username = request.getParameter("txtUsername");
        String password = request.getParameter("txtPassword");
        String url = Constants.loginFeature.INVALID_PAGE;

        try {
            String hashedPassword = SecurityUtils.hashString(password);
            //call DAO -> new DAO object & call method of DAO
            RegistrationDAO dao = new RegistrationDAO();
            RegistrationDTO result = dao.checkLogin(username, hashedPassword);
            if (result != null) {
                // Create session and add RegistrationDTO attribute
                HttpSession session = request.getSession();
                session.setAttribute("USER", result);

                //create account cookie for remind user
                Cookie cookie = new Cookie(username, password);
                cookie.setMaxAge(-1);   //means cookie existing until close browser
                response.addCookie(cookie);

                if (result.isRole() == true) {
                    url = Constants.loginFeature.SEARCH_PAGE_ADMIN;
                } else {
                    url = Constants.loginFeature.SEARCH_PAGE_USER;
                }

            }
        } catch (SQLException ex) {
            LOGGER.error(ex);
            response.sendError(response.SC_INTERNAL_SERVER_ERROR);
        } catch (NamingException ex) {
            LOGGER.error(ex);
            response.sendError(response.SC_INTERNAL_SERVER_ERROR);
        } catch (NoSuchAlgorithmException ex) {
            LOGGER.error(ex);
            response.sendError(response.SC_INTERNAL_SERVER_ERROR);
        } finally {
            response.sendRedirect(url);

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
