package client.view;

import client.model.MealModel;
import client.pcroommain.PcRoomClient;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class MealView extends JPanel {
    /*
     * 김치볶음밥 돈까스 떡라면 만두라면 우동 제육덮밥
    치즈라면 크림스파게티 토마토스파게티
     */
    JButton[] mealname = new JButton[9];
    String[] m_label_1 = {"김치볶음밥", "돈까스", "떡라면", "만두라면", "우동", "제육덮밥", "치즈라면", "크림스파게티", "토마토스파게티"};
    String[] m_label_2 = {"4500", "5000", "3500", "4000", "3000", "4500", "3500", "5000", "5000"};
    String[] m_label = new String[m_label_1.length];
    //	String [] m_label = {"<html>김치볶음밥<br/>4500원</html>","<html>돈까스<br/>5000원</html>", "<html>떡라면<br/>3500원</html>", "<html>만두라면<br/>4000원</html>", "<html>우동<br/>3000원</html>",
//			"<html>제육덮밥<br/>4500원</html>", "<html>치즈라면<br/>3500원</html>", "<html>크림스파게티<br/>5000원</html>", "<html>토마토스파게티<br/>5000원</html>"};
    String[] m_icon = {
            "src/imgs/김치볶음밥.jpg", "src/imgs/돈까스.jpg", "src/imgs/떡라면.jpg", "src/imgs/만두라면.jpg",
            "src/imgs/우동.jpg", "src/imgs/제육덮밥.jpg", "src/imgs/치즈라면.jpg", "src/imgs/크림스파게티.jpg", "src/imgs/토마토스파게티.jpg"};
    String[] m_code = new String[m_label.length];
    //DB의 상품코드를 문자열로 저장해서 불러오기
    PcRoomClient parent;
    MealModel mealMo;
    ArrayList data;

    public MealView(PcRoomClient parent) {
        for (int i = 0; i < 9; i++) {
            m_label[i] = "<html>" + m_label_1[i] + "<br/>" + m_label_2[i] + "원</html>";
            mealname[i] = new JButton( m_label[i], new ImageIcon( m_icon[i] ) );
            mealname[i].setVerticalTextPosition( JButton.BOTTOM );
            mealname[i].setHorizontalTextPosition( JButton.CENTER );
        }
        this.parent = parent;
        data = new ArrayList( );
        addLayout( );
        eventProc( );
        connectDB( );
    }

    public void connectDB() {
        try {
            mealMo = new MealModel( );
            for (int i = 0; i < mealname.length; i++) {
                m_code[i] = (String) mealMo.makeMealList( ).get( i );
            }
        } catch (Exception e) {
            System.out.println( "드라이버 로딩 실패 " );
        }
    }

    void addLayout() {
        JPanel mealmenu = new JPanel( );

        mealmenu.setLayout( new GridLayout( 3, 4 ) );
        for (int i = 0; i < 9; i++) {
            mealmenu.add( mealname[i] );
        }

        add( mealmenu );
    }

    public void eventProc() {
        mealMenuHandler mm = new mealMenuHandler( );
        for (int i = 0; i < mealname.length; i++) {
            mealname[i].addActionListener( mm );
        }
    }

    class mealMenuHandler implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            Object o = e.getSource( );

            for (int i = 0; i < mealname.length; i++) {
                if (o == mealname[i]) {
                    ArrayList orderDataList = new ArrayList( );

                    //System.out.println("<"+o.getText()+">");

                    //클릭한 상품의 정보를 저장
                    orderDataList.add( m_code[i] );
                    orderDataList.add( m_label_1[i] );
                    orderDataList.add( 1 );
                    orderDataList.add( m_label_2[i] );
//                    orderDataList.add( new JButton( "삭제 하기" ) );
                    orderDataList.add( new Boolean( true ) );
                    parent.addOrderData( orderDataList );
                }
            }
        }
    }
}
