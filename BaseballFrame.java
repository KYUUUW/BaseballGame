import java.awt.*;
import javax.swing.*;

public class BaseballFrame extends JFrame {
	
	BaseballFrame() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setTitle("Baseball Game");
		
		MakingPanel primary = new MakingPanel(this);
		getContentPane().add(primary);
		pack();
		setVisible(true);
		setResizable(false);
	}
}
