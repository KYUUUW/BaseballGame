import java.awt.*;
import javax.swing.*;
import java.awt.event.*;

public class PlayScreen extends JPanel{
   
   private JPanel leftPanel, rightPanel;
   private JPanel scoreBoard,scoreBoard2;
   private JLabel lblResultS,lblResultB, lblCount, lblRecord;
   
   private JTextField txtInput;
   private JButton btnInput;
   private MakingPanel making;
   private Image bgimg=null;
   
   private int nRandom,nInput;
   private int a,b,c,Ra,Rb,Rc,Strike,Ball,Count;
   private ImageIcon greenball=new ImageIcon("img/greenball.png");
   private ImageIcon yellowball=new ImageIcon("img/yellowball.png");
   private ImageIcon black=new ImageIcon("img/black.png");
   private JLabel greenball1,greenball2,greenball3,yellowball1,yellowball2,yellowball3;
   private JLabel black1,black2,black3;
   
   private GameListener gameL;   

   
   public PlayScreen (MakingPanel m) {
      
      Toolkit kit = Toolkit.getDefaultToolkit();
      bgimg = kit.getImage("img/bgimg.png"); 
      setLayout(null); 
      
      nRandom = (int)(Math.random()*999)+1;

      Ra=nRandom/100;
      Rb=(nRandom/10)%10;
      Rc=nRandom%10;
            
      System.out.println(nRandom);
      gameL = new GameListener();         //
      
      this.setPreferredSize(new Dimension(800,450));
      this.setBackground(Color.white);
      this.setLayout(null);
      
      making = m;
      
   //   leftPanel = new JPanel();
   //   leftPanel.setBounds(5, 5, 390, 440);
   //   leftPanel.setBackground(Color.WHITE);
   //   leftPanel.setLayout(null);
   //   add(leftPanel);
      
      rightPanel = new JPanel();
      rightPanel.setBounds(5, 5, 780, 440);
      //rightPanel.setBackground(Color.GREEN);
      rightPanel.setOpaque(false);      //투명
      rightPanel.setLayout(null);
      add(rightPanel);
      
      scoreBoard = new JPanel();
      scoreBoard.setBounds(230, 115, 390, 75);
      scoreBoard.setBackground(Color.BLACK);
   //   scoreBoard.setOpaque(false);      //투명
      rightPanel.add(scoreBoard);

      scoreBoard2 = new JPanel();
      scoreBoard2.setBounds(230, 40, 390, 75);
      scoreBoard2.setBackground(Color.BLACK);
   //   scoreBoard.setOpaque(false);      //투명
      rightPanel.add(scoreBoard2);
      
      
      
      /*lblResultS = new JLabel("");
      lblResultS.setFont(new Font("Verdana",Font.BOLD,50));
      lblResultS.setForeground(Color.YELLOW);
      
      lblResultS.setBackground(Color.WHITE);
      //lblResultS.setHorizontalAlignment(SwingConstants.LEFT);
      //lblResultS.setVerticalAlignment(SwingConstants.TOP);
      scoreBoard.add(lblResultS);
      
      lblResultB = new JLabel("");
      lblResultB.setFont(new Font("Verdana",Font.BOLD,50));
      lblResultB.setForeground(Color.GREEN);
      //lblResultB.setHorizontalAlignment(SwingConstants.LEFT);
      //lblResultB.setVerticalAlignment(SwingConstants.BOTTOM);
      scoreBoard.add(lblResultB);*/
      
      Count=0;
      lblCount = new JLabel("Count = "+Count);
      lblCount.setFont(new Font("Verdana", Font.BOLD, 20));
      lblCount.setForeground(Color.WHITE);
      lblCount.setBounds(220, 380, 350, 20);
      lblCount.setHorizontalAlignment(SwingConstants.CENTER);
      lblCount.setVerticalAlignment(SwingConstants.CENTER);
      rightPanel.add(lblCount);
      
      txtInput = new JTextField();
      txtInput.setBounds(250, 350, 200, 30);
      txtInput.addActionListener(gameL);   //
      rightPanel.add(txtInput);
      
      btnInput = new JButton("Confirm");
      btnInput.setBounds(460, 350, 100, 30);
      btnInput.addActionListener(gameL);
      btnInput.setFont(new Font("Verdana", Font.PLAIN, 15));
      rightPanel.add(btnInput);
      
      lblRecord = new JLabel("Record...");
      lblRecord.setBounds(20, 150, 350, 250);   
      lblRecord.setFont(new Font ("Verdana", Font.PLAIN, 10));
      lblRecord.setForeground(Color.BLACK);
      lblRecord.setVerticalAlignment(SwingConstants.TOP);
      rightPanel.add(lblRecord);
      
   }
   
   protected void paintComponent(Graphics g) 
   { 
      g.drawImage(bgimg, 5, 5, 780, 440, this); 

   } 


   private class GameListener implements ActionListener{
      public void actionPerformed(ActionEvent event){
      
         Object obj = event.getSource();
         
         if (obj == txtInput || obj == btnInput) {         //obj가 txtinput,btninput 이벤트를 받았을때
            nInput = Integer.parseInt(txtInput.getText());      //text에 입력된 값을 nInput에 넣는다.
            
            Strike=0;
            Ball=0;
            
            a=nInput/100;
            b=(nInput/10)%10;
            c=nInput%10;
            
            //효과 넣기
            
            
            if(Ra==a || Ra==b || Ra==c)
            {
               if(a==Ra)
               {
                  Strike++;
                  a=-1;
               }
                else Ball++;
            }
            if(Rb==a || Rb==b || Rb==c)
            {
               if(b==Rb)
               {
                  Strike++;
                  b=-1;
               }
               else Ball++;
            }
            if(Rc==a || Rc==b || Rc==c)
            {
               if(c==Rc)
               {
                  Strike++;
                  c=-1;
               }
               else Ball++;
            }
            Count++;
            
         /*   if(Strike==0)lblResultS.setText("S                        ");
            if(Strike==1)lblResultS.setText("S -                      ");
            if(Strike==2)lblResultS.setText("S --                     ");
            if(Ball==0)lblResultB.setText("B                        ");
            if(Ball==1)lblResultB.setText("B -                      ");
            if(Ball==2)lblResultB.setText("B --                     ");
            if(Ball==3)lblResultB.setText("B ---                    ");
            */
            
            
             scoreBoard.removeAll();
             
             scoreBoard2.removeAll();
         if(Ball==0)
            {
               
               black1 = new JLabel ("", black, SwingConstants.CENTER);
               black2 = new JLabel ("", black, SwingConstants.CENTER);
               black3 = new JLabel ("", black, SwingConstants.CENTER);
               
               scoreBoard.add(black1);
               scoreBoard.add(black2);
               scoreBoard.add(black3);
            } 
            
            if(Ball==1)
            {
               
               greenball1 = new JLabel ("", greenball, SwingConstants.CENTER);
               black1 = new JLabel ("", black, SwingConstants.CENTER);
               black2 = new JLabel ("", black, SwingConstants.CENTER);
               
               scoreBoard.add(greenball1);
               scoreBoard.add(black1);
               scoreBoard.add(black2);
            } 
            if(Ball==2)
            {
               greenball1 = new JLabel ("", greenball, SwingConstants.CENTER);
               greenball2 = new JLabel ("", greenball, SwingConstants.CENTER);
               black1 = new JLabel ("", black, SwingConstants.CENTER);
               scoreBoard.add(greenball1);
               scoreBoard.add(greenball2);
               scoreBoard.add(black1);
            }
            if(Ball==3)
            {
               greenball1 = new JLabel ("", greenball, SwingConstants.CENTER);
               greenball2 = new JLabel ("", greenball, SwingConstants.CENTER);
               greenball3 = new JLabel ("", greenball, SwingConstants.CENTER);
               scoreBoard.add(greenball1);
               scoreBoard.add(greenball2);
               scoreBoard.add(greenball3);
            }    
            if(Strike==0)
            {
               
               black1 = new JLabel ("", black, SwingConstants.CENTER);
               black2 = new JLabel ("", black, SwingConstants.CENTER);
               black3 = new JLabel ("", black, SwingConstants.CENTER);
               
               scoreBoard2.add(black1);
               scoreBoard2.add(black2);
               scoreBoard2.add(black3);
            } 
            if(Strike==1)
            {
               
               yellowball1 = new JLabel ("", yellowball, SwingConstants.CENTER);
               black1 = new JLabel ("", black, SwingConstants.CENTER);
               black2 = new JLabel ("", black, SwingConstants.CENTER);
               
               scoreBoard2.add(yellowball1);
               scoreBoard2.add(black1);
               scoreBoard2.add(black2);
               
            } 
            if(Strike==2)
            {
               yellowball1 = new JLabel ("", yellowball, SwingConstants.CENTER);
               yellowball2 = new JLabel ("", yellowball, SwingConstants.CENTER);
               black1 = new JLabel ("", black, SwingConstants.CENTER);
               scoreBoard2.add(greenball1);
               scoreBoard2.add(greenball2);
               scoreBoard2.add(black1);
            }
            if(Strike==3)
            {
               yellowball1 = new JLabel ("", yellowball, SwingConstants.CENTER);
               yellowball2 = new JLabel ("", yellowball, SwingConstants.CENTER);
               yellowball3 = new JLabel ("", yellowball, SwingConstants.CENTER);
               scoreBoard2.add(yellowball1);
               scoreBoard2.add(yellowball2);
               scoreBoard2.add(yellowball3);
            } 
            lblCount.setText("Count = "+Count);
            
            if(nInput==nRandom) 
            {
               lblResultS.setText("RIGHT");
            }
         }
      }
   }
   
}