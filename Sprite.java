//************************************** 
// Purpose: Sprite file 
// Author: SungWoo Kim 
// Date: 10/23/20
//**************************************

import java.awt.Graphics;

abstract class Sprite
{
    int x, y;
    int w, h;
    double vert_velocity = -12;

    abstract void update();
    abstract void drawYourself(Graphics g);

    boolean isTube() {return false;}
    boolean isMario() {return false;}
    boolean isGoomba() {return false;}
    boolean isFireball() {return false;}
    boolean isBrick() {return false;}

//************************
//didIClickOnATube method
//************************
   boolean didIClickOnATube(int mouse_x, int mouse_y)
	{
		if (mouse_x >= x && mouse_x <= x + w && mouse_y >= y && mouse_y <= y + h) //Tube image has 55W 400H
			return true;
	
		else
			return false; 
	}

//************************
//didIClickOnAGoomba method
//************************
   boolean didIClickOnAGoomba(int mouse_x, int mouse_y)
	{
		if (mouse_x >= x && mouse_x <= x + w && mouse_y >= y && mouse_y <= y + h) //Tube image has 55W 400H
			return true;
	
		else
			return false; 
	}    

//************************
//hasItCollide method
//************************
	boolean hasItCollide(Sprite a)
	{
		if(this.x+this.w <= a.x)					
		{	
			return false;
		}			

		else if(this.x >= a.x+a.w)					
		{	
			return false;
		}							

		else if(this.y+this.h <= a.y) 		
		{	
			return false;
		}							
			
		else if(this.y >= a.y+a.h) 			
		{	
			return false;
		}

		else
			return true;
	}	

}