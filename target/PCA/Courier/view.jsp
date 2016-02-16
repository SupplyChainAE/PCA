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
				/* noty({
					text : $("#message").val(),
					type : "success",
					layout : "topRight"
				});
 */			
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
              			<h3>Existing Couriers</h3>
           				 </div>	
           				   <div class="widget-content">
           				   <input type="hidden" id="message" value="${message}" />
						<table
								class="table table-striped table-bordered bootstrap-datatable datatable">
							<thead>
								<tr>
									<th>Courier Name</th>
									<th>Code</th>
									<th>Type</th>
									<th style="text-align: center;">Action</th>
								</tr>
							</thead>
							<tbody>
								<c:forEach var="courier" items="${courier}">
									<tr>
										<td>${courier.name}</td>
										<td>${courier.code}</td>
										<td>${courier.type}</td>
										<td style="text-align: center;"><a
												href='<c:url value="/Courier/edit/${courier.id}"></c:url>'
												class="btn btn-primary">Edit</a> <c:choose>
												<c:when test="${courier.enabled}">
													<a
															href='<c:url value="/Courier/disable/${courier.id}"></c:url>'
															class="btn btn-danger" style="margin-left: 20px">Disable</a>
												</c:when>
												<c:otherwise>
													<a
															href='<c:url value="/Courier/enable/${courier.id}"></c:url>'
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