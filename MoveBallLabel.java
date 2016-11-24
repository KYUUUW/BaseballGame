import java.awt.*;
import javax.swing.*;

public class MoveBallLabel extends JLabel implements Runnable{

	ImageIcon icon;
	Thread th;
	
	public MoveBallLabel () {
		icon = new ImageIcon("img/fireball.jpg");
		Image img = icon.getImage();
		Image img_m = img.getScaledInstance(50, 50,Image.SCALE_SMOOTH);
		icon = new ImageIcon(img_m);
		setIcon(icon);
		
	}
	
	public void start() {
		if (th == null) {
			th = new Thread(this);
		}
		th.start();
	}

	@Override
	public void run() {
		try {
			for (int i = 1; i <= 100 ; i++) {
				setBounds(i, i, 500, 500);
				Thread.sleep(10);
				icon = new ImageIcon("img/fireball.jpg");
				Image img = icon.getImage();
				Image img_m = img.getScaledInstance(50+i*2, 50+i*2,Image.SCALE_SMOOTH);
				icon = new ImageIcon(img_m);
				setIcon(icon);
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
	}
}

