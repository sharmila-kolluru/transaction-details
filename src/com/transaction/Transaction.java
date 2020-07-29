package com.transaction;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.connection.DatabaseConnection;
/**
 * Servlet implementation class Transaction
 */
@WebServlet("/Transaction")
public class Transaction extends HttpServlet {
	private static final long serialVersionUID = 1L;
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		try {
			Connection con = DatabaseConnection.getConnection();
		Statement stmt =con.createStatement(); 
		String ps ="select balance from transaction ORDER BY id DESC LIMIT 1";
				ResultSet rs1 = stmt.executeQuery(ps);
				int total;
		rs1.next();
		 int amount= rs1.getInt(1)-1000;
		String ps1 = "update transaction SET balance = ' " + amount + " ' ORDER BY id DESC LIMIT 1";
		 total= stmt.executeUpdate(ps1);
         if(total>0)
			{
	  			response.setContentType("text/html");
				PrintWriter pw=response.getWriter();
				pw.println("<script type=\"text/javascript\">");
				pw.println("alert('transaction succesful');");
			    pw.println("</script>");
				RequestDispatcher rd=request.getRequestDispatcher("transaction.jsp");
				rd.include(request, response);
			}
		}
		catch (Exception e) {
			e.printStackTrace();
		}

}
}
