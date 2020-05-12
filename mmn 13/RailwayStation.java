
/**
 * This class represent a train station and managing is schedule.
 * 
 * @author Golan Shoshani 203528088
 * @version 07/05/20
 */
public class RailwayStation {
    private Train [] _station; // create array for train objects
    private int _noOfTrs;
    private final int MAX_TRAINS = 100;
    private final int DEFAULT = 0; // default number
    private final int MAX_HOUR = 23, MAX_MIN = 59; // max hours in day and minutes in hour

    public RailwayStation(){
        _station = new Train[MAX_TRAINS]; // setting the size of the array to 100
        _noOfTrs = DEFAULT;
    }

    /**
     * adding a new train to the schedule.
     * @param f an train object, only works if the array isn't full and the train not exist, should not be null.
     * @return return true if train added or false if didnt.
     */
    public boolean addTrain(Train f){
        // check if the current number of trains is smaller then the sizze of the array.
        if (_noOfTrs < _station.length && f != null){
            for (int i = 0; i < _noOfTrs; i++){
                if (_station[i].equals(f))
                    return false;
            }
            // adding the train object in the last avilable index and raising the index count.
            _station[_noOfTrs] = new Train(f);
            _noOfTrs++;
            return true;
        }
        return false;
    }

    /**
     * remove a train from schedule, the train should exist and the array shouldn't be empty.
     * @param f the train object, shold not be null.
     * @return return true if the train was removed and false if didnt.
     */
    public boolean removeTrain(Train f){
        // check if the array isn't empty.
        if (_noOfTrs > DEFAULT && f != null){
            for (int i = 0; i < _noOfTrs; i++){
                if (_station[i].equals(f)){
                    for (int j = i; j < _noOfTrs-1; j++){
                        // overwrite the train in the current index with the next index
                        _station[j] = _station[j+1];
                    }
                    // reseting the last avilable index.  
                    _station[_noOfTrs -1] = null;
                    _noOfTrs--;
                    return true;
                }
            }
        }
        return false;
    }

    /**
     * return the earliest time of departure to a given destanation.
     * @param place an destination to check.
     * @return the time of the earliest train to the destination.
     */
    public Time1 firstDepartureToDestination(String place){
        if (_noOfTrs == DEFAULT || place == null)
            return null;
            
        int count = DEFAULT;
        
        // initialize an default time object to compare. 
        Time1 firstDepart = new Time1(MAX_HOUR, MAX_MIN);
        for (int i = 0; i < _noOfTrs; i++){
            // if the place is equal to the current index destination
            // and is departure before the time that is registerd then save that time.
            if (_station[i].getDestination().equals(place)){
                if (_station[i].getDeparture().before(firstDepart))
                    firstDepart = _station[i].getDeparture();
                    count++;
            }
        }
        if (count != DEFAULT)
            return(new Time1(firstDepart));
        else return null;
    }

    /**
     * return how many full trains thare are in the schedule.
     * @return how many full trains thare are in the schedule.
     */
    public int howManyFullTrains(){
        int fullTrains = DEFAULT;

        if (_noOfTrs == DEFAULT)
            return fullTrains;

        for (int i = 0; i < _noOfTrs; i++){
            // using the isFull method from the Train class.
            if (_station[i].isFull())
                fullTrains++;
        }
        return fullTrains;
    }

    /**
     * return the most popular destination.
     * @return the most popular destination.
     */
    public String mostPopularDestination(){
        if (_noOfTrs == DEFAULT)
            return null;
        
        String mostPopular = null; 
        int firstCount = DEFAULT;
        int secondCount = DEFAULT;

        // in the first loop check if the currect destination is equal the registerd one.
        // in the second loop check if the following indexs are equal the the destination from the first loop.
        for (int i = 0; i < _noOfTrs; i++){
            if (!_station[i].getDestination().equals(mostPopular)){
                for (int j = i; j < _noOfTrs; j++){
                    if (_station[i].getDestination().equals(_station[j].getDestination()))
                        firstCount++;
                }

                // if the count was bigger then the last count then change the registerd destination
                // and set the new count.
                if (firstCount > secondCount){
                    mostPopular = _station[i].getDestination();
                    secondCount = firstCount;
                }
                firstCount = DEFAULT;
            }
        }
        return mostPopular;
    }

    /**
     * formating the output of the class.
     * @return a new formated output.
     */
    public String toString(){
        if (_noOfTrs == DEFAULT)
            return ("There are no trains today.");
        
        String schedule = "The trains today are:\n";
        for (int i = 0; i < _noOfTrs; i++){
            schedule += _station[i].toString()+"\n";
        }
        return schedule;
    }

    /**
     * return an train object by the most expensive ticket.
     * @return the most expensive train object.
     */
    public Train mostExpensiveTicket(){
        if (_noOfTrs == DEFAULT)
            return null;

        // setting to the first index of the array.
        Train mostExpensive = _station[0];
        
        for (int i = 1; i < _noOfTrs; i++){
            if (!_station[i].isCheaper(mostExpensive))
                mostExpensive = _station[i];
        }
        return(new Train(mostExpensive));
    }

    /**
     * return train object by the longest duration.
     * @return the longest duration train object.
     */
    public Train longestTrain(){
        if (_noOfTrs == DEFAULT)
            return null;
        
        Train longest = _station[0];
        
        for (int i = 1; i < _noOfTrs; i++){
            if (_station[i].getDuration() > longest.getDuration())
                longest = _station[i];
        }
        return(new Train(longest));
    }
} // end of class