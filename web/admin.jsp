<%--
    Document   : admin
    Created on : May 26, 2020, 1:35:08 PM
    Author     : Dell
--%>

<%@page import="giangnth.dtos.QuestionDTO"%>
<%@page import="java.util.ArrayList"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <%@include file="template/main1.jsp" %>
        <title>Admin Page</title>
    </head>
    
            <body id="body" onload="callTable()">
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
                                <li><a href="#social">Search Question</a></li>
                                <li><a href="#service">Create Question</a></li>
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
                                        <span class="animated fadeInDown">Welcome Admin ${sessionScope.USER.name}</span>
                                        <a href="login.jsp" class="btn btn-blue btn-effect">Log out</a>
                                    </div>
                                </div>

                            </div>
                        </div><
                    </div><!-- /slider-wrapper -->
                </section>

                <!-- Social section -->
                <section id="social" class="parallax">
                    <div class="overlay">
                        <div class="container">
                            <div class="row">

                                <div class="sec-title text-center white wow animated fadeInDown">
                                    <h2>Search Question</h2>
                                    <p>Bring the best way to view your questions</p>
                                </div>

                                <div class="input-field">
                                    <form action="MainController" method="POST">
                                        <select name="chooseBox">
                                            <option selected="">Question Name</option>
                                            <option>Id Subject(ENG/PRJ)</option>
                                            <option>Status(active/deactive)</option>
                                        </select>
                                        <input type="text" name="txtSearch" class="subscribe form-control" placeholder="Search Me :)" />
                                        <ul class="social-button">
                                            <li class="wow animated zoomIn animated" style="visibility: visible; animation-name: zoomIn;">
                                                <input type="submit" value="search" name="action"/>
                                            </li>
                                        </ul>

                                    </form>
                                </div>

                            </div>
                        </div>
                        <div class="container-fluid">
                            <div class="sec-title text-center white wow animated fadeInDown sparkline13-graph" id="table">
                                <div class="datatable-dashv1-list custom-datatable-overright" style="text-align: center">
                                    <c:if test="${requestScope.QUESTION != null}">
                                        <c:if test="${not empty requestScope.QUESTION}" var="list">
                                            <c:if test="${requestScope.SUCCESS != null}"><h1 style="color: red">${requestScope.SUCCESS}</h1></c:if>
                                            <c:if test="${requestScope.ERROR != null}"><h1 style="color: red">${requestScope.ERROR}</h1></c:if>
                                                <table border="1" >                            
                                                    <thead>
                                                        <tr>
                                                            <th>Id Question</th>
                                                            <th>Id Subject</th>
                                                            <th>Question</th>
                                                            <th>Answer1</th>
                                                            <th>Answer2</th>
                                                            <th>Answer3</th>
                                                            <th>Answer4</th>
                                                            <th>Correct Answer</th>
                                                            <th>Status</th>
                                                            <th>Edit</th>
                                                            <th>Delete</th>
                                                        </tr>
                                                    </thead>
                                                    <tbody>
                                                    <c:forEach items="${requestScope.QUESTION}" var="dto" varStatus="counter">
                                                        <tr>
                                                            <td>${dto.idQuestion}</td>
                                                            <td>${dto.idSubject}</td>
                                                            <td>${dto.question}</td>
                                                            <td>${dto.answer1}</td>
                                                            <td>${dto.answer2}</td>
                                                            <td>${dto.answer3}</td>
                                                            <td>${dto.answer4}</td>
                                                            <td>${dto.correctAnswer}</td>
                                                            <td>${dto.quesStatus}</td>
                                                            <td>
                                                                <form action="edit.jsp" method="POST">
                                                                    <!-- parameter about question want to edit-->
                                                                    <input type="hidden" name="idQuestion" value="${dto.idQuestion}"/> 
                                                                    <input type="hidden" name="question" value="${dto.question}"/> 
                                                                    <input type="hidden" name="answer1" value="${dto.answer1}"/> 
                                                                    <input type="hidden" name="answer2" value="${dto.answer2}"/> 
                                                                    <input type="hidden" name="answer3" value="${dto.answer3}"/> 
                                                                    <input type="hidden" name="answer4" value="${dto.answer4}"/> 
                                                                    <input type="hidden" name="correctAnswer" value="${dto.correctAnswer}"/> 
                                                                    <input type="hidden" name="idSubject" value="${dto.idSubject}"/> 
                                                                    <input type="hidden" name="quesStatus" value="${dto.quesStatus}"/> 
                                                                    <!-- parameter for search-->
                                                                    <input type="hidden" name="txtSearch" value="${param.txtSearch}"/> 
                                                                    <input type="hidden" name="chooseBox" value="${param.chooseBox}"/> 
                                                                    <input type="submit" value="edit" name="action" style="color:black"/>
                                                                </form>
                                                            </td>
                                                            <c:if test="${dto.quesStatus != 'deactive'}">
                                                                <td>
                                                                    <c:url var="deleteLink" value="MainController">
                                                                        <c:param name="action" value="delete"/>
                                                                        <c:param name="id" value="${dto.idQuestion}" />
                                                                        <c:param name="txtSearch" value="${param.txtSearch}"/>
                                                                        <c:param name="chooseBox" value="${param.chooseBox}"/>
                                                                    </c:url> 
                                                                    <a href="${deleteLink}">Delete</a>
                                                                </td>
                                                            </c:if>
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
                                                                <li class="page-number"><a href="SearchController?txtSearch=${param.txtSearch}&chooseBox=${param.chooseBox}&pageNum=${requestScope.pageNum}&currentNum=${i}">${i}</a></li>
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
                </section>

                <section id="service" >
                    <div class="container">
                        <div class="row">
                            <div class="sec-title text-center">
                                <h2 class="wow animated bounceInLeft">Create New Question</h2>
                                <p class="wow animated bounceInRight">Give you everything you need to support your imagination</p>
                            </div>
                            <div class=" text-center wow animated zoomIn" data-wow-delay="0.3s">
                                <div class="service-item">
                                    <div class="service-icon">
                                        <i class="fa fa-tasks fa-3x"></i>
                                    </div>
                                    <h3><a href="insert.jsp">Click Here To Insert</a></h3>
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
        <c:if test="${requestScope.QUESTION != null}">
            var table = document.getElementById("table");
            table.scrollIntoView({behavior: 'smooth', block: 'center'});
        </c:if>
        }

    </script>