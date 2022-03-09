/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package duyvtt.controller;

import duyvtt.registration.RegistrationDAO;
import duyvtt.common.Constants;
import duyvtt.registration.RegistrationUpdateError;
import java.io.IOException;
import java.sql.SQLException;
import javax.naming.NamingException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.apache.log4j.Logger;

/**
 *
 * @author DELL
 */
public class UpdateServlet extends HttpServlet {

    private final Logger LOGGER = Logger.getLogger(UpdateServlet.class);

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
        String lastname = request.getParameter("txtLastname");
        String checkAdmin = request.getParameter("chkAdmin");
        boolean isAdmin = false;
        if (checkAdmin != null) {
            isAdmin = true;
        }
        String url = Constants.updateAccountFeature.SEARCH_LATS_NAME_SERVLET;

        HttpSession session = request.getSession();
        RegistrationUpdateError error = new RegistrationUpdateError();
        boolean foundError = false;
        try {
            if (lastname.trim().length() < 2 || lastname.trim().length() > 50) {
                foundError = true;
                error.setFullNameLengthErr("Last name is required form 2 to 50 chars");
            }
            if (foundError) {
                session.setAttribute("UPDATE_ERRORS", error);
            } else {
                RegistrationDAO dao = new RegistrationDAO();
                boolean result = dao.updateAccount(username, lastname, isAdmin);
                if (result) {
                    session.setAttribute("UPDATE_INFO", username + " account has been updated.");
                }
            }
        } catch (SQLException e) {
            LOGGER.error(e);
            response.sendError(response.SC_INTERNAL_SERVER_ERROR);
        } catch (NamingException e) {
            LOGGER.error(e);
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
