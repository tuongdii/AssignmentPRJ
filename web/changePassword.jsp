<%-- 
    Document   : changePassword
    Created on : Dec 16, 2021, 1:08:04 PM
    Author     : DELL
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Change Password</title>
    </head>
    <body>
        <h1>Change Password Page</h1>
        <form action="changePasswordAction" method="POST">
            <c:set var="errors" value="${sessionScope.CHANGEPASSWORD_ERROR}"/>
            Username: 
            <c:if test="${empty param.username}">
                ${sessionScope.USERNAME}
                <input type="hidden" name="txtUsername" value="${sessionScope.USERNAME}" />
            </c:if>
            <c:if test="${not empty param.username}">
                ${param.username}
                <input type="hidden" name="txtUsername" value="${param.username}" />
            </c:if>
            <br>
            Current password* <input type="password" name="txtCurrent" value="" /><br>
            <c:if test="${not empty errors.wrongPassword}">
                <font color ="red">
                ${errors.wrongPassword}
                </font><br>
            </c:if>
            New password* <input type="password" name="txtNew" value="" />(6 - 30 chars)<br>
            <c:if test="${not empty errors.passwordLengthErr}">
                <font color ="red">
                ${errors.passwordLengthErr}
                </font><br>
            </c:if>
            Confirm password* <input type="password" name="txtConfirm" value="" /><br>
            <c:if test="${not empty errors.confirmNotMatch}">
                <font color ="red">
                ${errors.confirmNotMatch}
                </font><br>
            </c:if>
                <input type="hidden" name="txtSearchValue" value="${param.lastSearchValue}" />
            <input type="submit" value="Save Changes" name="btAction" /><br>
        </form>
    </body>
</html>
