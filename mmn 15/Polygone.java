import sun.net.www.content.text.plain;

class Polygone {
    private PointNode _head;

    public Polygone() {
        _head = null;
    }

    public boolean addVertex(Point p, int pos){
        if(pos <= 0 || pos > listLength()+1)
            return false;

        if(_head == null)
            _head = p;
        else {
            int counter = 1;
            PointNode ptr = _head;

            while (ptr.getNext( ) != null || counter < pos)
                ptr = ptr.getNext( );
                counter++;
            PointNode tmp = ptr.getNext();
            ptr.getNext().setPoint(p);
            ptr.getNext().setNext(tmp);
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
        PointNode ptr = _head;
        
        return highestVertex(ptr, ptr.getPoint());
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

        if(length >= 0 && length < 3)
            if (length == 2)
                return ptr.getPoint().distance(ptr.getNext().getPoint());
            return 0;

        return calcPerimeter(ptr, 0);
    }

    private double calcPerimeter(PointNode ptr, double perimeter){
        if(ptr == null)
            return perimeter;
        
        perimeter += ptr.getPoint().distance(ptr.getNext().getPoint());

        return calcPerimeter(ptr.getNext(), perimeter);
    }

    
}