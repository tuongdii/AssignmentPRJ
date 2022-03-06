/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package duyvtt.controller;

import duyvtt.cart.CartObject;
import duyvtt.product.ProductDAO;
import duyvtt.product.ProductDTO;
import duyvtt.utils.MyApplicationConstants;
import java.io.IOException;
import java.sql.SQLException;
import java.util.Map;
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
public class RemoveItemsFromCartServlet extends HttpServlet {

    private final Logger LOGGER = Logger.getLogger(RemoveItemsFromCartServlet.class);

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
        boolean foundError = false;
        try {
            //1. Customer gose to his/her cart place
            HttpSession session = request.getSession(false);
            if (session != null) {
                //2. Customer take cart
                CartObject cart = (CartObject) session.getAttribute("CART");
                if (cart != null) {
                    //2. Customer take items
                    Map<ProductDTO, Integer> item = cart.getItems();
                    if (item != null) {
                        //4. get all selected Items
                        String[] removedItems = request.getParameterValues("chkItem");
                        ProductDAO dao = new ProductDAO();
                        if (removedItems != null) {
                            //5. Remove each iteam from cart
                            for (String items : removedItems) {
                                cart.removeItemFromCart(dao.getProductByID(items));
                            }
                            session.setAttribute("CART", cart);
                        }
                    }
                }
            }

        } catch (SQLException ex) {
            LOGGER.info(ex);
            foundError = true;
        } catch (NamingException ex) {
            LOGGER.info(ex);
            foundError = false;
        } finally {
            if (foundError) {
                response.sendError(500);
            } else {
                //6. refresh = call view cart again
                response.sendRedirect(MyApplicationConstants.RemoveItemFeature.VIEW_CART_PAGE);
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
