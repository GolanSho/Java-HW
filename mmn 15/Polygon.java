
/**
 * This Class represent a Polygon by a list that saves the order of the vertices
 * by the order of them in the polygon.
 * 
 * @author Golan Shoshani 203528088
 * @version 27/06/20
 */
class Polygon {
    private PointNode _head;

    /**
     * Construct an empty polygon.
     */
    public Polygon() {
        _head = null;
    }

    /**
     * add an vertex with a point to a specific position.
     * shold be within the range of the polygon (can be added to the last position).
     * The Time comlexity is O(n)
     * The Space complexity O(1)
     * @param p the point to add.
     * @param pos the posotion to be added on.
     * @return true if added and false if not.
     */
    public boolean addVertex(Point p, int pos){
        if(pos <= 0 || pos > listLength()+1)  // check if the position given is within the range
            return false;                     // the listLength time complexity is O(n)

        PointNode node = new PointNode(p);    // creating a new node for the given point.

        if(_head == null)
            _head = node;
        else {
            int counter = 1;
            PointNode ptr = _head;

            while (ptr.getNext() != null && counter < pos-1){
                ptr = ptr.getNext();
                counter++;
            }
            node.setNext(ptr.getNext());      // set the node next pointer to to the next in the list
            ptr.setNext(node);                // set the next pointer to node.
        }
        return true;
    }

    // Method to get the length of the polygon.
    private int listLength() {
        PointNode ptr = _head;
        int length = 0;
        while (ptr != null){
            ptr = ptr.getNext();
            length++;
        }
        return length;
    }

    /**
     * getting the highest vertex on the polygon.
     * if there is more then 1 get the first one that was found.
     * The Time comlexity is O(n)
     * The Space complexity O(1)
     * @return copy of the highest vertex.
     */
    public Point highestVertex(){
        if(_head == null)
            return null;
        
        return highestVertex(_head, _head.getPoint());   // recursive call time is O(n)
    }

    // overloading Method to help find the highest vertex.
    private Point highestVertex(PointNode ptr, Point highest){
        if (ptr.getNext() == null)
            return highest;

        if(ptr.getNext().getPoint().isAbove(highest))    // if the next point is above the highest till now
            highest = ptr.getNext().getPoint();          // set the highest to that.
        
        return highestVertex(ptr.getNext(), highest);
    }

    /**
     * This method represent the polygon in a string.
     * The Time comlexity is O(n)
     * The Space complexity O(1)
     * @return the formated string with the polygon info.
     */
    public String toString(){
        if(_head == null)
            return("The polygon has 0 vertices.");

        int length = listLength();
        PointNode ptr = _head;
        String str = ("The polygon has " + length + " vertices:\n(");

        if(length == 1)
            return (str + ptr.getPoint().toString() + ")");

        do {str = (str + ptr.getPoint().toString() + ",");
            ptr = ptr.getNext();
        }
        while (ptr.getNext() != null);
        str = (str  + ptr.getPoint().toString() + ")");
        
        return str;
    }

    /**
     * Calculating the perimeter of the polygon, the polygon should have at least 3 vertices.
     * The Time comlexity is O(n)
     * The Space complexity O(1)
     * @return the perimeter.
     */
    public double calcPerimeter(){
        int length = listLength();
        PointNode ptr = _head;

        if(length < 3){            // if there are less then 3 vertices 
            if (length == 2)       // than if there are 2 return the distance between them.
                return ptr.getPoint().distance(ptr.getNext().getPoint());
            return 0;
        }
        else
            return calcPerimeter(ptr, 0.0);  // start recursive call.
    }

    // overloading Method to help calculating the perimeter.
    private double calcPerimeter(PointNode ptr, double perimeter){
        if(ptr.getNext() == null)
            return perimeter + ptr.getPoint().distance(_head.getPoint());
        
        perimeter += ptr.getPoint().distance(ptr.getNext().getPoint());   // adding the distance between the two points to the perimeter.

        return calcPerimeter(ptr.getNext(), perimeter);
    }

    /**
     * Calculating the area of the polygon, if there are less then 3 vertices return 0.
     * The Time comlexity is O(n)
     * The Space complexity O(1)
     * @return the calculated area.
     */
    public double calcArea(){
        int length = listLength();
        
        if(length < 3 || _head == null)
            return 0;
        
        PointNode startNode = _head;
        PointNode curreNode = _head.getNext();
        PointNode nextNode = curreNode.getNext();  // setting the first triangle

        if(length == 3)
            return calcTriangle(startNode.getPoint(), curreNode.getPoint(), nextNode.getPoint());  
        else{
            double area = 0;

            while(nextNode.getNext() != null){
                area += calcTriangle(startNode.getPoint(), curreNode.getPoint(), nextNode.getPoint());   // using calcTrinangle method to calculate
                curreNode = curreNode.getNext();                                                         // the triangle area and add to the total area. 
                nextNode = nextNode.getNext();
            }
            return area + calcTriangle(startNode.getPoint(), curreNode.getPoint(), nextNode.getPoint());
        }
    }

    // method to help calculate an triangle area the time is O(1)
    private double calcTriangle(Point a, Point b, Point c){
        if(a == null || b == null || c == null)
            return 0;
        
        double triArea;
        double distA = a.distance(b);
        double distB = b.distance(c);
        double distC = c.distance(a);
        double s = (distA + distB + distC) / 2;  // s will be the half of the triangle perimeter

        triArea = Math.sqrt(s * (s-distA) * (s-distB) * (s - distC));

        return triArea;
    }

    /**
     * check if the area of the current polygon is bigger the other one.
     * The Time comlexity is O(1)
     * The Space complexity O(1)
     * @param other the other polygon.
     * @return true if bigger and false if not.
     */
    public boolean isBigger(Polygon other){
        return (this.calcArea() > other.calcArea()); 
    }

    /**
     * find the vertex by a given point, if the point is not in the polygon then return -1.
     * The Time comlexity is O(n)
     * The Space complexity O(1)
     * @param p the point to find
     * @return the position of the vertex with the given point.
     */
    public int findVertex(Point p){
        return findVertex(p, _head);   
    }

    // overloading method help to find the vertex.
    public int findVertex(Point p, PointNode ptr){
        if(ptr == null)
            return -1;
        
        if(ptr.getPoint().equals(p))   // if the currnt vertex point is equal to the given point return 1
            return 1;                  // if not add 1 and check the next.
        else
            return 1 + findVertex(p, ptr.getNext());
    }

    // help method to find vertex by a given position.
    private PointNode findNode(int pos){
        PointNode ptr = _head;
        int counter = 1;

        while(ptr.getNext() != null && counter < pos){
            ptr = ptr.getNext();
            counter++;
        }

        return ptr;
    }

    /**
     * get the next vertex by the given point.
     * The Time comlexity is O(n) - this method use a couple of O(n) methods. 
     * The Space complexity O(1)
     * @param p the point to search the next vertex by.
     * @return a copy of the point thats in the next vertex.
     */
    public Point getNextVertex(Point p){
        int pointPos = findVertex(p);  // getting the vertex position. 
        int length = listLength();

        if(pointPos == -1)
            return null;
        else if(pointPos == 1){
            if(length == 1)
                return new Point(p);
            else
                return new Point(_head.getNext().getPoint()); // if there is 1 vertex get his point if not get the next vertex point.
        }
        else if (pointPos == length)
            return new Point(_head.getPoint());
        else
            return new Point(findNode(pointPos).getNext().getPoint()); // using findNode to find the vertex by the position
    }                                                                  // and get the next vertex.

    /**
     * getting the bounding box for the polygon.
     * The Time comlexity is O(n)
     * The Space complexity O(1)
     * @return the bounding box as polygon.
     */
    public Polygon getBoundingBox(){
        if(listLength() < 3 || _head == null)
            return null;
        
        PointNode ptr = _head;
        return getBoundingBox(ptr.getPoint().getY(), ptr.getNext().getPoint().getY(), ptr.getPoint().getX(), ptr.getNext().getPoint().getX(), ptr);
        // using help method the get the 4 points to build the box, time is O(n).
    }

    // overloading method  to build the bounding box.
    private Polygon getBoundingBox(double maxHight, double minHight, double maxLength, double minLength, PointNode ptr){
        Polygon box = new Polygon();
        Point highestR = new Point(maxLength, maxHight);
        Point highestL = new Point(minLength, maxHight);
        Point lowestR = new Point(maxLength, minHight);
        Point lowestL = new Point(minLength, minHight);   // setting the starters points
        
        while(ptr != null){
            if(ptr.getPoint().isAbove(highestL))
                highestL.setY(ptr.getPoint().getY());
            if(ptr.getPoint().isAbove(highestR))
                highestR.setY(ptr.getPoint().getY());
            if(ptr.getPoint().isLeft(highestL))
                highestL.setX(ptr.getPoint().getX());
            if(ptr.getPoint().isRight(highestR))
                highestR.setX(ptr.getPoint().getX());
            if(ptr.getPoint().isUnder(lowestL))
                lowestL.setY(ptr.getPoint().getY());
            if(ptr.getPoint().isUnder(lowestR))
                lowestR.setY(ptr.getPoint().getY());
            if(ptr.getPoint().isLeft(lowestL))
                lowestL.setX(ptr.getPoint().getX());
            if(ptr.getPoint().isRight(lowestR))
                lowestR.setX(ptr.getPoint().getX());
            ptr = ptr.getNext();
        }
        box.addVertex(lowestL, 1);
        box.addVertex(lowestR, 2);
        box.addVertex(highestR, 3);
        box.addVertex(highestL, 4);

        return box;
    }
}