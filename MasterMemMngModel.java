package model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

public class MasterMemMngModel {
	
	String driver = "oracle.jdbc.driver.OracleDriver";
	String url = "jdbc:oracle:thin:@192.168.0.144:1521:orcl";
	String user = "pcroom";
	String pass = "1234";

	Connection con;
	
	public MasterMemMngModel() throws Exception{
		// 1. 드라이버로딩
		Class.forName(driver);

	}

	public ArrayList selectMemberList() throws Exception{
		ArrayList list = new ArrayList();
		Connection con = DriverManager.getConnection(url, user, pass);
		// 3. sql 문장 생성
		String sql = "SELECT m.member_tel member_tel, t.member_id member_id, "
				+ "SUM((t.end_time-t.start_time)*24) total_time\r\n" + 
				"FROM time_history t, member m\r\n" + 
				"WHERE m.member_id = t.member_id\r\n" + 
				"GROUP BY t.member_id, m.member_tel  "
				+ " ORDER BY t.member_id";
		// 4. 전송 객체
		PreparedStatement st = con.prepareStatement(sql);

		// 5. 전송
		ResultSet rs = st.executeQuery();
		while(rs.next()) {
			ArrayList data = new ArrayList();
			data.add(rs.getString("member_id"));
			data.add(rs.getString("member_tel"));
			data.add(rs.getString("total_time"));
			list.add(data);
		}
		// 6. 닫기
		st.close();
		con.close();
		
		
		return list;
	}
}
