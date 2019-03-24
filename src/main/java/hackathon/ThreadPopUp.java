package hackathon;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import sun.audio.*;

public class ThreadPopUp extends Thread{
	
	
	static boolean brightness = false; 
	
	
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
				nbP += Main.posture.get(i)?0:1;
				nbD += Main.distance.get(i)?0:1;
				nbB += Main.brightness.get(i)?0:1;
			}
			String msg = "";
			
			
			if(Main.posture.size()>0) {
				
				if(nbP/Main.posture.size()>0.5)
				{
					msg+= "Attention vous êtes mal positionné\n";
				}
				if(nbD/Main.distance.size()>0.5)
				{
					msg+= "Attention vous êtes trop près de l'écran\n";
				}
				if(nbB/Main.brightness.size()>0.5)
				{
					msg+= "Attention vous êtes dans un endroit sombre, pensez à baisser votre luminosité\n";
				}
				if(msg.compareTo("")!=0)
				{
					
					try {
						// open the sound file as a Java input stream
					    InputStream in = new FileInputStream("./src/main/resources/alerte.wav");

					    // create an audiostream from the inputstream
					    AudioStream audioStream;
						audioStream = new AudioStream(in);

					    // play the audio clip with the audioplayer class
					    AudioPlayer.player.start(audioStream);
						JFrame pop = new JFrame("POPUP");
						JOptionPane.showMessageDialog(pop, msg);
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
				
				Main.brightness.clear();
				Main.posture.clear();
				Main.distance.clear();
				
			}
			
		}
		
		
		
		
	}

}
