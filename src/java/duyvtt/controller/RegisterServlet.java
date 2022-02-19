/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package duyvtt.controller;

import duyvtt.registration.RegistrationDAO;
import duyvtt.registration.RegistrationDTO;
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
public class RegisterServlet extends HttpServlet {
    private final Logger LOGGER = Logger.getLogger(RegisterServlet.class);
    private final String LOGIN_PAGE = "login";
    private final String ERROR_PAGE = "registerPage";

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
        String password = request.getParameter("txtPassword");
        String confirm = request.getParameter("txtConfirm");
        String fullname = request.getParameter("txtFullname");


        RegistrationInsertError errors = new RegistrationInsertError();
        boolean foundErr = false;
        String url = ERROR_PAGE;

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
                request.setAttribute("INSERT_ERRORS", errors);
            } else {
                //Insert to DB
                RegistrationDTO dto = new RegistrationDTO(username, password, fullname, false);
                RegistrationDAO dao = new RegistrationDAO();
                boolean result = dao.insertAccount(dto);
                if (result) {
                    url = LOGIN_PAGE;
                }
            }
        } catch (SQLException ex) {
            String msg = ex.getMessage();
            log("RegisterServlet _ SQL " + msg);
            if (msg.contains("duplicate")) {
                errors.setUsernameIsExisted(username + " existed!!!");
                request.setAttribute("INSERT_ERRORS", errors);
            }
        } catch (NamingException ex) {
            log("RegisterServlet _ Maming " + ex.getMessage());
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
