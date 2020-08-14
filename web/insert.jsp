<%-- 
    Document   : insert
    Created on : May 28, 2020, 1:53:36 PM
    Author     : Dell
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Insert Page</title>
        <%@include file="template/login1.jsp" %>
        <script src="https://code.jquery.com/jquery-3.5.1.min.js"></script>
    </head>
    <div class="limiter">
        <div class="container-login100">
            <div class="wrap-login100">
                <form id="insertQ" action="MainController" method="POST" class="login100-form validate-form">
                    <div>
                        <span class="login100-form-title p-b-26">
                            <p style="color:red">${requestScope.ERROR}</p>
                            <p style="color:red">${requestScope.SUCCESS}</p>
                        </span>
                    </div>
                    <span class="login100-form-title p-b-26">
                        Insert Question
                    </span>
                    <span class="login100-form-title p-b-48">
                        <i class="zmdi zmdi-font"></i>
                    </span>
                    <c:if test="${requestScope.DTO != null}" var="list">
                        Id Question:
                        <div class="wrap-input100 validate-input" data-validate = "">
                            <input id="idQuestion" class="input100" type="text" name="idQuestion" required="" value="${requestScope.DTO.idQuestion}"/>
                            <span class="focus-input100"></span>
                        </div>
                        Question:
                        <div class="wrap-input100 validate-input" data-validate = "">
                            <input id="question" class="input100" type="text" name="question" required="" value="${requestScope.DTO.question}"/>
                            <span class="focus-input100"></span>
                        </div>
                        Answer 1:
                        <div class="wrap-input100 validate-input" data-validate = "">
                            <input id="answer1" class="input100" type="text" name="answer1" required="" value="${requestScope.DTO.answer1}"/>
                            <span class="focus-input100"></span>
                        </div>
                        Answer 2:
                        <div class="wrap-input100 validate-input" data-validate = "">
                            <input id="answer2" class="input100" type="text" name="answer2" required="" value="${requestScope.DTO.answer2}"/>
                            <span class="focus-input100"></span>
                        </div>
                        Answer 3:
                        <div class="wrap-input100 validate-input" data-validate = "">
                            <input id="answer3" class="input100" type="text" name="answer3" required="" value="${requestScope.DTO.answer3}"/>
                            <span class="focus-input100"></span>
                        </div>
                        Answer 4:
                        <div class="wrap-input100 validate-input" data-validate = "">
                            <input id="answer4" class="input100" type="text" name="answer4" required="" value="${requestScope.DTO.answer4}"/>
                            <span class="focus-input100" ></span>
                        </div>
                        Correct Answer:
                        <div class="wrap-input100 validate-input" data-validate = "">
                            <span class="focus-input100"></span>
                            <select name="correctAnswer">
                                <option selected="">Answer1</option>
                                <option>Answer2</option>
                                <option>Answer3</option>
                                <option>Answer4</option>
                            </select>
                        </div>
                        Id Subject (ENG/PRJ311):
                        <div class="wrap-input100 validate-input" data-validate = "">
                            <input id="idSubject" class="input100" type="text" name="idSubject" required="" value="${requestScope.DTO.idSubject}"/>
                            <span class="focus-input100"></span>
                        </div>
                    </c:if>
                    <c:if test="${!list}">
                        <div class="wrap-input100 validate-input" data-validate = "">
                            <input id="idQuestion" class="input100" type="text" name="idQuestion" required="" />
                            <span class="focus-input100" data-placeholder="Id Question"></span>
                        </div>
                        <div class="wrap-input100 validate-input" data-validate = "">
                            <input id="question" class="input100" type="text" name="question" required="" />
                            <span class="focus-input100" data-placeholder="Question"></span>
                        </div>

                        <div class="wrap-input100 validate-input" data-validate = "">
                            <input id="answer1" class="input100" type="text" name="answer1" required="" />
                            <span class="focus-input100" data-placeholder="Answer 1"></span>
                        </div>

                        <div class="wrap-input100 validate-input" data-validate = "">
                            <input id="answer2" class="input100" type="text" name="answer2" required="" />
                            <span class="focus-input100" data-placeholder="Answer 2"></span>
                        </div>

                        <div class="wrap-input100 validate-input" data-validate = "">
                            <input id="answer3" class="input100" type="text" name="answer3" required="" />
                            <span class="focus-input100" data-placeholder="Answer 3"></span>
                        </div>

                        <div class="wrap-input100 validate-input" data-validate = "">
                            <input id="answer4" class="input100" type="text" name="answer4" required=""/>
                            <span class="focus-input100" data-placeholder="Answer 4"></span>
                        </div>

                        <div class="wrap-input100 validate-input" data-validate = "">
                            <span class="focus-input100" data-placeholder="Correct Answer"></span><br/><br/>
                            <select name="correctAnswer">
                                <option selected="">Answer1</option>
                                <option>Answer2</option>
                                <option>Answer3</option>
                                <option>Answer4</option>
                            </select>
                        </div>

                        <div class="wrap-input100 validate-input" data-validate = "">
                            <input id="idSubject" class="input100" type="text" name="idSubject" required=""/>
                            <span class="focus-input100" data-placeholder="Id Subject (ENG/PRJ311)"></span>
                        </div>
                    </c:if>

                    <div class="container-login100-form-btn">
                        <div class="wrap-login100-form-btn">
                            <div class="login100-form-bgbtn"></div>
                            <button class="login100-form-btn" value="insert" name="action">
                                Insert
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
    $("#insertQ").on("submit", function (event) {
        if (checkInsert()) {
        } else {
            event.preventDefault();
        }
    });

    function checkInsert() {
        var idSubject = document.getElementById("idSubject").value;
        if (idSubject !== 'ENG' && idSubject !== 'PRJ311') {
//            trong java script không có == , chỉ có ===, !== dùng cho so sánh cả số và string
            alert("ID SUBJECT MUST BE ENG OR PRJ311");
            document.getElementById("idSubject").focus();
            return false;
        } else {
            return true;
        }
    }

</script>


