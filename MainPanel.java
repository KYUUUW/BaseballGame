import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class MainPanel extends JPanel{
	
	//Data
	private JLabel lblTitle;
	private JButton btn1p, btn2p, btnCredit;
	private MainListener mainL;
	private MakingPanel making;
	
	int selectedMode = 0; //어떤 
	
	//Constructor
	public MainPanel(MakingPanel m) {
		
		this.setPreferredSize(new Dimension(800,450));
		this.setBackground(Color.WHITE);
		this.setLayout(null);
		
		mainL = new MainListener();
		
		lblTitle = new JLabel("Baseball Game");
		lblTitle.setBounds(200,100,400,100);
		Font Arial = new Font("Arial",Font.BOLD,50);
		lblTitle.setFont(Arial);
		lblTitle.setHorizontalAlignment(SwingConstants.CENTER);
		lblTitle.setVerticalAlignment(SwingConstants.CENTER);
		this.add(lblTitle);
		
		
		Font btnFont = new Font("Verdana", Font.PLAIN, 25);
		btn1p = new JButton("1P");
		btn1p.setFont(btnFont);
		btn1p.setBounds(100, 300, 200, 50);
		btn1p.addActionListener(mainL);
		this.add(btn1p);
		
		btn2p = new JButton("2P");
		btn2p.setFont(btnFont);
		btn2p.setBounds(300, 300, 200, 50);
		btn2p.addActionListener(mainL);
		this.add(btn2p);
		
		btnCredit = new JButton("Credit");
		btnCredit.setFont(btnFont);
		btnCredit.setBounds(500, 300, 200, 50);
		btnCredit.addActionListener(mainL);
		this.add(btnCredit);
		
		making = m;
	}
	
	
	//공간 계산용으로 만듬
	public void paintComponent(Graphics page) {
		super.paintComponent(page);
		page.drawRect(200, 100, 400, 100);
	}
	
	// 1. 리스너 클래스 만들기
	private class MainListener implements ActionListener{
		public void actionPerformed(ActionEvent event){
		
			Object obj = event.getSource();
			
			
			//업콜로 구현함..
			if (obj == btn1p){
				making.exe1pPanel();
			}else if (obj == btn2p){
				making.exe2pPanel();
			}else if (obj == btnCredit){
				making.exeCreditPanel();
			}
		
			//이벤트 를 받아온다.
		}
	}

}
