import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.WebServlet;
import java.sql.*;

@WebServlet("/servlet/ResultatFormulaire")
public class ResultFormUser extends HttpServlet
{
  public void service( HttpServletRequest req, HttpServletResponse res ) 
  throws ServletException, IOException
  {
    PrintWriter out = res.getWriter();

    String nom = req.getParameter("nom");
    String prenom = req.getParameter("prenom");
    String email = req.getParameter("email");
    String telephone = req.getParameter("telephone");

    String preferenceRappel = req.getParameter("preferenceRappel");
    
    String preferenceLundi = req.getParameter("preferenceLundi");
    String preferenceMardi = req.getParameter("preferenceMardi");
    String preferenceMercredi = req.getParameter("preferenceMercredi");
    String preferenceJeudi = req.getParameter("preferenceJeudi");
    String preferenceVendredi = req.getParameter("preferenceVendredi");
    String preferenceSamedi = req.getParameter("preferenceSamedi");

    String preferenceMatin = req.getParameter("preferenceMatin");
    String preferenceAprem = req.getParameter("preferenceAprem");

    out.println("<!doctype html>");
    out.println("<html lang=\"fr\">");
    out.println("");
    out.println("<head>");
    out.println("<meta charset=\"UTF-8\">");
    out.println("<link rel=\"stylesheet\" href=\"/projetWeb/style/resultform.css\"/>");
    out.println("<title>R&eacute;sultat du Formulaire</title>");
    out.println("</head>");
    out.println("<body>");
    out.println("<header>");
    out.println("<nav>");
    out.println("<ul>");
    out.println("<li><a href=\"http://localhost:8080/projetWeb/userindex.html\">Accueil</a></li>");
    out.println("<li><a href=\"http://localhost:8080/projetWeb/servlet/Make\">Rendez-vous</a></li>");
    out.println("<li><a href=\"http://localhost:8080/projetWeb/usercontact.html\">Contact</a></li>");
    out.println("</ul>");
    out.println("</nav>");
    out.println("</header>");
    out.println("<main>");
    out.println("<div>");
    out.println("<h1>Votre demande est prise en compte</h1> ");
    out.println("<p>Merci de votre confiance</p>");
    out.println("</div>");
    out.println("<section class=\"info\">");
    out.println("<h2>Nous avons enregistrer ces informations vous concernant</h2>");
    out.println("<ul>");
    out.println("<li>Nom : " + nom + "</li>");
    out.println("<li>Pr&eacute;nom : "+ prenom + "</li>");
    if(email != null){
      out.println("<li>Email : " + email + "</li>");
    }
    if(telephone != null){
      out.println("<li>Tel : " + telephone + "</li>");
    }
    if(preferenceRappel != null){
      out.println("<li>Vous pr&eacute;f&eacute;rez &ecirc;tre rappel&eacute; par " + preferenceRappel + " afin de convenir d'un rendez-vous");
    }
     if(preferenceMatin != null){
      out.println("en matin&eacute;e");
    }
    if(preferenceAprem != null){
      out.println("<li>dans l'" + preferenceAprem);
    }
    out.println(" parmis les jours suivants :</li>");
    out.println("</ul>");
    out.println("<div class=\"jour\">");

    if(preferenceLundi != null){
      out.println("<p>" + preferenceLundi + "</p>");
    }
    if(preferenceMardi != null){
      out.println("<p>"+ preferenceMardi + "</p>");
    }
    if(preferenceMercredi != null){
      out.println("<p>" + preferenceMercredi + "</p>");
    }
    if(preferenceJeudi != null){
      out.println("<p>" + preferenceJeudi + "</p>");
    }
    if(preferenceVendredi != null){
      out.println("<p>" + preferenceVendredi + "</p>");
    }
    if(preferenceSamedi != null){
      out.println("<p>" + preferenceSamedi + "</p>");
    }
    out.println("</div>");
    out.println("</section>");
    out.println("<section class=\"contact\">");
    out.println("<h2>Nous vous recontactons au plus vite</h2>");
    out.println("</section>");
    try {
// On déclare le type de driver JDBC et le chemin d’accès à la base, si pb exception ClassNotFound
      Class.forName("org.sqlite.JDBC");
      String dbURL = "jdbc:sqlite:../webapps/projetWeb/BDD/data.db";
//On essaye de se connecter à la base
      Connection conn = DriverManager.getConnection(dbURL);
      if (conn != null) {
        out.println("Connected to the database");
// un Statement est une interface qui représente une instruction SQL
        Statement stat = conn.createStatement();
// On exécute les requêtes, attention à la différence entre executeUpdate et executeQuery

        stat.executeUpdate("insert into client values(" + nom + "," + prenom +"," + email +"," + telephone + "," + preferenceRappel + " );");
        ResultSet rs = stat.executeQuery( "SELECT num FROM client where nom=" + nom +" and prenom="+ prenom +";" );
        String idClient = rs.getString("num");
        stat.executeUpdate("insert into rendezvous values(" + idClient + " );");

// le resultat du select est mis dans un ResultSet
        rs = stat.executeQuery( "SELECT * FROM client;" );
//On récupere chaque information de la base        
        while ( rs.next() ) {
          int idjava = rs.getInt("num");
          String nomjava = rs.getString("nom");
          String prenomjava = rs.getString("prenom");
          String emailjava = rs.getString("email");
          String telephonejava = rs.getString("telephone");
          String preferenceRappeljava = rs.getString("preferenceRappel");
          out.println( "ID = " + idjava+" Prenom = "+prenomjava+" Nom = "+nomjava + " email = "+ emailjava + " tel ="+ telephonejava + "preference de Rappel = " + preferenceRappeljava );
        }
        
// On ferme les connexions au ResultSet, Statement et à la base
        rs.close();
        stat.close();
        conn.close();
      }

    } catch (ClassNotFoundException ex) {
      ex.printStackTrace();

    } catch (SQLException ex) {
      ex.printStackTrace();}

      out.println("Ces informations sont ajout&eacute;es dans la base de donn&eacute;e ...");
      out.println("</body>");
      out.println("</html>");

    }
  }
