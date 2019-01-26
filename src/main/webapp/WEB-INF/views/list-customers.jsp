<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="security" uri="http://www.springframework.org/security/tags" %>
<%@page contentType="text/html" pageEncoding="UTF-8" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
"http://www.w3.org/TR/html4/loose.dtd">

<html>

<head>
    <title>List Customers</title>

    <!-- reference our style sheet -->

    <link type="text/css"
          rel="stylesheet"
          href="${pageContext.request.contextPath}/resources/style.css"/>

</head>

<body>

<div id="wrapper">
    <div id="header">
        <h2>CRM - Customer Relationship Manager</h2>
        Logged as: <security:authentication property="principal.username"/>
        <br>
        <security:authentication property="principal.authorities"/>
    </div>
</div>

<div id="container">
    <security:authorize access="hasAnyRole('MANAGER','ADMIN')">
    <div id="content">
        <input type="button" value="Add customer"
               onclick="window.location.href='showFormForAdd';return false"
               class="add-button"
        />
        </security:authorize>
        <br>
        <form:form action="search" method="post">
            Search customer:
            <label>
                <input type="text" name="search"/>
            </label>
            <input type="submit" value="Search" class="add-button"/>
        </form:form>

        <!--  add our html table here -->

        <table>
            <tr>
                <th>First Name</th>
                <th>Last Name</th>
                <th>Email</th>
                <th>Action</th>
            </tr>

            <!-- loop over and print our customers -->
            <c:forEach var="tempCustomer" items="${customers}">
                <c:url var="updateLink" value="/customer/showFormForUpdate">
                    <c:param name="customerId" value="${tempCustomer.id}"/>
                </c:url>
                <c:url var="deleteLink" value="/customer/deleteCustomer">
                    <c:param name="customerId" value="${tempCustomer.id}"/>
                </c:url>

                <tr>
                    <td> ${tempCustomer.firstName} </td>
                    <td> ${tempCustomer.lastName} </td>
                    <td> ${tempCustomer.email} </td>
                    <security:authorize access="hasRole('ADMIN')">
                    <td><a href="${updateLink}">Update</a>
                        |
                    <a href="${deleteLink}" onclick="if(!(confirm('are you sure?')))return false">Delete</a></td>
                    </security:authorize>
                </tr>

            </c:forEach>

        </table>

    </div>

</div>


</body>

</html>









