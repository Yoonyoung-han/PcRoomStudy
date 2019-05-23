package Model;

import java.sql.Connection;

public class SnackModel {
	String driver = "oracle.jdbc.driver.OracleDriver";
	String url ="jdbc:oracle:thin:@192.168.0.144:1521:orcl"; // 지금은 thin으로 만드는 드라이버가 없지만 jdbc가 thin방식이라서 써준다.
	String user ="pcroom";
	String pass="1234";
	Connection con = null;
	
	public SnackModel() throws Exception{
		// 1. 드라이버로딩
		Class.forName(driver); 
	}
}
