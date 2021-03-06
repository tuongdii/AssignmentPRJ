<%-- 
    Document   : deleteConfirm
    Created on : Feb 22, 2022, 2:14:41 PM
    Author     : DELL
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Delete Confirm</title>
        <link rel="stylesheet" type="text/css" href="navStyle">
    </head>
    <body>
        <nav>
            <label class="logo">Assignment PRJ</label>
            <ul>
                <li><a href="searchAccountAction">Back To Search Page</a></li>
                <li><a href="logoutAction">Log out</a></li>
            </ul>
        </nav>
        <h1>Delete Account Confirm</h1>
        <h3>Do you want to delete "${param.username}" account?</h3>
        <c:url var="deleteUrl" value="deleteAccountAction">
            <c:param name="username" value="${param.username}"/>
        </c:url>
        <a href="${deleteUrl}"><button type="button">Yes</button></a>
        <a href="searchAccountAction"><button type="button">No</button></a>
    </body>
</html>
