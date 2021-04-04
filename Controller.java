//************************************** 
// Purpose: Controller file 
// Author: SungWoo Kim 
// Date: 10/09/20
//**************************************

import java.awt.event.MouseListener;
import java.awt.event.MouseEvent;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.KeyListener;
import java.awt.event.KeyEvent;

//*********************
//class Controller	  
//*********************
class Controller implements ActionListener, MouseListener, KeyListener
{

//*********************
//member variables
//*********************
	View view; 
	Model model;
	boolean keyLeft;
	boolean keyRight;
	boolean keyUp;
	boolean keyDown;
	boolean spacebar;

//*********************
//Constructor  
//*********************
	Controller(Model m)
	{
		model = m;
	}
		
//*********************
//update method  
//*********************
	void update()
	{
		model.mario.savePreviousCoordinate();
		if(keyRight)
		{
			model.mario.updateImage(); 
			model.mario.x+=10;
		}
		if(keyLeft) 
		{
			model.mario.updateImage();
			model.mario.x-=10;
		}
			
		//System.out.println("view scroll position " + view.scroll_pos); 	//to print out the current coordinate
		if(keyUp || spacebar) model.mario.jump();
	}


//*********************
//setView method  
//*********************
	void setView(View v)
	{
		view = v;
	}
	

//*********************
//keyPressed method  
//*********************	
	public void keyPressed(KeyEvent e)
	{
		switch(e.getKeyCode())
		{
			case KeyEvent.VK_RIGHT: keyRight = true; break;
			case KeyEvent.VK_LEFT: keyLeft = true; break;
			case KeyEvent.VK_UP: keyUp = true; break;
			case KeyEvent.VK_DOWN: keyDown = true; break;
			case KeyEvent.VK_SPACE: spacebar = true; break;

		}
	}

//*********************
//keyReleased method  
//*********************
	public void keyReleased(KeyEvent e)
	{
		switch(e.getKeyCode())
		{
			case KeyEvent.VK_RIGHT: 
				keyRight = false; 
				break;
			case KeyEvent.VK_LEFT: 
				keyLeft = false;
				break;
			case KeyEvent.VK_UP: 
				keyUp = false;
				break;
			case KeyEvent.VK_DOWN:
				keyDown = false;
				break;
			case KeyEvent.VK_SPACE:
				spacebar = false;
				break; 
		}
		
		char c = e.getKeyChar();
		if (c == 's')
		{
			System.out.println("You have clicked S key");
			model.marshal().save("map.json");
			System.out.println("You have successfully saved map.json");
		}
		
		if (c == 'l')
		{
			System.out.println("You have clicked L key");
			Json j = Json.load("map.json");
			model.unmarshal(j);
			System.out.println("You have successfully loaded map.json");
			
		}
	}

//*********************
//mousePressed method  
//*********************
	public void mousePressed(MouseEvent e)
	{
		model.addTube(e.getX()+model.mario.x - view.marioViewLocation, e.getY());	// Think of Model as a whole World screen 																					
	}																				// Think of view as a current page that user sees 
	public void mouseReleased(MouseEvent e) {    }
	public void mouseEntered(MouseEvent e) {    }
	public void mouseExited(MouseEvent e) {    }
	public void mouseClicked(MouseEvent e)
	{    
		if(e.getY() < 100)
		{
			//System.out.println("break here");
		}
	}

//*********************
//keyTyped method  
//*********************
	public void keyTyped(KeyEvent e)
	{
	}

//***********************
//actionPerformed method  
//***********************
	public void actionPerformed(ActionEvent e)
	{
		//view.removeButton();
	}
}
