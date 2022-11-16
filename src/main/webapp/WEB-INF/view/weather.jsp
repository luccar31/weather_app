<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!doctype html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <meta name="viewport"
          content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <title>Weather</title>
</head>
<body>
    <c:forEach var="location" items="weatherList" >
        <div>
            <p>${location.getId()}</p>
            <p>${location.getlocName()}</p>
            <p>${location.getProvince}</p>
            <p>${location.getWeatherDetails().getTemp()}</p>
        </div>
    </c:forEach>
</body>
</html>