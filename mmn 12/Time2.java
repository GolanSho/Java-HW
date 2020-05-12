/**
 * Represents time by the minutes from midnight. Values must represent a proper time.
 * 
 * @author Golan Shoshani 203528088
 * @version 21/04/20
 */
public class Time2 {
    private int _minFromMid;
    private final int DEFAULT = 0; // default time if not in range
    private final int MAX_HOUR = 23;
    private final int MAX_MINUTE = 59;
    private final int MINUTES_IN_HOUR = 60;

    /**
     * Constructs a Time2 object. 
     * Construct a new time instance with the specified hour and minute
     * @param h the hour value should be between 0-23
     * @param m the minute value should be between 0-59
     */
    public Time2 (int h, int m){
        if ((h > MAX_HOUR) || (h < DEFAULT))
            h = DEFAULT;
        
        if ((m > MAX_MINUTE) || (m < DEFAULT))
            m = DEFAULT;
        
        _minFromMid = h * MINUTES_IN_HOUR + m;
    }

    /**
     * Copy the time from other Time object.
     * @param t the other Time object
     */
    public Time2 (Time2 other) {
        this._minFromMid = other._minFromMid;
    }

    /**
     * Showing the currect hour.
     * @return  the hour
     */
    public int getHour(){
        return(_minFromMid / MINUTES_IN_HOUR);
    }

    /**
     * Showing the currect minute. 
     * @return the minute
     */
    public int getMinute(){
        return(_minFromMid % MINUTES_IN_HOUR);
    }

    /**
     * Setting the hour from 0 to 23.
     * @param num the value for the hour
     */
    public void setHour(int num){
        // checking if hour is between 0 - 23.
        if ((num <= MAX_HOUR) && (num >= DEFAULT))
            _minFromMid = getMinute() + (num * MINUTES_IN_HOUR);
    }

    /**
     * Setting the hour from 0 to 59.
     * @param num the value for the minute
     */
    public void setMinute(int num){
        // checking if minute is between 0 - 59.
        if ((num <= MAX_MINUTE) && (num >= DEFAULT))
            _minFromMid = (getHour() * MINUTES_IN_HOUR) + num;
    }

    /**
     * Calculate how much minutes from midnight are in the current time.
     * @return the number of minutes from midnight
     */
    public int minFromMidnight(){
        // the calculation already exist in the constractor.
        return(_minFromMid);
    }

    /**
     * Showing whether the given time is equal to the current time.
     * @param other the object Time to give
     * @return true for equal or false for not equal
     */
    public boolean equals(Time2 other){
        return((other.getHour() == getHour()) && (other.getMinute() == getMinute()));
    }

    /**
     * Showing if the given time is before the current time.
     * @param other the object Time to give
     * @return true for is before or false for not before
     */
    public boolean before(Time2 other){
        if (other.getHour() == getHour())
            return(getMinute() < other.getMinute());
        else
            return(getHour() < other.getHour());
    }

    /**
     * Showing if the given time is after the current time.
     * @param other the object Time to give
     * @return true for is after or false for not after
     */
    public boolean after(Time2 other){
        return other.before(this);
    }

    /**
     * Showing the difference in minutes between the given time and the current time.
     * @param other the other object Time to give
     * @return The difference in minutes
     */
    public int difference(Time2 other){
        return(Math.abs(_minFromMid - other._minFromMid));
    }

    /**
     * Formating the output to hh:mm
     * @return the formated time
     */
    public String toString(){
        // checking if its required to add 0 to the format.
        if (getHour() < 10){
            if (getMinute() < 10)
                return("0" + getHour() + ":0" + getMinute());
            else
                return("0" + getHour() + ":" + getMinute());
        }
        else {
            if (getMinute() < 10)
                return (getHour() + ":0" + getMinute());
            else
                return (getHour() + ":" + getMinute());
        }
    }

    /**
     * Create a new Time object with the current time and changing the time 
     * by the amount of minutes that was given.
     * @param num the amount of minutes to change the time
     * @return A new modified Time object
     */
    public Time2 addMinutes(int num){
        // Initialze a new temp varibales to the current time so it wont chenge
        int newMinute = getMinute();
        int newHour = getHour();
        int hoursToChange;
        
        // checking if the given value is positive or negative.
        if (num > DEFAULT){
            newMinute += num;

            // if the minutes surpass 59 then find the currect number after adding the hours.
            if (newMinute > MAX_MINUTE){
                newMinute = newMinute % MINUTES_IN_HOUR;
                
                // finding the hours to add.
                hoursToChange = (int)(Math.round(num / 60.0));
                if (hoursToChange == 0)
                    hoursToChange++;
                
                newHour += hoursToChange;
                if (newHour > MAX_HOUR)
                    newHour = newHour % 24;
            }
        }
        else if (num < DEFAULT){
            newMinute += num;

            if (newMinute < DEFAULT){
                newMinute = MINUTES_IN_HOUR - Math.abs(newMinute % MINUTES_IN_HOUR);

                hoursToChange = (int)(Math.ceil(Math.abs(num) / 60.0));
                if (hoursToChange == 0)
                    hoursToChange++;
                
                newHour -= hoursToChange;
                if (newHour < DEFAULT)
                    newHour = 24 - (Math.abs(newHour % 24));
            }
        }

        Time2 newTime = new Time2(newHour, newMinute);
        return newTime;
    }
}// end of class