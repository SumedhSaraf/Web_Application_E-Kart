<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
    <%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
    <%@ taglib  uri="http://www.springframework.org/tags" prefix="spring"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html lang="en">
<head>
  <meta http-equiv="Content-Type" content="text/html; charset=utf-8">
  <meta name="viewport" content="width=device-width, initial-scale=1">
  <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
  <link rel="stylesheet" href="<c:url value="/resources/css/homepage.css"/>">
  <link rel="stylesheet" href="<c:url value="/resources/blueprint/screen.css" />" type="text/css" media="screen, projection">
  <link rel="stylesheet" href="<c:url value="/resources/blueprint/print.css" />" type="text/css" media="print">
  <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.1.1/jquery.min.js"></script>
  <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
  <title>Ecommerce</title>
</head>
<%-- <%@ include file="register.jsp" %>
<%@ include file="login.jsp" %> --%>
<body>
 
<div class="container-fluid" id="maincontainer">
<div id="toplayer">

  <c:if test="${not empty logoutmessage}">
 <div class = "row">
 <div class = 'col-xs-2'></div>
 <div class = 'col-xs-2'></div>
 <div class = 'col-xs-2'> <span class = 'success'>${logoutmessage}</span></div>
 <div class = 'col-xs-2'></div>
 <div class = 'col-xs-2'></div>
 <div class = 'col-xs-2'></div>
 </div>
 </c:if>
  


<c:choose>
  <c:when test="${empty registerPerson.phoneNo}">
   <c:if test="${loginStatus != 1}">
    <button type="button" id="signup" class="btn btn-primary btn-md" data-toggle="modal" data-target="#myModal">SignUp</button>
    <button type="button" id="login" class="btn btn-primary btn-md" data-toggle="modal" data-target="#loginModal">Login</button>
   </c:if>
  
 <c:if test="${loginStatus == 1}">
  <nav class="navbar navbar-inverse">
  <div class="container-fluid">
    <div class="navbar-header">
      <a class="navbar-brand" href="/app/homepage">Ecommerce</a>
    </div>
    <ul class="nav navbar-nav">
      <li class="active"><a href="/app/homepage">Home</a></li>

      
   
    </ul>
    <ul class="nav navbar-nav navbar-right">
      <li><a href="#"><span class="glyphicon glyphicon-user"></span>${sessionScope.registerPerson.firstName} 
      ${sessionScope.registerPerson.lastName} 
      </a></li>
      <li><a href="/app/logout/"><span class="glyphicon glyphicon-log-out"></span>Logout</a></li>
    </ul>
  </div>
</nav>
 </c:if>
 

  </c:when>
  
  <c:otherwise>
  
  <c:if test="${sameemail == 1}">
    <button type="button" id="signup" class="btn btn-primary btn-md" data-toggle="modal" data-target="#myModal">SignUp</button>
    <button type="button" id="login" class="btn btn-primary btn-md" data-toggle="modal" data-target="#loginModal">Login</button>
 </c:if>
  
  
  <c:if test="${sameemail != 1}">
  <nav class="navbar navbar-inverse">
  <div class="container-fluid">
    <div class="navbar-header">
      <a class="navbar-brand" href="/app/homepage">Ecommerce</a>
    </div>
    <ul class="nav navbar-nav">
      <li class="active"><a href="/app/homepage">Home</a></li>
      <li><a href="/app/getOrders/${sessionScope.registerPerson.emailId}">MyOrders</a></li>
     
    </ul>
    <ul class="nav navbar-nav navbar-right">
      <li><a href="#"><span class="glyphicon glyphicon-user"></span>${registerPerson.firstName} 
      ${registerPerson.lastName} 
      </a></li>
      <li><a href="/app/logout/"><span class="glyphicon glyphicon-log-out"></span>Logout</a></li>
    </ul>
  </div>
</nav>
 </c:if>
  </c:otherwise>
</c:choose>




 

<!-- <button type="button" id="signup" class="btn btn-primary btn-md" data-toggle="modal" data-target="#myModal">SignUp</button>
<button type="button" id="login" class="btn btn-primary btn-md" data-toggle="modal" data-target="#loginModal">Login</button> -->
</div> 

<!-- Modal -->
<div id="myModal" class="modal fade" role="dialog">
  <div class="modal-dialog">

    <!-- Modal content-->
    <div class="modal-content">
      <div class="modal-header">
        <button type="button" class="close" data-dismiss="modal">&times;</button>
        <h4 class="modal-title">Enter your Details</h4>
      </div>
      <div class="modal-body">
       <%@ include file="register.jsp" %>
      </div>      
      <div class="modal-footer">
        <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
      </div>
    </div>
    </div>
</div>

<div id="loginModal" class="modal fade" role="dialog">
  <div class="modal-dialog">
  
        <div class="modal-content">
      <div class="modal-header">
        <button type="button" class="close" data-dismiss="modal">&times;</button>
        <h4 class="modal-title">Enter your Details</h4>
      </div>
      <div class="modal-body">
       <%@  include file="login.jsp" %> 
       <%-- <jsp:include page="login.jsp" /> --%>
      </div>      
      <div class="modal-footer">
        <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
      </div>
    </div>
    </div>
    </div>

<div id="Carousel" class="carousel slide" data-ride="carousel">
    <!-- Indicators -->
    <ol class="carousel-indicators">
      <li data-target="#Carousel" data-slide-to="0" class="active"></li>
      <li data-target="#Carousel" data-slide-to="1"></li>
      <li data-target="#Carousel" data-slide-to="2"></li>
      <li data-target="#Carousel" data-slide-to="3"></li>
    </ol>
     <!-- Wrapper for slides -->
    <div class="carousel-inner" role="listbox">

      <div class="item active">
        <img src="<c:url value="/resources/displayimages/ecom1.jpg"/>"  width="100%" height="100%">
       </div>

      <div class="item">
        <img src="<c:url value="/resources/displayimages/ecom2.jpg"/>" width="100%" height="100%">
      </div>
    
        <div class="item">
        <img src="<c:url value="/resources/displayimages/ecom3.jpg"/>" width="100%" height="100%">
      </div>
    
        <div class="item">
        <img src="<c:url value="/resources/displayimages/ecom4.jpg"/>" width="100%" height="100%">
      </div>
      </div>
    </div>

    <!-- Left and right controls -->
    <div class="adjustglyphicon">
      <a class="left carousel-control" href="#Carousel" role="button" data-slide="prev">
      <span class="glyphicon glyphicon-chevron-left" aria-hidden="true"></span>
       <span class="sr-only">Previous</span>
     </a>
  
    
      <a class="right carousel-control" href="#Carousel" role="button" data-slide="next">
      <span class="glyphicon glyphicon-chevron-right" aria-hidden="true"></span>
      <span class="sr-only">Next</span>
     
    </a>
    </div>


<div class = "row">
 <div class="col-xs-4">
      <img src="<c:url value="/resources/displayimages/storeLocatorTitle.gif"/>" width="100%" height="100%">
    </div>
    <div class="col-xs-4">
     <form:form class="form-horizontal" method="post" modelAttribute="storeSearchInput" action="/app/storeSearch">
    <div class="form-group">
    <label class="control-label col">Search by State</label>
     <form:input class="form-control" path="stateName" placeholder="Enter State Name"/>
      <form:errors path="stateName" cssClass="error" />
      <button type="submit" class="btn btn-primary">Search</button>
  </div>
  </form:form>
    </div>
    <div class="col-xs-4">
   
    </div>
    
    </div>
  </div>
  

<%--   <c:if test="${sameemail == 1}">
 <script type="text/javascript">
 $("#signup").trigger("click");
    </script>
    </c:if> --%>

  <c:if test="${check == 0}">
 <script type="text/javascript">
 $("#signup").trigger("click");
    </script>
    </c:if>
    
    
 <%--  <c:if test="${loginstatus == 0}">
 <script type="text/javascript">
 $("#login").trigger("click");
    </script>
    </c:if> --%>
    
<c:if test="${checkLogin == 0}">
 <script type="text/javascript">
 $("#login").trigger("click");
    </script>
  </c:if> 

</body>

</html>