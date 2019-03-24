package hackathon;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.Serializable;

import javax.swing.JPanel;

public class ImageJpanel extends JPanel implements Serializable{
	
	private BufferedImage image;
	

    public BufferedImage getImage() {
		return image;
	}

	public void setImage(BufferedImage image) {
		this.image = image;
	}

	public ImageJpanel() {
    	
    }
	
	public ImageJpanel(BufferedImage image) {
    	this.image = image;
    }
	
	@Override
	public void paint(Graphics g) {
		super.paint(g);
		if(image != null)
        	g.drawImage(image, 0, 0, this);
	}

}
