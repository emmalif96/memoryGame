<jsp:useBean id="loggedinuser" scope="session" type=""/>
<!DOCTYPE html>
<html xmlns:th="https://thymeleaf.org">
<head>
    <meta charset="UTF-8">
    <title>Logged In User</title>
</head>
<body>
<p th:text="${loggedinuser.uName}"></p>

</body>
</html>