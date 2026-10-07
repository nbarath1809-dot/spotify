<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Spotify Login</title>

<link rel="stylesheet" href="login.css">

</head>
<body>
	<%
String msg = request.getParameter("msg");

if("timeout".equals(msg)){
%>

<script>
alert("Session expired due to inactivity (2 Minutes).");
</script>

<%
}
%>

	<div class="container">

		<h1>Login</h1>

		<form id="loginForm" action="LoginServlet" method="post">

			<input type="email" id="loginEmail" name="email"
				placeholder="Enter Email" required>

			<input type="password" id="loginPassword" name="password"
				placeholder="Enter Password" required>

			<button type="submit">Login</button>

		</form>

		<p>
			New User?
			<a href="register.jsp">Register Here</a>
		</p>

	</div>

	<script src="login.js"></script>

</body>
</html>