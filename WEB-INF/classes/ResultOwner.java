// ServletOwner.java 
import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.WebServlet;
import java.sql.*;

@WebServlet("/historiqueOwner")
public class ResultOwner extends HttpServlet
{

    
    public void service( HttpServletRequest req, HttpServletResponse res ) 
	throws ServletException, IOException
    {
	
	PrintWriter out = res.getWriter();  
	res.setContentType( "text/html" );
	out.println("<!doctype html>");
	out.println("<html>");
	out.println("<head> <meta charset=utf-8/>");
	out.println("<link rel=\"stylesheet\" type=\"text/css\" href='"+req.getContextPath()+"/style/historiqueOwner.css' />");
	out.println("</head><body class=\"result\">");
	out.println("<header>");
	out.println("<nav>");
	out.println("<ul>");
	out.println("<li><a href=\"http://localhost:8080/projetWeb/servletOwner\">Accueil</a></li>");
	out.println("<li><a href=\"http://localhost:8080/projetWeb/configowner.html\">Configuration</a></li>");
	out.println("<li><a href=\"http://localhost:8080/projetWeb/historiqueOwner\">Rendez-vous</a></li>");
	out.println("<li><a href=\"http://localhost:8080/projetWeb/changedPassOwner.html\">Mot de passe</a></li>");
	out.println("<li><a href=\"http://localhost:8080/projetWeb/login.html\">Log out</a></li>");
	out.println("</ul>");
	out.println("</nav>");
	out.println("</header>");
	out.println("<main>");
	out.println("<h1>DÉTAIL DES RENDEZ-VOUS</h1>");
	out.println("<table>");

	try {
	    // On déclare le type de driver JDBC et le chemin d’accès à la base, si pb exception ClassNotFound
	    Class.forName("org.sqlite.JDBC");
	    String dbURL =  "jdbc:sqlite:../webapps/projetWeb/BDD/data.db";
	    //On essaye de se connecter à la base
	    Connection conn = DriverManager.getConnection(dbURL);
	    if (conn != null) {
		
		// un Statement est une interface qui représente une instruction SQL
		 Statement stat = conn.createStatement();

		 String query = "SELECT * FROM CLIENTS , RDV";
		 query += " WHERE ClIENTS.ID = RDV.IDCLIENT";

		 
		 
		 
		 // le resultat du select est mis dans un ResultSet
		 ResultSet rs = stat.executeQuery(query);
		 ResultSetMetaData rsMeta = rs.getMetaData();
		 out.print("<tr>");
		 for(int i = 1; i <= rsMeta.getColumnCount(); i++)
		     if(!rsMeta.getColumnName(i).equals("ID") && !rsMeta.getColumnName(i).equals("IDCLIENT"))
			 out.print("<th>"+rsMeta.getColumnName(i)+"</th>");
		 out.println("</tr>");
			 
		 while(rs.next()){
		     out.println("<tr>");
		     for(int i = 1; i <= rsMeta.getColumnCount(); i++)
			 if(!rsMeta.getColumnName(i).equals("ID") && !rsMeta.getColumnName(i).equals("IDCLIENT")){
			     
			     if(!rs.getString(rsMeta.getColumnName(i)).equals("null"))
				 out.print("<td>"+rs.getString(rsMeta.getColumnName(i))+"</td>");
			     else
				 out.print("<td> - </td>");
			 }
		     out.print("</tr>");
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

	

	out.println("</table>");
	out.println("</main>");   
	out.println("</body></html> ");
    }
}
