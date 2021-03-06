/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package duyvtt.controller;


import duyvtt.registration.RegistrationDAO;
import duyvtt.registration.RegistrationDTO;
import duyvtt.common.Constants;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import java.util.Properties;
import javax.naming.NamingException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
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
public class AccountSearchLastnameServlet extends HttpServlet {

    private final Logger LOGGER = Logger.getLogger(AccountSearchLastnameServlet.class);

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
        //get session
        HttpSession session = request.getSession(false);
        
        //get search value
        String searchValue = request.getParameter("txtSearchValue");
        if (searchValue == null) {
            searchValue = (String) session.getAttribute("SEARCH_VALUE");
        }
        
        ServletContext context = request.getServletContext();
        Properties prop = (Properties) context.getAttribute("SITE_MAP");
        
        //get user to check role
        String url = Constants.SearchLastnameFeature.SEARCH_PAGE_USER;
        RegistrationDTO user = (RegistrationDTO) session.getAttribute("USER");
        if (user.isRole()) {
            url = Constants.SearchLastnameFeature.SEARCH_PAGE_ADMIN;
        }
        boolean foundError = false;
        session.setAttribute("SEARCH_VALUE", searchValue);
        try {
            if (!searchValue.trim().isEmpty()) {
                //callDAO
                RegistrationDAO dao = new RegistrationDAO();
                dao.searchLastname(searchValue);
                List<RegistrationDTO> result = dao.getAccounts();
                if (result != null) {
                    for (int i = 0; i < result.size(); i++) {
                        if (result.get(i).getUsername().equals(user.getUsername())) {
                            result.remove(result.get(i));
                        }
                    }
                }
                request.setAttribute("SEARCH_RESULT", result);
            }

        } catch (NamingException | SQLException ex) {
            LOGGER.error(ex);
            foundError = true;
        }finally {
            if (foundError) {
                response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            } else {
                RequestDispatcher rd = request.getRequestDispatcher(prop.getProperty(url));
                rd.forward(request, response);
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
