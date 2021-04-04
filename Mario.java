//************************************** 
// Purpose: Mario file 
// Author: SungWoo Kim 
// Date: 10/09/20
//**************************************

import java.awt.image.BufferedImage;

//*******************
//class Mario
//*******************
class Mario 
{
	
//*********************
//member variables
//*********************
    int x, y; 
	int px, py;

	int width, height;  
	int countFrames;
	int marioImageNum;
	double vert_velocity; 
	boolean marioTop;
	static BufferedImage[] mario_images; 
	boolean flip; 

//*********************
//Constructor
//*********************
	public Mario (int x, int y) 
	{
		boolean = false; 
		countFrames = 0;
		this.x = x;
		this.y = y;
		width = 60;
		height = 95;
		marioImageNum = 0;
		vert_velocity = -12.0; 
		marioTop = false;

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
		
		if (y > 400-height)
		{
			//vert_velocity = -38; // bouncing Mario 
			vert_velocity = 0;
			y = 400-height;
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
		if(x+width >= t.x && px + width <= t.x)
			x = t.x - width;	 	
			  
		//in the tube, but we were previously on the right hand side of tube
		if(x <= t.x + t.width && px >= t.x + t.width)
			x = t.x + t.width;

		//in the tube, but used to be above the tube
		if(y + height >= t.y && py + height <= t.y)
		{
			y = t.y - height;
			//marioTop = true; 
			countFrames = 0;
			vert_velocity = 0;
		}

		//in the tube, but used to be below the tube  
		if(y <= t.height + t.y && py >= t.y + t.height)
		{  
			y = t.y + t.height;
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
}
