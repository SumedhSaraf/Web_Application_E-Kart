<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html>
<html>
<head>
<META http-equiv="Content-Type" content="text/html;charset=UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1">
  <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
  <link rel="stylesheet" href="<c:url value="/resources/css/homepage.css"/>">
  <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.1.1/jquery.min.js"></script>
  <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
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
      <li><a href="#"></a></li>
    </ul>
    <ul class="nav navbar-nav navbar-right">
      <li><a href="#"><span class="glyphicon glyphicon-user"></span>
      </a></li>
      <li><a href="/app/logout/"><span class="glyphicon glyphicon-log-out"></span>Logout</a></li>
    </ul>
  </div>
</nav>

<%--        <form:form modelAttribute="uploadPic" action="/app/uploadPhoto" method="post" enctype="multipart/form-data">
		  	<fieldset>		
			    <p>
					<form:label for="pics" path="pics" cssErrorClass="error">Photo:</form:label><br/>
					<form:input path="pics" type="file" /> <form:errors path="pics" />
				<p> 
				<p>
					<form:label for="id" path="id" cssErrorClass="error">Photo:</form:label><br/>
					<form:input path="id"/> <form:errors path="id" />
				<p>		
					<input type="submit"/>
				</p>
			</fieldset>
		</form:form> --%>
		
		
		<form id="fileuploadForm" action="/app/uploadPhoto" method="POST" enctype="multipart/form-data" class="cleanform">
			<div class="header">
		  		<h2>Form</h2>
		  
			</div>
			<label for="file">File</label>
			<input id="file" type="file" name="file" />
			<p><button type="submit">Upload</button></p>		
		</form>
		
				
	
</body>
</html>