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
				
				alert($("#message").val());
				
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
              			<h3>Existing Users</h3>
           				 </div>	
           				   <div class="widget-content">
           				   <input type="hidden" id="message" value="${message}" />
		
						<table
								class="table table-striped table-bordered bootstrap-datatable datatable">
							<thead>
								<tr>
									<th>User Name</th>
									<th>Roles</th>
									<th>Enabled</th>
									<th>Action</th>
								</tr>
							</thead>
							<tbody>
								<c:forEach var="user" items="${users}">
									<tr>
										<td>${user.userName}</td>
										<td><c:forEach var="role" items="${user.userRoles}">
										${role.role}<br />
											</c:forEach>
											</td>
										<td><c:choose>
												<c:when test="${user.enabled}">
										Yes
										</c:when>
												<c:otherwise>
										No
										</c:otherwise>
											</c:choose>
											</td>
										<td><a
												href='<c:url value="/User/edit/${user.id}"></c:url>'
												class="btn btn-primary">Edit</a> <c:choose>
												<c:when test="${user.enabled}">
													<a href='<c:url value="/User/disable/${user.id}"></c:url>'
															class="btn btn-danger" style="margin-left: 20px">Disable</a>
												</c:when>
												<c:otherwise>
													<a href='<c:url value="/User/enable/${user.id}"></c:url>'
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