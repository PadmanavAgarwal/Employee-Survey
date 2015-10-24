package Login;

import java.text.*;
import java.util.*;
import java.sql.*; 

public class questionsDAO {
	
	static Connection currentCon = null; 
	static ResultSet rs = null;
	public static ArrayList<ArrayList<String>> items() 
	{ //preparing some objects for connection
	  Statement stmt = null; 
	  ArrayList<ArrayList<String>> cart = null;
	  ArrayList<String> questions = null;
	  String itemQuery = "SELECT question_id,question,a.set_id from surveyquestions a, (SELECT distinct(set_id) FROM `surveyquestions` order by rand() limit 1) b where a.set_id=b.set_id order by rand();";
	  System.out.println("Query: "+itemQuery); 
	  try { //connect to DB 
		  currentCon = ConnectionManager.getConnection(); 
		  stmt=currentCon.createStatement(); 
		  rs = stmt.executeQuery(itemQuery); 
          
          cart = new ArrayList<ArrayList<String>>();

          while (rs.next()){
        	  questions = new ArrayList<String>();
              questions.add(String.valueOf(rs.getInt("question_id")));  
              questions.add(rs.getString("question"));
              questions.add(rs.getString("set_id"));
        	  
        	  cart.add(questions);
          
          }
      }
	  catch(Exception ex){System.out.println(ex);}
	 
		  finally { if (rs != null) { try { rs.close(); } catch (Exception e) {} rs = null; } if (stmt != null) { try { stmt.close(); } catch (Exception e) {} stmt = null; } if (currentCon != null) { try { currentCon.close(); } catch (Exception e) { } currentCon = null; } 
		  
		  }
	  return cart; 
	   }
	} 
