<%-- 
    Document   : viewCart
    Created on : Dec 18, 2021, 4:40:02 PM
    Author     : DELL
--%>

<%@page import="java.util.Map"%>
<%@page import="cart.CartObject"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Online Shopping</title>
    </head>
    <body>
        <h1>Your Cart include</h1>
        <%
          //1. Customer gose to his/her cart place
          if (session != null){
              //2. Customer takes his.her cart
              CartObject cart = (CartObject)session.getAttribute("CART");
              if (cart != null){
                  //3. 3 Customer takes iteam from cart
                  Map<String, Integer> item = cart.getItems();
                  if(item != null){
                      //4. Show items
                      %>
                      <form action="DispatchController">
                      <table border="1">
                          <thead>
                              <tr>
                                  <th>No.</th>
                                  <th>Name</th>
                                  <th>Quantity</th>
                                  <th>Action</th>
                              </tr>
                          </thead>
                          <tbody>
                              <% int count = 0;
                              for (String key : item.keySet()){
                                  %> 
                                  <tr>
                                      <td>
                                          <%= ++count %>
                                      </td>
                                      <td>
                                          <%= key %>
                                      </td>
                                      <td>
                                          <%= item.get(key) %>
                                      </td>
                                      <td>
                                          <input type="checkbox" name="chkItem" value="<%= key %>" />
                                      </td>
                                  </tr>
                              <%
                              }
                              %>
                              <tr>
                                  <th colspan="3">
                                      <a href="onlineShopping.html">Add more Item to Cart</a>
                                  </th>
                                  <th>
                                      <input type="submit" value="Remove Selected Items" name="btAction" />
                                  </th>                               
                              </tr>
                          </tbody>
                      </table>                          
                      </form>
                          
                      <%
                      return;
                  }
              }
          }
        %>
        <h2>
            No cart is existed!
        </h2>
    </body>
</html>
