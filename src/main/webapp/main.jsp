<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Погодный брокер</title>
</head>
<body>

<form action="/weather" method="POST">
    <h3>Укажите город:</h3>
    <p> <input required type="text" name="city"></p>
    <input type="submit" value="Узнать погоду" />
</form>

</body>
</html>
