package master.usejfreechart;

import java.sql.*;
import java.util.*;

public class DatabaseD {

	String URL = "jdbc:oracle:thin:@192.168.0.144:1521:orcl";
	String USER ="pcroom";
	String PASS = "1234";

	public ArrayList<ArrayList> getData() {

		ArrayList<ArrayList> data = new ArrayList<ArrayList>();
		try{
			Class.forName("oracle.jdbc.driver.OracleDriver");
			Connection con = DriverManager.getConnection(URL, USER , PASS);	
			
			//***************************************************************
			String sql = "SELECT member_id, sum(money) total_time_member\r\n" + 
					"FROM time_history\r\n" + 
					"GROUP BY member_id";
			//***************************************************************
			
			PreparedStatement stmt = con.prepareStatement( sql );	

			ResultSet rset = stmt.executeQuery();

			
			while( rset.next() ){
				ArrayList temp = new ArrayList();
				temp.add( rset.getInt("total_time_member"));			//**************** 값
				temp.add( rset.getString("member_id"));		//**************** 카테고리	
				data.add(temp);
			}
			rset.close();
			stmt.close();
			con.close();
		} catch(Exception ex){
			System.out.println("에러 : " + ex.getMessage() );
		}
		return data;
	}
}
