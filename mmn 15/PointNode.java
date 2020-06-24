public class PointNode {
    private Point _point;
    private PointNode _next;

    public PointNode(Point p){
        _point = p;
        _next = null;
    }

    public PointNode(Point p, PointNode n) {
        _point = p;
        _next = n;
    }

    public PointNode(PointNode p){
        _next = p;
    }
    
    public PointNode getNext( ) {
        return _next;
    }

    public void setNext(PointNode next) {
        _next = next;
    }

    public Point getPoint() {
        return new Point(_point);
    }

    public void setPoint(Point p) {
        _point = p;
    }

}