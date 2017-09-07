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
  <title>Insert title here</title>
</head>
<body>
<nav class="navbar navbar-inverse">
  <div class="container-fluid">
    <div class="navbar-header">
      <a class="navbar-brand" href="/app/homepage">Ecommerce</a>
    </div>
    <ul class="nav navbar-nav">
      <li><a href="/app/home">Home</a></li>
      <li><a href="#"></a></li>
    </ul>
    <ul class="nav navbar-nav navbar-right">
      <li><a href="#"><span class="glyphicon glyphicon-user"></span>${registerPerson.firstName} 
      ${registerPerson.lastName} 
      </a></li>
      <li><a href="/app/logout/"><span class="glyphicon glyphicon-log-out"></span>Logout</a></li>
    </ul>
  </div>
</nav>    
	<div class="container-fluid">
		<div class="row">
			<div class="col-xs-4">
				<table class="table table-hover">
					<thead>
						<tr>
							<th>Sections</th>
						</tr>
					</thead>
					<tbody>
						<c:forEach items="${depList}" var="dep">
							<tr>
								<td><div onclick='showProducts(event)' id = '${dep.deptId}' class ='dep'>${dep.deptName}</div></td>
							</tr>
						</c:forEach>
					</tbody>
				</table>
			</div>
			<div class="col-xs-8">
				<div class="products"></div>
				</div>


			</div>
		</div>
		
		<div class = "factory">
		<c:forEach items="${depList}" var="dep">

						<div class="${dep.deptId}">
							<table class="table">
								<tbody>

									<c:forEach items="${dep.products}" var="prod" varStatus="loop">

										<c:if test="${loop.index % 3 == 0}">
											<tr>
										</c:if>
										<td id = '${prod.productId}'><img alt="No Image"
											src="/app/getPhoto/${prod.productId}" height="200"
											width="200"  onclick="addProduct(event)" data-toggle="tooltip"
											 title="Add to Cart" id = "${prod.productId}"/>
											<div>${prod.productName}</div>
											<div>${prod.productPrice}</div>
										</td>
										<c:if test="${(loop.index + 1) % 3 == 0}">
											</tr>
										</c:if>
									</c:forEach>
								</tbody>
							</table>
						</div>
					</c:forEach>
		</div>
	
		
	<c:if test="${not empty registerPerson.emailId}">
	
	  <div class="row">
	  <div class = "col-xs-10">
	  </div>
	  <div class = "col-xs-2">
		  	<form action="/app/person/placeOrder">
   <button type="submit" class="btn btn-primary">CheckOut</button>
                          </form>
		  	</div>
	</div>	  	
	</c:if>
	
		
		
</body>

<script type="text/javascript">



$(document).ready(function() {
	
	$(".factory").hide();
   
}); 


/* $(document).ready(function() {
    $("div").click(function(event) {
        alert(event.target.id);
    });
}); */
function addProduct(event){
	
	
/* 	$.get("/app/person/addToCart/" + event.target.id, function(data, status){
        alert("Data: " + data + "\nStatus: " + status);
    });
}
	 */
	
	$.ajax({		
		url: "/app/person/addToCart/" + event.target.id,
		method: "POST",
		data:"result",
		success: function(data) {
			var Id = event.target.id
			if($.trim(data) == 'Added')
			{	
			
		       alert("Item added");
			}
			else
			{
				alert(data);
			}  
		
		}
	});	
}
 

function showProducts(event){
	
/* 	alert("hi");
	alert(event.target.id);
	var e1 = document.getElementByClass("deps");
	e1.addEventListener('click', function(){alert("four")}, false); */
	
	  $("div.dep").click(function(event) {
		      $('.products').html('');
		      var identifier = event.target.id;
		      var content = $('.' + identifier).html();
			  $('.products').html(content);
		});
	
     /* $(".products").empty();
	

	
     
     $("").click(function(event) {
         alert(event.target.id);
     });
     
     
     
	  var identifier = e.target.id;
	  var fullIdentifier = "." + identifier;
	  var content = $(fullIdentifier).html();
	  $(".products").append(content);  */
}

</script>
</html>