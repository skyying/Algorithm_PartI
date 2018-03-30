/**
 * Monte Carlo simulation. To estimate the percolation threshold,
 * consider the following computational experiment:
 * Initialize all sites to be blocked.
 * 1. Repeat the following until the system percolates:
 * 2. Choose a site uniformly at random among all blocked sites.
 * 3. Open the site. 
 * The fraction of sites that are opened when the system percolates 
 * provides an estimate of the percolation threshold.
 *
 * @author: Skyying
 */


import edu.princeton.cs.algs4.StdStats;
import edu.princeton.cs.algs4.StdRandom;

public class PercolationStats{
    
    private final double mean;
    private final double stddev;
    private final double confidenceLo;
    private final double confidenceHi;
    
    
    /**
     * value variable and store random threshold of each trials
     *
     * @param n  n means this trail will using a n * n Percolation grid
     * @param trials how many trails will be conducted.
     */
    public PercolationStats(int n, int trials) {

        validate(n, trials); 
        
        double[] threshold = new double[trials];
                
        
        for (int i = 0; i < trials; i++) {
            
            Percolation grid = new Percolation(n);
            
            double opened = 0; 
            
            while (!grid.percolates()) {
        	
        	int x = StdRandom.uniform(n) + 1;
        	int y = StdRandom.uniform(n) + 1;
        	
        	if( !grid.isOpen(y, x) ) {
        	    grid.open(y, x);
        	    opened++;
        	}
        	
            }
       
            threshold[i] = opened / (double) (n * n);
           
            //store threshold for trials;
        
        }
        
        
        
        mean = StdStats.mean(threshold);
        stddev = StdStats.stddev(threshold);
        double confidenceInterval = 1.96 * stddev / Math.sqrt(trials);
        confidenceLo = mean - confidenceInterval;
        confidenceHi = mean + confidenceInterval;
        	
        
        
        
    } //perform trials independent experiments on an n-by-n grid

    

    /**
     * check bound of n and trial, if they are not validate, 
     * throw a error.
     * 
     * @param n  n can't be zero for creating a grid 
     * @param trials  trial number must be greater than zero
     */
    private void validate(int n, int trials) {
        if (n <= 0 || trials <= 0) {
            throw new IllegalArgumentException("n and trials must greater than zero");
        }
    } //check bound


    public double mean() {

        return mean;

    }// sample mean of percolation threshold



    public double stddev() {

        return stddev;

    }// sample standard deviation of percolation threshold



    public double confidenceLo() {

        return confidenceLo;

    }//low  end point of 95% confidence interval



    public double confidenceHi() {

        return confidenceHi;

    }//high end point of 95% confidence interval
   
    
    
    public static void main(String[] args){
      
	
	
    }

}
