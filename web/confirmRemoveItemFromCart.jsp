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
        <h3>Do you want to remove "${param.txtItem}" from your cart?</h3>
        <c:url var="removeUrl" value="removeItems">
            <c:param name="txtItemId" value="${param.txtItemId}"/>
        </c:url>
        <a href="${removeUrl}"><button type="button">Yes</button></a>
        <a href="viewCart"><button type="button">No</button></a>
    </body>
</html>
