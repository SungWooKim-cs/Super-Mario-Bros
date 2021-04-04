//************************************** 
// Purpose: Tube file 
// Author: SungWoo Kim 
// Date: 10/23/20
//**************************************

import java.awt.image.BufferedImage;
import java.awt.Graphics;

//*******************
//class Tube
//*******************
public class Tube extends Sprite 
{

//*******************
//member variables
//*******************
	static BufferedImage tube_image; 
	Model model; 

//*******************
//Constructor
//*******************
   Tube(int x, int y, Model m)
    {
        this.x = x;
        this.y = y;
		w = 55;
		h = 400;
		model = m;

		loadTubeImage();
    }

//*****************************
//constructor
//*****************************	 	
	 Tube(Json ob, Model m)
	 {
       x = (int)ob.getLong("x");
       y = (int)ob.getLong("y");
       //System.out.println("You've loaded a tube at (" + x + ", " + y + ")"); 
	   w = 55;
	   h = 400;
	   model = m;
	   loadTubeImage();
	 }

//*******************
//update method
//*******************
void update()
{
}

//*******************
//Json marshal
//*******************	
	 Json marshal()				// Marshals this object into a JSON DOM
	 {
	     Json ob = Json.newObject();
	     ob.add("x", x);
	     ob.add("y", y);
	     return ob;
	 }


//*****************************
// loadTubeImage method
//****************************
	 void loadTubeImage()
	 {
		 if (tube_image == null)
			tube_image = View.loadImage("tube.png"); 	// load tube image
	 }

//***********************
//isTube method
//***********************	
	boolean isTube()
	{
		return true; 
	}
	
//***********************
//drawYourself method
//***********************		
	void drawYourself(Graphics g)
	{
		g.drawImage(tube_image, x - model.mario.x + model.mario.marioViewLocation, y, null);		
	}				

}
