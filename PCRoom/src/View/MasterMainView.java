package View;

import java.awt.*;
import java.util.*;

import javax.swing.*;
import javax.swing.table.AbstractTableModel;

import View.MasterMemMngView.MemberTableModel;

public class MasterMainView extends JPanel{
	JTextArea[][] pcSeat = new JTextArea[4][4];
	
	JTable		tableOrder;
	OrderTableModel tbModelOrder;
	

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
		int num = 1; 
		for(int i=0; i<pcSeat.length; i++) {
			for(int j=0; j<pcSeat[i].length; j++) {
				pcSeat[i][j] = new JTextArea(String.valueOf(num++));
				pcSeat[i][j].setSize(10, 10);
				pcSeat[i][j].setBackground(Color.GRAY);
			}
		}
		
		tbModelOrder = new OrderTableModel();
		tableOrder = new JTable(tbModelOrder);
		
		JPanel p_1 = new JPanel();
		p_1.setLayout(new GridLayout(4, 4, 5, 5));
		for(int i=0; i<pcSeat.length; i++) {
			for(int j=0; j<pcSeat[i].length; j++) {
				p_1.add(pcSeat[i][j]);
				
			}	
		}


		setLayout(new GridLayout(1, 2, 5, 0));
		add(p_1);
		add(new JScrollPane(tableOrder));
		
	}
	
	class OrderTableModel extends AbstractTableModel { 
		  
		ArrayList data = new ArrayList();
		String [] columnNames = {"시간","번호","상품명","총 금액"};

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
