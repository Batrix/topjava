<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Meal list editor</title>
</head>
<body>
<h2><a href="index.html">Home</a></h2>
<h2>Meal list editor</h2>
<hr>
<h3>${param.action == 'create' ? 'Create meal' : 'Edit meal'}</h3>
<jsp:useBean id="meal" scope="request" type="ru.javawebinar.topjava.model.Meal"/>
<form method="post" action="meals">
    <input type="hidden" name="id" value="${meal.id}">
    <br>Date&Time<input type="datetime-local" name="datetime" value="${meal.dateTime}">
    <br>Description<input type="text" name="description" value="${meal.description}">
    <br>Calories<input type="number" name="calories" value="${meal.calories}">
    <br>
    <br>
    <input type="submit" value="Save">
    <button onclick="window.history.back()">Cancel</button>
</form>
</body>
</html>
