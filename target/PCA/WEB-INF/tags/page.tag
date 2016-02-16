<%@ tag description="Page Components" language="java" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ attribute name="title" required="true"%>
<%@ attribute name="script" fragment="true"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=no">
<meta name="apple-mobile-web-app-capable" content="yes">
<meta charset="utf-8">
<title>PCA</title>
<jsp:include page="/resources.jsp" />
<jsp:invoke fragment="script" />
</head>
<body>
	<jsp:include page="/head.jsp" />
	<div class="container">
		<div class="row">

			<jsp:doBody />
		</div>
	</div>
<!-- <footer> -->
<!-- 	<div class="footer"> -->
<!--   <div class="footer-inner"> -->
<!--     <div class="container"> -->
<!--       <div class="row"> -->
<!--         <div class="span12"> &copy;  <a href="#">Snapdeal.com</a></div> -->
<!--         /span12  -->
<!--       </div> -->
<!--       /row  -->
<!--     </div> -->
<!--     /container  -->
<!--   </div> -->
<!--   /footer-inner  -->
<!-- </div> -->
<!-- </footer> -->
</body>
</html>
