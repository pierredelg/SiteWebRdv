import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.WebServlet;
import java.sql.*;
/*
Servlet créant la page d'accueil coté Client
*/
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

            // On déclare le type de driver JDBC et le chemin d’accès à la base
            Class.forName("org.sqlite.JDBC");
            String dbURL = "jdbc:sqlite:../webapps/projetWeb/BDD/data.db";

            //On essaye de se connecter à la base
            conn = DriverManager.getConnection(dbURL);

        }
        catch (Exception ex) {

            ex.printStackTrace();
            out.println("Erreur de connexion dans base de donnees");
        }
        try{
            //On initialise le statement pour executer les requetes dans la base
            stat = conn.createStatement();
            //On selectionne le nom l'image et le texte de bienvenue dans la table
            rs = stat.executeQuery("SELECT NOM,TEXTEBIENVENUE,URLIMAGE FROM MAGASIN;");

        }
        catch (SQLException ex) {
            ex.printStackTrace();
            out.println("Erreur de consultation dans la base de donnees");
        }
        try {
            //On récupere les données dans les variables
            while(rs.next()){
                nomEntreprise = rs.getString("NOM");
                texteBienvenue = rs.getString("TEXTEBIENVENUE");
                urlImage = rs.getString("URLIMAGE");
            }

        }catch (SQLException ex) {
            ex.printStackTrace();
            out.println("Erreur de recuperation dans la base de donnee");
        }
        try{
        // On ferme les connexions au ResultSet, Statement et à la base
          rs.close();
          stat.close();
          conn.close();
        } 
        catch (SQLException ex) {
            ex.printStackTrace();
            out.println("Erreur de fermeture de la base de donnees");
        }

        //On genere une page html en intégrant les variables récupérées dans la base de données

       out.println("<!doctype html>");
       out.println("<html lang=\"fr\">");
       out.println("");
       out.println("<head>");
       out.println("<meta charset=\"UTF-8\">");
       out.println("<link rel=\"stylesheet\" href=\"/projetWeb/style/userindex.css\"/>");
       out.println("<title>Accueil</title>");
       out.println("</head>");
       out.println("<body>");
       out.println("<header>");
       out.println("<nav>");
       out.println("<ul>");
       out.println("<li><a href=\"http://localhost:8080/projetWeb/Index\">Accueil</a></li>");
       out.println("<li><a href=\"http://localhost:8080/projetWeb/RDV\">Rendez-vous</a></li>");
       out.println("<li><a href=\"http://localhost:8080/projetWeb/Contact\">Contact</a></li>");
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