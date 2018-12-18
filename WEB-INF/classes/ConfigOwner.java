import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.WebServlet;
import java.sql.*;

/*
Servlet qui receptionne le formulaire de configuration coté Administrateur
*/
@WebServlet("/configOwner")
public class ConfigOwner extends HttpServlet
{
    public void service( HttpServletRequest req, HttpServletResponse res ) 
    throws ServletException, IOException
    {

	//Récupération des données du formulaire 
	String nomEntreprise = req.getParameter("nomE");
	String adresseEntreprise = req.getParameter("adresseE");
	String emailEntreprise = req.getParameter("emailE");
	String telephoneEntreprise = req.getParameter("telephoneE");
	String textBienvenue = req.getParameter("textBienvenueE");
	String urlImage = req.getParameter("urlImage");
	String horaireLundi = req.getParameter("horaireLundi");
	String horaireMardi = req.getParameter("horaireMardi");
	String horaireMercredi = req.getParameter("horaireMercredi");
	String horaireJeudi = req.getParameter("horaireJeudi");
	String horaireVendredi = req.getParameter("horaireVendredi");
	String horaireSamedi = req.getParameter("horaireSamedi");
	String horaireDimanche = req.getParameter("horaireDimanche");

	//On remplace les caracteres dangereux recus dans le formulaire
	nomEntreprise = nomEntreprise.replace('\'',' ');
	nomEntreprise = nomEntreprise.replace('"',' ');
	nomEntreprise = nomEntreprise.replace('<',' ');
	nomEntreprise = nomEntreprise.replace('>',' ');
	adresseEntreprise = adresseEntreprise.replace('\'',' ');
	adresseEntreprise = adresseEntreprise.replace('"',' ');
	adresseEntreprise = adresseEntreprise.replace('<',' ');
	adresseEntreprise = adresseEntreprise.replace('>',' ');
	emailEntreprise = emailEntreprise.replace('\'',' ');
	emailEntreprise = emailEntreprise.replace('"',' ');
	emailEntreprise = emailEntreprise.replace('<',' ');
	emailEntreprise = emailEntreprise.replace('>',' ');
	telephoneEntreprise = telephoneEntreprise.replace('\'',' ');
	telephoneEntreprise = telephoneEntreprise.replace('"',' ');
	telephoneEntreprise = telephoneEntreprise.replace('<',' ');
	telephoneEntreprise = telephoneEntreprise.replace('>',' ');
	textBienvenue = textBienvenue.replace('\'',' ');
	textBienvenue = textBienvenue.replace('"',' ');
	textBienvenue = textBienvenue.replace('<',' ');
	textBienvenue = textBienvenue.replace('>',' ');
	urlImage = urlImage.replace('\'',' ');
	urlImage = urlImage.replace('"',' ');
	urlImage = urlImage.replace('<',' ');
	urlImage = urlImage.replace('>',' ');
	horaireLundi =  horaireLundi.replace('\'',' ');
	horaireLundi = horaireLundi.replace('"',' ');
	horaireLundi = horaireLundi.replace('<',' ');
	horaireLundi = horaireLundi.replace('>',' ');
	horaireMardi =  horaireMardi.replace('\'',' ');
	horaireMardi = horaireMardi.replace('"',' ');
	horaireMardi = horaireMardi.replace('<',' ');
	horaireMardi = horaireMardi.replace('>',' ');
	horaireMercredi = horaireMercredi.replace('\'',' ');
	horaireMercredi = horaireMercredi.replace('"',' ');
	horaireMercredi = horaireMercredi.replace('<',' ');
	horaireMercredi = horaireMercredi.replace('>',' ');
	horaireJeudi = horaireJeudi.replace('\'',' ');
	horaireJeudi = horaireJeudi.replace('"',' ');
	horaireJeudi =  horaireJeudi.replace('<',' ');
	horaireJeudi = horaireJeudi.replace('>',' ');
	horaireVendredi = horaireVendredi.replace('\'',' ');
        horaireVendredi = horaireVendredi.replace('"',' ');
        horaireVendredi = horaireVendredi.replace('<',' ');
	horaireVendredi =  horaireVendredi.replace('>',' ');
	horaireSamedi = horaireSamedi.replace('\'',' ');
	horaireSamedi = horaireSamedi.replace('"',' ');
	horaireSamedi = horaireSamedi.replace('<',' ');
	horaireSamedi = horaireSamedi.replace('>',' ');
	horaireDimanche = horaireDimanche.replace('\'',' ');
	horaireDimanche = horaireDimanche.replace('"',' ');
	horaireDimanche = horaireDimanche.replace('<',' ');
	horaireDimanche = horaireDimanche.replace('>',' ');


       PrintWriter out = res.getWriter();

       String nom = "";
       String adresse = "";
       String mail = "";
       String tel = "";
       String text = "";
       String url = "";
       String lundi = "";
       String mardi = "";
       String mercredi = "";
       String jeudi = "";
       String vendredi = "";
       String samedi = "";
       String dimanche = "";


       out.println("<!doctype html>");
       out.println("<html lang=\"fr\">");
       out.println("");
       out.println("<head>");
       out.println("<meta charset=\"UTF-8\">");
       out.println("<link rel=\"stylesheet\" href=\"/projetWeb/style/resultform.css\"/>");
       out.println("<title>R&eacute;sultat de la Configuration</title>");
       out.println("</head>");
       out.println("<body>");
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
       out.println("</header>");
       out.println("<main>");

        //Si aucun champ n'est renseigné on affiche un message d'erreur
       if(nomEntreprise.equals("") && adresseEntreprise.equals("") && emailEntreprise.equals("") && telephoneEntreprise.equals("") && textBienvenue.equals("") && urlImage.equals("") && horaireLundi.equals("") && horaireMardi.equals("") && horaireMercredi.equals("") && horaireJeudi.equals("") && horaireVendredi.equals("") && horaireSamedi.equals("") && horaireDimanche.equals("")){
           out.println("<div>");
           out.println("<h1> Erreur </h1> ");
           out.println("<p>Aucun champ de formulaire rempli</p>");
           out.println("</div>");
       }
       else{

           ResultSet rs = null;
           Connection conn = null;
           Statement stat = null;

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

            if(conn != null){

                out.println("<div>");
                out.println("<h1>Les changements sont pris en compte</h1> ");
                out.println("</div>");
                out.println("<section class=\"info\">");
                out.println("<h2>Pr&eacute;sentation</h2>");
                out.println("<ul>");

                try{
                    //On initialise le statement pour executer les requetes dans la base
                    stat = conn.createStatement();

                    //On crée la table si elle n'existe pas
                    stat.executeUpdate("CREATE TABLE IF NOT EXISTS MAGASIN(NOM TEXT,ADRESSE TEXT,EMAIL TEXT,TEL TEXT,TEXTEBIENVENUE TEXT,URLIMAGE TEXT, LUNDI TEXT,MARDI TEXT,MERCREDI TEXT,JEUDI TEXT,VENDREDI TEXT,SAMEDI TEXT,DIMANCHE TEXT);");

                }
                catch (SQLException ex) {
                    ex.printStackTrace();
                    out.println("Erreur de création dans la base de donnees");
                }

                try{
                    //On selectionne toutes les données de la table
                    rs = stat.executeQuery("SELECT * FROM MAGASIN;");

                }
                catch (SQLException ex) {
                    ex.printStackTrace();
                    out.println("Erreur de consultation dans la base de donnees");
                }
                try {
                    //on récupére les données de la table
                    while(rs.next()){
                        nom = rs.getString("NOM");
                        adresse = rs.getString("ADRESSE");
                        mail = rs.getString("EMAIL");
                        tel = rs.getString("TEL");
                        text = rs.getString("TEXTEBIENVENUE");
                        url = rs.getString("URLIMAGE");
                        lundi = rs.getString("LUNDI");
                        mardi = rs.getString("MARDI");
                        mercredi = rs.getString("MERCREDI");
                        jeudi = rs.getString("JEUDI");
                        vendredi = rs.getString("VENDREDI");
                        samedi = rs.getString("SAMEDI");
                        dimanche = rs.getString("DIMANCHE");
                    }

                }catch (SQLException ex) {
                    ex.printStackTrace();
                    out.println("Erreur de recuperation dans la base de donnee");

                }try {
                    /*
                    Pour chaque donnée reçue dans le formulaire on vérifie qu'elle contienne une valeur
                    Si il existait une valeur dans la table on met à jour la table avec la nouvelle valeur
                    Si la table ne contenait aucune valeur on insert la nouvelle valeur dans la table
                    Et enfin on affiche à l'utilisateur les valeurs entrées dans la table

                    */
                    if(!nomEntreprise.equals("")) {
                        if(nom.equals("")){
                            stat.executeUpdate("INSERT INTO MAGASIN(NOM) VALUES( '"+ nomEntreprise +"');");
                        }else{
                            stat.executeUpdate("UPDATE MAGASIN SET NOM = REPLACE(NOM,'" + nom + "','" + nomEntreprise + "');");
                        }
                        out.println("<li>Nom de l'entreprise : " + nomEntreprise + "</li>");
                    }

                    if(!adresseEntreprise.equals("")) {
                        if(adresse.equals("")){
                            stat.executeUpdate("INSERT INTO MAGASIN(ADRESSE) VALUES( '"+ adresseEntreprise +"');");
                        }else{
                            stat.executeUpdate("UPDATE MAGASIN SET ADRESSE = REPLACE(ADRESSE,'" + adresse + "','" + adresseEntreprise + "');");
                        }
                        out.println("<li>Adresse de l'entreprise : " + adresseEntreprise + "</li>");
                    }

                    if(!emailEntreprise.equals("")){
                        if(mail.equals("")){
                            stat.executeUpdate("INSERT INTO MAGASIN(EMAIL) VALUES( '"+ emailEntreprise +"');");
                        }else{
                         stat.executeUpdate("UPDATE MAGASIN SET EMAIL = REPLACE(EMAIL,'"+ mail +"','"+emailEntreprise+"');");
                        }
                        out.println("<li>Email de l'entreprise : "+ emailEntreprise + "</li>");
                    }

                    if(!telephoneEntreprise.equals("")){
                        if(tel.equals("")){
                            out.println("INSERT INTO MAGASIN(TEL) VALUES( '"+ telephoneEntreprise +"');");
                            stat.executeUpdate("INSERT INTO MAGASIN(TEL) VALUES( '"+ telephoneEntreprise +"');");
            			    //  out.println("INSERT INTO MAGASIN(TEL) VALUES( '"+ telephoneEntreprise +"');");
                        }else{
                            stat.executeUpdate("UPDATE MAGASIN SET TEL = REPLACE(TEL,'"+ tel +"','"+telephoneEntreprise+"');");
                        }
                        out.println("<li>T&eacute;l&eacute;phone de l'entreprise : "+ telephoneEntreprise + "</li>");
                    }

                    if(!textBienvenue.equals("")){
                        if(text.equals("")){
                           stat.executeUpdate("INSERT INTO MAGASIN(TEXTEBIENVENUE) VALUES( '"+ textBienvenue +"');");
                       }else{
                           stat.executeUpdate("UPDATE MAGASIN SET TEXTEBIENVENUE = REPLACE(TEXTEBIENVENUE,'"+text+"','"+textBienvenue+"');");
                       }
                       out.println("<li>Votre texte de bienvenue sur le site de l'entreprise : "+ textBienvenue + "</li>");
                    }

                   if(!urlImage.equals("")){
                        if(url.equals("")){
                            stat.executeUpdate("INSERT INTO MAGASIN(URLIMAGE) VALUES( '"+ urlImage +"');");
                        }else{
                            stat.executeUpdate("UPDATE MAGASIN SET URLIMAGE = REPLACE(URLIMAGE,'"+url+"','"+urlImage+"');");
                        }
                        out.println("<li>L'url de l'image de page d'accueil : "+ urlImage + "</li>");
                    }

                    out.println("</ul>");
                    out.println("</section>");
                    out.println("<section class=\"info\">");
                    out.println("<h2>Horaires d'ouverture</h2>");
                    out.println("<ul>");

                    if(!horaireLundi.equals("")){
                        if(lundi.equals("")){
                            stat.executeUpdate("INSERT INTO MAGASIN(LUNDI) VALUES( '"+ horaireLundi +"');");
                        }else{
                           stat.executeUpdate("UPDATE MAGASIN SET LUNDI = REPLACE(LUNDI,'"+lundi+"','"+horaireLundi+"');");
                        }
                        out.println("<li>Lundi : "+ horaireLundi + "</li>");
                    }

                   if(!horaireMardi.equals("")){
                        if(mardi.equals("")){
                            stat.executeUpdate("INSERT INTO MAGASIN(MARDI) VALUES( '"+ horaireMardi +"');");
                        }else{
                            stat.executeUpdate("UPDATE MAGASIN SET MARDI = REPLACE(MARDI,'"+mardi+"','"+horaireMardi+"');");
                        }
                        out.println("<li>Mardi : "+ horaireMardi + "</li>");
                    }

                    if(!horaireMercredi.equals("")) {
                        if(mercredi.equals("")){
                            stat.executeUpdate("INSERT INTO MAGASIN(MERCREDI) VALUES( '"+ horaireMercredi +"');");
                        }else{
                           stat.executeUpdate("UPDATE MAGASIN SET MERCREDI = REPLACE(MERCREDI,'" + mercredi + "','" + horaireMercredi + "');");
                        }
                        out.println("<li>Mercredi : " + horaireMercredi + "</li>");
                    }

                    if(!horaireJeudi.equals("")){
                        if(jeudi.equals("")){
                            stat.executeUpdate("INSERT INTO MAGASIN(JEUDI) VALUES( '"+ horaireJeudi +"');");
                        }else{
                           stat.executeUpdate("UPDATE MAGASIN SET JEUDI = REPLACE(JEUDI,'"+jeudi+"','"+horaireJeudi+"');");
                       }
                       out.println("<li>Jeudi : "+ horaireJeudi + "</li>");
                    }

                    if(!horaireVendredi.equals("")){
                        if(vendredi.equals("")){
                            stat.executeUpdate("INSERT INTO MAGASIN(VENDREDI) VALUES( '"+ horaireVendredi +"');");
                        }else{
                           stat.executeUpdate("UPDATE MAGASIN SET VENDREDI = REPLACE(VENDREDI,'"+vendredi+"','"+horaireVendredi+"');");
                       }
                       out.println("<li>Vendredi : "+ horaireVendredi + "</li>");
                    }

                    if(!horaireSamedi.equals("")){
                        if(samedi.equals("")){
                            stat.executeUpdate("INSERT INTO MAGASIN(SAMEDI) VALUES( '"+ horaireSamedi +"');");
                        }else{
                           stat.executeUpdate("UPDATE MAGASIN SET SAMEDI = REPLACE(SAMEDI,'"+samedi+"','"+horaireSamedi+"');");
                       }
                       out.println("<li>Samedi : "+ horaireSamedi + "</li>");
                    }

                    if(!horaireDimanche.equals("")){
                        if(dimanche.equals("")){
                            stat.executeUpdate("INSERT INTO MAGASIN(DIMANCHE) VALUES( '"+ horaireDimanche +"');");
                        }else{
                           stat.executeUpdate("UPDATE MAGASIN SET DIMANCHE = REPLACE(DIMANCHE,'"+dimanche+"','"+horaireDimanche+"');");
                       }
                       out.println("<li>Dimanche : "+ horaireDimanche + "</li>");
                    }

                    out.println("</ul>");
                    out.println("</section>");

                }catch (SQLException ex) {
                    ex.printStackTrace();
                    out.println("Erreur de modification de la base de donnee");
                }
            }
            out.println("</main>");
            out.println("</body>");
            out.println("</html>");
        }
    }
}
