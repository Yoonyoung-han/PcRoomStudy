import java.awt.FlowLayout;
import java.awt.Font;
import java.util.Calendar;

import javax.swing.JFrame;
import javax.swing.JLabel;

public class DigitalClock extends JFrame implements Runnable{
	private Thread thread;
	private JLabel lbDate;

	public JLabel MakeDigitalClock(){

		setLayout(new FlowLayout());

		lbDate = new JLabel();
		lbDate.setFont(new Font("맑은고딕",Font.PLAIN, 20));

		if(thread == null){
			
			//this의 의미는 Runnable이 구현된 객체를 뜻함(DigitalClock)
			thread = new Thread(this);
			thread.start();
		}
		return lbDate;
//		add(Jlabel);
//		setBounds(100,100,400,100);
//		setVisible(true);
//		setDefaultCloseOperation(EXIT_ON_CLOSE);
	}
//	public static void main(String[] args) {
//		JFrame.setDefaultLookAndFeelDecorated(true);
//		new DigitalClock();
//	}
	public void run(){
		while(true){
			Calendar cal = Calendar.getInstance();
			String now ="<html>"+ cal.get(Calendar.YEAR)+"-"+
					(cal.get(Calendar.MONTH)+1)+"-"+
					cal.get(Calendar.DATE)+"<br>"+
					cal.get(Calendar.HOUR)+":"+
					cal.get(Calendar.MINUTE)+":"+
					cal.get(Calendar.SECOND)+"</html>";
			lbDate.setText(now);
			try{
				Thread.sleep(1000);
			}catch(InterruptedException e){
				e.printStackTrace();
			}
		}
	}
	
}
