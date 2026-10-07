<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Spotify Register</title>

<link rel="stylesheet" href="register.css">

</head>
<body>

<div class="container">

    <h1>Create Account</h1>

    <form id="registerForm" action="RegisterServlet" method="post">

        <input
            type="text"
            id="name"
            name="name"
            placeholder="Full Name"
            required>

        <input
            type="email"
            id="email"
            name="email"
            placeholder="Email"
            required>

        <input
            type="password"
            id="password"
            name="password"
            placeholder="Password"
            required>

        <input
            type="password"
            id="confirmPassword"
            name="confirmPassword"
            placeholder="Confirm Password"
            required>

        <input
            type="number"
            id="age"
            name="age"
            placeholder="Age"
            required>

        <button type="submit">
            Register
        </button>

    </form>

    <p>
        Already have an account?
        <a href="login.jsp">Login</a>
    </p>

</div>

<script src="register.js"></script>

</body>
</html>