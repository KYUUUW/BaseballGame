import java.awt.*;
import javax.swing.*;
import java.awt.event.*;

public class Credit extends JPanel {
	
	JPanel 	kyu, yong, jung, seok;
	JLabel 	lbl;
	JButton	btnHome;
	
	MakingPanel 	making;
	GameListener 	gameL;
	
	
	public Credit (MakingPanel m) {
		making = m;
		
		this.setPreferredSize(new Dimension(800,450));
		this.setBackground(Color.WHITE);
		this.setLayout(null);
		
		making.setFrameSize(BaseballConstants.SIZE_CREDIT);
		
		gameL = new GameListener();
		
		lbl = new JLabel("Make personal Credit");
		lbl.setFont(new Font("Verdana",Font.BOLD,40));
		lbl.setBounds(100, 100, 500, 50);
		this.add(lbl); 
		
		
		btnHome = new JButton(making.getHomeIcon());
		btnHome.setBounds(20, 20, 50, 50);
		btnHome.addActionListener(gameL);
		this.add(btnHome);
		
		
	}
	
	private class GameListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			System.out.println("a");
			Object obj = e.getSource();
			
			if (obj == btnHome) {
				making.exeMainPanel();
			}
			
		}
		
	}
}
