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
 *          ù��° �Ķ���ʹ� ��Ʈ����ũ �г� �Է��ϰ�, �ι�° �Ķ���Ϳ��� �� �г� �Է��ϸ� ��. 
 *          �׷��� �� �гο� �� �޼ҵ尡 �Һ� �̹��� �����.
 *          ��� �̹��� ũ��� �� �޼ҵ� ���� ���� �˾Ƽ� ���� �� �����.
 */

public class PlayScreen extends JPanel {

   private JPanel screenPanel;
   private JPanel sBoard_B, sBoard_S, answerPanel;
   private JPanel sBoard1p_S, sBoard2p_S, sBoard1p_B, sBoard2p_B;
   private JLabel lblCount, lblWin, lblWin2;

   private JTextField txtInput, txtInput1, txtInput2;
   private JButton btnInput, btnInput1, btnInput2, btnHome;
   private MakingPanel making;
   private Image bgimg = null;

   private int nRandom;
   private int a, b, c, Ra, Rb, Rc, nStrike, nBall, nCount;
   private int nInput; 

   private int num_1p, num_2p;

   private ImageIcon greenball = new ImageIcon("img/greenball.png");
   private ImageIcon yellowball = new ImageIcon("img/yellowball.png");
   private ImageIcon black = new ImageIcon("img/black.png");


   private JLabel black1, black2, black3;

   private GameListener gameL;
   private MoveBallLabel mvBall;

   // record
   private RecordPanel record1p, record2p;
   private final int nRecordX = 200; // size������

   public PlayScreen(MakingPanel m, int nPlayer) { //making : ���� ���� ����, nPlayer : �÷��̾� ��

	  //��� �̹��� ����
      Toolkit kit = Toolkit.getDefaultToolkit();
      bgimg = kit.getImage("img/bgimg.png");
      
      making = m;

      gameL = new GameListener();

      this.setBackground(Color.white);
      this.setLayout(null);

      screenPanel = new JPanel();

      btnHome = new JButton(making.getHomeIcon()); //MakingPanel ���� �������� ���� �´�.
      btnHome.setBounds(20, 20, 50, 50);
      btnHome.addActionListener(gameL);
      screenPanel.add(btnHome);

      // 1P���� 2P����
      if	  (nPlayer == 1)
         onePlayer();
      else if (nPlayer == 2)
         twoPlayer();
   }

   private void onePlayer() { // 1P �� ���rightPanel = new JPanel();

      making.setFrameSize(BaseballConstants.SIZE_1P);
      // screenPanel
      screenPanel.setBounds(0 + nRecordX, 0, 800 + nRecordX, 500);
      screenPanel.setOpaque(false); // ����
      screenPanel.setLayout(null);
      add(screenPanel);

      // ���� ó�� : 0 �ȳ�����, 111������ �� ����, ������ �ݺ� ����
      do {
         nRandom = (int) (Math.random() * 999) + 1;

         Ra = nRandom / 100;
         Rb = (nRandom / 10) % 10;
         Rc = nRandom % 10;

      } while (!propChk(nRandom)); // ������ �ݺ� ���� : propChk() : ��ȿ�� �˻�
      System.out.println(nRandom); //�۵� �˻� ����

      sBoard_B = new JPanel();
      sBoard_B.setBounds(235, 126, 390, 75);
      sBoard_B.setBackground(Color.BLACK);
      screenPanel.add(sBoard_B);

      sBoard_S = new JPanel();
      sBoard_S.setBounds(235, 51, 390, 75);
      sBoard_S.setBackground(Color.BLACK);
      screenPanel.add(sBoard_S);

      nCount = 0;
      lblCount = new JLabel("Count = " + nCount);
      lblCount.setFont(new Font("Verdana", Font.BOLD, 20));
      lblCount.setForeground(Color.WHITE);
      lblCount.setBounds(225, 385, 350, 20);
      lblCount.setHorizontalAlignment(SwingConstants.CENTER);
      lblCount.setVerticalAlignment(SwingConstants.CENTER);
      screenPanel.add(lblCount);

      txtInput = new JTextField();
      txtInput.setBounds(255, 355, 200, 30);
      txtInput.addActionListener(gameL); //
      screenPanel.add(txtInput);

      btnInput = new JButton("Confirm");
      btnInput.setBounds(465, 355, 100, 30);
      btnInput.addActionListener(gameL);
      btnInput.setFont(new Font("Verdana", Font.PLAIN, 15));
      screenPanel.add(btnInput);

      // record
      record1p = new RecordPanel();
      record1p.setBounds(0, 10, 200, 450);
      this.add(record1p);

   }

   private void twoPlayer() { // 2P�� ���

      making.setFrameSize(BaseballConstants.SIZE_2P);

      // screenPanel 2p size
      screenPanel.setBounds(0 + nRecordX, 0, 800 + nRecordX + nRecordX, 500);
      screenPanel.setOpaque(false); // ����
      screenPanel.setLayout(null);
      add(screenPanel);

      // record
      record1p = new RecordPanel();
      record1p.setBounds(0, 10, 200, 450);
      this.add(record1p);

      record2p = new RecordPanel();
      record2p.setBounds(1000, 10, 200, 450);
      this.add(record2p);

      nCount = 0;
      lblCount = new JLabel("nCount = " + nCount);
      lblCount.setFont(new Font("Verdana", Font.BOLD, 20));
      lblCount.setForeground(Color.BLACK);
      lblCount.setBounds(225, 200, 350, 20);
      lblCount.setHorizontalAlignment(SwingConstants.CENTER);
      lblCount.setVerticalAlignment(SwingConstants.CENTER);
      screenPanel.add(lblCount);

      txtInput1 = new JTextField();
      txtInput1.setBounds(50, 355, 100, 30);
      txtInput1.addActionListener(gameL);
      screenPanel.add(txtInput1);

      txtInput2 = new JTextField();
      txtInput2.setBounds(800 - 50 - 100 - 100, 355, 100, 30);
      txtInput2.addActionListener(gameL); 
      txtInput2.setEnabled(false);
      screenPanel.add(txtInput2);

      btnInput1 = new JButton("1P Go");
      btnInput1.setBounds(50 + 100, 355, 100, 30);
      btnInput1.addActionListener(gameL);
      btnInput1.setFont(new Font("Verdana", Font.PLAIN, 12));
      screenPanel.add(btnInput1);

      btnInput2 = new JButton("2P Go");
      btnInput2.setBounds(800 - 50 - 100, 355, 100, 30);
      btnInput2.addActionListener(gameL);
      btnInput2.setFont(new Font("Verdana", Font.PLAIN, 12));
      btnInput2.setEnabled(false);
      screenPanel.add(btnInput2);

      // �̹��� ũ�� ���� : 2�ο�����
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
      sBoard1p_S.setBackground(Color.black);
      screenPanel.add(sBoard1p_S);

      sBoard1p_B = new JPanel();
      sBoard1p_B.setBounds(235, 140, 52 * 3, 37);
      sBoard1p_B.setBackground(Color.black);
      screenPanel.add(sBoard1p_B);

      sBoard2p_S = new JPanel();
      sBoard2p_S.setBounds(445, 75, 52 * 3, 37);
      sBoard2p_S.setBackground(Color.black);
      screenPanel.add(sBoard2p_S);

      sBoard2p_B = new JPanel();
      sBoard2p_B.setBounds(445, 140, 52 * 3, 37);
      sBoard2p_B.setBackground(Color.black);
      screenPanel.add(sBoard2p_B);

      // �Է� �ޱ�

      num_1p = inputDialog(1);
      num_2p = inputDialog(2);

   }

   private int inputDialog(int p) {
      int n;
      String s = new String("");

      while (true) {
         String result = JOptionPane.showInputDialog(s + p + "P�� ���� �Է��Ͻÿ�");
         n = Integer.parseInt(result);
         if (propChk(n) == false) { // propChk() ��ȿ�� �˻�
            s = new String("�߸� �Է��ϼ̽��ϴ�.\n");
            continue;
         }
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

   private boolean propChk (int in) {
      int[] n = convertToArr(in);
      return !(n[2] == 0 || n[1] == 0 || n[0] == 0 || in < 111
            || n[2] == n[1] || n[1] == n[0] || n[0] == n[2] || in > 999);
   }

   private boolean propChk (int[] in) {
      int[] n = in;
      return !(n[2] == 0 || n[1] == 0 || n[0] == 0
            || (n[2] * 100 + n[1] * 10 + n[0]) < 111 || n[2] == n[1]
            || n[1] == n[0] || n[0] == n[2] || (n[2] * 100 + n[1] * 10 + n[0]) > 999);
   }
   
   private int convertToInt (String s) {
	   int n;
	   try {
		   n = Integer.parseInt(s);
	   } catch (NumberFormatException e) {
		   
		   return 0;
	   }
	   return n;
   }


   protected void paintComponent(Graphics g) {
      g.drawImage(bgimg, 10 + nRecordX, 10, 780, 440, this);
   }

   private void judgement(int input) {
      nStrike = 0;
      nBall = 0;

      a = input / 100;
      b = (input / 10) % 10;
      c = input % 10;

      // ȿ�� �ֱ�

      if (Ra == a || Ra == b || Ra == c) {
         if (a == Ra) {
            nStrike++;
            a = -1;
         } else
            nBall++;
      }
      if (Rb == a || Rb == b || Rb == c) {
         if (b == Rb) {
            nStrike++;
            b = -1;
         } else
            nBall++;
      }
      if (Rc == a || Rc == b || Rc == c) {
         if (c == Rc) {
            nStrike++;
            c = -1;
         } else
            nBall++;
      }
   }

   private void judgement(int[] n) {

      nStrike = 0;
      nBall = 0;

      a = n[2];
      b = n[1];
      c = n[0];

      // ȿ�� �ֱ�

      if (Ra == a || Ra == b || Ra == c) {
         if (a == Ra) {
            nStrike++;
            a = -1;
         } else
            nBall++;
      }
      if (Rb == a || Rb == b || Rb == c) {
         if (b == Rb) {
            nStrike++;
            b = -1;
         } else
            nBall++;
      }
      if (Rc == a || Rc == b || Rc == c) {
         if (c == Rc) {
            nStrike++;
            c = -1;
         } else
            nBall++;
      }
   }

   private void judgement(int in, int set) {
      nStrike = 0;
      nBall = 0;

      a = in / 100;
      b = (in / 10) % 10;
      c = in % 10;

      Ra = set / 100;
      Rb = (set / 10) % 10;
      Rc = set % 10;

      // ȿ�� �ֱ�

      if (Ra == a || Ra == b || Ra == c) {
         if (a == Ra) {
            nStrike++;
            a = -1;
         } else
            nBall++;
      }
      if (Rb == a || Rb == b || Rb == c) {
         if (b == Rb) {
            nStrike++;
            b = -1;
         } else
            nBall++;
      }
      if (Rc == a || Rc == b || Rc == c) {
         if (c == Rc) {
            nStrike++;
            c = -1;
         } else
            nBall++;
      }
   }
   
   private class GameListener implements ActionListener {
	   
	   int flag=0;
	   private JLabel[] gBalls;
	   private JLabel[] yBalls;

      public void actionPerformed(ActionEvent event) {
         // int nInput;
         
         Object obj = event.getSource();

         if (obj == txtInput || obj == btnInput) { // obj�� txtinput,btninput
                                          // �̺�Ʈ�� �޾�����
            nInput = Integer.parseInt(txtInput.getText()); // text�� �Էµ� ����
            // nInput�� �ִ´�.
            judgement(nInput); // 2P���� ��� ���� �����̶� �޼ҵ带 ����
            nCount++;

            // record1p.setRSB(nInput, nStrike, nBall); //record1p�� ��
            record1p.addText(nInput, nStrike, nBall);

            drawBalls(sBoard_S, sBoard_B);
            lblCount.setText("nCount = " + nCount);

            if (nInput == nRandom ) {
              
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
               screenPanel.remove(lblCount);
               screenPanel.remove(txtInput);
               screenPanel.remove(btnInput);
               screenPanel.remove(sBoard_S);
               screenPanel.remove(sBoard_B);
               answerPanel.setBounds(0 + nRecordX, 0, 1000 + nRecordX, 500);
               answerPanel.setBackground(new Color(29, 29, 27));
               add(answerPanel);
               repaint();
               
               
            }
         }

         // ���⼭���� 2P
         if (obj == btnInput1 || obj == txtInput1) {
            flag=1;
            int n = Integer.parseInt(txtInput1.getText());
            if (propChk(n)) {
               judgement(n, num_2p);
               System.out.println("1P judging");
               nCount++;
               System.out.println(nStrike + " " + nBall);
               drawBalls(sBoard1p_S, sBoard1p_B);
               lblCount.setText("nCount = " + nCount);

               record1p.addText(n, nStrike, nBall);
               
               txtInput1.setEnabled(false);
               btnInput1.setEnabled(false);
                  txtInput2.setEnabled(true);
                  btnInput2.setEnabled(true);
            } else {
               System.out.println("1P not judging");
               JOptionPane.showMessageDialog(null, "��ȿ�� ���� �Է��Ͻʽÿ�!");
            }
         }

         if (obj == btnInput2 || obj == txtInput2) {
            flag=2;
            int n = Integer.parseInt(txtInput2.getText());
            if (propChk(n)) {
               judgement(n, num_1p);
               System.out.println("2P judging");
               nCount++;
               System.out.println(nStrike + " " + nBall);
               drawBalls(sBoard2p_S, sBoard2p_B);
               lblCount.setText("nCount = " + nCount);

               record2p.addText(n, nStrike, nBall);
               
               txtInput1.setEnabled(true);
               btnInput1.setEnabled(true);
               txtInput2.setEnabled(false);
               btnInput2.setEnabled(false);
            } else {
               System.out.println("2P not judging");
               JOptionPane.showMessageDialog(null, "��ȿ�� ���� �Է��Ͻʽÿ�!");
            }
         }

         // home ��ư
         if (obj == btnHome) {
            making.exeMainPanel();
         }

      }

      private void drawBalls(JPanel st, JPanel bl) {
         JPanel strike = st;
         JPanel ball = bl;
         // call by reference �� ������ ����

         strike.removeAll();
         ball.removeAll();
         
         for (int i = 0; i <= 2; i++) {
        	 gBalls[i] = new JLabel(greenball);
        	 yBalls[i] = new JLabel(yellowball);
    
         }
         
         for (int i = 0; i <= 2; i++) {
        	 strike.add(yBalls[i]);
        	 if (i <= nBall) { gBalls[i].setVisible(true); }
        	 else { gBalls[i].setVisible(false); }
        	 
        	 ball.add(gBalls[i]);
        	 if (i <= nBall) { yBalls[i].setVisible(true); }
        	 else { yBalls[i].setVisible(false); }
         }
         
         

         /*if (nBall == 0) {

            black1 = new JLabel("", black, SwingConstants.CENTER);
            black2 = new JLabel("", black, SwingConstants.CENTER);
            black3 = new JLabel("", black, SwingConstants.CENTER);

            ball.add(black1);
            ball.add(black2);
            ball.add(black3);
         }

         if (nBall == 1) {

            greenball1 = new JLabel("", greenball, SwingConstants.CENTER);
            black1 = new JLabel("", black, SwingConstants.CENTER);
            black2 = new JLabel("", black, SwingConstants.CENTER);

            ball.add(greenball1);
            ball.add(black1);
            ball.add(black2);
         }
         if (nBall == 2) {
            greenball1 = new JLabel("", greenball, SwingConstants.CENTER);
            greenball2 = new JLabel("", greenball, SwingConstants.CENTER);
            black1 = new JLabel("", black, SwingConstants.CENTER);
            ball.add(greenball1);
            ball.add(greenball2);
            ball.add(black1);
         }
         if (nBall == 3) {
            greenball1 = new JLabel("", greenball, SwingConstants.CENTER);
            greenball2 = new JLabel("", greenball, SwingConstants.CENTER);
            greenball3 = new JLabel("", greenball, SwingConstants.CENTER);
            ball.add(greenball1);
            ball.add(greenball2);
            ball.add(greenball3);
         }
         if (nStrike == 0) {

            black1 = new JLabel("", black, SwingConstants.CENTER);
            black2 = new JLabel("", black, SwingConstants.CENTER);
            black3 = new JLabel("", black, SwingConstants.CENTER);

            strike.add(black1);
            strike.add(black2);
            strike.add(black3);
         }
         if (nStrike == 1) {

            yellowball1 = new JLabel("", yellowball, SwingConstants.CENTER);
            black1 = new JLabel("", black, SwingConstants.CENTER);
            black2 = new JLabel("", black, SwingConstants.CENTER);

            strike.add(yellowball1);
            strike.add(black1);
            strike.add(black2);

         }
         if (nStrike == 2) {
            yellowball1 = new JLabel("", yellowball, SwingConstants.CENTER);
            yellowball2 = new JLabel("", yellowball, SwingConstants.CENTER);
            black1 = new JLabel("", black, SwingConstants.CENTER);
            strike.add(yellowball1);
            strike.add(yellowball2);
            strike.add(black1);
        	 
        	 yellowball1.setVisible(true);
        	 yellowball2.setVisible(true);
        	 yellowball3.setVisible(false);
         }
         if (nStrike == 3) {
            yellowball1 = new JLabel(yellowball);
            yellowball2 = new JLabel(yellowball);
            yellowball3 = new JLabel(yellowball);
            strike.add(yellowball1);
            strike.add(yellowball2);
            strike.add(yellowball3);
            
            if(flag==1){
               // lblResultS.setText("RIGHT");
               // ������ ����
               // -----------------------------------------
               lblWin = new JLabel("1P");
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
               screenPanel.remove(sBoard1p_S);
               screenPanel.remove(sBoard1p_B);
               screenPanel.remove(sBoard2p_S);
               screenPanel.remove(sBoard2p_B);
               screenPanel.remove(lblCount);
               screenPanel.remove(txtInput1);
               screenPanel.remove(btnInput1);
               screenPanel.remove(txtInput2);
               screenPanel.remove(btnInput2);
               answerPanel.add(lblWin);
               answerPanel.add(lblWin2);
               answerPanel.setBounds(0 + nRecordX, 0, 1000 + nRecordX, 500);
               answerPanel.setBackground(new Color(29, 29, 27));
               add(answerPanel);
               repaint();
               // -----------------------------------------
            }
            
            if(flag==2){
               lblWin = new JLabel("2P");
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
               screenPanel.remove(sBoard1p_S);
               screenPanel.remove(sBoard1p_B);
               screenPanel.remove(sBoard2p_S);
               screenPanel.remove(sBoard2p_B);
               screenPanel.remove(lblCount);
               screenPanel.remove(txtInput1);
               screenPanel.remove(btnInput1);
               screenPanel.remove(txtInput2);
               screenPanel.remove(btnInput2);
               answerPanel.add(lblWin);
               answerPanel.add(lblWin2);
               answerPanel.setBounds(0 + nRecordX, 0, 1000 + nRecordX, 500);
               answerPanel.setBackground(new Color(29, 29, 27));
               add(answerPanel);
               repaint();
            }
         }*/

      }

   }

   // get,set
   public int getStrike() {return nStrike;}
   public int getBall() {return nBall;}
   public int getCount() {return nCount;}
   public int getInput() {return nInput;}

} // PlayScreen class