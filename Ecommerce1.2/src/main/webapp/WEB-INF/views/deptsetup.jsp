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
  <script type="text/javascript" href="<c:url value="/resources/jquery/jquery.serializeToJSON.js"/>"></script>
  <title>Insert title here</title>
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
      <li><a href=/app/logout/"><span class="glyphicon glyphicon-log-out"></span>Logout</a></li>
    </ul>
  </div>
  </nav>

  <button type="button" class="btn btn-primary" onclick="addDept()">Add Departments</button>
  <form id = 'departments' method = "post" = 'store' action="/app/createDept">
  <div class='addDeptName'>
  </div>
  
  <button type="submit" class="btn btn-primary">Create Store</button>
  </form>

</body>

<script type="text/javascript">
function addDept() {

	 $("<div class = 'dept'><label>Name</label> <input type='text' name = 'dept'class='deptname'/> <button type='button' class='btn btn-info btn-xs' onclick='addProducts()' >Add Products</button></div>")
     .appendTo(".addDeptName");
	 
	 $("<div class = 'sep'> <input type = 'hidden' name = 'prodname' value = '1' /><input type = 'hidden' name = 'price' value = '1' /></div>")
     .appendTo(".addDeptName");
	 

	}
	
function addProducts() {

	 $("<div class = 'prod'><label>Product Name</label> <input type='text' name = 'prodname'/><label>Product Price</label> <input type='text' name = 'price'/> <button type='button' class = 'btn btn-info btn-xs'onclick='removeProducts(event)' class='abc' >Remove</button></div>")
     .appendTo(".addDeptName");
	}

	 
function removeProducts(e){
	$(e.target.parentNode).remove();
	 }
	

function sendData(e){

}


	
</script>
</html>