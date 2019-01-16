package servlets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class AnzeigeErstellenServlet
 */
@WebServlet("/AnzeigeErstellenServlet")
public class AnzeigeErstellenServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
     
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String title = request.getParameter("title");
		String errorMsg= null;
		int price =  Integer.parseInt(request.getParameter("price"));
		if(price < 0)
			errorMsg = "Preisfeld kann nicht kleiner als 0€ sein.";
		String description = request.getParameter("description");
		String category = request.getParameter("category");
		
		doGet(request, response);
	}

}
