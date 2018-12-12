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
    out.println("<li><a href=\"http://localhost:8080/projetWeb/Index\">Accueil</a></li>");
    out.println("<li><a href=\"http://localhost:8080/projetWeb/RDV\">Rendez-vous</a></li>");
    out.println("<li><a href=\"http://localhost:8080/projetWeb/Contact\">Contact</a></li>");
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
      out.println("dans l'aprés-midi");
    }
    
    if(preferenceLundi != null || preferenceMardi != null || preferenceMercredi != null || preferenceJeudi != null || preferenceVendredi != null || preferenceSamedi != null){

      out.println(" parmis les jours suivants </li>");
      out.println("</ul>");
      out.println("<div class=\"jour\">");

      if(preferenceLundi != null){
        out.println("<p>" + "Lundi" + "</p>");
      }

      if(preferenceMardi != null){
        out.println("<p>"+ "Mardi" + "</p>");
      }

      if(preferenceMercredi != null){
        out.println("<p>" + "Mercredi" + "</p>");
      }

      if(preferenceJeudi != null){
        out.println("<p>" + "Jeudi" + "</p>");
      }

      if(preferenceVendredi != null){
        out.println("<p>" + "Vendredi" + "</p>");
      }

      if(preferenceSamedi != null){
        out.println("<p>" + "Samedi" + "</p>");
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

    ResultSet rs = null;
    Connection conn = null;
    Statement stat = null;
    try {
      if (correct = true){
        // On déclare le type de driver JDBC et le chemin d’accès à la base, si pb exception ClassNotFound
        Class.forName("org.sqlite.JDBC");
        String dbURL = "jdbc:sqlite:../webapps/projetWeb/BDD/data.db";
        //On essaye de se connecter à la base
        conn = DriverManager.getConnection(dbURL);

      } 
    }
    catch (ClassNotFoundException ex) {
      ex.printStackTrace();
      out.println("Erreur de connexion dans base de donn&eacute;es");
    } 
    catch (SQLException ex) {
      ex.printStackTrace();
      out.println("Erreur de connexion dans base de donn&eacute;es");
    }
    try {
      if (conn != null) {
          // un Statement est une interface qui représente une instruction SQL
        stat = conn.createStatement();

        stat.executeUpdate("CREATE TABLE IF NOT EXISTS CLIENTS(ID INTEGER PRIMARY KEY AUTOINCREMENT,NOM VARCHAR(50),PRENOM VARCHAR(50),EMAIL VARCHAR(20),TEL VARCHAR(20),RAPPEL_PAR VARCHAR(20));");
      }
    } 
    catch (SQLException ex) {
      ex.printStackTrace();
      out.println("Erreur de cr&eacute;ation dans la base de donnée");
    }
    try {
      stat.executeUpdate("INSERT INTO CLIENTS(NOM,PRENOM,EMAIL,TEL,RAPPEL_PAR) VALUES('" + nom + "','" + prenom +"','" + email +"','" + telephone + "','" + preferenceRappel + "');");
    } 
    catch (SQLException ex) {
      ex.printStackTrace();
      out.println("Erreur d'insertion dans la base de donn&eacute;e");
    }
    try {
           // On fait une nouvelle requete

      rs = stat.executeQuery( "SELECT * FROM CLIENTS;" );

    }  
    catch (SQLException ex) {
      ex.printStackTrace();
      out.println("Erreur de consultation dans la base de donn&eacute;es");
    }
    try {
          //On récupere chaque information de la base        
      while (rs.next()) {

        idjava = rs.getInt("ID");
        nomjava = rs.getString("NOM");
        prenomjava = rs.getString("PRENOM");
        emailjava = rs.getString("EMAIL");
        telephonejava = rs.getString("TEL");
        preferenceRappeljava = rs.getString("RAPPEL_PAR");
        out.println( "ID = " + idjava+" Prenom = "+prenomjava+" Nom = "+nomjava + " email = "+ emailjava + " tel ="+ telephonejava + " preference de Rappel = " + preferenceRappeljava );
      }

    } 
    catch (SQLException ex) {
      ex.printStackTrace();
      out.println("Erreur de r&eacute;cup&eacute;ration dans la base de donn&eacute;es");
    } 
    try {
      stat.executeUpdate("CREATE TABLE IF NOT EXISTS RDV(IDCLIENT INT NOT NULL,LUNDI VCHAR(20),MARDI VCHAR(20),MERCREDI VCHAR(20),JEUDI VCHAR(20),VENDREDI VCHAR(20),SAMEDI VCHAR(20),MATIN VCHAR(20),APREM VCHAR(20));");

    } 
    catch (SQLException ex) {
      ex.printStackTrace();
      out.println("Erreur de cr&eacute;ation dans la base de donn&eacute;es");
    }
    try {
      stat.executeUpdate("INSERT INTO RDV VALUES(" + idjava + ",'" + preferenceLundi + "','" + preferenceMardi + "','" + preferenceMercredi + "','" + preferenceJeudi + "','" + preferenceVendredi + "','" + preferenceSamedi + "','" + preferenceMatin + "','" + preferenceAprem + "');");

    } 
   catch (SQLException ex) {
    ex.printStackTrace();
    out.println("Erreur d'insertion dans la base de donn&eacute;es");
  }
  try {
          //On récupere les infos de la table RDV pour vérifier
    rs = stat.executeQuery( "SELECT * FROM RDV;");
    
  }
  catch (SQLException ex) {
   ex.printStackTrace();
   out.println("Erreur de consultation dans la base de donn&eacute;es");
 }
 try {
  while (rs.next()) {
    idrdv = rs.getInt("IDCLIENT");
    prefLundi=rs.getString("LUNDI");
    prefMardi=rs.getString("MARDI");
    prefMercredi=rs.getString("MERCREDI");
    prefJeudi=rs.getString("JEUDI");
    prefVendredi=rs.getString("VENDREDI");
    prefSamedi=rs.getString("SAMEDI");
    prefMatin=rs.getString("MATIN");
    prefAprem=rs.getString("APREM");
  }
}
catch (SQLException ex) {
 ex.printStackTrace();
 out.println("Erreur de r&eacute;cup&eacute;ration dans la base de donn&eacute;es");
}
try{
          // On ferme les connexions au ResultSet, Statement et à la base
  rs.close();
  stat.close();
  conn.close();
} 
catch (SQLException ex) {
 ex.printStackTrace();
 out.println("Erreur de fermeture de la base de donn&eacute;es");
}
finally{
  out.println("</body>");
  out.println("</html>");
}
}
}
