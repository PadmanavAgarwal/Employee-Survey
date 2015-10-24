package Login;

import java.sql.*; 
import java.sql.Connection;
import java.util.*; 
import com.mysql.jdbc.*;

public class ConnectionManager 
{ static Connection con;
  static String url; 
  public static Connection getConnection() 
  { try 
    { String url = "jdbc:mysql://localhost/ultimate1" ; 
      Class.forName("com.mysql.jdbc.Driver");
      try
      { con = DriverManager.getConnection(url,"root","");  
       // and password is "password"
      } catch (SQLException ex) { ex.printStackTrace(); }
    } catch(ClassNotFoundException e) { System.out.println(e); }
  return con; 
   
      
 
  }
}