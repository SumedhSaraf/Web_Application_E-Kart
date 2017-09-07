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
  <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.1.1/jquery.min.js"></script>
  <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
<title>Insert title here</title>
</head>
<body>
<form:form id="form" method="post" modelAttribute="registerPerson" action="/app/createPerson">

   
		  		<div class = "form-group">
		  		<label for="firstName" class="control-label col-xs-3">FirstName:</label>
		  		<div class="col-xs-9">
		  		<form:input path="firstName" class="form-control"  placeholder="Enter First Name"/>
		  		</div>
		  	    <form:errors path="firstName" cssClass="error" />
		  	    </div>
		  		
		  	    
		 	
 		 		<div class = "form-group">
		  		<label for="lastName" class="control-label col-xs-3">LastName:</label>
		  		<div class="col-xs-9">
		  		<form:input path="lastName" class="form-control"  placeholder="Enter last Name"/>
		  		</div>
		  	    <form:errors path="lastName" cssClass="error" />
		  	    </div>
		  		
		  		
		 		<div class = "form-group">
		  		<label for="password" class="control-label col-xs-3">Password:</label>
		  		<div class="col-xs-9">
		  		<form:input path="password" type="password" class="form-control"  placeholder="Enter password"/>
		  		</div>
		  	    <form:errors path="password" cssClass="error" />
		  	    </div>
		  	    
		  		<div class = "form-group">
		  		<label for="emailId" class="control-label col-xs-3">Email:</label>
		  		<div class="col-xs-9">
		  		<form:input path="emailId" class="form-control"  placeholder="Enter Email"/>
		  		</div>
		  	    <form:errors path="emailId" cssClass="error" />
		  	    </div>
		  	    
		  		<div class = "form-group">
		  		<label for="phoneNo" class="control-label col-xs-3">Mobile No:</label>
		  		<div class="col-xs-9">
		  		<form:input path="phoneNo" class="form-control"  placeholder="Enter Phone Number"/>
		  		</div>
		  	    <form:errors path="phoneNo" cssClass="error" />
		  	    </div>
	       
		  	   
	          	<div class = "form-group"> 
	          	<label for="role" class="control-label col-xs-3">Select Role:</label> 	
	          	<div class="col-xs-9">
		  		<form:select path="role" class="form-control">
					<form:option value="admin">Admin</form:option>
					<form:option value="user">User</form:option>
				</form:select>
				</div>
				</div> 
				
		 <c:if test="${sameemail == 1}">
		<div class=error> <%= request.getAttribute("respmessage") %> </div>
         </c:if>
		  
		  
	         <button type="submit" class="btn btn-primary">Create Account</button>
			
		</form:form>
</body>


</html>