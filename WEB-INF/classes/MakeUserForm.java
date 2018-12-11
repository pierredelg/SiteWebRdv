import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.WebServlet;

@WebServlet("/RDV")
public class MakeUserForm extends HttpServlet
{
  public void service( HttpServletRequest req, HttpServletResponse res ) 
       throws ServletException, IOException
  	{

  		getServletContext().getRequestDispatcher("/userform.html").forward(req, res);
  		
	}
}