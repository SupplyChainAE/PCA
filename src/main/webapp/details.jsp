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
		$("#dataTable").dataTable({});	
		$('#daterange').daterangepicker({
			format : "YYYY/MM/DD"
		});

	});
	
	
	
	function getModalData(id)
	{
		
		$.ajax({url : "/PCA/Download/getData",
					data : {
						id : id,
						
					},
					type : "post",
					success : function(
							result) {
						
						$("#courierBody").empty();
					$("#courierBody").append("<tr><td>"+result.warehouseName+"</td><td>"+result.courierName +"</td><td>"+result.awb+"</td>");
					$("#detailsBody").empty();
					$("#detailsBody").append("<tr><td>6.5*6.5</td><td>"+result.para1 +"</td><td>"+result.para1d+"</td>"+
					"</tr><tr><td>8.5*11.5</td><td>"+result.para2 +"</td><td>"+result.para2d +"</td></tr>"+
					/* "<tr><td>10*10</td><td>"+result.para3 +"</td><td>"+result.para3d +"</td></tr>"+ */
				 	"<tr><td>11.5*13.5</td><td>"+result.para4 +"</td><td>"+result.para4d +"</td></tr>"+
					"<tr><td>13.5*16</td><td>"+result.para5 +"</td><td>"+result.para5d +"</td></tr>"+
					"<tr><td>15.5*18</td><td>"+result.para6 +"</td><td>"+result.para6d +"</td></tr>"+
					"<tr><td>17.5*19</td><td>"+result.para7 +"</td><td>"+result.para7d +"</td></tr>"+
					"<tr><td>20*22.5</td><td>"+result.para10 +"</td><td>"+result.para10d +"</td></tr>"+
					"<tr><td>22.5*24.5</td><td>"+result.para11 +"</td><td>"+result.para11d +"</td></tr>"+
					"<tr><td>Tape </td><td>"+result.para8 +"</td><td>"+result.para8d +"</td></tr>"+
					"<tr><td>Sticker </td><td>"+result.para9 +"</td><td>"+result.para9d +"</td></tr>");
					}
		});
		
		
		$("#requestModal").modal('show');
		
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
              			<h3>Requests</h3>
              			<div style="float: right;padding-top: 10px; padding-right: 10px " >
              				 <form method="post" class="form-horizontal" id = "form" 
									action="<c:url value="/Download/downloadRequests"/>">
									<input type="text" name="cond" class="hidden" value = "${condition}">
									<input type="text" name="status" class="hidden" value = "${status}">
									<button type="submit"  id= "submit" class="btn btn-success" style="margin-left: 30px; margin-bottom: 20px;">Download</button>				
              				<%-- <a href="/PCA/Download/downloadRequests?cond=${condition}&status=${status} "class ="btn btn-success" style="margin-left: 30px" >Download Requests</a> --%>
              				</form>
              			</div>
           				 </div>	
           				 
           				 	
           				   <div class="widget-content">
									<table id="dataTable"
									class="table table-striped table-bordered bootstrap-datatable datatable">
										<thead>
											<tr>
												<th>Request Id</th>
												<th>Request Status</th>
												<th>Seller Code</th>
												<th>Seller Name</th>
												<th>Action</th>
												<th>Created on</th>
											</tr>
										</thead>
										<tbody>
											<c:forEach var="SellerRequest1" items="${requestList}">
									<tr>
													<td>${SellerRequest1.requestId}</td>
													<td>${SellerRequest1.requestStatus}</td>
													<td>${SellerRequest1.sellerCode}</td>
													<td>${SellerRequest1.sellerName}</td>
													<td align="center">
											
														<button type="submit" class="btn btn-success edit" id="${SellerRequest1.id}" onclick="getModalData(${SellerRequest1.id})" >Show Details</button>
														
											
										</td>
													
													<td>${SellerRequest1.createdOn}</td>
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
	
	<div class="modal fade" id ="requestModal">
  	<div class="modal-dialog modal-lg">
    <div class="modal-content">
      <div class="modal-header">
        <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
        <h4 class="modal-title">Request Parameters</h4>
      </div>
      <div class="modal-body">
      	<div class="span5">
			<div class="widget-header"> <i class="icon-th-list"></i>
              			<h3>Courier Details</h3>
           				   
		 </div>       	
		 <div class="widget-content">
           				  	<table id="dataTable"
									class="table table-striped table-bordered bootstrap-datatable datatable">
										<thead>
											<tr>
												<th>Warehouse</th>
												<th>Courier </th>
												<th>AWB</th>
											</tr>
										</thead>
										<tbody id = "courierBody"></tbody>
										</table>
        </div>
        
        <br>
        <br>
        </div>
        <div class="span5">
			<div class="widget-header"> <i class="icon-th-list"></i>
              			<h3>Request Details</h3>
           				 </div>	
           				   <div class="widget-content">
           				  	<table id="dataTable"
									class="table table-striped table-bordered bootstrap-datatable datatable">
										<thead>
											<tr>
												<th>Parameters</th>
												<th>Order Qty</th>
												<th>Dispatch Qty</th>
											</tr>
										</thead>
										<tbody id = "detailsBody">
															
										</tbody>
								</table> 
           				   
			</div>	      		
		 </div>       	
        </div>
      </div>
      <div class="modal-footer">
        <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
        
      </div>
    </div><!-- /.modal-content -->
  </div><!-- /.modal-dialog -->
<!-- /.modal -->


	
	
	</jsp:body>
</tags:page>



