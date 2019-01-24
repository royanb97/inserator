<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ page import="java.util.ArrayList" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Anzeige Details</title>
</head>
<body>
	<% ArrayList<String> comment = (ArrayList<String>) request.getAttribute("comment");
	   ArrayList<String> commentName = (ArrayList<String>) request.getAttribute("commentName");%>
	
	<h1>Anzeige Details</h1>
	
	<h2><%= request.getAttribute("title") %></h2>
	<h4>von <a href="user?user=<%= request.getAttribute("seller") %>"> <%= request.getAttribute("seller") %></a></h4>
	<img src="placeholder.jpg" height=300 width=500/>
	<br/>
	<p><i>Erstellt am: <%= request.getAttribute("timestamp") %></i></p>
	<h3><font color="blue"><%= request.getAttribute("price") %> EUR</font></h3>
	<p><%= request.getAttribute("description") %></p>
	<br/>
	
	<% if(!request.getAttribute("buyer").equals(request.getAttribute("seller"))) { %>
	 <button onclick="window.location.href = 'Kaufen?id=<%= request.getAttribute("id")%>';">Kaufen</button>
	<% } %>
	
	<button onclick="window.location.href='AnzeigeEditServlet?id=<%= request.getAttribute("id") %>';">Bearbeiten</button>
	<button onclick="window.location.href='Delete?id=<%= request.getAttribute("id") %>';">Löschen</button>
	
	<br/>
	<hr/>
	<h3>Kommentare</h3>
	<% for(int i=0; i<comment.size(); i++){ %>
	<p><i><% out.println(commentName.get(i)); %> schrieb: </i>
	<p><% out.println(comment.get(i)); %>
	<% } %>
	
	<form action="AnzeigeDetails?anzeigeParam=<%= request.getAttribute("anzeigeParam")%>" method="POST">
	 <input type="text" name="newComment" style="width:600px; height:100px" required>
	 <input type="submit" value="Kommentar erstellen">
	
	</form>
</body>
</html>