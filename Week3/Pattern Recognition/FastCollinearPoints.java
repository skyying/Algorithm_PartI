import java.util.Arrays;
import java.util.ArrayList;
import java.util.Collections;

public class FastCollinearPoints{

  private ArrayList<LineSegment> lines = new ArrayList<>();
  private Point[] pointSet;

  public FastCollinearPoints(Point[] points){

      checkRepeatedPoints(points);

    pointSet = Arrays.copyOf(points, points.length);
    int len = points.length;
    Arrays.sort(pointSet);

    for (int i = 0; i < len - 1; ++i){
      
      ArrayList<Double> slopeCandidate = new ArrayList<>();
      Point[] restPoints = new Point[len - i - 1];
      Point currentPoint = pointSet[i];

      for( int j = 0; j < i; j++){
        slopeCandidate.add(currentPoint.slopeTo(pointSet[j]));
      }
      for(int j = i + 1; j < len; j++){
        restPoints[j - i - 1 ] = pointSet[j]; 
      } 
      Collections.sort(slopeCandidate);
      Arrays.sort(restPoints, currentPoint.slopeOrder());
      addSegments(currentPoint, slopeCandidate, restPoints);

    }

  }

    /**
     * check if segment is validate, if yes, add segments
     * @param startPoint
     * @param slopeCandidate
     * @param restPoints
     */
  private void addSegments(Point startPoint, ArrayList<Double> slopeCandidate, Point[]restPoints){

    int count = 1;
    double prevSlope = Double.NEGATIVE_INFINITY;
    double currentSlope = Double.NEGATIVE_INFINITY;
    int lenOfRestPoints = restPoints.length;

    Boolean isNewSegment = !slopeCandidate.contains(prevSlope);

    for( int j = 0; j < lenOfRestPoints ; j++ ){

      currentSlope = startPoint.slopeTo(restPoints[j]);
      isNewSegment = !slopeCandidate.contains(prevSlope);

      if( Double.compare(currentSlope, prevSlope) != 0) {

        if( count >= 3 && isNewSegment) { // check if need to add segments
          lines.add(new LineSegment(startPoint, restPoints[j - 1]));
        }
          count = 1; // reset count

      }else{

        count++;

      }

      prevSlope = currentSlope;  //update prevSlope

    } // end of for loop

    // check if continue same slope till the end
    if( count >= 3 && isNewSegment ){
      lines.add(new LineSegment(startPoint, restPoints[lenOfRestPoints - 1] ));
    }

  }

  /**
   * return segments number found
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

    /**
     * return how many number of this segments
     * @return number of segments
     */
  public int numberOfSegments(){

    return lines.size();

  }
  /**
   *  return LineSegment array
   */
  public LineSegment[] segments(){
    return lines.toArray(new LineSegment[numberOfSegments()]);
  }

  public static void main(String[] args){
  }
}
