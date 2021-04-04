//************************************** 
// Purpose: Game file 
// Author: SungWoo Kim 
// Date: 10/23/20
//**************************************

import javax.swing.JFrame;
import java.awt.Toolkit;

//*********************
//class Game	  
//*********************
public class Game extends JFrame
{
	
//*********************
//member variables
//*********************
	Model model;
	View view;
	Controller controller;

//*********************
//Constructor	  
//*********************
	public Game()
	{
		model = new Model();
		controller = new Controller(model);
		view = new View(controller, model);
		view.addMouseListener(controller);
		this.addKeyListener(controller);
		this.setTitle("Polymorphism");
		this.setSize(800, 500);
		this.setFocusable(true);
		this.getContentPane().add(view);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setVisible(true);
		
		try{
		Json j = Json.load("map.json");
		model.unmarshal(j);
		} catch(Exception e)
		{
			e.printStackTrace();
			System.out.println("There was an error loading your map");
			System.exit(0);
		}
	}

//*********************
//main method	  
//*********************
	public static void main(String[] args)
	{
		Game g = new Game();
		g.run();
	}

	public void run()
	{
		while(true)
		{
			controller.update();
			model.update();	
			view.repaint(); 					// Indirectly calls View.paintComponent
			Toolkit.getDefaultToolkit().sync(); // Updates screen

			// Go to sleep for 25 miliseconds
			try
			{
				Thread.sleep(25);
			} catch(Exception e) {
				e.printStackTrace();
				System.exit(1);
			}
		}
	}
}
