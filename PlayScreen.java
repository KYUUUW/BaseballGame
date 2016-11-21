import java.awt.*;
import javax.swing.*;
import java.awt.event.*;

/*
 * 버전 V.4 PlayOperation 없애고 PlayScreen 에서 처리하는 것으로 해결함.
 * Constructor 내에서는 배경만 만들고 Constructor 에서 
 * 1P일때는 onePlayer, 2P 일때는 twoPlayer 메소드를 호출해서 컴포넌트들을 배치한다.
 */

public class PlayScreen extends JPanel {

	private JPanel leftPanel, rightPanel;
	private JPanel scoreBoardBall, scoreBoardStrike;
	private JLabel lblResultS, lblResultB, lblCount, lblRecord;

	private JTextField txtInput, txtInput1, txtInput2;
	private JButton btnInput, btnInput1, btnInput2;
	private MakingPanel making;
	private Image bgimg = null;

	private int nRandom, nInput;
	private int a, b, c, Ra, Rb, Rc, Strike, Ball, Count;
	private ImageIcon greenball = new ImageIcon("img/greenball.png");
	private ImageIcon yellowball = new ImageIcon("img/yellowball.png");
	private ImageIcon black = new ImageIcon("img/black.png");
	private JLabel greenball1, greenball2, greenball3, yellowball1, yellowball2, yellowball3;
	private JLabel black1, black2, black3;

	private GameListener gameL;

	public PlayScreen(MakingPanel m, int nPlayer) {

		Toolkit kit = Toolkit.getDefaultToolkit();
		bgimg = kit.getImage("img/bgimg.png");
		setLayout(null);

		nRandom = (int) (Math.random() * 999) + 1;

		Ra = nRandom / 100;
		Rb = (nRandom / 10) % 10;
		Rc = nRandom % 10;

		System.out.println(nRandom);
		gameL = new GameListener();

		this.setPreferredSize(new Dimension(800, 450));
		this.setBackground(Color.white);
		this.setLayout(null);

		making = m;

		// leftPanel = new JPanel();
		// leftPanel.setBounds(5, 5, 390, 440);
		// leftPanel.setBackground(Color.WHITE);
		// leftPanel.setLayout(null);
		// add(leftPanel);

		rightPanel = new JPanel();
		rightPanel.setBounds(0, 0, 800, 500);
		// rightPanel.setBackground(Color.GREEN);
		rightPanel.setOpaque(false); // 투명
		rightPanel.setLayout(null);
		add(rightPanel);

		// 1P인지 2P인지
		if (nPlayer == 1)
			onePlayer();
		else if (nPlayer == 2)
			twoPlayer();
	}

	private void onePlayer() { // 1P 일 경우

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

		/*
		 * lblResultS = new JLabel(""); lblResultS.setFont(new
		 * Font("Verdana",Font.BOLD,50));
		 * lblResultS.setForeground(Color.YELLOW);
		 * 
		 * lblResultS.setBackground(Color.WHITE);
		 * //lblResultS.setHorizontalAlignment(SwingConstants.LEFT);
		 * //lblResultS.setVerticalAlignment(SwingConstants.TOP);
		 * scoreBoardBall.add(lblResultS);
		 * 
		 * lblResultB = new JLabel(""); lblResultB.setFont(new
		 * Font("Verdana",Font.BOLD,50)); lblResultB.setForeground(Color.GREEN);
		 * //lblResultB.setHorizontalAlignment(SwingConstants.LEFT);
		 * //lblResultB.setVerticalAlignment(SwingConstants.BOTTOM);
		 * scoreBoardBall.add(lblResultB);
		 */

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

		lblRecord = new JLabel("Record...");
		lblRecord.setBounds(25, 155, 350, 250);
		lblRecord.setFont(new Font("Verdana", Font.PLAIN, 10));
		lblRecord.setForeground(Color.BLACK);
		lblRecord.setVerticalAlignment(SwingConstants.TOP);
		rightPanel.add(lblRecord);

	}

	private void twoPlayer() { // 2P일 경우
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
		txtInput1.addActionListener(gameL); //
		rightPanel.add(txtInput1);

		txtInput2 = new JTextField();
		txtInput2.setBounds(800 - 50 - 100 - 100, 355, 100, 30);
		txtInput2.addActionListener(gameL); //
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
		rightPanel.add(btnInput2);

	}

	protected void paintComponent(Graphics g) {
		g.drawImage(bgimg, 10, 10, 780, 440, this);

	}

	private void judgement(int input) {
		Strike = 0;
		Ball = 0;

		a = nInput / 100;
		b = (nInput / 10) % 10;
		c = nInput % 10;

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

			Object obj = event.getSource();

			if (obj == txtInput || obj == btnInput) { // obj가 txtinput,btninput
														// 이벤트를 받았을때
				nInput = Integer.parseInt(txtInput.getText()); // text에 입력된 값을
																// nInput에 넣는다.
				judgement(nInput); // 2P에서 계속 사용될 예정이라 메소드를 만듬
				Count++;

				/*
				 * if(Strike==0)lblResultS.setText("S                        ");
				 * if(Strike==1)lblResultS.setText("S -                      ");
				 * if(Strike==2)lblResultS.setText("S --                     ");
				 * if(Ball==0)lblResultB.setText("B                        ");
				 * if(Ball==1)lblResultB.setText("B -                      ");
				 * if(Ball==2)lblResultB.setText("B --                     ");
				 * if(Ball==3)lblResultB.setText("B ---                    ");
				 */

				scoreBoardBall.removeAll();
				scoreBoardStrike.removeAll();
				if (Ball == 0) {

					black1 = new JLabel("", black, SwingConstants.CENTER);
					black2 = new JLabel("", black, SwingConstants.CENTER);
					black3 = new JLabel("", black, SwingConstants.CENTER);

					scoreBoardBall.add(black1);
					scoreBoardBall.add(black2);
					scoreBoardBall.add(black3);
				}

				if (Ball == 1) {

					greenball1 = new JLabel("", greenball, SwingConstants.CENTER);
					black1 = new JLabel("", black, SwingConstants.CENTER);
					black2 = new JLabel("", black, SwingConstants.CENTER);

					scoreBoardBall.add(greenball1);
					scoreBoardBall.add(black1);
					scoreBoardBall.add(black2);
				}
				if (Ball == 2) {
					greenball1 = new JLabel("", greenball, SwingConstants.CENTER);
					greenball2 = new JLabel("", greenball, SwingConstants.CENTER);
					black1 = new JLabel("", black, SwingConstants.CENTER);
					scoreBoardBall.add(greenball1);
					scoreBoardBall.add(greenball2);
					scoreBoardBall.add(black1);
				}
				if (Ball == 3) {
					greenball1 = new JLabel("", greenball, SwingConstants.CENTER);
					greenball2 = new JLabel("", greenball, SwingConstants.CENTER);
					greenball3 = new JLabel("", greenball, SwingConstants.CENTER);
					scoreBoardBall.add(greenball1);
					scoreBoardBall.add(greenball2);
					scoreBoardBall.add(greenball3);
				}
				if (Strike == 0) {

					black1 = new JLabel("", black, SwingConstants.CENTER);
					black2 = new JLabel("", black, SwingConstants.CENTER);
					black3 = new JLabel("", black, SwingConstants.CENTER);

					scoreBoardStrike.add(black1);
					scoreBoardStrike.add(black2);
					scoreBoardStrike.add(black3);
				}
				if (Strike == 1) {

					yellowball1 = new JLabel("", yellowball, SwingConstants.CENTER);
					black1 = new JLabel("", black, SwingConstants.CENTER);
					black2 = new JLabel("", black, SwingConstants.CENTER);

					scoreBoardStrike.add(yellowball1);
					scoreBoardStrike.add(black1);
					scoreBoardStrike.add(black2);

				}
				if (Strike == 2) {
					yellowball1 = new JLabel("", yellowball, SwingConstants.CENTER);
					yellowball2 = new JLabel("", yellowball, SwingConstants.CENTER);
					black1 = new JLabel("", black, SwingConstants.CENTER);
					scoreBoardStrike.add(greenball1);
					scoreBoardStrike.add(greenball2);
					scoreBoardStrike.add(black1);
				}
				if (Strike == 3) {
					yellowball1 = new JLabel("", yellowball, SwingConstants.CENTER);
					yellowball2 = new JLabel("", yellowball, SwingConstants.CENTER);
					yellowball3 = new JLabel("", yellowball, SwingConstants.CENTER);
					scoreBoardStrike.add(yellowball1);
					scoreBoardStrike.add(yellowball2);
					scoreBoardStrike.add(yellowball3);
				}
				lblCount.setText("Count = " + Count);

				if (nInput == nRandom) {
					lblResultS.setText("RIGHT");
				}
			}
			
			if (obj == btnInput1 || obj == txtInput1) {
				
			}

		}
	}

}