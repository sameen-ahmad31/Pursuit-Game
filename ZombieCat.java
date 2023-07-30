import java.util.Random;

public class ZombieCat extends Cat {

    int roundsZ;
    boolean eats = false;

    
    public ZombieCat(int x, int y, City city, Random rand)
    {
        super(x, y, city, rand);
        this.lab = LAB_RED; //when it is not created, it is just red
        this.stepLen = 3; //the zombie cat jumps 3 spaces at a time 
    }

    public void step()
    {
        roundsZ++; //each time it moves, it increases its rounds
        //a zombie cat jumps 3 spaces at a time
        this.lab = LAB_RED;
        super.step(); 

    }

    public void takeAction()
    {
        //eats the mouse 
        for(Creature a : city.creatures)
        {
            if(a instanceof Mouse)
            {
                if(this.getGridPoint().dist(a.getGridPoint()) == 0)
                {
                    eats = true; //ate the mouse, so it becomes true
                    zEatsMouse(a);
                }
            }
        }

        //eats the cat
        for(Creature b : city.creatures)
        {
            if(b instanceof Cat && (!(b instanceof ZombieCat)))
            {
                if(this.getGridPoint().dist(b.getGridPoint()) == 0)
                {
                    eats = true; //ate the mouse, so it becomes true
                    zEatsCats(b);
                }
            }
        } 

        //chasing the mouse and cat
        for(Creature c : city.creatures)
        {
            if(((c instanceof Mouse) || (c instanceof Cat)) && (!(c instanceof ZombieCat)))
            {
                //if the zombie cat is less than or equal to 40 grid points of the mouse or cat
                if(this.getGridPoint().dist(c.getGridPoint()) <= 40)
                {
                    chaseMouseandCats(c);
                }
            } 
         }

   
        //if it reaches 100 rounds and did not eat anything
        if((roundsZ % 100 == 0) && (eats == false))
        {
            dieZombieCat();
        }
    }



        public void zEatsMouse(Creature a)
        {
            a.dead = true; //mouse dies
            this.lab = LAB_RED;
        }

        public void zEatsCats(Creature b)
        {
            int xt = b.getX();
            int yt = b.getY();
            b.dead = true; //cat dies
            this.lab = LAB_RED;
            city.creaturesToAdd.add(new ZombieCat(xt,yt,this.city,this.rand)); //create the ZombieCat
        }
    
        public void chaseMouseandCats(Creature k)
        {
            this.lab = LAB_BLACK; //zombie cat changes its color to black
            
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

        public void dieZombieCat()
        {
            this.dead = true; //zombie cat dies
        }
    
    
     

    
}
