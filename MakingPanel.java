import java.awt.*;
import javax.swing.*;
/*
 * MakigPanel은 게임 화면들의 기능에서 공통적으로 필요한 기능을 제공하고,
 * 화면간의 이동을 가능캐 한다. 
 */
public class MakingPanel extends JPanel{

	private MainPanel		panMainPanel;	//	메인화면
	private PlayScreen		panPlayScreen;	//	게임화면
	private Credit			panCredit;		//	크레딧화면
	private ImageIcon home = new ImageIcon("img/Home.png");		//	메인화면으로 돌아가기 위한 아이콘
	
	private BaseballFrame frame;	//	프레임 크기 조절을 위해 상위의 BaseBallFrame 클래스 불러올거임.
	
	public MakingPanel(BaseballFrame fr) {	//	프레임 사이즈 조절때문에 BaseballFrame 객체를 파라미터로 받음
		frame = fr; //	업콜
		
		removeAll();						//	깨끗이 하고
		panMainPanel = new MainPanel(this);	//	메인패널 생성
		this.add(panMainPanel);				//	현재 패널에 추가함.
		revalidate();						//	refresh
		repaint();
		
	}
	
	public void setFrameSize(Dimension d) {		//	프레임의 크기 조절하는 메소드
		frame.setSize(d);						//	JPanel 클래스의 setSize 메소드 이용.
	}

	public ImageIcon getHomeIcon() {	//	HomeIcon은 게임화면들에 공통적으로 필요한 것이기 때문에 get으로 손쉽게 제공해줌.
		Image img = home.getImage();									//	이미지 갖고온다.
		Image img_m = img.getScaledInstance(50, 50,Image.SCALE_SMOOTH);	//	크기 조정.
		return new ImageIcon(img_m); 									//	이미지아이콘 리턴
	}
	
	public void exeMainPanel(){
		removeAll();							//	깨끗이 하고
		panMainPanel = new MainPanel(this);		//	패널 생성, 파라미터로 자신을 줘 업콜이 가능하도록 한다.
		this.add(panMainPanel);					//	현재 패널에 add 함.
		revalidate();							//	refresh
		repaint();
	}
	
	public void exe1pPanel(){									//	위와 과정은 같음.
		removeAll();
		panPlayScreen = new PlayScreen(this, 1);				//	1인용은 1을 파라미터로 줌
		panPlayScreen.setLayout(null);
		panPlayScreen.setPreferredSize(new Dimension(1000,450));//	패널의 크기 변경 (record때문에 커지므로)
		this.add(panPlayScreen);
		revalidate();
		repaint();
	}
	
	public void exe2pPanel() {									//	위와 완전히 같음. 2인용
		removeAll();
		panPlayScreen = new PlayScreen(this, 2);
		panPlayScreen.setLayout(null);
		panPlayScreen.setPreferredSize(new Dimension(1200,450));
		this.add(panPlayScreen);
		revalidate();
		repaint();
	}
	
	public void exeCreditPanel () {								//	위와 같음. 크레딧
		removeAll();
		panCredit = new Credit(this);
		this.add(panCredit);
		revalidate(); //기능은?
		repaint();
	}
	
}
