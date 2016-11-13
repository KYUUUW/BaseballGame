import java.awt.*;
import javax.swing.*;

public class MakingPanel extends JPanel{

	private MainPanel		panMainPanel;
	private PlayOperation	panPlayOperation;
	private Credit			panCredit;
	
	public MakingPanel() {
		
		
		panMainPanel = new MainPanel(this);
		this.add(panMainPanel);
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
		panPlayOperation = new PlayOperation(this, 1);
		this.add(panPlayOperation);
		revalidate();
		repaint();
	}
	
	public void exe2pPanel() {
		removeAll();
		panPlayOperation = new PlayOperation(this, 2);
		this.add(panPlayOperation);
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
