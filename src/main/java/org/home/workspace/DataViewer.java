package org.home.workspace;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.home.workspace.utils.StringUtil;

@WebServlet("/dataViewer") 
public class DataViewer extends HttpServlet { 
	private static final long serialVersionUID = 1L; 
 
    public DataViewer() { 
        super(); 
    } 
 
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException { 

		response.getWriter().append("Served at: ").append(request.getContextPath()); 
	} 
 
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException { 
 
		response.setContentType("text/html"); 
		PrintWriter out = response.getWriter(); 
		out.println("<HTML>");
		out.println("<HEAD>");
		out.println("<TITLE> Display Products </TITLE>");
		out.println("</HEAD>");
		out.println("<BODY>");
		out.println("<CENTER>");
		out.println("<BR><H2>Displaying All Products </H2>");
		out.println("<BR>");
		out.println("<BR>");
		out.println("<TABLE style=\"border: 1px solid black;\">");
		out.println("<TR>");
		out.println("<TH>Id </TH>");
		out.println("<TH>Name </TH>");
		out.println("<TH>Products</TH>");
		out.println("</TR>");
 
		String ID = request.getParameter("id"); 
		int id = Integer.parseInt(ID); 
         
		try { 
			Class.forName("com.mysql.cj.jdbc.Driver"); 
			Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/teksystems", "root", "Teksystems@2021"); 
			PreparedStatement ps = con.prepareStatement("select * from productsdb where id=?"); 
			ps.setInt(1, id); 
			ResultSet rs = ps.executeQuery(); 
			
			while(rs.next()) {
				out.println("<TR>");
				out.println("<TD>"+rs.getInt(1) + "</TD>");
				out.println("<TD>"+StringUtil.encodeHtmlTag(rs.getString(2)) + "</TD>");
				out.println("<TD>"+StringUtil.encodeHtmlTag(rs.getString(3)) + "</TD>");
				out.println("</TR>");
			}
 
		} catch (Exception e) { 
			e.printStackTrace(); 
		} finally { 
			out.close(); 
		} 
		
		out.println("</TABLE>");
		out.println("</CENTER>");
		out.println("</BODY>");
		out.println("</HTML>");
	 
	} 
 
}



