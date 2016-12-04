import java.awt.*;
import javax.swing.*;
import java.awt.event.*;

public class Credit extends JPanel {
	
	private JLabel 		lblCredit, lblKyu, lblYong, lblJeong, lblSeok, lblClass, lblCopyright;
	private JButton		btnHome;
	private int			width, height;
	
	MakingPanel 	making;
	GameListener 	gameL;
	
	public Credit (MakingPanel m) {
		making = m;															//창을 띄우긴 위함
			
		this.setPreferredSize(new Dimension(800,450));						//클래스 크기 설정
		this.setBackground(Color.BLACK);									//배경 설정
		this.setLayout(null);
		
		making.setFrameSize(BaseballConstants.SIZE_CREDIT);					//창크기 불러오기
		
		gameL = new GameListener();											//리스너를 받는다.

		lblCredit = new JLabel("Credits");									//크레딧 문구
		lblCredit.setFont(new Font("Times New Roman",Font.BOLD,40));
		lblCredit.setLayout(null);
		lblCredit.setBounds(150, 50, 500, 50);
		lblCredit.setForeground(Color.white);
		lblCredit.setHorizontalAlignment(SwingConstants.CENTER);
		this.add(lblCredit); 
		
		btnHome = new JButton(making.getHomeIcon());						//홈 버튼
		btnHome.setBounds(20, 20, 50, 50);
		btnHome.addActionListener(gameL);
		this.add(btnHome);
		
		//kyu, yong, jeong, seok
		width = 500;
		height = 30;
		
		Font fnt = new Font("Times New Roman",Font.PLAIN,15);				//폰트 설정
		
		lblClass = new JLabel("JAVA PROGRAMMING");							//타이틀을 라벨로 설정
		lblClass.setBounds(150,150,width,height);
		lblClass.setHorizontalAlignment(SwingConstants.CENTER);
		lblClass.setForeground(Color.white);
		lblClass.setFont(fnt);
		add(lblClass);
		
		lblKyu = new JLabel("Lee Kyu Won / Computer Engineering / 15010815");//사람 별로 cedit을 설명한다.
		lblKyu.setBounds(150,200,width,height);
		lblKyu.setFont(fnt);
		lblKyu.setForeground(Color.white);
		lblKyu.setHorizontalAlignment(SwingConstants.CENTER);
		add(lblKyu);

		lblYong = new JLabel("Ham Ju Yong / Computer Engineering / 15010995");
		lblYong.setBounds(150,230,width,height);
		lblYong.setFont(fnt);
		lblYong.setForeground(Color.white);
		lblYong.setHorizontalAlignment(SwingConstants.CENTER);
		add(lblYong);
		
		lblJeong = new JLabel("Jeong Dahm / English Language and Literature / 14011935");
		lblJeong.setBounds(150,260,width,height);
		lblJeong.setFont(fnt);
		lblJeong.setForeground(Color.white);
		lblJeong.setHorizontalAlignment(SwingConstants.CENTER);
		add(lblJeong);
		
		lblSeok = new JLabel("Choi Eun Seok / Computer Engineering / 15010927");
		lblSeok.setBounds(150,290,width,height);
		lblSeok.setFont(fnt);
		lblSeok.setForeground(Color.white);
		lblSeok.setHorizontalAlignment(SwingConstants.CENTER);
		add(lblSeok);
		
		lblCopyright = new JLabel("\u00a9 2016 ALL RIGHTS RESERVED"); // \u00a9
		lblCopyright.setBounds(100,350,600,20);
		lblCopyright.setFont(new Font("Times New Roman",Font.PLAIN,15));
		lblCopyright.setForeground(Color.white);
		lblCopyright.setHorizontalAlignment(SwingConstants.CENTER);
		add(lblCopyright);
	} // constructor
	
	private class GameListener implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {				//버튼 리스너를 받아서
			// TODO Auto-generated method stub
			System.out.println("a");
			Object obj = e.getSource();								// 값을 obj에 넣어준다.
			
			if (obj == btnHome) {									//홈키를 누르면 홈으로 이동 한다.
				making.exeMainPanel();
			}
			
		} // actionPerformed()
	} // GameListener()
	
} // Credit 
