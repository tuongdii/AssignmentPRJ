<%-- 
    Document   : profile
    Created on : Mar 12, 2022, 12:07:12 AM
    Author     : DELL
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Profile</title>
        <link rel="stylesheet" type="text/css" href="navStyle">
    </head>
    <body>
        <nav>
            <label class="logo">Assignment PRJ</label>
            <ul>
                <li><a href="shop">Shopping</a></li>
                <li><a href="home">Home</a></li>
            </ul>
        </nav>
        <h1>Your Profile</h1>
        <c:if test="${not empty sessionScope.USER}">
            <form action="updateAccountAction" method="POST">
                Username: ${sessionScope.USER.username}<br>
                <input type="hidden" name="txtUsername" 
                       value="${sessionScope.USER.username}" />
                Full name:  <input type="text" name="txtLastname" 
                                   value="${sessionScope.USER.lastname}"/><br>
                Role: <c:if test="${sessionScope.USER.role == true}" >
                    Admin
                    <input type="hidden" name="chkAdmin" 
                           value="${sessionScope.USER.role}" />
                </c:if>
                <c:if test="${sessionScope.USER.role == false}" >
                    User
                </c:if>
                <br>
                <input type="submit" value="Update" name="btAction" />
            </form>
            <c:if test="${not empty sessionScope.UPDATE_ERRORS}">
                <c:if test="${not empty sessionScope.UPDATE_ERRORS.fullNameLengthErr}">
                    <font color="red">
                    ${sessionScope.UPDATE_ERRORS.fullNameLengthErr}
                    </font>
                </c:if>             
                <c:remove var="UPDATE_ERRORS" scope="session"/>
            </c:if> 
            <c:if test="${not empty sessionScope.UPDATE_INFO}">
                <font color="red">
                ${sessionScope.UPDATE_INFO}
                </font>
                <c:remove var="UPDATE_INFO" scope="session"/>
            </c:if>
        </c:if>
    </body>
</html>
