import java.awt.*;
import javax.swing.*;

@SuppressWarnings("serial")
public class MoveBallLabel extends JLabel implements Runnable{ //JLabel을 상속받고 Runnable 인터페이스를 구현하는 클래스

   ImageIcon icon;
   Thread th;
   
   public MoveBallLabel () {            //생성자
      icon = new ImageIcon("img/fireball.jpg");//이미지를 생성한다.
      Image img = icon.getImage();         //
      Image img_m = img.getScaledInstance(166, 235,Image.SCALE_SMOOTH);   //크기조정
      icon = new ImageIcon(img_m);         
      setIcon(icon);                  //아이콘을 set한다.
      
   }
   
   public void start() {
      if (th == null) {               //만약 스레드가 없다면
         th = new Thread(this);         //새로 생성한다.
      }
      th.start();                     //스레드를 시작한다.
   }

   @Override
   public void run() {                  //실제 스레드가 동작하는 부분
      try {                        
         for (int i = 1; i <= 500 ; i++) {            //반복문을 이용한다.
            setBounds(100, -20, i, i);               //이미지의 위치를 i값을 통한 반복으로 변경시킨다.
            Thread.sleep(2);                     //이미지가 움직인후 잠시 멈추어 움직이는 효과를 준다.
            icon = new ImageIcon("img/fireball.jpg");   //아이콘을 생성한다.
            Image img = icon.getImage();            
            Image img_m = img.getScaledInstance(166, 235,Image.SCALE_SMOOTH);   //크기조정된 이미지를 대입
            icon = new ImageIcon(img_m);               //아이콘에 새로운 이미지를 생성
            setIcon(icon);                        //아이콘을 set한다.
         }
      } catch (Exception e) {
         // TODO: handle exception
      }
   }
}
