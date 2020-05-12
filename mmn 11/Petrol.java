import java.util.Scanner;

//---------------------------------------
// Created by: Golan Shoshani 203528088
// This program will get from user miles per gallon
// and converte to litres consumption for 100 km.
//---------------------------------------

public class Petrol{
    public static void main (String [] args){
        // Setting known values for calaculation.
        final int MAX_ROUND = 100;
        final int MAX_KM = 100;
        final double MILE_TO_KM = 1.609; 
        final double GALLON_TO_LITRES = 3.785;

        System.out.println ("Please enter the car's petrol consumption " +
                            "measured in miles/gallon:");
        
        // Getting the value from the user with Scanner.
        Scanner scan = new Scanner (System.in);
        double milesPerGallon = scan.nextDouble();

        /* Calculating user input for the convertion and rounding
           the result to .2 accuracy.
        */
        double litresPerKm = MAX_KM / (milesPerGallon * MILE_TO_KM) * GALLON_TO_LITRES; 
        double roundedResult = Math.round(litresPerKm * MAX_ROUND) / (MAX_ROUND * 1.0);

        System.out.println("The car's petrol consumption converted to litres/100km is:\n" + roundedResult);
    } // end of method main
} //end of class Petrol