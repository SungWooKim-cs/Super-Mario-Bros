//************************************** 
// Purpose: Model file 
// Author: SungWoo Kim 
// Date: 10/09/20
//**************************************

import java.util.ArrayList;
import java.awt.Graphics;
import java.util.Iterator;

//*******************
//class Model
//*******************
class Model
{

//*******************
//member variables
//*******************
	int mouse_x;
	int mouse_y;
	int x;
	int y;
	ArrayList<Tube> tubes;							//ArrayList object "tubes" created
	Mario mario; 

//*******************
//Constructor
//*******************
	Model()
	{
		tubes = new ArrayList<Tube>();				//Initialize in constructor
		mario = new Mario(200,50);
	}

//*******************
//update method
//*******************
	public void update()
	{	
		// check for collisions with tubes
		// loop through all of the tubes in your array and see if Mario is colliding with one of them
		// if Mario is colliding, get him back out of the tube 

		mario.update();
		for (int i = 0; i < tubes.size(); i++)
			if (hasItCollide(tubes.get(i)))
			{
				mario.getOutOfTube(tubes.get(i));
			}	
	}

//************************
//hasItCollide method
//************************
	boolean hasItCollide(Tube t)
	{
		if(mario.x+mario.width <= t.x)					// mario on the left-side of the tube
		{	
			//System.out.println("this is happening1");
			return false;
		}			

		else if(mario.x >= t.x+t.width)					// mario on the right-side of the tube
		{	
			//System.out.println("this is happening2");
			return false;
		}							

		else if(mario.y+mario.height <= t.y) 			// mario on top of the tube
		{	
			//System.out.println("this is happening3");
			return false;
		}							
			
		else if(mario.y >= t.y+t.height) 				// mario at the bottom of the tube 
		{	
			//System.out.println("this is happening4");
			return false;
		}							
			
		else
			return true;
	}	

//********************
//unmarshal method
//********************
	void unmarshal(Json ob)
	{
		tubes = new ArrayList<Tube>();
        Json tmpList = ob.get("tubes");
        for(int i = 0; i < tmpList.size(); i++)
            tubes.add(new Tube(tmpList.get(i)));
	}
	
//************************
//Json method
//************************	
	Json marshal()			// Marshals this object into a JSON DOM
	{
      Json ob = Json.newObject();
      Json tmpList = Json.newList();
      ob.add("tubes", tmpList);
      for(int i = 0; i < tubes.size(); i++) 
          tmpList.add(tubes.get(i).marshal());
      return ob;
	}
	
//************************
//addTube method
//************************		
	public void addTube(int mouse_x, int mouse_y)			//to add when empty, to remove when full
	{
		Tube t = new Tube(mouse_x, mouse_y);
		boolean didIClickOnATube = false;
		Iterator<Tube> tubeIterator = tubes.iterator();

		while(tubeIterator.hasNext())
		// for (int i = 0; i < tubes.size(); i++)			//loop through all of the tube in ArrayList
		{	
			Tube temp = tubeIterator.next(); 
			if (temp.didIClickOnATube(mouse_x, mouse_y))
				{
					// tubes.remove(temp); 
					tubeIterator.remove();					//remove a tube when click a tube 
					didIClickOnATube = true;
					break; 
				}
		}
		if (!didIClickOnATube)
			tubes.add(t); 									//add a tube when click an empty spot
	}
}

