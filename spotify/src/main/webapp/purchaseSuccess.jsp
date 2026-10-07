<%@ page language="java" contentType="text/html; charset=UTF-8"
pageEncoding="UTF-8"%>

<%
String username = (String) session.getAttribute("username");

if(username == null){
    response.sendRedirect("login.jsp");
    return;
}

String product = (String) session.getAttribute("product");
Double price = (Double) session.getAttribute("price");
%>

<!DOCTYPE html>
<html>

<head>

<meta charset="UTF-8">

<title>Purchase Successful</title>

<link rel="stylesheet" href="success.css">

</head>

<body>

<div class="container">

    <div class="success-card">

        <div class="icon">
            ✔
        </div>

        <h1>Purchase Successful</h1>

        <p>
            Thank you,
            <strong><%= username %></strong>
        </p>

        <h2><%= product %></h2>

        <h3>Amount Paid : ₹<%= price %></h3>

        <p class="msg">
            Your Spotify Premium subscription has been activated successfully.
        </p>

        <div class="buttons">

            <a href="store.jsp" class="btn">
                Back to Store
            </a>

            <a href="PurchaseHistoryServlet" class="btn">
				Purchase History
			</a>

            <a href="home.jsp" class="btn">
                Home
            </a>

        </div>

    </div>

</div>

</body>

</html>