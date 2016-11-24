import java.awt.Point;

public class RecordData 
{
	private int		nRecord;
	private int 	nStrike, nBall;
	
	// constructor 
	public RecordData() {
		nRecord = 0;
		nStrike = nBall = 0;		
	} 

	public RecordData(int strike, int ball){
		nRecord = 0;
		nStrike = strike;
		nBall = ball;
			
	}
	
	// get/set
	public int 	 getRecord()	{ return nRecord; }  // to get number from PlayScreen 
	public int   getStrike()	{ return nStrike; }
	public int 	 getBall()		{ return nBall; }
	
	public void setRecord(int input)	{ nRecord = input; }
	public void setStrike(int strike)	{ nStrike = strike; }
	public void setBall(int ball)		{ nBall = ball; }
	public void setSB(int strike, int ball)  { nStrike = strike; nBall = ball; }
	public void setRSB(int input, int strike, int ball)
			{ nRecord = input; nStrike = strike; nBall = ball; }
	
	
	
} // LineDrawData class