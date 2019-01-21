package servlets;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ibm.db2.jcc.am.SqlException;

import utils.DB2Util;

/**
 * Servlet implementation class DeleteServlet
 */
@WebServlet("/Delete")
public class DeleteServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public DeleteServlet() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int anzeigeID = Integer.parseInt(request.getParameter("id"));
		Connection con = null;
		
		try {
			con = DB2Util.getExternalConnection("jspprj");
			
			PreparedStatement ps = con.prepareStatement("DELETE dbp20.anzeige WHERE id=?");
			PreparedStatement ps2 = con.prepareStatement("DELETE dbp20.hatkategorie WHERE anzeigeid=?");			
			ps.setInt(1, anzeigeID);
			ps2.setInt(1, anzeigeID);
			ps.executeUpdate();
			ps2.executeUpdate();
			
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
		
		response.sendRedirect(request.getContextPath() + "/main");
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
