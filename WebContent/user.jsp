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
	<% ArrayList<String> offered = (ArrayList<String>) request.getAttribute("offered"); %>
	<h1>Profil von <%= request.getAttribute("user") %></h1>
	
	<h3><%= request.getAttribute("name") %>, beigetreten: <%= request.getAttribute("registDate") %></h3>
	
	<h2><%= request.getAttribute("user") %> bietet oder hat verkauft:</h2>
	
	<% for(int i=0; i<offered.size(); i++){ %>
	<h3><% out.println(offered.get(i)); %></h3>
	
	<% } %>
	
	<hr>
	
	<h2><%= request.getAttribute("user") %> hat folgende Artikel gekauft:</h2>
	
</body>
</html>