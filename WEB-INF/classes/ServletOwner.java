// ServletOwner.java 
import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.WebServlet;
import java.sql.*;

/*
Servlet qui permet de vérifier l'identifiant et le mot de passe de la page login
Mais aussi l'affichage de la page d'accueil coté Administrateur
*/
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

		//On déclare une session 
		HttpSession session = req.getSession();

		/*si l'identifiant et le mot de passe est null
		Lorsque l'on revient sur la page par la navbar
		on récupere l'identifiant et le mot de passe de la session*/

		if(identifiant == null && password == null){
			identifiant = (String)session.getAttribute("identifiant");
			password = (String)session.getAttribute("password");
		}

		PrintWriter out = res.getWriter();  
		res.setContentType( "text/html" );
		out.println("<!doctype html>");
		out.println("<head> <meta charset=utf-8/>");
		out.println("<link rel=\"stylesheet\" type=\"text/css\" href=\"/projetWeb/style/loginOwner.css\" />");

		try {

	    	// On déclare le type de driver JDBC et le chemin d’accès à la base, si pb exception ClassNotFound
			Class.forName("org.sqlite.JDBC");
			String dbURL =  "jdbc:sqlite:../webapps/projetWeb/BDD/data.db";

	    	//On essaye de se connecter à la base
			Connection conn = DriverManager.getConnection(dbURL);
			if (conn != null) {

				// un Statement est une interface qui représente une instruction SQL
				Statement stat = conn.createStatement();

				// le resultat du select est mis dans un ResultSet on selectionne l'identifiant et le mot de passe de la base
				ResultSet rs = stat.executeQuery( "SELECT * FROM utilisateurs;" );

				//On récupere le mot de passe et l'identifiant de la base de donnée
				while(rs.next()){
					id = rs.getString("identifiant");
					pass = rs.getString("password");
				}
				//si l'identifiant et le mot de passe est bon on affiche la page d'acceuil coté administrateur
				if((identifiant.equals(id) && password.equals(pass))){
					session.setAttribute("identifiant",id);
					session.setAttribute("password",pass);
					out.println("</head><body>");
					out.println("<header>");
					out.println("<nav>");
					out.println("<ul>");
					out.println("<li><a href=\"http://localhost:8080/projetWeb/servletOwner\">Accueil</a></li>");
					out.println("<li><a href=\"http://localhost:8080/projetWeb/configowner.html\">Configuration</a></li>");
					out.println("<li><a href=\"http://localhost:8080/projetWeb/historiqueOwner\">Rendez-vous</a></li>");
					out.println("<li><a href=\"http://localhost:8080/projetWeb/changedPassOwner.html\">Mot de passe</a></li>");
					out.println("<li><a href=\"http://localhost:8080/projetWeb/login.html\">Deconnexion</a></li>");
					out.println("</ul>");
					out.println("</nav>");
					out.println("</header><main>");
					out.println("<h1>Bienvenue dans votre espace<span class=\"identity\"> "+identifiant.toUpperCase()+"</span></h1>");
					out.println("<section><img src=\"image/parametre.png\" alt=\"[roue]\" /></section>");

				//sinon on affiche un message d'erreur et un bouton redirige vers la page de login
				}else{
					out.println("</head><body class=\"result\">");
					out.println("<header>");
					out.println("<nav><ul><li></li></ul></nav>");
					out.println("</header><main> ");
					out.println("<h1 class=\"erreur\">Erreur d'authentification</h1>");
					out.println("<p>Identifiant et/ou mot de passe incorrect</p>");	     
					out.println("<form name=\"loginOwner\" method=\"post\" action=\"login.html\">");
					out.println("<button>CONNEXION</button>");
					out.println("</form>");

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
