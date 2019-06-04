package master.productJfreechart;

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
			String sql = "SELECT day_pd.productday product_day, SUM(day_pd.day_cnt*pd.product_price) day_total_pay\r\n" + 
					"FROM product pd, (SELECT TO_CHAR(payment_date, 'YY-MM-DD') productday, product_id, COUNT(product_id) day_cnt\r\n" + 
					"FROM payment\r\n" + 
					"WHERE payment_date BETWEEN TO_DATE(?, 'YYYY-MM-DD') AND TO_DATE(?, 'YYYY-MM-DD::HH24:MI:SS')  "+
					"GROUP BY TO_CHAR(payment_date, 'YY-MM-DD'), product_id\r\n" + 
					"ORDER BY productday)day_pd\r\n" + 
					"WHERE pd.product_id = day_pd.product_id\r\n" + 
					"GROUP BY day_pd.productday  "
					+ " ORDER BY product_day";
			//***************************************************************
			
			PreparedStatement stmt = con.prepareStatement( sql );
			System.out.println("db: "+String.valueOf(fromYear)+'-'+String.valueOf(fromMth)+'-'+String.valueOf(fromDate));
			System.out.println("db: "+String.valueOf(toYear)+'-'+String.valueOf(toMth)+'-'+String.valueOf(toDate));
			stmt.setString(1, String.valueOf(fromYear)+'-'+String.valueOf(fromMth)+'-'+String.valueOf(fromDate));
			stmt.setString(2, String.valueOf(toYear)+'-'+String.valueOf(toMth)+'-'+String.valueOf(toDate)+":23:59:59");

			ResultSet rset = stmt.executeQuery();

			
			while( rset.next() ){
				ArrayList temp = new ArrayList();
				temp.add( rset.getInt("day_total_pay"));			//**************** 값
				temp.add( rset.getString("product_day"));		//**************** 카테고리	
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
