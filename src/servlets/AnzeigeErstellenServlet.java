package servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

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
		float price =  Float.parseFloat(request.getParameter("price"));
		if(price < 0)
			errorMsg = "Preisfeld kann nicht kleiner als 0 EUR sein.";
		String description = request.getParameter("description");
		if(description.length()>100)
			errorMsg = "Laenge der Beschreibung Ueberschreitet die Anzahl erlaubter Zeichen von 100";
		String category = request.getParameter("category");
		if(errorMsg != null){
			RequestDispatcher rd = getServletContext().getRequestDispatcher("/login.html");
			PrintWriter out= response.getWriter();
			out.println("<font color=red>"+errorMsg+"</font>");
			rd.include(request, response);
		} else {
			PreparedStatement ps = null;
			ResultSet rs = null;
			try {
				Connection con;
				con = DB2Util.getExternalConnection("project");
				ps = con.prepareStatement("insert into anzeige values(?,?,?,?,?)");
				ps.setString(1, title);
				ps.setString(2, description);
				ps.setFloat(3, price);
				ps.setString(4, "John Doe");
				ps.setString(5, "aktiv");
				rs = ps.executeQuery();
		
				
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} finally {
				try {
					rs.close();
					ps.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
			}
		}
	}

}
