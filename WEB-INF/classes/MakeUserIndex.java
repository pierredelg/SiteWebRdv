import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.WebServlet;
import java.sql.*;

@WebServlet("/Index")
public class MakeUserIndex extends HttpServlet
{
    public void service( HttpServletRequest req, HttpServletResponse res )
            throws ServletException, IOException
    {
        ResultSet rs = null;
        Connection conn = null;
        Statement stat = null;
        String nomEntreprise = "";
        String texteBienvenue = "";
        String urlImage = "";
        PrintWriter out = res.getWriter();

        try {
            // On déclare le type de driver JDBC et le chemin d’accès à la base, si pb exception ClassNotFound
            Class.forName("org.sqlite.JDBC");
            String dbURL = "jdbc:sqlite:../webapps/projetWeb/BDD/data.db";
            //On essaye de se connecter à la base
            conn = DriverManager.getConnection(dbURL);

        }
        catch (Exception ex) {

            ex.printStackTrace();
            out.println("Erreur de connexion dans base de donn&eacute;es");
        }
        try{

            stat = conn.createStatement();

            rs = stat.executeQuery("SELECT NOM,TEXTEBIENVENUE,URLIMAGE FROM MAGASIN;");

        }
        catch (SQLException ex) {
            ex.printStackTrace();
            out.println("Erreur de consultation dans la base de donn&eacute;es");
        }
        try {

            while(rs.next()){
                nomEntreprise = rs.getString("NOM");
                texteBienvenue = rs.getString("TEXTEBIENVENUE");
                urlImage = rs.getString("URLIMAGE");
            }

        }catch (SQLException ex) {
            ex.printStackTrace();
            out.println("Erreur de r&eacute;cup&eacute;ration dans la base de donn&eacute;e");
        }

        out.println("<!doctype html>");
        out.println("<html lang=\"fr\">");
        out.println("");
        out.println("<head>");
        out.println("<meta charset=\"UTF-8\">");
        out.println("<link rel=\"stylesheet\" href=\"/projetWeb/style/resultform.css\"/>");
        out.println("<title>Accueil</title>");
        out.println("</head>");
        out.println("<body>");
        out.println("<header>");
        out.println("<nav>");
        out.println("<ul>");
        out.println("<li><a href=\"http://localhost:8080/projetWeb/Index\">Accueil</a></li>");
        out.println("<li><a href=\"http://localhost:8080/projetWeb/servlet/Make\">Rendez-vous</a></li>");
        out.println("<li><a href=\"http://localhost:8080/projetWeb/usercontact.html\">Contact</a></li>");
        out.println("</ul>");
        out.println("</nav>");
        out.println("</header>");
        out.println("<main>");
        out.println("<h1>Bienvenue chez "+ nomEntreprise + "</h1>");
        out.println("<img src=\""+ urlImage + "\" alt=\"Logo\">");
        out.println("<section>");
        out.println("<p>"+ texteBienvenue +"</p>");
        out.println("</section>");
        out.println("</main>");
        out.println("</body>");
        out.println("</html>");

    }
}