<%-- 
    Document   : onlineShopping
    Created on : Feb 20, 2022, 12:37:44 AM
    Author     : DELL
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Shopping Page</title>
    </head>
    <body>
        <h1>Online Shopping Page</h1>
        <c:set var="products" value="${requestScope.LIST_PRODUCT}"/>
        <c:set var="cart" value="${sessionScope.CART}"/>
        <c:if test="${not empty products}">
            <form action="addToCart">
                <table border="1">
                    <thead>
                        <tr>
                            <th>No.</th>
                            <th>Book Title</th>
                            <th>Price</th>
                            <th>Description</th>
                            <th>Quantity</th>
                            <th>Select</th>
                            <th>Action</th>
                        </tr>
                    </thead>
                    <tbody>
                        <c:forEach var="product" items="${products}" varStatus="counter">

                            <tr>
                                <td>
                                    ${counter.count}
                                </td>
                                <td>
                                    ${product.name}
                                </td>
                                <td>
                                    <fmt:formatNumber type = "currency" value="${product.price}" maxFractionDigits="2" />                       
                                </td>
                                <td>
                                    ${product.description}
                                </td>
                                <td>
                                    <c:set var="currentQuantity" value="${product.quantity - cart.getItemQuantity(product)}"/>
                                    ${currentQuantity}
                                </td>
                                <td>
                                    <input type="checkbox" name="chkProduct" value="${product.id}" 
                                           <c:if test="${currentQuantity <= 0}">
                                               disabled="disabled"
                                           </c:if>   
                                           />
                                </td>
                                <td>
                                     <c:url var="addItemUrl" value="addToCart">
                                        <c:param name="chkProduct" value="${product.id}"/>
                                    </c:url>
                                    <a href="${addItemUrl}"><button type="button">Add To Cart</button></a>
                                </td>
                            </tr>
                            
                        </c:forEach>
                            <tr>
                                  <th colspan="5">
                                      <a href="viewCart"><button type="button">View Your Cart</button></a>
                                  </th>
                                  <th>
                                      <input type="submit" value="Add Selected Items" name="btAction" />
                                  </th>                               
                              </tr>
                    </tbody>
                </table>
            </form>
        </c:if>  
    </body>
</html>
