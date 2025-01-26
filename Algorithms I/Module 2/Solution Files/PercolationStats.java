import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.StdStats;

public class PercolationStats {

    private double[] samples;
    private int t;
    private double constant = 1.96;

    // perform independent trials on an n-by-n grid
    public PercolationStats(int n, int trials) {
        if (n <= 0 || trials <= 0) {
            throw new IllegalArgumentException("Invalid value of n or trials");
        }
        this.t = trials;
        this.samples = new double[trials];
        for (int idx = 0; idx < trials; idx++) {
            Percolation test = new Percolation(n);
            // Monte Carlo Simulation, open a cell at random until the system percolates
            while (!test.percolates()) {

                int row = StdRandom.uniformInt(1, n + 1);
                int col = StdRandom.uniformInt(1, n + 1);
                if (!test.isOpen(row, col)) {
                    test.open(row, col);
                }

            }
            samples[idx] = (double) test.numberOfOpenSites() / (n * n);
        }
    }

    // sample mean of percolation threshold
    public double mean() {
        return StdStats.mean(this.samples);
    }

    // sample standard deviation of percolation threshold
    public double stddev() {
        return StdStats.stddev(this.samples);
    }

    // low endpoint of 95% confidence interval
    public double confidenceLo() {
        return this.mean() - ((this.constant * this.stddev()) / Math.sqrt(this.t));
    }

    // high endpoint of 95% confidence interval
    public double confidenceHi() {
        return this.mean() + ((this.constant * this.stddev()) / Math.sqrt(this.t));
    }

    // test client (see below)
    public static void main(String[] args) {
        PercolationStats getStats = new PercolationStats(2, 10000);
        System.out.println(getStats.mean());
        System.out.println(getStats.stddev());
        System.out.println(getStats.confidenceLo());
        System.out.println(getStats.confidenceHi());

    }

}
