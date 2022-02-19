/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package duyvtt.controller;

import duyvtt.registration.RegistrationDAO;
import duyvtt.registration.RegistrationInsertError;
import java.io.IOException;
import java.sql.SQLException;
import java.util.Properties;
import javax.naming.NamingException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.log4j.Logger;

/**
 *
 * @author DELL
 */
public class UpdateServlet extends HttpServlet {

    private final Logger LOGGER = Logger.getLogger(UpdateServlet.class);
    private final String SEARCH_LAST_NAME_SERVLET = "searchAccountAction";

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
        response.setContentType("text/html;charset=UTF-8");
        request.setCharacterEncoding("utf-8");

        String username = request.getParameter("txtUsername");
        String lastname = request.getParameter("txtLastname");
        String checkAdmin = request.getParameter("chkAdmin");
        String searchValue = request.getParameter("lastSearchValue");
        boolean isAdmin = false;
        if (checkAdmin != null) {
            isAdmin = true;
        }
        String url = SEARCH_LAST_NAME_SERVLET;

        RegistrationInsertError error = new RegistrationInsertError();
        boolean foundError = false;
        try {
            if (lastname.trim().length() < 2 || lastname.trim().length() > 50) {
                foundError = true;
                error.setFullNameLengthErr("Last name is required form 2 to 50 chars");
            }
            if (foundError) {
                request.setAttribute("UPDATE_ERRORS", error);
            } else {
                RegistrationDAO dao = new RegistrationDAO();
                boolean result = dao.updateAccount(username, lastname, isAdmin);
            }
        } catch (SQLException e) {
            LOGGER.error(e);
            response.sendError(500);
        } catch (NamingException e) {
            LOGGER.error(e);
            response.sendError(500);
        } finally {
            ServletContext context = request.getServletContext();
            Properties siteMapProp = (Properties) context.getAttribute("SITE_MAP");
            url = siteMapProp.getProperty(SEARCH_LAST_NAME_SERVLET);
            url = url + "?txtSearchValue=" + searchValue;
            RequestDispatcher rd = request.getRequestDispatcher(url);
            rd.forward(request, response);

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
