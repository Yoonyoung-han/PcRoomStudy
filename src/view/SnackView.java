package view;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JPanel;

import pcroommain.PcRoomClient;

public class SnackView  extends JPanel {
	/*
	 * 고래밥 구운양파 깐풍새우깡 꼬꼬스낵 썬칩 오감자
오사쯔 차카니 카라멜땅콩 콘초 팅쵹 포카칩 피쉬스낵
허니버터칩
	 */
JButton[] snackname=new JButton[14];
String [] s_label = {"<html>고래밥<br/>1500원</html>","<html>구운양파<br/>1500원</html>","<html>깐풍새우깡<br/>2000원</html>","<html>꼬꼬스낵<br/>2000원</html>","<html>썬칩<br/>2500원</html>",
		"<html>오감자<br/>2500원</html>","<html>오사쯔<br/>3000원</html>","<html>차카니<br/>1500원</html>","<html>카라멜땅콩<br/>2500원</html>",
		"<html>콘초<br/>2500원</html>","<html>팅쵹<br/>2500원</html>","<html>포카칩<br/>3500원</html>","<html>피쉬스낵<br/>2500원</html>","<html>허니버터칩<br/>4000원</html>"};

String [] s_icon = {"src/imgs/고래밥.jpg", "src/imgs/구운양파.jpg","src/imgs/깐풍새우깡.jpg","src/imgs/꼬꼬스낵.jpg","src/imgs/썬칩.jpg","src/imgs/오감자.jpg","src/imgs/오사쯔.jpg"
		,"src/imgs/차카니.jpg","src/imgs/카라멜땅콩.jpg","src/imgs/콘초.jpg","src/imgs/팅쵹.jpg","src/imgs/포카칩.jpg","src/imgs/피쉬스낵.jpg","src/imgs/허니버터칩.jpg"};

PcRoomClient parent;

public SnackView(){
for (int i = 0; i <14; i++) {
	snackname[i] = new JButton(s_label[i],new ImageIcon(s_icon[i]));
}


addLayout();
eventProc();
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
		showOrderInfo();
		
	}
	
}

void showOrderInfo() {
	for (int i = 0; i < snackname.length; i++) {
	
	}
}

}
