<%@ taglib prefix="tags" tagdir="/WEB-INF/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>


<%@ page isELIgnored="false"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
pageEncoding="UTF-8"%>
<tags:page title="Warehouse Management">
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
								if ($("#warehouse_name").hasClass(
										"duplicate")
										|| $("#warehouse_code")
												.hasClass(
														"duplicate")) {
									e.preventDefault();
									alert("Duplicate warehouse Name or Code. Please correct and proceed.");
									/* noty({
										text : "Duplicate warehouse Name or Code. Please correct and proceed.",
										type : "error",
										layout : "topRight"
									}); */
								}
							});
			$("#warehouse_name")
					.on(
							"change",
							function() {
								var warehouse_name = $(this).val();
								var request = $
										.ajax({
											type : "POST",
											url : "/PCA/Warehouse/checkName",
											data : {
												name : warehouse_name
											}
										});
								request
										.done(function(msg) {
											if (msg == "failure") {
												$("#warehouse_name")
														.addClass(
																"duplicate");
												
												alert("Warehouse Name already exists. Please Select a different name.");
												/* noty({
													text : "Warehouse Name already exists. Please Select a different name.",
													type : "error",
													layout : "topRight"
												}); */
											} else {
												$("#warehouse_name")
														.removeClass(
																"duplicate");
											}
										});
								request
										.fail(function(jqXHR,
												textStatus) {
											alert("Failed to check Warehouse Name.");
											/* noty({
												text : "Failed to check Warehouse Name.",
												type : "error",
												layout : "topRight"
											}); */
										});
							});
			$("#warehouse_code")
					.on(
							"change",
							function() {
								var warehouse_code = $(this).val();
								var request = $
										.ajax({
											type : "POST",
											url : "/PCA/Warehouse/checkCode",
											data : {
												code : warehouse_code
											}
										});
								request
										.done(function(msg) {
											if (msg == "failure") {
												$("#warehouse_code")
														.addClass(
																"duplicate");
												alert("Warehouse Code already exists. Please Select a different code.");
												/* noty({
													text : "Warehouse Code already exists. Please Select a different code.",
													type : "error",
													layout : "topRight"
												}); */
											} else {
												$("#warehouse_code")
														.removeClass(
																"duplicate");
											}
										});
								request
										.fail(function(jqXHR,
												textStatus) {
											alert("Failed to check Warehouse Code.");
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
              			<h3>Warehouse Management</h3>
           				 </div>	
           				 <!-- /widget-header -->
						   <div class="widget-content">
						   	<input type="hidden" id="message" value="${message}" />
							 <form name="warehouse" action='<c:url value="/Warehouse/save" />'
									id="form">
										<input type="hidden" name="id" value="${warehouse.id}">
										<fieldset>
										<div class="control-groups">								
											<label class="control-label">Name :</label>
											<div class="controls"> 	
											<input type="text" name="name"  value="${warehouse.name}"
											id="warehouse_name" class ="required">
											<!-- /control-group -->
											</div>
										</div>
										
									<div class="col-lg-4">
												<label>Code : </label> <input type="text" name="code"
												 value="${warehouse.code}"	class ="required" id="warehouse_code" >
											</div>
										
										<label>Address Line 1 : </label> <input type="text"
									name="address1" class="form-control "
									value="${warehouse.address1}">
									<label>Address Line 2 : </label> <input type="text"
									name="address2" class="form-control "
									value="${warehouse.address2}">
									<label>City : </label>  <input type="text"
									name="city" value="${warehouse.city}"
									class="form-control " />
									<label>State : </label> <input type="text" name="state"
									class="form-control "
									value="${warehouse.state}">
									<label>Pincode : </label> <input type="text" class = " form-control digits"
									name="pincode" 
									value="${warehouse.pincode}" >
									<label>Contact Number (+91) :</label>  <input type="text"
									class="form-control" name="mobile"
									value="${warehouse.mobile}" >
										
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