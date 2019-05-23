import java.awt.*;
import java.util.*;

import javax.swing.*;

import View.MasterMainView;
import View.MasterMemMngView;
import View.MasterProductStatView;
import View.MasterUseStatView;

public class PcRoomMaster extends JFrame{
	MasterMainView				masterMain;
	MasterProductStatView		productStat;
	MasterUseStatView			useStat;
	MasterMemMngView			memMng;
	JTextArea					taChat, taMemo, taUsing, taDate;
//	JLabel						lbUsing, lbDate;

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
//		lbUsing			= new JLabel();

		JTabbedPane  pane = new JTabbedPane();
		pane.addTab("현황", masterMain );
		pane.addTab("상품 통계", productStat);
		pane.addTab("사용 통계", useStat );
		pane.addTab("회원 관리", memMng );

		pane.setSelectedIndex(0);	// 제일 먼저 뜨는 화면 지정
		
		JPanel p_north	= new JPanel();
		p_north.add(taUsing);
		p_north.add(taDate);
		taUsing.setText("청담 1호점\n사용 중 13대");
		

		JPanel p_south	= new JPanel();
//		p_south.setLayout(new FlowLayout(FlowLayout.CENTER,5, 0));
		p_south.setLayout(new GridLayout(1, 2, 5, 0));
		p_south.add(taChat);
		p_south.add(taMemo);

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
