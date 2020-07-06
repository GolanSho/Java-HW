/**
 * This Class represent a Point with x and y coordinates.
 * 
 * @author Golan Shoshani 203528088
 * @version 27/06/20
 */
public class Point {
    private double _x;
    private double _y;

    /**
    * Construct a point.
    * @param x The x coordinate
    * @param y The y coordinate
    */
    public Point(double x, double y){
        _x = x;
        _y = y;
    }

    /**
    * Copy constructor for Point.
    * Construct a point with the same coordinates as other point.
    * @param other The point object from which to construct the new point
    */
    public Point (Point other){
        this._x = other._x;
        this._y = other._y;
    }

    /**
    * Returns the x coordinate of the point.
    * @return The x coordinate of the point.
    */
    public double getX(){
        return _x;
    }

    /**
    * Returns the y coordinate of the point.
    * @return The y coordinate of the point.
    */
    public double getY(){
        return _y;
    }

    /**
    * Sets the x coordinate of the point.
    * @param x The new x coordinate
    */
    public void setX(double num){
        _x = num;
    }

    /**
    * Sets the y coordinate of the point.
    * @param y The new y coordinate
    */
    public void setY(double num){
        _y = num;
    }

    /**
    * Returns a string representation of this point.
    * @return String representation of this point
    */
    public String toString(){
        return("(" + getX() + "," + getY() + ")");
    }

    /**
    * Check if this point equals other point.
    * @param other The point to be compared with this point
    * @return true if this point equals other point
    */
    public boolean equals(Point other){
        if((this._x == other._x) && (this._y == other._y))
            return true;
        return false;
    }

    /**
    * Check if this point is above other point.
    * @param other The point to be compared with this point
    * @return true if this point is above other point
    */
    boolean isAbove (Point other){
        return(this._y > other._y);
    }

    /**
    * Check if this point is under other point.
    * @param other The point to check if this point is under
    * @return true if this point is under other point
    */
    boolean isUnder (Point other){
        return other.isAbove(this);
    }

    /**
    * Check if this point is to the left of other point.
    * @param other The point to check if this point is left of
    * @return true if this point is to the left of other point
    */
    boolean isLeft (Point other){
        return(this._x < other._x);
    }

    /**
    * Check if this point is to the right of other point.
    * @param other The point to check if this point is right of
    * @return true if this point is to the right of other point
    */
    boolean isRight (Point other){
        return other.isLeft(this);
    }

    /**
    * Calculate the distance between this point and other point.
    * @param other The point to calculate the distance from
    * @return The distance between this and p points
    */
    double distance (Point p){
        return(Math.sqrt(Math.pow((p._y - this._y), 2) + Math.pow((p._x - this._x), 2)));
    }

    /**
    * Moves the x coordinate by dX and the y coordinate by dY.
    * @param dX The amount to move in the x direction
    * @param dY The amount to move in the y direction
    */
    void move (double dx, double dy){
        setX(this._x + dx);
        setY(this._y + dy);
    }   
}