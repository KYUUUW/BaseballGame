/*import java.awt.*;
import javax.swing.*;

public class PlayOperation extends JPanel{
	
	private PlayScreen scr1, scr2;
	private MakingPanel making;
	
	public PlayOperation (MakingPanel m ,int p){ //파라미터는 플레이어 수
		
		making = m;
		
		if (p == 1){
			scr1 = new PlayScreen(m);
			add(scr1);
		}
		else if (p == 2){
			setLayout(new BorderLayout());
			setPreferredSize(new Dimension(800,900));
			
			scr1 = new PlayScreen(m);
			add(scr1,BorderLayout.NORTH);
			
			scr2 = new PlayScreen(m);
			add(scr2,BorderLayout.SOUTH);
			
		}
		else{
			System.out.println("Error");
		}
		
	}
}
*/
