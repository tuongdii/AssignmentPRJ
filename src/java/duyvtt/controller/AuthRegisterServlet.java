/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package duyvtt.controller;

import duyvtt.registration.RegistrationDAO;
import duyvtt.registration.RegistrationDTO;
import duyvtt.registration.RegistrationInsertError;
import duyvtt.common.Constants;
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
public class AuthRegisterServlet extends HttpServlet {

    private final Logger LOGGER = Logger.getLogger(AuthRegisterServlet.class);

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
        String confirm = request.getParameter("txtConfirm");
        String fullname = request.getParameter("txtFullname");

        RegistrationInsertError errors = new RegistrationInsertError();
        boolean foundErr = false;
        boolean foundServerError = false;
        String url = Constants.RegisterFeature.ERROR_PAGE;

        HttpSession session = request.getSession();

        try {
            //1. Check all user errors
            if (username.trim().length() < 6 || username.trim().length() > 20) {
                foundErr = true;
                errors.setUsernameLengthErr("Username is required form 6 to 20 chars");
            }
            if (password.length() < 6 || password.length() > 30) {
                foundErr = true;
                errors.setPasswordLengthErr("Password is required form 6 to 30 chars");
            } else if (!confirm.trim().equals(password.trim())) {
                foundErr = true;
                errors.setConfirmNotMatch("Confirm must match password");
            }
            if (fullname.trim().length() < 2 || fullname.trim().length() > 50) {
                foundErr = true;
                errors.setFullNameLengthErr("Full name is required form 2 to 50 chars");
            }

            if (foundErr) {
                session.setAttribute("INSERT_ERRORS", errors);
                session.setAttribute("txtUsername", username);
                session.setAttribute("txtFullname", fullname);
            } else {
                //Insert to DB
                RegistrationDTO dto = new RegistrationDTO(username, password, fullname, false);
                RegistrationDAO dao = new RegistrationDAO();
                boolean result = dao.insertAccount(dto);
                if (result) {
                    url = Constants.RegisterFeature.LOGIN_PAGE;
                }
            }
        } catch (SQLException ex) {
            String msg = ex.getMessage();
            LOGGER.error(ex);
            if (msg.contains("duplicate")) {
                errors.setUsernameIsExisted(username + " existed!!!");
                session.setAttribute("INSERT_ERRORS", errors);
            }else{
                foundServerError = true;
            }
        } catch (NamingException ex) {
            LOGGER.error(ex);
            foundServerError = true;
        } finally {
            if (!foundServerError) {
                response.sendRedirect(url);
            }else{
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
