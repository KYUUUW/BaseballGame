import java.awt.*;
import javax.swing.*;

public class Credit extends JPanel {
	
	JPanel kyu, yong, jung, seok;
	JLabel lbl;
	MakingPanel making;
	
	public Credit (MakingPanel m) {
		
		this.setPreferredSize(new Dimension(800,450));
		this.setBackground(Color.WHITE);
		
		
		lbl = new JLabel("Make personal Credit");
		lbl.setFont(new Font("Verdana",Font.BOLD,40));
		this.add(lbl); 
		
		making = m;
		
	}
}
