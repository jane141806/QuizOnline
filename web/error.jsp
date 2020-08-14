<%-- 
    Document   : error
    Created on : May 21, 2020, 11:51:02 AM
    Author     : Dell
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Error Page</title>
    </head>
    <body onload="checkExit()">
        <h1 style="color:red">${requestScope.ERROR}</h1>
    </body>
</html>
