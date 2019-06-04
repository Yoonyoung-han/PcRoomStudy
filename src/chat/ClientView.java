package chat;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.ArrayList;

import javax.swing.*;

public class ClientView extends JFrame {

    Socket s;
    DataOutputStream out;
    DataInputStream in;

    public ClientView() {
        connInfo( );
    }

    private void connInfo() {
        String address = "127.0.0.1";
        int port = 1234;
        try {
            s = new Socket( address, port );

            out = new DataOutputStream( s.getOutputStream( ) );
            in = new DataInputStream( s.getInputStream( ) );

        } catch (IOException e) {
            System.out.println( "connInfo error" + e.getMessage() );
        }
    }

    public void orderMenu(ArrayList orderArray) {
        String msg = "/order >" + orderArray +"\n";
        try {
            out.writeUTF( msg );
            // 받으려면 while(s.isConnected)로 뭔가를 처리 in.readUTF;
        } catch (IOException e) {
            System.out.println( "orderMenu 실패" + e.getMessage( ) );
        }
    }

    public void sendText(String pcId, String sendMsg) {
        String msg = "/chat >" + pcId + "번 자리 :  " + sendMsg + "\n";

        try {
            out.writeUTF( msg );
        } catch (IOException e) {
            System.out.println( "sendText 실패" + e.getMessage( ) );
        }
    }

    public void connectServer(String idSend, String pcId) {
        String msg = "/start >" + pcId + "번 자리에서 " + idSend + " 아이디가 로그인 했습니다. " +"\n";

        try {
            out.writeUTF( msg );
            System.out.println( msg );

            // 받으려면 while(s.isConnected)로 뭔가를 처리 in.readUTF;
        } catch (IOException e) {
            System.out.println( "connectServer 실패" + e.getMessage( ) );
        }
    }

    public static void main(String[] args) {
        new ClientView( );
    }
}