import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class MainPanel extends JPanel{
	
	//Data
	private JLabel lblTitle;
	private JButton btn1p, btn2p, btnCredit;
	private MainListener mainL;
	private MakingPanel making;
	private JPanel main;
	
	int selectedMode = 0; //어떤 
	
	private Image bgimg = null;
	
	//Constructor
	public MainPanel(MakingPanel m) {
		
		
		Toolkit kit = Toolkit.getDefaultToolkit();						//배경설정을 위한 크기준비 작업
		bgimg = kit.getImage("img/mainbg.png");							//배경 이미지를 불러온다.
		
		making = m;
		
		this.setPreferredSize(new Dimension(800+200,450));				//배경크기 설정
		this.setLayout(null);
	
		making.setFrameSize(BaseballConstants.SIZE_MAIN);				//프레임 사이즈 설정
		
		mainL = new MainListener();										//버튼을 사용하기위해 리스너를 불러온다.
																		//1P
		btn1p = new JButton(new ImageIcon("img/btn1p.png"));			//버튼을 이미지로 불러온다.
		btn1p.setBounds(300+100, 250, 200, 45);							//버튼의 크기
		btn1p.addActionListener(mainL);									//리스너와 연결
		this.add(btn1p);												//메인에 추가
		
		btn2p = new JButton(new ImageIcon("img/btn2p.png"));			//2P
		btn2p.setBounds(300+100, 300, 200, 45);
		btn2p.addActionListener(mainL);
		this.add(btn2p);
		
		btnCredit = new JButton(new ImageIcon("img/btncredit.png"));	//Credit
		btnCredit.setBounds(300+100, 350, 200, 45);
		btnCredit.addActionListener(mainL);
		this.add(btnCredit);
		
		main = new JPanel();											//타이틀을 위한 패널
		main.setBounds(250,120,500,70);
		main.setBackground(Color.BLACK);
		add(main);
		
		lblTitle = new JLabel("Baseball Game");							//타이틀을 위의 main패널에 넣는다.
		lblTitle.setBounds(0,0,300,70);
		Font Arial = new Font("Arial",Font.BOLD,50);
		lblTitle.setFont(Arial);
		lblTitle.setForeground(Color.WHITE);
		lblTitle.setHorizontalAlignment(SwingConstants.CENTER);
		lblTitle.setVerticalAlignment(SwingConstants.CENTER);
		main.add(lblTitle);
		
		//making = m;
	}
	
	
	protected void paintComponent(Graphics g) {							//배경을 위한 이미지 Component
		g.drawImage(bgimg, 5, 5, 1000, 440, this);
	}
	
	
	// 1. 리스너 클래스 만들기
	private class MainListener implements ActionListener{				//액션 리스너를 받은 클래스
		public void actionPerformed(ActionEvent event){					//이벤트를 받는다.
		
			Object obj = event.getSource();								//이벤트를 변수에 저장
			
			
			//업콜로 구현함..
			if (obj == btn1p){											//버튼 1P를 눌렀을 경우
				making.exe1pPanel();									//MakingPanel에서 화면을 띄운다.
			}else if (obj == btn2p){
				making.exe2pPanel();
			}else if (obj == btnCredit){
				making.exeCreditPanel();
			}
		
			//이벤트 를 받아온다.
		}
	}

}
