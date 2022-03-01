/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package duyvtt.controller;

import duyvtt.cart.CartObject;
import duyvtt.cart.OrderService;
import duyvtt.orderDetail.OrderDetailDTO;
import duyvtt.product.ProductDTO;
import java.io.IOException;
import java.math.BigDecimal;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
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
public class CartCheckoutServlet extends HttpServlet {
    private final Logger LOGGER = Logger.getLogger(CartCheckoutServlet.class);
    private final String SHOP_PAGE = "shopPage";
    private final String VIEW_CART = "viewCart";
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
        String fullname = request.getParameter("txtFullname");
        String url = VIEW_CART;
        try {
            //1. staff goes to cart place
            HttpSession session = request.getSession(false);
            if (session != null){
                //2. staff take customer's cart
                CartObject cart = (CartObject) session.getAttribute("CART");
                if (cart != null){
                    //create OrderDetailDTO List
                    List<OrderDetailDTO> orderDetailList = new ArrayList<>();
                    Map<ProductDTO, Integer> items = cart.getItems();
                    if (items != null){
                        for (ProductDTO item : items.keySet()) {
                            String productId = item.getId();
                            int quantity = items.get(item);
                            BigDecimal price = item.getPrice();
                            BigDecimal total =  price.multiply(BigDecimal.valueOf(quantity));
                            OrderDetailDTO dto = new OrderDetailDTO(productId, price, quantity, total);
                            
                            //add dto to orderdetail list
                            orderDetailList.add(dto);
                        }
                        OrderService service = new OrderService();
                        boolean result = service.checkoutService(fullname, orderDetailList);
                        if(result){
                            url = SHOP_PAGE;
                            session.removeAttribute("CART");
                        }
                    }
                }
            }
        } catch (SQLException ex) {
            LOGGER.info(ex);
        } catch (NamingException ex) {
            LOGGER.info(ex);
        }finally{
            ServletContext context = request.getServletContext();
            Properties siteMapProp = (Properties) context.getAttribute("SITE_MAP");
            RequestDispatcher rq = request.getRequestDispatcher(siteMapProp.getProperty(url));
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
