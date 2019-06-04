package client.pcroommain;

import java.awt.*;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.awt.event.ActionEvent;
import javax.swing.*;
import javax.swing.border.TitledBorder;
import javax.swing.table.AbstractTableModel;

import chat.ClientView;
import client.view.ClientMainView;
import client.view.DrinkView;
import client.view.MealView;
import client.view.SnackView;
import master.view.MasterMainView.StopWatch;


public class PcRoomClient extends JFrame {
    ClientMainView client;
    DrinkView drink;
    MealView meal;
    SnackView snack;
    public JTextArea taChat, taOrderTotal;
    JButton bOrder, bDelete, bExit, bCal;
    JRadioButton rbCash, rbCard;
    public JTable tableOrderList;
    OrderTableModel tbModelOrder;
    JTextField tfChat;
    String idSend, pcId;

    ClientView cv = new ClientView( );


    public PcRoomClient() {
        addLayout( );
        eventProc( );
    }


    public PcRoomClient(String idSend, String pcId) {
        this.idSend = idSend;
        this.pcId = pcId;

        addLayout( );
        eventProc( );
    }

    private void addLayout() {
        cv.connectServer( idSend, pcId );

        //멤버변수 객체생성
        taChat = new JTextArea( 15, 15 );
        tfChat = new JTextField( );
        //		taOrderList = new JTextArea("주문 리스트",10,25);
        taOrderTotal = new JTextArea( "Total", 5, 10 );
        bOrder = new JButton( "주문 하기" );
        bDelete = new JButton( "전체 삭제" );
        bExit = new JButton( "종료 하기" );
        bCal = new JButton( "계산 하기" );
        rbCash = new JRadioButton( );
        rbCard = new JRadioButton( );
        tbModelOrder = new OrderTableModel( );
        tableOrderList = new JTable( tbModelOrder );

        /** 레이아웃   */
        //각각의 화면을 관리하는 클래스 객체 생성
        client = new ClientMainView( );
        drink = new DrinkView( this );
        meal = new MealView( this );
        snack = new SnackView( this );

        // tab // 스낵, 식사, 음료 탭
        JTabbedPane left_tabbed = new JTabbedPane( );
        left_tabbed.addTab( "인기 상품", client );
        left_tabbed.addTab( "스낵", new JScrollPane( snack ) );
        left_tabbed.addTab( "식사", new JScrollPane( meal ) );
        left_tabbed.addTab( "음료", new JScrollPane( drink ) );
        left_tabbed.setSelectedIndex( 1 );

        // 채팅
        JPanel left_chat = new JPanel( );
        left_chat.setBorder( new TitledBorder( "채팅" ) );
        left_chat.setLayout( new BorderLayout( ) );
        left_chat.add( taChat, BorderLayout.CENTER );
        left_chat.add( tfChat, BorderLayout.SOUTH );

        // 중앙(왼쪽) 영역 // 탭 + 채팅
        JPanel p_center = new JPanel( );
        p_center.setLayout( new BorderLayout( ) );
        p_center.add( left_tabbed, BorderLayout.CENTER );
        p_center.add( left_chat, BorderLayout.SOUTH );

        // p_north // 상단, 자리번호, 아이디, 사용 시간
        JPanel p_north = new JPanel( );

        p_north.setLayout( new FlowLayout( ) );

        StopWatch time = new StopWatch( );
        time.timeText.setForeground( Color.black );

        p_north.add( new JLabel( "아이디 : " + idSend ) );
        p_north.add( new JLabel( "          자리번호 : " + pcId ) );
        p_north.add( new JLabel( "          사용 시간     " ) );
        time.start( );
        p_north.add( time.timeText );

        p_north.add( bExit );

        // p_east
        JPanel p_east = new JPanel( );

        // p_east_center   // 주문 내용 및 총 금액
        JPanel p_east_center = new JPanel( );
        JPanel p_east_center_1 = new JPanel( );

        p_east_center.setBorder( new TitledBorder( "주문 내용" ) );
        p_east_center.setLayout( new BorderLayout( ) );
        p_east_center.add( new JScrollPane( tableOrderList ), BorderLayout.CENTER );
        p_east_center.add( p_east_center_1, BorderLayout.SOUTH );

        p_east_center_1.setLayout( new BorderLayout( ) );
        p_east_center_1.add( taOrderTotal, BorderLayout.CENTER );
        p_east_center_1.add( bCal, BorderLayout.EAST );

        JPanel p_east_south = new JPanel( );
        p_east_south.setBorder( new TitledBorder( "주문 완료 및 수정" ) );
        p_east_south.setLayout( new BorderLayout( ) );

        p_east.setLayout( new BorderLayout( ) );
        p_east.add( p_east_center, BorderLayout.CENTER );
        p_east.add( p_east_south, BorderLayout.SOUTH );

        // p_east_north    // 현금,카드 라디오 버튼 및 주문하기 삭제하기 버튼
        JPanel p_east_south_1 = new JPanel( );
        p_east_south_1.add( rbCash );
        p_east_south_1.add( new JLabel( "현금 결제          " ) );
        p_east_south_1.add( rbCard );
        p_east_south_1.add( new JLabel( "카드 결제" ) );

        // p_south_right_divide // 주문하기, 삭제하기 버튼
        JPanel p_east_south_2 = new JPanel( );
        p_east_south_2.setLayout( new GridLayout( 1, 2 ) );
        p_east_south_2.add( bOrder );
        p_east_south_2.add( bDelete );

        // p_east_south
        p_east_south.setLayout( new BorderLayout( ) );
        p_east_south.add( p_east_south_1, BorderLayout.NORTH );
        p_east_south.add( p_east_south_2, BorderLayout.CENTER );

        setLayout( new BorderLayout( ) );
        add( p_north, BorderLayout.NORTH );
        add( p_center, BorderLayout.CENTER );
        add( p_east, BorderLayout.EAST );

        setSize( 1320, 1000 );
        setVisible( true );


        setDefaultCloseOperation( JFrame.EXIT_ON_CLOSE );
    }

    class OrderTableModel extends AbstractTableModel {
        ArrayList data = new ArrayList( );
        String[] columnNames = {"상품 번호", "상품 명", "수량", "금액", " "};


        public int getColumnCount() {
            return columnNames.length;
        }

        public int getRowCount() {
            return data.size( );
        }

        public Object getValueAt(int row, int col) {
            ArrayList temp = (ArrayList) data.get( row );
            return temp.get( col );
        }

        public String getColumnName(int col) {
            return columnNames[col];
        }

        public Class getColumnClass(int c) {
            return getValueAt( 0, c ).getClass( );
        }

        public boolean isCellEditable(int row, int col) {
            //체크박스 수정할 수 있게 만들기
            if (col == 4) {
                return true;
            } else {
                return false;
            }
        }

        public void setValueAt(Object value, int row, int col) {
            ArrayList temp = (ArrayList) data.get( row );
            temp.set( col, value );
            fireTableCellUpdated( row, col );
        }
    }

    public void eventProc() {
        ButtonEventHandler beh = new ButtonEventHandler( );
        bOrder.addActionListener( beh );
        bExit.addActionListener( beh );
        bDelete.addActionListener( beh );
        bCal.addActionListener( beh );
        tfChat.addActionListener( beh );
    }

    // 버튼 이벤트 핸들러 만들기
    class ButtonEventHandler implements ActionListener {
        public void actionPerformed(ActionEvent ev) {
            Object o = ev.getSource( );

            if (o == bOrder) {
                JOptionPane.showConfirmDialog( null, "주문하기" );
                ArrayList orderArray = orderDecision( ); // 주문하기
                cv.orderMenu( orderArray );
            } else if (o == bDelete) { // 주문 삭제하기
                JOptionPane.showConfirmDialog( null, "삭제하기" );
                deleteDecision( );
            } else if (o == bExit) { // 사용 종료하기

            } else if (o == bCal) {
                orderDecision( ); // 계산 하기
            } else if (o == tfChat) {
                String sendMsg = tfChat.getText( );
                taChat.append( pcId + "번 > " + sendMsg + "\n");
                cv.sendText( pcId, sendMsg );

                tfChat.setText( null );
            }
        }
    }

    private void deleteDecision() {
        tbModelOrder.data = new ArrayList( );
        tbModelOrder.fireTableDataChanged( );
    }

    public ArrayList orderDecision() {
        ArrayList orderCode = new ArrayList( );
        int total = 0;

        //1. JTable에 row 만큼 반복문 돌려서 네번째 행의 값을 얻어오기(메소드 : getValueAt)
        for (int i = 0; i < tableOrderList.getRowCount( ); i++) {
            boolean select = (Boolean) tableOrderList.getValueAt( i, 4 );
            if (select == true) {
                //2. int total 변수 하나 만들어서 그 변수에 총 금액 더하기
                total += Integer.parseInt( (String) tableOrderList.getValueAt( i, 3 ) );
                orderCode.add( tableOrderList.getValueAt( i, 0 ) ); //체크박스 선택한 열의 첫번째 행(상품코드) ArrayList 에 담아서 리턴
            }
        }
        //3. 반복문 끝나고 나서 2번 변수를 taTotal에 값 출력하기
        taOrderTotal.setText( "총 " + total + " 원 입니다." );
        System.out.println( orderCode );
        return orderCode;
    }


    public void addOrderData(ArrayList data) {

        tbModelOrder.data.add( data );
        tbModelOrder.fireTableDataChanged( );
        System.out.println( tbModelOrder.data.size( ) );
    }


    public static void main(String[] logInfo) {
        new PcRoomClient( );
    }
}
