
class Polygon {
    private PointNode _head;

    public Polygon() {
        _head = null;
    }

    public boolean addVertex(Point p, int pos){
        if(pos <= 0 || pos > listLength()+1)
            return false;

        PointNode node = new PointNode(p);

        if(_head == null)
            _head = node;
        else {
            int counter = 1;
            PointNode ptr = _head;

            while (counter < pos){
                ptr = ptr.getNext();
                counter++;
            }
            node.setNext(ptr.getNext());
            ptr.setNext(node);
        }
        return true;
    }

    
    private int listLength() {
        PointNode ptr = _head;
        int length = 0;
        while (ptr != null){
            ptr = ptr.getNext();
            length++;
        }
        return length;
    }

    public Point highestVertex(){
        if(_head == null)
            return null;
        
        return highestVertex(_head, _head.getPoint());
    }

    private Point highestVertex(PointNode ptr, Point highest){
        if (ptr.getNext() == null)
            return highest;

        if(ptr.getNext().getPoint().isAbove(ptr.getPoint()))
            highest = ptr.getNext().getPoint();
        
        return highestVertex(ptr.getNext(), highest);
    }

    public String toString(){
        if(_head == null)
            return("The polygon has 0 vertices.");

        PointNode ptr = _head;    
        String str = ("The polygon has " + listLength() + " vertices: \n( "
                        + ptr.getPoint().toString());
        

        while (ptr != null) {
            str = (str  + " ," + ptr.getPoint().toString());
            ptr = ptr.getNext();
        }
        str += " )";
        
        return str;
    }

    public double calcPerimeter(){
        int length = listLength();
        PointNode ptr = _head;

        if(length >= 0 && length < 3){
            if (length == 2)
                return ptr.getPoint().distance(ptr.getNext().getPoint());
            return 0;
        }
        else
            return calcPerimeter(ptr, 0);
    }

    private double calcPerimeter(PointNode ptr, double perimeter){
        if(ptr == null)
            return perimeter;
        
        perimeter += ptr.getPoint().distance(ptr.getNext().getPoint());

        return calcPerimeter(ptr.getNext(), perimeter);
    }

    public double calcArea(){
        return 0.0;
    }

    public boolean isBigger(Polygon other){
        return (this.calcArea() > other.calcArea()); 
    }

    public int findVertex(Point p){
        return findVertex(p, _head);   
    }

    public int findVertex(Point p, PointNode ptr){
        if(ptr == null)
            return -1;
        
        if(ptr.getPoint().equals(p))
            return 1;
        else
            return 1 + findVertex(p, ptr);
    }

    private PointNode findNode(int pos){
        PointNode ptr = _head;
        int counter = 1;

        while(counter < pos){
            ptr = ptr.getNext();
        }

        return ptr;
    }

    public Point getNextVertex(Point p){
        int pointPos = findVertex(p);
        int length = listLength();

        if(pointPos == -1)
            return null;
        else if(pointPos == 1){
            if(length == 1)
                return new Point(p);
            else
                return new Point(_head.getNext().getPoint());
        }
        else if (pointPos == length)
            return new Point(_head.getPoint());
        else
            return new Point(findNode(pointPos).getNext().getPoint());
    }

    public Polygon getBoundingBox(){
        return null;
    }
}