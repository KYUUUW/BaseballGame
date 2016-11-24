import javax.swing.JFrame;

public class RecordExam
{	
		public static void main(String[] args){
			JFrame frame = new JFrame ("Record Exam"); 
			frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);	  
			frame.setResizable(false);                                                     
			
			RecordPanel record = new RecordPanel();
			
			frame.getContentPane().add(record);
			frame.pack();		 
			frame.setVisible(true);                             
			
		}	// main()
	
} 