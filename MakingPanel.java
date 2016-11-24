import java.awt.*;
import javax.swing.*;

public class MakingPanel extends JPanel{

	private MainPanel		panMainPanel;
	private PlayScreen		panPlayScreen;
	private Credit			panCredit;
	private ImageIcon home = new ImageIcon("img/Home.png");
	private BaseballFrame frame;
	
	public MakingPanel(BaseballFrame fr) {
		frame = fr;
		
		removeAll();
		panMainPanel = new MainPanel(this);
		this.add(panMainPanel);
		revalidate();
		repaint();
		
	}
	
	public void setFrameSize(Dimension d) {
		frame.setSize(d);
	}
	
	// get, set
	public ImageIcon getHomeIcon() {
		Image img = home.getImage();
		Image img_m = img.getScaledInstance(50, 50,Image.SCALE_SMOOTH);
		return new ImageIcon(img_m); 
	}
	
	public void exeMainPanel(){
		removeAll();
		panMainPanel = new MainPanel(this);
		this.add(panMainPanel);
		revalidate();
		repaint();
	}
	
	public void exe1pPanel(){
		removeAll();
		panPlayScreen = new PlayScreen(this, 1);
		panPlayScreen.setLayout(null);
		panPlayScreen.setPreferredSize(new Dimension(1000,450));
		this.add(panPlayScreen);
		revalidate();
		repaint();
	}
	
	public void exe2pPanel() {
		removeAll();
		panPlayScreen = new PlayScreen(this, 2);
		panPlayScreen.setLayout(null);
		panPlayScreen.setPreferredSize(new Dimension(1200,450));
		this.add(panPlayScreen);
		revalidate();
		repaint();
	}
	
	public void exeCreditPanel () {
		removeAll();
		panCredit = new Credit(this);
		this.add(panCredit);
		revalidate(); //±‚¥…¿∫?
		repaint();
	}
	
}
