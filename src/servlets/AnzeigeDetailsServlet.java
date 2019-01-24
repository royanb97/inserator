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
		ArrayList<String> comment = new ArrayList<>();
		ArrayList<String> commentName = new ArrayList<>();
		
		
		Connection con = null;
		
		//Anzeige-Sektion
		try {
			con = DB2Util.getExternalConnection("jspprj");
			PreparedStatement ps = con.prepareStatement("SELECT Titel, Ersteller, Preis, Text, Erstellungsdatum FROM dbp20.Anzeige WHERE id=?");
			PreparedStatement ps2 = con.prepareStatement("SELECT Text, Benutzername FROM dbp20.Kommentar, dbp20.HatKommentar WHERE id=kommentarid AND anzeigeid=? ORDER BY erstellungsdatum ASC");
			ps.setInt(1, anzeigeID);
			ps2.setInt(1, anzeigeID);
			
			ResultSet rs = ps.executeQuery();
			ResultSet rs2 = ps2.executeQuery();
			
			while(rs.next()) {
				title = rs.getString("Titel");
				seller = rs.getString("Ersteller");
				price = rs.getFloat("Preis");
				description = rs.getString("Text");
				timestamp = rs.getString("Erstellungsdatum");
								
			}
			
			while(rs2.next()) {
				comment.add(rs2.getString("Text"));
				commentName.add(rs2.getString("Benutzername"));
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
		request.setAttribute("comment", comment);
		request.setAttribute("commentName", commentName);
		request.setAttribute("anzeigeParam", anzeigeID);
		request.getRequestDispatcher("AnzeigeDetails.jsp").forward(request, response);
		
		
		
		
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int anzeigeID = Integer.parseInt(request.getParameter("anzeigeParam"));
		String user="k.ralf";
		String newComment = request.getParameter("newComment");
		int commentID = 0;
		Connection con = null;
		
		try {
			con = DB2Util.getExternalConnection("jspprj");
			PreparedStatement ps = con.prepareStatement("INSERT INTO dbp20.Kommentar (Text) values (?)");
			PreparedStatement ps2 = con.prepareStatement("INSERT INTO dbp20.hatkommentar (Benutzername, anzeigeid, kommentarid) values (?,?,?)");
			PreparedStatement fetchID = con.prepareStatement("SELECT id from dbp20.kommentar ORDER BY id DESC FETCH FIRST ROW ONLY");
			ps.setString(1, newComment);
			ps.execute();
			
			ResultSet rs = fetchID.executeQuery();
			while(rs.next()) {
				commentID = rs.getInt("id");
			}
			
			ps2.setString(1, user);
			ps2.setInt(2, anzeigeID);
			ps2.setInt(3, commentID);
			ps2.execute();
			
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				con.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		response.sendRedirect(request.getContextPath() + "/AnzeigeDetails?anzeigeParam="+anzeigeID);
	}

}
