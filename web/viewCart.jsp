<%-- 
    Document   : viewCart
    Created on : Dec 18, 2021, 4:40:02 PM
    Author     : DELL
--%>

<%@page import="duyvtt.product.ProductDTO"%>
<%@page import="java.util.Map"%>
<%@page import="duyvtt.cart.CartObject"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Online Shopping</title>
    </head>
    <body>
        <h1>Your Cart include</h1>
        <c:set var="cart" value="${sessionScope.CART}"/>
        <c:if test="${not empty cart}">
            <c:set var="items" value="${cart.items}"/>
            <c:if test="${not empty items}">
                <form action="removeItems">
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
                            <c:forEach var="item" items="${items}" varStatus="counter">
                                <c:set var="dto" value="${item.key}"/>
                                <c:set var="quantity" value="${item.value}"/>
                                <tr>
                                    <td>
                                        ${counter.count}
                                    </td>
                                    <td>
                                        ${dto.name}
                                    </td>
                                    <td>
                                        ${quantity}
                                    </td>
                                    <td>
                                        <input type="checkbox" name="chkItem" value="${dto.id}" />
                                    </td>
                                </tr>
                            </c:forEach>
                            <tr>
                                <th colspan="3">
                                    <a href="shop">Add more Item to Cart</a>
                                </th>
                                <th>
                                    <input type="submit" value="Remove Selected Items" name="btAction" />
                                </th>                               
                            </tr>
                        </tbody>
                    </table>
                </form>
                <a href="checkout"><button>Check out</button></a>
            </c:if>
        </c:if>
        <c:if test="${empty cart}">
            <h2>No cart is existed!</h2>
        </c:if>
    </body>
</html>
