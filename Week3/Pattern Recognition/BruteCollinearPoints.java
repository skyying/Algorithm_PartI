import java.util.ArrayList;
import java.util.Arrays;

public class BruteCollinearPoints {

  private LineSegment[] segments;

  public BruteCollinearPoints(Point[] points) {

    if( points == null || points.length < 4) {
      throw new IllegalArgumentException("no points here");
    }

    checkRepeatedPoints(points);

    // copy points
    Point[] pointSet  = new Point[points.length];
    System.arraycopy( points, 0, pointSet, 0, points.length );
    Arrays.sort(pointSet);

    //create a new ArrayList to record segments
    ArrayList<LineSegment> segmentsFound = new ArrayList<>();

    //loop through points

    int N = pointSet.length;
    for( int p = 0; p < N; p++) {

      for( int q = p + 1; q < N; q++ ) {

        for(int r = q + 1; r < N; r++) {

          for( int s = r + 1; s < N; s++ ) {

            double slope1 = pointSet[p].slopeTo(pointSet[q]);
            double slope2 = pointSet[p].slopeTo(pointSet[r]);
            double slope3 = pointSet[p].slopeTo(pointSet[s]);

            int isOneTwoSame = Double.compare(slope1, slope2);

            int isTwoThreeSame = Double.compare(slope2, slope3);

            //if 3 slopes have same value, record segments
            if( isOneTwoSame == 0 && isTwoThreeSame == 0) {

              segmentsFound.add(new LineSegment(pointSet[p], pointSet[s]));
            }
          }

        }

      }
    }

    // turn arrayList to array 
    segments = new LineSegment[segmentsFound.size()];
    segmentsFound.toArray(segments);


  }   // finds all line segments containing 4 points

  /**
   * return number of line segments
   * @return {Number}
   */
  public int numberOfSegments()   {

    return segments.length;

  }

  /**
   * an array to store line segments
   * @return Array
   */
  public LineSegment[] segments()  {

    return segments;

  }   // the line segments

  /**
   * check if points are legal
   * @param points
   */
  private void checkRepeatedPoints(Point[] points) {

    for(int i =0; i<points.length; i++) {
      for(int j=i+1; j<points.length; j++ ) {
        if(points[i].compareTo(points[j])==0) {
          throw new IllegalArgumentException("Duplicated entries in given points.");
        }

      }
    }

  }

  public static void main(String[] args) {


  }
}
