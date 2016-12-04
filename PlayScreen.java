import java.awt.*;
import javax.swing.*;
import java.awt.event.*;

/*
 * ���� V.4 
 * PlayOperation ���ְ� PlayScreen ���� ó���ϴ� ������ �ذ���.
 * Constructor �������� ��游 ����� Constructor ���� 
 * 1P�϶��� onePlayer, 2P �϶��� twoPlayer �޼ҵ带 ȣ���ؼ� ������Ʈ���� ��ġ�Ѵ�.
 * 
 * ���� V.5 
 * 2P ����� ���� ������.
 * 2P ��忡���� ���� �۾����� �ϹǷ� ���� ũ�⸦ ������.
 * constructor ���� �����ϴ� ������� �޼ҵ带 ����� �޼ҵ忡�� ó���ϴ� ����� ���ַ���.
 * �޼ҵ� ���� :
 * 1. inputDialog : ���̾�α׷� �Է� �޴� �޼ҵ�
 * 2. convertToArr : ���ڷ� �Է¹��� �Է��� �迭��, �迭�� ���ڸ����� ��������
 * 3. propChk :  propriety Check �� ���ڷ�, ������ ���� �Է� �Ǿ����� üũ�ϴ� �޼ҵ�, �����ε� ����
 * !�߿�! 4. judgement : �Էµ� ���� ��S��B ���� �Ǵ���. �����ε� ����
 * !�߿�! 5. drawBalls : GameListener class ������ �޼ҵ��, ��Ʈ����ũ �гΰ�, �� �гο� �Һ� �̹����� �����.
 * 			ù��° �Ķ���ʹ� ��Ʈ����ũ �г� �Է��ϰ�, �ι�° �Ķ���Ϳ��� �� �г� �Է��ϸ� ��. 
 * 			�׷��� �� �гο� �� �޼ҵ尡 �Һ� �̹��� �����.
 * 			��� �̹��� ũ��� �� �޼ҵ� ���� ���� �˾Ƽ� ���� �� �����.
 */

public class PlayScreen extends JPanel {

	private JPanel  gamePanel, 	// ���� ������ ���� �г�
					sBoard_S,	// 1�ο��϶� Ball ������ �г�	
					sBoard_B, 	// 1�ο��϶� Strike ������ �г�
					
					sBoard1p_S,	sBoard2p_S, // --- 2�ο��϶� ������ strike ball ������ �г�
					sBoard1p_B, sBoard2p_B,
	
					answerPanel;	// ������ �������� ������ �г�
	
	private JLabel  lblCount;	// ��ȸ ������ �����ߴ��� �� �ִ� �г�

	private JTextField	txtInput,				// 1�ο��� ���� �� �� ���� �Է�ĭ
						txtInput1, txtInput2;	// 2�ο��� �����Ҷ� ���� �Է�ĭ
	
	private JButton	btnInput,				// 1�ο� �϶� ���� �Է� ��ư
					btnInput1, btnInput2,	// 2�ο� �϶� ���� �Է� ��ư
					btnHome;				// ���� ȭ������ ���ư��� ��ư
	
	private MakingPanel making;		// ���� ������ ���� MakingPanel�� ������
	
	private Image bgimg = null;		// ��� �̹��� ���� : �켱 null�� �ʱ�ȭ

	private int nRandom,		// 1P�϶� ��ǻ�Ͱ� ������ ������ 3�ڸ�
				Strike, Ball, 	// �� ���� ��Ʈ����ũ�� �� ����
				Count,			// ��ȸ �����ߴ��� ���� ����
				
				num_1p, num_2p;	// 2�� ��忡�� �Է¹��� ������ ��
	
	// --- ���� �̹����� �ҷ��´�. (������ �гο��� ��� ����)
	private ImageIcon greenball = new ImageIcon("img/greenball.png");
	private ImageIcon yellowball = new ImageIcon("img/yellowball.png");
	private ImageIcon black = new ImageIcon("img/black.png");

/*	private JLabel greenball1, greenball2, greenball3, 
					yellowball1, yellowball2, yellowball3;
	private JLabel black1, black2, black3;*/

	private GameListener gameL;				// �׼� �����ʸ� ������
	
	private MoveBallLabel mvBall;			// ���� �����̴� �����带 ����

	private RecordPanel record1p, record2p; // ���� ����� ����
	private final int nRecordX = 200; 		// ������ size������

	// --- Constructor --- �����ڿ����� 1P�� 2P�� ���ÿ� ����ϴ°͸� �����ڿ� ����
	public PlayScreen(MakingPanel m, int nPlayer) {
		
		this.setBackground(Color.black);	// ����
		this.setLayout(null);				// ���̾ƿ� �޴��� off
		
		making = m;	// �Ķ���ͷ� �޾ƿ� making�� �ּҸ� class ���������� ����
		
		gameL = new GameListener();	//���Ӹ����� ����
		
		//---��� �̹����� �����ϱ����� �����Ϳ� ������.
		Toolkit kit = Toolkit.getDefaultToolkit();
		bgimg = kit.getImage("img/bgimg.png");
		
		setLayout(null);
		
		gamePanel = new JPanel(); 						// ���� ������ ���� �г� ����
		
		// --- Home ��ư ����
		btnHome = new JButton(making.getHomeIcon());	
		btnHome.setBounds(20, 20, 50, 50);
		btnHome.addActionListener(gameL); //���Ӹ����ʸ�  �߰�
		gamePanel.add(btnHome);
	
		
		//---1P�̸� onePlayer(), 2P �̸� twoPlayer() ���� 
		if (nPlayer == 1)
			onePlayer();
		else if (nPlayer == 2)
			twoPlayer();
	}

	private void onePlayer() { // --- ���ο��϶� ����

		making.setFrameSize(BaseballConstants.SIZE_1P); // �����ӻ����� ����
		
		gamePanel.setBounds(0 + nRecordX, 0, 800 + nRecordX, 500);
		gamePanel.setOpaque(false); // ����
		gamePanel.setLayout(null);
		add(gamePanel);

		do {
			nRandom = (int) (Math.random() * 999) + 1;
		} while (!PropChk(nRandom)); // ��ȿ�� �˻� : false�� �ٽ� ����

		System.out.println(nRandom);	// ���α׷� �ÿ� ���� �ʿ���

		// ball : scoreBoard
		sBoard_B = new JPanel();
		sBoard_B.setBounds(235, 126, 390, 75);
		sBoard_B.setBackground(Color.BLACK);
		gamePanel.add(sBoard_B);


		sBoard_S = new JPanel();
		sBoard_S.setBounds(235, 51, 390, 75);
		sBoard_S.setBackground(Color.BLACK);
		gamePanel.add(sBoard_S);

		Count = 0;
		lblCount = new JLabel("Count = " + Count);
		lblCount.setFont(new Font("Verdana", Font.BOLD, 20));
		lblCount.setForeground(Color.WHITE);
		lblCount.setBounds(225, 385, 350, 20);
		lblCount.setHorizontalAlignment(SwingConstants.CENTER);	//JLabel���� ���ڰ� ���� ��ġ
		lblCount.setVerticalAlignment(SwingConstants.CENTER);
		gamePanel.add(lblCount);

		txtInput = new JTextField();
		txtInput.setBounds(255, 355, 200, 30);
		txtInput.addActionListener(gameL); 
		gamePanel.add(txtInput);

		btnInput = new JButton("Confirm");
		btnInput.setBounds(465, 355, 100, 30);
		btnInput.addActionListener(gameL);
		btnInput.setFont(new Font("Verdana", Font.PLAIN, 15));
		gamePanel.add(btnInput);


		// record
		record1p = new RecordPanel();
		record1p.setBounds(0, 10, 200, 450);
		this.add(record1p);

	}

	private void twoPlayer() { // 2P�� ���

		making.setFrameSize(BaseballConstants.SIZE_2P);

		// gamePanel 2p size
		gamePanel.setBounds(0 + nRecordX, 0, 800 + nRecordX + nRecordX, 500);	
		gamePanel.setOpaque(false); // ����
		gamePanel.setLayout(null);
		add(gamePanel);

		// record
		record1p = new RecordPanel();
		record1p.setBounds(0, 10, 200, 450);
		this.add(record1p);

		record2p = new RecordPanel();
		record2p.setBounds(1000, 10, 200, 450);
		this.add(record2p);

		Count = 0;
		lblCount = new JLabel("Count = " + Count);
		lblCount.setFont(new Font("Verdana", Font.BOLD, 20));
		lblCount.setForeground(Color.BLACK);
		lblCount.setBounds(225, 200, 350, 20);
		lblCount.setHorizontalAlignment(SwingConstants.CENTER);
		lblCount.setVerticalAlignment(SwingConstants.CENTER);
		gamePanel.add(lblCount);

		txtInput1 = new JTextField();
		txtInput1.setBounds(50, 355, 100, 30);
		txtInput1.addActionListener(gameL);
		gamePanel.add(txtInput1);

		txtInput2 = new JTextField();
		txtInput2.setBounds(800 - 50 - 100 - 100, 355, 100, 30);
		txtInput2.addActionListener(gameL); 
		txtInput2.setEnabled(false);
		gamePanel.add(txtInput2);

		btnInput1 = new JButton("1P Go");
		btnInput1.setBounds(50 + 100, 355, 100, 30);
		btnInput1.addActionListener(gameL);
		btnInput1.setFont(new Font("Verdana", Font.PLAIN, 12));
		gamePanel.add(btnInput1);

		btnInput2 = new JButton("2P Go");
		btnInput2.setBounds(800 - 50 - 100, 355, 100, 30);
		btnInput2.addActionListener(gameL);
		btnInput2.setFont(new Font("Verdana", Font.PLAIN, 12));
		btnInput2.setEnabled(false);
		gamePanel.add(btnInput2);

		// --- �̹��� ũ�� ���� : 2�ο����� (2�ο뿡�� �Һ��� ����� �� ����)
		Image gBall = greenball.getImage(); 									// gBall ��ü�� greenball�� �̹�����
		Image gBall_s = gBall.getScaledInstance(45, 35, Image.SCALE_SMOOTH);	// Image ��ü�� �޼ҵ� �̿��ؼ� �̹��� ũ�� �缳��
		greenball = new ImageIcon(gBall_s);										// ũ�Ⱑ �缳���� �̹����� �ٽ� �̹��� �����ܿ� �Է�

		Image yBall = yellowball.getImage();									// --- ���� ����
		Image yBall_s = yBall.getScaledInstance(45, 35, Image.SCALE_SMOOTH);
		yellowball = new ImageIcon(yBall_s);

		Image blk = black.getImage();
		Image blk_s = blk.getScaledInstance(45, 35, Image.SCALE_SMOOTH);
		black = new ImageIcon(blk_s);

		sBoard1p_S = new JPanel();
		sBoard1p_S.setBounds(235, 75, 52 * 3, 37);
		sBoard1p_S.setBackground(Color.BLACK);
		gamePanel.add(sBoard1p_S);

		sBoard1p_B = new JPanel();
		sBoard1p_B.setBounds(235, 140, 52 * 3, 37);
		sBoard1p_B.setBackground(Color.BLACK);
		gamePanel.add(sBoard1p_B);

		sBoard2p_S = new JPanel();
		sBoard2p_S.setBounds(445, 75, 52 * 3, 37);
		sBoard2p_S.setBackground(Color.BLACK);
		gamePanel.add(sBoard2p_S);

		sBoard2p_B = new JPanel();
		sBoard2p_B.setBounds(445, 140, 52 * 3, 37);
		sBoard2p_B.setBackground(Color.BLACK);
		gamePanel.add(sBoard2p_B);

		// �Է� �ޱ�
		num_1p = inputDialog(1);
		num_2p = inputDialog(2);

	}

	//------���̾�α׷� �Է� �޴� �޼ҵ� -------//
	private int inputDialog(int p) {	// �Ķ���� p�� ���� �÷��̾��� ���� �޴°���
		
		int n;						//�Է¹��� �� ���� ����
		String s = new String("");	//���� �߸� �Է����� ���, �߸��Է��϶�� �޼��� ����

		while (true) {
			String result = JOptionPane.showInputDialog(s + p + "P�� ���� �Է��Ͻÿ�");	//ó������ s�� ��������Ƿ� �߸��Է��ߴٴ� �޼����� �ȳ���
			
			if (PropChk(result) == false) { 			// PropChk() ��ȿ�� �˻� �޼ҵ� �̿�. �߸��� �Է� ������ �ٽ� �Է¹���
				s = new String("�߸� �Է��ϼ̽��ϴ�.\n");		// s �� ���ڿ��� �ٲ㼭 �߸��Է��ߴٴ� �޼��� ���
				continue;								// ���� �ڵ� �����ϰ� �� ��.
			}
			n = Integer.parseInt(result);				// ��ȿ�� �� �� ���, n�� �Է� �� �� ����
			System.out.println("input" + p + "P:" + n);	// ���α׷� �ÿ� ���� ���� �ڵ�
			return n;
		}
	}
	//------------------------------------//
	
	// ----- ���ڸ����� �迭�� �ٲ��ִ� �޼ҵ� ------//
	private int[] convertToArr(int n) {
		int arr[] = new int[3];	// ��ĭ¥�� �迭 ���� (C���� ����� �ٸ��ٴ°� ����)
		arr[2] = n / 100;		// �迭�� ��ȯ
		arr[1] = (n / 10) % 10;
		arr[0] = n % 10;
		return arr;				// �迭�� ��ȯ
	}
	// ----- ���ڸ����� �迭�� �ٲ��ִ� �޼ҵ� ------//

	
	//----------��ȿ�� �˻�---------//
	//�������� �����Ǵ� ����, �ԷµǴ� ���� ��ȿ�� ������ Ȯ���ϰ�
	//��ȿ�ϸ� true, ��ȿ�ϸ� false return
	private boolean PropChk(int in) { 	// ������ �Ķ������ ���
		int[] n = convertToArr(in);		// �迭�� �ٲ���
		return PropChk(n); 				// �ߺ��Ǵ� �ڵ带 ���̱� ���� �������̵��� �޼ҵ� ���
	}
	
	private boolean PropChk(String s) { // ���ڿ��� �Ķ������ ���
		int n;
		try { 							// ParseInt�� ���� ���ڿ��� ���� �ƴѰ�� NumberFormatException�� ��´�.
			n = Integer.parseInt(s);
		}catch(NumberFormatException e){// ��ȿ�� ���ڿ��̹Ƿ� false �����Ѵ�.
	        return false;
	    } 
		return PropChk(n);				//��ȿ�� ���ڿ��ϰ�� ���� �Ǵ��ؼ� true or false �������ش�.
		
	}

	private boolean PropChk(int[] in) { //�迭�� �Ķ������ ���
		int[] n = in;
		return !(n[2] == 0 || n[1] == 0 || n[0] == 0			// ���� 0�� ���� ���
				|| (n[2] * 100 + n[1] * 10 + n[0]) < 111 		// ���ڸ� ���� �ƴѰ��
				|| n[2] == n[1] || n[1] == n[0] || n[0] == n[2] //�ߺ��Ǵ� ���� �ִ°��
				|| (n[2] * 100 + n[1] * 10 + n[0]) > 999 ); 	//���ڸ� ���� �Ѵ� ���
	}
	//---------��ȿ�� �˻�----------//


	//------ ���� �˻� �޼ҵ�-------//
	//�� ���� �Է¹ް� ���ؼ� ��S ��B ���� �Ǵ�����
	private void judgement(int in, int set) {
		
		int[] arrSet = new int[3];	// ������ �迭 ����
		int[] arrIn = new int[3];
		
		Strike = 0;		//	Strike�� Ball�� 0����  �ʱ�ȭ ���ش�.
		Ball = 0;
		
		arrSet = convertToArr(in);	//	���� �Է¹��� ������ �Ķ���͸� �迭�� �ٲ���
		arrIn = convertToArr(set);
		
		for (int i = 0; i <= 2; i++) {
			for (int j = 0; j <= 2; j++) {
				if (arrIn[i] == arrSet[j] && i != j) {	//	���� ���� ���Դµ� ��ġ�� �ٸ���� : Ball++
					Ball++;
				}
			}
			if (arrIn[i] == arrSet[i]) {
				Strike++;								//	�������� ��ġ�� �������  : Strike++
			}
		}
	}
	//------ ���� �˻� �޼ҵ�-------//
	
	protected void paintComponent(Graphics g) {
		g.drawImage(bgimg, 10 + nRecordX, 10, 780, 440, this);	//��� �׸���
	}

	private class GameListener implements ActionListener {
		
		int flag=0;		//	���ο��ϋ� = 0, ���ο� 1P �����϶� = 1, 2P �����϶� = 2

		public void actionPerformed(ActionEvent event) {
			
			int nInput;		//	�Է¹��� �� �����
			
			Object obj = event.getSource();

			if (obj == txtInput || obj == btnInput) {	//	event �� �°��� txtInput �̳� btnInput �϶� : 1�ο� ��ư��
	
				if (PropChk(txtInput.getText())) {		//	�켱 ��ȿ���˻���� �ϰ�
					
					nInput = Integer.parseInt(txtInput.getText());	//	��ȿ�ϴٸ� �Է¹��� �� ����

					judgement(nInput, nRandom); 					//	��Ʈ����ũ �� ����
					Count++;										//	�õ� Ƚ�� 1 �ø�

					record1p.addText(nInput, Strike, Ball);			//	�õ��� ����� ����� ���ڵ� �гο� ���� �Է°��� ���� �˷��ش�. (�Է°�, ��Ʈ����ũ ��, �� �� )

					drawBalls(sBoard_S, sBoard_B);					//	�����ǿ� ���̵������� �Ѵ�. �Ķ���ͷ� ������ �гε��� �Ѱ��ش�.
					lblCount.setText("Count = " + Count);			//	Count �� ���� ������

				}
				else {
					JOptionPane.showMessageDialog(null, "�ùٸ� ���� �Է��Ͻʽÿ�!");	//	��ȿ���� ���� �� �Է��ϸ� , ���̾�α� �޼����� ����ش�.
				}
			}

			// ���⼭���� 2P
			if (obj == btnInput1 || obj == txtInput1) {		//	2�ο��϶� 1P�� ��ư�� ���� ���
				
				flag = 1;
				if (PropChk(txtInput1.getText())) {					//	��ȿ�� �˻�
					int n = Integer.parseInt(txtInput1.getText());	//	��ȿ�ϸ� �� ����
					
					judgement(n, num_2p);							//	��Ʈ����ũ, �� ����
					//System.out.println(Strike + " " + Ball);		//	�ý��� ������
					
					drawBalls(sBoard1p_S, sBoard1p_B);				//	�Һ� �׸���
					
					Count++;										//	Ƚ�� ����, �׸��� ���
					lblCount.setText("Count = " + Count);

					record1p.addText(n, Strike, Ball);				//	���ڵ� �гο� ����� �߰��մϴ�.
					
					//	--- 1P �÷��̾��� �õ��� �Ϸ�Ǹ� 2P�� Ȱ��ȭ�ǰ�, 1P�� ��Ȱ��ȭ �˴ϴ�.
					txtInput1.setEnabled(false);
					btnInput1.setEnabled(false);
		            txtInput2.setEnabled(true);
		            btnInput2.setEnabled(true);
				} else {	//	---	��ȿ���� ������ ������ ���̾�α� �޼��� �����ϴ�.
					//System.out.println("1P not judging");	//	������
					JOptionPane.showMessageDialog(null, "��ȿ�� ���� �Է��Ͻʽÿ�!");
				}
			}

			if (obj == btnInput2 || obj == txtInput2) {		//	���� 1P ����� �Ϲ��� ����
				
				flag = 2;
				if (PropChk(txtInput2.getText())) {
					int n = Integer.parseInt(txtInput2.getText());
					
					judgement(n, num_1p);
					//System.out.println(Strike + " " + Ball);
					
					drawBalls(sBoard2p_S, sBoard2p_B);
					
					Count++;
					lblCount.setText("Count = " + Count);

					record2p.addText(n, Strike, Ball);
					
					txtInput1.setEnabled(true);
					btnInput1.setEnabled(true);
					txtInput2.setEnabled(false);
					btnInput2.setEnabled(false);
				} else {
					//System.out.println("2P not judging");
					JOptionPane.showMessageDialog(null, "��ȿ�� ���� �Է��Ͻʽÿ�!");
				}
			}

			// home ��ư
			if (obj == btnHome) {	//	Ȩ��ư�� ������ MakingPanel���� MainPanel�� ���� ����.
				making.exeMainPanel();
			}

		}
		
		//-----------�̰����� ���� �����̴� ���� �����ִ� �޼ҵ� --------//
		private void moveBall (int n) {
			
			JLabel lblWin, lblWin2;
			
			// ������ ����
			// -----------------------------------------
			if (n == 0) {lblWin = new JLabel("YOU");}
			else  {
				lblWin = new JLabel(n + "P");
			}
			lblWin.setFont(new Font("Verdana", Font.BOLD, 100));
			lblWin.setBounds(350, -50, 400, 400);
			lblWin.setForeground(Color.magenta);
			lblWin2 = new JLabel("WIN!");
			lblWin2.setFont(new Font("Verdana", Font.BOLD, 100));
			lblWin2.setBounds(350, 100, 400, 400);
			lblWin2.setForeground(Color.magenta);
			answerPanel = new JPanel();
			answerPanel.setLayout(null);
			mvBall = new MoveBallLabel();
			mvBall.setBounds(0, -100, 200, 200);
			answerPanel.add(mvBall);
			mvBall.start();
			answerPanel.add(lblWin);
			answerPanel.add(lblWin2);
			gamePanel.remove(lblCount);
			gamePanel.remove(txtInput);
			gamePanel.remove(btnInput);
			gamePanel.remove(sBoard_S);
			gamePanel.remove(sBoard_B);
			answerPanel.setBounds(0 + nRecordX, 0, 1000 + nRecordX, 500);
			answerPanel.setBackground(new Color(29, 29, 27));
			add(answerPanel);
			repaint();
			gamePanel.repaint();
			
			return;
		}
		//-----------�̰����� ���� �����̴� ���� �����ִ� �޼ҵ� --------//
		
		
		//------------�����ǿ� ���� �׷��ִ� �żҵ�------------//
		private void drawBalls(JPanel st, JPanel bl) {
			
			JPanel strike = st;	//	��Ʈ����ũ, ���� �������� �ּ� ����
			JPanel ball = bl; 
			
			strike.removeAll();	//	���� �ִ� �ҵ��� �� ����
			ball.removeAll();
			
			strike.setLayout(new FlowLayout(FlowLayout.LEFT,0,0));	//	�ҵ��� ���ʺ��� ������ ��.
			ball.setLayout(new FlowLayout(FlowLayout.LEFT,0,0));
			
			
			JLabel[] gBall = new JLabel[3];	//	���� �� �������� ���� �� �����Ƿ� �迭�� ����
			JLabel[] yBall = new JLabel[3];
			
			
			for (int i = 0; i <= 2; i++) {	//	�켱 �����ϰ� add ����.
				gBall[i] = new JLabel(greenball);
				ball.add(gBall[i]);
				gBall[i].setVisible(true);
				yBall[i] = new JLabel(yellowball);
				strike.add(yBall[i]);
				yBall[i].setVisible(true);
			}
			
			
			for (int i = 0; i <= 2; i++) {							//	��Ʈ����ũ, ���� ���� ���� � ������ �Ǵ���.
				if (i + 1 <= Ball) { gBall[i].setVisible(true); }	//	��� ���̰� ���� ���Ѵ�.
				else { gBall[i].setVisible(false); }
				if (i + 1 <= Strike) { yBall[i].setVisible(true); }
				else { yBall[i].setVisible(false);}
			}
			
			strike.revalidate();	//	Refresh ����.
			strike.repaint();
			ball.revalidate();
			ball.repaint();
			
			if (Strike == 3) { moveBall(flag); }	//	���� ���ӿ��� �̰������, �¸� ȭ�� �����.

			// ���� �ڵ�� goto 100
		}
		//------------�����ǿ� ���� �׷��ִ� �żҵ�------------//

	}




} // PlayScreen class


/* 100 :
 * 
 * if (Ball == 0) {

black1 = new JLabel("", black, SwingConstants.CENTER);
black2 = new JLabel("", black, SwingConstants.CENTER);
black3 = new JLabel("", black, SwingConstants.CENTER);

ball.add(black1);
ball.add(black2);
ball.add(black3);
}

if (Ball == 1) {

greenball1 = new JLabel("", greenball, SwingConstants.CENTER);
black1 = new JLabel("", black, SwingConstants.CENTER);
black2 = new JLabel("", black, SwingConstants.CENTER);

ball.add(greenball1);
ball.add(black1);
ball.add(black2);
}
if (Ball == 2) {
greenball1 = new JLabel("", greenball, SwingConstants.CENTER);
greenball2 = new JLabel("", greenball, SwingConstants.CENTER);
black1 = new JLabel("", black, SwingConstants.CENTER);
ball.add(greenball1);
ball.add(greenball2);
ball.add(black1);
}
if (Ball == 3) {
greenball1 = new JLabel("", greenball, SwingConstants.CENTER);
greenball2 = new JLabel("", greenball, SwingConstants.CENTER);
greenball3 = new JLabel("", greenball, SwingConstants.CENTER);
ball.add(greenball1);
ball.add(greenball2);
ball.add(greenball3);
}
if (Strike == 0) {

black1 = new JLabel("", black, SwingConstants.CENTER);
black2 = new JLabel("", black, SwingConstants.CENTER);
black3 = new JLabel("", black, SwingConstants.CENTER);

strike.add(black1);
strike.add(black2);
strike.add(black3);
}
if (Strike == 1) {

yellowball1 = new JLabel("", yellowball, SwingConstants.CENTER);
black1 = new JLabel("", black, SwingConstants.CENTER);
black2 = new JLabel("", black, SwingConstants.CENTER);

strike.add(yellowball1);
strike.add(black1);
strike.add(black2);

}
if (Strike == 2) {
yellowball1 = new JLabel("", yellowball, SwingConstants.CENTER);
yellowball2 = new JLabel("", yellowball, SwingConstants.CENTER);
black1 = new JLabel("", black, SwingConstants.CENTER);
strike.add(yellowball1);
strike.add(yellowball2);
strike.add(black1);
}
if (Strike == 3) {
yellowball1 = new JLabel(yellowball);
yellowball2 = new JLabel(yellowball);
yellowball3 = new JLabel(yellowball);
strike.add(yellowball1);
strike.add(yellowball2);
strike.add(yellowball3);
moveBall(flag);

}*/
