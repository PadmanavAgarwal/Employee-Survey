package Login;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map.Entry;

public class ratingDAO {
	
  	
  Connection currentCon=null;
  

  public static void storeRating(HashMap<String,int[]> map,ArrayList<String> subOrds, int noq){ //noq may be taken from map array size
	  
	 int []na = new int[noq];
	 int []rating = new int[noq];
	 if(subOrds!=null){
	 Iterator<String> iterate = subOrds.iterator();
	 while(iterate.hasNext())
	 {   String emp=iterate.next();
		 rating = map.get(emp);	 
		 int ratings[]=new int[noq];
         for(int i=0;i<noq;i++)
	     {
		 ratings[i]+=rating[i];
		 if(rating[i]==0)na[i]++;
	     }
	 }
	 }  
  }
  
  
  
  public HashMap<String, int[]> update(String user_id)
  {  
	 
	 HashMap<String, int[]> ratingMap=new HashMap<String, int[]>();
	 
	 ArrayList<String> subords=new worksforDAO().getSubOrds(user_id);
	 Iterator iterate = subords.iterator();
	 //String []subOrds = new String[subords.size()];
	 /*String SubOrdinates =new String("\'"+iterate.next()+"\'");
	 int i=0;
	 while(iterate.hasNext())
	 { //subOrds[i++]=(String) iterate.next();
	   SubOrdinates += ",\'"+(String)iterate.next()+"\'";
	 }*/
	 int i;
	 PreparedStatement ps=null;
	 PreparedStatement ps1=null;
	 
	 ResultSet rs=null;
	 try{
		 currentCon = ConnectionManager.getConnection();
		 String query = "select question_id,sum(rating) as 'totalRating', count(question_id) as 'noa' from survey  where user_id in ( ?";
		 for(i=1;i<subords.size();i++)
	     query += ",?";
	     query += ")group by question_id";
		 //String nnaQuery="select question_id,count(*) as 'nna' from survey where user_id in ( select * from unnest(?)) and rating=0 group by question_id";
		 String nnaQuery="Select a.question_id,count(b.question_id) as 'nna' from (select distinct(question_id) from survey) a  left join survey b on a.question_id=b.question_id and rating=0 where user_id in (?"; 
		 for(i=1;i<subords.size();i++)
		 nnaQuery += ",?";
		 nnaQuery += ")group by b.question_id";
		 System.out.println(query);
		 ps=currentCon.prepareStatement(query);
		 ps1=currentCon.prepareStatement(nnaQuery);
		 i=0;
		 while(iterate.hasNext())
		 {++i;
		  String user=(String)iterate.next();
		  ps.setString(i,(String)user);
		  ps1.setString(i,(String)user);
		 }
		 
		 rs=ps.executeQuery();
		 while(rs.next())
		 {
			// String user_id=rs.getString("user_id");
			 String question_id=rs.getString("question_id");
			 String totalRating=rs.getString("totalRating");
			 int noa=rs.getInt("noa");
			 System.out.println(question_id + "  :  " + totalRating);
			 ratingMap.put(question_id,new int[3]);
			 ratingMap.get(question_id)[0]=Integer.valueOf(totalRating);
			 ratingMap.get(question_id)[1]=0;
			 ratingMap.get(question_id)[2]=noa;
			 
		 }
		 rs=null;
		 rs=ps1.executeQuery();
		 while(rs.next())
		 {
			 String question_id=rs.getString("question_id");
			 int nna = rs.getInt("nna");
			 ratingMap.get(question_id)[1]=nna;
		 }
		 
		//Iterator<Entry<String, String>> emp = empMap.entrySet().iterator();
		
			 
	 }catch(Exception e){
		 e.printStackTrace();
	 }
	 return ratingMap;
  }

  
  
}
