package View;

import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.*;

public class MealView  extends JPanel{
/*
 * 김치볶음밥 돈까스 떡라면 만두라면 우동 제육덮밥
치즈라면 크림스파게티 토마토스파게티

 */
	JButton[] mealname =new JButton[14];
	String [] m_label = {"김치볶음밥<br>4500원","돈까스<br>5000원", "떡라면<br>3500원", "만두라면<br>4000원", "우동<br>3000원", 
			"제육덮밥<br>4500원", "치즈라면<br>3500원", "크림스파게티<br>5000원", "토마토스파게티<br>5000원"};
	String [] m_icon = {
			"src/imgs/김치볶음밥.jpg", "src/imgs/돈까스.jpg", "src/imgs/떡라면.jpg", "src/imgs/만두라면.jpg", 
			"src/imgs/우동.jpg" , "src/imgs/제육덮밥.jpg", "src/imgs/치즈라면.jpg", "src/imgs/크림스파게티.jpg", "src/imgs/토마토스파게티.jpg"};
	
	
	public MealView() {
		for (int i = 0; i <9; i++) {
			mealname[i] = new JButton(m_label[i],new ImageIcon(m_icon[i]));
		}
	}
	
	void addLayout() {
		JPanel mealmenu = new JPanel();
		
		mealmenu.setLayout(new GridLayout(2,5));
		for (int i = 0; i < 9; i++) {
			mealmenu.add(mealname[i]);
		}
		
		add(mealmenu);
	}
	
	
}
