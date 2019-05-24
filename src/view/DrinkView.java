package view;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.*;


public class DrinkView extends JPanel {
/*
 * 갈아만든배 망고스무디 밀키스 봉봉 사이다 아메리카노
청포도스무디 카라멜마끼아또 카페라떼 콜라
토레타 환타
 */
	JButton [] drinkname = new JButton[12];
	String [] d_label= {"<html>갈아만든 배<br/>1200원</html>","<html>망고스무디<br/>2000원</html>","<html>밀키스<br/>1200원</html>","<html>봉봉<br/>1500원</html>","<html>사이다<br/>1200원</html>","<html>아메리카노<br/>2000원</html>"
			,"<html>청포도스무디<br/>2000원</html>","<html>카라멜마끼아또<br/>3000원</html>","<html>카페라떼<br/>2500원</html>","<html>콜라<br/>1200원</html>","<html>토레타<br/>1500원</html>","<html>환타<br/>1200원</html>"};
	String [] d_icon = {"src/imgs/갈아만든배.jpg","src/imgs/망고스무디.jpg","src/imgs/밀키스.jpg","src/imgs/봉봉.jpg","src/imgs/사이다.jpg","src/imgs/아메리카노.jpg"
			,"src/imgs/청포도스무디.jpg","src/imgs/카라멜마끼아또.jpg","src/imgs/카페라떼.jpg","src/imgs/콜라.jpg","src/imgs/토레타.jpg","src/imgs/환타.jpg"};
	
	public DrinkView() {
		for (int i = 0; i < 12; i++) {
			drinkname[i] = new JButton(d_label[i],new ImageIcon(d_icon[i]));
		}
		addLayout();
		eventProc();
	}
	
	void addLayout() {
		JPanel drinkmenu = new JPanel();
		
		drinkmenu.setLayout(new GridLayout(4,4));
		for (int i = 0; i < 12; i++) {
			drinkmenu.add(drinkname[i]);
		}
		
		add(drinkmenu);
	}
	
	public void eventProc() {
		
	}
	
}
