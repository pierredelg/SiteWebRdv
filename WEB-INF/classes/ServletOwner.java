// ServletOwner.java 
import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.WebServlet;
import java.sql.*;

@WebServlet("/servletOwner")
public class ServletOwner extends HttpServlet
{


	public void service( HttpServletRequest req, HttpServletResponse res ) 
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


				//VERIFIE SI TU AS LE TEMPS AVEC LES BUTTONS CACHÉS
				//J'AI DÉJA ESSAYÉ MAIS JE COMPRENDS PAS PK CA MARCHE PAS
				
				/*out.println("<form><input type=\"hidden\" name=\"identifiant\" value=\"pierre\">");
				out.println("<input type=\"hidden\" name=\"password\" \" value=\"projet\"></form>");
				out.println("<p>"+identifiant+" "+password+"</p>");*/
		
				if((identifiant.equals(id) && password.equals(pass))){
					out.println("</head><body>");
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
					out.println("</header><main>");
					out.println("<h1>Bienvenue dans votre espace "+identifiant.toUpperCase()+"</h1>");
					out.println("<section><img src=\"image/parametre.png\" alt=\"[roue]\" /></section>");


				}else{
					out.println("</head><body class=\"result\">");
					out.println("<header>");
					out.println("<nav><ul><li></li></ul></nav>");
					out.println("</header><main> ");
					out.println("<h1 class=\"erreur\">ERREUR AUTHENTIFICATION !!</h1>");
					out.println("<p>Identifiant et/ou Password INCORRECTE !!!</p>");	     
					out.println("<form name=\"loginOwner\" method=\"post\" action=\"login.html\">");
					out.println("<button>CONNEXION</button>");

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

		out.println("</main>");   
		out.println("</body></html> ");
	}
}
