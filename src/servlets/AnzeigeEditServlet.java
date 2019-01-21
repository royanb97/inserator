package servlets;

import java.io.IOException;
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
 * Servlet implementation class AnzeigeEditServlet
 */
@WebServlet("/AnzeigeEditServlet")
public class AnzeigeEditServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public AnzeigeEditServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		int anzeigeID = Integer.parseInt(request.getParameter("id"));
		String title = null;
		float price = 0;
		String description = null;
		
		
		Connection con = null;
		
		try {
			con = DB2Util.getExternalConnection("jspprj");
			PreparedStatement ps = con.prepareStatement("SELECT titel, text, preis FROM dbp20.anzeige WHERE id=?");
			ps.setInt(1, anzeigeID);
			ResultSet rs = ps.executeQuery();
			while(rs.next()) {
				title = rs.getString("titel");
				price = rs.getFloat("preis");
				description = rs.getString("text");
			}
			
		} catch (SQLException sqle) {
			sqle.printStackTrace();
		} finally {
			try {
				con.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		request.setAttribute("title", title);
		request.setAttribute("id", anzeigeID);
		request.setAttribute("price", price);
		request.setAttribute("description", description);
		request.getRequestDispatcher("AnzeigeEdit.jsp").forward(request, response);
		
		
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		int anzeigeID = Integer.parseInt(request.getParameter("id"));
		String title = request.getParameter("title");
		float price = Float.parseFloat(request.getParameter("price"));
		String category = request.getParameter("category");
		String description = request.getParameter("description");
		
		Connection con = null;
		try {
			con = DB2Util.getExternalConnection("jspprj");
			PreparedStatement psu = con.prepareStatement("UPDATE dbp20.anzeige SET titel=?, preis=?, text=? WHERE id=?");
			PreparedStatement psuc = con.prepareStatement("UPDATE dbp20.hatkategorie SET kategorie=? WHERE anzeigeid=?");
			
			psu.setString(1, title);
			psu.setFloat(2, price);
			psu.setString(3, description);
			psu.setInt(4, anzeigeID);
			
			psuc.setString(1, category);
			psuc.setInt(2, anzeigeID);
			
			psu.executeUpdate();
			psuc.executeUpdate();
			
			
		}catch(SQLException sqle) {
			sqle.printStackTrace();
		} finally {
			try {
				con.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		response.sendRedirect(request.getContextPath() + "/main");
	}

}
