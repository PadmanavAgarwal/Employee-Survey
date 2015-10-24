package Login;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.HashMap;
//import java.util.Map;
import java.util.Map.Entry;

public class worksforDAO {
	
  	
  static Connection currentCon=null;
  
  
  public HashMap<String,String> update()
  {  
	 HashMap<String, String> empMap=null;
	// ArrayList<String> subords=null;
	 PreparedStatement ps=null;
	 ResultSet rs=null;
	 try{
		 currentCon=ConnectionManager.getConnection();
		 String query="select emp_id,manager_id from worksfor";
		 ps=currentCon.prepareStatement(query);
		 rs=ps.executeQuery();
		 empMap = new HashMap<String, String>();
		 while(rs.next())
		 {
			 empMap.put(rs.getString("emp_id"),rs.getString("manager_id"));
		 }
		 
		
		 
	 }catch(Exception e){
		 e.printStackTrace();
	 }
	 finally{if(currentCon!=null){try{currentCon.close();}catch(Exception e){if(ps!=null && rs!=null){try{ps.close();rs.close();}catch(Exception ex){}}}}}
    return empMap;
     }
    
     
  public ArrayList<String> getSubOrds(String manager_id)
  {
	  ArrayList<String> subOrds = new ArrayList<String>();
	  getSubOrds(manager_id,subOrds);
	  return subOrds;
	  
  }
  private void getSubOrds(String manager_id, ArrayList<String> subOrdinates)
  {
	  HashMap<String,String> empMap = update();
	  if(empMap!=null)
	  for( Entry<String, String> emp : empMap.entrySet())
		{
		  if(manager_id.equalsIgnoreCase(emp.getValue()))
		  {   String curSub=emp.getKey();
			  subOrdinates.add(curSub);
			  if(empMap.containsValue(curSub))
			  {
				  getSubOrds(curSub,subOrdinates);
			  }
		   }	
		}
	  System.out.println(subOrdinates);
	  
  }
  
  
  
   
}
