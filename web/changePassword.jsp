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
            <c:set var="errors" value="${requestScope.CHANGEPASSWORD_ERROR}"/>
            <c:set var="info" value = "${requestScope.CHANGEPASSWORD_SUCCESS}"/>
            Username: 
            <c:if test="${empty param.username}">
                ${requestScope.USERNAME}
            </c:if>
            <c:if test="${not empty param.username}">
                ${param.username}
            </c:if>

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
            <input type="submit" value="Save Changes" name="btAction" /><br>
            <c:if test="${not empty info}">
                <font color ="red">
                ${info}
                </font><br>
            </c:if>
        </form>
    </body>
</html>
