//************************************** 
// Purpose: Tube file 
// Author: SungWoo Kim 
// Date: 10/09/20
//**************************************

import java.awt.image.BufferedImage;

//*******************
//class Tube
//*******************
public class Tube 
{

//*******************
//member variables
//*******************
	int x, y; 
	final int width = 55;
	final int height = 400;
	static BufferedImage tube_image; 

//*******************
//Constructor
//*******************
   Tube(int x, int y)
    {
        this.x = x;
        this.y = y;

		if (tube_image == null)
			tube_image = View.loadImage("tube.png"); 	// load tube image
    }

//*******************
//class didIClickOnATube
//*******************
   boolean didIClickOnATube(int mouse_x, int mouse_y)
	{
		if (mouse_x >= x && mouse_x <= x + width && mouse_y >= y && mouse_y <= y + height) //Tube image has 55W 400H
			return true;
	
		else
			return false; 
	}

//*******************
//Json marshal
//*******************	
	 Json marshal()				// Marshals this object into a JSON DOM
	 {
	     Json ob = Json.newObject();
	     ob.add("tube_x", x);
	     ob.add("tube_y", y);
	     return ob;
	 }

//*****************************
// //Unmarshaling constructor
//*****************************	 	
	 Tube(Json ob)
	 {
       x = (int)ob.getLong("tube_x");
       y = (int)ob.getLong("tube_y");
       //System.out.println("You've loaded a tube at (" + x + ", " + y + ")"); 
	 }
}
