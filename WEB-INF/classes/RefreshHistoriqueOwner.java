// ServletOwner.java 
import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.WebServlet;
import java.sql.*;
import java.util.*;

@WebServlet("/refreshBDD")
public class RefreshHistoriqueOwner extends HttpServlet
{

    
    public void service( HttpServletRequest req, HttpServletResponse res ) 
	throws ServletException, IOException
    {

	List<String> tabNomsADelete = new ArrayList<>();
	String delete = "";
	int i = -1;
	
	try {
	    // On déclare le type de driver JDBC et le chemin d’accès à la base, si pb exception ClassNotFound
	    Class.forName("org.sqlite.JDBC");
	    String dbURL =  "jdbc:sqlite:../webapps/projetWeb/BDD/data.db";
	    //On essaye de se connecter à la base
	    Connection conn = DriverManager.getConnection(dbURL);
	    if (conn != null) {
		
		// un Statement est une interface qui représente une instruction SQL
		Statement stat = conn.createStatement();

		String noms = "SELECT NOM FROM CLIENTS INNER JOIN RDV on CLIENTS.ID = RDV.IDCLIENT ORDER BY ID DESC";
		ResultSet rs = stat.executeQuery(noms);
		while(rs.next()){
		    
		    if(req.getParameter(rs.getString("NOM")) != null){
			i++;
			tabNomsADelete.add(rs.getString("NOM"));
		    }
		}
		rs.close();

		
		for(String nom : tabNomsADelete){
		     
		    delete = "delete from CLIENTS where NOM = (select NOM from CLIENTS INNER JOIN RDV ON (CLIENTS.ID = RDV.IDCLIENT)";
		    delete += " where  CLIENTS.NOM = \""+nom+"\")";	 
		 		 
		    // le resultat des select sont mis dans les ResultSet
		    stat.executeUpdate(delete);
		}
			
		 
		stat.close();
		conn.close();
	    
	    }
	    
	    tabNomsADelete.removeAll(tabNomsADelete);
	    
	}catch (ClassNotFoundException ex) {
	    ex.printStackTrace();
	}
	catch (SQLException ex) {
	    ex.printStackTrace();
	}

       	res.sendRedirect("historiqueOwner");
    }
}




