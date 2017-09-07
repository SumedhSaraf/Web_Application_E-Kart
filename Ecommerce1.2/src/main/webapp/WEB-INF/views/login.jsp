<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="ISO-8859-1"%>
 <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib  uri="http://www.springframework.org/tags" prefix="spring"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>Insert title here</title>
</head>
<body>
<form:form id="login" method="post" modelAttribute="loginPerson" action="/app/person/login">
		  	    
		  		<div class = "form-group">
		  		<label for="emailId" class="control-label col-xs-3">Email:</label>
		  		<div class="col-xs-9">
		  		<form:input path="emailId" class="form-control"  placeholder="Enter Email"/>
		  		</div>
		  	    <form:errors path="emailId" cssClass="error" />
		  	    </div>
		  	    
			    <div class = "form-group">
		  		<label for="password" class="control-label col-xs-3">Password:</label>
		  		<div class="col-xs-9">
		  		<form:input path="password" type="password" class="form-control"  placeholder="Enter password"/>
		  		</div>
		  	    <form:errors path="password" cssClass="error" />
		  	    </div>
	 
	 	 <c:if test="${loginStatus == 0}">
		 <div class= "error"><%= request.getAttribute("message") %></div> 
         </c:if>
	 
			 <button type="submit" class="btn btn-primary">Login</button>
			 
		</form:form>
</body>

</html>