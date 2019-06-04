package master.productJfreechart;

import java.sql.*;
import java.util.*;

public class DatabaseC {

	String URL = "jdbc:oracle:thin:@192.168.0.144:1521:orcl";
	String USER ="pcroom";
	String PASS = "1234";

	public ArrayList<ArrayList> getData(int fromYear, int fromMth, int fromDate) {

		ArrayList<ArrayList> data = new ArrayList<ArrayList>();
		try{
			Class.forName("oracle.jdbc.driver.OracleDriver");
			Connection con = DriverManager.getConnection(URL, USER , PASS);	
			
			//***************************************************************
			String sql = "SELECT hour_pd.producthour product_hour, SUM(hour_pd.hour_cnt*pd.product_price) hour_total_pay\r\n" + 
					"FROM product pd, (SELECT TO_CHAR(payment_date, 'YY-MM-DD-HH') producthour, product_id, COUNT(product_id) hour_cnt\r\n" + 
					"FROM payment\r\n" + 
					"WHERE payment_date BETWEEN TO_DATE(?, 'YYYY-MM-DD:HH24:MI:SS') AND TO_DATE(?, 'YYYY-MM-DD:HH24:MI:SS')  "+
					"GROUP BY TO_CHAR(payment_date, 'YY-MM-DD-HH'), product_id\r\n" + 
					"ORDER BY producthour)hour_pd\r\n" + 
					"WHERE pd.product_id = hour_pd.product_id\r\n" + 
					"GROUP BY hour_pd.producthour  "
					+ " ORDER BY product_hour";
			//***************************************************************
			System.out.println(String.valueOf(fromYear)+'-'+String.valueOf(fromMth)+'-'+String.valueOf(fromDate)+":00:00:00");
			System.out.println(String.valueOf(fromYear)+'-'+String.valueOf(fromMth)+'-'+String.valueOf(fromDate)+":23:59:59");
			PreparedStatement stmt = con.prepareStatement( sql );	
			stmt.setString(1, String.valueOf(fromYear)+'-'+String.valueOf(fromMth)+'-'+String.valueOf(fromDate)+":00:00:00");
			stmt.setString(2, String.valueOf(fromYear)+'-'+String.valueOf(fromMth)+'-'+String.valueOf(fromDate)+":23:59:59");

			ResultSet rset = stmt.executeQuery();

			
			while( rset.next() ){
				ArrayList temp = new ArrayList();
				temp.add( rset.getInt("hour_total_pay"));			//**************** 값
				temp.add( rset.getString("product_hour"));		//**************** 카테고리	
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
