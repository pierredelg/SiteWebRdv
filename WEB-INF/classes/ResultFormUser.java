import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.WebServlet;
import java.sql.*;

@WebServlet("/servlet/ResultatFormulaire")
public class ResultFormUser extends HttpServlet{

  public void service( HttpServletRequest req, HttpServletResponse res ) 
  throws ServletException, IOException{

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

    boolean correct = true; 
    int id = 1;

    int idjava = 0;
    String nomjava = null;
    String prenomjava = null;
    String emailjava = null;
    String telephonejava = null;
    String preferenceRappeljava = null;

    int idrdv = 0;
    String prefMatin = null;
    String prefAprem = null;
    String prefLundi = null;
    String prefMardi = null;
    String prefMercredi = null;
    String prefJeudi = null;
    String prefVendredi = null;
    String prefSamedi = null;

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
    out.println("<h2>Nous avons enregistr&eacute; ces informations vous concernant</h2>");
    out.println("<ul>");
    out.println("<li>Nom : " + nom + "</li>");
    out.println("<li>Pr&eacute;nom : "+ prenom + "</li>");
    
    if(!email.equals("")){
      out.println("<li>Email : " + email + "</li>");
    }
    
    if(!telephone.equals("")){
      out.println("<li>Tel : " + telephone + "</li>");
    }

    out.println("<li>Vous pr&eacute;f&eacute;rez ");

    if(preferenceRappel != null){
      out.println("&ecirc;tre contact&eacute; par " + preferenceRappel + " afin de convenir d'un rendez-vous");
    }
    else{
      out.println("prendre rendez-vous ");
    }
    
    if(preferenceMatin != null){
      out.println("en matin&eacute;e");
    }
    
    if(preferenceAprem != null){
      out.println("<li>dans l'" + preferenceAprem);
    }
    
    if(preferenceLundi != null && preferenceMardi != null && preferenceMercredi != null && preferenceJeudi != null && preferenceVendredi != null && preferenceSamedi != null){

      out.println(" parmis les jours suivants </li>");
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

    }else{

      out.println("</ul>");
    }
    

    out.println("</section>");
    out.println("<section class=\"contact\">");
    
    if(telephone.equals("") && email.equals("")){
      correct = false;
      out.println("<h2>Veuillez ressaisir le formulaire de rendez-vous</h2>");
      out.println("<h2>Vous n'avez renseign&eacute; aucun moyen de vous contacter</h2>");
    }
    else{
      out.println("<h2>Nous vous recontactons au plus vite</h2>");
    }
    out.println("</section>");
    try {
      if (correct = true){
        // On déclare le type de driver JDBC et le chemin d’accès à la base, si pb exception ClassNotFound
        Class.forName("org.sqlite.JDBC");
        String dbURL = "jdbc:sqlite:../webapps/projetWeb/BDD/data.db";
        //On essaye de se connecter à la base
        Connection conn = DriverManager.getConnection(dbURL);
        if (conn != null) {
          out.println("Connected to the database");
          // un Statement est une interface qui représente une instruction SQL
          Statement stat = conn.createStatement();

          stat.executeUpdate("CREATE TABLE IF NOT EXISTS CLIENTS(NUM INT PRIMARY KEY NOT NULL,NOM VCHAR(50) NOT NULL,PRENOM VCHAR(50) NOT NULL,EMAIL VCHAR(20),TEL VCHAR(20),PREFERENCERAPPEL VCHAR(20));");

          // On exécute les requêtes, attention à la différence entre executeUpdate et executeQuery
          ResultSet rs = stat.executeQuery( "SELECT NUM FROM CLIENTS ORDER BY DESC LIMIT 1;" );

        
          if(rs != null){
              id = rs.getInt("NUM");
              id++;
            }
         

          stat.executeUpdate("INSERT INTO CLIENTS VALUES(" + id + ",'" + nom + "','" + prenom +"','" + email +"'," + telephone + ",'" + preferenceRappel + "');");

           // On fait une nouvelle requete

          rs = stat.executeQuery( "SELECT * FROM CLIENTS;" );
          //On récupere chaque information de la base        
          while (rs.next()) {

            idjava = rs.getInt("NUM");
            nomjava = rs.getString("NOM");
            prenomjava = rs.getString("PRENOM");
            emailjava = rs.getString("EMAIL");
            telephonejava = rs.getString("TEL");
            preferenceRappeljava = rs.getString("PREFERENCERAPPEL");
            out.println( "ID = " + idjava+" Prenom = "+prenomjava+" Nom = "+nomjava + " email = "+ emailjava + " tel ="+ telephonejava + " preference de Rappel = " + preferenceRappeljava );
          }
          stat.executeUpdate("CREATE TABLE IF NOT EXISTS RDV(IDCLIENT INT NOT NULL,PREFLUNDI VCHAR(20),PREFMARDI VCHAR(20),PREFMERCREDI VCHAR(20),PREFJEUDI VCHAR(20),PREFVENDREDI VCHAR(20),PREFSAMEDI VCHAR(20),PREFMATIN VCHAR(20),PREFAPREM VCHAR(20));");
          stat.executeUpdate("INSERT INTO RDV VALUES(" + idjava + ",'" + preferenceLundi + "','" + preferenceMardi + "','" + preferenceMercredi + "','" + preferenceJeudi + "','" + preferenceVendredi + "','" + preferenceSamedi + "','" + preferenceMatin + "','" + preferenceAprem + "');");

          //On récupere les infos de la table RDV pour vérifier
          rs = stat.executeQuery( "SELECT * FROM RDV;" );

          while (rs.next()) {
            idrdv = rs.getInt("IDCLIENT");
            prefLundi=rs.getString("PREFLUNDI");
            prefMardi=rs.getString("PREFMARDI");
            prefMercredi=rs.getString("PREFMERCREDI");
            prefJeudi=rs.getString("PREFJEUDI");
            prefVendredi=rs.getString("PREFVENDREDI");
            prefSamedi=rs.getString("PREFSAMEDI");
            prefMatin=rs.getString("PREFMATIN");
            prefAprem=rs.getString("PREFAPREM");

            out.println("ID = " + idrdv + " preference = " + prefLundi + " " + prefMardi + " " + prefMercredi + " " + prefJeudi + " " + prefVendredi + " " + prefSamedi + " " + prefMatin + " " + prefAprem);
          }

          // On ferme les connexions au ResultSet, Statement et à la base
          rs.close();
          stat.close();
          conn.close();
        }
      }
    } 
    catch (ClassNotFoundException ex) {
      ex.printStackTrace();

    } 
    catch (SQLException ex) {
      ex.printStackTrace();}
      out.println( "ID = " + idjava+" Prenom = "+prenomjava+" Nom = "+nomjava + " email = "+ emailjava + " tel ="+ telephonejava + " preference de Rappel = " + preferenceRappeljava );
      out.println("ID = " + idrdv + " preference = " + prefLundi + " " + prefMardi + " " + prefMercredi + " " + prefJeudi + " " + prefVendredi + " " + prefSamedi + " " + prefMatin + " " + prefAprem);
      out.println("Ces informations sont ajout&eacute;es dans la base de donn&eacute;e ...");
      out.println("</body>");
      out.println("</html>");

    }
  }
