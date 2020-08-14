<%-- 
    Document   : edit
    Created on : May 28, 2020, 10:54:23 AM
    Author     : Dell
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Edit Question Page</title>
        <%@include file="template/login1.jsp" %>
        <script src="https://code.jquery.com/jquery-3.5.1.min.js"></script>
    </head>
    <div class="limiter">
        <div class="container-login100">
            <div class="wrap-login100">
                <form id="editQ" action="MainController" method="POST" class="login100-form validate-form">
                    <span class="login100-form-title p-b-26">
                        Edit Question
                    </span>
                    <span class="login100-form-title p-b-48">
                        <i class="zmdi zmdi-font"></i>
                    </span>
                    Id Question : 
                    <div class="wrap-input100 validate-input" data-validate = "">
                        <input id="idQuestion" class="input100" type="text" name="idQuestion" readonly="true" value="${param.idQuestion}"/>
                        <span class="focus-input100"></span>
                    </div>
                    Question : 
                    <div class="wrap-input100 validate-input" data-validate = "">
                        <input id="question" class="input100" type="text" name="question" value="${param.question}" required=""/>
                        <span class="focus-input100"></span>
                    </div>
                    Answer 1: 
                    <div class="wrap-input100 validate-input" data-validate = "">
                        <input id="answer1" class="input100" type="text" name="answer1" value="${param.answer1}" required=""/>
                        <span class="focus-input100"></span>
                    </div>
                    Answer 2: 
                    <div class="wrap-input100 validate-input" data-validate = "">
                        <input id="answer2" class="input100" type="text" name="answer2" value="${param.answer2}" required=""/>
                        <span class="focus-input100"></span>
                    </div>
                    Answer 3: 
                    <div class="wrap-input100 validate-input" data-validate = "">
                        <input id="answer3" class="input100" type="text" name="answer3" value="${param.answer3}" required=""/>
                        <span class="focus-input100"></span>
                    </div>
                    Answer 4: 
                    <div class="wrap-input100 validate-input" data-validate = "">
                        <input id="answer4" class="input100" type="text" name="answer4" value="${param.answer4}" required=""/>
                        <span class="focus-input100"></span>
                    </div>
                    Correct Answer: 
                    <div class="wrap-input100 validate-input" data-validate = "">
                        <input id="correctAnswer" class="input100" type="text" name="correctAnswer" value="${param.correctAnswer}" required=""/>
                        <span class="focus-input100"></span>
                    </div>
                    Id Subject (ENG/PRJ311): 
                    <div class="wrap-input100 validate-input" data-validate = "">
                        <input id="idSubject" class="input100" type="text" name="idSubject" value="${param.idSubject}" required=""/>
                        <span class="focus-input100"></span>
                    </div>
                    Question Status (active/deactive):
                    <div class="wrap-input100 validate-input" data-validate = "">
                        <input id="quesStatus" class="input100" type="text" name="quesStatus" value="${param.quesStatus}" required=""/>
                        <span class="focus-input100"></span>
                    </div>

                    <input type="hidden" name="txtSearch" value="${param.txtSearch}"/> 
                    <input type="hidden" name="chooseBox" value="${param.chooseBox}"/> 

                    <div class="container-login100-form-btn">
                        <div class="wrap-login100-form-btn">
                            <div class="login100-form-bgbtn"></div>
                            <button class="login100-form-btn" value="edit" name="action">
                                Edit
                            </button>
                        </div>
                    </div>
                    <div class="text-center p-t-115">
                        <span class="txt1">
                            Back To Admin Page
                        </span>

                        <a class="txt2" href="admin.jsp">
                            BACK
                        </a>
                    </div>
                </form>
            </div>
        </div>
    </div>
    <div id="dropDownSelect1"></div>
    <%@include file="template/login2.jsp" %>

</body>
</html>
<script>
    $("#editQ").on("submit", function(event) {
        if(checkEdit()){
        } else {
             event.preventDefault();
        }
        
    });
    
    function checkEdit() {
        
        var answer1 = document.getElementById("answer1").value;
        var answer2 = document.getElementById("answer2").value;
        var answer3 = document.getElementById("answer3").value;
        var answer4 = document.getElementById("answer4").value;
        var correctAnswer = document.getElementById("correctAnswer").value;
        var idSubject = document.getElementById("idSubject").value;
        var quesStatus = document.getElementById("quesStatus").value;
        
        if (correctAnswer !== answer1 && correctAnswer !== answer2 && correctAnswer !== answer3 && correctAnswer !== answer4){
            alert("Invalid Correct Answer");
            document.getElementById("correctAnswer").focus();
            return false;
        } else if (idSubject !== 'ENG' && idSubject !== 'PRJ311') {
//            trong java script không có == , chỉ có ===, !== dùng cho so sánh cả số và string
            alert("ID SUBJECT MUST BE ENG OR PRJ311");
            document.getElementById("idSubject").focus();
            return false;
        }else if (quesStatus !== 'active' && quesStatus !== 'deactive') {
//            trong java script không có == , chỉ có ===, !== dùng cho so sánh cả số và string
            alert("Satus must be active or deactive");
            document.getElementById("quesStatus").focus();
            return false;
        } else {
            return true;
        }
    }

</script>

