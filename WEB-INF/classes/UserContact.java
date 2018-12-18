import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.WebServlet;
import java.sql.*;
/*
Servlet créant la page de contact coté Client
*/
@WebServlet("/Contact")
public class UserContact extends HttpServlet {

    public void service(HttpServletRequest req, HttpServletResponse res)
            throws ServletException, IOException {
        ResultSet rs = null;
        Connection conn = null;
        Statement stat = null;
        String adresseEntreprise = "";
        String emailEntreprise = "";
        String telephoneEntreprise = "";
        String horaireLundi = "";
        String horaireMardi = "";
        String horaireMercredi = "";
        String horaireJeudi = "";
        String horaireVendredi = "";
        String horaireSamedi = "";
        String horaireDimanche = "";
        PrintWriter out = res.getWriter();

        try {
            // On déclare le type de driver JDBC et le chemin d’accès à la base
            Class.forName("org.sqlite.JDBC");
            String dbURL = "jdbc:sqlite:../webapps/projetWeb/BDD/data.db";

            //On essaye de se connecter à la base
            conn = DriverManager.getConnection(dbURL);

        } catch (Exception ex) {

            ex.printStackTrace();
            out.println("Erreur de connexion dans base de donn&eacute;es");
        }
        try {
             //On initialise le statement pour executer les requetes dans la base
            stat = conn.createStatement();
             //On selectionne les données dans la table
            rs = stat.executeQuery("SELECT ADRESSE,EMAIL,TEL,LUNDI,MARDI,MERCREDI,JEUDI,VENDREDI,SAMEDI,DIMANCHE FROM MAGASIN;");
        } catch (SQLException ex) {
            ex.printStackTrace();
            out.println("Erreur de consultation dans la base de donn&eacute;es");
        }
        try {
            //On récupere les données
            while (rs.next()) {
                adresseEntreprise = rs.getString("ADRESSE");
                emailEntreprise = rs.getString("EMAIL");
                telephoneEntreprise = rs.getString("TEL");
                horaireLundi = rs.getString("LUNDI");
                horaireMardi = rs.getString("MARDI");
                horaireMercredi = rs.getString("MERCREDI");
                horaireJeudi = rs.getString("JEUDI");
                horaireVendredi = rs.getString("VENDREDI");
                horaireSamedi = rs.getString("SAMEDI");
                horaireDimanche = rs.getString("DIMANCHE");
            }

        } catch (SQLException ex) {
            ex.printStackTrace();
            out.println("Erreur de r&eacute;cup&eacute;ration dans la base de donn&eacute;e");
        }
         //On genere une page html en intégrant les variables récupérées dans la base de données
        out.println("<!doctype html>");
        out.println("<html lang=\"fr\">");
        out.println("");
        out.println("<head>");
        out.println("<meta charset=\"UTF-8\">");
        out.println("<link rel=\"stylesheet\" href=\"/projetWeb/style/usercontact.css\"/>");
        out.println("<title>Contact</title>");
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
        out.println("<h1>Nous contacter</h1>");
        out.println("<div class=\"content\">");
        out.println("<div class=\"info\">");
        out.println("<section class=\"adresse\">");
        out.println("<img src=\"./image/Maison.jpg\" alt=\" logo maison\">");
        out.println("<p>" + adresseEntreprise + "</p>");
        out.println("</section>");
        out.println("<section class=\"telephone\">");
        out.println("<img src=\"./image/telephone.png\" alt=\"logo telephone\">");
        out.println("<p>" + telephoneEntreprise + "</p>");
        out.println("</section>");
        out.println("<section class=\"mail\">");
        out.println("<img src=\"./image/email.jpg\" alt=\"logo email\">");
        out.println("<p>" + emailEntreprise + "</p>");
        out.println("</section>");
        out.println("<section class=\"horaire\">");
        out.println("<span>Horaires d'ouverture</span>");
        out.println("<ul>");
        out.println("<li>Lundi : " + horaireLundi + "</li>");
        out.println("<li>Mardi : " + horaireMardi + "</li>");
        out.println("<li>Mercredi : " + horaireMercredi + " </li>");
        out.println("<li>Jeudi : " + horaireJeudi + "</li>");
        out.println("<li>Vendredi : " + horaireVendredi + "</li>");
        out.println("<li>Samedi : " + horaireSamedi + "</li>");
        out.println("<li>Dimanche : " + horaireDimanche + "</li>");
        out.println("</ul>");
        out.println("</section>");
        out.println("</div>");
        out.println("<div class=\"map\">");
        out.println("<iframe src=\"https://www.google.com/maps/embed?pb=!1m18!1m12!1m3!1d11379802.737617277!2d-6.932434662998441!3d45.86586441149135!2m3!1f0!2f0!3f0!3m2!1i1024!2i768!4f13.1!3m3!1m2!1s0xd54a02933785731%3A0x6bfd3f96c747d9f7!2sFrance!5e0!3m2!1sfr!2sfr!4v1544641799293\" width=\"600\" height=\"450\" frameborder=\"0\" style=\"border:0\" allowfullscreen></iframe>");
        out.println("<section class=\"network\">");
        out.println("<span>Et aussi :</span>");
        out.println("<a href=\"https://fr-fr.facebook.com\"><img class=\"Facebook\" src=\"./image/facebook.png\" alt=\"Logo facebook\" title=\"Facebook\" /></a>");
        out.println("<a href=\"https://www.instagram.com\"><img class=\"Instagram\" src=\"./image/insta.jpg\" alt=\"Logo instagram\" title=\"Instagram\" /></a>");
        out.println("<a href=\"https://twitter.com\"><img class=\"Twitter\" src=\"./image/twitter.png\" alt=\"Logo twitter\" title=\"Facebook\" /></a>");
        out.println("</section>");
        out.println("</div>");
        out.println("</div>");
        out.println("</main>");
        out.println("</body>");
        out.println("</html>");
    }
}
