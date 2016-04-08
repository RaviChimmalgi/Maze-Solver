/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package maze;

import java.applet.Applet;

/**
 *
 * @author rave
 */
public class MazeApplet extends Applet {

    /**
     * Initialization method that will be called after the applet is loaded into
     * the browser.
     */
    @Override
    public void init() {
        // TODO start asynchronous download of heavy resources
    }
    
    @Override
    public void start(){
        long startTime = 0;
        long endTime = 0;
        double diff = 0;
        byte numRuns = 10;
        System.out.println("N          Runtime(ms)");
        for (int i = 2; i <= 50; i++) {
            diff = 0;
            System.out.println(i);
            for (int j = 0; j < numRuns; j++) {
                Maze maze = new Maze(i);
              
                StdDraw.clear();
                StdDraw.show(0);
                maze.draw();
                startTime = System.nanoTime();
                maze.solveDFS();
                endTime = System.nanoTime();
                diff = (endTime - startTime);
          
                 //System.out.println("   " + diff);
            }
          
        }
    }

    // TODO overwrite start(), stop() and destroy() methods
}
