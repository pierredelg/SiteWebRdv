import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.WebServlet;
/*
Servlet créant la page de rendez-vous coté Client
*/
@WebServlet("/RDV")
public class MakeUserForm extends HttpServlet
{
  public void service( HttpServletRequest req, HttpServletResponse res ) 
       throws ServletException, IOException
  	{
  		//On envoie directement la page html userform.html 
  		getServletContext().getRequestDispatcher("/userform.html").forward(req, res);
  		
	}
}