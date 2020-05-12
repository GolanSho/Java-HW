/**
 * This class creates and changes time outpots by the user requset.
 * 
 * @author Golan Shoshani 203528088
 * @version 18/04/20
 */
public class Time1 {
    private int _hour;
    private int _minute;
    private final int DEFAULT = 0; // default time if not in range
    private final int MAX_HOUR = 23;
    private final int MAX_MINUTE = 59;
    private final int MINUTES_IN_HOUR = 60;

    /**
     * Initialze a Time object
     * @param h the hour value
     * @param m the minute value
     */
    public Time1(int h, int m){
        //checking if the given time is in range, if not setting to default.
        if ((h > MAX_HOUR) || (h < DEFAULT))
            _hour = DEFAULT;
        else
            _hour = h;
        
        if ((m > MAX_MINUTE) || (m < DEFAULT))
            _minute = DEFAULT;
        else
            _minute = m;
    }

    /**
     * Copy the time from other Time object.
     * @param t the other Time object
     */
    public Time1(Time1 t){
        this._hour = t._hour;
        this._minute = t._minute;
    }

    /**
     * Showing the currect hour.
     * @return  the hour
     */
    public int getHour(){
        return _hour;
    }

    /**
     * Showing the currect minute. 
     * @return the minute
     */
    public int getMinute(){
        return _minute;
    }

    /**
     * Setting the hour from 0 to 23.
     * @param num the value for the hour
     */
    public void setHour(int num){
        // checking if hour is between 0 - 23.
        if ((num <= MAX_HOUR) && (num >= DEFAULT))
            _hour = num;
    }

    /**
     * Setting the minute from 0 to 59.
     * @param num the value for the minute
     */
    public void setMinute(int num){
        // checking if minute is between 0 - 59.
        if ((num <= MAX_MINUTE) && (num >= DEFAULT))
            _minute = num;
    }

    /**
     * Formating the output to hh:mm
     * @return the formated time
     */
    public String toString(){
        // checking if its required to add 0 to the format.
        if (_hour < 10){
            if (_minute < 10)
                return("0" + _hour + ":0" + _minute);
            else
                return("0" + _hour + ":" + _minute);
        }
        else {
            if (_minute < 10)
                return (_hour + ":0" + _minute);
            else
                return (_hour + ":" + _minute);
        }
    }

    /**
     * Calculate how much minutes from midnight are in the current time.
     * @return the number of minutes from midnight
     */
    public int minFromMidnight(){
        return(_hour * MINUTES_IN_HOUR + _minute);
    }

    /**
     * Showing whether the given time is equal to the current time.
     * @param other the object Time to give
     * @return true for equal or false for not equal
     */
    public boolean equals(Time1 other){
        return((other._hour == _hour) && (other._minute == _minute));
    }

    /**
     * Showing if the given time is before the current time.
     * @param other the object Time to give
     * @return true for is before or false for not before
     */
    public boolean before(Time1 other){
        if (other._hour == _hour)
            return(_minute < other._minute);
        else
            return(_hour < other._hour);
    }

    /**
     * Showing if the given time is after the current time.
     * @param other the object Time to give
     * @return true for is after or false for not after
     */
    public boolean after(Time1 other){
        return other.before(this);
    }

    /**
     * Showing the difference in minutes between the given time and the current time.
     * @param other the other object Time to give
     * @return The difference in minutes
     */
    public int difference(Time1 other){
        // converting the hours to minutes, adding the minutes and calculating the difference 
        return(Math.abs((_hour * MINUTES_IN_HOUR + _minute) - (other._hour * MINUTES_IN_HOUR + other._minute)));
    }

    /**
     * Create a new Time object with the current time and changing the time 
     * by the amount of minutes that was given.
     * @param num the amount of minutes to change the time
     * @return A new modified Time object
     */
    public Time1 addMinutes(int num){
        // Initialze a new temp varibales to the current time so it wont chenge
        int newMinute = _minute;
        int newHour = _hour;
        int hoursToChange;
        
        // checking if the given value is positive or negative.
        if (num > DEFAULT){
            newMinute += num;

            // if the minutes surpass 59 then find the currect number after adding the hours.
            if (newMinute > MAX_MINUTE){
                // finding the hours to add.
                hoursToChange = newMinute / MINUTES_IN_HOUR;
                if (hoursToChange == DEFAULT)
                    hoursToChange++;
                
                newHour += hoursToChange;
                if (newHour > MAX_HOUR)
                    newHour = newHour % 24;
                
                newMinute = newMinute % MINUTES_IN_HOUR;
            }
        }
        else if (num < DEFAULT){
            newMinute += num;

            if (newMinute < DEFAULT){
                hoursToChange = (int)Math.ceil(Math.abs(newMinute) / 60.0);
                if (hoursToChange == 0)
                    hoursToChange++;
                
                newHour -= hoursToChange;
                if (newHour < DEFAULT)
                    newHour = 24 - (Math.abs(newHour % 24));

                newMinute = MINUTES_IN_HOUR - Math.abs(newMinute % MINUTES_IN_HOUR);
            }
        }

        Time1 newTime = new Time1(newHour, newMinute);
        return newTime;
    }

} // end of class

