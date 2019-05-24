import java.awt.*;
import java.util.*;

import javax.swing.*;
import javax.swing.border.TitledBorder;

import view.MasterMainView;
import view.MasterMemMngView;
import view.MasterProductStatView;
import view.MasterUseStatView;

public class PcRoomMaster extends JFrame{
	MasterMainView				masterMain;
	MasterProductStatView		productStat;
	MasterUseStatView			useStat;
	MasterMemMngView			memMng;
	JTextArea					taChat, taMemo, taUsing, taDate;
	JLabel						lbUsing, lbDate;
	DigitalClock				dtClock;

	public PcRoomMaster() {
		//각각의 화면을 관리하는 클래스 객체 생성
		masterMain 		= new MasterMainView();
		productStat		= new MasterProductStatView();
		useStat			= new MasterUseStatView();
		memMng			= new MasterMemMngView();
		taChat			= new JTextArea(10, 40);
		taMemo			= new JTextArea(10, 40);
		taUsing			= new JTextArea(3, 10);
		taDate			= new JTextArea(3, 10);
		lbUsing			= new JLabel();
		lbDate			= new JLabel();
		dtClock			= new DigitalClock();

		JTabbedPane  pane = new JTabbedPane();
		pane.addTab("현황", masterMain );
		pane.addTab("상품 통계", productStat);
		pane.addTab("사용 통계", useStat );
		pane.addTab("회원 관리", memMng );

		pane.setSelectedIndex(0);	// 제일 먼저 뜨는 화면 지정
		
		// 상단 가게명, 현재 사용 중 pc 개수
		JPanel p_using = new JPanel();
		p_using.setSize(20, 40);
		p_using.setBackground(Color.LIGHT_GRAY);

		// 현재 시간
		JPanel p_date = new JPanel();
		lbDate = dtClock.MakeDigitalClock();
		p_date.setSize(20,40);
		p_date.setBackground(Color.LIGHT_GRAY);
		p_date.add(lbDate);		
		
		JPanel p_north	= new JPanel();
		p_north.add(p_using);
		p_north.add(p_date);
		
//		taUsing.setText("청담 1호점\n사용 중 13대");
		
		// 하단 채팅
		JPanel p_chat = new JPanel();
		p_chat.add(taChat);
		p_chat.setBorder(new TitledBorder("채팅"));
		
		// 하단 메모
		JPanel p_memo = new JPanel();
		p_memo.add(taMemo);
		p_memo.setBorder(new TitledBorder("메모"));
		

		JPanel p_south	= new JPanel();
//		p_south.setLayout(new FlowLayout(FlowLayout.CENTER,5, 0));
		p_south.setLayout(new GridLayout(1, 2));
		p_south.add(p_chat);
		p_south.add(p_memo);

		// 화면크기지정
		setLayout(new BorderLayout());
		add(p_north, BorderLayout.NORTH);
		add(pane, BorderLayout.CENTER);
		add(p_south, BorderLayout.SOUTH);
		setSize(1000,1000);
		setVisible( true );

		setDefaultCloseOperation( JFrame.EXIT_ON_CLOSE );	
	}
	
	
	public static void main(String[] args) 
	{
		new PcRoomMaster();
			
	}

}
