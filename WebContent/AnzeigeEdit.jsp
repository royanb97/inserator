<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Anzeige bearbeiten</title>
</head>
<body>
 <h1>Bearbeite Deine Anzeige</h1>
 <form action = "AnzeigeEditServlet?id=<%= request.getAttribute("id") %>" method = "POST">
         Titel: <input type = "text" name = "title" value="<%= request.getAttribute("title") %>" required />
         <br />
         Preis: <input type = "text" name = "price" value="<%= request.getAttribute("price") %>" required />
         <br />
         Beschreibung: <input type = "text" name = "description" value="<%= request.getAttribute("description") %>"style="width: 350px; height: 100px" required />
         <br />
         Kategorie: <input type="radio" name="category" value="Digitale Waren" checked> Digitale Waren
         			<input type="radio" name="category" value="Haus & Garten"> Haus & Garten
         			<input type="radio" name="category" value="Mode & Kosmetik"> Mode & Kosmetik
         			<input type="radio" name="category" value="Multimedia & Elektronik"> Multimedia & Elektronik
         			
         <input type = "submit" value = "Erstellen" />
      </form>
</body>
</html>