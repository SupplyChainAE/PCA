<%@ taglib prefix="tags" tagdir="/WEB-INF/tags"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page isELIgnored="false"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
pageEncoding="UTF-8"%>
<tags:page>
	<jsp:attribute name="title">Update Price</jsp:attribute>
	<jsp:attribute name="script">
	<script type="text/javascript">
		$(document).ready(function() {
			if ($("#message").val() != "") {
				alert($("#message").val());
 			}
			
			/* $.ajax({url : "/PCA/Courier/getPriorityData",
				data : {
					type : 'AIR',
				},
				type : "post",
				success : function(
						result) {
					
					$("#courierBody").empty();
					for ( var i = 0; i < result.length; i++) {
						$("#courierBody").append("<tr><td class=\"hidden\"><input type=\"text\" name=\"id\" value=" + result[i].id + " ></input></td><td  name = \"code\">"+ result[i].code+"</td name = \"name\"><td>"+result[i].name+"</td><td><input type=\"text\" class=\"priority\" name=\"priority\" onkeypress=\"if ( isNaN(this.value + String.fromCharCode(event.keyCode) )) return false;\" value=" +result[i].priority +"></input> </td></tr>");
					}
			} */
			
			$("form")
			.submit(
					function(e) {
						$("#form").validate();
						var flag = 0;
						
						
						$('#dataTable tbody tr').each(
								function() {
									var price = $(this).find('td:eq(2) input').val();
									
									if (parseFloat(price) == 0 ){
										
										flag = 1;
									} 
						});
						
						if (parseInt(flag) ==1 ){
							e.preventDefault();
							alert("Price cannot be equal to Zero ");
							 
						}
						
					});
		
		
			
			
		});
		
			
			
		
		
	
	
			
			 
			
		
			
			
		
	</script>
	</jsp:attribute>
	<jsp:body>
	<div class="main">
	<div class="main-inner">
		<div class="container">
			<div class="row">
				<div class="span12">
<!-- 						<div class="widget widget-table action-table"> -->
            			<div class="widget-header"> <i class="icon-th-list"></i>
              			<h3>Update Parameters Price</h3>
           				 </div>	
           				   <div class="widget-content">
           				   <input type="hidden" id="message" value="${message}" />
									
           				 <form name="parameterPrice" action='<c:url value="/Mapping/saveParameterPrice" />' method ="post"
									id="form">  
						<table
								id ="dataTable" 
								class="table table-striped table-bordered bootstrap-datatable datatable">
							<thead>
								<tr>
									<th class="hidden">id</th>
									<th>Parameter Name</th>
									<th>Price</th>
									
							</tr>
							</thead>
							<tbody>
									<c:forEach var="parameterPrice" items="${parameterPrice}">
									
									<tr>
									<td  class ="hidden "><input type="text"   name="id" value="${parameterPrice.id}" ></input>  ${parameterPrice.id}</td>
									<td>${parameterPrice.parameterName}</td>
										<td><input type="text" required ="Required"  name="price" value="${parameterPrice.price}" ></input></td>
									</tr>
									</c:forEach>
																
							</tbody>
						</table>
						<div class="control-groups" style="margin-top: 20px;">	
							<center>
								<button type="submit" class="btn btn-primary" id ="save">SAVE</button>
							</center>
						</div> 
					</form>
					</div>
					
				</div>
			</div>
		</div>
	</div>
	</div>
</jsp:body>
</tags:page>