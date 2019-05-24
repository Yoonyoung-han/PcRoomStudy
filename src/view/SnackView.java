package view;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JPanel;

import model.SnackModel;
import pcroommain.PcRoomClient;

public class SnackView  extends JPanel {
	/*
	 * 고래밥 구운양파 깐풍새우깡 꼬꼬스낵 썬칩 오감자
오사쯔 차카니 카라멜땅콩 콘초 팅쵹 포카칩 피쉬스낵
허니버터칩
	 */
JButton[] snackname=new JButton[14];
String [] s_label_1 = {"고래밥","구운양파","깐풍새우깡","꼬꼬스낵","썬칩","오감자",
		"오사쯔","차카니", "카라멜땅콩", "콘초", "팅쵹", "포카칩", "피쉬스낵","허니버터칩"};
String [] s_label_2 = {"1500","1500","2000","2000","2000","2500","2500","3000","1500","2500","2500","2500","3500","2500","4000"};
String [] s_label= new String[s_label_1.length];

String [] s_icon = {"src/imgs/고래밥.jpg", "src/imgs/구운양파.jpg","src/imgs/깐풍새우깡.jpg","src/imgs/꼬꼬스낵.jpg","src/imgs/썬칩.jpg","src/imgs/오감자.jpg","src/imgs/오사쯔.jpg"
		,"src/imgs/차카니.jpg","src/imgs/카라멜땅콩.jpg","src/imgs/콘초.jpg","src/imgs/팅쵹.jpg","src/imgs/포카칩.jpg","src/imgs/피쉬스낵.jpg","src/imgs/허니버터칩.jpg"};
String [] s_code = new String[s_label.length];

//DB의 상품코드를 문자열로 저장해서 불러오기
SnackModel snackMo;


PcRoomClient parent;
ArrayList data;

public SnackView(PcRoomClient parent){
for (int i = 0; i <14; i++) {
	s_label[i] = "<html>" + s_label_1[i] + "<br/>" + s_label_2[i]+"원</html>";
	snackname[i] = new JButton(s_label[i],new ImageIcon(s_icon[i]));
	snackname[i].setVerticalTextPosition(JButton.BOTTOM);
	snackname[i].setHorizontalTextPosition(JButton.CENTER);
}
this.parent = parent;
data = new ArrayList();
addLayout();
eventProc();
connectDB();

}

public void connectDB() {
	try {
		snackMo = new SnackModel();
		for (int i = 0; i < snackname.length; i++) {
			s_code[i] =(String)snackMo.makeSnackList().get(i);
		}
	} catch (Exception e) {
		System.out.println("드라이버 로딩 실패 ");

	}
	
}

void addLayout() {

	JPanel snackmenu = new JPanel();
	
	snackmenu.setLayout(new GridLayout(4,4));
	for (int i = 0; i < 14; i++) {
		snackmenu.add(snackname[i]);
	}
	add(snackmenu);
}


public void eventProc() {
	snackMenuHandler sm = new snackMenuHandler();
	for (int i = 0; i < snackname.length; i++) {
		snackname[i].addActionListener(sm);
	}
}

class snackMenuHandler implements ActionListener{

	@Override
	public void actionPerformed(ActionEvent e) {
		JButton o = (JButton)e.getSource();
		
		for(int i=0; i<snackname.length; i++) {
			if( o == snackname[i]) {
				ArrayList orderDataList = new ArrayList();
				
				//System.out.println("<"+o.getText()+">");

					//클릭한 상품의 정보를 저장
					orderDataList.add(s_code[i]);
					orderDataList.add(s_label_1[i]);
					orderDataList.add(1);
					orderDataList.add(s_label_2[i]);

					orderDataList.add(new JButton("삭제 하기"));
					

					parent.addOrderData(orderDataList);
			}
		}
		

	}
	
}

void showOrderInfo() {
	for (int i = 0; i < snackname.length; i++) {
	
	}
}

}
