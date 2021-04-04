//************************************** 
// Purpose: Mario file 
// Author: SungWoo Kim 
// Date: 10/23/20
//**************************************

import java.awt.image.BufferedImage;
import java.awt.Graphics;

//*******************
//class Mario
//*******************
class Mario extends Sprite
{
	
//*********************
//member variables
//*********************
	int px, py;

	int countFrames;
	int marioImageNum; 
	boolean marioTop;
	static BufferedImage[] mario_images; 
	boolean flip;
	int marioViewLocation;

//*********************
//Constructor
//*********************
	public Mario (int x, int y) 
	{
		countFrames = 0;
		this.x = x;
		marioViewLocation = this.x; 
		this.y = y;
		w = 60;
		h = 95;
		marioImageNum = 0;
		marioTop = false;
		flip = false; 

		mario_images = new BufferedImage[5];
		mario_images[0] = View.loadImage("mario1.png");
		mario_images[1] = View.loadImage("mario2.png");
		mario_images[2] = View.loadImage("mario3.png");
		mario_images[3] = View.loadImage("mario4.png");
		mario_images[4] = View.loadImage("mario5.png");	


	}

//*********************
//update method
//*********************
	void update()
	{
		countFrames++;		
		if(!marioTop)
		{	
			vert_velocity += 0.9;
			y += vert_velocity;
		}
		
		if (y > 400-h)
		{
			vert_velocity = 0;
			y = 400-h;
			countFrames = 0;
		}

		if(y < -50) // prevents Mario to jump off of the screen
		{
			y = -50;
		}	
	}

//*********************
//jump method
//*********************		
	void jump()
	{
		if (countFrames < 5)
			vert_velocity-=5; 
		
		marioTop = false; 
	}

//***********************
//isMario method
//***********************	
	boolean isMario()
	{
		return true; 
	}	

//******************************
//savePreviousCoordinate method
//******************************
	void savePreviousCoordinate()
	{
		px = x;
		py = y;
	}


//***********************
//getOutOfTube method
//***********************
	void getOutOfTube(Tube t)
	{			
		//in the tube, but we were previously on the left hand side of tube
		if(x+w >= t.x && px + w <= t.x)
			x = t.x - w;	 	
			  
		//in the tube, but we were previously on the right hand side of tube
		if(x <= t.x + t.w && px >= t.x + t.w)
			x = t.x + t.w;

		//in the tube, but used to be above the tube
		if(y + h >= t.y && py + h <= t.y)
		{
			y = t.y - h;
			countFrames = 0;
			vert_velocity = 0;
		}

		//in the tube, but used to be below the tube  
		if(y <= t.h + t.y && py >= t.y + t.h)
		{  
			y = t.y + t.h;
		}
	}

//***********************
//updateImage method
//***********************		
	void updateImage()
	{
		marioImageNum++; 
		if(marioImageNum > 4)
			marioImageNum = 0; 
	}				

//***********************
//drawYourself method
//***********************		
	void drawYourself(Graphics g)
	{
		if(flip)	//flip the image
			g.drawImage(mario_images[marioImageNum], marioViewLocation + w, y, -w, h, null);
		else		//draw as normal
			g.drawImage(mario_images[marioImageNum], marioViewLocation, y, w, h, null);
	}				
}
