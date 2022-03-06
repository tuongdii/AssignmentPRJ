<%-- 
    Document   : confirmCheckout
    Created on : Mar 1, 2022, 2:05:06 PM
    Author     : DELL
--%>

<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>checkout</title>
    </head>
    <body>
        <h1>Cofirm Checkout</h1>
        <c:set var="cart" value="${sessionScope.CART}"/>
        <c:if test="${not empty cart}">
            <c:set var="items" value="${CART.items}"/>
            <c:if test="${not empty items}">
                <h2>Your Bill</h2>
                <table border="1">
                    <thead>
                        <tr>
                            <th>No.</th>
                            <th>Name</th>
                            <th>Quantity</th>
                            <th>Price</th>
                            <th>Total</th>
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
                                    <fmt:formatNumber type = "currency" value="${dto.price}" maxFractionDigits="2" /> 
                                </td>
                                <td>
                                    <c:set var="total" value="${quantity * dto.price}"/>
                                    <fmt:formatNumber type = "currency" value="${total}" maxFractionDigits="2" /> 
                                    <c:set var="totalPayment" value="${totalPayment + total}"/>
                                </td>
                            </tr>
                        </c:forEach>                      
                    </tbody>
                </table>
                <h4>Total Payment: 
                    <fmt:formatNumber type = "currency" value="${dto.price}" maxFractionDigits="2" /> 
                </h4>
                <form action="checkoutAction">
                    Enter full name: <input type="text" name="txtFullname" value="" /><br>
                    <input type="submit" value="Checkout" name="btAction" />
                </form>
                <c:if test="${not empty requestScope.CHECKOUT_ERROR}">
                    <font color="red">
                    ${requestScope.CHECKOUT_ERROR}
                    </font>
                </c:if>
                <a href="viewCart"><button>Cancel</button></a>
            </c:if>
        </c:if>
    </body>
</html>
