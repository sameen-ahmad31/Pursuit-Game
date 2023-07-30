import java.util.Random;

public class Cat extends Creature
{
    int roundsC;
    boolean eatMouse = false;
    boolean chase = false;
    
    public Cat(int x, int y, City city, Random rand)
    {
        super(x, y, city, rand);
        this.lab = LAB_YELLOW; //cat has the color yellow
        this.stepLen = 2; //jumps two spaces at a time so the stepLen is 2 
    }

    public void step()
    {
        //cat jumps two spaces at a time, they do not traverse the grid point, they jump over, that is, if they are on space
       // this.lab = LAB_YELLOW; 
        chase = false;
        super.step(); //calls the step superconstructor

    }

    public void takeAction()
    {
        roundsC++; //increase the round counter every time it steps forward

        // cat eats a mouse if they end up on the same location
        for(Creature c : city.creatures)
        {
            if(c instanceof Mouse)
            {
                if(this.getGridPoint().dist(c.getGridPoint()) == 0)
                {
                    eatmouse(c);
                }
            }
        }

        //chasing the mouse
        for(Creature k : city.creatures)
        {
            if(k instanceof Mouse)
            {
                //if the cat if less than or equal to 20 grid points of the mouse
                if(this.getGridPoint().dist(k.getGridPoint()) <= 20)
                {
                    //it moves towards the mouse and is displayed using the color cyan
                    chase = true;
                    chaseMouse(k);
                }
            }
        }

        //if it is at round 50 and the cat did not eat a mouse
        if((roundsC % 50 == 0) && (eatMouse == false))
        {
            catIntoZombie();
        }

        if(chase != true)
        {
            this.lab = LAB_YELLOW; 
            randomturnCat();
        }

    }

    
    public void chaseMouse(Creature k)
     {
         this.lab = LAB_CYAN;
    
         if((this.getGridPoint().x - k.getGridPoint().x < 0) && (this.getGridPoint().y - k.getGridPoint().y == 0))
         {
             setDir(EAST);
         }
         else if((this.getGridPoint().x - k.getGridPoint().x == 0) && (this.getGridPoint().y - k.getGridPoint().y > 0))
         {
             setDir(SOUTH);
         }
         else if((this.getGridPoint().x - k.getGridPoint().x > 0) && (this.getGridPoint().y - k.getGridPoint().y == 0))
         {
             setDir(WEST);
         }
         else if((this.getGridPoint().x - k.getGridPoint().x == 0) && (this.getGridPoint().y - k.getGridPoint().y < 0))
         {
             setDir(NORTH);
         }
         else if( ((this.getGridPoint().x - k.getGridPoint().x) >= (this.getGridPoint().y - k.getGridPoint().y)) && ((this.getGridPoint().x- k.getGridPoint().x < 0)) )
         {
            setDir(EAST);
         }
         else if( ((this.getGridPoint().x - k.getGridPoint().x) >= (this.getGridPoint().y - k.getGridPoint().y)) && ((this.getGridPoint().x - k.getGridPoint().x > 0)) )
         {
            setDir(WEST);
         }
         else if ( ((this.getGridPoint().y - k.getGridPoint().y) >= (this.getGridPoint().x - k.getGridPoint().x)) && ((this.getGridPoint().y - k.getGridPoint().y < 0)))
         {
            setDir(NORTH);
         }
         else if ( ((this.getGridPoint().y - k.getGridPoint().y) >= (this.getGridPoint().x - k.getGridPoint().x)) && ((this.getGridPoint().y - k.getGridPoint().y > 0)))
         {
            setDir(SOUTH);
         }


     }
 
     public void randomturnCat()
     {
         //i can be a random number from 1-20, so the probability that it is 1 is 5% of the time
         this.lab = LAB_YELLOW; 
         int i = rand.nextInt(19);
         //cat randomly turns, change direction, 5% of the time 
         if(i == 0) 
         {
             this.randomTurn();
         } 
     }
 
     public void catIntoZombie()
     {
         int xr = this.getX();
         int yr = this.getY(); 
         this.dead = true; //cat dies
         city.creaturesToAdd.add(new ZombieCat(xr,yr,this.city,this.rand)); //create the ZombieCat
     }
 
     public void eatmouse(Creature c)
     {
        c.dead = true; //mouse dies
        this.lab = LAB_YELLOW; 
         
     } 
 
 
 
}

