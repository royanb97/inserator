package servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ibm.db2.jcc.am.SqlException;

import utils.DB2Util;

/**
 * Servlet implementation class mainServlet
 */
@WebServlet("/main")
public class mainServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public mainServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		RequestDispatcher rd = getServletContext().getRequestDispatcher("/main.jsp");
		rd.include(request, response);
		
		
		String title = null;
		float price = 0;
		String text = null;
		String creator = null;
		
		Connection con = null;
		try {
			con = DB2Util.getExternalConnection("project");
			PreparedStatement ps = con.prepareStatement("SELECT Titel, Preis, Text, Ersteller FROM dbp20.anzeige WHERE status='aktiv'");
			ResultSet rs = ps.executeQuery();
			while(rs.next()) {
				title = rs.getString("Titel");
				price = rs.getFloat("Preis");
				text = rs.getString("Text");
				creator = rs.getString("Ersteller");
				System.out.println(title + price + text + creator);
				PrintWriter out= response.getWriter();
				out.write("<img src='/WebContent/placeholder.jpg' />");
				out.write("<a href=''>" + title + "</a>" + " " + ", " + price + ", " + text + ", " + creator + "<br />" + "<br />");
			}
		} catch (SQLException sqle) {
			PrintWriter dberr = response.getWriter();
			dberr.write("<font color=red>"+"Sorry, die Verbindung zur Datenbank ging verloren." + "</font>");
			sqle.printStackTrace();
		} finally {
			try {
				con.close();
			} catch (SQLException e) {
				PrintWriter dberr = response.getWriter();
				dberr.write("<font color=red>"+"Sorry, die Verbindung zur Datenbank ging verloren." + "</font>");
				e.printStackTrace();
			}
		}
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}
}
