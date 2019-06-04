package master.view;

import java.awt.*;
import java.awt.event.*;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.*;

import javax.swing.*;
import javax.swing.table.AbstractTableModel;

public class MasterMainView extends JPanel{
//	JTextArea[][] pcSeat = new JTextArea[4][4];
	public JLabel[] pcSeat = new JLabel[16];
	
	JTable		tableOrder;
	OrderTableModel tbModelOrder;
	BufferedReader in;
	OutputStream out;
	StopWatch[] swUse= new StopWatch[16];
	JPanel[] p_pc;
	JTextField pcEx = new JTextField(10);
	
	JButton[] btEx1 = new JButton[16];
	JButton[] btEx2 = new JButton[16];
	

	

	// 생성자 함수
	public MasterMainView(){
		addLayout();	//화면구성
		eventProc();	//DB연결
		connectDB();
	}
	
	void connectDB(){

	}


	// 이벤트 등록
	public void eventProc(){
		
		useActionHandler uah = new useActionHandler();
		for(int i=0; i<btEx1.length; i++) {
			btEx1[i].addActionListener(uah);
			btEx2[i].addActionListener(uah);
		}
	}
	
	class useActionHandler implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			Object o = e.getSource();
			for(int i=0; i<btEx1.length; i++) {
				if( o == btEx1[i]) {
					swUse[i].timeText.setVisible(true);
					swUse[i].start();
					break;
				} else if(o == btEx2[i]) {
					swUse[i].pause();
					swUse[i].timeText.setVisible(false);
					System.out.println(swUse[i].timeText.getText());
					swUse[i].stop();
					break;
				}
			}
			
		}
		
	}

	/*	객체 생성 및 화면 구성   */
	void addLayout(){
		
		for(int i=0; i<pcSeat.length; i++) {
			pcSeat[i] = new JLabel();
			swUse[i] = new StopWatch();
			pcSeat[i].setText((i+1)+"번");
			btEx1[i] = new JButton("시작");
			btEx2[i] = new JButton("정지");
		}
		
		p_pc = new JPanel[16];
		for(int i=0; i<p_pc.length; i++) {
			p_pc[i] = new JPanel();
//			p_pc[i] = swUse.createCenterPanel();
			p_pc[i].add(pcSeat[i]);
			p_pc[i].add(swUse[i].timeText);
			swUse[i].timeText.setVisible(false);
			p_pc[i].add(btEx1[i]);
			p_pc[i].add(btEx2[i]);
			p_pc[i].setBackground(Color.LIGHT_GRAY);
		}
//		p_pc[0].add(pcEx);
//		for(int i=0; i<pcSeat.length; i++) {
//			for(int j=0; j<pcSeat[i].length; j++) {
//				pcSeat[i][j] = new JTextArea(String.valueOf(num++));
//				pcSeat[i][j].setSize(10, 10);
//				pcSeat[i][j].setBackground(Color.GRAY);
//			}
//		}
		
		tbModelOrder = new OrderTableModel();
		tableOrder = new JTable(tbModelOrder);
		
		JPanel p_1 = new JPanel();
		p_1.setLayout(new GridLayout(4, 4, 5, 5));

		int num = 0; 
		for(int i=0; i<4; i++) {
			for(int j=0; j<4; j++) {
				p_1.add(p_pc[num++]);
			}
		}
//		for(int i=0; i<pcSeat.length; i++) {
//			for(int j=0; j<pcSeat[i].length; j++) {
//				p_1.add(pcSeat[i][j]);
//				
//			}	
//		}


		setLayout(new GridLayout(1, 2, 5, 0));
		add(p_1);
		add(new JScrollPane(tableOrder));
		
	}
	
	class OrderTableModel extends AbstractTableModel { 
		  
		ArrayList data = new ArrayList();
		String [] columnNames = {"시간","번호","상품명", "결제 방법","총 금액"};

			public int getColumnCount() { 
		        return columnNames.length; 
		    } 
		     
		    public int getRowCount() { 
		        return data.size(); 
		    } 

		    public Object getValueAt(int row, int col) { 
				ArrayList temp = (ArrayList)data.get( row );
		        return temp.get( col ); 
		    }
		    
		    public String getColumnName(int col){
		    	return columnNames[col];
		    }
	}
	
	public static class StopWatch {
		public JLabel timeText=null; // 시간 표시 라벨
		TimeThread timeTh=null; // 시간 쓰레드
		long time = 0l, preTime = 0l; // 시간 계산을 위한 변수들
		
		public StopWatch(){
			timeText = new JLabel(toTime(time));
			timeText.setForeground(Color.white);
			timeText.setFont(new Font("Gothic",Font.PLAIN, 20));
		}
		
		public void start(){
			if(timeTh == null || !timeTh.isAlive()){
				if(time != 0) preTime = System.currentTimeMillis() - time;
				else preTime = System.currentTimeMillis();
				timeTh = new TimeThread();
				timeTh.start();
			}
		}

		public void pause(){
			if(timeTh == null) return;
			if(timeTh.isAlive()) timeTh.interrupt();
		}

		public void stop(){
			if(timeTh.isAlive()) {
				timeTh.interrupt();
			}
			time = 0l;
			timeText.setText(toTime(time));
		}

		private class TimeThread extends Thread{
			public void run() {
				try {
					while (!Thread.currentThread().isInterrupted()) {
						sleep(10);
						time = System.currentTimeMillis() - preTime;
						timeText.setText(toTime(time));
					}
				} catch (Exception e) {
				}
			}
		}
		
		private String toTime(long time){
			int m = (int)(time / 1000.0 / 60.0);
			int s = (int)(time % (1000.0 * 60) / 1000.0);
			int ms = (int)(time % 1000 / 10.0);

			return String.format("%02d : %02d : %02d", m, s, ms);
		}

		
	}
	
	
			
	
	
	
}
