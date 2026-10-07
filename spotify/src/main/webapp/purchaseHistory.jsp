<%@ page language="java" contentType="text/html; charset=UTF-8"
pageEncoding="UTF-8"%>

<%@ page import="java.util.ArrayList" %>

<%
ArrayList<String[]> history =
(ArrayList<String[]>)request.getAttribute("history");
%>

<!DOCTYPE html>

<html>

<head>

<meta charset="UTF-8">

<title>Purchase History</title>

<link rel="stylesheet" href="history.css">

</head>

<body>

<div class="container">

<h1>Purchase History</h1>

<table>

<tr>

<th>Product</th>

<th>Price</th>

<th>Purchase Date</th>

</tr>

<%

if(history!=null){

for(String row[]:history){

%>

<tr>

<td><%=row[0]%></td>

<td>₹<%=row[1]%></td>

<td><%=row[2]%></td>

</tr>

<%

}

}

else{

%>

<tr>

<td colspan="3">

No Purchases Found

</td>

</tr>

<%

}

%>

</table>

<br>

<a href="store.jsp" class="btn">
Back to Store
</a>

<a href="home.jsp" class="btn">
Home
</a>

</div>

</body>

</html>