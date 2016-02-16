<%@ taglib prefix="tags" tagdir="/WEB-INF/tags"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>

<%@ page isELIgnored="false"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
pageEncoding="UTF-8"%>
<tags:page title="Update Courier Pincode">
<jsp:attribute name="script">
<script>

$(document).ready(function(){
	
	$("#update").addClass('active');

});	
</script>
</jsp:attribute>

<jsp:body>
<div class="main">
	<div class="main-inner">
		<div class="container">
			<div class="row">
				<div class="span12">
					<div class="widget widget-nopad">
						<div class="widget-header">
							<i class="icon-list-alt"></i>
							<h3>Update Pincode</h3>
						</div>
						<!-- /widget-header -->
						<div class="widget-content">
							<div class="widget big-stats-container">
								<div class="widget-content">
								<br>
									<input type="hidden" name="message" id="message"
												value="${message}" />
							<input type="hidden" name="errorFlag" id="errorFlag"
												value="${errorFlag}" />
							<c:if test="${not empty message and errorFlag =='true'}">
							<div class="alert alert-danger">
								<button type="button" class="close" data-dismiss="alert">×</button>
									${message}
							</div>
							</c:if>
							<c:if test="${errorFlag =='false'}">
							<div class="alert alert-success">
							<button type="button" class="close" data-dismiss="alert">×</button>
									${message}
							</div>
							</c:if>
				
								<form class="form-box" name="file"
												action='<c:url value="/Mapping/updateCourierPincode"/>' method="post"
												enctype="multipart/form-data" id="form">
												
							<div class="controls" align="center" style="margin-left: 20px;">
								<label for="action" style="display:inline;">Action: </label>
								&nbsp;<select id="action" name="action" style="margin-top:5px">
								
								<option value="Add">Add</option>
								<option value="Remove">Remove</option> 
								
								</select>
							</div>					
												
							<br>
							<div class="controls" align="center" style="margin-left: 20px;">
								<label for="courier" style="display:inline;">courier: </label>
								&nbsp;<select id="courier" name="courier" style="margin-top:5px">
								<c:forEach var="courier" items="${courier}">
								<option value="${courier.id}">${courier.name}</option> 
								</c:forEach>
								</select>
							</div>
							<br>
							<div class="controls" align="center" style="margin-left: 85px;">
								<label for="postedFile" style="display:inline;">Upload File: </label>
								&nbsp;
								<input type="file" name="postedFile" style="padding-top:10px;"
														class="form-control required" />
							</div>
							<div style="text-align: center;">
								<br><input type="submit" class="btn btn-primary"
														value="Submit" />
								<a href='<c:url value="/Mapping/downloadPincodeTemplate" />'
														class="btn btn-success" style="margin-left: 20px">Download
									Template</a>
							</div>
						
					</form>	
								</div>
								<!-- /widget-content -->

							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
</div>
</jsp:body>
</tags:page>