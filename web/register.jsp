<%-- 
    Document   : register
    Created on : May 22, 2020, 10:26:56 AM
    Author     : Dell
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Register Page</title>
        <%@include file="template/login1.jsp" %>
    </head>
    <body>
        <div class="limiter">
            <div class="container-login100">
                <div class="wrap-login100">
                    <form action="/LoginTest/MainController" method="POST" class="login100-form validate-form">
                        <span class="login100-form-title p-b-26">
                            Register
                        </span>
                        <span class="login100-form-title p-b-48">
                            <i class="zmdi zmdi-font"></i>
                        </span>
                        <div class="wrap-input100 validate-input" data-validate = "Valid email example: aaa@gmail.com">
                            <input id="email" class="input100" type="text" name="txtEmail" required=""/>
                            <span class="focus-input100" data-placeholder="Email"></span>
                        </div>
                        <div class="wrap-input100 validate-input" data-validate = "Valid name example: John">
                            <input id="name" class="input100" type="text" name="txtName" required=""/>
                            <span class="focus-input100" data-placeholder="Name"></span>
                        </div>
                        <div class="wrap-input100 validate-input" data-validate="Enter password">
                            <span class="btn-show-pass">
                                <i class="zmdi zmdi-eye"></i>
                            </span>
                            <input id="pass" class="input100" type="password" name="txtPassword" required=""/>
                            <span class="focus-input100" data-placeholder="Password"></span>
                        </div>
                        <div class="wrap-input100 validate-input" data-validate="Confirm password">
                            <span class="btn-show-pass">
                                <i class="zmdi zmdi-eye"></i>
                            </span>
                            <input class="input100" id="pass1" type="password" name="txtConfirmPass" required=""/>
                            <span class="focus-input100" data-placeholder="Confirm Password"></span>
                        </div>

                        <div class="container-login100-form-btn">
                            <div class="wrap-login100-form-btn">
                                <div class="login100-form-bgbtn"></div>
                                <button class="login100-form-btn" value="register" name="action" onclick="return checkRegister()">
                                    Register
                                </button>
                            </div>
                        </div>
                        <div class="text-center p-t-115">
                                <span class="txt1">
                                    Back To Login
                                </span>

                                <a class="txt2" href="login.jsp">
                                    Login
                                </a>
                            </div>
                    </form>
                </div>
            </div>
        </div>
        <div id="dropDownSelect1"></div>
        <%@include file="template/login2.jsp" %>
        
<!--        <form action="/LoginTest/MainController" method="POST">
            Email: <input id="email" type="text" name="txtEmail"/><br/>
            Password: <input id="pass" type="password" name="txtPassword" required=""/><br/>
            Confirm password:  <input id="pass1" type="password" name="txtConfirmPass" required=""/><br/>
            Name: <input id="name" type="text" name="txtName" required=""/><br/>
            <input type="submit" value="register" name="action" onclick="return checkRegister()" />
        </form>-->
    </body>
</html>
<script>
    function checkRegister() {
        var email = document.getElementById("email").value;
        var pass = document.getElementById("pass").value;
        var pass1 = document.getElementById("pass1").value;
        var name = document.getElementById("name").value;

        if (!email.match("^[a-z][a-z0-9_\.]{2,32}@[a-z0-9]{2,}(\.[a-z0-9]{2,4}){1,2}$")) {
            alert("Invalid Email");
            document.getElementById("email").focus();
            return false;
        } else if (pass !== pass1) {
//            trong java script không có == , chỉ có ===, !== dùng cho so sánh cả số và string
            alert("Invalid Confirm Password !");
            document.getElementById("pass1").focus();
            return false;
        } else {
            return true;
        }
    }

</script>
