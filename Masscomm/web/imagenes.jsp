<%-- 
    Document   : imagenes
    Created on : 23-sep-2016, 12:37:50
    Author     : claencina
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <c:forEach items="${imagen}" var="im" varStatus="i"> 
            <a href="<c:url value="Im"><c:param name="id" value="${im.id}"/></c:url>">Foto</a>
        </c:forEach>
    </body>
</html>
