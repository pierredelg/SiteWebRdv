import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.WebServlet;
import java.sql.*;
/*
Servlet qui permet de changer l'identifiant et le mot de passe de l'administrateur
*/
@WebServlet("/ChangePassOwner")
public class ChangePassOwner extends HttpServlet
{

    
    public void service( HttpServletRequest req, HttpServletResponse res ) 
	throws ServletException, IOException
    {
   	//On récupere les informations des textfields
	String identifiant = req.getParameter("identifiant");
	String password = req.getParameter("password");

	String id = "";
	String pass = "";

	PrintWriter out = res.getWriter(); 

	res.setContentType( "text/html" );
	out.println("<!doctype html>");
	out.println("<head> <meta charset=utf-8/>");
	out.println("<link rel=\"stylesheet\" type=\"text/css\" href=\"/projetWeb/style/changePassOwner.css\" />");

	try {
	   
	    // On déclare le type de driver JDBC et le chemin d’accès à la base, si pb exception ClassNotFound
	    Class.forName("org.sqlite.JDBC");
	    String dbURL =  "jdbc:sqlite:../webapps/projetWeb/BDD/data.db";
	    //On essaye de se connecter à la base
	    Connection conn = DriverManager.getConnection(dbURL);
	    if (conn != null) {
		// un Statement est une interface qui représente une instruction SQL
		Statement stat = conn.createStatement();
		
		// le resultat du select est mis dans un ResultSet
		ResultSet rs = stat.executeQuery( "SELECT * FROM utilisateurs;" );
		
		//On recupere l'identifiant et le mot de passe de la base de donnée et on les remplace par les nouvelles données
		while(rs.next()){
		    id = rs.getString("identifiant");
		    pass = rs.getString("password");
		    stat.executeUpdate("UPDATE utilisateurs SET identifiant = REPLACE(identifiant,'"+id+"','"+identifiant+"');");
		    stat.executeUpdate("UPDATE utilisateurs SET password = REPLACE(password,'"+pass+"','"+password+"');");
		}
	       
		
		out.println("</head><body>");
		out.println("<header>");
		out.println("<nav><ul><li></li></ul></nav>");
		out.println("</header>");
		out.println("<main>");
		out.println("<h1> MOT DE PASSE ENREGISTRÉ </h1>");
		out.println("<p>Valider le changement svp !!</p><div>");
		out.println("<div><form  name=\"loginOwner\" method=\"post\"  action=\"login.html\">");
	       	out.println("<button>VALIDER</button>");
		out.println("</div></main>");
		
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

	out.println("<footer></footer>");   
	out.println("</body></html> ");
    }
}
