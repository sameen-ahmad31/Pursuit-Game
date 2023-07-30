import java.util.Random;

public class Dog extends Creature{

    int roundsD;
    boolean ate = false;
    
    public Dog(int x, int y, City city, Random rand)
    {
        super(x, y, city, rand);
        this.lab = LAB_MAGENTA; //dog's color is magenta
        this.stepLen = 4; //it jumps up to 4 spaces
    }

    public void step()
    {
        roundsD++; 
        this.lab = LAB_MAGENTA;
        super.step();
    }

    public void takeAction()
    {
        //the dog only eats the zombie cat
        for(Creature c : city.creatures)
        {
            if(c instanceof ZombieCat)
            {
                if(this.getGridPoint().dist(c.getGridPoint()) == 0)
                {
                    eatZombieCats(c);
                }
            }
        }
         
        //chasing the zombie cat
        //when it is chasing, it changes its color to orange
        for(Creature k : city.creatures)
        {
            if(k instanceof ZombieCat)
            {
                //if the zombie cat is less than or equal to 15 grid points of the dog
                if(this.getGridPoint().dist(k.getGridPoint()) <= 15)
                {
                    chaseZombieCats(k);
                }
              }
          }


        //if the dog does not eat at 30 rounds, then it dies
        if((roundsD % 30 == 0) && (ate == false))
        {
            dieDog();
        }


    }

    public void eatZombieCats(Creature c)
    {
        int xq = c.getX();
        int yq = c.getY();
        ate = true; //ate the zombie cat, so it becomes true
        c.dead = true; //zombie cat dies
        this.lab = LAB_MAGENTA;
        city.creaturesToAdd.add(new Dog(xq,yq,this.city,this.rand)); //create a Dog
    }

    public void chaseZombieCats(Creature k)
    {
        //it moves towards the zombie cat 
        this.lab = LAB_ORANGE; //dog changes its color to orange
    
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

    public void dieDog()
    {
        this.dead = true;
    } 



    
}
