import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import java.util.*;

public class RecordPanel extends JPanel
{
	private JLabel lblTitle, lblRecord;
	private RecordData data;
	private ArrayList<RecordData> dataList;
	private String strRecord;
	


	//constructor
	public RecordPanel() {
		setPreferredSize(new Dimension(200,450));
		setBackground(Color.black);
		setLayout(null);

		data = new RecordData();
		dataList = new ArrayList<RecordData>();
		
		lblTitle = new JLabel("Record");
		lblTitle.setBounds(50,10,100,50);
		lblTitle.setForeground(Color.white);
		lblTitle.setFont(new Font("Verdana", Font.BOLD, 21));
		lblTitle.setHorizontalAlignment(SwingConstants.CENTER);
		lblTitle.setLayout(null);
		add(lblTitle);
		
//		ptRecord = new Point(70, 80);
		
		//strRecord = new String(""+data.getRecord());
		
		lblRecord = new JLabel("<html>Record<br>new line</html>");
		lblRecord.setBounds(50,70,100,300);
		lblRecord.setHorizontalAlignment(SwingConstants.CENTER);
		lblRecord.setVerticalAlignment(SwingConstants.TOP);
		lblRecord.setForeground(Color.white);
		lblRecord.setFont(new Font("Verdana", Font.PLAIN, 15));
		//lblRecord.setText(strRecord);
		lblRecord.setLayout(null);
		this.add(lblRecord);
	
	}
	
	
	public void addText(String str){
		
		
		
	} // addText()
	
	public void rePaint() {
		removeAll();
		
		
		revalidate();
		repaint();

		
	} // rePaint()
	
	
	
	//get,set
	
	public RecordData getRecordData()	{ return data; }
	public int getData()				{ return data.getRecord(); }
//	public Point getDataPoint()			{ return data.getPoint(); }
	public int getStrike()				{ return data.getStrike(); }
	public int getBall()				{ return data.getBall(); }
	
//	public void setRecordData(Point pt, int input) {
//		data.setPoint(pt);
//		data.setRecord(input);
//	}
	public void setInput(int input) { data.setRecord(input); }
//	public void setPoint(Point pt)	{ data.setPoint(pt); }
	public void setStrike(int strike) { data.setStrike(strike); }
	public void setBall(int ball) 	{ data.setBall(ball); }
	public void setSB(int strike, int ball) { data.setSB(strike, ball); }
	public void setRSB(int input, int strike, int ball) 
			{ data.setRSB(input, strike, ball); }
			
		
	
} // RecordPanel class 