<%-- 
    Document   : login
    Created on : May 21, 2020, 11:19:17 AM
    Author     : Dell
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Login Page</title>
        <%@include file="template/login1.jsp" %>
        <!--===============================================================================================-->
    </head>
    <body>

        <div class="limiter">
            <div class="container-login100">
                <div class="wrap-login100">
                    <div>
                        <span class="login100-form-title p-b-26">
                            <p style="color:red">${requestScope.SUCCESS}</p>
                        </span>
                    </div>
                    <form action="MainController" method="POST" class="login100-form validate-form">
                        <span class="login100-form-title p-b-26">
                            Welcome
                        </span>
                        <span class="login100-form-title p-b-48">
                            <i class="zmdi zmdi-font"></i>
                        </span>
                        <div class="wrap-input100 validate-input" data-validate = "Valid email example: aaa@gmail.com">
                            <input id="email" class="input100" type="text" name="txtEmail" required="">
                            <span class="focus-input100" data-placeholder="Email"></span>
                        </div>
                        <div class="wrap-input100 validate-input" data-validate="Enter password">
                            <span class="btn-show-pass">
                                <i class="zmdi zmdi-eye"></i>
                            </span>
                            <input class="input100" type="password" name="txtPassword" required="">
                            <span class="focus-input100" data-placeholder="Password"></span>
                        </div>

                        <div class="container-login100-form-btn">
                            <div class="wrap-login100-form-btn">
                                <div class="login100-form-bgbtn"></div>
                                <button class="login100-form-btn" value="login" name="action" onclick="return checkLogin()">
                                    Login
                                </button>
                                <!--                                <input class="login100-form-btn" type="submit" value="login" name="action" onclick="return checkLogin()"/>-->
                            </div>
                        </div>
                        <!--                        Email: <input type="text" id="email" name="txtEmail" required=""/><br/>-->
                        <!--                        Password: <input type="password" name="txtPassword" required=""/><br/>
                                                <input type="submit" value="login" name="action" onclick="return checkLogin()"/>-->

                        <div class="text-center p-t-115">
                            <span class="txt1">
                                Don’t have an account?
                            </span>

                            <a class="txt2" href="register.jsp">
                                Sign Up
                            </a>
                        </div>
                    </form>
                </div>
            </div>
        </div>
        <div id="dropDownSelect1"></div>
        <%@ include file="template/login2.jsp" %>
    </body>
</html>

<script>
    function checkLogin() {
        var email = document.getElementById("email").value;
        if (!email.match("^[a-z][a-z0-9_\.]{2,32}@[a-z0-9]{2,}(\.[a-z0-9]{2,4}){1,2}$")) {
            alert("Invalid Email !");
            document.getElementById("email").focus();
            return false;
        } else {
            return true;
        }
    }
</script>
