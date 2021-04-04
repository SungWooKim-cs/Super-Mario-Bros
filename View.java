//************************************** 
// Purpose: View file 
// Author: SungWoo Kim 
// Date: 10/23/20
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

	static int MAX_SCROLL;
	
//*******************
//Constructor
//*******************	
	public View(Controller c, Model m)
	{
		c.setView(this);
		this.model = m;
		MAX_SCROLL = 2500; 
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

		for (int i = 0; i < model.sprites.size(); i++)
			{
				model.sprites.get(i).drawYourself(g);
			}
	}
}
