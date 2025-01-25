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

        // Now, initialize the grid with all cells closed
        this.grid = new int[n][n];

        // Finally, open a cell at random until the system percolates
        while (!this.percolates()) {

            int randomRow = StdRandom.uniformInt(1, n + 1);
            int randomCol = StdRandom.uniformInt(1, n + 1);
            if (this.isOpen(randomRow, randomCol)) {
                continue;
            }
            else {
                if (this.openSites == 50) {
                    break;
                }
                this.openSites++;
                this.open(randomRow, randomCol);
                // If it is in the top row, union with the source
                if (randomRow == 1) {
                    this.uf.union(0, randomCol);
                }

                // If it is in the bottom row, union with the sink
                if (randomRow == n) {
                    this.uf.union((n * n) + 1, ((randomRow - 1) * n) + randomCol);
                }

                // Debugging to check if union works
                this.uf.union(0, ((randomRow - 1) * n) + randomCol);
                // Check the parents
                int parent0 = this.uf.find(0);
                int parent1 = this.uf.find(((randomRow - 1) * n) + randomCol);
                boolean a = parent0 == parent1;
                System.out.println(a);
            }
        }
    }

    // opens the site (row, col) if it is not open already
    public void open(int row, int col) {
        if (row <= 0 || row > this.gridSize || col <= 0 || col > this.gridSize) {
            throw new IllegalArgumentException("Invalid argument(s) passed to function");
        }
        this.grid[row - 1][col - 1] = 1;
    }

    // is the site (row, col) open?
    public boolean isOpen(int row, int col) {
        if (row <= 0 || row > this.gridSize || col <= 0 || col > this.gridSize) {
            throw new IllegalArgumentException("Invalid argument(s) passed to function");
        }
        return this.grid[row - 1][col - 1] == 1;
    }

    // is the site (row, col) full?
    public boolean isFull(int row, int col) {
        if (row <= 0 || row > this.gridSize || col <= 0 || col > this.gridSize) {
            throw new IllegalArgumentException("Invalid argument(s) passed to function");
        }
        // If the element at the given index is part of the same set as the source, return true
        return this.uf.find(((row - 1) * this.gridSize) + col) == this.uf.find(0);
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
        Percolation test = new Percolation(50);
        // Number of open sites
        System.out.println(test.numberOfOpenSites());
        // Does the system percolate?
        System.out.println(test.percolates());
    }

}
