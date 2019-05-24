package view;

import java.awt.*;
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


	}

	/*	객체 생성 및 화면 구성   */
	void addLayout(){
		for(int i=0; i<pcSeat.length; i++) {
			pcSeat[i] = new JLabel();
		}
		for(int i=0; i<pcSeat.length; i++) {
			pcSeat[i].setText((i+1)+"번");
		}
		JPanel[] p_pc = new JPanel[16];
		for(int i=0; i<p_pc.length; i++) {
			p_pc[i] = new JPanel();
			p_pc[i].add(pcSeat[i]);
			p_pc[i].setBackground(Color.LIGHT_GRAY);
		}
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
	
	
	
	
	
}
