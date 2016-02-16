<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="tags" tagdir="/WEB-INF/tags"%>
<%@ page isELIgnored="false"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
pageEncoding="UTF-8"%>
<tags:resources>
	<jsp:attribute name="title">Login Form</jsp:attribute>
	<jsp:body>
	<div class="container">
		<div class="row">
			
<div class="account-container">
	
	<div class="content clearfix">
		
		<form action="/PCA/j_spring_security_check" method="post">
		
			<h1>PCA Login</h1>		
			
			<div class="login-fields">
				
				<p>Please provide your details</p>
				<p></p>
				<div class="field">
					<label for="username">Username</label>
					<input type="text" id="username" name="username" value="" placeholder="Username" class="login username-field" />
				</div> <!-- /field -->
				
				<div class="field">
					<label for="password">Password:</label>
					<input type="password" id="password" name="password" value="" placeholder="Password" class="login password-field"/>
				</div> <!-- /password -->
				
			</div> <!-- /login-fields -->
			
			<div class="login-actions">
									
				<button type="submit" class="button btn btn-success btn-large" >Sign In</button>
				
			</div> <!-- .actions -->
			
			
			
		</form>
		
	</div> <!-- /content -->
	
</div> <!-- /account-container -->
		</div>
	</div>
	<!-- <footer>
	<div class="row">
		<div class="col-sm-12" style="text-align: center">&copy;
			Snapdeal.com</div>
		/.col
	</div>
	/.row </footer>
	 -->		
</jsp:body>
</tags:resources>