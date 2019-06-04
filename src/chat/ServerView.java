package chat;


import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

public class ServerView extends JFrame implements Runnable {

    JTextArea ta;
    JTextField tf;
    JButton sendButton;
    ServerSocket serverSocket;
    Socket s;

    public ServerView() {
        addLayout( );
    }

    public void run() {
        try {
            serverSocket = new ServerSocket( 1234 );

        } catch (IOException e) {
            System.out.println( "서버 소켓 에러" + e.getMessage( ) );
        }
        // 서버는 계속 대기
        while (true) {
            try {
                s = serverSocket.accept( );
                System.out.println( "Server On" );
                System.out.println( s.getInetAddress( ) + "에서 접속함" );
                ChatService cs = new ChatService( s );
                cs.start( );


            } catch (IOException e) {
                System.out.println( "서버 대기 에러 : accept 실패" + e.getMessage( ) );
            }
        }
    }


    class ChatService extends Thread {
        DataInputStream in;
        DataOutputStream out;

        ChatService(Socket s) {
            try {
                in = new DataInputStream( s.getInputStream( ) );
                out = new DataOutputStream( s.getOutputStream( ) );

            } catch (Exception e) {
                System.out.println( "생성자 에러" + e.getMessage( ) );
            }
        } // 생성자 종료

        public void run() {
            while (s.isConnected( )) {
                String msg;
                try {
                    msg = in.readUTF( );
                    if (msg == null) return;

                    taAppend( msg );

                } catch (IOException e) {
                    System.out.println( "cs.run error" + e.getMessage( ) );
                }
            }
        }
    }

    private void taAppend(String msg) {
        ta.append( msg );
    }


    private void addLayout() {
        ta = new JTextArea( 40, 25 );
        tf = new JTextField( 25 );
        sendButton = new JButton( "보내기" );

        setLayout( new BorderLayout( ) );
        add( ta, BorderLayout.CENTER );
        add( tf, BorderLayout.SOUTH );
        add( sendButton, BorderLayout.EAST );
        sendButton.setVisible( true );

        setDefaultCloseOperation( EXIT_ON_CLOSE );
        setVisible( true );
        setBounds( 200, 100, 400, 600 );
        setTitle( "서버 부분" );
    }


    /**
     * 이벤트 처리
     */
    private void eventProc() {
        ServerHandler sh = new ServerHandler( );
        sendButton.addActionListener( sh );
        tf.addActionListener( sh );
    }

    class ServerHandler implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            // System.out.println( "이벤트" );    // 이벤트 발생하는지 알아보려고 최초에 활용
            Object o = e.getSource( );

            if (o == sendButton || o == tf) {
                sendText( );
            }
        }
    }

    private void sendText() {
        JOptionPane.showMessageDialog( null, "메세지 전송" );
    }
    public static void main(String[] args) {
        ServerView cs = new ServerView( );
        new Thread( cs ).start( );
    }
}
