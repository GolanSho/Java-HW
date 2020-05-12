/**
 * This class  represents a Train.
 * 
 * @author Golan Shoshani 203528088
 * @version 24/04/20
 */
public class Train {
    private String _destination;
    private Time1 _departure; 
    private int _duration;
    private int _passengers;
    private int _seats;
    private int _price;
    private final int DEFAULT = 0; // default value if not in range

    /**
     * Constructor of class Train, Constructs a new train.
     * @param dest the destination.
     * @param h the hour of departure.
     * @param m the minute of departure.
     * @param duration the duration of the train, should be positive, otherwise it set to 0.
     * @param pass the amount of passengers, should be positive, otherwise it set to 0, also should be less than seats otherwise it set to seats.
     * @param seats the amount of seats, should be positive, otherwise it set to 0.
     * @param price the price for a ticket, should be positive, otherwise it set to 0.
     */
    public Train(String dest, int h, int m, int duration,
                 int pass, int seats, int price){
        _seats = (seats < DEFAULT) ? DEFAULT : seats; 
        
        // check if passengers are bigger then 0 and if bigger then seats, if true passengers = seats.
        if (pass >= DEFAULT){
            if (pass > seats)
                _passengers = _seats;
            else
                _passengers = pass;
        }
        else
            _passengers = DEFAULT;
    
        _duration = (duration < DEFAULT) ? DEFAULT : duration;

        _price = (price < DEFAULT) ? DEFAULT : price;
        
        _departure = new Time1(h, m); // constract a new time for depart

        _destination = dest;
    }

    /**
     * Copy constructor for Train. Construct a train with the same instance variables as another train.
     * @param other the other Train object
     */
    public Train(Train other){
        this._destination = other._destination;
        this._departure = other._departure; 
        this._duration = other._duration;
        this._passengers = other._passengers;
        this._seats = other._seats;
        this._price = other._price;
    }

    /**
     * Returns the arrival time.
     * @return the arrival time.
     */
    public Time1 getArrivalTime(){
        // return the time after added minutes of the duration.
        return(this._departure.addMinutes(_duration));
    }

    /**
     * returns the departure time.
     * @return the departure time.
     */
    public Time1 getDeparture(){
        Time1 getDepart = new Time1(_departure);
        return(getDepart);
    }

    /**
     * returns the destination.
     * @return the destination.
     */
    public String getDestination(){
        return(_destination);
    }

    /**
     * returns the duration of the train.
     * @return the duration of the train.
     */
    public int getDuration(){
        return(_duration);
    }

    /**
     * returns the number of passengers.
     * @return the number of passengers.
     */
    public int getPassengers(){
        return(_passengers);
    }

    /**
     * returns the price of the train.
     * @return the price of the train.
     */
    public int getPrice(){
        return(_price);
    }

    /**
     * returns the number of seats.
     * @return the number of seats.
     */
    public int getSeats(){
        return(_seats);
    }

    /**
     * updates the departure time of the train. t in not null.
     * @param t the time of the train.
     */
    public void setDeparture(Time1 t){
        Time1 setDepart = new Time1(t); 
        this._departure = setDepart;
    }

    /**
     * updates the destination of the train. d in not null.
     * @param d the destination
     */
    public void setDestination(String d){
        _destination = d;
    }

    /**
     * updates the duration of the train. d should be positive or zero, otherwise duration is unchanged.
     * @param d the duration.
     */
    public void setDuration(int d){
        if (d >= DEFAULT)
            _duration = d;
    }

    /**
     * updates the number of passengers. p should be positive or zero, otherwise passengers is unchanged. p should be less than seats otherwise it should be set to seats.
     * @param p the number of passengers.
     */
    public void setPassengers(int p){
        if (p >= DEFAULT)
            _passengers = (p < _seats) ? p : _seats;
    }

    /**
     * updates the number of seats. s should be positive or zero, otherwise seats is unchanged. s should be larger than passengers, otherwise seats is unchanged.
     * @param s the number of seats.
     */
    public void setSeats(int s){
        if (s >= DEFAULT)
            _seats = (s > _passengers) ? s : _seats;
    }

    /**
     * updates the price. p should be positive or zero, otherwise price is unchanged.
     * @param p the price.
     */
    public void setPrice(int p){
        if (p >= DEFAULT)
            _price = p;
    }

    /**
     * Return a string representation of the train.
     * @return string representation of the train.
     */
    public String toString(){
        if (this.isFull())
            return("Train to " + _destination + " departs at " + _departure + ". Train is full.");
        else
            return("Train to " + _destination + " departs at " + _departure + ". Train is not full.");
    }

    /**
     * Check if the received train is equal to this train.
     * @param other the recived train
     * @return True if the received train is equal to this train.
     */
    public boolean equals(Train other){
        return((other._destination.equals(_destination)) && (other._seats == _seats) && (other._departure.equals(_departure)));
    }

    /**
     * Add a number of passengers to the train.
     * @param num the amount to add
     * @return True if the total number of current passengers and num is less or equal to seats.
     */
    public boolean addPassengers(int num){
        // check if total amount after adding passengers is less then the seats, if true then add the passengers. 
        if ((num + _passengers) > _seats)
            return false;
        else {
            _passengers += num;
            return true;
        }
    }

    /**
     * Returns true if train is full.
     * @return true if train is full.
     */
    public boolean isFull(){
        return(_passengers == _seats);
    }

    /**
     * Returns true if the price for this train is cheaper than the other train. other is not null.
     * @param other the other train to compare price with.
     * @return true if the price for this train is cheaper than the other train.
     */
    public boolean isCheaper(Train other){
        return(_price < other._price);
    }

    /**
     * Returns the total price for all passengers.
     * @return total price for all passengers.
     */
    public int totalPrice(){
        return(_passengers *_price);
    }

    /**
     * Returns true if the arrival time of this train is earlier than the arrival time of the other train. other is not null.
     * @param other the other train to compare arrival time with.
     * @return true if the arrival time of this train is earlier than arrival time of the other train.
     */
    public boolean arrivesEarlier(Train other){

        // check if the hour and minute of the train is less then the given train.
        if (this.getArrivalTime().getHour() == other.getArrivalTime().getHour())
            return(this.getArrivalTime().getMinute() < other.getArrivalTime().getMinute());
        else 
            return(this.getArrivalTime().getHour() < other.getArrivalTime().getHour());
    }
}