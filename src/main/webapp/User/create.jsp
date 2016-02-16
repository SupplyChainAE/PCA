<%@ taglib prefix="tags" tagdir="/WEB-INF/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>

<%@ page isELIgnored="false"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
pageEncoding="UTF-8"%>

<tags:page title="User Management">
<jsp:attribute name="script">

	
<script type="text/javascript">
	$(document)
			.ready(
					function() {
						$("#form").validate();
						if ($("#message").val() != "") {
							/* noty({
								text : $("#message").val(),
								type : "error",
								layout : "topRight"
							}); */
							alert($("#message").val());
						}
						$("form")
								.submit(
										function(e) {
											$("#form").validate();
											if ($("#user_name").hasClass(
													"error")) {
												e.preventDefault();
						/* 						noty({
													text : "Please enter a different Username.",
													type : "error",
													layout : "topRight"
												});
						 */
						 						alert("Please enter a different Username.");
						 					}
											if( $("#roles").val() == null)
											{
												e.preventDefault();
												/* noty({
													text : "Please select role and warehouse for the user.",
													type : "error",
													layout : "topRight"
												}); */
												
												alert("Please select role and warehouse for the user.");
											}
										})
						$("#user_name")
								.on(
										"change",
										function() {
											var user_name = $(this).val();
											var request = $.ajax({
												type : "POST",
												url : "/PCA/User/checkUser",
												data : {
													name : user_name
												}
											});
											request
													.done(function(msg) {
														if (msg == "failure") {
															/* noty({
																text : "Username already exists. Please Select a different name.",
																type : "error",
																layout : "topRight"
															}); */
															alert("Username already exists. Please Select a different name.");
															$("#user_name")
																	.addClass(
																			"error");
														} else {
															$("#user_name")
																	.removeClass(
																			"error");
														}
													});
											request
													.fail(function(jqXHR,
															textStatus) {
														noty({
															text : "Failed to check Username.",
															type : "error",
															layout : "topRight"
														});
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
              			<h3>User</h3>
           				 </div>	
           				 <!-- /widget-header -->
						   <div class="widget-content">
						   	<input type="hidden" id="message" value="${message}" />
							 <form name="user" action='<c:url value="/User/save" />'
									id="form">
										<fieldset>
										<input type="hidden" id="message" name = "id" value="${user.id}" />
										<div class="control-groups">								
											<label class="control-label">Username :</label>
											<div class="controls"> 	
											<input type="text" name="userName" class="required"
													value="${user.userName}" id="user_name" required>
											<!-- /control-group -->
											</div>
										</div>
										<c:if test="${not edit}">
											<div class="col-lg-4">
												<label>Password : </label> <input type="password"
													name="password" class="form-control required" required>
											</div>
										</c:if>
										
										
										<label>Roles : </label>
										 <select name="role[]"
										class="form-control required" id ="roles" multiple="multiple"
										data-rel="chosen" required>
										
										<c:forEach items="${roles}" var="role">
											<c:set var="found" value="false" />
											<c:forEach var="savedRole" items="${user.userRoles}">
												<c:if test="${role.id eq savedRole.id}">
													<option value="${role.id}" selected="selected">${role.role}</option>
													<c:set var="found" value="true" />
												</c:if>
											</c:forEach>
											<c:if test="${not found}">
												<option value="${role.id}">${role.role}</option>
											</c:if>
										</c:forEach>
										</select>
										
										<div class="control-groups" style="margin-top: 20px;">	
											<center>
												<button type="submit" class="btn btn-primary">Submit</button>
											</center>
										</div> 
									</fieldset>
							</form>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>	
	
	</jsp:body>
</tags:page>