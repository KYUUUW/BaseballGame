import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import java.util.*;

public class RecordPanel extends JPanel
{
	private JLabel lblTitle, lblRecord;  //제목과 사용자가 PlayScreen에서 입력한 값(nRecord, nStrike, nBall)을 보여줄 JLabel 선언 
	private String strRecord;		//nRecord, nStrike, nBall을 텍스트로 만들어서 보여줄 String 선언
	int nRecord, nStrike, nBall;	//사용자가 PlayScreen에서 입력한 값과 strike, ball 갯수를 입력받을 정수형 변수 선언 

	//constructor
	public RecordPanel() {
		//RecordPanel의 크기, 색을 지정하고 마음대로 배치할 수 있도록 Layout 값을 null로 지정 
		setPreferredSize(new Dimension(200,450));
		setBackground(new Color(29,29,27));
		setLayout(null);

		//nRecord, nStrike, nBall을 0으로 초기화
		nRecord = nStrike = nBall = 0;

		//'Record'라는 타이틀을 넣을 lblTitle 생성하고 사이즈, 글자색, 폰트, 정렬 설정하고 RecordPanel에 보이도록 추가
		lblTitle = new JLabel("Record");
		lblTitle.setBounds(50,10,100,50);
		lblTitle.setForeground(Color.white);
		lblTitle.setFont(new Font("Verdana", Font.BOLD, 21));
		lblTitle.setHorizontalAlignment(SwingConstants.CENTER);
		lblTitle.setLayout(null);
		add(lblTitle);

		//String 변수 strRecord 생성하고 텍스트처럼 사용할 수 있도록 <html> 태그 사용하여 전환
		strRecord = new String("<html> </html>");

		//strRecord 텍스트를 보일 lblRecord 생성하고 사이즈, 정렬, 글자색, 폰트 설정하고 RecordPanel에 보이도록 추가
		lblRecord = new JLabel(strRecord);
		lblRecord.setBounds(0,70,200,300);
		lblRecord.setHorizontalAlignment(SwingConstants.CENTER);
		lblRecord.setVerticalAlignment(SwingConstants.TOP);
		lblRecord.setForeground(Color.white);
		lblRecord.setFont(new Font("Verdana", Font.PLAIN, 15));
		lblRecord.setVisible(false);
		lblRecord.setLayout(null);	//사용자가 숫자(nRecord)를 입력하기 전까지 lblRecord 보이지 않도록 설정 
		this.add(lblRecord);
	}


	//사용자가 PlayScreen에서 입력한 정수 값들을 텍스트에 축가해주기 위한 매소드  
	public void addText(int input, int strike, int ball){
		//사용자가 PlayScreen에서 입력한 값을 nRecord, nStrike, nBall에 저장
		nRecord = input;
		nStrike = strike;
		nBall = ball;

		String strTemp = new String();

		lblRecord.setVisible(true);  //사용자가 PlayScreen에서 값을 입력했을 경우 lblRecord를 보이게 함 


		//사용자가 새로운 값을 PlayScreen에서 입력하기 전까지 lblRecord에 저장되어 있던 텍스트를 strTemp에 저장
		strTemp = lblRecord.getText(); 
		//이전까지 입력되어 있던 글자 (<html> </html>) 사이의 값만 strTemp에 저장 할 수 있도록 
		//substring method 사용하여 앞에서 6번째 인덱스의 글자부터 뒤에서 7번째 글자 사이면 strTemp에 저장 
 		strTemp = strTemp.substring(6 , strTemp.length() - 8);
 		//strTemp에 다시 <html> 태그를 추가하여 텍스트 중에 줄바꿈을 할 수 있는 <br/> 태그를 사용할 수 있도록 한다
 		//nStrike, nBall, nRecord는 정수형 변수이기 때문에 String.valueOf() 매소드를 사용하여 문자열로 바꿔준다
 		//마지막으로 strTemp 문자열을 마지막에 붙여, 사용자가 값을 입력하면 새로운 값이 이전 값 위에 추가되서 보여지도록 한다
		strTemp = "<html>" +
				String.valueOf(nStrike)+"S"+
				String.valueOf(nBall)+ "B " + 
				String.valueOf(nRecord) + "<br/>" +
				strTemp + " </html>"; 
		System.out.println(strTemp); //시스템 화면에 출력
		lblRecord.setText(strTemp);	 //변경된 strTemp의 텍스트로 lblRecord에 설정 
		this.add(lblRecord);		//RecordPanel에 새로운 레코드를 출력하기 위해 lblRecord를 다시 추가 

	} // addText()


	//get,set	
	//PlayScreen class에서 입력된 값을 RecordPanel에 저장하기 위한 get, set 매소드 
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