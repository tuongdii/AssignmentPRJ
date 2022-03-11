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
                                value="${sessionScope.SEARCH_VALUE}" />
            <input type="submit" value="Search" name="btAction" />
        </form></br>

        <c:set var="searchValue" value="${sessionScope.SEARCH_VALUE}"/>
        <c:if test="${not empty searchValue}">
            <c:set var="result" value="${requestScope.SEARCH_RESULT}"/>
            <c:if test="${not empty result}">
                <table border="1">
                    <thead>
                        <tr>
                            <th>No.</th>
                            <th>Username</th>
                            <th>Full Name</th>
                            <th>Role</th>
                            <th>Delete</th>
                            <th>Update</th>        
                        </tr>
                    </thead>
                    <tbody>
                        <c:forEach var="dto" items="${result}" varStatus="counter">
                        <form action="updateAccountAction" method="POST">
                            <tr>
                                <td align="right">
                                    ${counter.count}
                                </td>
                                <td>
                                    ${dto.username}
                                    <input type="hidden" name="txtUsername" 
                                           value="${dto.username}" />
                                </td>
                                <td>
                                    <input type="text" name="txtLastname" value="${dto.lastname}"/>                                   
                                </td>
                                <td>
                                    <input type="checkbox" name="chkAdmin" value="ON" 
                                           <c:if test="${dto.role == true}" >
                                               checked="checked"
                                           </c:if>
                                           />
                                </td>
                                <td>
                                    <c:url var="deleteUrl" value="deleteCofirm">
                                        <c:param name="username" value="${dto.username}"/>
                                    </c:url>
                                    <a href="${deleteUrl}">Delete</a>
                                </td>
                                <td>
                                    <input type="submit" value="Update" name="btAction" />                                     
                                </td>        
                            </tr> 
                        </form>
                    </c:forEach>

                </tbody>
            </table>

        </c:if>
        <c:if test="${empty result}">
            <h2>No record is matched!!</h2>
        </c:if>     
    </c:if>
    <c:if test="${not empty sessionScope.UPDATE_ERRORS}">
        <c:if test="${not empty sessionScope.UPDATE_ERRORS.fullNameLengthErr}">
            <font color="red">
            ${sessionScope.UPDATE_ERRORS.fullNameLengthErr}
            </font>
        </c:if>             
        <c:remove var="UPDATE_ERRORS" scope="session"/>
    </c:if>  
    <c:if test="${not empty requestScope.DELETE_INFO}">
        <font color="red">
        ${requestScope.DELETE_INFO}
        </font>
    </c:if>  
    <c:if test="${not empty sessionScope.UPDATE_INFO}">
        <font color="red">
        ${sessionScope.UPDATE_INFO}
        </font>
        <c:remove var="UPDATE_INFO" scope="session"/>
    </c:if>
</body>
</html>
