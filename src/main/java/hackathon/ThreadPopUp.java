package hackathon;

public class ThreadPopUp extends Thread{
	
	
	
	
	public void run() {
		
		try {
			sleep(5000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		int nbP = 0;
		int nbD = 0;
		int nbB = 0;
		
		for(int i = 0 ; i<Main.posture.size();i++)
		{
			nbP += Main.posture.get(i)?0:1;
			nbD += Main.distance.get(i)?0:1;
			nbB += Main.brightness.get(i)?0:1;
		}
		
		
		
	}

}
