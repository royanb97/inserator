<%@ page import="Connection" %>

<html>
   <meta http-equiv="Refresh" content="5;url=anzeige_erstellen.html">
   <head>
      <title>Danke für das Erstellen ihrer Anzeige!</title>
   </head>
   
   <body>
   		<h1>Danke für das Erstellen ihrer Anzeige!</h1>
      <ul>
         <li><p><b>Titel:</b>
            <%= request.getParameter("title")%>
         </p></li>
         <li><p><b>Preis:</b>
            <%= request.getParameter("price") + " Euro"%>
         </p></li>
         <li><p><b>Beschreibung:</b>
            <%= request.getParameter("description")%>
         </p></li>
         <li><p><b>Kategorie:</b>
            <%= request.getParameter("category")%>
         </p></li>
      </ul>

      <%= getConnection("ins") %>

      
   </body>
</html>