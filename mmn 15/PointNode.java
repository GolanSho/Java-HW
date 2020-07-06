/**
 * This Class represent a single vertical in the polygon.
 * 
 * @author Golan Shoshani 203528088
 * @version 27/06/20
 */
public class PointNode {
    private Point _point;
    private PointNode _next;

    /**
     * Construct a new separte vertical.
     * @param p the point the vertical will hold.
     * @param _next will be null
     */
    public PointNode(Point p){
        _point = p;
        _next = null;
    }

    /**
     * Construct a vertical with the next vertical on the polygon.
     * @param p the point the vertical will hold.
     * @param n the pointer to the next vertical.
     */
    public PointNode(Point p, PointNode n) {
        _point = p;
        _next = n;
    }

    /**
     * Construct a vertical without a point, but will hold the next vertical on the polygon.
     * @param p the pointer to next vertical.
     */
    public PointNode(PointNode p){
        _next = p;
    }
    
    /**
     * returns the next vertical on the polygon.
     * @return the pointer to the next vertical.
     */
    public PointNode getNext( ) {
        return _next;
    }

    /**
     * set the next vertical on the polygon.
     */
    public void setNext(PointNode next) {
        _next = next;
    }

    /**
     * returns a copy of the point in the vertical.
     * @return a new copy of the point.
     */
    public Point getPoint() {
        return new Point(_point);
    }

    /**
     * set the point in the vertical.
     * @param p the point to be set.
     */
    public void setPoint(Point p) {
        _point = p;
    }

}