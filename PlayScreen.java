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

	private JPanel leftPanel, rightPanel;
	private JPanel scoreBoardBall, scoreBoardStrike, answerPanel;
	private JPanel sBoard1p_S, sBoard2p_S, sBoard1p_B, sBoard2p_B;
	private JLabel lblResultS, lblResultB, lblCount, lblRecord, lblWin,
			lblWin2;

	private JTextField txtInput, txtInput1, txtInput2;
	private JButton btnInput, btnInput1, btnInput2, btnHome;
	private MakingPanel making;
	private Image bgimg = null;

	private int nRandom, n_1p, n_2p;
	private int a, b, c, Ra, Rb, Rc, Strike, Ball, Count;
	private int nInput, nPlay;

	private int arr_1p[], arr_2p[];
	private int num_1p, num_2p;

	private ImageIcon greenball = new ImageIcon("img/greenball.png");
	private ImageIcon yellowball = new ImageIcon("img/yellowball.png");
	private ImageIcon black = new ImageIcon("img/black.png");

	private JLabel greenball1, greenball2, greenball3, yellowball1,
			yellowball2, yellowball3;
	private JLabel black1, black2, black3;

	private GameListener gameL;

	private MoveBallLabel mvBall;

	// record
	private RecordPanel record1p, record2p;
	private int nRecordX = 200; // size조정용

	public PlayScreen(MakingPanel m, int nPlayer) {

		Toolkit kit = Toolkit.getDefaultToolkit();
		bgimg = kit.getImage("img/bgimg.png");
		setLayout(null);

		// Random 만드는것 onePlayer() 로 옮김.

		gameL = new GameListener();

		// this.setPreferredSize(new Dimension(800+nRecordX+nRecordX, 450));
		this.setBackground(Color.white);
		this.setLayout(null);

		making = m;

		// leftPanel = new JPanel();
		// leftPanel.setBounds(5, 5, 390, 440);
		// leftPanel.setBackground(Color.WHITE);
		// leftPanel.setLayout(null);
		// add(leftPanel);

		rightPanel = new JPanel();
		/*
		 * rightPanel.setBounds(0+nRecordX, 0, 800+nRecordX, 500);
		 * rightPanel.setOpaque(false); // 투명 rightPanel.setLayout(null);
		 * add(rightPanel);
		 */

		btnHome = new JButton(making.getHomeIcon());
		btnHome.setBounds(20, 20, 50, 50);
		btnHome.addActionListener(gameL);
		rightPanel.add(btnHome);
		nPlay = nPlayer;
		// 1P인지 2P인지
		if (nPlayer == 1)
			onePlayer();
		else if (nPlayer == 2)
			twoPlayer();
	}

	private void onePlayer() { // 1P 일 경우rightPanel = new JPanel();

		making.setFrameSize(BaseballConstants.SIZE_1P);
		// rightPanel
		rightPanel.setBounds(0 + nRecordX, 0, 800 + nRecordX, 500);
		rightPanel.setOpaque(false); // 투명
		rightPanel.setLayout(null);
		add(rightPanel);

		// 예외 처리 : 0 안나오게, 111이하의 수 제거, 같은수 반복 제거
		do {
			nRandom = (int) (Math.random() * 999) + 1;

			Ra = nRandom / 100;
			Rb = (nRandom / 10) % 10;
			Rc = nRandom % 10;

		} while (Ra == 0 || Rb == 0 || Rc == 0 || // 0 안나오게
				nRandom < 111 || // 111이하의 수 제거
				Ra == Rb || Rb == Rc || Rc == Ra); // 같은수 반복 제거

		System.out.println(nRandom);

		// ball : scoreBoard
		scoreBoardBall = new JPanel();
		scoreBoardBall.setBounds(235, 126, 390, 75);
		scoreBoardBall.setBackground(Color.BLACK);
		// scoreBoardBall.setOpaque(false); //투명
		rightPanel.add(scoreBoardBall);

		// strike : scoreBoardStrike
		scoreBoardStrike = new JPanel();
		scoreBoardStrike.setBounds(235, 51, 390, 75);
		scoreBoardStrike.setBackground(Color.BLACK);
		// scoreBoard.setOpaque(false); //투명
		rightPanel.add(scoreBoardStrike);

		Count = 0;
		lblCount = new JLabel("Count = " + Count);
		lblCount.setFont(new Font("Verdana", Font.BOLD, 20));
		lblCount.setForeground(Color.WHITE);
		lblCount.setBounds(225, 385, 350, 20);
		lblCount.setHorizontalAlignment(SwingConstants.CENTER);
		lblCount.setVerticalAlignment(SwingConstants.CENTER);
		rightPanel.add(lblCount);

		txtInput = new JTextField();
		txtInput.setBounds(255, 355, 200, 30);
		txtInput.addActionListener(gameL); //
		rightPanel.add(txtInput);

		btnInput = new JButton("Confirm");
		btnInput.setBounds(465, 355, 100, 30);
		btnInput.addActionListener(gameL);
		btnInput.setFont(new Font("Verdana", Font.PLAIN, 15));
		rightPanel.add(btnInput);

		/*
		 * lblRecord = new JLabel("Record..."); lblRecord.setBounds(25, 155,
		 * 350, 250); lblRecord.setFont(new Font("Verdana", Font.PLAIN, 10));
		 * lblRecord.setForeground(Color.BLACK);
		 * lblRecord.setVerticalAlignment(SwingConstants.TOP);
		 * rightPanel.add(lblRecord);
		 */

		// record
		record1p = new RecordPanel();
		record1p.setBounds(0, 10, 200, 450);
		this.add(record1p);

	}

	private void twoPlayer() { // 2P일 경우

		making.setFrameSize(BaseballConstants.SIZE_2P);

		// rightPanel 2p size
		rightPanel.setBounds(0 + nRecordX, 0, 800 + nRecordX + nRecordX, 500);
		rightPanel.setOpaque(false); // 투명
		rightPanel.setLayout(null);
		add(rightPanel);

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
		rightPanel.add(lblCount);

		txtInput1 = new JTextField();
		txtInput1.setBounds(50, 355, 100, 30);
		txtInput1.addActionListener(gameL);
		rightPanel.add(txtInput1);

		txtInput2 = new JTextField();
		txtInput2.setBounds(800 - 50 - 100 - 100, 355, 100, 30);
		txtInput2.addActionListener(gameL); 
		txtInput2.setEnabled(false);
		rightPanel.add(txtInput2);

		btnInput1 = new JButton("1P Go");
		btnInput1.setBounds(50 + 100, 355, 100, 30);
		btnInput1.addActionListener(gameL);
		btnInput1.setFont(new Font("Verdana", Font.PLAIN, 12));
		rightPanel.add(btnInput1);

		btnInput2 = new JButton("2P Go");
		btnInput2.setBounds(800 - 50 - 100, 355, 100, 30);
		btnInput2.addActionListener(gameL);
		btnInput2.setFont(new Font("Verdana", Font.PLAIN, 12));
		btnInput2.setEnabled(false);
		rightPanel.add(btnInput2);

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
		rightPanel.add(sBoard1p_S);

		sBoard1p_B = new JPanel();
		sBoard1p_B.setBounds(235, 140, 52 * 3, 37);
		sBoard1p_B.setBackground(Color.BLACK);
		rightPanel.add(sBoard1p_B);

		sBoard2p_S = new JPanel();
		sBoard2p_S.setBounds(445, 75, 52 * 3, 37);
		sBoard2p_S.setBackground(Color.BLACK);
		rightPanel.add(sBoard2p_S);

		sBoard2p_B = new JPanel();
		sBoard2p_B.setBounds(445, 140, 52 * 3, 37);
		sBoard2p_B.setBackground(Color.BLACK);
		rightPanel.add(sBoard2p_B);

		// 입력 받기

		arr_1p = convertToArr(num_1p = inputDialog(1));
		arr_2p = convertToArr(num_2p = inputDialog(2));

	}

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

	private int[] convertToArr(int n) {
		int arr[] = new int[3];
		arr[2] = n / 100;
		arr[1] = (n / 10) % 10;
		arr[0] = n % 10;
		return arr;
	}

	private boolean PropChk(int in) {
		int[] n = convertToArr(in);
		return !(n[2] == 0 || n[1] == 0 || n[0] == 0 || in < 111
				|| n[2] == n[1] || n[1] == n[0] || n[0] == n[2] || in > 999);
	}

	private boolean PropChk(int[] in) {
		int[] n = in;
		return !(n[2] == 0 || n[1] == 0 || n[0] == 0
				|| (n[2] * 100 + n[1] * 10 + n[0]) < 111 || n[2] == n[1]
				|| n[1] == n[0] || n[0] == n[2] || (n[2] * 100 + n[1] * 10 + n[0]) > 999 );
	}
	
	private boolean PropChk(String s) {
		int n;
		try {
			n = Integer.parseInt(s);
		}catch(NumberFormatException e){
	        return false;
	    } 
		return PropChk(n);
		
	}
	

	protected void paintComponent(Graphics g) {
		g.drawImage(bgimg, 10 + nRecordX, 10, 780, 440, this);
	}

	private void judgement(int input) {
		Strike = 0;
		Ball = 0;

		a = input / 100;
		b = (input / 10) % 10;
		c = input % 10;

		// 효과 넣기

		if (Ra == a || Ra == b || Ra == c) {
			if (a == Ra) {
				Strike++;
				a = -1;
			} else
				Ball++;
		}
		if (Rb == a || Rb == b || Rb == c) {
			if (b == Rb) {
				Strike++;
				b = -1;
			} else
				Ball++;
		}
		if (Rc == a || Rc == b || Rc == c) {
			if (c == Rc) {
				Strike++;
				c = -1;
			} else
				Ball++;
		}
	}

	private void judgement(int[] n) {

		Strike = 0;
		Ball = 0;

		a = n[2];
		b = n[1];
		c = n[0];

		// 효과 넣기

		if (Ra == a || Ra == b || Ra == c) {
			if (a == Ra) {
				Strike++;
				a = -1;
			} else
				Ball++;
		}
		if (Rb == a || Rb == b || Rb == c) {
			if (b == Rb) {
				Strike++;
				b = -1;
			} else
				Ball++;
		}
		if (Rc == a || Rc == b || Rc == c) {
			if (c == Rc) {
				Strike++;
				c = -1;
			} else
				Ball++;
		}
	}

	private void judgement(int in, int set) {
		Strike = 0;
		Ball = 0;

		a = in / 100;
		b = (in / 10) % 10;
		c = in % 10;

		Ra = set / 100;
		Rb = (set / 10) % 10;
		Rc = set % 10;

		// 효과 넣기

		if (Ra == a || Ra == b || Ra == c) {
			if (a == Ra) {
				Strike++;
				a = -1;
			} else
				Ball++;
		}
		if (Rb == a || Rb == b || Rb == c) {
			if (b == Rb) {
				Strike++;
				b = -1;
			} else
				Ball++;
		}
		if (Rc == a || Rc == b || Rc == c) {
			if (c == Rc) {
				Strike++;
				c = -1;
			} else
				Ball++;
		}
	}

	private class GameListener implements ActionListener {

		public void actionPerformed(ActionEvent event) {
			// int nInput;
			Object obj = event.getSource();

			if (obj == txtInput || obj == btnInput) { // obj가 txtinput,btninput
														// 이벤트를 받았을때
				if (PropChk(txtInput.getText())) {
					nInput = Integer.parseInt(txtInput.getText());

					// text에 입력된 값을
					// nInput에 넣는다.
					judgement(nInput); // 2P에서 계속 사용될 예정이라 메소드를 만듬
					Count++;

					record1p.addText(nInput, Strike, Ball);

					drawBalls(scoreBoardStrike, scoreBoardBall);
					lblCount.setText("Count = " + Count);

					if (nInput == nRandom && nPlay == 1) {
						// 쓰레드 구현
						// -----------------------------------------
						lblWin = new JLabel("YOU");
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
						rightPanel.remove(lblCount);
						rightPanel.remove(txtInput);
						rightPanel.remove(btnInput);
						rightPanel.remove(scoreBoardStrike);
						rightPanel.remove(scoreBoardBall);
						answerPanel.setBounds(0 + nRecordX, 0, 1000 + nRecordX, 500);
						answerPanel.setBackground(new Color(29, 29, 27));
						add(answerPanel);
						repaint();
						// -----------------------------------------
					}
				}
				else {
					JOptionPane.showMessageDialog(null, "올바른 수를 입력하십시오!");
				}
			}

			// 여기서부터 2P
			if (obj == btnInput1 || obj == txtInput1) {
				
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

		private void drawBalls(JPanel st, JPanel bl) {
			JPanel strike = st;
			JPanel ball = bl;
			// call by reference 기 때문에 가능

			strike.removeAll();
			ball.removeAll();

			if (Ball == 0) {

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
			}

		}

	}

	// get,set
	public int getStrike() {
		return Strike;
	}

	public int getBall() {
		return Ball;
	}

	public int getCount() {
		return Count;
	}

	public int getInput() {
		return nInput;
	}

} // PlayScreen class
