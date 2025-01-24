/* *****************************************************************************
 *  Name:              Ada Lovelace
 *  Coursera User ID:  123456
 *  Last modified:     October 16, 1842
 **************************************************************************** */

import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.WeightedQuickUnionUF;

public class Percolation {

    private int gridSize;
    private int openSites;
    private WeightedQuickUnionUF uf;
    private int[][] grid;

    // creates n-by-n grid, with all sites initially blocked
    public Percolation(int n) {
        // Edge case: Throw an exception if an invalid value is passed for n
        if (n <= 0) throw new IllegalArgumentException("Invalid value for n");

        // Initialize the variables for convenience
        this.gridSize = n;
        this.openSites = 0; // Initially, all sites are blocked

        // Initialize WeightedQuickUnionFind data structure
        // Use n^2 + 2 elements
        // 0 to n^2-1 for the grid, and the last two for source and sink
        this.uf = new WeightedQuickUnionUF(n * n + 2);

        // Now, initialize the grid and select a random cell and open it
        this.grid = new int[n][n];
        for (int i = 0; i < n * n; i++) {
            int randomRow = StdRandom.uniformInt(0, n);
            int randomCol = StdRandom.uniformInt(0, n);
            if (this.isOpen(randomRow, randomCol)) {
                continue;
            }
            else {
                this.openSites++;
                this.open(randomRow, randomCol);
            }
        }

    }

    // opens the site (row, col) if it is not open already
    public void open(int row, int col) {
        if (row <= 0 || row >= this.gridSize || col <= 0 || col >= this.gridSize) {
            throw new IllegalArgumentException("Invalid argument(s) passed to function");
        }
        this.grid[row - 1][col - 1] = 1;
    }

    // is the site (row, col) open?
    public boolean isOpen(int row, int col) {
        if (row <= 0 || row >= this.gridSize || col <= 0 || col >= this.gridSize) {
            throw new IllegalArgumentException("Invalid argument(s) passed to function");
        }
        return this.grid[row - 1][col - 1] == 1;
    }

    // is the site (row, col) full?
    public boolean isFull(int row, int col) {
        if (row <= 0 || row >= this.gridSize || col <= 0 || col >= this.gridSize) {
            throw new IllegalArgumentException("Invalid argument(s) passed to function");
        }
        // If the element at the given index is part of the same set as the source, return true
        return this.uf.find(//Fix this) == this.uf.find(this.gridSize*this.gridSize);
    }

    // returns the number of open sites
    public int numberOfOpenSites() {
        return this.openSites;
    }

    // does the system percolate?
    public boolean percolates() {
        // If source and sink are part of the same set, the system percolates
        return this.uf.find(this.gridSize * this.gridSize) == this.uf.find(
                this.gridSize * this.gridSize + 1);
    }

    // test client (optional)
    public static void main(String[] args) {

    }

}
