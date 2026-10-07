<%@ page language="java" contentType="text/html; charset=UTF-8"
pageEncoding="UTF-8"%>

<%
String username=(String)session.getAttribute("username");

if(username==null){
    response.sendRedirect("login.jsp");
    return;
}
%>

<!DOCTYPE html>
<html>

<head>

<meta charset="UTF-8">

<title>Spotify Premium Store</title>

<link rel="stylesheet" href="store.css">

</head>

<body>

<div class="container">

<h1>Spotify Premium Store</h1>

<div class="plans">

<div class="card">

<h2>Premium Individual</h2>

<p>✔ Ad-Free Music</p>

<p>✔ Offline Download</p>

<p>✔ Unlimited Skips</p>

<h3>₹119 / Month</h3>

<form action="PurchaseServlet" method="post">

<input type="hidden" name="product" value="Premium Individual">

<input type="hidden" name="price" value="119">

<input type="submit" value="Buy Now">

</form>

</div>

<div class="card">

<h2>Premium Duo</h2>

<p>✔ Two Premium Accounts</p>

<p>✔ Offline Download</p>

<p>✔ Unlimited Skips</p>

<h3>₹149 / Month</h3>

<form action="PurchaseServlet" method="post">

<input type="hidden" name="product" value="Premium Duo">

<input type="hidden" name="price" value="149">

<input type="submit" value="Buy Now">

</form>

</div>

<div class="card">

<h2>Premium Family</h2>

<p>✔ Up to 6 Accounts</p>

<p>✔ Ad-Free Music</p>

<p>✔ Offline Download</p>

<h3>₹179 / Month</h3>

<form action="PurchaseServlet" method="post">

<input type="hidden" name="product" value="Premium Family">

<input type="hidden" name="price" value="179">

<input type="submit" value="Buy Now">

</form>

</div>

<div class="card">

<h2>Premium Student</h2>

<p>✔ Student Discount</p>

<p>✔ Ad-Free Music</p>

<p>✔ Offline Download</p>

<h3>₹59 / Month</h3>

<form action="PurchaseServlet" method="post">

<input type="hidden" name="product" value="Premium Student">

<input type="hidden" name="price" value="59">

<input type="submit" value="Buy Now">

</form>

</div>

</div>

<br>

<a href="home.jsp" class="homeBtn">⬅ Back to Home</a>

</div>

</body>

</html>