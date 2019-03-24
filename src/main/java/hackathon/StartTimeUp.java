package hackathon;

import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;

import javax.imageio.ImageIO;

import com.github.sarxos.webcam.Webcam;

public class StartTimeUp extends Thread{
	private boolean alive = false;
	private Webcam webcam;
	static private int compteur = 0;
	private int threshold;
	
	public StartTimeUp(int threshold, Webcam webcam)
	{
		super();
		alive = false;
		compteur = 0;
		this.webcam = webcam;
		this.threshold = threshold;
	}

	public void setAlive(boolean alive) {
		this.alive = alive;
	}
	
	public int getCOmpteur() {
		return compteur;
	}

	public void setCompteur(int compteur) {
		this.compteur = compteur;
	}
	
	public void run() {
		while (true) 
		{
			
			System.out.println(this.compteur);
			BufferedImage temp = webcam.getImage();

			try {
				sleep(1000);

			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			String path = "./pictures/"+(Interface.imageID++)+".png";
			File file = new File(path);
			if(temp!=null)
				try {
					ImageIO.write(temp, "PNG", file);
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			Process p = null;
			try {
				p = Runtime.getRuntime().exec("python3 TimeUp.py "+ path);
				BufferedReader in = new BufferedReader(new InputStreamReader(p.getInputStream()));
				System.out.println(in.readLine());

				String ret = in.readLine();
				System.out.println(ret);

				if ((ret != null) && (ret.compareTo("True") == 0))
				{
					System.out.println(ret);
					this.compteur++;
				}	
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			if (this.compteur > this.threshold)
			{
				System.out.println("trop longtemps");
				return;
			}
			file.delete();
		}
	}
	
	
	
	
	}
