<%@ taglib prefix="tags" tagdir="/WEB-INF/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>


<%@ page isELIgnored="false"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
pageEncoding="UTF-8"%>
<tags:page title="Courier Management">
<jsp:attribute name="script">

	
<script type="text/javascript">
$(document)
.ready(
		function() {
			$("#form").validate();
			$("form")
					.submit(
							function(e) {
								$("#form").validate();
								if ($("#courier_name").hasClass(
										"duplicate")
										|| $("#courier_code")
												.hasClass(
														"duplicate")) {
									e.preventDefault();
									alert("Duplicate courier Name or Code. Please correct and proceed.");
									/* noty({
										text : "Duplicate warehouse Name or Code. Please correct and proceed.",
										type : "error",
										layout : "topRight"
									}); */
								}
								
								if($("#warehouse").val() == null )
								{
									e.preventDefault();
									alert("Please select warehouse");
									
								}
							});
						
					
							
			$("#courier_name")
					.on(
							"change",
							function() {
								
								var courier_name = $(this).val();
								var request = $
										.ajax({
											type : "POST",
											url : "/PCA/Courier/checkName",
											data : {
												name : courier_name
											}
										});
								request
										.done(function(msg) {
											if (msg == "failure") {
												$("#courier_name")
														.addClass(
																"duplicate");
												alert("Courier Name already exists. Please Select a different name.");
												/* noty({
													text : "Warehouse Name already exists. Please Select a different name.",
													type : "error",
													layout : "topRight"
												}); */
											} else {
												$("#courier_name")
														.removeClass(
																"duplicate");
											}
										});
								request
										.fail(function(jqXHR,
												textStatus) {
											alert("Failed to check courier Name.");
											/* noty({
												text : "Failed to check Warehouse Name.",
												type : "error",
												layout : "topRight"
											}); */
										});
							});
			$("#courier_code")
					.on(
							"change",
							function() {
								var courier_code = $(this).val();
								var request = $
										.ajax({
											type : "POST",
											url : "/PCA/Courier/checkCode",
											data : {
												code : courier_code
											}
										});
								request
										.done(function(msg) {
											if (msg == "failure") {
												$("#courier_code")
														.addClass(
																"duplicate");
												alert("Courier Code already exists. Please Select a different code.");
												/* noty({
													text : "Warehouse Code already exists. Please Select a different code.",
													type : "error",
													layout : "topRight"
												}); */
											} else {
												$("#courier_code")
														.removeClass(
																"duplicate");
											}
										});
								request
										.fail(function(jqXHR,
												textStatus) {
											alert("Failed to check courier Code.");
											/* noty({
												text : "Failed to check Warehouse Code.",
												type : "error",
												layout : "topRight"
											}); */
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
              			<h3>Courier Management</h3>
           				 </div>	
           				 <!-- /widget-header -->
						   <div class="widget-content">
						   	<input type="hidden" id="message" value="${message}" />
							 <form name="courier" action='<c:url value="/Courier/save" />'
									id="form">
										<input type="hidden" name="id" value="${courier.id}">
										<fieldset>
										<div class="control-groups">								
											<label class="control-label">Name :</label>
											<div class="controls"> 	
											<input type="text" name="name"  value="${courier.name}"
											id="courier_name" class ="required">
											<!-- /control-group -->
											</div>
													<label>Code : </label> <input type="text" name="code"
												 value="${courier.code}"	class ="required" id="courier_code" >
											</div>
											
											<c:choose>
												<c:when  test="${courier.type eq 'AIR'}">
													<div class="radio">
													<label>Air   <input type="radio" name="type" value="AIR"
												 	value="${courier.type}" id="courier_type" checked="checked" ></label>
													</div>
													<div class="radio">
														<label>Surface  
														 <input type="radio" name="type" value="SURFACE"
														 value="${courier.type}" id="courier_type" >  </label>
													</div>
												</c:when>
												<c:when  test="${courier.type eq 'SURFACE'}">
													<div class="radio">
													<label>Air   <input type="radio" name="type" value="AIR"
												 	id="courier_type"  ></label>
													</div>
													<div class="radio">
														<label>Surface  
														 <input type="radio" name="type" value="SURFACE"
														  id="courier_type" checked="checked" >  </label>
													</div>
												</c:when>
												<c:otherwise>
													<div class="radio">
													<label>Air   <input type="radio" name="type" value="AIR"
												 	id="courier_type" checked="checked" ></label>
													</div>
													<div class="radio">
														<label>Surface  
														 <input type="radio" name="type" value="SURFACE"
														 id="courier_type" >  </label>
													</div>
												
												</c:otherwise>
											</c:choose>
											
											<label>Warehouses :</label>
											
											<select name="warehouse[]" class="form-control " id ="warehouse" 
											 multiple="multiple" data-rel="chosen"  >
											<c:forEach var="warehouse" items="${warehouseList}">
												<c:set var="found" value="false" />
												<c:forEach var="savedRole" items="${courier.warehouseList}">
												<c:if test="${warehouse.id eq savedRole.id}">
												<option value="${warehouse.id}" selected="selected">${warehouse.name}</option>
												<c:set var="found" value="true" />
												</c:if>
											</c:forEach>
												<c:if test="${not found}">
													<option value="${warehouse.id}">${warehouse.name}</option>
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