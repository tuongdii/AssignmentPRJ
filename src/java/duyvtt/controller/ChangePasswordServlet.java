/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package duyvtt.controller;

import duyvtt.registration.RegistrationChangePasswordError;
import duyvtt.registration.RegistrationDAO;
import duyvtt.registration.RegistrationDTO;
import duyvtt.utils.Helper;
import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.sql.SQLException;
import java.util.Properties;
import java.util.logging.Level;
import javax.naming.NamingException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import org.apache.log4j.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author DELL
 */
public class ChangePasswordServlet extends HttpServlet {

    private final Logger LOGGER = Logger.getLogger(ChangePasswordServlet.class);

    private static String CHANGE_PASSWORD_PAGE = "changePassword";

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
        request.setCharacterEncoding("UTF-8");
        String username = request.getParameter("txtUsername");
        String currentPassword = request.getParameter("txtCurrent");
        String newPassword = request.getParameter("txtNew");
        String confirmPassword = request.getParameter("txtConfirm");

        RegistrationChangePasswordError error = new RegistrationChangePasswordError();
        boolean foundError = false;
        String url = CHANGE_PASSWORD_PAGE;
        try {
            request.setAttribute("USERNAME", username);
            String hashedCurrentPassword = Helper.hashString(currentPassword);
            String hashedNewPassword = Helper.hashString(newPassword);
            String hashedConfirmPassword = Helper.hashString(confirmPassword);

            RegistrationDAO dao = new RegistrationDAO();
            System.out.println(username);
            RegistrationDTO dto = dao.checkLogin(username, hashedCurrentPassword);
            if(dto == null){
                foundError = true;
                error.setWrongPassword("Enter the wrong password");
            }
            if(!hashedNewPassword.equalsIgnoreCase(hashedConfirmPassword)){
                foundError = true;
                error.setConfirmNotMatch("Confirm must match password");
            }
            if(newPassword.trim().length()<6 || newPassword.trim().length()>30){
                foundError = true;
                error.setPasswordLengthErr("Password is required form 6 to 30 chars");
            }
            if(foundError){
                request.setAttribute("CHANGEPASSWORD_ERROR", error);
            }else{
                boolean result = dao.changePassword(username, hashedNewPassword);
                if(result){
                    request.setAttribute("CHANGEPASSWORD_SUCCESS", "Change password successfully.");
                }
            }
        } catch (NoSuchAlgorithmException ex) {
            LOGGER.error(ex);
        } catch (SQLException ex) {
            LOGGER.error(ex);
        } catch (NamingException ex) {
            LOGGER.error(ex);
        } finally {
            ServletContext context = request.getServletContext();
            Properties siteMapProp = (Properties) context.getAttribute("SITE_MAP");
            url = siteMapProp.getProperty(url);
            RequestDispatcher rq = request.getRequestDispatcher(url);
            rq.forward(request, response);
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
