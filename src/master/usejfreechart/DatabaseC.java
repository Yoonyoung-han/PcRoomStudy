package master.usejfreechart;

import java.sql.*;
import java.util.*;

public class DatabaseC {

	String URL = "jdbc:oracle:thin:@192.168.0.144:1521:orcl";
	String USER ="pcroom";
	String PASS = "1234";

	public ArrayList<ArrayList> getData(int fromYear, int fromMth, int fromDate, int toYear, int toMth, int toDate) {

		ArrayList<ArrayList> data = new ArrayList<ArrayList>();
		try{
			Class.forName("oracle.jdbc.driver.OracleDriver");
			Connection con = DriverManager.getConnection(URL, USER , PASS);	
			
			//***************************************************************
			String sql = "SELECT TO_CHAR(start_time, 'YY-MM') start_time_mth, SUM(money) total_time_mth\r\n" + 
					"FROM time_history\r\n" + 
					"WHERE start_time BETWEEN TO_DATE(?, 'YYYY-MM-DD') AND TO_DATE(?, 'YYYY-MM-DD')  "+
					"GROUP BY TO_CHAR(start_time, 'YY-MM')\r\n" + 
					"ORDER BY start_time_mth";
			//***************************************************************
			System.out.println("dbc: "+String.valueOf(fromYear)+'-'+String.valueOf(fromMth)+'-'+String.valueOf(fromDate));
			System.out.println("dbc: "+String.valueOf(toYear)+'-'+String.valueOf(toMth)+'-'+String.valueOf(toDate));
			PreparedStatement stmt = con.prepareStatement( sql );	
			stmt.setString(1, String.valueOf(fromYear)+'-'+String.valueOf(fromMth)+'-'+String.valueOf(fromDate));
			stmt.setString(2, String.valueOf(toYear)+'-'+String.valueOf(toMth)+'-'+String.valueOf(toDate));

			ResultSet rset = stmt.executeQuery();
			System.out.println(sql);

			
			while( rset.next() ){
				ArrayList temp = new ArrayList();
				temp.add( rset.getInt("total_time_mth"));			//**************** 값
				temp.add( rset.getString("start_time_mth"));		//**************** 카테고리	
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
