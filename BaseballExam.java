import java.awt.*;
import javax.swing.*;

/*
 * �߻�޼ҵ��.
 * Ŭ���� ����� �޼ҵ忡 �׳� �޼ҵ� �̸��� �����ϰ�,
 * �װ��� ������ �� Ŭ������ ��ӹ��� Ŭ�������� �������̵� �ؼ�  �����ϴ°�.
 * �������� ���� �� �ִ�.
 * �߻� �޼ҵ�� ��ӹ��� Ŭ�������� ������ �������̵� ����� ��.
 * 
 * �߻�Ŭ������.
 * �߻� �޼ҵ带 ���� �ִ� Ŭ����.
 * �߻�Ŭ������ ������ �Ұ����ϴ�.
 * ��ӹ޾Ƽ��� ���� Ŭ����
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
		
		//02-3408-3837 �� 324
	}
	
}
