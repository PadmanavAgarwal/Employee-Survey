package Login;

import java.text.*;
import java.util.*;
import java.sql.*; 
public class UserDAO
{ static Connection currentCon = null; 
  static ResultSet rs = null;
  
  public static UserBean login(UserBean bean) 
  { //preparing some objects for connection
  Statement stmt = null; 
  String username = bean.getUsername();
  String password = bean.getPassword(); 
  String searchQuery = "select * from members where username= ? AND password= ? ;"; 
  // "System.out.println" prints in the console; Normally used to trace the process 
  System.out.println("Your user name is " + username); 
  System.out.println("Your password is " + password); 
  System.out.println("Query: "+searchQuery); 
  try { //connect to DB 
   currentCon = ConnectionManager.getConnection(); 
   PreparedStatement ps=currentCon.prepareStatement(searchQuery);
   ps.setString(1,username);
   ps.setString(2,password);
   rs = ps.executeQuery(); 
   boolean more = rs.next(); // if user does not exist set the isValid variable to false 
   if (!more) 
   { System.out.println("Sorry, you are not a registered user! Please sign up first"); 
     bean.setValid(false); 
   } //if user exists set the isValid variable to true 
   else if (more) 
   { 
  /* String firstName = rs.getString("FirstName"); 
     String lastName = rs.getString("LastName"); 
     System.out.println("Welcome " + firstName); 
     bean.setFirstName(firstName);
     bean.setLastName(lastName); */
     bean.setValid(true); 
     ps=currentCon.prepareStatement("Select rating from survey where user_id = ?");
     ps.setString(1,username);
     rs=ps.executeQuery();
     boolean attempt=rs.next();
     bean.setAttempted(attempt);
     System.out.println(attempt);
     ps=currentCon.prepareStatement("Select count(emp_id) as 'numSub' from worksfor where manager_id=?");
     ps.setString(1, username);
     rs=ps.executeQuery();
     if(rs.next())
     bean.setPosition(rs.getInt("numSub"));
     else 
     bean.setPosition(0);
    }
   } catch (Exception ex) 
   { System.out.println("Log In failed: An Exception has occurred! " + ex); 
     ex.printStackTrace();
    } //some exception handling 
   finally { if (rs != null) { try { rs.close(); } catch (Exception e) {} rs = null; } if (stmt != null) { try { stmt.close(); } catch (Exception e) {} stmt = null; } if (currentCon != null) { try { currentCon.close(); } catch (Exception e) { } currentCon = null; } } return bean; } } 