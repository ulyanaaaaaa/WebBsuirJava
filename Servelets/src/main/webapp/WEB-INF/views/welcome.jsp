<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>

    <!-- Bootstrap core CSS -->
    <link href="webjars/bootstrap/4.3.1/css/bootstrap.min.css" rel="stylesheet">

    <style>
        .footer {
            position: absolute;
            bottom: 0;
            width: 100%;
            height: 60px;
            background-color: #f5f5f5;
        }

        .footer .container {
            width: auto;
            max-width: 680px;
            padding: 0 15px;
        }
    </style>
</head>
<body>

<nav role="navigation" class="navbar navbar-default">
    <div class="navbar-header">
        <img src="https://www.kv.by/sites/default/files/user7743/logo_iba_group.jpg" width="50" height="50">
    </div>

    <div class="navbar-collapse">
        <ul class="nav navbar-nav">
            <li class="active"><a href="#">Home</a></li>
        </ul>
        <ul class="nav navbar-nav navbar-right">
            <li><a href="LoginServlet">Login</a></li>
            <li><a href="LogoutServlet">Logout</a></li>
        </ul>
    </div>
</nav>

<div class="container">
    <h2>Welcome ${name}</h2>

    <table class="table">
        <caption>Список вашей группы</caption>
        <thead>
        <tr>
            <th>Имя</th>
            <th>Телефон</th>
            <th>Email</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach items="${group}" var="person">
            <tr>
                <td>${person.name}</td>
                <td>${person.phone}</td>
                <td>${person.email}</td>
            </tr>
        </c:forEach>
        </tbody>
    </table>

    <p><font color="red">${errorMessage}</font></p>
    <form method="POST" action="GroupListServlet">
        <h4>Новый:</h4>
        <p>Введите имя: <input name="nname" type="text" /></p>
        <p>Введите телефон: <input name="nphone" type="text" /></p>
        <p>Введите email: <input name="nemail" type="text" /></p>
        <input name="add" type="submit" class="btn btn-primary" />
    </form>
</div>

<footer class="footer">
    <div class="container">
        <p>2021 Все права защищены</p>
    </div>
</footer>

<script src="webjars/jquery/3.3.1/jquery.min.js"></script>
<script src="webjars/bootstrap/4.3.1/js/bootstrap.min.js"></script>
</body>
</html>