<%--
  Created by IntelliJ IDEA.
  User: fiatf
  Date: 29.10.20
  Time: 18:42
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page isELIgnored="false" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
    <form method="post">
        <input type="hidden" name="action" value="EDIT">
        <input type="hidden" name="id" value="${requestScope.client.id}">
        <label>Name</label>
        <input type="text" name="name" value="${requestScope.client.name}">
        <label>Phone number</label>
        <input type="text" name="phoneNr" value="${requestScope.client.phoneNumber}">
        <label>Avatar</label>
        <input type="file" name="avatar">
        <input type="submit" value="Update">
    </form>

</body>
</html>
