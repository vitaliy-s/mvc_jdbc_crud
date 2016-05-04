<%--
  Created by IntelliJ IDEA.
  User: vitaliy
  Date: 04.05.16
  Time: 17:15
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<html>
<head>
    <title>New/Edit User</title>
</head>
<body>
<div align="center">
    <h1>New/Edit User</h1>
    <form:form action="saveUser" method="post" modelAttribute="user">
        <table>
            <form:hidden path="id"/>
            <tr>
                <td>Name:</td>
                <td><form:input path="name"/></td>
            </tr>
            <tr>
                <td>Password:</td>
                <td><form:input path="password"/></td>
            </tr>
            <tr>
                <td>Mail:</td>
                <td><form:input path="mail"/></td>
            </tr>
            <tr>
                <td colspan="2" align="center"><input type="submit" value="Save"></td>
            </tr>
        </table>
    </form:form>
</div>
</body>
</html>
