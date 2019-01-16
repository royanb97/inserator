<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <meta charset="UTF-8">
<html>
   <body>
      <h1>Erstelle eine Anzeige</h1>
      <form action = "" method = "POST">
         Titel: <input type = "text" name = "first_name">
         <br />
         Preis: <input type = "text" name = "last_name" />
         <br />
         Beschreibung: <input type = "text" name = "description" />
         <br />
         Kategorie: <input type="radio" name="category" value="Digitale Waren" checked> Digitale Waren
         			<input type="radio" name="category" value="Haus und Garten"> Haus & Garten
         			<input type="radio" name="category" value="Mode und Kosmetik"> Mode & Kosmetik
         			<input type="radio" name="category" value="Multimedia und Elektronik"> Multimedia & Elektronik
         			
         <input type = "submit" value = "Erstellen" />
      </form>
      
   </body>
</html>