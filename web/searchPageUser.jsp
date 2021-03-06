<%-- 
    Document   : search
    Created on : Dec 7, 2021, 12:30:11 AM
    Author     : DELL
--%>

<%--<%@page import="duyvtt.registration.RegistrationDTO"%>
<%@page import="java.util.List"%>--%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Search</title>
        <link rel="stylesheet" type="text/css" href="navStyle">
    </head>
    <body>
        <nav>
            <label class="logo">Assignment PRJ</label>
            <ul>
                <li><a href="shop">Shopping</a></li>
                    <c:if test="${not empty sessionScope.USER}">
                    <li><a href="profilePage">Profile</a></li>
                    </c:if>
                <li><a href="logoutAction">Log out</a></li>
            </ul>
        </nav>
        <font color="red">
        <c:if test="${not empty sessionScope.USER.lastname}">
            Welcome, ${sessionScope.USER.lastname}
        </c:if>
        </font>
        <h1>Search Page</h1>
        <form action="searchAccountAction">
            Search Value <input type="text" name="txtSearchValue" 
                                value="${param.txtSearchValue}" />
            <input type="submit" value="Search" name="btAction" />
        </form></br>

        <c:set var="searchValue" value="${param.txtSearchValue}"/>
        <c:if test="${not empty searchValue}">
            <c:set var="result" value="${requestScope.SEARCH_RESULT}"/>
            <c:if test="${not empty result}">
                <table border="1">
                    <thead>
                        <tr>
                            <th>No.</th>
                            <th>Username</th>
                            <th>Full Name</th>
                            <th>Role</th      
                        </tr>
                    </thead>
                    <tbody>
                        <c:forEach var="dto" items="${result}" varStatus="counter">

                            <tr>
                                <td align="right">
                                    ${counter.count}
                                </td>
                                <td>
                                    ${dto.username}
                                </td>

                                <td>
                                    ${dto.lastname}                                  
                                </td>
                                <td>

                                    <c:if test="${dto.role == true}" >
                                        Admin
                                    </c:if>
                                    <c:if test="${dto.role == false}" >
                                        User
                                    </c:if>

                                </td>
                            </tr> 
                        </c:forEach>
                    </tbody>
                </table>
            </c:if>
            <c:if test="${empty result}">
                <h2>No record is matched!!</h2>
            </c:if>
        </c:if>
    </body>
</html>
