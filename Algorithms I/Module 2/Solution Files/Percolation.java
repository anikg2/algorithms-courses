import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.WeightedQuickUnionUF;

public class Percolation {

    private int gridSize;
    private int openSites;
    private WeightedQuickUnionUF uf;
    private boolean[][] grid;

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

        // Now, initialize the grid with all cells closed
        this.grid = new boolean[n][n];

    }

    // opens the site (row, col) if it is not open already
    public void open(int row, int col) {
        if (row <= 0 || row > this.gridSize || col <= 0 || col > this.gridSize) {
            throw new IllegalArgumentException("Invalid argument(s) passed to function");
        }

        if (!this.isOpen(row, col)) {
            // Increment number of open sites and set array value at location
            this.openSites++;
            this.grid[row - 1][col - 1] = true;
            int n = this.gridSize;
            int searchElement = ((row - 1) * n) + col;

            // If it is in the top row, union with the source
            if (row == 1) {
                this.uf.union(0, searchElement);
            }
            // If it is in the bottom row, union with the sink
            if (row == n) {
                this.uf.union((n * n) + 1, searchElement);
            }

            // Check if neighbors are open, and if so, union with them
            if (row > 1 && this.isOpen(row - 1, col)) {
                this.uf.union((row - 2) * n + col, searchElement);
            }
            if (row < n && this.isOpen(row + 1, col)) {
                this.uf.union((row) * n + col, searchElement);
            }
            if (col > 1 && this.isOpen(row, col - 1)) {
                this.uf.union((row - 1) * n + col - 1, searchElement);
            }
            if (col < n && this.isOpen(row, col + 1)) {
                this.uf.union((row - 1) * n + col + 1, searchElement);
            }
        }
    }

    // is the site (row, col) open?
    public boolean isOpen(int row, int col) {
        if (row <= 0 || row > this.gridSize || col <= 0 || col > this.gridSize) {
            throw new IllegalArgumentException("Invalid argument(s) passed to function");
        }
        return this.grid[row - 1][col - 1];
    }

    // is the site (row, col) full?
    public boolean isFull(int row, int col) {
        if (row <= 0 || row > this.gridSize || col <= 0 || col > this.gridSize) {
            throw new IllegalArgumentException("Invalid argument(s) passed to function");
        }
        // If the element at the given index is part of the same set as the source, return true
        int searchElement = ((row - 1) * this.gridSize) + col;
        return this.uf.find(searchElement) == this.uf.find(0);
    }

    // returns the number of open sites
    public int numberOfOpenSites() {
        return this.openSites;
    }

    // does the system percolate?
    public boolean percolates() {
        // If source and sink are part of the same set, the system percolates
        return this.uf.find(0) == this.uf.find(this.gridSize * this.gridSize + 1);
    }

    // test client (optional)
    public static void main(String[] args) {
        int n = 50;
        Percolation test = new Percolation(n);
        // Monte Carlo Simulation, open a cell at random until the system percolates
        while (!test.percolates()) {

            int row = StdRandom.uniformInt(1, n + 1);
            int col = StdRandom.uniformInt(1, n + 1);
            if (!test.isOpen(row, col)) {
                test.open(row, col);
            }

        }

        // Number of open sites when the system percolates
        System.out.println(test.numberOfOpenSites());
    }

}
