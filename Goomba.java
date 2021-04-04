//************************************** 
// Purpose: Goomba file 
// Author: SungWoo Kim 
// Date: 10/23/20
//**************************************

import java.awt.image.BufferedImage;
import java.awt.Graphics;

//*******************
//class Goomba
//*******************
public class Goomba extends Sprite 
{

//*******************
//member variables
//*******************
	static BufferedImage goomba_image; 
    static BufferedImage fireball_image; 
	Model model; 
    boolean goombaTop;
    int goombaImageNum;
    int direction; 
    int px, py;
    boolean isBurning;  
    int goombaFrames;
    int fireballSpeed;
    

//*******************
//Constructor
//*******************
   Goomba(int x, int y, Model m)
    {
        this.x = x;
        this.y = y;

        px = x;
		py = y;

		w = 48;
		h = 57;
		model = m;
        goombaTop = false;
        direction = 1; 
        fireballSpeed = 5;

		loadGoombaImage();
    }

//******************
//constructor
//******************
	 Goomba(Json ob, Model m)
	 {
       x = (int)ob.getLong("x");
       y = (int)ob.getLong("y");
       px = x;
	   py = y;
       direction = 1; 
       fireballSpeed = 5;
	   w = 48;
	   h = 57;
       model = m;
	   loadGoombaImage();
	 }

//*******************
//update method
//*******************
void update()
{
    savePreviousCoordinate();

    if(!goombaTop)
    {
        vert_velocity += 0.9;
        y += vert_velocity;
    }
    if (y > 400-h)
		{
			vert_velocity = 0;
			y = 400-h;
        }

    if(y < -50) // prevents goomba to jump off of the screen
		{
			y = -50;
		}	
    
   	x+=fireballSpeed*direction; 

    for (int i = 0; i < model.sprites.size(); i++)
		{	
			if(model.sprites.get(i).isTube() && this.hasItCollide(model.sprites.get(i)))
			{
                getOutOfTube((Tube)model.sprites.get(i));           
			} 
		}  

    if(isBurning)
		{	
			goombaFrames++;
		}     
}

//******************************
//savePreviousCoordinate method
//******************************
	void savePreviousCoordinate()
	{
		px = x;
		py = y;
	}

//******************************
//goombaBurning method
//******************************
	void goombaBurning()
	{
        isBurning = true; 
        fireballSpeed = 0;
        if(fireball_image == null)
            fireball_image = View.loadImage("goomba_fire.png"); 
	}

//***********************
//getOutOfTube method
//***********************
	void getOutOfTube(Tube t)
	{				
        //in the tube, but we were previously on the left hand side of tube
		if(x+w >= t.x && px + w <= t.x)
		{	 
            x = t.x - w;
            direction = direction*-1; 
        }   	 	
			  
		//in the tube, but we were previously on the right hand side of tube
		if(x <= t.x + t.w && px >= t.x + t.w)
		{	 
            x = t.x + t.w;
            direction = direction*-1;
        }   
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
// loadGoombaImage method
//****************************
	 void loadGoombaImage()
	 {
		 if (goomba_image == null)
			goomba_image = View.loadImage("goomba1.png"); 	// load goomba image
	 }

//***********************
//isGoomba method
//***********************	
	boolean isGoomba()
	{
		return true; 
	}

//***********************
//drawYourself method
//***********************		
	void drawYourself(Graphics g)
	{
		if(isBurning)
        {
            g.drawImage(fireball_image, x - model.mario.x + model.mario.marioViewLocation, y, -w, h, null);
        }
        else
            g.drawImage(goomba_image, x - model.mario.x + model.mario.marioViewLocation, y, null);	
	}				

}
