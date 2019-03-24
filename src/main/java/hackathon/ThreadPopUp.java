package hackathon;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

public class ThreadPopUp extends Thread{
	
	
	
	
	public void run() {
		
		while(true)
		{
			try {
				sleep(5000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			float nbP = 0;
			float nbD = 0;
			float nbB = 0;
			
			for(int i = 0 ; i<Main.posture.size();i++)
			{
				System.out.println(Main.distance.get(i));
				nbP += Main.posture.get(i)?0:1;
				nbD += Main.distance.get(i)?0:1;
				nbB += Main.brightness.get(i)?0:1;
			}
			String msg = "";
			System.out.println(nbP + " "+ nbD+ " "+nbB);
			System.out.println(nbP/Main.posture.size() + " "+ nbD/Main.posture.size()+ " "+nbB/Main.posture.size());
			
			
			if(Main.posture.size()>0) {
				
				if(nbP/Main.posture.size()>0.3)
				{
					msg+= "Attention vous êtes mal positionné\n";
				}
				if(nbD/Main.distance.size()>0.3)
				{
					msg+= "Attention vous êtes trop près de l'écran\n";
				}
				if(nbB/Main.brightness.size()>0.3)
				{
					msg+= "Attention vous êtes dans un endroit sombre, pensez à baisser votre luminosité\n";
				}
				if(msg.compareTo("")!=0)
				{
					JFrame pop = new JFrame("POPUP");
					JOptionPane.showMessageDialog(pop, msg);
				}
				
				Main.brightness.clear();
				Main.posture.clear();
				Main.distance.clear();
				
			}
			
		}
		
		
		
		
	}

}
