<%-- 
    Document   : user
    Created on : May 26, 2020, 1:41:47 PM
    Author     : Dell
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <%@include file="template/main1.jsp" %>
        <title>User Page</title>
    </head>

    <body onload="callTable()" id="body">
        <header id="navigation" class="navbar-inverse navbar-fixed-top animated-header">
            <div class="container">
                <div class="navbar-header">
                    <!-- responsive nav button -->
                    <button type="button" class="navbar-toggle" data-toggle="collapse" data-target=".navbar-collapse">
                        <span class="sr-only">Toggle navigation</span>
                        <span class="icon-bar"></span>
                        <span class="icon-bar"></span>
                        <span class="icon-bar"></span>
                    </button>
                    <!-- /responsive nav button -->

                    <!-- logo -->
                    <h1 class="navbar-brand">
                        <a href="#body">Blue</a>
                    </h1>
                    <!-- /logo -->
                </div>

                <!-- main nav -->
                <nav class="collapse navbar-collapse navbar-right" role="navigation">
                    <ul id="nav" class="nav navbar-nav">
                        <li><a href="#body">Home</a></li>
                        <li><a href="#service">Take Quiz</a></li>
                        <li><a href="#social">Show History</a></li>
                    </ul>
                </nav>
                <!-- /main nav -->

            </div>
        </header>
        <!--
        End Fixed Navigation
        ==================================== -->
        <section id="home-slider">
            <div id="slider" class="sl-slider-wrapper">

                <div class="sl-slider">

                    <div class="sl-slide" data-orientation="horizontal" data-slice1-rotation="-25" data-slice2-rotation="-25" data-slice1-scale="2" data-slice2-scale="2">

                        <div class="bg-img bg-img-1"></div>

                        <div class="slide-caption">
                            <div class="caption-content">
                                <h2 class="animated fadeInDown">Quiz Online Website</h2>
                                <span class="animated fadeInDown">Welcome ${sessionScope.USER.name}</span>
                                <a href="login.jsp" class="btn btn-blue btn-effect">Log out</a>
                            </div>
                        </div>

                    </div>
                </div><
            </div><!-- /slider-wrapper -->
        </section>

        <section id="service" >
            <div class="container">
                <div class="row"><br/><br/><br/>
                    <div class="sec-title text-center">
                        <h2 class="wow animated bounceInLeft">Take Quiz</h2>
                        <p class="wow animated bounceInRight">Let's review everything you learned</p>
                        <c:if test="${requestScope.QUIZSUCCESS != null}">
                            <p class="wow animated bounceInLeft" style="color:red">${requestScope.QUIZSUCCESS} ${requestScope.score}/10 - (Correct Answer: ${requestScope.numCorrectAnswer}/${requestScope.totalQuestion})</p>
                        </c:if>

                    </div>
                    <div class=" text-center wow animated zoomIn" data-wow-delay="0.3s">
                        <div class="service-item">
                            <div class="service-icon">
                                <i class="fa fa-tasks fa-3x"></i>
                            </div><br/><br/>

                            <form action="MainController" method="POST">
                                Choose Subject: 
                                <select name="subject">
                                    <option selected="">ENG</option>
                                    <option>PRJ311</option>
                                </select>
                                <h3><input type="submit" value="View Quiz" name="action" /></h3>
                            </form>
                            <div class="sec-title text-center">
                                <div style="text-align: center" class="sec-title text-center" id="subjectTable">
                                    <c:if test="${requestScope.SUBJECT != null}">
                                        <c:if test="${not empty requestScope.SUBJECT}" var="list">
                                            <table border="1" class="sec-title text-center">                            
                                                <thead>
                                                    <tr>
                                                        <th>Id Subject</th>
                                                        <th>Number of questions</th>
                                                        <th>Time</th>
                                                        <th>Take Quiz</th>
                                                    </tr>
                                                </thead>
                                                <tbody>
                                                    <c:forEach items="${requestScope.SUBJECT}" var="dto" varStatus="counter">
                                                        <tr>
                                                            <td>${dto.idSubject}</td>
                                                            <td>${dto.numQuestion}</td>
                                                            <td>${dto.time}</td>
                                                            <td>
                                                                <form action="MainController" method="POST">
                                                                    <input type="hidden" name="idSubject" value="${dto.idSubject}">
                                                                    <input type="hidden" name="numQuestion" value="${dto.numQuestion}">
                                                                    <input type="hidden" name="time" value="${dto.time}">
                                                                    <input type="submit" value="Take Quiz" name="action" /> 
                                                                </form>
                                                            </td>
                                                        </tr>
                                                    </c:forEach>
                                                </tbody>
                                            </table>
                                        </c:if>
                                        <c:if test="${!list}">
                                            <h1 style="color: red">No Record Found</h1>
                                        </c:if>
                                    </c:if>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div><br/><br/><br>
        </section>

        <section id="social" class="parallax">
            <div class="overlay">
                <div class="container">
                    <div class="row">

                        <div class="sec-title text-center white wow animated fadeInDown">
                            <h2>View Quiz History</h2>
                            <p>You did it well. I'm proud of you</p>
                        </div>

                        <div class="input-field">
                            <form action="MainController" method="POST">
                                <input type="text" name="txtSearch" class="subscribe form-control" placeholder="Search By ID Subject (ENG/PRJ311) " />
                                <ul class="social-button">
                                    <li class="wow animated zoomIn animated" style="visibility: visible; animation-name: zoomIn;">
                                        <input type="submit" value="Search History" name="action"/>
                                    </li>
                                </ul>
                            </form>
                        </div>

                        <div class="sec-title text-center white wow animated fadeInDown sparkline13-graph" id="table">
                            <div class="datatable-dashv1-list custom-datatable-overright" style="text-align: center">
                                <c:if test="${requestScope.LIST != null}">
                                    <c:if test="${not empty requestScope.LIST}" var="list">
                                        <table border="1" >                            
                                            <thead>
                                                <tr>
                                                    <th>Id Quiz</th>
                                                    <th>Id Subject</th>
                                                    <th>Score</th>
                                                    <th>Doing Date</th>

                                                </tr>
                                            </thead>
                                            <tbody>
                                                <c:forEach items="${requestScope.LIST}" var="dto" varStatus="counter">
                                                    <tr>
                                                        <td>${dto.idQuiz}</td>
                                                        <td>${dto.idSubject}</td>
                                                        <td>${dto.score}</td>
                                                        <td>${dto.doingDate}</td>
                                                    </tr>
                                                </c:forEach>
                                            </tbody>
                                        </table>
                                        <div class="fixed-table-pagination">
                                            <div class="pull-left pagination-detail">
                                                <span class="pagination-info">Showing 1 to</span>
                                                <span class="page-list"><span class="btn-group dropup">
                                                        <button type="button" class="btn btn-default dropdown-toggle" data-toggle="dropdown">
                                                            <span class="page-size">20</span> 
                                                            <span class="caret"></span>
                                                        </button>
                                                        <ul class="dropdown-menu" role="menu">
                                                            <li role="menuitem" class="active"><a href="#">20</a></li>
                                                        </ul>
                                                    </span> rows per page</span>
                                            </div>
                                            <div class="pull-right pagination">
                                                <ul class="pagination">
                                                    <c:forEach var="i" begin="1" end="${requestScope.pageNum}">
                                                        <c:if test="${i == requestScope.currentNum}}" var="hasCurrentNum">
                                                            <li class="page-number active"><a href="#">${i}</a></li>
                                                            </c:if>
                                                            <c:if test="${!hasCurrentNum}">
                                                            <li class="page-number"><a href="SearchController?txtSearch=${param.txtSearch}&pageNum=${requestScope.pageNum}&currentNum=${i}">${i}</a></li>
                                                            </c:if>
                                                        </c:forEach>
                                                </ul>
                                            </div>
                                        </div>
                                    </c:if>
                                    <c:if test="${!list}">
                                        <h1 style="color: red">No Record Found</h1>
                                    </c:if>
                                </c:if>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </section>

        <footer id="footer">
            <div class="container">
                <div class="row text-center">
                    <div class="footer-content">
                        <div class="wow animated fadeInDown">
                            <p>Jane Nguyen is me :></p>
                            <p>You can contact to support me</p>
                        </div>
                        <div class="footer-social">
                            <ul>
                                <li class="wow animated zoomIn"><a href="https://www.facebook.com/profile.php?id=100021563148041"><i class="fa fa-thumbs-up fa-3x"></i></a></li>
                                <li class="wow animated zoomIn" data-wow-delay="0.3s"><a href="https://twitter.com/explore"><i class="fa fa-twitter fa-3x"></i></a></li>
                                <li class="wow animated zoomIn" data-wow-delay="0.6s"><a href="https://www.skype.com/en/"><i class="fa fa-skype fa-3x"></i></a></li>
                                <li class="wow animated zoomIn" data-wow-delay="0.9s"><a href="http://www.phimmoi.net/phim/slam-dunk-5312/"><i class="fa fa-dribbble fa-3x"></i></a></li>
                                <li class="wow animated zoomIn" data-wow-delay="1.2s"><a href="https://www.youtube.com/channel/UC4yzoGuKkCL_8FzI-B-x83A"><i class="fa fa-youtube fa-3x"></i></a></li>
                            </ul>
                        </div>

                        <p>Blue &copy; Copyright 2016. Design and Developed By<a href="https://www.facebook.com/profile.php?id=100021563148041"> Giangnthse140109</a> </p>
                    </div>
                </div>
            </div>
        </footer>


    </body>

    <%@include file="template/main2.jsp" %>
</html>
<script>
    function callTable() {
    <c:if test="${requestScope.SUBJECT != null}">
        var table = document.getElementById("subjectTable");
        table.scrollIntoView({behavior: 'smooth', block: 'center'});
    </c:if>
    <c:if test="${requestScope.QUIZSUCCESS != null}">
        var table = document.getElementById("subjectTable");
        table.scrollIntoView({behavior: 'smooth', block: 'center'});
    </c:if>
    <c:if test="${requestScope.LIST != null}">
        var table = document.getElementById("table");
        table.scrollIntoView({behavior: 'smooth', block: 'center'});
    </c:if>
    }
</script>
