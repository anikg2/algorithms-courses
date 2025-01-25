public class PercolationStats {

    // perform independent trials on an n-by-n grid
    public PercolationStats(int n, int trials) {
        if (n <= 0 || trials <= 0) {
            throw new IllegalArgumentException("Invalid value of n or trials");
        }
    }

    // sample mean of percolation threshold
    public double mean() {
        return 2.0;
    }

    // sample standard deviation of percolation threshold
    public double stddev() {
        return 2.0;
    }

    // low endpoint of 95% confidence interval
    public double confidenceLo() {
        return 2.0;
    }

    // high endpoint of 95% confidence interval
    public double confidenceHi() {
        return 2.0;
    }

    // test client (see below)
    public static void main(String[] args) {
        
    }

}
