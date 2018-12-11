import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.WebServlet;
import java.sql.*;


@WebServlet("/configOwner")
public class ConfigOwner extends HttpServlet
{
	public void service( HttpServletRequest req, HttpServletResponse res ) 
	throws ServletException, IOException
	{

		String nomEntreprise = req.getParameter("nomE");

		String adresseEntreprise = req.getParameter("adresseE");

		String emailEntreprise = req.getParameter("emailE");

		String telephoneEntreprise = req.getParameter("telephoneE");

		String textBienvenue = req.getParameter("textBienvenueE");

        String ulrImage = req.getParameter("urlImage");

		String horaireLundi = req.getParameter("horaireLundi");

		String horaireMardi = req.getParameter("horaireMardi");

		String horaireMercredi = req.getParameter("horaireMercredi");

		String horaireJeudi = req.getParameter("horaireJeudi");

		String horaireVendredi = req.getParameter("horaireVendredi");

		String horaireSamedi = req.getParameter("horaireSamedi");

		String horaireDimanche = req.getParameter("horaireDimanche");

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
		out.println("<li><a href=\"http://localhost:8080/projetWeb/login.html\">Log out</a></li>");
		out.println("</ul>");
		out.println("</nav>");
		out.println("</header>");
		out.println("<main>");

		if(nomEntreprise.equals("") && adresseEntreprise.equals("") && emailEntreprise.equals("") && telephoneEntreprise.equals("") && textBienvenue.equals("") && horaireLundi.equals("") && horaireMardi.equals("") && horaireMercredi.equals("") && horaireJeudi.equals("") && horaireVendredi.equals("") && horaireSamedi.equals("") && horaireDimanche.equals("")){
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
			if(conn != null){

			    out.println("<div>");
                out.println("<h1>Les changements sont pris en compte</h1> ");
                out.println("</div>");
                out.println("<section class=\"info\">");
                out.println("<h2>Pr&eacute;sentation</h2>");
                out.println("<ul>");

				try{

                    stat = conn.createStatement();

                    rs = stat.executeQuery("SELECT * FROM MAGASIN;");

                }
                catch (SQLException ex) {
                    ex.printStackTrace();
                    out.println("Erreur de consultation dans la base de donn&eacute;es");
                }
                try {

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
                    out.println("Erreur de r&eacute;cup&eacute;ration dans la base de donn&eacute;e");
                }try {
                    if(!nomEntreprise.equals("")) {
                        stat.executeUpdate("UPDATE MAGASIN SET NOM = REPLACE(NOM,'" + nom + "','" + nomEntreprise + "');");
                        out.println("<li>Nom de l'entreprise : " + nomEntreprise + "</li>");
                    }

                    if(!adresseEntreprise.equals("")) {
                        stat.executeUpdate("UPDATE MAGASIN SET ADRESSE = REPLACE(ADRESSE,'" + adresse + "','" + adresseEntreprise + "');");
                        out.println("<li>Adresse de l'entreprise : " + adresseEntreprise + "</li>");
                    }

                    if(!emailEntreprise.equals("")){
                        stat.executeUpdate("UPDATE MAGASIN SET EMAIL = REPLACE(EMAIL,'"+ mail +"','"+emailEntreprise+"');");
                        out.println("<li>Email de l'entreprise : "+ emailEntreprise + "</li>");
                    }

                    if(!telephoneEntreprise.equals("")){
                        stat.executeUpdate("UPDATE MAGASIN SET TEL = REPLACE(TEL,'"+ tel +"','"+telephoneEntreprise+"');");
                        out.println("<li>T&eacute;l&eacute;phone de l'entreprise : "+ telephoneEntreprise + "</li>");
                    }

                    if(!textBienvenue.equals("")){
                        stat.executeUpdate("UPDATE MAGASIN SET TEXTEBIENVENUE = REPLACE(TEXTEBIENVENUE,'"+text+"','"+textBienvenue+"');");
                        out.println("<li>Votre texte de bienvenue sur le site de l'entreprise : "+ textBienvenue + "</li>");
                    }

                    if(!ulrImage.equals("")){
                        stat.executeUpdate("UPDATE MAGASIN SET URLIMAGE = REPLACE(URLIMAGE,'"+url+"','"+ulrImage+"');");
                        out.println("<li>L'url de l'image de page d'accueil : "+ ulrImage + "</li>");
                    }

                    out.println("</ul>");
                    out.println("</section>");
                    out.println("<section class=\"info\">");
                    out.println("<h2>Horaires d'ouverture</h2>");
                    out.println("<ul>");

                    if(!horaireLundi.equals("")){
                        stat.executeUpdate("UPDATE MAGASIN SET LUNDI = REPLACE(LUNDI,'"+lundi+"','"+horaireLundi+"');");
                        out.println("<li>Lundi : "+ horaireLundi + "</li>");
                    }

                    if(!horaireMardi.equals("")){
                        stat.executeUpdate("UPDATE MAGASIN SET MARDI = REPLACE(MARDI,'"+mardi+"','"+horaireMardi+"');");
                        out.println("<li>Mardi : "+ horaireMardi + "</li>");
                    }

                    if(!horaireMercredi.equals("")) {
                        stat.executeUpdate("UPDATE MAGASIN SET MERCREDI = REPLACE(MERCREDI,'" + mercredi + "','" + horaireMercredi + "');");
                        out.println("<li>Mercredi : " + horaireMercredi + "</li>");
                    }

                    if(!horaireJeudi.equals("")){
                        stat.executeUpdate("UPDATE MAGASIN SET JEUDI = REPLACE(JEUDI,'"+jeudi+"','"+horaireJeudi+"');");
                        out.println("<li>Jeudi : "+ horaireJeudi + "</li>");
                    }

                    if(!horaireVendredi.equals("")){
                        stat.executeUpdate("UPDATE MAGASIN SET VENDREDI = REPLACE(VENDREDI,'"+vendredi+"','"+horaireVendredi+"');");
                        out.println("<li>Vendredi : "+ horaireVendredi + "</li>");
                    }

                    if(!horaireSamedi.equals("")){
                        stat.executeUpdate("UPDATE MAGASIN SET SAMEDI = REPLACE(SAMEDI,'"+samedi+"','"+horaireSamedi+"');");
                        out.println("<li>Samedi : "+ horaireSamedi + "</li>");
                    }

                    if(!horaireDimanche.equals("")){
                        stat.executeUpdate("UPDATE MAGASIN SET DIMANCHE = REPLACE(DIMANCHE,'"+dimanche+"','"+horaireDimanche+"');");
                        out.println("<li>Dimanche : "+ horaireDimanche + "</li>");
                    }

                    out.println("</ul>");
                    out.println("</section>");

				// stat = conn.createStatement();
    			//stat.executeUpdate("CREATE TABLE IF NOT EXISTS MAGASIN(NOM TEXT,ADRESSE TEXT,EMAIL TEXT,TEL TEXT,TEXTEBIENVENUE TEXT,URLIMAGE TEXT, LUNDI TEXT,MARDI TEXT,MERCREDI TEXT,JEUDI TEXT,VENDREDI TEXT,SAMEDI TEXT,DIMANCHE TEXT);");
    			//stat.executeUpdate("INSERT INTO MAGASIN VALUES('" + nomEntreprise + "','" + adresseEntreprise +"','" + emailEntreprise +"','" + telephoneEntreprise + "','" + textBienvenue + "','" + urlImage + "','" +horaireLundi +  "','" + horaireMardi + "','" + horaireMercredi + "','" + horaireJeudi + "','" + horaireVendredi + "','" + horaireSamedi + "','" + horaireDimanche + "');");
                //INSERT INTO MAGASIN VALUES('mon magasin','1 rue nationale 59000 Lille', 'monmagasin@gmail.com', '02389084424', 'bienvenue dans mon magasin','./image/accueil.jpg','Fermé','9h00-12h00 14h00-18h00','9h00-12h00 14h00-18h00','9h00-12h00 14h00-18h00','9h00-12h00 14h00-18h00','9h00-12h00 14h00-18h00','Fermé');
				}catch (SQLException ex) {
					ex.printStackTrace();
					out.println("Erreur de modification de la base de donn&eacute;e");
				}
			}
			out.println("</main>");
			out.println("</body>");
			out.println("</html>");
		}
	}
}