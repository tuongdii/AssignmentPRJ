/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package duyvtt.controller;

import duyvtt.cart.CartObject;
import duyvtt.cart.CartUpdateQuantityError;
import duyvtt.common.Constants;
import duyvtt.product.ProductDAO;
import duyvtt.product.ProductDTO;
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
import javax.servlet.http.HttpSession;
import org.apache.log4j.Logger;

/**
 *
 * @author DELL
 */
public class CartUpdateQuantityServlet extends HttpServlet {

    private final Logger LOGGER = Logger.getLogger(CartUpdateQuantityServlet.class);

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
        String itemId = request.getParameter("txtId");
        String quantityParam = request.getParameter("txtQuantity");

        ServletContext context = request.getServletContext();
        Properties prop = (Properties) context.getAttribute("SITE_MAP");

        CartUpdateQuantityError errors = new CartUpdateQuantityError();

        String url = Constants.UpdateItemFeature.VIEW_CART;

        boolean foundServerError = false;
        try {
            ProductDAO dao = new ProductDAO();
            ProductDTO dto = dao.getProductByID(itemId);

            if (quantityParam.isEmpty()) {
                url = Constants.UpdateItemFeature.CONFIRM_REMOVE;
                request.setAttribute("ITEM_REMOVE", dto);
            } else {
                int quantity = Integer.parseInt(quantityParam);

                if (quantity == 0) {
                    url = Constants.UpdateItemFeature.CONFIRM_REMOVE;
                    request.setAttribute("ITEM_REMOVE", dto);
                } else if (quantity < 0) {
                    errors.setQuantityInvalid("The quantity must be a non-negative number.");
                    request.setAttribute("UPDATE_ITEM_ERRORS", errors);
                } else if (quantity > 0) {
                    HttpSession session = request.getSession(false);
                    if (session != null) {
                        CartObject cart = (CartObject) session.getAttribute("CART");
                        if (cart != null) {
                            if (dto.getQuantity() >= quantity) {
                                boolean result = cart.updateItemQuantity(dto, quantity);
                                if (result) {
                                    request.setAttribute("UPDATE_ITEM_INFO",
                                            "The quanity of products " + dto.getName() + " has been updated.");
                                }
                            } else {
                                errors.setNotEnoughQuantity("Not enough quantity of product " + dto.getName()
                                        + ". Please return to the shop page to track the available quantity.");
                                request.setAttribute("UPDATE_ITEM_ERRORS", errors);
                            }
                        }
                    }
                }
            }
        } catch (NumberFormatException ex) {
            errors.setQuantityInvalid("Invalid quantity.");
            request.setAttribute("UPDATE_ITEM_ERRORS", errors);
        } catch (SQLException | NamingException ex) {
            LOGGER.error(ex);
            foundServerError = true;
        } finally {
            if (foundServerError) {
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
