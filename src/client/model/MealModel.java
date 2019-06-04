package client.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

public class MealModel {
	String driver = "oracle.jdbc.driver.OracleDriver";
	String url ="jdbc:oracle:thin:@192.168.0.144:1521:orcl"; // 지금은 thin으로 만드는 드라이버가 없지만 jdbc가 thin방식이라서 써준다.
	String user ="pcroom";
	String pass="1234";
	Connection con = null;

	public MealModel() throws Exception{
		// 1. 드라이버로딩
		Class.forName(driver); 
	}

	public ArrayList makeMealList() throws Exception {
		ArrayList p_id= new ArrayList();

		// 2. Connection 연결객체 얻어오기
		con = DriverManager.getConnection(url,user,pass);
		// 3. sql 문장 만들기
		String sql = "SELECT product_id " + 
				" FROM product " + 
				" WHERE product_cat = '식사'";
		// 4. sql 전송객체 (PreparedStatement)
		PreparedStatement st = con.prepareStatement(sql);
		// 5. sql 전송
		ResultSet rs = st.executeQuery(); 

		while(rs.next()) {
			p_id.add(rs.getString("product_id"));

		}
		//6. 닫기
		st.close();
		con.close();
		return p_id;

	}
}
