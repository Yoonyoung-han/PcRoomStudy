package login;

import javax.swing.*;
import javax.swing.border.TitledBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Vector;

public class LogInView extends JFrame {

    JComboBox ComboPcPlace;
    JTextField tfId, tfPass;
    JButton bLogIn;
    LogInModel db;

    public LogInView() {
        connectDB( );
        addLayout( );    //화면
        eventProc( );    // 디비연결

    }

    private void eventProc() {
        ActionHandler ah = new ActionHandler( );

        //이벤트
        bLogIn.addActionListener( ah );
    }

    class ActionHandler implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            // System.out.println( "이벤트" );    // 이벤트 발생하는지 알아보려고 최초에 활용
            Object o = e.getSource( );

            if (o == bLogIn) {
                loginCheck( );
            }
        }
    }

    private void loginCheck() {
        String id = tfId.getText( );
        String passwd = tfPass.getText( );
        try {
            String[] valid = db.loginCheck( id, passwd );
            String id2 = valid[0];
            String passwd2 = valid[1];
            System.out.println( id2 + "//" + passwd2 ); // 넘어온 값 확인용

            if (id.equals( id2 ) && passwd.equals( passwd2 )) {
                JOptionPane.showMessageDialog( null, "로그인 성공" );
            } else {
                JOptionPane.showMessageDialog( null, "아이디 혹은 암호가 틀렸습니다." );
            }
        } catch (Exception e) {
            e.printStackTrace( );
        }
    }

    private void connectDB() {
        try {
            db = new LogInModel( );
            System.out.println( db );
        } catch (Exception e) {
            e.printStackTrace( );
        }
    }


    public void addLayout() {

        Vector<String> list = null;

        try {
            list = db.comboPcPlace( );

        } catch (Exception e) {
            e.printStackTrace( );
        }
//        String[] PcPlace = { "1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12", "13", "14", "15", "16"};
        ComboPcPlace = new JComboBox( list );
        ComboPcPlace.setBorder( new TitledBorder( "사용 가능한 자리" ) );
        ComboPcPlace.setBackground( Color.cyan );

        tfId = new JTextField( 15 );
        tfId.setBorder( new TitledBorder( "아이디" ) );
        tfPass = new JTextField( 15 );
        tfPass.setBorder( new TitledBorder( "암호" ) );

        bLogIn = new JButton( "로그인하기" );
        bLogIn.setBackground( Color.blue );
        bLogIn.setContentAreaFilled( false );
        bLogIn.setOpaque( true );

        JPanel center_panel = new JPanel( );
        center_panel.add( tfId );
        center_panel.add( tfPass );

        setLayout( new GridLayout( 4, 1 ) );
        add( ComboPcPlace );
        add( tfId );
        add( tfPass );
        add( bLogIn );


        setVisible( true );

        setSize( 400, 500 );
    }


    public static void main(String[] args) {
        new LogInView( );
    }
}
