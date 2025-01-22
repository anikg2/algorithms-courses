/* *****************************************************************************
 *  Name:              Ada Lovelace
 *  Coursera User ID:  123456
 *  Last modified:     October 16, 1842
 **************************************************************************** */

import edu.princeton.cs.algs4.StdRandom;

public class Percolation {

    private int gridSize;
    private int openSites;
    private int[][] gridOpen;
    private int[][] gridFull;
    private boolean doesItPercolate;

    // creates n-by-n grid, with all sites initially blocked
    public Percolation(int n) {
        // Edge case: Throw an exception if an invalid value is passed for n
        if (n <= 0) throw new IllegalArgumentException();

        // Initialize the variables for convenience
        this.gridSize = n;
        this.doesItPercolate = false;
        this.openSites = 0;

        // Initialize the two necessary arrays
        this.gridOpen = new int[this.gridSize][this.gridSize];
        this.gridFull = new int[this.gridSize][this.gridSize];

        // Monte Carlo Simulation
        // Keep opening random sites until the system percolates
        while (!this.doesItPercolate) {
            // Select a 2D index at random
            int randomRow = StdRandom.uniformInt(0, this.gridSize);
            int randomCol = StdRandom.uniformInt(0, this.gridSize);

            // If the site is already open, try again
            if (isOpen(randomRow + 1, randomCol + 1)) {
                continue;
            }

            // Otherwise, open the site and increase the count of open sites
            open(randomRow + 1, randomCol + 1);
            this.openSites++;

            // Continue from here!

        }
    }

    // opens the site (row, col) if it is not open already
    public void open(int row, int col) {
        if (row < 1 || row > this.gridSize || col < 1 || col > this.gridSize) {
            throw new IllegalArgumentException();
        }
        this.gridOpen[row - 1][col - 1] = 1;
    }

    // is the site (row, col) open?
    public boolean isOpen(int row, int col) {
        if (row < 1 || row > this.gridSize || col < 1 || col > this.gridSize) {
            throw new IllegalArgumentException();
        }
        return this.gridOpen[row - 1][col - 1] == 1;
    }

    // is the site (row, col) full?
    public boolean isFull(int row, int col) {
        if (row < 1 || row > this.gridSize || col < 1 || col > this.gridSize) {
            throw new IllegalArgumentException();
        }
        return this.gridFull[row - 1][col - 1] == 1;
    }

    // returns the number of open sites
    public int numberOfOpenSites() {
        return this.openSites;
    }

    // does the system percolate?
    public boolean percolates() {
        return this.doesItPercolate;
    }

    // test client (optional)
    public static void main(String[] args) {

    }

}
