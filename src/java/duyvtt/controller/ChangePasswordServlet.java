/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package duyvtt.controller;

import duyvtt.registration.RegistrationChangePasswordError;
import duyvtt.registration.RegistrationDAO;
import duyvtt.registration.RegistrationDTO;
import duyvtt.utils.SecurityHelper;
import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.sql.SQLException;
import javax.naming.NamingException;
import org.apache.log4j.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author DELL
 */
public class ChangePasswordServlet extends HttpServlet {

    private final Logger LOGGER = Logger.getLogger(ChangePasswordServlet.class);

    private static String SEARCH_LASTNAME_CONTROLLER = "searchAccountAction";
    private static String ERROR_PAGE = "changePassword";
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
        HttpSession session = request.getSession(true);
        if(session.getAttribute("CHANGEPASSWORD_ERROR") != null){
            session.removeAttribute("CHANGEPASSWORD_ERROR");
        }
        if(session.getAttribute("CHANGEPASSWORD_SUCCESS") != null){
            session.removeAttribute("CHANGEPASSWORD_SUCCESS");
        }
        RegistrationChangePasswordError error = new RegistrationChangePasswordError();
        boolean foundError = false;
        String url = ERROR_PAGE;
       
        try {
            String hashedCurrentPassword = SecurityHelper.hashString(currentPassword);
            String hashedNewPassword = SecurityHelper.hashString(newPassword);
            String hashedConfirmPassword = SecurityHelper.hashString(confirmPassword);

            RegistrationDAO dao = new RegistrationDAO();
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
            
            session.setAttribute("USERNAME", username);
            if(foundError){
                session.setAttribute("CHANGEPASSWORD_ERROR", error);
            }else{
                boolean result = dao.changePassword(username, hashedNewPassword);
                if(result){
                    url = SEARCH_LASTNAME_CONTROLLER;
                    session.setAttribute("CHANGEPASSWORD_SUCCESS", "Change password successfully.");
                }
            }
        } catch (NoSuchAlgorithmException ex) {
            LOGGER.error(ex);
            response.sendError(500);
        } catch (SQLException ex) {
            LOGGER.error(ex);
            response.sendError(500);
        } catch (NamingException ex) {
            LOGGER.error(ex);
            response.sendError(500);
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
