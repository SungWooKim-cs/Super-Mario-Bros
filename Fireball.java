//************************************** 
// Purpose: Fireball file 
// Author: SungWoo Kim 
// Date: 10/23/20
//**************************************

import java.awt.image.BufferedImage;
import java.awt.Graphics;

//*******************
//class Fireball
//*******************
class Fireball extends Sprite
{
	
//*********************
//member variables
//*********************

static BufferedImage fireball_image;
Model model;  
int direction; 
int goombaImageNum;
int px, py;
int fireballSpeed = 10;

	
//*********************
//Constructor
//*********************
	public Fireball (int x, int y, Model m) 
	{
		this.x = x;
        this.y = y;

        px = x;
		py = y;

		w = 47;
		h = 47;
		model = m;
        direction = 1; 

		loadFireballImage();

	}


//*********************
//update method
//*********************
	void update()
	{
        
	x+=fireballSpeed*direction;   
    vert_velocity+= 5;
    y += vert_velocity;
    savePreviousCoordinate();

    if (y > 400-h)
		{
			vert_velocity = -38; 
			y = 400-h;
        }

    for(int i = 0; i < model.sprites.size(); i++)
	    {	
            if(model.sprites.get(i).isGoomba() && this.hasItCollide(model.sprites.get(i)) && !((Goomba)model.sprites.get(i)).isBurning)
            {
              ((Goomba)model.sprites.get(i)).goombaBurning();  
            }	
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

//*****************************
// loadFireballImage method
//****************************
	 void loadFireballImage()
	 {
		 if (fireball_image == null)
			fireball_image = View.loadImage("fireball.png"); 	// load fireball image
	 }

//***********************
//isFireball method
//***********************	
	boolean isFireball()
	{
		return true; 
	}

//***********************
//drawYourself method
//***********************		
	void drawYourself(Graphics g)
	{	        
		g.drawImage(fireball_image, x-model.mario.x +model.mario.marioViewLocation -w/2, y, null);
	}				
}