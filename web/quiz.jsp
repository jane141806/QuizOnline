<%-- 
    Document   : quiz
    Created on : Jun 1, 2020, 1:46:51 PM
    Author     : Dell
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Quiz Page</title>
    </head>
    <body>
        <h1>Name: ${sessionScope.USER.name}</h1>
        <h1>Subject ID: ${requestScope.QUESTIONDTO.idSubject} </h1>
        <h1>Total number of questions: ${requestScope.numQuestion} </h1>
        <p id="timer"></p>

        <br/><br/>

        Question ${requestScope.currentNum + 1}: ${requestScope.QUESTIONDTO.question}<br/>
        <form action="TakeQuizController" method="POST">
            <TABLE>
                <!--                nếu đã chọn thì tích vào câu đã chọn-->
                <!--                answer 1-->
                <c:if test="${requestScope.QUESTIONDTO.chooseAnswer == requestScope.QUESTIONDTO.answer1}" var="answer1Correct">
                    <tr>
                        <td><input type="radio" name="quiz" value="${requestScope.QUESTIONDTO.answer1}" checked/>${requestScope.QUESTIONDTO.answer1}</td>
                    </tr>
                </c:if>
                <c:if test="${!answer1Correct}">
                    <tr>
                        <td><input type="radio" name="quiz" value="${requestScope.QUESTIONDTO.answer1}" />${requestScope.QUESTIONDTO.answer1}</td>
                    </tr>
                </c:if>
                <!--                answer 2-->
                <c:if test="${requestScope.QUESTIONDTO.chooseAnswer == requestScope.QUESTIONDTO.answer2}" var="answer2Correct">
                    <tr>
                        <td><input type="radio" name="quiz" value="${requestScope.QUESTIONDTO.answer2}" checked/>${requestScope.QUESTIONDTO.answer2}</td>
                    </tr>
                </c:if>
                <c:if test="${!answer2Correct}">
                    <tr>
                        <td><input type="radio" name="quiz" value="${requestScope.QUESTIONDTO.answer2}" />${requestScope.QUESTIONDTO.answer2}</td>
                    </tr>
                </c:if>
                <!--                answer 3-->
                <c:if test="${requestScope.QUESTIONDTO.chooseAnswer == requestScope.QUESTIONDTO.answer3}" var="answer3Correct">
                    <tr>
                        <td><input type="radio" name="quiz" value="${requestScope.QUESTIONDTO.answer3}" checked/>${requestScope.QUESTIONDTO.answer3}</td>
                    </tr>
                </c:if>
                <c:if test="${!answer3Correct}">
                    <tr>
                        <td><input type="radio" name="quiz" value="${requestScope.QUESTIONDTO.answer3}" />${requestScope.QUESTIONDTO.answer3}</td>
                    </tr>
                </c:if>
                <!--                answer 4-->
                <c:if test="${requestScope.QUESTIONDTO.chooseAnswer == requestScope.QUESTIONDTO.answer4}" var="answer4Correct">
                    <tr>
                        <td><input type="radio" name="quiz" value="${requestScope.QUESTIONDTO.answer4}" checked/>${requestScope.QUESTIONDTO.answer4}</td>
                    </tr>
                </c:if>
                <c:if test="${!answer4Correct}">
                    <tr>
                        <td><input type="radio" name="quiz" value="${requestScope.QUESTIONDTO.answer4}" />${requestScope.QUESTIONDTO.answer4}</td>
                    </tr>
                </c:if>

                <input type="hidden" name="currentNum" value="${requestScope.currentNum + 1}"/>
                <input type="hidden" name="idSubject" value="${requestScope.QUESTIONDTO.idSubject}"/>
                <input type="hidden" name="numQuestion" value="${requestScope.numQuestion}"/>
                <c:if test="${requestScope.currentNum != (requestScope.numQuestion - 1)}">
                    <tr><td>
                            <input type="submit" value="next" name="action" /></td></tr>
                        </c:if>
                        <c:if test="${requestScope.currentNum != 0}">
                    <tr><td>
                            <input  type="submit" value="back" name="action" /></td></tr>
                        </c:if>
            </TABLE>
        </form>
        <br/><br/>
        <form action="MainController" method="POST">
            <input type="hidden" name="email" value="${sessionScope.USER.email}"/>
            <input type="hidden" name="idSubject" value="${requestScope.QUESTIONDTO.idSubject}"/>
            <input type="hidden" name="TIMER" value="${sessionScope.TIMER}"/>
            <input id="submit" type="submit" value="SUBMIT QUIZ" name="action" />

        </form>
        <script>
// Set the date we're counting down to
            var countDownDate = new Date("${sessionScope.TIMER}").getTime();

// Update the count down every 1 second
            var x = setInterval(function () {
// Get today's date and time
                var now = new Date().getTime();

// Find the distance between now and the count down date
                var distance = countDownDate - now;

// Time calculations for days, hours, minutes and seconds
                var days = Math.floor(distance / (1000 * 60 * 60 * 24));
                var hours = Math.floor((distance % (1000 * 60 * 60 * 24)) / (1000 * 60 * 60));
                var minutes = Math.floor((distance % (1000 * 60 * 60)) / (1000 * 60));
                var seconds = Math.floor((distance % (1000 * 60)) / 1000);

// Display the result in the element with id="demo"
                document.getElementById("timer").innerHTML = days + "d " + hours + "h "
                        + minutes + "m " + seconds + "s ";

// If the count down is finished, write some text
                if (distance < 0) {
                    clearInterval(x);
                    document.getElementById("submit").click();
                }
            }, 1000);
        </script>
    </body>

</html>
