<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ page import="java.util.ArrayList" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Benutzerprofil</title>
</head>
<body>
	<% ArrayList<String> offered = (ArrayList<String>) request.getAttribute("offered");
	ArrayList<Float> offeredPrice = (ArrayList<Float>) request.getAttribute("offeredPrice");
	ArrayList<String> offeredDate = (ArrayList<String>) request.getAttribute("offeredDate");
	ArrayList<String> offeredStatus = (ArrayList<String>) request.getAttribute("offeredStatus");
	
	ArrayList<String> sold = (ArrayList<String>) request.getAttribute("sold");
	ArrayList<Float> soldPrice = (ArrayList<Float>) request.getAttribute("soldPrice");
	ArrayList<String> soldDate = (ArrayList<String>) request.getAttribute("soldDate");
	ArrayList<String> soldSeller = (ArrayList<String>) request.getAttribute("soldSeller");
	%>
	<h1>Profil von <%= request.getAttribute("user") %></h1>
	
	<h3><%= request.getAttribute("name") %>, beigetreten: <%= request.getAttribute("registDate") %></h3>
	
	<h2><%= request.getAttribute("user") %> bietet oder hat verkauft:</h2>
	
	<% for(int i=0; i<offered.size(); i++){ %>
	<h3><% out.println(offered.get(i)); %></h3>
	<p><% out.println(offeredPrice.get(i)); %> Euro
	<p>Seit <% out.println(offeredDate.get(i).substring(0,10)); %>
	<p><i><% out.println(offeredStatus.get(i)); %></i>
	
	<% } %>
	
	<hr>
	
	<h2><%= request.getAttribute("user") %> hat folgende Artikel gekauft:</h2>
	
	<% for(int i=0; i<sold.size(); i++){ %>
	<h3><% out.println(sold.get(i)); %></h3>
	<p><% out.println(soldPrice.get(i)); %> Euro
	<p>Seit <% out.println(soldDate.get(i).substring(0,10)); %>
	<p>von <% out.println(soldSeller.get(i)); %>
	<p><i>verkauft</i>
	
	<% } %>
	
</body>
</html>