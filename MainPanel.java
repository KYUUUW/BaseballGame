import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class MainPanel extends JPanel{
	
	//Data
	private JLabel lblTitle;
	private JButton btn1p, btn2p, btnCredit;
	private MainListener mainL;
	private MakingPanel making;
	private JPanel main;
	
	int selectedMode = 0; //� 
	
	private Image bgimg = null;
	
	//Constructor
	public MainPanel(MakingPanel m) {
		
		
		Toolkit kit = Toolkit.getDefaultToolkit();						//��漳���� ���� ũ���غ� �۾�
		bgimg = kit.getImage("img/mainbg.png");							//��� �̹����� �ҷ��´�.
		
		making = m;
		
		this.setPreferredSize(new Dimension(800+200,450));				//���ũ�� ����
		this.setLayout(null);
	
		making.setFrameSize(BaseballConstants.SIZE_MAIN);				//������ ������ ����
		
		mainL = new MainListener();										//��ư�� ����ϱ����� �����ʸ� �ҷ��´�.
																		//1P
		btn1p = new JButton(new ImageIcon("img/btn1p.png"));			//��ư�� �̹����� �ҷ��´�.
		btn1p.setBounds(300+100, 250, 200, 45);							//��ư�� ũ��
		btn1p.addActionListener(mainL);									//�����ʿ� ����
		this.add(btn1p);												//���ο� �߰�
		
		btn2p = new JButton(new ImageIcon("img/btn2p.png"));			//2P
		btn2p.setBounds(300+100, 300, 200, 45);
		btn2p.addActionListener(mainL);
		this.add(btn2p);
		
		btnCredit = new JButton(new ImageIcon("img/btncredit.png"));	//Credit
		btnCredit.setBounds(300+100, 350, 200, 45);
		btnCredit.addActionListener(mainL);
		this.add(btnCredit);
		
		main = new JPanel();											//Ÿ��Ʋ�� ���� �г�
		main.setBounds(250,120,500,70);
		main.setBackground(Color.BLACK);
		add(main);
		
		lblTitle = new JLabel("Baseball Game");							//Ÿ��Ʋ�� ���� main�гο� �ִ´�.
		lblTitle.setBounds(0,0,300,70);
		Font Arial = new Font("Arial",Font.BOLD,50);
		lblTitle.setFont(Arial);
		lblTitle.setForeground(Color.WHITE);
		lblTitle.setHorizontalAlignment(SwingConstants.CENTER);
		lblTitle.setVerticalAlignment(SwingConstants.CENTER);
		main.add(lblTitle);
		
		//making = m;
	}
	
	
	protected void paintComponent(Graphics g) {							//����� ���� �̹��� Component
		g.drawImage(bgimg, 5, 5, 1000, 440, this);
	}
	
	
	// 1. ������ Ŭ���� �����
	private class MainListener implements ActionListener{				//�׼� �����ʸ� ���� Ŭ����
		public void actionPerformed(ActionEvent event){					//�̺�Ʈ�� �޴´�.
		
			Object obj = event.getSource();								//�̺�Ʈ�� ������ ����
			
			
			//���ݷ� ������..
			if (obj == btn1p){											//��ư 1P�� ������ ���
				making.exe1pPanel();									//MakingPanel���� ȭ���� ����.
			}else if (obj == btn2p){
				making.exe2pPanel();
			}else if (obj == btnCredit){
				making.exeCreditPanel();
			}
		
			//�̺�Ʈ �� �޾ƿ´�.
		}
	}

}
