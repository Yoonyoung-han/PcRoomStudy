package View;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;

import javax.swing.*;


public class SnackView  extends JPanel {
	/*
	 * 고래밥 구운양파 깐풍새우깡 꼬꼬스낵 썬칩 오감자
오사쯔 차카니 카라멜땅콩 콘초 팅쵹 포카칩 피쉬스낵
허니버터칩
	 */
JButton[] snackname=new JButton[14];
String [] s_label = {"고래밥<br>1500원","구운양파<br>1500원","깐풍새우깡<br>2000원","꼬꼬스낵<br>2000원","썬칩<br>2500원",
		"오감자<br>2500원","오사쯔<br>3000원","차카니<br>1500원","카라멜땅콩<br>2500원","콘초<br>2500원","팅쵹<br>2500원","포카칩<br>3500원","피쉬스낵<br>2500원","허니버터칩<br>4000원"};

String [] s_icon = {"src/imgs/고래밥.jpg", "src/imgs/구운양파.jpg","src/imgs/깐풍새우깡.jpg","src/imgs/꼬꼬스낵.jpg","src/imgs/썬칩.jpg","src/imgs/오감자.jpg","src/imgs/오사쯔.jpg"
		,"src/imgs/차카니.jpg","src/imgs/카라멜땅콩.jpg","src/imgs/콘초.jpg","src/imgs/팅쵹.jpg","src/imgs/포카칩.jpg","src/imgs/피쉬스낵.jpg","src/imgs/허니버터칩.jpg"};



public SnackView(){
for (int i = 0; i <14; i++) {
	snackname[i] = new JButton(s_label[i],new ImageIcon(s_icon[i]));
	}
}

void addLayout() {
	JPanel snackmenu = new JPanel();
	
	snackmenu.setLayout(new GridLayout(3,5));
	for (int i = 0; i < 14; i++) {
		snackmenu.add(snackname[i]);
	}
	
	add(snackmenu);
	
	
	
}
}
