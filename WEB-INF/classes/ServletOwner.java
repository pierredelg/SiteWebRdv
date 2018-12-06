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
	out.println("<link rel=\"stylesheet\" type=\"text/css\" href=\"/projetWeb/style/loginOwner.css\" />");

	try {
	   
	    // On déclare le type de driver JDBC et le chemin d’accès à la base, si pb exception ClassNotFound
	    Class.forName("org.sqlite.JDBC");
	    String dbURL =  "jdbc:sqlite://localhost:8080/projetWeb/data.db";
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
		    out.println("<header><h1>BIENVENUE "+identifiant.toUpperCase()+"</h1></header><main> ");


		    out.println("<section><img src=\"image/parametre.png\" alt=\"[roue]\" /></section>");
		    out.println("<input type=\"hidden\" name=\"infos\" value="+pass+">");
		     
		    out.println("<section class=\"button\"><form  name=\"configuration\" method=\"get\"  action=\"http://localhost:8080/projetWeb/resultOwner\">");
		    out.println("<button class=\"button1\">Configuration</button>");
		    out.println("<button class=\"button2\">Historique</button></section>");

		     
		}else{
		    out.println("</head><body class=\"result\">");
		    out.println("<header><h1>ERREUR !!!!</h1></header><main> ");
		    out.println("<section class=\"button\"><p class=\"message\">Identifiant et/ou Password INCORRECTE !!!</p>");	     
		    out.println("<form name=\"loginOwner\" method=\"get\" action=\"loginOwner.html\">");
		    out.println("<button class=\"button3\">CONNEXION</button></section>");
		    
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
