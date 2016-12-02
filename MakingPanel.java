import java.awt.*;
import javax.swing.*;
/*
 * MakigPanel�� ���� ȭ����� ��ɿ��� ���������� �ʿ��� ����� �����ϰ�,
 * ȭ�鰣�� �̵��� ����ĳ �Ѵ�. 
 */
public class MakingPanel extends JPanel{

	private MainPanel		panMainPanel;	//	����ȭ��
	private PlayScreen		panPlayScreen;	//	����ȭ��
	private Credit			panCredit;		//	ũ����ȭ��
	private ImageIcon home = new ImageIcon("img/Home.png");		//	����ȭ������ ���ư��� ���� ������
	
	private BaseballFrame frame;	//	������ ũ�� ������ ���� ������ BaseBallFrame Ŭ���� �ҷ��ð���.
	
	public MakingPanel(BaseballFrame fr) {	//	������ ������ ���������� BaseballFrame ��ü�� �Ķ���ͷ� ����
		frame = fr; //	����
		
		removeAll();						//	������ �ϰ�
		panMainPanel = new MainPanel(this);	//	�����г� ����
		this.add(panMainPanel);				//	���� �гο� �߰���.
		revalidate();						//	refresh
		repaint();
		
	}
	
	public void setFrameSize(Dimension d) {		//	�������� ũ�� �����ϴ� �޼ҵ�
		frame.setSize(d);						//	JPanel Ŭ������ setSize �޼ҵ� �̿�.
	}

	public ImageIcon getHomeIcon() {	//	HomeIcon�� ����ȭ��鿡 ���������� �ʿ��� ���̱� ������ get���� �ս��� ��������.
		Image img = home.getImage();									//	�̹��� ����´�.
		Image img_m = img.getScaledInstance(50, 50,Image.SCALE_SMOOTH);	//	ũ�� ����.
		return new ImageIcon(img_m); 									//	�̹��������� ����
	}
	
	public void exeMainPanel(){
		removeAll();							//	������ �ϰ�
		panMainPanel = new MainPanel(this);		//	�г� ����, �Ķ���ͷ� �ڽ��� �� ������ �����ϵ��� �Ѵ�.
		this.add(panMainPanel);					//	���� �гο� add ��.
		revalidate();							//	refresh
		repaint();
	}
	
	public void exe1pPanel(){									//	���� ������ ����.
		removeAll();
		panPlayScreen = new PlayScreen(this, 1);				//	1�ο��� 1�� �Ķ���ͷ� ��
		panPlayScreen.setLayout(null);
		panPlayScreen.setPreferredSize(new Dimension(1000,450));//	�г��� ũ�� ���� (record������ Ŀ���Ƿ�)
		this.add(panPlayScreen);
		revalidate();
		repaint();
	}
	
	public void exe2pPanel() {									//	���� ������ ����. 2�ο�
		removeAll();
		panPlayScreen = new PlayScreen(this, 2);
		panPlayScreen.setLayout(null);
		panPlayScreen.setPreferredSize(new Dimension(1200,450));
		this.add(panPlayScreen);
		revalidate();
		repaint();
	}
	
	public void exeCreditPanel () {								//	���� ����. ũ����
		removeAll();
		panCredit = new Credit(this);
		this.add(panCredit);
		revalidate(); //�����?
		repaint();
	}
	
}
