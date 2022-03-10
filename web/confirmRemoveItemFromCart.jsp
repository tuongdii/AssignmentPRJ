<%-- 
    Document   : confirmRemoveItemFromCart
    Created on : Mar 9, 2022, 10:13:00 PM
    Author     : DELL
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Remove Confirm</title>
        <link rel="stylesheet" type="text/css" href="navStyle">
    </head>
    <body>
        <nav>
            <label class="logo">Assignment PRJ</label>
            <ul>
                <li><a href="viewCart">Back To Your Cart</a></li>
            </ul>
        </nav>
        <h1>Remove Item Confirm</h1>
        <c:if test="${not empty requestScope.ITEM_REMOVE}">
            <c:set var="name" value="${requestScope.ITEM_REMOVE.name}"/>
            <c:set var="id" value="${requestScope.ITEM_REMOVE.id}"/>
        </c:if>
        <c:if test="${empty requestScope.ITEM_REMOVE}">
            <c:set var="name" value="${param.txtItem}"/>
            <c:set var="id" value="${param.txtItemId}"/>
        </c:if>
        <h3>Do you want to remove "${name}" from your cart?</h3>
        <c:url var="removeUrl" value="removeItems">
            <c:param name="txtItemId" value="${id}"/>
        </c:url>
        <a href="${removeUrl}"><button type="button">Yes</button></a>
        <a href="viewCart"><button type="button">No</button></a>
    </body>
</html>
