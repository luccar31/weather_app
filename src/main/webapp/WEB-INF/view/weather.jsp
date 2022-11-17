<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!doctype html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <meta name="viewport"
          content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <link rel="stylesheet" href="css/bootstrap.css">
    <link rel="stylesheet" href="css/my-style.css">
    <title>Weather</title>
</head>
<body class="container fancy-bg">
    <div class="row p-4">
        <h2 class="text-center">Ultima vez actualizado: ${lastUpdated}</h2>
    </div>
    <div class="row row-cols-3">
        <c:forEach var="location" items="${weatherList}" >
            <div class="col g-3">
                <div class="card h-100 shadow-lg border-0">
                    <div class="card-header mb-1 card-color">
                        <h5 class="card-title text-nowrap text-truncate">${location.locName}</h5>
                        <h6 class="card-subtitle text-muted text-nowrap text-truncate mb-1">${location.province}</h6>
                    </div>
                    <div class="card-body">
                        <p class="card-text"><span class="fw-bold">Temperatura: </span>${location.weatherDetails.temperature}°C</p>
                    </div>
                    <div class="card-body">
                        <p class="text-muted"><span class="fw-bold">Api ID:</span> ${location.apiId}</p>
                        <p class="text-muted"><span class="fw-bold">Database ID:</span> ${location.id}</p>
                    </div>
                </div>
            </div>
        </c:forEach>
    </div>
</body>
</html>