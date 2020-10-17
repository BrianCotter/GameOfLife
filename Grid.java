/*
Grid class that creates Grid structure for GoL World
Has Draw method that gets called 20x a sec to update screen

Rules for the Game of Life: 
1. Survivals. Every counter with two or three neighboring counters survives for the next generation.
2. Deaths. Each counter with four or more neighbors dies (is removed) from overpopulation. Every counter with
   one neighbor or none dies from isolation.
3. Births. Each empty cell adjacent to exactly three neighbors--no more, no fewer--is a birth cell. A counter is
   placed on it at the next move. 

*/

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.util.Random;

public class Grid {

    public static final int WIDTH = 50;
    public static final int HEIGHT = 50;
    public static final int PROB = 30;
    Random rand = new Random();
    private Graphics2D g2D;
    private int[][] grid;
    private int[][] buffer;
	
    Grid(){
	grid = new int[WIDTH][HEIGHT];
        buffer = new int[WIDTH][HEIGHT];
		
	//Intialize the 2D grid
        for(int x=1; x < WIDTH-1; x++){
            for(int y=1; y < HEIGHT-1; y++){
                if(rand.nextInt(100) < PROB){
                    grid[x][y] = 1; // Alive
                }
                else{
                    grid[x][y] = 0; // Dead
                }               
            }
        }
    }
	
	
    public void update(){
        int neighbors = 0;

        for(int x = 1; x < WIDTH-1; x++){
            for(int y = 1; y < HEIGHT-1; y++){
                buffer[x][y] = 0;

                // Find the number of neighbors of the current generation
                neighbors = grid[x-1][y-1]+grid[x-1][y+0]+grid[x-1][y+1]+grid[x+0][y-1]+grid[x+0][y+1]+grid[x+1][y-1]+grid[x+1][y+0]+grid[x+1][y+1];
				
                // conditions based on # of neighbors 
                // set buffer either ALIVE or DEAD
                if(grid[x][y]==0 && neighbors == 3){ // New cell is "born"
                    buffer[x][y] = 1;
                }
                else if(neighbors >= 4 || neighbors <= 1){ // Over/under population
                    buffer[x][y] = 0;
                }
                else{ // Remains alive "survives" or dead cell reamains dead
                    buffer[x][y] = grid[x][y];
                }    
            }
        }
		
        // Copy buffer to grid 
        for(int i=0; i<WIDTH; i++){
            for(int j=0; j<HEIGHT; j++)
                grid[i][j] = buffer[i][j];
        }
    }

	
    // Call 20x a second 
    public void draw(Graphics g){
	g2D = (Graphics2D)g;
		
	// Draw state over every cell in the grid
        for(int x = 0; x < WIDTH; x++){
            for(int y = 0; y < HEIGHT; y++){

                Cell cell = new Cell(x*Cell.SCALE,y*Cell.SCALE);
                
                if(grid[x][y] == 1){
                    g2D.setColor(Color.BLACK);
                }
                else{
                    g2D.setColor(Color.WHITE);
                }
                g2D.fill(cell.getShape());

            }
        }

        // After drawing current state update buffer
        update();
    } 
}
