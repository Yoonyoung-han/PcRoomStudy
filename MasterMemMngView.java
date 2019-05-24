package view;

import java.awt.*;
import java.util.*;

import javax.swing.*;
import javax.swing.table.AbstractTableModel;

import model.MasterMemMngModel;


public class MasterMemMngView extends JPanel {
	JTable		tableMember;
	MemberTableModel tbModelMember;
	MasterMemMngModel db;
	
	
	// 생성자 함수
	public MasterMemMngView(){
		addLayout();	//화면구성
		eventProc();	//DB연결
		connectDB();
		
		selectMember();
	}

	void connectDB(){

		try {
			db = new MasterMemMngModel();
		} catch (Exception e) {
			System.out.println("실패");
		}
	}


	// 이벤트 등록
	public void eventProc(){


	}

	public void selectMember() {
		try {
			tbModelMember.data = db.selectMemberList();
			tbModelMember.fireTableDataChanged();
		} catch(Exception e) {
			System.out.println("멤버 조회 실패");
		}
	}

	/*	객체 생성 및 화면 구성   */
	void addLayout(){
		tbModelMember = new MemberTableModel();
		tableMember = new JTable(tbModelMember);

		setLayout(new BorderLayout());
		add(new JScrollPane(tableMember), BorderLayout.CENTER);

	}


	//화면에 테이블 붙이는 메소드 
	class MemberTableModel extends AbstractTableModel { 

		ArrayList data = new ArrayList();
		String [] columnNames = {"아 이 디","연 락 처", "누 적 사 용 시 간"};

		//=============================================================
		// 1. 기본적인 TabelModel  만들기
		// 아래 세 함수는 TabelModel 인터페이스의 추상함수인데
		// AbstractTabelModel에서 구현되지 않았기에...
		// 반드시 사용자 구현 필수!!!!

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
