package master.productJfreechart;

import java.sql.*;
import java.util.*;

public class DatabaseD {

	String URL = "jdbc:oracle:thin:@192.168.0.144:1521:orcl";
	String USER ="pcroom";
	String PASS = "1234";

	public ArrayList<ArrayList> getData(int fromYear, int fromMth, int fromDate, int toYear, int toMth, int toDate) {

		ArrayList<ArrayList> data = new ArrayList<ArrayList>();
		try{
			Class.forName("oracle.jdbc.driver.OracleDriver");
			Connection con = DriverManager.getConnection(URL, USER , PASS);	
			
			//***************************************************************
			String sql = "SELECT mth_pd.productmth product_mth, SUM(mth_pd.mth_cnt*pd.product_price) mth_total_pay\r\n" + 
					"FROM product pd, (SELECT TO_CHAR(payment_date, 'YY-MM') productmth, product_id, COUNT(product_id) mth_cnt\r\n" + 
					"FROM payment\r\n" + 
					"WHERE payment_date BETWEEN TO_DATE(?, 'YYYY-MM-DD') AND TO_DATE(?, 'YYYY-MM-DD')  "+
					"GROUP BY TO_CHAR(payment_date, 'YY-MM'), product_id\r\n" + 
					"ORDER BY productmth)mth_pd\r\n" + 
					"WHERE pd.product_id = mth_pd.product_id\r\n" + 
					"GROUP BY mth_pd.productmth "
					+ " ORDER BY product_mth";
			//***************************************************************
			
			PreparedStatement stmt = con.prepareStatement( sql );	
			System.out.println("dbc: "+String.valueOf(fromYear)+'-'+String.valueOf(fromMth)+'-'+String.valueOf(fromDate));
			System.out.println("dbc: "+String.valueOf(toYear)+'-'+String.valueOf(toMth)+'-'+String.valueOf(toDate));
			stmt.setString(1, String.valueOf(fromYear)+'-'+String.valueOf(fromMth)+'-'+String.valueOf(fromDate));
			stmt.setString(2, String.valueOf(toYear)+'-'+String.valueOf(toMth)+'-'+String.valueOf(toDate));

			ResultSet rset = stmt.executeQuery();

			
			while( rset.next() ){
				ArrayList temp = new ArrayList();
				temp.add( rset.getInt("mth_total_pay"));			//**************** 값
				temp.add( rset.getString("product_mth"));		//**************** 카테고리	
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
