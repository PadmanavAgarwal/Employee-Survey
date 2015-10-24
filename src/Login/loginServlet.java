package Login;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class loginServlet
 */
@WebServlet("/loginServlet")
public class loginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public loginServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		{ try 
		{ UserBean user = new UserBean(); 
		  ArrayList<ArrayList<String>> questions = new ArrayList<ArrayList<String>>();  
		//ArrayList<CartBean> cart=new ArrayList<CartBean>();
		  user.setUserName(request.getParameter("un")); 
		  user.setPassword(request.getParameter("pw")); 
		  user = UserDAO.login(user); 
		  if (user.isValid()) 
		  {
		   //cart = CartDAO.items();
		   questions = questionsDAO.items();
			System.out.println(user.getUsername());
			HttpSession session = request.getSession(true); 
		   if(user.getPosition()>0)
		   {
		   HashMap<String,int[]> ratingMap=new ratingDAO().update(user.getUsername());
		   session.setAttribute("ratingMap", ratingMap);
		   }
		   session.setAttribute("currentSessionUser",user);
		   session.setAttribute("questions",questions);
		   response.sendRedirect("index.jsp"); 
		  //logged-in page
		  } else 
			 {
			   String h="Invalid Credentials";
			   request.setAttribute("message",h);
			   RequestDispatcher view=getServletContext().getRequestDispatcher("/login.jsp");
			   view.forward(request, response);
			 //  response.sendRedirect("login.jsp"); 
			 }
		  //error page 
		  } catch (Throwable theException)
		  {theException.printStackTrace(); 
		  }
		
	}
	}

}
