// ServletOwner.java 
import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.WebServlet;
import java.sql.*;

@WebServlet("/resultOwner")
public class ResultOwner extends HttpServlet
{

    
    public void doGet( HttpServletRequest req, HttpServletResponse res ) 
	throws ServletException, IOException
    {
	
	PrintWriter out = res.getWriter();  
	res.setContentType( "text/html" );
	out.println("<!doctype html>");
	out.println("<head> <meta charset=utf-8/>");
	out.println("<link rel=\"stylesheet\" type=\"text/css\" href='"+req.getContextPath()+"/style/loginOwner.css' />");
	out.println("</head><body class=\"result\">");
	out.println("<header><h1>DÉTAIL DES RENDEZ-VOUS</h1></header><main>");
	out.println("<table>");
	out.println("<tr><th>ID</th><th>IDENTIFIANT</th><th>PASSWORD</th></tr>");
	

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
		 int i = 1;
		 while(rs.next()){
		    String id = rs.getString("identifiant");
		    String pass = rs.getString("password");
		     out.println("<tr><td>"+i+"</td><td>"+id+"</td><td>"+pass+"</td></tr>");
		     i++;
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
	out.println("</main><footer><form name=\"loginOwner\" method=\"get\" action=\"loginOwner.html\">");
	out.println("<button class=\"out\">LOG OUT</button></footer>");   
	out.println("</body></html> ");
    }
}
