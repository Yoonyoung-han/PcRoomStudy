package client.view;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JPanel;

import client.model.DrinkModel;
import client.pcroommain.PcRoomClient;


public class DrinkView extends JPanel {
    /*
     * 갈아만든배 망고스무디 밀키스 봉봉 사이다 아메리카노
    청포도스무디 카라멜마끼아또 카페라떼 콜라
    토레타 환타
     */
    JButton[] drinkname = new JButton[12];
    String[] d_label_1 = {"갈아만든배", "망고스무디", "밀키스", "봉봉", "사이다", "아메리카노",
            "청포도스무디", "카라멜마끼아또", "카페라떼", "콜라", "토레타", "환타"};
    String[] d_label_2 = {"1200", "2000", "1200", "1500", "1200", "2000", "2000", "3000", "2500", "1200", "1500", "1200"};
    String[] d_label = new String[d_label_1.length];
    //	String [] d_label= {"<html>갈아만든 배<br/>1200원</html>","<html>망고스무디<br/>2000원</html>","<html>밀키스<br/>1200원</html>","<html>봉봉<br/>1500원</html>","<html>사이다<br/>1200원</html>","<html>아메리카노<br/>2000원</html>"
//			,"<html>청포도스무디<br/>2000원</html>","<html>카라멜마끼아또<br/>3000원</html>","<html>카페라떼<br/>2500원</html>","<html>콜라<br/>1200원</html>","<html>토레타<br/>1500원</html>","<html>환타<br/>1200원</html>"};
    String[] d_icon = {"src/imgs/갈아만든배.jpg", "src/imgs/망고스무디.jpg", "src/imgs/밀키스.jpg", "src/imgs/봉봉.jpg", "src/imgs/사이다.jpg", "src/imgs/아메리카노.jpg"
            , "src/imgs/청포도스무디.jpg", "src/imgs/카라멜마끼아또.jpg", "src/imgs/카페라떼.jpg", "src/imgs/콜라.jpg", "src/imgs/토레타.jpg", "src/imgs/환타.jpg"};
    String[] d_code = new String[d_label.length];

    //DB의 상품코드를 문자열로 저장해서 불러오기
    DrinkModel drinkMo;
    PcRoomClient parent;
    ArrayList data;

    public DrinkView(PcRoomClient parent) {
        for (int i = 0; i < 12; i++) {
            d_label[i] = "<html>" + d_label_1[i] + "<br/>" + d_label_2[i] + "원</html>";
            drinkname[i] = new JButton( d_label[i], new ImageIcon( d_icon[i] ) );
            drinkname[i].setVerticalTextPosition( JButton.BOTTOM );
            drinkname[i].setHorizontalTextPosition( JButton.CENTER );
        }
        this.parent = parent;
        data = new ArrayList( );
        addLayout( );
        eventProc( );
        connectDB( );
    }

    public void connectDB() {
        try {
            drinkMo = new DrinkModel( );
            for (int i = 0; i < drinkname.length; i++) {
                d_code[i] = (String) drinkMo.makeDrinkList( ).get( i );
            }
        } catch (Exception e) {
            System.out.println( "드라이버 로딩 실패 " );

        }

    }


    void addLayout() {
        JPanel drinkmenu = new JPanel( );

        drinkmenu.setLayout( new GridLayout( 4, 4 ) );
        for (int i = 0; i < 12; i++) {
            drinkmenu.add( drinkname[i] );
        }

        add( drinkmenu );
    }

    public void eventProc() {
        drinkMenuHandler dm = new drinkMenuHandler( );
        for (int i = 0; i < drinkname.length; i++) {
            drinkname[i].addActionListener( dm );
        }
    }

    class drinkMenuHandler implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            Object o = e.getSource( );
            for (int i = 0; i < drinkname.length; i++) {
                if (o == drinkname[i]) {
                    ArrayList orderDataList = new ArrayList( );

                    //System.out.println("<"+o.getText()+">");

                    //클릭한 상품의 정보를 저장
                    orderDataList.add( d_code[i] );
                    orderDataList.add( d_label_1[i] );
                    orderDataList.add( 1 );
                    orderDataList.add( d_label_2[i] );
//                    orderDataList.add( new JButton( "삭제 하기" ) );
                    orderDataList.add( new Boolean( true ) );
                    parent.addOrderData( orderDataList );
                }
            }
        }

    }

}
