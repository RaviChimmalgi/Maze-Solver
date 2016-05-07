# Maze-Solver
Maze Solver Using Custom DFS

Using an existing Java-based maze [program] ( http://algs4.cs.princeton.edu/41undirected/Maze.java.html), this solver tries to solve the maze using a custom Depth First Search Algorithm.

![Maze-Solver screen shot](https://github.com/RaviChimmalgi/Maze-Solver/blob/master/maze-solver.png)

## Custom DFS Algorithm
1. For every location, check for possible next non visited path steps
2. If more than 1 path, save location as decision point in a stack and pick path in order of (South, East, North, West)
3. If possible path is zero (dead-end), then return to the last decision point and pick next non-visited available path. While retracing back to decision point, the path is colored black.


## Running the app
- Easiest way is to download and run Maze-Solver.jar file. This will start solving random mazes with the mazes increasing in size every instance.
- Or you can download and open the project in Netbeans, Eclipse or any other Java IDE

## Technology used
Java
