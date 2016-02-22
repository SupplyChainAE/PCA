<%@ taglib prefix="tags" tagdir="/WEB-INF/tags"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page isELIgnored="false"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<tags:page>
	<jsp:attribute name="title">Update Weight</jsp:attribute>
	<jsp:attribute name="script">
	<script type="text/javascript">
		$(document).ready(function() {
			if ($("#message").val() != "") {
				alert($("#message").val());
			}

			$("form").submit(function(e) {
				$("#form").validate();
				var flag = 0;

				$('#dataTable tbody tr').each(function() {
					var weight = $(this).find('td:eq(2) input').val();

					if (parseFloat(weight) == 0) {
						flag = 1;
					}
				});
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
              			<h3>Update Parameter Weight</h3>
           				 </div>	
           				   <div class="widget-content">
           				   <input type="hidden" id="message" value="${message}" />
									
           				 <form name="parameterWeight"
									action='<c:url value="/Mapping/saveParameterWeight" />'
									method="post" id="form">  
						<table id="dataTable"
										class="table table-striped table-bordered bootstrap-datatable datatable">
							<thead>
								<tr>
									<th>Parameter Name</th>
									<th>Weight</th>
									
							</tr>
							</thead>
							<tbody>
									<c:forEach var="parameterWeight" items="${parameterWeight}">
									<c:if test="${parameterWeight.parameter != 'para3' }">
									<tr>
									<td class="hidden "><input type="text" name="para"
															value="${parameterWeight.parameter}"></input></td>
									<td>${parameterWeight.name}</td>
										<td><input type="text" required="Required" name="weight"
															value="${parameterWeight.weight}"></input>
													</td>
									</tr>
									</c:if>
									</c:forEach>
																
							</tbody>
						</table>
						<div class="control-groups" style="margin-top: 20px;">	
							<center>
								<button type="submit" class="btn btn-primary" id="save">SAVE</button>
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