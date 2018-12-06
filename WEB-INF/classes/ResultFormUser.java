// Servlet Test.java  de test de la configuration
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
    out.println("<head>");
    out.println("<title>Resultat du Formulaire</title>");
    out.println("</head>");
    out.println("<body>");
    out.println("<h1>Servlet ResultatFormulaire</h1> ");
    out.println("<br>");
    out.println("Bonjour ");
    out.println("<br>");
    out.println("Nom = " + nom);
    out.println("<br>");
    out.println("Prenom = "+ prenom);
    out.println("<br>");
    out.println("email = " + email);
    out.println("<br>");
    out.println("telephone = " + telephone);
    out.println("<br>");
    out.println("preferenceRappel = " + preferenceRappel);
    out.println("<br>");
    out.println("preferenceLundi = " + preferenceLundi);
    out.println("<br>");
    out.println("preferenceMardi = "+ preferenceMardi);
    out.println("<br>");
    out.println("preferenceMercredi = " + preferenceMercredi);
    out.println("<br>");
    out.println("preferenceJeudi = " + preferenceJeudi);
    out.println("<br>");
    out.println("preferenceVendredi = " + preferenceVendredi);
    out.println("<br>");
    out.println("preferenceSamedi = " + preferenceSamedi);
    out.println("<br>");
    out.println("preferenceMatin = " + preferenceMatin);
    out.println("<br>");
    out.println("preferenceAprem = " + preferenceAprem);
    out.println("<br>");
    try {
// On déclare le type de driver JDBC et le chemin d’accès à la base, si pb exception ClassNotFound
      Class.forName("org.sqlite.JDBC");
      String dbURL = "jdbc:sqlite:./data.db";
//On essaye de se connecter à la base
      Connection conn = DriverManager.getConnection(dbURL);
      if (conn != null) {
        System.out.println("Connected to the database");
// un Statement est une interface qui représente une instruction SQL
        Statement stat = conn.createStatement();
// On exécute les requêtes, attention à la différence entre executeUpdate et executeQuery

        stat.executeUpdate("insert into client values(" + nom + "," + prenom +"," + email +"," + telephone + "," + preferenceRappel + " );");
        ResultSet rs = stat.executeQuery( "SELECT num FROM client where nom=" + nom +" and prenom="+ prenom +";" );
        String idClient = rs.getString("num");
        stat.executeUpdate("insert into rendezvous values(" + idClient + " );");

// le resultat du select est mis dans un ResultSet
        rs = stat.executeQuery( "SELECT * FROM client;" );
        
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

      out.println("Ces informations sont ajoutées dans la base de donnée ...");
      out.println("</body>");
      out.println("</html>");




    
    
  }
}
