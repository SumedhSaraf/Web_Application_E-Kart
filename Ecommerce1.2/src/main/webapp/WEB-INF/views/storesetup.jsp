<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
    <%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html lang="en">
<head>
  <meta http-equiv="Content-Type" content="text/html; charset=utf-8">
  <meta name="viewport" content="width=device-width, initial-scale=1">
  <link rel="stylesheet" href="<c:url value="/resources/blueprint/screen.css" />" type="text/css" media="screen, projection">
  <link rel="stylesheet" href="<c:url value="/resources/blueprint/print.css" />" type="text/css" media="print">
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
      <li><a href="#"><span class="glyphicon glyphicon-user"></span>${registerPerson.firstName} 
      ${registerPerson.lastName} 
      </a></li>
      <li><a href="/app/logout"><span class="glyphicon glyphicon-log-out"></span>Logout</a></li>
    </ul>
  </div>
</nav>    

  <c:if test="${not empty respmessage}">
 <div class = "row">
 <div class = 'col-xs-2'></div>
 <div class = 'col-xs-2'></div>
 <div class = 'col-xs-2'> <span class = 'success'>${respmessage}</span></div>
 <div class = 'col-xs-2'></div>
 <div class = 'col-xs-2'></div>
 <div class = 'col-xs-2'></div>
 </div>
 </c:if>                      

<form:form id="store" class = "form-horizontal" method="post" modelAttribute="registerStore" action="/app/createStore">
	
		 
		
  		  		<div class = "form-group">
		  		<div class = "row">
		  		<div class = col-xs-3></div>
		  		<label for="storeName" class = "control-label col-xs-2">
		  		<span class = "pull-right">StoreName:</span></label>
		  	    <div class="col-xs-4">
		  		<form:input path="storeName" class="form-control"  placeholder="Enter Store Name"/>
		  		</div>
		  	    <form:errors path="storeName" cssClass="error" />
		  	    <div class = col-xs-3></div>
		  	    </div>
		  	    </div>
		  	    
		 	
		 		<div class = "form-group">
		 		<div class = "row">
		 		<div class = col-xs-3></div>
		  		<label for="city" class="control-label col-xs-2">
		  		<span class = "pull-right">City:</span></label>
		  		<div class="col-xs-4">
		  		<form:input path="city" class="form-control"  placeholder="Enter City Name"/>
		  		</div>
		  	    <form:errors path="city" cssClass="error" />
		  	    <div class = col-xs-3></div>
		  	    </div>
		  	    </div>
		  		
		  		 
		        <div class = "form-group"> 
		        <div class = "row">
		        <div class = col-xs-3></div>
	          	<label for="state" class="control-label col-xs-2">
	          	<span class = "pull-right">Select State:</span></label></label> 	
	          	<div class="col-xs-4">
	          	
	          	<form:select path="state" class="form-control">                       
	<form:option value="Alabama">AL</form:option>
	<form:option value="Alaska">AK</form:option>
	<form:option value="Arizona">AZ</form:option>
	<form:option value="Arkansas">AR</form:option>
	<form:option value="California">CA</form:option>
	<form:option value="Colorado">CO</form:option>
	<form:option value="Connecticut">CT</form:option>
	<form:option value="Delaware">DE</form:option>
	<form:option value="Dist of Columbia">DC</form:option>
	<form:option value="Florida">FL</form:option>
	<form:option value="Georgia">GA</form:option>
	<form:option value="Hawaii">HI</form:option>
	<form:option value="Idaho">ID</form:option>
	<form:option value="Illinois">IL</form:option>
	<form:option value="Indiana">IN</form:option>
	<form:option value="Iowa">IA</form:option>
	<form:option value="Kansas">KS</form:option>
	<form:option value="Kentucky">KY</form:option>
	<form:option value="Louisiana">LA</form:option>
	<form:option value="Maine">ME</form:option>
	<form:option value="Maryland">MD</form:option>
	<form:option value="Massachusetts">MA</form:option>
	<form:option value="Michigan">MI</form:option>
	<form:option value="Minnesota">MN</form:option>
	<form:option value="Mississippi">MS</form:option>
	<form:option value="Missouri">MO</form:option>
	<form:option value="Montana">MT</form:option>
	<form:option value="Nebraska">NE</form:option>
	<form:option value="Nevada">NV</form:option>
	<form:option value="NewHampshire">NH</form:option>
	<form:option value="NewJersey">NJ</form:option>
	<form:option value="NewMexico">NM</form:option>
	<form:option value="NewYork">NY</form:option>
	<form:option value="NorthCarolina">NC</form:option>
	<form:option value="NorthDakota">ND</form:option>
	<form:option value="Ohio">OH</form:option>
	<form:option value="Oklahoma">OK</form:option>
	<form:option value="Oregon">OR</form:option>
	<form:option value="Pennsylvania">PA</form:option>
	<form:option value="RhodeIsland">RI</form:option>
	<form:option value="SouthCarolina">SC</form:option>
	<form:option value="SouthDakota">SD</form:option>
	<form:option value="Tennessee">TN</form:option>
	<form:option value="Texas">TX</form:option>
	<form:option value="Utah">UT</form:option>
	<form:option value="Vermont">VT</form:option>
	<form:option value="Virginia">VA</form:option>
	<form:option value="Washington">WA</form:option>
	<form:option value="WestVirginia">WV</form:option>
	<form:option value="Wisconsin">WI</form:option>
	<form:option value="Wyoming">WY</form:option>
   </form:select>

				</div>
				<div class = col-xs-3></div>
				</div>
				</div>
		  
		  	    
		  		<div class = "form-group">
		  		<div class = "row">
		  		<div class = col-xs-3></div>
		  		<label for="street1" class="control-label col-xs-2">Street1:</label>
		  		<div class="col-xs-4">
		  		<form:input path="street1" class="form-control"  placeholder="Enter Street address"/>
		  		</div>
		  	    <form:errors path="street1" cssClass="error" />
		  	    <div class = col-xs-3></div>
		  	    </div>
		  	    </div>
		  	    
		  	       
		  		<div class = "form-group">
		  		<div class = "row">
		  		<div class = col-xs-3></div>
		  		<label for="street2" class="control-label col-xs-2">Street2:</label>
		  		<div class="col-xs-4">
		  		<form:input path="street2" class="form-control"  placeholder="Enter Street address"/>
		  		</div>
		  	    <form:errors path="street2" cssClass="error" />
		  	    <div class = col-xs-3></div>
		  	    </div>
		  	    </div>
		  	    
		  		<div class = "form-group">
		  		<div class = "row">
		  		<div class = col-xs-3></div>
		  		<label for="zipCode" class="control-label col-xs-2">ZipCode:</label>
		  		<div class="col-xs-4">
		  		<form:input path="zipCode" class="form-control"  placeholder="Enter Street address"/>
		  		</div>
		  	    <form:errors path="zipCode" cssClass="error" />
		  	    <div class = col-xs-3></div>
		  	    </div>
		  	    </div> 
		  	    
		  	<button type="submit" class="btn btn-primary">Next</button>
		</form:form>
</body>

</html>