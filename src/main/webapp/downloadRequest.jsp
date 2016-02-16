<%@ taglib prefix="tags" tagdir="/WEB-INF/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>

<%@ page isELIgnored="false"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
pageEncoding="UTF-8"%>

<tags:page title="Home">
<jsp:attribute name="script">


<script>
	$(document).ready(function() {

		
		$("#home").addClass('active');
		$('#daterange').daterangepicker({
			format : "YYYY/MM/DD"
			
		});
		$("#form")
		.submit(
				function(e) {
					
					
					if ($("#status").val()=="ALL" && $("#daterange").val() == ''&& $("#sellerCode").val() == ''){
							e.preventDefault();
							alert("Please select daterange or sellerCode");
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
              			<h3>Requests</h3>
           				 </div>	
           				 <!-- /widget-header -->
						   <div class="widget-content">
							 <form method="post" class="form-horizontal" id = "form" 
									action="<c:url value="/Download/getRequests"/>">
										<fieldset>
										<div class="control-groups">								
											<label class="control-label">Status</label>
											<div class="controls"> 	
											<select id="status" name="status">  <!--Call run() function-->
	                                            <option
														value="ALL">ALL</option>
	                                            <option
														value="Request Created">Request Created</option>
	                                            <option
														value="Request Processing">Request Processing</option>
	                                            <option
														value="Request Rejected">Request Rejected</option>
	                                            <option value="Dispatched">Dispatched</option>
	                                            <option value="Delayed">Delayed</option>
	                                            <option value="Delivered">Delivered</option>
	                                            <option value="RTO">RTO</option>
	                                        </select>
											<!-- /control-group -->
											</div>
										</div>
										<div class="control-groups" style="margin-top: 20px">								
											<label class="control-label">Date Range</label>
											
											<div class="controls"> 	
													<input type="text" name="dateRange" id="daterange">

                                            </div>
										</div>
										<div class="control-groups" style="margin-top: 20px">								
											<label class="control-label">Seller Code</label>
											
											<div class="controls"> 	
												<input type="text" name="sellerCode" id="sellerCode">
                                            </div>
										</div>
										
										<div class="control-groups" style="margin-top: 20px">								
											<label class="control-label">Warehouse</label>
											
											<div class="controls"> 	
												<select name="warehouse" class="form-control required">
												<option selected disabled value="">Select Warehouse</option>
												<c:forEach var="wh" items="${warehouse}">
												<option value="${wh.id}">${wh.code}</option>
												</c:forEach>
												</select>
                                            </div>
										</div>
										
										<div class="control-groups" style="margin-top: 20px">								
											<label class="control-label">Courier</label>
											
											<div class="controls"> 	
												<select name="courier" class="form-control required">
												<option selected disabled value="">Select Courier</option>
												<c:forEach var="courier" items="${courier}">
												<option value="${courier.id}">${courier.code}</option>
												</c:forEach>
												</select>
                                            </div>
										</div>
										
										<div class="control-groups" style="margin-top: 20px;"  >	
											<center>
												<button type="submit"  id= "submit" class="btn btn-primary">Submit</button>
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



