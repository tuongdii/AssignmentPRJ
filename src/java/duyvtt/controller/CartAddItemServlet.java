/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package duyvtt.controller;

import duyvtt.cart.CartObject;
import duyvtt.product.ProductDAO;
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
import javax.servlet.http.HttpSession;
import org.apache.log4j.Logger;

/**
 *
 * @author DELL
 */
public class CartAddItemServlet extends HttpServlet {

    private final Logger LOGGER = Logger.getLogger(CartAddItemServlet.class);

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
        String url = Constants.AddItemToCartFeature.SHOPPING_PAGE;
        
        ServletContext context = request.getServletContext();
        Properties prop = (Properties) context.getAttribute("SITE_MAP");
        
        boolean foundError = false;
        try {
            //1. Customer goes to cart place
            HttpSession session = request.getSession();
            
            //2. Customer take a cart
            CartObject cart = (CartObject) session.getAttribute("CART");
            if (cart == null) {
                cart = new CartObject();
            }
            //3. customer takes item
            String listProductsID[] = request.getParameterValues("chkProduct");
            if (listProductsID == null) {
                request.setAttribute("ADD_ERROR", "Please select product before adding to cart.");
            } else {
                ProductDAO dao = new ProductDAO();
                
                //4. customer drops item to cart
                for (String id : listProductsID) {
                    cart.addItemToCart(dao.getProductByID(id));
                }
            }

            session.setAttribute("CART", cart);

        } catch (SQLException | NamingException ex) {
            LOGGER.error(ex);
            foundError = true;
        } finally {
            if (foundError) {
                response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            } else {
                //5. redirect to online shopping page
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
