package servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;

import utils.DB2Util;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import store.Anzeige;

/**
 * Servlet implementation class AnzeigeErstellenServlet
 */
@WebServlet("/AnzeigeErstellenServlet")
public class AnzeigeErstellenServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
     
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String title = request.getParameter("title");
		String errorMsg= null;
		float price = Float.parseFloat(request.getParameter("price"));
		if (price<0)
			errorMsg = "Preis darf nicht negativ sein.";
		String description = request.getParameter("description");
		
		if(description.length()>100)
			errorMsg = "Laenge der Beschreibung Ueberschreitet die Anzahl erlaubter Zeichen von 100";
		String category = request.getParameter("category");
		if(errorMsg != null){
			PrintWriter out= response.getWriter();
			out.println("<font color=red>"+errorMsg+"</font>");
			RequestDispatcher rd = getServletContext().getRequestDispatcher("/AnzeigeErstellen.jsp");
			rd.include(request, response);
			
		} else {
			Connection con;
			try {
				con = DB2Util.getExternalConnection("project");
				try (PreparedStatement ps = con.prepareStatement("insert into dbp20.anzeige (titel, text, preis, ersteller, status) values(?,?,?,?,?)");
						PreparedStatement ps2 = con.prepareStatement("insert into dbp20.hatkategorie (kategorie) values (?)");) {
					ps.setString(1, title);
					ps.setString(2, description);
					ps.setFloat(3, price);
					ps.setString(4, "k.ralf");
					ps.setString(5, "aktiv");
					ps2.setString(1, category);
					System.out.println("Erstelle " + title + ", " + description + " mit Kategorie " + category);
					ps.execute();
					RequestDispatcher rd = getServletContext().getRequestDispatcher("/AnzeigeErstellt.html");
					rd.include(request, response); 
					ps.close();
					ps2.close();
					
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} 
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			
				
			
		}
	}

}
