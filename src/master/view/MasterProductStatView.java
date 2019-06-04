package master.view;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Calendar;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;

import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;

import master.productJfreechart.ChartB;
import master.productJfreechart.ChartC;
import master.productJfreechart.ChartD;

public class MasterProductStatView extends JPanel {
	ChartB demob;
	JFreeChart chartb;
	ChartPanel chartPanelb;
	
	ChartC democ;
	JFreeChart chartc;
	ChartPanel chartPanelc;
	
	ChartD demod;
	JFreeChart chartd;
	ChartPanel chartPaneld;
	
	JPanel p_time, p_day, p_month;
	
	JComboBox[] cbY=new JComboBox[5], cbM=new JComboBox[5], cbD=new JComboBox[5];
	int[] lastDays = {31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31};
	
	JButton[] btCheck = new JButton[3];

	public MasterProductStatView(){
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
		btCheck[2] = new JButton("확인");
		
		eventProc();
		
		// 시간별
		p_time = new JPanel();
		demob = new ChartB();		 // (2) DB에서 가져온 값으로 차트 
		chartb = demob.getChart((Integer)cbY[0].getSelectedItem(), cbM[0].getSelectedIndex()+1, cbD[0].getSelectedIndex()+1);     
		chartPanelb=new ChartPanel(chartb); 
		
		JPanel p_time_set = new JPanel();
		p_time_set.setLayout(new FlowLayout());
		p_time_set.add(cbY[0]);
		p_time_set.add(new JLabel("년"));
		p_time_set.add(cbM[0]);
		p_time_set.add(new JLabel("월"));
		p_time_set.add(cbD[0]);
		p_time_set.add(new JLabel("일"));
		p_time_set.add(btCheck[0]);
		
		
		p_time.setLayout(new BorderLayout());
		p_time.add(p_time_set, BorderLayout.NORTH);
		p_time.add(chartPanelb, BorderLayout.CENTER);

		// 일별
		p_day = new JPanel();
		
		democ = new ChartC();		 // (2) DB에서 가져온 값으로 차트 
		chartc = democ.getChart((Integer)cbY[1].getSelectedItem(), cbM[1].getSelectedIndex()+1, cbD[1].getSelectedIndex()+1, 
				(Integer)cbY[2].getSelectedItem(), cbM[2].getSelectedIndex()+1, cbD[2].getSelectedIndex()+1);     
		chartPanelc=new ChartPanel(chartc); 
		
		JPanel p_day_set = new JPanel();
		p_day_set.setLayout(new FlowLayout());
		p_day_set.add(cbY[1]);
		p_day_set.add(new JLabel("년"));
		p_day_set.add(cbM[1]);
		p_day_set.add(new JLabel("월"));
		p_day_set.add(cbD[1]);
		p_day_set.add(new JLabel("일"));
		p_day_set.add(new JLabel("~"));
		p_day_set.add(cbY[2]);
		p_day_set.add(new JLabel("년"));
		p_day_set.add(cbM[2]);
		p_day_set.add(new JLabel("월"));
		p_day_set.add(cbD[2]);
		p_day_set.add(new JLabel("일"));
		p_day_set.add(btCheck[1]);
		
		p_day.setLayout(new BorderLayout());
		p_day.add(p_day_set, BorderLayout.NORTH);
		p_day.add(chartPanelc, BorderLayout.CENTER);
		
		// 월별
		p_month = new JPanel();
		
		demod = new ChartD();		 // (2) DB에서 가져온 값으로 차트 
		chartd = demod.getChart((Integer)cbY[3].getSelectedItem(), cbM[3].getSelectedIndex()+1, 
				(Integer)cbD[3].getItemAt(0), 
				(Integer)cbY[4].getSelectedItem(), cbM[4].getSelectedIndex()+1, 
				lastDays[cbM[4].getSelectedIndex()]);     
		chartPaneld=new ChartPanel(chartd); 
		
		JPanel p_month_set = new JPanel();
		p_month_set.setLayout(new FlowLayout());
		p_month_set.add(cbY[3]);
		p_month_set.add(new JLabel("년"));
		p_month_set.add(cbM[3]);
		p_month_set.add(new JLabel("월"));
		p_month_set.add(new JLabel("~"));
		p_month_set.add(cbY[4]);
		p_month_set.add(new JLabel("년"));
		p_month_set.add(cbM[4]);
		p_month_set.add(new JLabel("월"));
		p_month_set.add(btCheck[2]);
		
		p_month.setLayout(new BorderLayout());
		p_month.add(p_month_set, BorderLayout.NORTH);
		p_month.add(chartPaneld, BorderLayout.CENTER);
		
		setLayout(new GridLayout(3,1));
		add(p_time);
		add(p_day);
		add(p_month);
		
		
	}
	
	public void eventProc() {
		ActionHandler ah = new ActionHandler();
		for(int i=0; i<cbY.length; i++) {
			cbY[i].addActionListener(ah);
			cbM[i].addActionListener(ah);
			cbD[i].addActionListener(ah);
		}
		for(int i=0; i<3; i++) {
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
			else if((evt == cbY[1] || evt == cbM[1])) {
				setDay(1);
			}
			else if((evt == cbY[2] || evt == cbM[2])) {
				setDay(2);
			}
			else if((evt == cbY[3] || evt == cbM[3])) {
				setDay(3);
			}
			else if((evt == cbY[4] || evt == cbM[4])) {
				setDay(4);
			}
			
			if(evt == btCheck[0]) {
				p_time.remove(chartPanelb);
				p_time.revalidate(); 
				p_time.repaint(); 
				
				demob = new ChartB();		 // (2) DB에서 가져온 값으로 차트 
				chartb = demob.getChart((Integer)cbY[0].getSelectedItem(), cbM[0].getSelectedIndex()+1, cbD[0].getSelectedIndex()+1);     
				chartPanelb=new ChartPanel(chartb); 
				
				p_time.add(chartPanelb);
				
				
			} else if( evt == btCheck[1]) {
				p_day.remove(chartPanelc);
				p_day.revalidate(); 
				p_day.repaint(); 
				
				democ = new ChartC();
				chartc = democ.getChart((Integer)cbY[1].getSelectedItem(), cbM[1].getSelectedIndex()+1, cbD[1].getSelectedIndex()+1, 
						(Integer)cbY[2].getSelectedItem(), cbM[2].getSelectedIndex()+1, cbD[2].getSelectedIndex()+1);
				chartPanelc=new ChartPanel(chartc); 
				p_day.add(chartPanelc);
			} else if(evt == btCheck[2]) {
				p_month.remove(chartPaneld);
				p_month.revalidate(); 
				p_month.repaint(); 
				
				demod = new ChartD();
				chartd = demod.getChart((Integer)cbY[3].getSelectedItem(),
							cbM[3].getSelectedIndex()+1, 
							1, 
							(Integer)cbY[4].getSelectedItem(), 
							cbM[4].getSelectedIndex()+1, 
							lastDays[cbM[4].getSelectedIndex()]);
				chartPaneld=new ChartPanel(chartd); 
				p_month.add(chartPaneld);
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
		for(int i=0; i<5; i++) {
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

