<!DOCTYPE html>

<html lang="en">
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<head>
<meta charset="utf-8">
<title>Dashboard - Bootstrap Admin Template</title>
<meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=no">
<meta name="apple-mobile-web-app-capable" content="yes">
<link href="css/bootstrap.min.css" rel="stylesheet">
<link href="css/bootstrap-responsive.min.css" rel="stylesheet">
<link href="http://fonts.googleapis.com/css?family=Open+Sans:400italic,600italic,400,600"
        rel="stylesheet">
<link href="<c:url value="/css/daterangepicker-bs3.css" />" rel="stylesheet">


<script src="<c:url value="/js/daterangepicker.min.js"/>"></script>
<script src="<c:url value="/js/jquery.validate.js"/>"></script>    		
<link href="css/font-awesome.css" rel="stylesheet">
<link href="css/style.css" rel="stylesheet">
<!-- Le HTML5 shim, for IE6-8 support of HTML5 elements -->
<!--[if lt IE 9]>
      <script src="http://html5shim.googlecode.com/svn/trunk/html5.js"></script>
    <![endif]-->
</head>
<body>
<div class="navbar navbar-fixed-top">
  <div class="navbar-inner">
    <div class="container"> <a class="btn btn-navbar" data-toggle="collapse" data-target=".nav-collapse"><span
                    class="icon-bar"></span><span class="icon-bar"></span><span class="icon-bar"></span> </a><a class="brand" href="index.html">Bootstrap Admin Template </a>
      <div class="nav-collapse">
        <ul class="nav pull-right">
          <li class="dropdown"><a href="#" class="dropdown-toggle" data-toggle="dropdown"><i
                            class="icon-cog"></i> Account <b class="caret"></b></a>
            <ul class="dropdown-menu">
              <li><a href="javascript:;">Settings</a></li>
              <li><a href="javascript:;">Help</a></li>
            </ul>
          </li>
          <li class="dropdown"><a href="#" class="dropdown-toggle" data-toggle="dropdown"><i
                            class="icon-user"></i> EGrappler.com <b class="caret"></b></a>
            <ul class="dropdown-menu">
              <li><a href="javascript:;">Profile</a></li>
              <li><a href="javascript:;">Logout</a></li>
            </ul>
          </li>
        </ul>
        <form class="navbar-search pull-right">
          <input type="text" class="search-query" placeholder="Search">
        </form>
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
			<li ><a href="<c:url value="/User/Create"/>"><i
					class="icon-user"></i><span>User</span> </a></li>
				<li ><a href="<c:url value="/home"/>"><i
						class="icon-dashboard"></i><span>Dashboard</span> </a></li>
				<li><a href="<c:url value="/upload"/>"><i class="icon-upload-alt"></i><span>Upload ASR</span>
				</a></li>
				<li><a href="<c:url value="/view"/>"><i class="icon-list-alt"></i><span>View ASR</span> </a>
				</li>
				<li><a href="<c:url value="/update"/>"><i class="icon-edit"></i><span>Update ASR Status</span>
				</a></li>
				<li><a href="<c:url value="/reports"/>"><i class="icon-code"></i><span>Reports</span>
				</a></li>
				<li class="dropdown"><a href="javascript:;"
					class="dropdown-toggle" data-toggle="dropdown"> <i
						class="icon-long-arrow-down"></i><span>Admin</span> <b
						class="caret"></b>
				</a>
					<ul class="dropdown-menu">
						<li><a href="<c:url value="/user"/>">Users</a>
						</li>
						<li><a href="<c:url value="/warehouse"/>">Warehouse</a>
						</li>
						<li><a href="<c:url value="/seller"/>">Sellers</a>
						</li>
						<li><a href="<c:url value="/status"/>">Status</a>
						</li>
						
					</ul></li>
			</ul>
		</div>
		<!-- /container -->
	</div>
	<!-- /subnavbar-inner -->
</div>
<!-- /subnavbar -->
<!-- /subnavbar -->
<div class="main">
  <div class="main-inner">
    <div class="container">
      <br/>
	<br/>
<br/>
<br/><br/>
<br/>YOUR CONTENT GOES HERE<br/>
<br/><br/>
<br/><br/>
<br/><br/>	 
<br/><br/>
<br/><br/>	 
<br/><br/>
<br/><br/>	 
    </div>
    <!-- /container --> 
  </div>
  <!-- /main-inner --> 
</div>
<div class="footer">
  <div class="footer-inner">
    <div class="container">
      <div class="row">
        <div class="span12"> &copy; 2013 <a href="http://www.egrappler.com/">Bootstrap Responsive Admin Template</a>. </div>
        <!-- /span12 --> 
      </div>
      <!-- /row --> 
    </div>
    <!-- /container --> 
  </div>
  <!-- /footer-inner --> 
</div>
<!-- /footer --> 
<!-- Le javascript
================================================== --> 
<!-- Placed at the end of the document so the pages load faster --> 
<script src="js/jquery-1.7.2.min.js"></script> 
<script src="js/bootstrap.js"></script>
<script src="<c:url value="/js/bootstrap-datepicker.min.js"/>"></script>
<script src="<c:url value="/js/daterangepicker.min.js"/>"></script>
    		
</body>
</html>