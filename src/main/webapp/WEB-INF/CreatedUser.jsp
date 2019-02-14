<%--
  Created by IntelliJ IDEA.
  User: bartv
  Date: 2/11/2019
  Time: 5:58 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
<head>
    <title>User Created</title>
</head>
<body>
<h1>User ${User} Created</h1>

<h1>Users</h1>
<ul>
    <c:forEach items="${Userlist}" var="user">
        <Li>${user}</Li>
    </c:forEach>
</ul>

</body>
</html>
