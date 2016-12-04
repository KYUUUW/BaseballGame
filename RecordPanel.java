import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import java.util.*;

public class RecordPanel extends JPanel
{
	private JLabel lblTitle, lblRecord;  //����� ����ڰ� PlayScreen���� �Է��� ��(nRecord, nStrike, nBall)�� ������ JLabel ���� 
	private String strRecord;		//nRecord, nStrike, nBall�� �ؽ�Ʈ�� ���� ������ String ����
	int nRecord, nStrike, nBall;	//����ڰ� PlayScreen���� �Է��� ���� strike, ball ������ �Է¹��� ������ ���� ���� 

	//constructor
	public RecordPanel() {
		//RecordPanel�� ũ��, ���� �����ϰ� ������� ��ġ�� �� �ֵ��� Layout ���� null�� ���� 
		setPreferredSize(new Dimension(200,450));
		setBackground(new Color(29,29,27));
		setLayout(null);

		//nRecord, nStrike, nBall�� 0���� �ʱ�ȭ
		nRecord = nStrike = nBall = 0;

		//'Record'��� Ÿ��Ʋ�� ���� lblTitle �����ϰ� ������, ���ڻ�, ��Ʈ, ���� �����ϰ� RecordPanel�� ���̵��� �߰�
		lblTitle = new JLabel("Record");
		lblTitle.setBounds(50,10,100,50);
		lblTitle.setForeground(Color.white);
		lblTitle.setFont(new Font("Verdana", Font.BOLD, 21));
		lblTitle.setHorizontalAlignment(SwingConstants.CENTER);
		lblTitle.setLayout(null);
		add(lblTitle);

		//String ���� strRecord �����ϰ� �ؽ�Ʈó�� ����� �� �ֵ��� <html> �±� ����Ͽ� ��ȯ
		strRecord = new String("<html> </html>");

		//strRecord �ؽ�Ʈ�� ���� lblRecord �����ϰ� ������, ����, ���ڻ�, ��Ʈ �����ϰ� RecordPanel�� ���̵��� �߰�
		lblRecord = new JLabel(strRecord);
		lblRecord.setBounds(0,70,200,300);
		lblRecord.setHorizontalAlignment(SwingConstants.CENTER);
		lblRecord.setVerticalAlignment(SwingConstants.TOP);
		lblRecord.setForeground(Color.white);
		lblRecord.setFont(new Font("Verdana", Font.PLAIN, 15));
		lblRecord.setVisible(false);
		lblRecord.setLayout(null);	//����ڰ� ����(nRecord)�� �Է��ϱ� ������ lblRecord ������ �ʵ��� ���� 
		this.add(lblRecord);
	}


	//����ڰ� PlayScreen���� �Է��� ���� ������ �ؽ�Ʈ�� �డ���ֱ� ���� �żҵ�  
	public void addText(int input, int strike, int ball){
		//����ڰ� PlayScreen���� �Է��� ���� nRecord, nStrike, nBall�� ����
		nRecord = input;
		nStrike = strike;
		nBall = ball;

		String strTemp = new String();

		lblRecord.setVisible(true);  //����ڰ� PlayScreen���� ���� �Է����� ��� lblRecord�� ���̰� �� 


		//����ڰ� ���ο� ���� PlayScreen���� �Է��ϱ� ������ lblRecord�� ����Ǿ� �ִ� �ؽ�Ʈ�� strTemp�� ����
		strTemp = lblRecord.getText(); 
		//�������� �ԷµǾ� �ִ� ���� (<html> </html>) ������ ���� strTemp�� ���� �� �� �ֵ��� 
		//substring method ����Ͽ� �տ��� 6��° �ε����� ���ں��� �ڿ��� 7��° ���� ���̸� strTemp�� ���� 
 		strTemp = strTemp.substring(6 , strTemp.length() - 8);
 		//strTemp�� �ٽ� <html> �±׸� �߰��Ͽ� �ؽ�Ʈ �߿� �ٹٲ��� �� �� �ִ� <br/> �±׸� ����� �� �ֵ��� �Ѵ�
 		//nStrike, nBall, nRecord�� ������ �����̱� ������ String.valueOf() �żҵ带 ����Ͽ� ���ڿ��� �ٲ��ش�
 		//���������� strTemp ���ڿ��� �������� �ٿ�, ����ڰ� ���� �Է��ϸ� ���ο� ���� ���� �� ���� �߰��Ǽ� ���������� �Ѵ�
		strTemp = "<html>" +
				String.valueOf(nStrike)+"S"+
				String.valueOf(nBall)+ "B " + 
				String.valueOf(nRecord) + "<br/>" +
				strTemp + " </html>"; 
		System.out.println(strTemp); //�ý��� ȭ�鿡 ���
		lblRecord.setText(strTemp);	 //����� strTemp�� �ؽ�Ʈ�� lblRecord�� ���� 
		this.add(lblRecord);		//RecordPanel�� ���ο� ���ڵ带 ����ϱ� ���� lblRecord�� �ٽ� �߰� 

	} // addText()


	//get,set	
	//PlayScreen class���� �Էµ� ���� RecordPanel�� �����ϱ� ���� get, set �żҵ� 
	public int getRecord()		{ return nRecord; }
	public int getStrike()		{ return nStrike; }
	public int getBall()		{ return nBall; }
	
	public void setRecord(int input)	{ nRecord = input; }
	public void setStrike(int strike)	{ nStrike = strike; }
	public void setBall(int ball)		{ nBall = ball; }	
	public void setRSB(int input, int strike, int ball) 
		 								{ nRecord = input; 
		 								  nStrike = strike; 
		 								  nBall = ball; }
			

} // RecordPanel class 