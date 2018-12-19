import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.WebServlet;
import java.sql.*;
/*
Servlet qui receptionne le formulaire de rendez-vous coté Client
*/
@WebServlet("/servlet/ResultatFormulaire")
public class ResultFormUser extends HttpServlet{

  public void service( HttpServletRequest req, HttpServletResponse res ) 
  throws ServletException, IOException{

   
    //On récupere les informations du formulaire
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

    //On remplace les caracteres dangereux recus dans le formulaire
    nom = nom.replace('\'',' ');
    nom = nom.replace('"',' ');
    nom = nom.replace('<',' ');
    nom = nom.replace('>',' ');
    prenom = prenom.replace('\'',' ');
    prenom = prenom.replace('"',' ');
    prenom = prenom.replace('<',' ');
    prenom = prenom.replace('>',' ');
    email = email.replace('\'',' ');
    email = email.replace('"',' ');
    email = email.replace('<',' ');
    email = email.replace('>',' ');
    telephone = telephone.replace('\'',' ');
    telephone = telephone.replace('"',' ');
    telephone = telephone.replace('<',' ');
    telephone = telephone.replace('>',' ');
    preferenceRappel = preferenceRappel.replace('\'',' ');
    preferenceRappel = preferenceRappel.replace('"',' ');
    preferenceRappel = preferenceRappel.replace('<',' ');
    preferenceRappel = preferenceRappel.replace('>',' ');
    preferenceLundi = preferenceLundi.replace('\'',' ');
    preferenceLundi = preferenceLundi.replace('"',' ');
    preferenceLundi = preferenceLundi.replace('<',' ');
    preferenceLundi = preferenceLundi.replace('>',' ');
    preferenceMardi = preferenceMardi.replace('\'',' ');
    preferenceMardi = preferenceMardi.replace('"',' ');
    preferenceMardi = preferenceMardi.replace('<',' ');
    preferenceMardi = preferenceMardi.replace('>',' ');
    preferenceMercredi = preferenceMercredi.replace('\'',' ');
    preferenceMercredi = preferenceMercredi.replace('"',' ');
    preferenceMercredi = preferenceMercredi.replace('<',' ');
    preferenceMercredi = preferenceMercredi.replace('>',' ');
    preferenceJeudi = preferenceJeudi.replace('\'',' ');
    preferenceJeudi = preferenceJeudi.replace('"',' ');
    preferenceJeudi = preferenceJeudi.replace('<',' ');
    preferenceJeudi = preferenceJeudi.replace('>',' ');
    preferenceVendredi = preferenceVendredi.replace('\'',' ');
    preferenceVendredi = preferenceVendredi.replace('"',' ');
    preferenceVendredi = preferenceVendredi.replace('<',' ');
    preferenceVendredi = preferenceVendredi.replace('>',' ');
    preferenceSamedi = preferenceSamedi.replace('\'',' ');
    preferenceSamedi = preferenceSamedi.replace('"',' ');
    preferenceSamedi = preferenceSamedi.replace('<',' ');
    preferenceSamedi = preferenceSamedi.replace('>',' ');
    preferenceMatin = preferenceMatin.replace('\'',' ');
    preferenceMatin = preferenceMatin.replace('"',' ');
    preferenceMatin = preferenceMatin.replace('<',' ');
    preferenceMatin = preferenceMatin.replace('>',' ');
    preferenceAprem = preferenceAprem.replace('\'',' ');
    preferenceAprem = preferenceAprem.replace('"',' ');
    preferenceAprem = preferenceAprem.replace('<',' ');
    preferenceAprem = preferenceAprem.replace('>',' ');


    boolean correct = true; 
    int idjava = 0;

    PrintWriter out = res.getWriter();

    //On genere un page HTML avec les résultats du formulaire

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
    
    //On affiche sur la page uniquement les données renseignées par le client dans le formulaire

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
    

    //Si aucun moyen de rappel n'est renseigné on affiche un message d'erreur 
    //et on passe le booléen correct a false
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
      //si tout est correct on essaye de se connecter
      if (correct = true){
        // On déclare le type de driver JDBC et le chemin d’accès à la base
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
         //On initialise le statement pour executer les requetes dans la base
        stat = conn.createStatement();
         //On crée la table si elle n'existe pas
        stat.executeUpdate("CREATE TABLE IF NOT EXISTS CLIENTS(ID INTEGER PRIMARY KEY AUTOINCREMENT,NOM VARCHAR(50),PRENOM VARCHAR(50),EMAIL VARCHAR(20),TEL VARCHAR(20),RAPPEL_PAR VARCHAR(20));");
      }
    } 
    catch (SQLException ex) {
      ex.printStackTrace();
      out.println("Erreur de cr&eacute;ation dans la base de donnée");
    }
    try {
      //On insere les données dans la table
      stat.executeUpdate("INSERT INTO CLIENTS(NOM,PRENOM,EMAIL,TEL,RAPPEL_PAR) VALUES('" + nom + "','" + prenom +"','" + email +"','" + telephone + "','" + preferenceRappel + "');");
    } 
    catch (SQLException ex) {
      ex.printStackTrace();
      out.println("Erreur d'insertion dans la base de donn&eacute;e");
    }
    try {
           // On fait une nouvelle requete

      rs = stat.executeQuery( "SELECT ID FROM CLIENTS;" );

    }  
    catch (SQLException ex) {
      ex.printStackTrace();
      out.println("Erreur de consultation dans la base de donn&eacute;es");
    }
    try {
          //On récupere l'id client de la base        
      while (rs.next()) {
        idjava = rs.getInt("ID"); 
      }

    } 
    catch (SQLException ex) {
      ex.printStackTrace();
      out.println("Erreur de r&eacute;cup&eacute;ration dans la base de donn&eacute;es");
    } 
    try {
      //On crée la table si elle n'existe pas
      stat.executeUpdate("CREATE TABLE IF NOT EXISTS RDV(IDCLIENT INT NOT NULL,LUNDI VCHAR(20),MARDI VCHAR(20),MERCREDI VCHAR(20),JEUDI VCHAR(20),VENDREDI VCHAR(20),SAMEDI VCHAR(20),MATIN VCHAR(20),APREM VCHAR(20));");

    } 
    catch (SQLException ex) {
      ex.printStackTrace();
      out.println("Erreur de cr&eacute;ation dans la base de donn&eacute;es");
    }
    try {
      //On insere les valeurs dans la table 
      stat.executeUpdate("INSERT INTO RDV VALUES(" + idjava + ",'" + preferenceLundi + "','" + preferenceMardi + "','" + preferenceMercredi + "','" + preferenceJeudi + "','" + preferenceVendredi + "','" + preferenceSamedi + "','" + preferenceMatin + "','" + preferenceAprem + "');");

    } 
   catch (SQLException ex) {
    ex.printStackTrace();
    out.println("Erreur d'insertion dans la base de donn&eacute;es");
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
