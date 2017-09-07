<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
    <%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html lang="en">
<head>
  <meta http-equiv="Content-Type" content="text/html; charset=utf-8">
  <meta name="viewport" content="width=device-width, initial-scale=1">
  <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
  <link rel="stylesheet" href="<c:url value="/resources/css/homepage.css"/>">
  <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.1.1/jquery.min.js"></script>
  <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
  <script type="text/javascript" href="<c:url value="/resources/jquery/jquery-3.1.1.js"/>"></script>
  <title>Ecommerce</title>
</head>
<body>

<nav class="navbar navbar-inverse">
  <div class="container-fluid">
    <div class="navbar-header">
      <a class="navbar-brand" href="/app/homepage">Ecommerce</a>
    </div>
    <ul class="nav navbar-nav">
      <li><a href="/app/homepage">Home</a></li>
    </ul>
    <ul class="nav navbar-nav navbar-right">
      <li><a href="#"><span class="glyphicon glyphicon-user"></span>${registerPerson.firstName} 
      ${registerPerson.lastName} 
      </a></li>
      <li><a href="/app/logout/"><span class="glyphicon glyphicon-log-out"></span>Logout</a></li>
    </ul>
  </div>
</nav>   



<c:choose>
  <c:when test="${empty orders}">
  <div class= 'info'> There are no orders to display </div>
  </c:when>
  <c:otherwise>
  <div class="container">
  <h2></h2>           
  <table class="table table-hover">
    <thead>
      <tr>
        <th>ProductName</th>
        <th>ProductPrice</th>
      </tr>
    </thead>
    <tbody>
   <c:forEach items="${orders}" var="item">
   
    <c:forEach items="${item.products}" var="item">
      <tr>
        <td>${item.productName}</td>
        <td>${item.productPrice}</td>
      </tr>
      </c:forEach>
      </c:forEach>
      </tbody>
  </table>
  </div>
  </c:otherwise>
  </c:choose>
  
    
  

</body>
</html>