package hackathon;


import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.Shape;
import java.awt.Toolkit;
import java.awt.image.BufferedImage;
import java.awt.image.ImageObserver;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.text.AttributedCharacterIterator;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JFrame;

import com.github.sarxos.webcam.Webcam;

public class TakePictureThread extends Thread{

	private boolean takes = false;
	private Webcam webcam;
	
	public TakePictureThread(Webcam webcam)
	{
		super();
		this.webcam = webcam;
		takes = false;
	}
	
	public boolean isTakes() {
		return takes;
	}

	public void setTakes(boolean takes) {
		this.takes = takes;
	}

	public void run() {
		
		ImageIcon icon;
		ImageIcon imageIcon;
		
		while(true)
		{
			
			try {
				BufferedImage temp = webcam.getImage();
				
				
				
				
				if(takes) {
					String path = "./pictures/"+(Interface.imageID++)+".png";
					File file = new File(path);
					if(temp!=null)
						ImageIO.write(temp, "PNG", file);
					
					Process p = Runtime.getRuntime().exec("python3 traiter_image.py "+path);
					
					BufferedReader in = new BufferedReader(new InputStreamReader(p.getInputStream()));
					String ret = in.readLine();
					
					boolean posture = true;
					boolean distance = true;
					boolean brightness = true;
					
					if(ret != null)
					{
						posture = ret.split(";")[0].compareTo("True") == 0;
						distance = ret.split(";")[1].compareTo("True") == 0;
						brightness = ret.split(";")[2].compareTo("True") == 0;
					}
					
					
					if(!posture) {
						//JFrame f = new JFrame("Attention");
						//f.setVisible(true);
						icon = new ImageIcon("./assets/bad.png");
					}else
						icon = new ImageIcon("./assets/good.png");
					
					imageIcon = new ImageIcon(icon.getImage().getScaledInstance(200, 200, Image.SCALE_DEFAULT));
					Interface.labelStatus.setIcon(imageIcon);
					temp = ImageIO.read(new File(path));
					file.delete();
				}
				
				Interface.imageJpanel.setImage(temp);
				Interface.imageJpanel.invalidate();
				Interface.imageJpanel.repaint();
				
			} catch (Exception e1) 
			{
				e1.printStackTrace();
			}
			/*
			try {
				this.sleep(100);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				System.out.println(e.getMessage());
			}
			*/
		}

	}

}
