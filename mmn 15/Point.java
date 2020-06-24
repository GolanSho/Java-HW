public class Point {
    private double _x;
    private double _y;

    public Point(double x, double y){
        _x = x;
        _y = y;
    }
    public Point (Point other){
        this._x = other._x;
        this._y = other._y;
    }

    public double getX(){
        return _x;
    }

    public double getY(){
        return _y;
    }

    public void setX(double num){
        _x = num;
    }

    public void setY(double num){
        _y = num;
    }

    public String toString(){
        return("(" + getX() + "," + getY() + ")");
    }

    public boolean equals(Point other){
        if((this._x == other._x) && (this._y == other._y))
            return true;
        return false;
    }

    boolean isAbove (Point other){
        return(other._y < this._y);
    }

    boolean isUnder (Point other){
        return other.isAbove(this);
    }

    boolean isLeft (Point other){
        return(this._x < other._x);
    }

    boolean isRight (Point other){
        return other.isLeft(this);
    }

    double distance (Point p){
        return(Math.sqrt(Math.pow((this._x - p._x), 2) + Math.pow((this._y - p._y), 2)));
    }

    void move (double dx, double dy){
        if((this._x + dx) >= 0 && (this._y + dy) >= 0){
            setX(this._x + dx);
            setY(this._y + dy);
        }
    }   
}