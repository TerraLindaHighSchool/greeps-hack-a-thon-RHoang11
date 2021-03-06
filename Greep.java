import greenfoot.*;

/**
 * A Greep is an alien creature that likes to collect tomatoes.
 * 
 * @author (your name here)
 * @version 0.2
 */
public class Greep extends Creature
{
    // Remember: you cannot extend the Greep's memory. So:
    // no additional fields (other than final fields) allowed in this class!
    
    private boolean goHome = false; 
    public boolean flag1 = false; 
    public boolean flag2 = false; 
    public final int HOW_MANY_STEPS = 4;
    
    /**
     * Default constructor for testing purposes.
     */
    public Greep()
    {
        this(null);
    }
    
    /**
     * Create a Greep with its home space ship.
     */
    public Greep(Ship ship)
    {
        super(ship);
    }

    /**
     * Do what a greep's gotta do.
     */
    public void act()
    {
        super.act();   // do not delete! leave as first statement in act().
        if (carryingTomato()) 
        {
            int remainingSteps = getMemory();
            if(remainingSteps == 0)
            {
                spit("purple");
                turnHome();
            }
            else
            {
                remainingSteps--;
                setMemory(remainingSteps);
            }
            
            if (atShip()) {
                dropTomato();
                turn(180);
            }
            else {
                move();
                goHome = true;
            }
            if (atWater() || isAtEdge())
            {
                turn(Greenfoot.getRandomNumber(65));
                setMemory(HOW_MANY_STEPS + Greenfoot.getRandomNumber(HOW_MANY_STEPS));
            }
        }
        else {
            checkFood();
            //spit("red");
        }
        
        
        if(atWater() || isAtEdge())
        {
            turn(Greenfoot.getRandomNumber(60));
            
        }
        
    }
    
    /**
     * Is there any food here where we are? If so, try to load some!
     */
    public void checkFood()
    {
        // check whether there's a tomato pile here
        TomatoPile tomatoes = (TomatoPile) getOneIntersectingObject(TomatoPile.class);
        Greep greeps = (Greep) getOneIntersectingObject(Greep.class);
        if (tomatoes != null) 
        {
            loadTomato();
            
            
            // Note: this attempts to load a tomato onto *another* Greep. It won't
            // do anything if we are alone here.
        }
        else
        {
            move();
            
            
        }
    }
    
    public void moveToFlag()
    {
        TomatoPile tomatoes = (TomatoPile) getOneIntersectingObject(TomatoPile.class);
        if (tomatoes != null)
        {
            setFlag(1, true);
            flag1 = true;
        }
        if(tomatoes != null && flag1 == true)
        {
            setFlag(2, true); 
            flag2 = true;
        }
        
    }
    
    /**
     * When a greep is on a tomato patch, the greep will call the other greeps to itself
     * only greeps in a certain area are called.
     */
    public void callGreeps()
    {
        //int dX = Creature.getX() - getX();
        //int dY = Creature.getY() - getY();
        //setRotation((int) (180 * Math.atan2(dY, dX) / Math.PI));
    }
    /**
     * This method specifies the name of the author (for display on the result board).
     */
    public static String getAuthorName()
    {
        return "Ryan Hoang";  // write your name here!
    }

    /**
     * This method specifies the image we want displayed at any time. (No need 
     * to change this for the competition.)
     */
    public String getCurrentImage()
    {
        if (carryingTomato()) {
            return "greep-with-food.png";
        }
        else {
            return "greep.png";
        }
    }
    
    
}