<%@ taglib prefix="tags" tagdir="/WEB-INF/tags"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page isELIgnored="false"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
pageEncoding="UTF-8"%>
<tags:page>
	<jsp:attribute name="title">User Management</jsp:attribute>
	<jsp:attribute name="script">
	<script type="text/javascript">
		$(document).ready(function() {
			if ($("#message").val() != "") {
				
				alert( $("#message").val());
				/* noty({
					text : $("#message").val(),
					type : "success",
					layout : "topRight"
				}); */
			}
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
              			<h3>Existing Warehouses</h3>
           				 </div>	
           				   <div class="widget-content">
           				   <input type="hidden" id="message" value="${message}" />
						<table
								class="table table-striped table-bordered bootstrap-datatable datatable">
							<thead>
								<tr>
									<th>Warehouse Name</th>
									<th>Code</th>
									<th>City</th>
									<th>State</th>
									<th>Pincode</th>
									<th>Contact Number</th>
									<th style="text-align: center;">Action</th>
								</tr>
							</thead>
							<tbody>
								<c:forEach var="warehouse" items="${warehouse}">
									<tr>
										<td>${warehouse.name}</td>
										<td>${warehouse.code}</td>
										<td>${warehouse.city}</td>
										<td>${warehouse.state}</td>
										<td>${warehouse.pincode}</td>
										<td>${warehouse.mobile}</td>
										<td style="text-align: center;"><a
												href='<c:url value="/Warehouse/edit/${warehouse.id}"></c:url>'
												class="btn btn-primary">Edit</a> <c:choose>
												<c:when test="${warehouse.enabled}">
													<a
															href='<c:url value="/Warehouse/disable/${warehouse.id}"></c:url>'
															class="btn btn-danger" style="margin-left: 20px">Disable</a>
												</c:when>
												<c:otherwise>
													<a
															href='<c:url value="/Warehouse/enable/${warehouse.id}"></c:url>'
															class="btn btn-success" style="margin-left: 20px">Enable</a>
												</c:otherwise>
											</c:choose>
											</td>
									</tr>
									
								</c:forEach>
							</tbody>
						</table>
					</div>
				</div>
			</div>
		</div>
	</div>
	</div>
</jsp:body>
</tags:page>