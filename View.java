//************************************** 
// Purpose: View file 
// Author: SungWoo Kim 
// Date: 10/09/20
//**************************************

import javax.swing.JPanel;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;
import java.io.IOException;
import java.io.File;
import javax.swing.JButton;
import java.awt.Color;

//*************
//class View
//*************
class View extends JPanel
{

//*******************
//member variables
//*******************	
	Model model; 
	Mario mario; 
	int marioViewLocation;
	
//*******************
//Constructor
//*******************	
	public View(Controller c, Model m)
	{
		c.setView(this);
		this.model = m;
		
		
		marioViewLocation = 100;					//view location only 

		// mario_images = new BufferedImage[5];
		// mario_images[0] = loadImage("mario1.png");
		// mario_images[1] = loadImage("mario2.png");
		// mario_images[2] = loadImage("mario3.png");
		// mario_images[3] = loadImage("mario4.png");
		// mario_images[4] = loadImage("mario5.png");
	}

//*******************
//loadImage method
//*******************
	static BufferedImage loadImage(String filename)
	{
		BufferedImage im = null;
		try 
		{
			im = ImageIO.read(new File(filename));
			System.out.println(filename + " has been loaded");
		} 
		catch(Exception e) 
		{
			e.printStackTrace(System.err);
			System.exit(1);
		}
		return im;
	}

//*************************
//paintComponent method
//*************************
	public void paintComponent(Graphics g) // method that will draw it
	{
		//draw sky
		g.setColor(new Color(128, 255, 255));
		g.fillRect(0, 0, this.getWidth(), this.getHeight());

		//draw ground
		 
		g.setColor(new Color(100, 255, 80));
		g.fillRect(0, 400, this.getWidth(), 100);

		//draw tubes
		for(int i = 0; i < model.tubes.size(); i++)
		{
			// if (tube_image == null)
			// 	this.tube_image = loadImage("tube.png");
		
			Tube t = model.tubes.get(i);
			g.drawImage(Tube.tube_image, t.x - model.mario.x+marioViewLocation, t.y, null);		
		}

		// draw Mario
		g.drawImage(model.mario.mario_images[model.mario.marioImageNum], marioViewLocation, model.mario.y, null);
	}
}
