import java.awt.*;
import javax.swing.*;

public class BaseballFrame extends JFrame {				//패널을 만드는 클래스
	
	BaseballFrame() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setTitle("Baseball Game");
		
		MakingPanel primary = new MakingPanel(this);
		getContentPane().add(primary);
		pack();
		setVisible(true);
	}
}
