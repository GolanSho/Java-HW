import java.util.Scanner;

//-------------------------------------------------
// Created by: Golan Shoshani 203528088
// This program will get from user 3 digits per point
// and calculate the area and perimeter of the trapez.
//-------------------------------------------------

public class Trapezoid{
    public static void main (String [] args){
        Scanner scan = new Scanner(System.in);

        System.out.println("Please enter the left point coordinates of the base followed by its length:");
        // Getting values of point 1 and 2
        int pointA1 = scan.nextInt();
        int pointA2 = scan.nextInt();
        int pointALength = scan.nextInt();
        
        System.out.println("Please enter the left point coordinates of the other base followed by its length:");
        
        int pointB1 = scan.nextInt();
        int pointB2 = scan.nextInt();
        int pointBLength = scan.nextInt();
        
        // Calculating the area of the trapez
        double areaOfTrapez = (Math.abs(pointA2 - pointB2) * (pointALength + pointBLength)) / 2;
        
        // Calculating the Sides of the trapez
        double wingA = Math.sqrt(Math.pow((pointA1 - pointB1),2) + Math.pow((pointA2 - pointB2),2));
        
        double wingB = Math.sqrt(Math.pow(((pointA1 + pointALength) - (pointB1 + pointBLength)),2) + Math.pow((pointA2 - pointB2),2));

        // Calculating the perimiter of the trapez
        double  perimeterOfTrapez = pointALength + pointBLength + wingA + wingB;
        
        System.out.println("The area of the trapezoid is " + areaOfTrapez);
        System.out.println("The perimeter of the trapezoid is " + perimeterOfTrapez);
    }
}