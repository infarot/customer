<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%--
  Created by IntelliJ IDEA.
  User: Dawid
  Date: 2019-01-12
  Time: 7:14 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Add new customer</title>
    <link type="text/css"
          rel="stylesheet"
          href="${pageContext.request.contextPath}/resources/style.css">
    <link type="text/css"
          rel="stylesheet"
          href="${pageContext.request.contextPath}/resources/add-customer-style.css">
</head>
<body>
<div id="wrapper">
    <div id=header>
        <h2>CRM - Customer Relationship Manager</h2>
    </div>
</div>
<div id="container">
    <h3>Save customer</h3>
    <form:form action="saveCustomer" modelAttribute="customer" method="post">
        <table>
            <tbody>
            <tr>
                <td><label>First name:</label></td>
                <td><form:input path="firstName"/></td>
            </tr>
            <tr>
                <td><label>Last name:</label></td>
                <td><form:input path="lastName"/></td>
            </tr>
            <tr>
                <td><label>Email:</label></td>
                <td><form:input path="email"/></td>
            </tr>
            <tr>
                <td></td>
                <td><input type="submit" value="Save" class="save"></td>
            </tr>
            </tbody>
        </table>
    </form:form>
</div>
<div style="clear: both"></div>
<p>
    <a href="${pageContext.request.contextPath}/customer/list">Back to list</a>
</p>

</body>
</html>
