<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Anzeige Details</title>
</head>
<body>
	<h1>Anzeige Details</h1>
	
	<h2><%= request.getAttribute("title") %></h2>
	<h4>von <%= request.getAttribute("seller") %></h4>
	<img src="placeholder.jpg" height=300 width=500/>
	<br/>
	<p><i>Erstellt am: <%= request.getAttribute("timestamp") %></i></p>
	<h3><font color="blue"><%= request.getAttribute("price") %> EUR</font></h3>
	<p><%= request.getAttribute("description") %></p>
	<br/>
	
	<% if(!request.getAttribute("buyer").equals(request.getAttribute("seller"))) { %>
	 <button onclick="window.location.href = 'Kaufen?id=<%= request.getAttribute("id")%>';">Kaufen</button>
	<% } %>
	
	
	<button>Bearbeiten</button>
	<button>Löschen</button>
	<br/>
	<hr/>
	<h3>Kommentare</h3>
	<p>null</p>
</body>
</html>