package hackathon;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.Label;
import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;

import javax.imageio.ImageIO;

import com.github.sarxos.webcam.Webcam;


public class Interface extends JFrame implements ActionListener{
	
	
	private JButton start = new JButton("Start");
	private JButton closeWebcam = new JButton("Pause");
	private JButton setPosition = new JButton("Take position");
	private Webcam webcam = Webcam.getDefault();
	static ImageJpanel imageJpanel = new ImageJpanel();
	static JLabel labelStatusPosture;
	static JLabel labelStatusDistance;
	static JLabel labelStatusBrightness;
    private TakePictureThread thread;
	static int imageID = 0;
	
	private boolean positionTaken = false;
	
	
	public Interface()
	{
		
		JPanel container = new JPanel();

        this.setTitle("Bordeaux Hackthon");

        this.setSize(800, 600);

        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        container.setLayout(new BorderLayout());
        
        start.addActionListener(this);
        closeWebcam.addActionListener(this);
        setPosition.addActionListener(this);
        
        container.add(imageJpanel,BorderLayout.CENTER);
        
        JPanel buttonsPanel = new JPanel();
       
        buttonsPanel.add(setPosition);
        buttonsPanel.add(start);
        //buttonsPanel.add(closeWebcam);
        
        container.add(buttonsPanel, BorderLayout.SOUTH);

        this.setContentPane(container);
        
        Webcam webcam = Webcam.getDefault();
		webcam.setViewSize(new Dimension(640, 480));
		webcam.open();
		
		ImageIcon icon = new ImageIcon("./assets/goodT.png");
		ImageIcon imageIcon = new ImageIcon(icon.getImage().getScaledInstance(150, 150, Image.SCALE_DEFAULT));
		labelStatusPosture = new JLabel(imageIcon);
		
		ImageIcon icon2 = new ImageIcon("./assets/goodT.png");
		ImageIcon imageIcon2 = new ImageIcon(icon2.getImage().getScaledInstance(150, 150, Image.SCALE_DEFAULT));
		labelStatusDistance = new JLabel(imageIcon2);
		
		ImageIcon icon3 = new ImageIcon("./assets/badPosture.jpg");
		ImageIcon imageIcon3 = new ImageIcon(icon3.getImage().getScaledInstance(150, 150, Image.SCALE_DEFAULT));
		labelStatusBrightness = new JLabel(imageIcon3);
		
		JPanel statusPanel = new JPanel();
		statusPanel.setLayout(new GridLayout(3, 0));
		
		
		statusPanel.add(labelStatusPosture);
		statusPanel.add(new JLabel(imageIcon2));
		statusPanel.add(new JLabel(imageIcon3));
		
        container.add(statusPanel,BorderLayout.EAST);
		
        
        if(thread == null || !thread.isAlive())
		{
			thread = new TakePictureThread(webcam);
			thread.start();
		}
        
        
		this.setVisible(true);
        
        
        
	
	
	}

	public void actionPerformed(ActionEvent e) {
		
		if(positionTaken) {
		
			if(e.getSource().equals(start))
			{
				thread.setTakes(true);
				ThreadPopUp thread = new ThreadPopUp();
				thread.start();
			}
			if(e.getSource().equals(closeWebcam))
			{
				if(closeWebcam.getText().compareTo("Pause")==0)
				{
					if(thread.isAlive()) {
						thread.setTakes(false);
						thread.interrupt();
						webcam.close();
						closeWebcam.setText("Resume");
						
					}
				}
				else
				{
					
					if(thread != null) {
						webcam.open();
						thread.resume();
						thread.setTakes(true);
						closeWebcam.setText("Pause");
					}
					
				}
			}
			
		}
		if(e.getSource().equals(setPosition))
		{
			BufferedImage temp = webcam.getImage();
			try {
				String path = "./pictures/"+(Interface.imageID++)+".png";
				ImageIO.write(temp, "PNG", new File(path));
				
				positionTaken = true;
				
				Process p = Runtime.getRuntime().exec("python3 ./initialize.py "+path);
				p.waitFor();
				BufferedReader in = new BufferedReader(new InputStreamReader(p.getInputStream()));
				
				String ret = in.readLine();
				System.out.println(ret);
				
				
			} catch (Exception e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
		
		
	}

}
