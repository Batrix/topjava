<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Meal list</title>
</head>
<body>
<h2><a href="index.html">Home</a></h2>
<h2>Meal list</h2>
<hr>
<table border="1" cellpadding="8" cellspacing="0">
    <tr>
        <th>Date&Time</th>
        <th>Description</th>
        <th>Calories</th>
    </tr>
    <c:forEach items="${mealList}" var="meals">
        <jsp:useBean id="meals" scope="page" type="ru.javawebinar.topjava.model.MealWithExceed"/>
        <tr bgcolor="${meals.exceed ? 'tomato' : '#adff2f'}">
            <td>${meals.dateTime.toLocalDate()} ${meals.dateTime.toLocalTime()}</td>
            <td><c:out value="${meals.description}"/></td>
            <td><c:out value="${meals.calories}"/></td>
        </tr>
    </c:forEach>
</table>
</body>
</html>
