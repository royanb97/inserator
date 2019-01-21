package servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ibm.db2.jcc.am.SqlException;

import utils.DB2Util;

/**
 * Servlet implementation class AnzeigeDetailsServlet
 */
@WebServlet("/AnzeigeDetails")
public class AnzeigeDetailsServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       

    public AnzeigeDetailsServlet() {
        super();
        // TODO Auto-generated constructor stub
    }


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		int anzeigeID = Integer.parseInt(request.getParameter("anzeigeParam"));
	
		String title = null;
		float price = 0;
		String description = null;
		String timestamp = null;
		String seller = null;
		String buyer = "k.ralf"; //Example user who's already logged in
		String comment = null;
		String commentCreator = null;
		
		
		Connection con = null;
		
		//Anzeige-Sektion
		try {
			con = DB2Util.getExternalConnection("jspprj");
			PreparedStatement ps = con.prepareStatement("SELECT Titel, Ersteller, Preis, Text, Erstellungsdatum FROM dbp20.Anzeige WHERE id=?");
			ps.setInt(1, anzeigeID);
			ResultSet rs = ps.executeQuery();
			while(rs.next()) {
				title = rs.getString("Titel");
				seller = rs.getString("Ersteller");
				price = rs.getFloat("Preis");
				description = rs.getString("Text");
				timestamp = rs.getString("Erstellungsdatum");
				//System.out.println(title);				
			}
			
		} catch (SQLException sqle) {
			sqle.printStackTrace();
			PrintWriter dberr = response.getWriter();
			dberr.write("<font color=red>"+"Sorry, die Verbindung zur Datenbank ging verloren." + "</font>");
		} finally {
			try {
				con.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
		//Forward Attributes to JSP
		request.setAttribute("title", title);
		request.setAttribute("id", anzeigeID);
		request.setAttribute("price", price);
		request.setAttribute("description", description);
		request.setAttribute("timestamp", timestamp);
		request.setAttribute("seller", seller);
		request.setAttribute("buyer", buyer);
		request.getRequestDispatcher("AnzeigeDetails.jsp").forward(request, response);
		
		
		
		
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
