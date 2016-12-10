import java.awt.*;
import javax.swing.*;

public class BaseballFrame extends JFrame {				//프레임을 만드는 클래스
	
	BaseballFrame() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);	//	종료 형식
		setTitle("Baseball Game");						//	타틀네임
		
		MakingPanel primary = new MakingPanel(this);	//	사용자 정의 클래스 가져옴
		getContentPane().add(primary);					//	add 함
		pack();											//	패널에 사이즈에 맞게 프레임 크기 조절
		setVisible(true);								//	보이게함
	}
}
