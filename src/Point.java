public class Point implements Comparable<Point>{

    // X coordinate
    private double x;
    // Y coordinate
    private double y;

    /**
     * Basic constructor for Points
     * @param x the x coordinate of the point
     * @param y the y coordinate of the point
     */
    public Point(double x, double y){
        this.x = x;
        this.y = y;
    }

    /**
     * Getter for the x coordinate
     * @return x coordinate
     */
    public double getX(){
        return x;
    }

    /**
     * Getter for the y coordinate
     * @return y coordinate
     */
    public double getY(){
        return y;
    }

    /**
     * Compares this object with the specified object for order.  Returns a
     * negative integer, zero, or a positive integer as this object is less
     * than, equal to, or greater than the specified object.
     *
     * @param o the object to be compared.
     * @return a negative integer, zero, or a positive integer as this object
     * is less than, equal to, or greater than the specified object.
     * @throws NullPointerException if the specified object is null
     * @throws ClassCastException   if the specified object's type prevents it
     *                              from being compared to this object.
     */
    @Override
    public int compareTo(Point o) {
        int compareX = Double.compare(this.getX(), o.getX());
        if(compareX != 0){
            return compareX;
        }
        else{
            int compareY = Double.compare(this.getY(), o.getY());
            if(compareY != 0) {
                return compareY;
            }
            else{
                return 0;
            }
        }
    }

    /**
     * toString in order to return the x and y values  EXAMPLE: (1.000,2.000)
     * @return the x and y value of the point
     */
    public String toString(){
        return("(" + String.format("%.3f",this.x) + "," + String.format("%.3f",this.y) + ")");
    }


}
