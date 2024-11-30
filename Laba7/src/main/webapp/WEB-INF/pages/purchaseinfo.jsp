<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib prefix="from" uri="http://www.springframework.org/tags/form" %>
<%@ page session="false" %>
<%@ page contentType="text/html; charset=UTF-8" %>
<meta http-equiv="refresh" content="30">
<html>
<head>
    <meta name="viewport" content="width=device-width, initial-scale=1.0"><link rel='stylesheet' type="text/css" href="<c:url value="/css/bootstrap.min.css"/>"/>
    <link rel="stylesheet" type="text/css" href="<c:url value="/css/bootstrap.min.css"/> "/>
</head>
<body>
<br/>
<div>
    <div class="container">
        <div  class="row">
            <a class="btn btn-primary col-xs-4 col-xs-offset-1 col-sm-2 col-md-1 col-lg-1" align="center" type="submit" href="/purchases">Выход</a>
        </div>
        <div class="row">
            <br/>
            <br/>
            <div class="col-xs-12 col-sm-10 col-sm-offset-1 col-md-8 col-md-offset-2 col-lg-8 col-lg-offset-2 ">
                <table class="table">
                    <tr class="bg-info">
                        <td >№</td>
                        <td >${purchase.id}</td>
                    </tr>
                    <tr>
                        <td >Марка</td>
                        <td >${purchase.name}</td>
                    </tr>
                    <tr>
                        <td >Марка</td>
                        <td >${purchase.model}</td>
                    </tr>
                    <tr>
                        <td >Количество</td>
                        <td >${purchase.amount}</td>
                    </tr>
                </table>

            </div>
        </div>
    </div>
</div>
</body>
</html>
