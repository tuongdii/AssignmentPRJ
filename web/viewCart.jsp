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
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Online Shopping</title>
        <link rel="stylesheet" type="text/css" href="navStyle">
    </head>
    <body>
        <nav>
            <label class="logo">Assignment PRJ</label>
            <ul>
                <li><a href="shop">Shop page</a></li>
            </ul>
        </nav>
        <h1>Your Cart include</h1>
        <c:set var="cart" value="${sessionScope.CART}"/>
        <c:if test="${not empty cart}">
            <c:set var="items" value="${cart.items}"/>
            <c:if test="${not empty items}">
                <table border="1">
                    <thead>
                        <tr>
                            <th>No.</th>
                            <th>Name</th>
                            <th>Quantity</th>
                            <th>Price</th>
                            <th>Total</th>
                            <th>Remove</th>
                            <th>Update</th>
                        </tr>
                    </thead>
                    <tbody>
                        <c:forEach var="item" items="${items}" varStatus="counter">
                            <c:set var="dto" value="${item.key}"/>
                            <c:set var="quantity" value="${item.value}"/>
                        <form action="updateItem">
                            <tr>
                                <td align="right">
                                    ${counter.count}
                                </td>
                                <td>
                                    ${dto.name}
                                    <input type="hidden" name="txtId" value="${dto.id}" />
                                </td>
                                <td>
                                    <input style="text-align: right" type="text"
                                           name="txtQuantity" value="${quantity}" size="5" />
                                </td>
                                <td align="right">
                                    <fmt:formatNumber type = "currency" 
                                                      value="${dto.price}" maxFractionDigits="2" />
                                </td>

                                <td align="right">
                                    <fmt:formatNumber type = "currency" 
                                                      value="${dto.price * quantity}" maxFractionDigits="2" />
                                </td>
                                <td>
                                    <c:url var="removeUrl" value="removeItemsConfirm">
                                        <c:param name="txtItemId" value="${dto.id}"/>
                                        <c:param name="txtItem" value="${dto.name}"/>
                                    </c:url>
                                    <a href="${removeUrl}">Remove</a>
                                </td>
                                <td>
                                    <input type="submit" value="Update" name="btAction" />
                                </td>
                            </tr>
                        </form>

                    </c:forEach>
                </tbody>
            </table>
            <a href="checkout"><button>Check out</button></a>
            <a href="shop"><button>Add more to cart</button></a><br>
            <c:if test="${not empty requestScope.CHECKOUT_ERROR.notEnoughQuantityErr}">
                <font color="red">
                ${requestScope.CHECKOUT_ERROR.notEnoughQuantityErr}
                </font>
            </c:if>
            <c:if test="${not empty requestScope.UPDATE_ITEM_ERRORS.notEnoughQuantity}">
                <font color="red">
                ${requestScope.UPDATE_ITEM_ERRORS.notEnoughQuantity}
                </font>
            </c:if>
            <c:if test="${not empty requestScope.UPDATE_ITEM_ERRORS.quantityInvalid}">
                <font color="red">
                ${requestScope.UPDATE_ITEM_ERRORS.quantityInvalid}
                </font>
            </c:if>
            <c:if test="${not empty requestScope.UPDATE_ITEM_INFO}">
                <font color="red">
                ${requestScope.UPDATE_ITEM_INFO}
                </font>
            </c:if>
        </c:if>
    </c:if>
    <c:if test="${empty cart}">
        <h2>No cart is existed!</h2>
    </c:if>
</body>
</html>
