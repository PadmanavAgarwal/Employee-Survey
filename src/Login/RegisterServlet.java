package Login;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class RegisterServlet
 */
@WebServlet("/RegisterServlet")
public class RegisterServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	static Connection currentCon = null; 
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public RegisterServlet() {
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
		
		  String user=request.getParameter("email");
		  String pwd=request.getParameter("pw");
		
		 try { //connect to DB 
			 
			  currentCon = ConnectionManager.getConnection(); 
			  String insertSQL = "insert into members(email,password) values(?,?)";
			  PreparedStatement ps = currentCon.prepareStatement(insertSQL);
			      ps.setString(1,user);
			      ps.setString(2,pwd);
			      ps.executeUpdate();  
			      
			 response.sendRedirect("login.jsp?reg=success"); 
			    
		      
		     } catch (Exception ex) 
		  { System.out.println("Log In failed: An Exception has occurred! " + ex); 
		  } //some exception handling 
		   finally
		   {  if(currentCon != null) { try { currentCon.close(); } catch (Exception e) { } currentCon = null; } 
	
		   }
	
		
		
	}

}
