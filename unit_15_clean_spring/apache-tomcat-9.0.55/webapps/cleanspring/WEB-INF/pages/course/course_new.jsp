<%--
  Created by IntelliJ IDEA.
  User: egorfuntusov
  Date: 27.01.2022
  Time: 16:53
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<form:form action = "${pageContext.request.contextPath}/courses/new" method = "post" modelAttribute="course">
    <br />
    name: <form:select items="${courseNames}" var="name" path="name" />
    <input type = "submit" value = "Submit" />
</form:form>
</body>
</html>
