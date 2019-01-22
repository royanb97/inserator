package servlets;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import utils.DB2Util;

/**
 * Servlet implementation class userServlet
 */
@WebServlet("/user")
public class userServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       

    public userServlet() {
        super();
        // TODO Auto-generated constructor stub
    }


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String user = request.getParameter("user");
		String name = null;
		String registDate = null;
		Connection con = null;
		ArrayList<String> offered = new ArrayList<>();
		
		try {
			con = DB2Util.getExternalConnection("jspprj");
			PreparedStatement ps = con.prepareStatement("SELECT name, eintrittsdatum FROM dbp20.Benutzer WHERE benutzername=?");
			PreparedStatement ps2 = con.prepareStatement("SELECT titel, preis, erstellungsdatum FROM dbp20.anzeige WHERE ersteller=?");
			ps.setString(1, user);
			ps2.setString(1, user);
			ResultSet rs = ps.executeQuery();
			ResultSet rs2 = ps2.executeQuery();
			
			while(rs.next()) {
				name = rs.getString("name");
				registDate = rs.getString("eintrittsdatum");
				registDate = registDate.substring(0, 10);
			}
			
			while(rs2.next()) {
				offered.add(rs2.getString("titel"));
			}
			
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
		
		
		request.setAttribute("name", name);
		request.setAttribute("user", user);
		request.setAttribute("registDate", registDate);
		request.setAttribute("offered", offered);
		request.getRequestDispatcher("user.jsp").include(request, response);
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
