package servlets;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
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
 * Servlet implementation class KaufenServlet
 */
@WebServlet("/Kaufen")
public class KaufenServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public KaufenServlet() {
        super();
        // TODO Auto-generated constructor stub
    }


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		int anzeigeID = Integer.parseInt(request.getParameter("id"));
		String buyer = "k.ralf"; //Standard User already logged in
		
			
		Connection con = null;
		
		try {
			con = DB2Util.getExternalConnection("jspprj");
			PreparedStatement ps = con.prepareStatement("INSERT INTO dbp20.Kauft (Benutzername, AnzeigeID) VALUES (?,?)");
			ps.setString(1, buyer);
			ps.setInt(2, anzeigeID);
			ps.execute();
			
			RequestDispatcher rd = getServletContext().getRequestDispatcher("/Gekauft.html");
			rd.include(request, response);
			
		} catch(SQLException sqle) {
			sqle.printStackTrace();
		} finally {
			try {
				con.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		
		
		
		
		
		
		
	}

	
	
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
