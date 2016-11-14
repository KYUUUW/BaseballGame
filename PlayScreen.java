import java.awt.*;
import javax.swing.*;
import java.awt.event.*;

public class PlayScreen extends JPanel{
	
	private JPanel leftPanel, rightPanel;
	private JPanel scoreBoard;
	private JLabel lblResult, lblCount, lblRecord;
	private JTextField txtInput;
	private JButton btnInput;
	private MakingPanel making;
	
	private int nRandom,nInput;
	private int a,b,c,Ra,Rb,Rc,Strike,Ball,Count;
	
	private GameListener gameL;	
	
	public PlayScreen (MakingPanel m) {
		
		
		nRandom = (int)(Math.random()*999)+1;

		Ra=nRandom/100;
		Rb=(nRandom/10)%10;
		Rc=nRandom%10;
				
		System.out.println(nRandom);
		gameL = new GameListener();			//
		
		this.setPreferredSize(new Dimension(800,450));
		this.setBackground(Color.white);
		this.setLayout(null);
		
		making = m;
		
		leftPanel = new JPanel();
		leftPanel.setBounds(5, 5, 390, 440);
		leftPanel.setBackground(Color.WHITE);
		leftPanel.setLayout(null);
		add(leftPanel);
		
		rightPanel = new JPanel();
		rightPanel.setBounds(405, 5, 390, 440);
		rightPanel.setBackground(new Color(150,150,200));
		rightPanel.setLayout(null);
		add(rightPanel);
		
		scoreBoard = new JPanel();
		scoreBoard.setBounds(20, 50, 350, 120);
		scoreBoard.setBackground(new Color(130,60,100));
		leftPanel.add(scoreBoard);
		
		lblResult = new JLabel("[]S []B");
		lblResult.setFont(new Font("Verdana",Font.BOLD,80));
		lblResult.setForeground(Color.YELLOW);
		lblResult.setHorizontalAlignment(SwingConstants.CENTER);
		lblResult.setVerticalAlignment(SwingConstants.CENTER);
		scoreBoard.add(lblResult);
		
		Count=0;
		lblCount = new JLabel("Count = "+Count);
		lblCount.setFont(new Font("Verdana", Font.BOLD, 20));
		lblCount.setForeground(Color.BLUE);
		lblCount.setBounds(20, 400, 350, 20);
		lblCount.setHorizontalAlignment(SwingConstants.CENTER);
		lblCount.setVerticalAlignment(SwingConstants.CENTER);
		leftPanel.add(lblCount);
		
		txtInput = new JTextField();
		txtInput.setBounds(20, 50, 250, 50);
		txtInput.addActionListener(gameL);	//
		rightPanel.add(txtInput);
		
		btnInput = new JButton("Confirm");
		btnInput.setBounds(275, 50, 100, 50);
		btnInput.addActionListener(gameL);
		btnInput.setFont(new Font("Verdana", Font.PLAIN, 15));
		rightPanel.add(btnInput);
		
		lblRecord = new JLabel("Record...");
		lblRecord.setBounds(20, 150, 350, 250);	
		lblRecord.setFont(new Font ("Verdana", Font.PLAIN, 10));
		lblRecord.setForeground(Color.BLACK);
		lblRecord.setVerticalAlignment(SwingConstants.TOP);
		rightPanel.add(lblRecord);
		
	}
	
	private class GameListener implements ActionListener{
		public void actionPerformed(ActionEvent event){
		
			Object obj = event.getSource();
			
			if (obj == txtInput || obj == btnInput) {			//obj가 txtinput,btninput 이벤트를 받았을때
				nInput = Integer.parseInt(txtInput.getText());		//text에 입력된 값을 nInput에 넣는다.
				
				Strike=0;
				Ball=0;
				
				a=nInput/100;
				b=(nInput/10)%10;
				c=nInput%10;
				
				if(Ra==a || Ra==b || Ra==c)
				{
					if(a==Ra)
					{
						Strike++;
						a=-1;
					}
					 else Ball++;
				}
				if(Rb==a || Rb==b || Rb==c)
				{
					if(b==Rb)
					{
						Strike++;
						b=-1;
					}
					else Ball++;
				}
				if(Rc==a || Rc==b || Rc==c)
				{
					if(c==Rc)
					{
						Strike++;
						c=-1;
					}
					else Ball++;
				}
				Count++;

				lblResult.setText(Strike+"S"+Ball+"B");			
				lblCount.setText("Count = "+Count);
				
				if(nInput==nRandom) 
				{
					lblResult.setText("RIGHT");
				}
			}
		}
	}
	
}
