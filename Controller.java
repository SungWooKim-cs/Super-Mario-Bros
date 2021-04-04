//************************************** 
// Purpose: Controller file 
// Author: SungWoo Kim 
// Date: 10/23/20
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
	boolean control;
	boolean addTubeEditor = false;
	boolean addGoombaEditor = false;

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
			model.mario.flip = false; 
		}
		if(keyLeft) 
		{
			model.mario.updateImage();
			model.mario.x-=10;
			model.mario.flip = true; 
		}

		if(control) 
		{
			model.addFireball();
		}
		
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
//mousePressed method  
//*********************
	public void mousePressed(MouseEvent e)
	{
		if(addTubeEditor)
			model.addTube(e.getX()+model.mario.x - model.mario.marioViewLocation, e.getY());
		else if(addGoombaEditor)
			model.addGoomba(e.getX()+model.mario.x - model.mario.marioViewLocation, e.getY());													
	}																				
	
	public void mouseReleased(MouseEvent e) {    }
	public void mouseEntered(MouseEvent e) {    }
	public void mouseExited(MouseEvent e) {    }
	public void mouseClicked(MouseEvent e) { 	}

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
			case KeyEvent.VK_CONTROL: control = true; break;

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
			case KeyEvent.VK_CONTROL:
				control = false;
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

		if (c == 'q')
		{
			System.exit(0);
		}

		if (c == 't')
		{
			addGoombaEditor = false; 
			addTubeEditor = !addTubeEditor; 		//swap true and false 
		}

		if (c == 'g')
		{
			addTubeEditor = false; 
			addGoombaEditor = !addGoombaEditor; 		//swap true and false 
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
	}
}
