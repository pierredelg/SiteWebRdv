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
	out.println("<link rel=\"stylesheet\" type=\"text/css\" href='"+req.getContextPath()+"/style/loginOwner.css' />");
	out.println("</head><body>");

	

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
		    
		     out.println("<header><h1>BIENVENUE "+identifiant.toUpperCase()+"</h1></header><main> ");

		     out.println("<section><img src=\"image/parametre.png\" alt=\"[roue]\" /></section>");
		     
		     out.println("<section class=\"button\"><form  name=\"configuration\" method=\"get\" action=\"configuaration.html\">");
		     out.println("<input  class=\"button1\" type=\"submit\"  name=\"configuration\" value=\"Configuration\">");
		     
		     out.println("<form  name=\"servletResultat\" method=\"get\" action=\"http://localhost:8080/projetWeb/servletResultat\">");
		     out.println("<input  class=\"button2\" type=\"submit\"  name=\"resultat\" value=\"Historique\"></section>");
		     
		 }else{
		   
		     out.println("<header><h1>ERREUR !!!!</h1></header><main> ");
		     out.println("<section class=\"button\"><p class=\"message\">Identifiant et/ou Password INCORRECTE !!!</p>");
		     
		     out.println("<form name=\"loginOwner\" method=\"get\" action=\"loginOwner.html\">");
		     out.println("<input class=\"button3\" type=\"submit\"  name=\"réesayer\" value=\"Connexion\"></section>");
		    
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

	


	out.println("</main><footer></footer>");   
	out.println("</body></html> ");
    }
}
