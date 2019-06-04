package master.usejfreechart;

import java.sql.*;
import java.util.*;

public class Database {

	String URL = "jdbc:oracle:thin:@192.168.0.144:1521:orcl";
	String USER ="pcroom";
	String PASS = "1234";

	public ArrayList<ArrayList> getData(int fromYear, int fromMth, int fromDate, int toYear, int toMth, int toDate) {

		ArrayList<ArrayList> data = new ArrayList<ArrayList>();
		try{
			Class.forName("oracle.jdbc.driver.OracleDriver");
			Connection con = DriverManager.getConnection(URL, USER , PASS);	
			
			//***************************************************************
			String sql = "SELECT TO_CHAR(start_time, 'YY-MM-DD') start_time_day, SUM(money) total_time_day\r\n" + 
					"FROM time_history\r\n" + 
					"WHERE start_time BETWEEN TO_DATE(?, 'YYYY-MM-DD') AND TO_DATE(?, 'YYYY-MM-DD::HH24:MI:SS')  "+
					"GROUP BY TO_CHAR(start_time, 'YY-MM-DD')\r\n" + 
					"ORDER BY start_time_day";
			//***************************************************************
			System.out.println("db: "+String.valueOf(fromYear)+'-'+String.valueOf(fromMth)+'-'+String.valueOf(fromDate));
			System.out.println("db: "+String.valueOf(toYear)+'-'+String.valueOf(toMth)+'-'+String.valueOf(toDate));
			PreparedStatement stmt = con.prepareStatement( sql );	
			stmt.setString(1, String.valueOf(fromYear)+'-'+String.valueOf(fromMth)+'-'+String.valueOf(fromDate));
			stmt.setString(2, String.valueOf(toYear)+'-'+String.valueOf(toMth)+'-'+String.valueOf(toDate)+":23:59:59");
			
//			stmt.setString(2, String.valueOf(toYear)+'0'+String.valueOf(toMth)+String.valueOf(toDate));

			ResultSet rset = stmt.executeQuery();

			
			while( rset.next() ){
				ArrayList temp = new ArrayList();
				temp.add( rset.getInt("total_time_day"));			//**************** 값
				temp.add( rset.getString("start_time_day"));		//**************** 카테고리	
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
