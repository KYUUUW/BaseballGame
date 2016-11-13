import java.awt.*;
import javax.swing.*;

public class PlayScreen extends JPanel{
	
	private JPanel leftPanel, rightPanel;
	private JPanel scoreBoard;
	private JLabel lblResult, lblCount, lblRecord;
	private JTextField txtInput;
	private JButton btnInput;
	private MakingPanel making;
	
	public PlayScreen (MakingPanel m) {
		
		this.setPreferredSize(new Dimension(800,450));
		this.setBackground(Color.white);
		this.setLayout(null);
		
		making = m;
		
		leftPanel = new JPanel();
		leftPanel.setBounds(5, 5, 390, 440);
		leftPanel.setBackground(Color.MAGENTA);
		leftPanel.setLayout(null);
		add(leftPanel);
		
		rightPanel = new JPanel();
		rightPanel.setBounds(405, 5, 390, 440);
		rightPanel.setBackground(Color.CYAN);
		rightPanel.setLayout(null);
		add(rightPanel);
		
		scoreBoard = new JPanel();
		scoreBoard.setBounds(20, 50, 350, 120);
		scoreBoard.setBackground(Color.BLACK);
		leftPanel.add(scoreBoard);
		
		lblResult = new JLabel("[]S []B");
		lblResult.setFont(new Font("Verdana",Font.BOLD,80));
		lblResult.setForeground(Color.YELLOW);
		lblResult.setHorizontalAlignment(SwingConstants.CENTER);
		lblResult.setVerticalAlignment(SwingConstants.CENTER);
		scoreBoard.add(lblResult);
		
		lblCount = new JLabel("Count = 0");
		lblCount.setFont(new Font("Verdana", Font.BOLD, 20));
		lblCount.setForeground(Color.BLUE);
		lblCount.setBounds(20, 400, 350, 20);
		lblCount.setHorizontalAlignment(SwingConstants.CENTER);
		lblCount.setVerticalAlignment(SwingConstants.CENTER);
		leftPanel.add(lblCount);
		
		txtInput = new JTextField();
		txtInput.setBounds(20, 50, 250, 50);
		rightPanel.add(txtInput);
		
		btnInput = new JButton("Confirm");
		btnInput.setBounds(275, 50, 100, 50);
		btnInput.setFont(new Font("Verdana", Font.PLAIN, 15));
		rightPanel.add(btnInput);
		
		lblRecord = new JLabel("Record...");
		lblRecord.setBounds(20, 150, 350, 250);	
		lblRecord.setFont(new Font ("Verdana", Font.PLAIN, 10));
		lblRecord.setVerticalAlignment(SwingConstants.TOP);
		rightPanel.add(lblRecord);
		
	}
	

	
	
}
