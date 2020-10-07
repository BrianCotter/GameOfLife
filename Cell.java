import java.awt.Rectangle;

public class Cell {

    enum State{
        DEAD,
        ALIVE;
    }
 
    private Rectangle rect;
    private State state;

    Cell(int x, int y){
        rect = new Rectangle(x, y, 10, 10);
    }

    public Rectangle getShape(){
        return rect;
    }

    public void setState(State state){
        this.state = state;
    }

    public State getState(){
        return state; 

    }

    public void setColor(){
        

    }
    

}
