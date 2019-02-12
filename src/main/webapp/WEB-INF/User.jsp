<%--
  Created by IntelliJ IDEA.
  User: bartv
  Date: 2/11/2019
  Time: 5:33 PM
  To change this template use File | Settings | File Templates.
--%>

<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
<head>
    <title>User Create</title>
</head>
<body>
<h1>Users</h1>
<ul>
    <c:forEach items="${Userlist}" var="user">
        <Li>${user}</Li>
    </c:forEach>
</ul>
<form name="CreateUser" action="${pageContext.request.contextPath}/CreatedUser" method="post">
<table>
    <tbody>
    <tr>
        <td>Name:</td>
        <td><input type="text" name="UserName" value="" size="20"/></td>
    </tr>
    </tbody>
</table>
    <input type="submit" value="submit" name="submit">
</form>
</body>
</html>
