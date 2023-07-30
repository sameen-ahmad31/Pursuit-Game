import java.util.Random;

public class Mouse extends Creature{
    int roundsM;

    public Mouse(int x, int y, City city, Random rand)
    {
        super(x, y, city, rand);
        this.lab = LAB_BLUE;
        this.stepLen = 1;
    }

    public void step()
    {
        // all the creatures are moved forward by taking a step in the simulation. This could move the creature in a direction, determine if it starved, or other general state changes
        roundsM++;
        super.step();
    }

    public void takeAction()
    {
        //In the takeAction method, each creature assess its surroundings, determines if it should eat something, chase something, or do something.
        //After all the actions are taken, any creatures that are dead are removed from the creatures list. And then any new creatures are added. Note that there is a method addNewCreatures that is provided for you, and duplicated below.
        
        //after 20 rounds, create a new mouse at the same location 
        if(roundsM % 20 == 0)
        {
          newMouse();
        }

        //after 30 rounds, the mouse dies
        if(roundsM % 30 == 0)
        {
            dieMouse(); //makes it dead so it will die
        }

        randomturnMouse();

    }

    public void newMouse()
    {
        int xs = this.getX();
        int ys = this.getY();
        city.creaturesToAdd.add(new Mouse(xs,ys,this.city,this.rand));

    }

    public void randomturnMouse()
    {
        //randomly turns 20% of the time 
        //can from 1-5, so the probability that it is 1 is 20%
        int i = rand.nextInt(4);
        if(i == 0)
        {
            this.randomTurn();
        }
    }

    public void dieMouse()
    {
        this.dead = true; //makes it dead so it will die
    } 
    
}
