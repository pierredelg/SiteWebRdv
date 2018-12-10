// ServletOwner.java 
import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.WebServlet;
import java.sql.*;

@WebServlet("/servletOwner")
public class ServletOwner extends HttpServlet
{

    
    public void doPost( HttpServletRequest req, HttpServletResponse res ) 
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
	out.println("<link rel=\"stylesheet\" type=\"text/css\" href=\"/projetWeb/style/loginOwner.css\" />");

	try {
	   
	    // On déclare le type de driver JDBC et le chemin d’accès à la base, si pb exception ClassNotFound
	    Class.forName("org.sqlite.JDBC");
	    String dbURL =  "jdbc:sqlite:../webapps/projetWeb/BDD/data.db";
	    //	out.println("<!doctype html>");
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
		    out.println("</head><body>");
		    out.println("<header><div class =\"droit\"><h1>BIENVENUE "+identifiant.toUpperCase()+"</h1></div><div class=\"gauche\"><a href=\"changedPassOwner.html\">Changed password</a></div></header><main> ");


		    out.println("<section><img src=\"image/parametre.png\" alt=\"[roue]\" /></section>");
		     
		    out.println("<div class=\"buttons\"><form  name=\"configuration\" method=\"post\"  action=\"http://localhost:8080/projetWeb/configOwner\">");
		    out.println("<button class=\"button1\">Configuration</button></form>");

		    out.println("<form  name=\"historique\" method=\"post\"  action=\"http://localhost:8080/projetWeb/historiqueOwner\">");
		    out.println("<button class=\"button2\">Historique</button></form></div>");

		     
		}else{
		    out.println("</head><body class=\"result\">");
		    out.println("<header><h1>ERREUR !!!!</h1></header><main> ");
		    out.println("<div class=\"button\"><p class=\"message\">Identifiant et/ou Password INCORRECTE !!!</p>");	     
		    out.println("<form name=\"loginOwner\" method=\"post\" action=\"login.html\">");
		    out.println("<button class=\"button3\">CONNEXION</button></div>");
		    
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
	}

	out.println("</main><footer></footer>");   
	out.println("</body></html> ");
    }
}
