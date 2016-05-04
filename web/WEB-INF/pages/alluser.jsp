<%--
  Created by IntelliJ IDEA.
  User: vitaliy
  Date: 04.05.16
  Time: 13:03
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>User Manager</title>
</head>
<body>
<%--<c:forEach var="user" items="${listuser}" varStatus="status">
    ${user.id}
    ${user.name}
    ${user.password}
    ${user.mail}
</c:forEach>--%>
<div align="center">
    <h1>User List</h1>
    <h3><a href="/newUser">New User</a></h3>
    <table border="1">
        <th>User_ID</th>
        <th>Name</th>
        <th>Password</th>
        <th>Mail</th>
        <th>Action</th>
<c:forEach var="user" items="${listuser}" varStatus="status">
<tr>
    <td>${user.id}</td>
    <td>${user.name}</td>
    <td>${user.password}</td>
    <td>${user.mail}</td>
    <td>
        <a href="/editUser?id=${user.id}">Edit</a>
        <a href="/deleteUser?id=${user.id}">Delete</a>
    </td>
</tr>
</c:forEach>
    </table>
</div>
</body>
</html>
