/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package duyvtt.controller;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author DELL
 */
@WebServlet(name = "DispatchController", urlPatterns = {"/DispatchController"})
public class DispatchController extends HttpServlet {
    private final String LOGIN_PAGE = "login.html";
    private final String LOGIN_CONTROLLER = "LoginServlet";
    private final String SEARCH_LASTNAME_CONTROLLER = "SearchLastnameServlet";
    private final String DELETE_CONTROLLER = "DeteleAccountServlet";
    private final String UPDATE_CONTROLLER = "UpdateServlet";
    private final String PROCESS_REQUEST_CONTROLLER = "ProcessRequestServlet";
    private final String ADD_ITEM_TO_CART = "AddItemToCartServlet";
    private final String VIEW_CART_PAGE = "viewCart.jsp";
    private final String REMOVE_ITEAMS_FROM_CART_SERVLET = "RemoveItemsFromCartServlet";
    private final String REGISTER_CONTROLLER = "RegisterServlet";
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
        PrintWriter out = response.getWriter();
        
        //which button dóe user click?
        String button = request.getParameter("btAction");
        String url = LOGIN_PAGE;
        try  {
            if(button==null){
                url = PROCESS_REQUEST_CONTROLLER;
            }
            else if(button.equalsIgnoreCase("Login")){
                url = LOGIN_CONTROLLER;
            }
            else if (button.equalsIgnoreCase("Search")){
                url = SEARCH_LASTNAME_CONTROLLER;
            }
            else if (button.equalsIgnoreCase("delete")){
                url = DELETE_CONTROLLER;
            }
            else if (button.equalsIgnoreCase("update")){
                url = UPDATE_CONTROLLER;
            }
            else if(button.equalsIgnoreCase("Add Item To Your Cart")){
                url = ADD_ITEM_TO_CART;
            }
            else if(button.equalsIgnoreCase("View Your Cart")){
                url = VIEW_CART_PAGE;
            }
            else if (button.equalsIgnoreCase("Remove Selected Items")){
                url = REMOVE_ITEAMS_FROM_CART_SERVLET;
            }
            else if (button.equalsIgnoreCase("Register")){
                url = REGISTER_CONTROLLER;
            }
        }finally{
            RequestDispatcher rd = request.getRequestDispatcher(url);
            rd.forward(request, response);
            out.close();
            
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
