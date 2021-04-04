//************************************** 
// Purpose: Model file 
// Author: SungWoo Kim 
// Date: 10/23/20
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
	ArrayList<Sprite> sprites;							//ArrayList object "tubes" created
	Mario mario;
								//Redundant reference to Mario for easier updating 
	

//*******************
//Constructor
//*******************
	Model()
	{
		sprites = new ArrayList<Sprite>();				//Initialize in constructor
		mario = new Mario(200,50);
		sprites.add(mario);	
	}

//*******************
//update method
//*******************
	public void update()
	{	
		for (int i = 0; i < sprites.size(); i++)
		{	
			sprites.get(i).update();							// where polymorphism takes place
			if(sprites.get(i).isTube())
			{
				Tube t = (Tube)sprites.get(i);
				if (hasItCollide(mario, t))
				{
					mario.getOutOfTube(t);
				}
			}
		
			if(sprites.get(i).isGoomba())
			{
				
				Goomba g = (Goomba)sprites.get(i);
				if(g.isBurning)
					{
						if(g.goombaFrames > 20)
							sprites.remove(g); 
					}	
			}

			if(sprites.get(i).isFireball())
			{
				Fireball f = (Fireball)sprites.get(i);
				if(f.x > View.MAX_SCROLL)
					{
						sprites.remove(f);
					}	
			}
		}
	}
//************************
//hasItCollide method
//************************
	boolean hasItCollide(Sprite a, Sprite b)
	{
		if(a.x+a.w <= b.x)					//left-side of the tube
		{	
			return false;
		}			

		else if(a.x >= b.x+b.w)					//right-side of the tube
		{	
			return false;
		}							

		else if(a.y+a.h <= b.y) 				//on top of the tube
		{	
			return false;
		}							
			
		else if(a.y >= b.y+b.h) 				//bottom of the tube 
		{	
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
		sprites = new ArrayList<Sprite>();
		sprites.add(mario);
		Json jsonList = ob.get("sprites");
        Json tubesList = jsonList.get("tubes"); 
		Json goombasList = jsonList.get("goombas"); 
        //add Tubes back in to sprites
		for(int i = 0; i < tubesList.size(); i++)
		{	
            sprites.add(new Tube(tubesList.get(i), this));
		}
		//add Goombas back in to sprites
		  for(int i = 0; i < goombasList.size(); i++)
		{	
            sprites.add(new Goomba(goombasList.get(i), this));
		}
	}
	
//************************
//Json method
//************************	
	Json marshal()			// Marshals this object into a JSON DOM
	{
      Json ob = Json.newObject();
	  Json spritesOb = Json.newObject();
      Json tmpList = Json.newList();

      ob.add("sprites", spritesOb);
	  spritesOb.add("tubes", tmpList);
	  for (int i= 0; i < sprites.size(); i++)
	  {
		  if(sprites.get(i).isTube())
		  {
			  Tube t = (Tube)sprites.get(i);
			  tmpList.add(t.marshal());
		  }
	  }

	  tmpList = Json.newList();
	  spritesOb.add("goombas", tmpList);
	  for (int i= 0; i < sprites.size(); i++)
	  {
		  if(sprites.get(i).isGoomba())
		  {
			  Goomba g = (Goomba)sprites.get(i);
			  tmpList.add(g.marshal());
		  }
	  }
      return ob;
	}
	
//************************
//addTube method
//************************		
	public void addTube(int x, int y)			//to add when empty, to remove when full 
	{
		Tube t = new Tube(x, y, this);					
		boolean didIClickOnATube = false;
		Iterator<Sprite> tubeIterator = sprites.iterator();

		for (int i = 0; i < sprites.size(); i++)			//loop through all of the tube in ArrayList
		{	
			if(sprites.get(i).didIClickOnATube(x, y))
			{
						sprites.remove(i); 
						didIClickOnATube = true;
						break; 	
			}
		}
		if (!didIClickOnATube)
			sprites.add(t); 									//add a tube when click an empty spot
	}

//************************
//addGoomba method
//************************		
	public void addGoomba(int x, int y)			//to add when empty, to remove when full   
	{
		Goomba g = new Goomba(x, y, this);	
		boolean didIClickOnAGoomba = false;			
		for (int i = 0; i < sprites.size(); i++)			//loop through all of the tube in ArrayList
		{	
			
			if(sprites.get(i).didIClickOnATube(x, y))
			{
				sprites.remove(i); 
				didIClickOnAGoomba = true;
				break; 	
			}
		}
		if (!didIClickOnAGoomba)
			sprites.add(g); 		
	}	

//************************
//addFireball method
//************************		
	public void addFireball()			
	{
		int fireballLocation = mario.x+mario.w; 
		if(mario.flip)
		{
			fireballLocation = mario.x;
		}	

		Fireball f = new Fireball(fireballLocation,mario.y + mario.h/2, this);	
		sprites.add(f);
	}		
	
}
