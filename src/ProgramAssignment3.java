/*
Name: Spenser Currier
Assignment: PA #3
Course/Semester: CS371 Fall 2019
Class Section: Section 1
Lab section: none
Sources consulted: Sola Gbenro, Colton Freitas, Daniel Carver
        We all were working together in our capstone room for example, if somebody was confused we would put up an example of what we had to do next(not code)
        and tell them the direction to go next. Also, if anybody ran into a problem others would attempt to help debug code.
 */
import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class ProgramAssignment3 {

    //All the points part of the Convex Hull
    private static Set<Point> convexHull = new HashSet<>();

    public static void main(String[] args){
        ArrayList<Point> Points = new ArrayList<>();

        //Read file
        try{
            File f = new File(args[0]);
            Scanner sc = new Scanner(f);
            double numPoints = sc.nextDouble();
            for( int i = 0; i < numPoints; i++){
                double x = sc.nextDouble();
                double y = sc.nextDouble();
                Points.add(new Point(x,y));
            }
        }
        catch(FileNotFoundException e){
            System.out.println("File not Found");
        }

        Collections.sort(Points);
        //Find first two points
        Point minX = Points.get(0);
        Point maxX = Points.get(Points.size() - 1);

        //Find upper half
        findUpperHull(minX, maxX,Points);
        //Find lower half
        findUpperHull(maxX, minX,Points);

        //Loop over array and print each point
        ArrayList<Point> convexArrayList = new ArrayList<>(convexHull);
        Collections.sort(convexArrayList);
        for(Point p : convexArrayList) {
            System.out.println(""+ p.toString());
        }

    }


    /**
     * Adds points to the ConvexHull set if they are apart of the hull
     * @param p The first point
     * @param q The second point
     * @param s The List of all point to compare to the first and second point
     */
    public static void findUpperHull(Point p, Point q, List<Point> s){
        ArrayList<Point> upperPointsLeft = new ArrayList<>();
        ArrayList<Point> upperPointsRight = new ArrayList<>();
        if(s.isEmpty()){
            convexHull.add(p);
            convexHull.add(q);
            return;
        }
        //The farthest distance from the line
        double maxDistance = findDeterminate(p,q,s.get(0));
        //The point farthest from the line
        Point pointMax = s.get(0);

        //Find the point farthest away from the line
        for (int k = 1; k < s.size(); k++) {
            Point p3 = s.get(k);
            double determinate = findDeterminate(p,q,p3);
            if(determinate >= maxDistance){
                maxDistance = determinate;
                pointMax = p3;
            }
        }
        //Adding points to their respective lists
        for (int i = 0; i < s.size(); i++) {
            //The current point from all the points
            Point currentPoint = s.get(i);
            //Determine if point is on the left side and add if needed
            double determinateLeft = findDeterminate(p,pointMax,currentPoint);
            if(determinateLeft >= 0 && currentPoint != pointMax){
                upperPointsLeft.add(currentPoint);
            }
            //Determine if point is on the right side and add if needed
            double determinateRight = findDeterminate(pointMax,q,currentPoint);
            if(determinateRight >= 0 && currentPoint != pointMax){
                upperPointsRight.add(currentPoint);
            }
        }
        //Recurse left
        findUpperHull(p,pointMax,upperPointsLeft);
        //Recurse Right
        findUpperHull(pointMax,q,upperPointsRight);

    }

    /**
     * Finds the determinate between three points
     * @param A The first point
     * @param B The second point
     * @param C The third point
     * @return The determinate
     */
    public static double findDeterminate(Point A, Point B, Point C){
        double determinate = 0.0;
        determinate += (A.getX() * B.getY());
        determinate += (C.getX() * A.getY());
        determinate += (B.getX() * C.getY());
        determinate -= (C.getX() * B.getY());
        determinate -= (B.getX() * A.getY());
        determinate -= (A.getX() * C.getY());
        return determinate;
    }
}
