<html>
   <body>
      <h1>Anzeige erstellen</h1>

      <form action = "anzeige_erstellenServlet" method = "POST">
         Titel: <input type = "text" name = "title">
         <br />
         Preis: <input type = "text" name = "price" />
         <br/>
         Beschreibung: <input type = "text" name = "description" />
         <br/>Kategorie:
        <input type="radio" name=category value="Digitale Waren"/>Digitale Waren
        <input type="radio" name=category value="Haus & Garten"/>Haus & Garten
        <input type="radio" name=category value="Mode & Kosmetik"/>Mode & Kosmetik
        <input type="radio" name=category value="Multimedia & Elektronik"/>Multimedia & Elektronik

         <input type = "submit" value = "Erstellen" />
      </form>
      
   </body>
</html>