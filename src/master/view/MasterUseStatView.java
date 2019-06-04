package master.view;

import java.awt.*;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Calendar;

import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;

import master.usejfreechart.ChartB;
import master.usejfreechart.ChartC;
import master.usejfreechart.ChartD;

import javax.swing.*;

public class MasterUseStatView extends JPanel {
	ChartB demob;
	JFreeChart chartb;
	ChartPanel chartPanelb;
	ChartC democ;
	JFreeChart chartc;
	ChartPanel chartPanelc;
	JPanel p_day, p_month;
	
	
	JComboBox[] cbY=new JComboBox[4], cbM=new JComboBox[4], cbD=new JComboBox[2];
	int[] lastDays = {31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31};
	
	JButton[] btCheck = new JButton[2];

	public MasterUseStatView(){
		Integer[] ysu = new Integer[11];
		for(int i=0; i<ysu.length; i++) {
			ysu[i] = 2015+i;
		}
		Integer[] msu = new Integer[12];
		for(int i=0; i<msu.length; i++) {
			msu[i] = 1+i;
		}
		for(int i=0; i<cbY.length; i++) {
			cbY[i] = new JComboBox(ysu);
			cbM[i] = new JComboBox(msu);
		}

		for(int i=0; i<cbD.length; i++) {
			cbD[i] = new JComboBox();
		}
		
		initDate();

		
		btCheck[0] = new JButton("확인");
		btCheck[1] = new JButton("확인");
		
		eventProc();



		// 일별
		p_day = new JPanel();
		
		demob = new ChartB();		 // (2) DB에서 가져온 값으로 차트 
		chartb= demob.getChart((Integer)cbY[0].getSelectedItem(), cbM[0].getSelectedIndex()+1, cbD[0].getSelectedIndex()+1, 
				(Integer)cbY[1].getSelectedItem(), cbM[1].getSelectedIndex()+1, cbD[1].getSelectedIndex()+1);    
		chartPanelb=new ChartPanel(chartb); 
		
		JPanel p_day_set = new JPanel();
		p_day_set.setLayout(new FlowLayout());
		p_day_set.add(cbY[0]);
		p_day_set.add(new JLabel("년"));
		p_day_set.add(cbM[0]);
		p_day_set.add(new JLabel("월"));
		p_day_set.add(cbD[0]);
		p_day_set.add(new JLabel("일"));
		p_day_set.add(new JLabel("~"));
		p_day_set.add(cbY[1]);
		p_day_set.add(new JLabel("년"));
		p_day_set.add(cbM[1]);
		p_day_set.add(new JLabel("월"));
		p_day_set.add(cbD[1]);
		p_day_set.add(new JLabel("일"));
		p_day_set.add(btCheck[0]);
		
		p_day.setLayout(new BorderLayout());
		p_day.add(p_day_set, BorderLayout.NORTH);
		p_day.add(chartPanelb, BorderLayout.CENTER);

		// 월별
		p_month = new JPanel();
		
		democ = new ChartC();		 // (2) DB에서 가져온 값으로 차트
		System.out.println(cbM[1].getSelectedIndex());
		chartc = democ.getChart((Integer)cbY[0].getSelectedItem(), cbM[0].getSelectedIndex()+1, 
				(Integer)cbD[0].getItemAt(0), 
				(Integer)cbY[1].getSelectedItem(), cbM[1].getSelectedIndex()+1, 
				lastDays[cbM[1].getSelectedIndex()]);     
		chartPanelc=new ChartPanel(chartc); 
		
		JPanel p_month_set = new JPanel();
		p_month_set.setLayout(new FlowLayout());
		p_month_set.add(cbY[2]);
		p_month_set.add(new JLabel("년"));
		p_month_set.add(cbM[2]);
		p_month_set.add(new JLabel("월"));
		p_month_set.add(new JLabel("~"));
		p_month_set.add(cbY[3]);
		p_month_set.add(new JLabel("년"));
		p_month_set.add(cbM[3]);
		p_month_set.add(new JLabel("월"));
		p_month_set.add(btCheck[1]);
		
		p_month.setLayout(new BorderLayout());
		p_month.add(p_month_set, BorderLayout.NORTH);
		p_month.add(chartPanelc, BorderLayout.CENTER);

		// 회원별
		ChartD demod = new ChartD();		 // (2) DB에서 가져온 값으로 차트 
		JFreeChart chartd = demod.getChart();     
		ChartPanel chartPaneld=new ChartPanel(chartd); 

		// JFreeChart는 ChartPanel이나 ChartFrame에만 붙일 수 있다.
		// 차트만 출력하려면 ChartFrame에 .붙여서 바로 출력하거나
		// 기존의 화면에 보이게 하려면 ChartPanel에 붙이고 다시 우리 화면 JPanel에 붙인다. 
		setLayout(new GridLayout(3,1));
		add(p_day);
		add(p_month);
		add(chartPaneld);
		

		


	}

	public void eventProc() {
		ActionHandler ah = new ActionHandler();
		for(int i=0; i<cbY.length; i++) {
			cbY[i].addActionListener(ah);
			cbM[i].addActionListener(ah);
		}
		for(int i=0; i<2; i++) {
			cbD[i].addActionListener(ah);
			btCheck[i].addActionListener(ah);
		}

	}

	class ActionHandler implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			Object evt = e.getSource();
			if((evt == cbY[0] || evt == cbM[0])) {
				setDay(0);
			}
			if((evt == cbY[1] || evt == cbM[1])) {
				setDay(1);
			}
			if(evt == btCheck[0]) {
				p_day.remove(chartPanelb);
				p_day.revalidate(); 
				p_day.repaint(); 
				
				demob = new ChartB();
				chartb = demob.getChart((Integer)cbY[0].getSelectedItem(), cbM[0].getSelectedIndex()+1, cbD[0].getSelectedIndex()+1, 
						(Integer)cbY[1].getSelectedItem(), cbM[1].getSelectedIndex()+1, cbD[1].getSelectedIndex()+1);
				chartPanelb=new ChartPanel(chartb); 
				System.out.println("setDate1"+(Integer)cbY[0].getSelectedItem());
				p_day.add(chartPanelb);
				
				
			} else if( evt == btCheck[1]) {
				p_month.remove(chartPanelc);
				p_month.revalidate(); 
				p_month.repaint(); 
				
				democ = new ChartC();
				chartc = democ.getChart((Integer)cbY[2].getSelectedItem(), 
							cbM[2].getSelectedIndex()+1, 
							1, 
							(Integer)cbY[3].getSelectedItem(), 
							cbM[3].getSelectedIndex()+1, 
							lastDays[cbM[3].getSelectedIndex()]);
				chartPanelc=new ChartPanel(chartc); 
				p_month.add(chartPanelc);
			}
		}

	}

	void initDate() {
		Calendar c = Calendar.getInstance();
		int y = c.get(Calendar.YEAR);
		int m = c.get(Calendar.MONTH)+1;
		for(int i=0; i<cbY.length; i++) {
			cbY[i].setSelectedItem(y);
			cbM[i].setSelectedItem(m);
		}
		setDay();	//일. 먼저 day 콤보박스에 아이템을 채운 상태에서 설정해야 함. 
		int d = c.get(Calendar.DATE);
		for(int i=0; i<cbD.length; i++) {
			cbD[i].setSelectedItem(d);
		}
		
	}
	
	void setDay() {
		//윤년이라면 lastDays[1]=29로 바꾸기
		//평년이라면 lastDays[1]=28로 바꾸기
		//확인 2016-2 윤년 29일 있음
		for(int i=0; i<2; i++) {
			int year = (Integer)cbY[i].getSelectedItem();
			if(( year%4==0 && year%100!=0 )|| year%400==0) {
				lastDays[1] = 29;
			} else lastDays[1] = 28;
			int month = cbM[i].getSelectedIndex();		//선택한 인덱스 얻어오기
			cbD[i].removeAllItems();
			for(int j=1; j<=lastDays[month]; j++) {	
				cbD[i].addItem(j);
			}
		}
	
	}
	
	void setDay(int i) {
		//윤년이라면 lastDays[1]=29로 바꾸기
		//평년이라면 lastDays[1]=28로 바꾸기
		//확인 2016-2 윤년 29일 있음
		
			int year = (Integer)cbY[i].getSelectedItem();
			if(( year%4==0 && year%100!=0 )|| year%400==0) {
				lastDays[1] = 29;
			} else lastDays[1] = 28;
			int month = cbM[i].getSelectedIndex();		//선택한 인덱스 얻어오기
			cbD[i].removeAllItems();
			for(int j=1; j<=lastDays[month]; j++) {	
				cbD[i].addItem(j);
			}
	
	}
}
