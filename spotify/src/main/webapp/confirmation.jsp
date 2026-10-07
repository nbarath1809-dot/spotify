<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<!DOCTYPE html>

<html>

<head>

<meta charset="UTF-8">

<title>Registration Successful</title>

<link rel="stylesheet" href="confirmation.css">

</head>

<body>

<div class="container">

    <div class="success-icon">
        ✅
    </div>

    <h2>Registration Successful</h2>

    <div class="info">

        <p>
            <b>Name :</b>
            <%= session.getAttribute("name") %>
        </p>

        <p>
            <b>Email :</b>
            <%= session.getAttribute("email") %>
        </p>

    </div>

    <a href="login.jsp" class="login-btn">
        Login Now
    </a>

</div>

</body>

</html>