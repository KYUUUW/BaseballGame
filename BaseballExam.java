import java.awt.*;
import javax.swing.*;

/*
 * 추상메소드란.
 * 클래스 선언시 메소드에 그냥 메소드 이름만 지정하고,
 * 그것의 구현은 그 클래스를 상속받은 클래스에서 오버라이딩 해서  구현하는것.
 * 다형성을 갖을 수 있다.
 * 추상 메소드는 상속받은 클래스에서 무조건 오버라이딩 해줘야 함.
 * 
 * 추상클래스란.
 * 추상 메소드를 갖고 있는 클래스.
 * 추상클래스는 생성이 불가능하다.
 * 상속받아서만 쓰는 클래스
 * 
 */

public class BaseballExam {
	
	
	public static void main(String ar[]) {
		
		JFrame fr = new JFrame("UI Practice");
		fr.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		MakingPanel primary = new MakingPanel();
		fr.getContentPane().add(primary);
		fr.pack();
		fr.setVisible(true);
		
		//fr.setResizable(false);
		
		//02-3408-3837 광 324
	}
	
}
