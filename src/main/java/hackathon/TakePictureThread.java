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
		
		ImageIcon icon,icon2,icon3;
		ImageIcon imageIcon,imageIcon2,imageIcon3;
		
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
						
						
						
						
						Main.posture.add(posture);
						Main.distance.add(distance);
						Main.brightness.add(brightness);
					}
					
					
					if(!posture) {
						icon = new ImageIcon("./assets/badPos.png");
					}else
						icon = new ImageIcon("./assets/goodT.png");
					
					if(!distance) {
						icon2 = new ImageIcon("./assets/badDis.png");
					}else
						icon2 = new ImageIcon("./assets/goodT.png");
					
					if(!brightness) {
						icon3 = new ImageIcon("./assets/badB.jpg");
					}else
						icon3 = new ImageIcon("./assets/goodT.png");
					
					
					imageIcon = new ImageIcon(icon.getImage().getScaledInstance(150, 150, Image.SCALE_DEFAULT));
					Interface.labelStatusPosture.setIcon(imageIcon);
					
					imageIcon2 = new ImageIcon(icon2.getImage().getScaledInstance(150, 150, Image.SCALE_DEFAULT));
					Interface.labelStatusDistance.setIcon(imageIcon2);
					
					imageIcon3 = new ImageIcon(icon3.getImage().getScaledInstance(150, 150, Image.SCALE_DEFAULT));
					Interface.labelStatusBrightness.setIcon(imageIcon3);
					
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
