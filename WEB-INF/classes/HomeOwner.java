import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.WebServlet;
import java.sql.*;

@WebServlet("/HomeOwner")
public class HomeOwner extends HttpServlet
{


	public void service( HttpServletRequest req, HttpServletResponse res ) 
	throws ServletException, IOException
	{

		HttpSession session = req.getSession();
		String identifiant = (String)session.getAttribute("identifiant");
		String password = (String)session.getAttribute("password");

		PrintWriter out = res.getWriter();  
		res.setContentType( "text/html" );
		out.println("<!doctype html>");
		out.println("<head> <meta charset=utf-8/>");
		out.println("<link rel=\"stylesheet\" type=\"text/css\" href=\"/projetWeb/style/loginOwner.css\" />");
		out.println("</head><body>");
		out.println("<header>");
		out.println("<nav>");
		out.println("<ul>");
		out.println("<li><a href=\"http://localhost:8080/projetWeb/HomeOwner\">Accueil</a></li>");
		out.println("<li><a href=\"http://localhost:8080/projetWeb/configowner.html\">Configuration</a></li>");
		out.println("<li><a href=\"http://localhost:8080/projetWeb/historiqueOwner\">Rendez-vous</a></li>");
		out.println("<li><a href=\"http://localhost:8080/projetWeb/changedPassOwner.html\">Mot de passe</a></li>");
		out.println("<li><a href=\"http://localhost:8080/projetWeb/login.html\">Log out</a></li>");
		out.println("</ul>");
		out.println("</nav>");
		out.println("</header><main>");
		out.println("<h1>Bienvenue dans votre espace "+identifiant.toUpperCase()+"</h1>");
		out.println("<section><img src=\"image/parametre.png\" alt=\"[roue]\" /></section>");

		out.println("</main>");   
		out.println("</body></html> ");
	}
}
