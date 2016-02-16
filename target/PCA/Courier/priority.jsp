<%@ taglib prefix="tags" tagdir="/WEB-INF/tags"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page isELIgnored="false"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
pageEncoding="UTF-8"%>
<tags:page>
	<jsp:attribute name="title">Courier Management</jsp:attribute>
	<jsp:attribute name="script">
	<script type="text/javascript">
		$(document).ready(function() {
			if ($("#message").val() != "") {
				alert($("#message").val());
 			}
			
			$.ajax({url : "/PCA/Courier/getPriorityData",
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
			}
			
		});
		
			
			$("form")
			.submit(
					function(e) {
						$("#form").validate();
						var flag = 0;
						
						var maxVal =  $('#dataTable tbody tr').length;
						var temp = 0;
						$('#dataTable tbody tr').each(
								function() {
									var priority = $(this).find('td:eq(3) input').val();
									if (priority > maxVal){
										flag = 1;
									} 
									if (parseInt(priority) == 0){
										flag = 2;
									} 
									if (parseInt(priority) == parseInt(temp) && parseInt(temp)>0){
										flag = 3;
									}
									temp = priority;
									
						});
						
						if (parseInt(flag) ==1 ){
							e.preventDefault();
							alert("Priority Greater than Number of rows");
							 
						}
						
						
						if (parseInt(flag) == 2){
							e.preventDefault();
							alert("Priority Cannot be Zero"); 
						}
						
						if (parseInt(flag) == 3){
							e.preventDefault();
							alert("Duplicate Priority found"); 
						}
					});
		});
	
		
		
		
	function getPriorityData()
	{
		var selected = document.getElementById("type").value;	
		
		$.ajax({url : "/PCA/Courier/getPriorityData",
			data : {
				type : selected,
			},
			type : "post",
			success : function(
					result) {
				
				$("#courierBody").empty();
				for ( var i = 0; i < result.length; i++) {
					$("#courierBody").append("<tr><td class=\"hidden\"><input type=\"text\" name=\"id\" value=" + result[i].id + " ></input></td><td  name = \"code\">"+ result[i].code+"</td name = \"name\"><td>"+result[i].name+"</td><td><input type=\"text\" class=\"priority\" name=\"priority\" onkeypress=\"if ( isNaN(this.value + String.fromCharCode(event.keyCode) )) return false;\" value=" +result[i].priority +"></input> </td></tr>");
				}
		}
	});
	}
			
			 
			
		
			
			
		
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
              			<h3>Couriers Priority</h3>
           				 </div>	
           				   <div class="widget-content">
           				   <input type="hidden" id="message" value="${message}" />
           				   
											<label>Courier Type :</label>
											<select name="type"  class="form-control " id ="type" 
											 >
												<option value="AIR" >AIR</option>
												<option value="SURFACE">SURFACE</option>
											</select>
									<button type="submit" style="margin-bottom: 5px; margin-left:5px " onclick="getPriorityData();" class ="btn btn-primary" >Submit</button>
           				 <form name="priority" action='<c:url value="/Courier/savePriority" />' method ="post"
									id="form">  
						<table
								id ="dataTable" 
								class="table table-striped table-bordered bootstrap-datatable datatable">
							<thead>
								<tr>
									<th class="hidden">id</th>
									<th>Courier Name</th>
									<th>Code</th>
									<th>Priority</th>
							</tr>
							</thead>
							<tbody id ="courierBody">
																
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