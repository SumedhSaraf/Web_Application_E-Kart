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




<c:if test="${not empty message}">
<div>${message}</div>
</c:if>


<div class="container-fluid" id="maincontainer">
<div class = row>
<div class = 'col-xs-4'></div>
<div class = 'col-xs-3'>
<table class="table table-hover">
    <thead>
      <tr>
        <th>Product Name</th>
        <th>Price</th>
        <th>Quantity</th>
      </tr>
    </thead>
    <tbody>
   <c:forEach items = '${products}' var='products'>
   
  
      <tr>
        <td>${products.productName};</td>
        <td>${products.productPrice};</td>
      </tr>
      </c:forEach>
      </tbody>
  </table>
  </div> 
  <div class = 'col-xs-5'></div>
  </div>
</div>
<div id = 'submitButton'>
<button type="submit" class="btn btn-primary">Submit</button>
</div>

</body>
<script type="text/javascript">
$(".btn").click(function(event) {
    $('#submitButton').html('');
	  $('#submitButton').html("<div class = 'success'> Your order is confirmed</div>");
});
</script>
</html>