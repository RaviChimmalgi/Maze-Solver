/*************************************************************************
 *  Compilation:  javac Maze.java
 *  Execution:    java Maze.java N
 *  Dependecies:  StdDraw.java
 *
 *  Generates a perfect N-by-N maze using depth-first search with a stack.
 *  Note: this program generalizes nicely to finding a random tree
 *        in a graph.
 *  Copyright © 2000–2011, Robert Sedgewick and Kevin Wayne.
 *
 *  Copyright © 2013, Ravi Chimmalgi for Random DFS (SolveRandom methods)
 *************************************************************************/
package maze;

import java.util.ArrayList;
import java.util.*;
public class Maze {
    private int N;                 // dimension of maze
    private boolean[][] north;     // is there a wall to north of cell i, j
    private boolean[][] east;
    private boolean[][] south;
    private boolean[][] west;
    private boolean[][] visited;

    private boolean done = false;


    public Maze(int N) {
        this.N = N;
        StdDraw.setXscale(0, N+2);
        StdDraw.setYscale(0, N+2);
        init();
        generate();
    }

    private void init() {
        // initialize border cells as already visited
        visited = new boolean[N+2][N+2];
        for (int x = 0; x < N+2; x++) visited[x][0] = visited[x][N+1] = true;
        for (int y = 0; y < N+2; y++) visited[0][y] = visited[N+1][y] = true;




        // initialze all walls as present
        north = new boolean[N+2][N+2];
        east  = new boolean[N+2][N+2];
        south = new boolean[N+2][N+2];
        west  = new boolean[N+2][N+2];
        for (int x = 0; x < N+2; x++)
            for (int y = 0; y < N+2; y++)
                north[x][y] = east[x][y] = south[x][y] = west[x][y] = true;
    }



//Consider maze as a N X N matrix of cells, then the following methods return true if
//there is wall to the top, right, left or bottom to the cell. 0 <= x,y < N
public boolean hasTopWall(int x, int y){
    return south[y+1][x+1];
 }
public boolean hasRightWall(int x, int y){
    return east[y+1][x+1];
 }
public boolean hasLeftWall(int x, int y){
    return west[y+1][x+1];
 }
public boolean hasBottomWall(int x, int y){
    return north[y+1][x+1];
 }




    // generate the maze
    private void generate(int x, int y) {
        visited[x][y] = true;

        // while there is an unvisited neighbor
        while (!visited[x][y+1] || !visited[x+1][y] || !visited[x][y-1] || !visited[x-1][y]) {

            // pick random neighbor (could use Knuth's trick instead)
            while (true) {
                double r = Math.random();
                if (r < 0.25 && !visited[x][y+1]) {
                    north[x][y] = south[x][y+1] = false;
                    generate(x, y + 1);
                    break;
                }
                else if (r >= 0.25 && r < 0.50 && !visited[x+1][y]) {
                    east[x][y] = west[x+1][y] = false;
                    generate(x+1, y);
                    break;
                }
                else if (r >= 0.5 && r < 0.75 && !visited[x][y-1]) {
                    south[x][y] = north[x][y-1] = false;
                    generate(x, y-1);
                    break;
                }
                else if (r >= 0.75 && r < 1.00 && !visited[x-1][y]) {
                    west[x][y] = east[x-1][y] = false;
                    generate(x-1, y);
                    break;
                }
            }
        }
    }

    // generate the maze starting from lower left
    private void generate() {
        generate(1, 1);



    }


       // solve the maze starting from the start state
    public void solveDFS() {
        for (int x = 1; x <= N; x++)
            for (int y = 1; y <= N; y++)
                visited[x][y] = false;
        done = false;
        solveDFS(1, 1);
    }


    // solve the maze using depth-first search
    private void solveDFS(int x, int y) {
        if (x == 0 || y == 0 || x == N+1 || y == N+1) return;
        if (done || visited[x][y]) return;
        visited[x][y] = true;
          //  System.out.println(x+" "+y);
        StdDraw.setPenColor(StdDraw.BLUE);
        StdDraw.filledCircle(x + 0.5, y + 0.5, 0.25);
        StdDraw.show(30);

        // reached middle
        //Edit: reached end point?
        if (x == N && y == N) done = true;

        if (!north[x][y]) {
           // System.out.println("South");
        solveDFS(x, y + 1);}
        if (!east[x][y])  {
            //System.out.println("East");
            solveDFS(x + 1, y);}
        if (!south[x][y]) {
            //System.out.println("North");
            solveDFS(x, y - 1);}
        if (!west[x][y])  {
            //System.out.println("West");
            solveDFS(x - 1, y);}

        if (done) return;

        StdDraw.setPenColor(StdDraw.GRAY);
        //System.out.println("Grey");
        StdDraw.filledCircle(x + 0.5, y + 0.5, 0.25);
        StdDraw.show(30);
    }



        public void solveRandom() {
             StdDraw.setPenColor(StdDraw.BLUE);
        for (int x = 1; x <= N; x++)
            for (int y = 1; y <= N; y++)
                visited[x][y] = false;
        done = false;
        solveRandom(1, 1);
    }

     public class CellCoordinate {
    int x;
    int y;

     CellCoordinate(int x, int y){
      this.x=x;
      this.y=y;
    }
}


    private Stack<CellCoordinate> decisionPoints = new Stack<>();

    public void solveRandom(int x, int y){
        if (x == 0 || y == 0 || x == N+1 || y == N+1) return;
        if (done ) return;
        visited[x][y] = true;


        StdDraw.filledCircle(x + 0.5, y + 0.5, 0.25);
        StdDraw.show(30);
        StdDraw.setPenColor(StdDraw.BLUE);
        if (x == N && y == N) done = true;


        ArrayList possiblePaths = new ArrayList<>();

                if(!north[x][y] & !visited[x][y+1]) possiblePaths.add(1);
                if(!east[x][y] & !visited[x+1][y]) possiblePaths.add(2);
                if(!south[x][y] & !visited[x][y-1]) possiblePaths.add(3);
                if(!west[x][y] & !visited[x-1][y]) possiblePaths.add(4);
                //System.out.println("paths: "+possiblePaths.size());


                if(possiblePaths.size()>1)
                {CellCoordinate cell = new CellCoordinate(x,y);
                //System.out.println("decisionPoint: "+x+" "+y+" Paths: "+possiblePaths.size());
                decisionPoints.push(cell);
                }

               if(possiblePaths.isEmpty())
               {
                    CellCoordinate c1;
                    if(!decisionPoints.isEmpty())
                    {c1 = decisionPoints.peek();
                    if(visited[c1.x][c1.y+1] & visited[c1.x+1][c1.y] & visited[c1.x][c1.y-1] & visited[c1.x-1][c1.y])
                        {decisionPoints.pop();
                    if(!decisionPoints.isEmpty())
                        c1 = decisionPoints.peek();}
                    //System.out.println("peek "+c2.x+" "+c2.y);
                    StdDraw.setPenColor(StdDraw.BLACK);
                    solveRandom(c1.x,c1.y);}
               }
               else {
                Random random = new Random();
               int index = random.nextInt(possiblePaths.size());
               //System.out.println(possiblePaths.get(index));
               if(possiblePaths.get(index).equals(1))
                   {//System.out.println("South");
                   solveRandom(x, y + 1);}
               if(possiblePaths.get(index).equals(2))
                   {//System.out.println("East");
                   solveRandom(x + 1, y);}
               if(possiblePaths.get(index).equals(3))
                   {//System.out.println("North");
                   solveRandom(x, y - 1);}
               if(possiblePaths.get(index).equals(4))
                   {//System.out.println("West");
                   solveRandom(x - 1, y);}
               }
                if (done) return;

        StdDraw.setPenColor(StdDraw.GRAY);
        //System.out.println("Grey");
        StdDraw.filledCircle(x + 0.5, y + 0.5, 0.25);
        StdDraw.show(30);
    }



    // draw the maze
    public void draw() {
        StdDraw.setPenColor(StdDraw.RED);

        //Edit: Changed starting and ending points
        StdDraw.filledCircle(1.5, 1.5, 0.375);
        StdDraw.filledCircle(N + 0.5, N + 0.5, 0.375);


        StdDraw.setPenColor(StdDraw.BLACK);
        for (int x = 1; x <= N; x++) {
            for (int y = 1; y <= N; y++) {
                if (south[x][y]) StdDraw.line(x, y, x + 1, y);
                if (north[x][y]) StdDraw.line(x, y + 1, x + 1, y + 1);
                if (west[x][y])  StdDraw.line(x, y, x, y + 1);
                if (east[x][y])  StdDraw.line(x + 1, y, x + 1, y + 1);
            }
        }
        StdDraw.show(10);
    }

    public void drawWallMatrix(){

        System.out.println("Top walls");
        for(int x=1;x<N+1;x++)
        { for(int y=1;y<N+1;y++)
        {   if(south[y][x]==true)
            System.out.print(1+" ");
        else
            System.out.print(0+" ");
        }
        System.out.println();
    }
         System.out.println();
            System.out.println("Right walls");
        for(int x=1;x<N+1;x++)
        { for(int y=1;y<N+1;y++)
        {   if(east[y][x])
            System.out.print(1+" ");
        else
            System.out.print(0+" ");
        }
        System.out.println();
    }
          System.out.println();
            System.out.println("Left walls");
        for(int x=1;x<N+1;x++)
        { for(int y=1;y<N+1;y++)
        {   if(west[y][x])
            System.out.print(1+" ");
        else
            System.out.print(0+" ");
        }
        System.out.println();
    }

          System.out.println();
            System.out.println("Bottom walls");
        for(int x=1;x<N+1;x++)
        { for(int y=1;y<N+1;y++)
        {   if(north[y][x])
            System.out.print(1+" ");
        else System.out.print(0+" ");
        }
        System.out.println();
    }



    }


    // a test client
    public static void main(String[] args) {

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

                 System.out.println("   " + diff);
            }

        }

//
//        int N = 50;
//        Maze maze = new Maze(N);
//       // maze.initMatrix();
//        StdDraw.show(0);
//
//        maze.draw();
//        maze.solveRandom();
//        //maze.solveDFS();
//
//       maze.drawWallMatrix();
//       System.out.println(maze.hasTopWall(3, 2));
//       System.out.println(maze.hasBottomWall(3, 2));
//       System.out.println(maze.hasRightWall(3, 2));
//       System.out.println(maze.hasLeftWall(3, 2));

    }

}
