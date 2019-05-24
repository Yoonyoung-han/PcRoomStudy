package pcroommain;
import java.awt.BorderLayout;
import java.awt.GridLayout;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTextArea;

import view.ClientMainView;
import view.DrinkView;
import view.MealView;
import view.SnackView;

public class PcRoomClient extends JFrame {
	ClientMainView client;
	DrinkView drink;
	MealView meal;
	SnackView snack;
	JTextArea taChat, taOrderList, taOrderTotal;
	JButton bOrder;
	JRadioButton rbCash, rbCard;
	
	
	public PcRoomClient() {
		//멤버변수 객체생성
		taChat = new JTextArea("채팅",15,30);
		taOrderList = new JTextArea("주문 리스트",10,25);
		taOrderTotal = new JTextArea("Total",5,10);
		bOrder = new JButton("주문 하기");
		rbCash = new JRadioButton();
		rbCard = new JRadioButton();
		
// 탭이랑 나머지 잡다한 거 크게 하나 붙이기
		
//탭 영역
	JPanel p_center = new JPanel();
	p_center.setLayout(new BorderLayout());
	
		//각각의 화면을 관리하는 클래스 객체 생성
		client = new ClientMainView();
		drink = new DrinkView();
		meal = new MealView();
		snack = new SnackView();
		
		JTabbedPane  pane = new JTabbedPane();
		pane.addTab("인기 상품", client );
		pane.addTab("스낵", new JScrollPane(snack));
		pane.addTab("식사",new JScrollPane( meal));
		pane.addTab("음료", new JScrollPane(drink));
		
		pane.setSelectedIndex(1); 

		
		
		p_center.add(pane,BorderLayout.CENTER);
		




		
		
//탭 밑 영역

			
				//주문 및 채팅 영역
			JPanel p_south_left = new JPanel();
			p_south_left.add(taChat);
			
			JPanel p_south_center = new JPanel();
			p_south_center.setLayout(new BorderLayout());
			p_south_center.add(taOrderList,BorderLayout.CENTER);
			p_south_center.add(taOrderTotal,BorderLayout.SOUTH);
		
			
			JPanel p_south_right = new JPanel();
			p_south_right.setLayout(new BorderLayout());			
			p_south_right.add(bOrder, BorderLayout.CENTER);
				JPanel p_south_right_north = new JPanel();
				p_south_right_north.add(rbCash);
				p_south_right_north.add(new JLabel("현금 결제"));
				p_south_right_north.add(rbCard);
				p_south_right_north.add(new JLabel("카드 결제"));
			p_south_right.add(p_south_right_north, BorderLayout.NORTH);
		
		JPanel p_south = new JPanel();
		p_south.setLayout(new GridLayout(1,3));
		p_south.add(p_south_left);
		p_south.add(p_south_center);
		p_south.add(p_south_right);
		
		p_center.add(p_south,BorderLayout.SOUTH);
			
			
			add("Center", pane );
			add("South",p_south);
			setSize(1320,1000);
			setVisible( true );
			

		setDefaultCloseOperation( JFrame.EXIT_ON_CLOSE );	

	}
	
	public static void main(String[] args) {
		new PcRoomClient();

	}

}
