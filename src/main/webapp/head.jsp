
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page isELIgnored="false"%>
<%@ taglib uri="http://www.springframework.org/security/tags"    prefix="security"%>
<%@ page import="org.springframework.security.core.context.SecurityContextHolder"%>
<%@ page import="com.snapdeal.entity.User"%>
<% User sessionUser = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();%>

<div class="navbar navbar-fixed-top">
	<div class="navbar-inner">
		<div class="container">
			
			<a class="btn btn-navbar" data-toggle="collapse"
				data-target=".nav-collapse"><span class="icon-bar"></span><span
				class="icon-bar"></span><span class="icon-bar"></span> </a><a
				class="brand" href="<c:url value="/home"/>">PCA(Packaging Courier Allocation and Status Update)</a>
			<div class="nav-collapse">
				<ul class="nav pull-right">
					<li class="dropdown"><a href="#" class="dropdown-toggle"
						data-toggle="dropdown"><i class="icon-user"></i> <%=SecurityContextHolder.getContext().getAuthentication()
					.getName()%>
							<b class="caret"></b> </a>
						<ul class="dropdown-menu">
							<li><a href="<c:url value="/j_spring_security_logout"/>">Logout</a></li>
						</ul>
					</li>
				</ul>
				<!--  <form class="navbar-search pull-right">
					<input type="text" class="search-query" placeholder="Search">
				</form> -->
			</div>
			<!--/.nav-collapse -->
		</div>
		<!-- /container -->
	</div>
	<!-- /navbar-inner -->
</div>
<!-- /navbar -->
<div class="subnavbar">
	<div class="subnavbar-inner">
		<div class="container">
		
			<ul id="mainnav" class="mainnav">
				<security:authorize access="hasAnyRole('ROLE_ADMIN')">
				<li id="user" class="dropdown "><a href="#" class="dropdown-toggle" data-toggle="dropdown"> <i class="icon-user"></i><span>Users</span> <b class="caret"></b> </a>
					<ul class="dropdown-menu">
						<li class="subnavbar-open-right"><a href="/PCA/User/create">Create</a>
						</li>
						<li><a href="/PCA/User/view">View/Edit</a>
						</li>
					</ul>
				</li>
				</security:authorize>
				<security:authorize access="hasAnyRole('ROLE_ADMIN')">
				
				<li id="warehouse" class="dropdown"><a href="#" class="dropdown-toggle" data-toggle="dropdown"> <i class="icon-inbox"></i><span>Warehouse</span> <b class="caret"></b>
				</a>
					<ul class="dropdown-menu">
						<li class="subnavbar-open-right"><a href="/PCA/Warehouse/create">Create</a>
						</li>
						<li><a href="/PCA/Warehouse/view">View/Edit</a>
						</li>
					</ul>
				</li>
				</security:authorize>
				<security:authorize access="hasAnyRole('ROLE_ADMIN')">
				
				<li id="courier" class="dropdown"><a href="#" class="dropdown-toggle" data-toggle="dropdown"> <i class="shortcut-icon icon-list-alt"></i><span>Courier</span> <b class="caret"></b>
				</a>
					<ul class="dropdown-menu">
						<li class="subnavbar-open-right"><a href="/PCA/Courier/create">Create</a>
						</li>
						<li><a href="/PCA/Courier/view">View/Edit</a>
						</li>
						<li><a href="/PCA/Courier/priority">Priority</a>
						</li>
					</ul>
				</li>
			</security:authorize>
				<li id="downloadRequest"><a href="<c:url value="/Download/downloadRequest"/>"><i
						class="icon-download"></i><span>Download Requests</span> </a>
				</li>
				<li id="upload"><a href="<c:url value="/Upload/uploadRequest"/>"><i
						class="icon-upload-alt"></i><span>Upload Request</span> </a>
				</li>
				<security:authorize access="hasAnyRole('ROLE_ADMIN')">
				<li class="dropdown"><a href="javascript:;"
					class="dropdown-toggle" data-toggle="dropdown"> <i
						class="icon-long-arrow-down"></i><span>Admin</span> <b
						class="caret"></b> </a>
					<ul class="dropdown-menu">
						<li><a href="<c:url value="/Mapping/pincodeWarehouse"/>">Pincode Warehouse Mapping</a></li>
						<li><a href="<c:url value="/Mapping/courierAwb"/>">Courier Awb Mapping</a></li>
						<li><a href="<c:url value="/Mapping/pincodeCourier"/>">Courier Pincode Mapping</a></li>
						<li><a href="<c:url value="/Mapping/parameterPrice"/>">Parameters Price Mapping</a></li>
						
					</ul>
				</li>
				</security:authorize>
			</ul>
		</div>
		<!-- /container -->
	</div>
	<!-- /subnavbar-inner -->
</div>
<!-- /subnavbar -->

