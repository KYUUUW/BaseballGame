import java.awt.Point;

public class RecordData 
{
//	private Point 	ptRecord;
	private int		nRecord;
	private int 	nStrike, nBall;
	
	// constructor 
	public RecordData() {
	//	ptRecord = new Point(70,80);
		nRecord = 0;
		nStrike = nBall = 0;		
	} 
	//public RecordData(Point pt, int nInput) {
//		ptRecord = pt;
//		nRecord = nInput;
//		nStrike = nBall = 0;
//	}
	public RecordData(int strike, int ball){
		//ptRecord = new Point(70,80);
		nRecord = 0;
		nStrike = strike;
		nBall = ball;
			
	}
	
	// get/set
	//public Point getPoint()		{ return ptRecord; }
	public int 	 getRecord()	{ return nRecord; }
	//public int 	 getPointX()	{ return ptRecord.x; }
	//public int 	 getPointY()	{ return ptRecord.y; }
	public int   getStrike()	{ return nStrike; }
	public int 	 getBall()		{ return nBall; }
	
	//public void setPoint(Point pt)		{ ptRecord = pt; }
	public void setRecord(int input)	{ nRecord = input; }
	public void setStrike(int strike)	{ nStrike = strike; }
	public void setBall(int ball)		{ nBall = ball; }
	public void setSB(int strike, int ball)  { nStrike = strike; nBall = ball; }
	public void setRSB(int input, int strike, int ball)
			{ nRecord = input; nStrike = strike; nBall = ball; }
	
	
	
	
} // LineDrawData class