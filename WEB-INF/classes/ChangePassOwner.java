import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.WebServlet;
import java.sql.*;

@WebServlet("/ChangePassOwner")
public class ChangePassOwner extends HttpServlet
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
		
		while(rs.next()){
		    id = rs.getString("identifiant");
		    pass = rs.getString("password");
		    stat.executeUpdate("UPDATE utilisateurs SET identifiant = REPLACE(identifiant,'"+id+"','"+identifiant+"');");
		    stat.executeUpdate("UPDATE utilisateurs SET password = REPLACE(password,'"+pass+"','"+password+"');");
		}
	       
		
		out.println("</head><body>");
		out.println("<header><h1> MOT DE PASSE ENREGISTRÉ </h1></header>");
		out.println("<main><p>Valider le changement svp !!</p><div>");
		out.println("<form  name=\"loginOwner\" method=\"post\"  action=\"loginOwner.html\">");
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