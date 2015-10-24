package Login;

import java.text.*;
import java.util.*;
import java.sql.*; 

public class CartDAO {
	
	static Connection currentCon = null; 
	static ResultSet rs = null;
	public static ArrayList<CartBean> items() 
	{ //preparing some objects for connection
	  Statement stmt = null; 
	  ArrayList<CartBean> cart = null;
	  String itemQuery = "SELECT username,update_id,updateType,serialNumber,status,enggCode,ROCode from updatelogs"; 
	  // "System.out.println" prints in the console; Normally used to trace the process 
	 
	  System.out.println("Query: "+itemQuery); 
	  try { //connect to DB 
		  currentCon = ConnectionManager.getConnection(); 
		  stmt=currentCon.createStatement(); 
		  rs = stmt.executeQuery(itemQuery); 

          cart = new ArrayList<CartBean>();

          while (rs.next()){
        	  
        	  CartBean item = new CartBean();
        	  
        	  item.setUser(rs.getString("username"));
        	  item.setUpdateId(rs.getString("update_id"));
        	  item.setUpdateType(rs.getString("updateType"));
        	  item.setSerialNo(rs.getString("serialNumber"));
        	  item.setStatus(rs.getString("status"));
        	  item.setEnggCode(rs.getString("enggCode"));
        	  item.setROCode(rs.getString("ROCode"));
        	  
        	  cart.add(item);
          
          }
      }
	  catch(Exception ex){System.out.println(ex);}
	 
		  finally { if (rs != null) { try { rs.close(); } catch (Exception e) {} rs = null; } if (stmt != null) { try { stmt.close(); } catch (Exception e) {} stmt = null; } if (currentCon != null) { try { currentCon.close(); } catch (Exception e) { } currentCon = null; } 
		  
		  }
	   return cart; 
	   }
	} 
