// ServletOwner.java 
import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.WebServlet;
import java.sql.*;

@WebServlet("/servletOwner")
public class ServletOwner extends HttpServlet
{

    
    public void doGet( HttpServletRequest req, HttpServletResponse res ) 
	throws ServletException, IOException
    {

	String identifiant = req.getParameter("identifiant");
	String password = req.getParameter("password");
	String id = "";
	String pass = "";

	PrintWriter out = res.getWriter();  
	res.setContentType( "text/html" );
	out.println("<!doctype html>");
	out.println("<head> <meta charset=utf-8/>");
	out.println("<link rel=\"stylesheet\" type=\"text/css\" href='"+req.getContextPath()+"/loginOwner.css' />");
	out.println("</head><body><center> <br><br><br>");
	

	try {
	    // On déclare le type de driver JDBC et le chemin d’accès à la base, si pb exception ClassNotFound
	    Class.forName("org.sqlite.JDBC");
	    String dbURL =  "jdbc:sqlite:./data.db";
	    //On essaye de se connecter à la base
	    Connection conn = DriverManager.getConnection(dbURL);
	    if (conn != null) {
		
		// un Statement est une interface qui représente une instruction SQL
		 Statement stat = conn.createStatement();
		
		 // le resultat du select est mis dans un ResultSet
		 ResultSet rs = stat.executeQuery( "SELECT * FROM utilisateurs;" );
		
		 while(rs.next()){
		     id = rs.getString("identifiant");
		     pass = rs.getString("password");
		 }
		
		 if(identifiant.equals(id) && password.equals(pass)){
	  
		     out.println("<h1>BIENVENUE "+identifiant+"</h1> ");

		     
		     out.println("<br><br><br><br><br><br><p>");
		     out.println("<div><form name=\"configuration\" method=\"get\" action=\"configuaration.html\">");
		     out.println("<input type=\"submit\"  name=\"configuration\" value=\"Configuration\"></div>");
		     
		     out.println("<br><br><br><br><br><br>");
		     out.println("<div><form name=\"servletResultat\" method=\"get\" action=\"http://localhost:8080/projetWeb/servletResultat\">");
		     out.println("<input type=\"submit\"  name=\"resultat\" value=\"Historique\"></div>");
		     
		 }else{
	   
		     out.println("<h1>ERREUR !!!!</h1> ");
		     out.println("<br><br><br><br><br><br>");
		     out.println("<form name=\"loginOwner\" method=\"get\" action=\"loginOwner.html\">");
		     out.println("<input type=\"submit\"  name=\"réessayer\" value=\"RÉESAYER\">");
		    
		 }
    
		 rs.close();
		 stat.close();
		 conn.close();
	    }
	}catch (ClassNotFoundException ex) {
	    ex.printStackTrace();
	}
	catch (SQLException ex) {
	    ex.printStackTrace();
	    out.println("<p>raté é</p>");
	}

	


	out.println("<br></center>");   
	out.println("</body></html> ");
    }
}
