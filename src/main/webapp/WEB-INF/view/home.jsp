<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!doctype html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <meta name="viewport"
          content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <link rel="stylesheet" href="css/bootstrap.css">
    <title>Home</title>
</head>
<body class="container">
	<h1>Hola! El timer está:</h1>
    <h3>
    <c:choose>
        <c:when test="${activated}">
            activado
        </c:when>
        <c:otherwise>
            desactivado
        </c:otherwise>
    </c:choose>
    </h3>
</body>
<script src="js/bootstrap.js"></script>
</html>