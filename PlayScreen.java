import java.awt.*;
import javax.swing.*;
import java.awt.event.*;

/*
 * 버전 V.4 
 * PlayOperation 없애고 PlayScreen 에서 처리하는 것으로 해결함.
 * Constructor 내에서는 배경만 만들고 Constructor 에서 
 * 1P일때는 onePlayer, 2P 일때는 twoPlayer 메소드를 호출해서 컴포넌트들을 배치한다.
 * 
 * 버전 V.5 
 * 2P 기능이 이제 구현됨.
 * 2P 모드에서는 불이 작아져야 하므로 불의 크기를 조절함.
 * constructor 에서 구현하는 방법보단 메소드를 만들어 메소드에서 처리하는 방법을 위주로함.
 * 메소드 설명 :
 * 1. inputDialog : 다이얼로그로 입력 받는 메소드
 * 2. convertToArr : 숫자로 입력받은 입력을 배열로, 배열에 한자리수로 나눠담음
 * 3. propChk :  propriety Check 의 약자로, 적절한 수가 입력 되었는지 체크하는 메소드, 오버로딩 지원
 * !중요! 4. judgement : 입력된 수가 몇S몇B 인지 판단함. 오버로딩 지원
 * !중요! 5. drawBalls : GameListener class 내부의 메소드로, 스트라이크 패널과, 볼 패널에 불빛 이미지를 띄워줌.
 * 			첫번째 파라미터는 스트라이크 패널 입력하고, 두번째 파라미터에는 볼 패널 입력하면 됨. 
 * 			그러면 그 패널에 저 메소드가 불빛 이미지 띄워줌.
 * 			대신 이미지 크기는 저 메소드 쓰기 전에 알아서 조절 해 줘야함.
 */

public class PlayScreen extends JPanel {

	private JPanel  gamePanel, 	// 게임 진행을 위하 패널
					sBoard_S,	// 1인용일때 Ball 전광판 패널	
					sBoard_B, 	// 1인용일때 Strike 전광판 패널
					
					sBoard1p_S,	sBoard2p_S, // --- 2인용일때 각각의 strike ball 전광판 패널
					sBoard1p_B, sBoard2p_B,
	
					answerPanel;	// 정답을 맞췄을때 나오는 패널
	
	private JLabel  lblCount;	// 몇회 게임을 진행했는지 세 주는 패널

	private JTextField	txtInput,				// 1인용을 실행 할 때 숫자 입력칸
						txtInput1, txtInput2;	// 2인용을 실행할때 숫자 입력칸
	
	private JButton	btnInput,				// 1인용 일때 숫자 입력 버튼
					btnInput1, btnInput2,	// 2인용 일때 숫자 입력 버튼
					btnHome;				// 메인 화면으로 돌아가는 버튼
	
	private MakingPanel making;		// 업콜 구조를 위해 MakingPanel을 선언함
	
	private Image bgimg = null;		// 배경 이미지 저장 : 우선 null로 초기화

	private int nRandom,		// 1P일때 컴퓨터가 생성한 랜덤의 3자리
				Strike, Ball, 	// 그 턴의 스트라이크와 볼 저장
				Count,			// 몇회 진행했는지 세는 변수
				
				nInput,
				num_1p, num_2p;

	private ImageIcon greenball = new ImageIcon("img/greenball.png");
	private ImageIcon yellowball = new ImageIcon("img/yellowball.png");
	private ImageIcon black = new ImageIcon("img/black.png");

/*	private JLabel greenball1, greenball2, greenball3, 
					yellowball1, yellowball2, yellowball3;
	private JLabel black1, black2, black3;*/

	private GameListener gameL;
	private MoveBallLabel mvBall;

	// record
	private RecordPanel record1p, record2p;
	private final int nRecordX = 200; // size조정용

	public PlayScreen(MakingPanel m, int nPlayer) {
		
		this.setBackground(Color.black);
		this.setLayout(null);
		making = m;
		
		//---배경 이미지를 설정하기위해 데이터에 저장함.
		Toolkit kit = Toolkit.getDefaultToolkit();
		bgimg = kit.getImage("img/bgimg.png");
		setLayout(null);
		
		gamePanel = new JPanel(); //게임 진행을 위한 패널 생성
		
		btnHome = new JButton(making.getHomeIcon());
		btnHome.setBounds(20, 20, 50, 50);
		btnHome.addActionListener(gameL);
		gamePanel.add(btnHome);

		gameL = new GameListener();		
		
		//---1P이면 onePlayer(), 2P 이면 twoPlayer() 실행 
		if (nPlayer == 1)
			onePlayer();
		else if (nPlayer == 2)
			twoPlayer();
	}

	private void onePlayer() { 

		making.setFrameSize(BaseballConstants.SIZE_1P);
		gamePanel.setBounds(0 + nRecordX, 0, 800 + nRecordX, 500);
		gamePanel.setOpaque(false); // 투명
		gamePanel.setLayout(null);
		add(gamePanel);

		do {
			nRandom = (int) (Math.random() * 999) + 1;
		} while (!PropChk(nRandom)); // 유효성 검사

		System.out.println(nRandom);

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
		lblCount.setHorizontalAlignment(SwingConstants.CENTER);
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

	private void twoPlayer() { // 2P일 경우

		making.setFrameSize(BaseballConstants.SIZE_2P);

		// gamePanel 2p size
		gamePanel.setBounds(0 + nRecordX, 0, 800 + nRecordX + nRecordX, 500);
		gamePanel.setOpaque(false); // 투명
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

		// 이미지 크기 변경 : 2인용으로
		Image gBall = greenball.getImage();
		Image gBall_s = gBall.getScaledInstance(45, 35, Image.SCALE_SMOOTH);
		greenball = new ImageIcon(gBall_s);

		Image yBall = yellowball.getImage();
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

		// 입력 받기

		num_1p = inputDialog(1);
		num_2p = inputDialog(2);

	}

	//------다이얼로그로 입력 받는 메소드 -------//
	private int inputDialog(int p) {
		int n;
		String s = new String("");

		while (true) {
			String result = JOptionPane.showInputDialog(s + p + "P의 수를 입력하시오");
			
			if (PropChk(result) == false) { // PropChk() 예외처리 메소드
				s = new String("잘못 입력하셨습니다.\n");
				continue;
			}
			n = Integer.parseInt(result);
			System.out.println("input" + p + "P:" + n);
			return n;
		}
	}
	//------------------------------------//
	
	// ----- 세자리수를 배열로 바꿔주는 메소드 ------//
	private int[] convertToArr(int n) {
		int arr[] = new int[3];
		arr[2] = n / 100;
		arr[1] = (n / 10) % 10;
		arr[0] = n % 10;
		return arr;
	}
	// ----- 세자리수를 배열로 바꿔주는 메소드 ------//

	
	//----------유효성 검사---------//
	//랜덤으로 생성되는 수나, 입력되는 수가 유효한 수인지 확인하고
	//유효하면 true, 무효하면 false return
	private boolean PropChk(int in) { 	//정수가 파라미터일 경우
		int[] n = convertToArr(in);
		return PropChk(n); 				//중복되는 코드를 줄이기 위해 오버라이딩된 메소드 사용
	}
	
	private boolean PropChk(String s) { //문자열이 파라미터인 경우
		int n;
		try { 							//ParseInt로 들어온 문자열이 수가 아닌경우 NumberFormatException을 뱉는다.
			n = Integer.parseInt(s);
		}catch(NumberFormatException e){//무효한 문자열이므로 false 리턴한다.
	        return false;
	    } 
		return PropChk(n);
		
	}

	private boolean PropChk(int[] in) { //배열이 파라미터일 경우
		int[] n = in;
		return !(n[2] == 0 || n[1] == 0 || n[0] == 0			// 수에 0이 들어가는 경우
				|| (n[2] * 100 + n[1] * 10 + n[0]) < 111 		// 세자리 수가 아닌경우
				|| n[2] == n[1] || n[1] == n[0] || n[0] == n[2] //중복되는 수가 있는경우
				|| (n[2] * 100 + n[1] * 10 + n[0]) > 999 ); 	//세자리 수가 넘는 경우
	}
	//---------유효성 검사----------//


	//------ 점수 검사 메소드-------//
	//두 수를 입력받고 비교해서 몇S 몇B 인지 판단해줌
	private void judgement(int in, int set) {
		
		int[] arrSet = new int[3];
		int[] arrIn = new int[3];
		
		Strike = 0;
		Ball = 0;
		
		arrSet = convertToArr(in);
		arrIn = convertToArr(set);
		
		for (int i = 0; i <= 2; i++) {
			for (int j = 0; j <= 2; j++) {
				if (arrIn[i] == arrSet[j] && i != j) {
					Ball++;
				}
			}
			if (arrIn[i] == arrSet[i]) {
				Strike++;
			}
		}
	}
	//------ 점수 검사 메소드-------//
	
	protected void paintComponent(Graphics g) {
		g.drawImage(bgimg, 10 + nRecordX, 10, 780, 440, this);
	}

	private class GameListener implements ActionListener {
		
		int flag=0;

		public void actionPerformed(ActionEvent event) {
			// int nInput;
			Object obj = event.getSource();

			if (obj == txtInput || obj == btnInput) { // obj가 txtinput,btninput
														// 이벤트를 받았을때
				if (PropChk(txtInput.getText())) {
					nInput = Integer.parseInt(txtInput.getText());

					// text에 입력된 값을
					// nInput에 넣는다.
					judgement(nInput, nRandom); // 2P에서 계속 사용될 예정이라 메소드를 만듬
					Count++;

					record1p.addText(nInput, Strike, Ball);

					drawBalls(sBoard_S, sBoard_B);
					lblCount.setText("Count = " + Count);

				}
				else {
					JOptionPane.showMessageDialog(null, "올바른 수를 입력하십시오!");
				}
			}

			// 여기서부터 2P
			if (obj == btnInput1 || obj == txtInput1) {
				
				flag=1;
				if (PropChk(txtInput1.getText())) {
					int n = Integer.parseInt(txtInput1.getText());
					judgement(n, num_2p);
					System.out.println("1P judging");
					Count++;
					System.out.println(Strike + " " + Ball);
					drawBalls(sBoard1p_S, sBoard1p_B);
					lblCount.setText("Count = " + Count);

					record1p.addText(n, Strike, Ball);
					
					txtInput1.setEnabled(false);
					btnInput1.setEnabled(false);
		            txtInput2.setEnabled(true);
		            btnInput2.setEnabled(true);
				} else {
					System.out.println("1P not judging");
					JOptionPane.showMessageDialog(null, "유효한 수를 입력하십시오!");
				}
			}

			if (obj == btnInput2 || obj == txtInput2) {
				
				flag=2;
				if (PropChk(txtInput2.getText())) {
					int n = Integer.parseInt(txtInput2.getText());
					judgement(n, num_1p);
					System.out.println("2P judging");
					Count++;
					System.out.println(Strike + " " + Ball);
					drawBalls(sBoard2p_S, sBoard2p_B);
					lblCount.setText("Count = " + Count);

					record2p.addText(n, Strike, Ball);
					
					txtInput1.setEnabled(true);
					btnInput1.setEnabled(true);
					txtInput2.setEnabled(false);
					btnInput2.setEnabled(false);
				} else {
					System.out.println("2P not judging");
					JOptionPane.showMessageDialog(null, "유효한 수를 입력하십시오!");
				}
			}

			// home 버튼
			if (obj == btnHome) {
				making.exeMainPanel();
			}

		}
		
		//-----------이겼을때 공이 움직이는 것을 보여주는 메소드 --------//
		private void moveBall (int n) {
			
			JLabel lblWin, lblWin2;
			
			// 쓰레드 구현
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
		//-----------이겼을때 공이 움직이는 것을 보여주는 메소드 --------//
		
		
		//------------전광판에 공을 그려주는 매소드------------//
		private void drawBalls(JPanel st, JPanel bl) {
			
			JPanel strike = st;
			JPanel ball = bl; // 
			
			strike.removeAll();
			ball.removeAll();
			
			strike.setLayout(new FlowLayout(FlowLayout.LEFT,0,0));
			ball.setLayout(new FlowLayout(FlowLayout.LEFT,0,0));
			
			
			JLabel[] gBall = new JLabel[3];
			JLabel[] yBall = new JLabel[3];
			
			
			for (int i = 0; i <= 2; i++) {
				gBall[i] = new JLabel(greenball);
				ball.add(gBall[i]);
				gBall[i].setVisible(true);
				yBall[i] = new JLabel(yellowball);
				strike.add(yBall[i]);
				yBall[i].setVisible(true);
			}
			
			
			for (int i = 0; i <= 2; i++) {
				if (i + 1 <= Ball) { gBall[i].setVisible(true); }
				else { gBall[i].setVisible(false); }
				if (i + 1 <= Strike) { yBall[i].setVisible(true); }
				else { yBall[i].setVisible(false);}
			}
			
			strike.revalidate();
			strike.repaint();
			ball.revalidate();
			ball.repaint();
			
			if (Strike == 3) { moveBall(flag); }

			// 전의 코드는 goto 100
		}
		//------------전광판에 공을 그려주는 매소드------------//

	}


	// get,set
	public int getStrike() 	{return Strike;	}
	public int getBall() 	{return Ball;	}
	public int getCount() 	{return Count;	}
	public int getInput() 	{return nInput;	}

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
