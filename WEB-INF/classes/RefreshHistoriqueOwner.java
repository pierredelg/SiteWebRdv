// ServletOwner.java 
import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.WebServlet;
import java.sql.*;

@WebServlet("/refreshBDD")
public class ResultOwner extends HttpServlet
{

    
    public void service( HttpServletRequest req, HttpServletResponse res ) 
	throws ServletException, IOException
    {

	enum names = req.getParameterNames();

	for(int i = 0; i < names.length(); i++){
	    out.println("<p>"+names[i]+"</p>");
	}
	String nom = "";
	try {
	    // On déclare le type de driver JDBC et le chemin d’accès à la base, si pb exception ClassNotFound
	    Class.forName("org.sqlite.JDBC");
	    String dbURL =  "jdbc:sqlite:../webapps/projetWeb/BDD/data.db";
	    //On essaye de se connecter à la base
	    Connection conn = DriverManager.getConnection(dbURL);
	    if (conn != null) {
		
		// un Statement est une interface qui représente une instruction SQL
		 Statement stat = conn.createStatement();

		 String query = "delete from CLIENTS where NOM = (select NOM from CLIENTS INNER JOIN RDV ON (CLIENTS.ID = RDV.IDCLIENT)";
		 query += " where  CLIENTS.NOM = "+nom+")";	 
		 		 
		 // le resultat des select sont mis dans les ResultSet
		 ResultSet rs = stat.executeUpdate(query);
		

	
		 //mm requete que rdv sur nom
		 //tableau
		     
	       		
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

	
    }
}



//res.sendRedirect("historiqueOwner");
