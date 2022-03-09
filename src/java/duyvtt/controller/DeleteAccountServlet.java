/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package duyvtt.controller;

import duyvtt.registration.RegistrationDAO;
import duyvtt.common.Constants;
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
public class DeleteAccountServlet extends HttpServlet {

    private final Logger LOGGER = Logger.getLogger(DeleteAccountServlet.class);
   

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
        String username = request.getParameter("username");
        boolean foundError = false;
        ServletContext context = request.getServletContext();
        Properties prop = (Properties) context.getAttribute("SITE_MAP");
        String url = Constants.deleteAccountFeature.SEARCH_LATS_NAME_SERVLET;
        try {
            //call DAO
            RegistrationDAO dao = new RegistrationDAO();
            boolean result = dao.deleteAccount(username);
            if (result) {
                String deleteInfo = username + " account has been deleted successfully.";
                request.setAttribute("DELETE_INFO", deleteInfo);
            }//end if delete successfully
        } catch (SQLException e) {
            foundError = true;
            LOGGER.error(e);
        } catch (NamingException e) {
            foundError = true;
            LOGGER.error(e);
        } finally {
            if (!foundError) {
                RequestDispatcher rd = request.getRequestDispatcher(prop.getProperty(url));
                rd.forward(request, response);
            } else {
                response.sendError(response.SC_INTERNAL_SERVER_ERROR);
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
