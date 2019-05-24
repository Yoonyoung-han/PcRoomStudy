package login;

import oracle.jdbc.proxy.annotation.Pre;

import javax.swing.*;
import java.sql.*;
import java.util.Vector;

public class LogInModel {
    String driver = "oracle.jdbc.driver.OracleDriver";
    String url = "jdbc:oracle:thin:@192.168.0.144:1521:orcl";
    String user = "pcroom";
    String pass = "1234";
    Connection con = null;

    public LogInModel() throws Exception {
        // 드라이버 로딩
        Class.forName( driver );
    }

    public Vector comboPcPlace() throws Exception {
        // connection
        con = DriverManager.getConnection( url, user, pass );

        // sql 문장
        String sql = "SELECT PC_PLACE FROM COMPUTER  " +
                "  WHERE PC_PLACE IS NOT NULL AND PC_STATUS = 0";

        System.out.println( sql );

        // 전송 객체
        PreparedStatement st = con.prepareStatement( sql );
        System.out.println( sql );

        // sql 결과 전송
        ResultSet rs = st.executeQuery( );
        Vector<String> data = new Vector( );


        while (rs.next( )) {
            data.add( rs.getString( "PC_PLACE" ) );
        }
        return data;
    }

    public String[] loginCheck(String id, String passwd) throws Exception {
        con = DriverManager.getConnection( url, user, pass );

        String sql = "SELECT MEMBER_ID, MEMBER_PASS FROM MEMBER WHERE MEMBER_ID = ? AND MEMBER_PASS = ?";

        System.out.println( sql );

        PreparedStatement st = con.prepareStatement( sql );
        st.setString( 1, id );
        st.setString( 2, passwd );

        ResultSet rs = st.executeQuery( );

        String[] valid = new String[2];
        if(rs.next()) {
            valid[0] = rs.getString( "MEMBER_ID" );
            valid[1] = rs.getString( "MEMBER_PASS" );
        }
        return valid;
    }
}
