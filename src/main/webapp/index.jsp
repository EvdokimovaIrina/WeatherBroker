<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Погодный брокер</title>
</head>
<body>

<h3>Запрос погоды с ресурса "weather.yahoo.com"
    Укажите город:</h3>


<form action="/rest/weather" method="GET">
    <p><input required type="text" name="city"></p>

    <td><input type="submit" value="Получить погоду"/>
    </td>
</form>

<h3>Получить ранее запрошенную погоду
    Укажите город:</h3>

<form action="/rest/weather/xml" method="GET">
    <p><input required type="text" name="city"></p>
    <input type="submit" value="Узнать погоду (формат xml)"/>
    </form>


    <form action="/rest/weather/json" method="GET">
        <p><input required type="text" name="city"></p>
        <input type="submit" value="Узнать погоду (формат json)"/>
    </form>



</body>
</html>
