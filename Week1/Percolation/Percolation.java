import edu.princeton.cs.algs4.WeightedQuickUnionUF;
/**
 * A percolation with method of open, isFull, isOpen, isPercoates
 * to know when open sites (row, col), does it percolate or not.
 */

public class Percolation {

    private int N;
    private WeightedQuickUnionUF unionOpen;
    private WeightedQuickUnionUF unionFull;
    private boolean[] sites;
    private int top;
    private int bottom;
    private int openSiteNumber;


    public Percolation(int n) {

      if( n < 1 ){
        throw new IllegalArgumentException("n should greater than 0");
        }

        //initial variables

        N = n;

        // create a 1D array to store if x, y is open
        sites = new boolean[ N * N + 2];

        top = 0;
        bottom = N * N + 1;
        openSiteNumber = 0;

        sites[top] = true;
        sites[bottom] = true;

        unionOpen = new WeightedQuickUnionUF( N * N + 2);
        unionFull = new WeightedQuickUnionUF( N * N + 2);

    } // create n-by-n grid, with all sites blocked


    /**
     * @param  y  row
     * @param  x  column
     */
    private void checkBoundary(int y, int x) {

    if (x < 1 || y < 1 || x > N || y > N) {
	
	throw new IllegalArgumentException("row " + "and column should in the range: 1 - " + N);

    }

    } //check if x, y is outside grid 

    /**
     * @param  y  row
     * @param  x  column
     * @return integer
     */
    private int get1DIndex(int y, int x) {

      checkBoundary(y, x);
      return N * (y - 1) + x;

    } // turn a coordinator to 1D array index

    /**
     * @param  y  row
     * @param  x  column
     */
    public void open(int y, int x) {

      if (isOpen(y, x)) {
        return;
      }
      openSiteNumber += 1;
      sites[get1DIndex(y, x)] = true;
      unionNearOpenSites(y, x);

    } // open site (row, col) if it is not open already

    /**
     * @param  y  row
     * @param  x  column
     */
    private void unionNearOpenSites(int y, int x) {

        int curIndex = get1DIndex(y, x);

        if (y == 1) { // first row
            unionOpen.union(curIndex, top);
            unionFull.union(curIndex, top);
        }

        if (y == N) { // last row
            unionOpen.union(curIndex, bottom);
        }

        for (int d = 0; d < 4; d++) { // d means direction number

            int nearIndex = getNearIndex(y, x, d);

            if (nearIndex > 0) {

            unionOpen.union(curIndex, nearIndex);
            unionFull.union(curIndex, nearIndex);

            }
        }

    } // union near site if they are open


    /**
     * @param  y  row
     * @param  x  column
     * @param  d  four directions, from zero to three 
     * means up, down, left, right
     * @return near Index
     */
    private int getNearIndex(int y, int x, int d) {

        int nearIndex = -1;

        if (d == 0 && y > 1) { // neighbor top

        nearIndex = get1DIndex(y - 1, x);

        } else if (d == 1 && y < N) { // neighbor down

          nearIndex = get1DIndex(y + 1, x);

        } else if (d == 2 && x > 1) { // neighbor left

          nearIndex = get1DIndex(y, x - 1);

        } else if (d == 3 && x < N) { // neighbor right

          nearIndex = get1DIndex(y, x + 1);

        }

        if (nearIndex > 0 && sites[nearIndex]) {
          return nearIndex;
        }

        return -1;

    } // get near site's index if they are open



    /**
     * @param  y  row
     * @param  x  column
     * @return 1D index 
     */
    public boolean isOpen(int y, int x) {

      return sites[get1DIndex(y, x)];

    } // is site (row, col) open?


    /**
     * @param  y  row
     * @param  x  column
     * @return true if is Full
     */
    public boolean isFull(int y, int x) {

        return unionFull.connected(top, get1DIndex(y, x));

    }// 

    /**
     * @return number of open sites
     */
    public int numberOfOpenSites() {

      return openSiteNumber;

    }// calculate the number of open sites

    /**
     * @return true if it is percolates
     */
    public boolean percolates() {

      return unionOpen.connected(top, bottom);

    }// return true if it  percolates

    public static void main(String[] args) {

    }

}
