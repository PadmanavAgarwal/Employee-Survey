package Login;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Iterator;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class surveySubmit
 */
@WebServlet("/surveySubmit")
public class surveySubmit extends HttpServlet {
	private static final long serialVersionUID = 1L;
      
	
	static Connection currentCon=null;
    /**
     * @see HttpServlet#HttpServlet()
     */
    public surveySubmit() {
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
		PreparedStatement ps=null;
		int error=1;
		//fetch question ids from database. "quest+id" parameter get and update corresponding question voting
		try{
			currentCon = ConnectionManager.getConnection();
			HttpSession session=request.getSession();
			UserBean user=(UserBean) session.getAttribute("currentSessionUser");
			if(!user.hasAttempted()){
			ArrayList<ArrayList<String>> questions = (ArrayList<ArrayList<String>>) session.getAttribute("questions");
			Iterator<ArrayList<String>> quesIterator = questions.iterator();
			int size=questions.size();
			while(quesIterator.hasNext())
			{   ArrayList<String> current= quesIterator.next();
				int id=Integer.valueOf(current.get(0));
				String quesid="ques"+String.valueOf(id);
				String rating=request.getParameter(quesid);
				String setId=current.get(2);
				String insertQuery="Insert into survey(question_id, user_id, rating, set_id) values(?,?,?,?)";
				ps=currentCon.prepareStatement(insertQuery);
				ps.setInt(1, id);
				ps.setString(2, user.getUsername());
				ps.setString(3, rating);
				ps.setString(4, setId);
				ps.executeUpdate();
				System.out.println(insertQuery);
				size--;
			}
						
			if(size==0){
			user.setAttempted(true);
		    session.setAttribute("currentSessionUser", user);
			
			}
			error=0;
			}
		}
		catch(Exception e){
			e.printStackTrace();
			error=1;
		}
		finally{try{
			if(ps!=null)ps.close();}catch(Exception e){if(currentCon!=null)try{currentCon.close();}catch(Exception ex){}}}
		if(error==0)
			response.sendRedirect("thanks.jsp");
		else
			response.sendRedirect("sorry.jsp");
	 
	}

}
