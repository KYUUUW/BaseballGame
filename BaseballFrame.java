import java.awt.*;
import javax.swing.*;

public class BaseballFrame extends JFrame {				//�������� ����� Ŭ����
	
	BaseballFrame() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);	//	���� ����
		setTitle("Baseball Game");						//	ŸƲ����
		
		MakingPanel primary = new MakingPanel(this);	//	����� ���� Ŭ���� ������
		getContentPane().add(primary);					//	add ��
		pack();											//	�гο� ����� �°� ������ ũ�� ����
		setVisible(true);								//	���̰���
	}
}
