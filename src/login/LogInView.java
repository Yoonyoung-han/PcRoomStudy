package login;

import client.pcroommain.PcRoomClient;

import javax.swing.*;
import javax.swing.border.TitledBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Vector;

public class LogInView extends JFrame {

    String idSend;
    String pcId;

    JComboBox ComboPcPlace;
    JTextField tfId, tfPass;
    JButton bLogIn;
    LogInModel db;
    Vector<String> list = null;

    public LogInView() {
        connectDB( );   // 디비 연결
        addLayout( );   // 화면 연결
        eventProc( );   // 이벤트 연결
    }

    private void addLayout() {
        comboGet( );

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

        setDefaultCloseOperation( JFrame.EXIT_ON_CLOSE );
        setVisible( true );
        setSize( 400, 500 );
    }

    private Vector<String> comboGet() {
        try {
            list = db.comboPcPlace( );
            ComboPcPlace = new JComboBox( list );
            ComboPcPlace.setBorder( new TitledBorder( "사용 가능한 자리" ) );
            ComboPcPlace.setBackground( Color.yellow );

        } catch (Exception e) {
            e.printStackTrace( );
        }
        return list;
    }

    private void connectDB() {
        try {
            db = new LogInModel( );
            System.out.println( db );
        } catch (Exception e) {
            e.printStackTrace( );
        }
    }


    private void eventProc() {
        ActionHandler ah = new ActionHandler( );

        //이벤트
        bLogIn.addActionListener( ah );
        ComboPcPlace.addActionListener( ah );
    }

    public class ActionHandler implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            // System.out.println( "이벤트" );    // 이벤트 발생하는지 알아보려고 최초에 활용
            Object o = e.getSource( );

            if (o == bLogIn) {
                loginCheck( );
            } else if (o == ComboPcPlace) {
//                comboGet( );
//            }
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
//                JOptionPane.showMessageDialog( null, "로그인 성공" );
                    clientStart( );
                    updateCombo();

                } else {
                    JOptionPane.showMessageDialog( null, "아이디 혹은 암호가 틀렸습니다." );
                }
            } catch (Exception e) {
                e.printStackTrace( );
            }
        }

        private void clientStart() {
            idSend = tfId.getText( );
            pcId = (String) ComboPcPlace.getSelectedItem( );

            PcRoomClient prc = new PcRoomClient( idSend, pcId );

            setVisible( false );
            System.out.println( "clientStart 아이디//자리번호 : " + idSend + " // " + pcId );
        }

    }

    private void updateCombo() {
        pcId = (String) ComboPcPlace.getSelectedItem( );
        try {
            db.updateCombo( pcId );
        } catch (Exception e) {
            e.printStackTrace( );
        }
    }

    public static void main(String[] args) {
        new LogInView( );
    }
}