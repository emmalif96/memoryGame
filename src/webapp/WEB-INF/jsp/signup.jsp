<!DOCTYPE html>
<html xmlns:th="https://thymeleaf.org">
<head>
    <meta charset="UTF-8">
    <title>Signup</title>
</head>
<body>
<h2>Signup for an account</h2>
<form action="#" th:action="@{/signup}" th:object="${user}"  method="post">
    <input type="text" name="username" id="username" th:field="*{uName}"/>
    <input type="text" name="password" id="password" th:value="*{password}"/>
    <input type="submit" value="Sign up"/>
</form>
</body>
</html>
