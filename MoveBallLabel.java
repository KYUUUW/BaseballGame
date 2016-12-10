import java.awt.*;
import javax.swing.*;

@SuppressWarnings("serial")
public class MoveBallLabel extends JLabel implements Runnable{ //JLabel�� ��ӹް� Runnable �������̽��� �����ϴ� Ŭ����

   ImageIcon icon;
   Thread th;
   
   public MoveBallLabel () {            //������
      icon = new ImageIcon("img/fireball.jpg");//�̹����� �����Ѵ�.
      Image img = icon.getImage();         //
      Image img_m = img.getScaledInstance(166, 235,Image.SCALE_SMOOTH);   //ũ������
      icon = new ImageIcon(img_m);         
      setIcon(icon);                  //�������� set�Ѵ�.
      
   }
   
   public void start() {
      if (th == null) {               //���� �����尡 ���ٸ�
         th = new Thread(this);         //���� �����Ѵ�.
      }
      th.start();                     //�����带 �����Ѵ�.
   }

   @Override
   public void run() {                  //���� �����尡 �����ϴ� �κ�
      try {                        
         for (int i = 1; i <= 500 ; i++) {            //�ݺ����� �̿��Ѵ�.
            setBounds(100, -20, i, i);               //�̹����� ��ġ�� i���� ���� �ݺ����� �����Ų��.
            Thread.sleep(2);                     //�̹����� �������� ��� ���߾� �����̴� ȿ���� �ش�.
            icon = new ImageIcon("img/fireball.jpg");   //�������� �����Ѵ�.
            Image img = icon.getImage();            
            Image img_m = img.getScaledInstance(166, 235,Image.SCALE_SMOOTH);   //ũ�������� �̹����� ����
            icon = new ImageIcon(img_m);               //�����ܿ� ���ο� �̹����� ����
            setIcon(icon);                        //�������� set�Ѵ�.
         }
      } catch (Exception e) {
         // TODO: handle exception
      }
   }
}
