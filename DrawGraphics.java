/*
DrawGraphics.java
Code from MIT OCW 6.092 replaced to write my Conway's GoL grid 
*/

import java.awt.Graphics;

public class DrawGraphics {
    Grid grid; 
    
    // Initializes this class for drawing
    public DrawGraphics() {
        grid = new Grid();
    }
	
    // Draw the contents of the window on surface. Called 20 times per second 
    public void draw(Graphics g) {
        grid.draw(g);
    }
}
