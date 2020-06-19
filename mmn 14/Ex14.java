/**
 * This class includes the 4 tasks methods and private methods to help them.
 * 
 * @author Golan Shoshani 203528088
 * @version 19/06/20
 */
public class Ex14{
    /**
     * Calculating what choises will lead to amir victory in the coin game, and print the game with the total score.
     * The Time comlexity is O(n) - in the method thare are O(1) actions and O(n) in the for loop, the testaHead method is also O(1).
     * The Space complexity O(1) - the method recive an array so it dosen't responsible for the array space,  the testaHead method is also O(1).
     * @param arr The array which represent the coins in the game, should include at least 2 numbers.
     */
    public static void win(int [] arr){
        
        final int MAX_TURNS = arr.length / 2; // setting the number of turns to loop at. 
        int amirSum = 0;
        int tamarSum = 0;
        int currentCoin; // the current coin for each round that the players will choose.
        int startPos = 0;
        int endPos = arr.length - 1; // start and end positions in the array.

        for (int i = 1; i <= MAX_TURNS; i++){
            currentCoin = testaHaed(arr, startPos, endPos, amirSum); // setting amir choise by the testaHead method.
            System.out.println("Amir took " + currentCoin);
            amirSum += currentCoin;

            if (currentCoin == arr[startPos]) // advane to the next coin from start or end.
                startPos += 1;
            else
                endPos -= 1;

            currentCoin = Math.max(arr[startPos], arr[endPos]); // tamara will always take the higher option.
            System.out.println("Tamar took " + currentCoin);
            tamarSum += currentCoin;

            if (currentCoin == arr[startPos])
                startPos += 1;
            else
                endPos -= 1;
        }

        System.out.println("Final Score:\nAmir total " + amirSum + "\nTamar total " + tamarSum);
    }

    // this method will test the next moves to see which is the better option to take.
    // The comlexity is O(1)
    private static int testaHaed(int [] arr, int start, int end, int sum){
        int startNextMove;
        int endNextMove;

        if (start + 1 == end) // if its the last 2 coins, take the higher.
            return Math.max(arr[start], arr[end]);

        if (arr[start+1] >= arr[end]) // if choose the start get the higher from the available options.
            startNextMove = Math.max(arr[start+2], arr[end]);
        else
            startNextMove = Math.max(arr[start+1], arr[end-1]);
        
        if (arr[end-1] >= arr[start]) // if choose the end...
            endNextMove = Math.max(arr[start], arr[end-2]);
        else
            endNextMove = Math.max(arr[start+1], arr[end-1]);

        // finally return the higher from start or end by their total with the current score. 
        if ((arr[start] + startNextMove + sum) >= (arr[end] + endNextMove + sum)) 
            return arr[start];
        else
            return arr[end];
    }

    /**
     * Find the max triplet multiplication from array of numbers and print the triplet.
     * The Time complexity is O(n) - in the method thare are O(1) actions and O(n) in the for loop.
     * The Space complexity is O(1) - the method recive an array so it dosen't responsible for the array space.
     * @param arr The array that include the numbers to find the triplet from, should be at least 3 numbers. 
     * @return the max multiplication.
     */
    public static int findTriplet (int [] arr){
        int biggestNum1 = 0;
        int biggestNum2 = 0;
        int smallestNum1 = 0;
        int smallestNum2 = 0;
        int biggestNum3 = 0;
        
        for (int i = 0; i < arr.length; i++){

            if ((arr[i] > 0) && (arr[i] > biggestNum3)){  // check if the numbers are positive.
                if (arr[i] > biggestNum2) {
                    if (arr[i] > biggestNum1){
                        biggestNum3 = biggestNum2;
                        biggestNum2 = biggestNum1;
                        biggestNum1 = arr[i];
                    }
                    else {
                        biggestNum3 = biggestNum2;
                        biggestNum2 = arr[i];      // check if the current number is bigger then the biggest nembers so far
                    }                              // and replace them by order.
                }
                else
                    biggestNum3 = arr[i];
            }
            else if ((arr[i] < 0) && (arr[i] < smallestNum2)){  // check if the numbers are negitive.
                if (arr[i] < smallestNum1){
                    smallestNum2 = smallestNum1;
                    smallestNum1 = arr[i];
                }
                else
                    smallestNum2 = arr[i];
            }
        }
        if ((biggestNum1 * biggestNum2 * biggestNum3) >= (smallestNum1 * smallestNum2 * biggestNum1)){
            System.out.println(biggestNum1 + " " + biggestNum2 + " " + biggestNum3);
            return (biggestNum1 * biggestNum2 * biggestNum3);
        }
        else {
            System.out.println(smallestNum1 + " " + smallestNum2 + " " + biggestNum1);
            return (smallestNum1 * smallestNum2 * biggestNum1);
        }

    }

    /**
     * Check how many times a pattern can be placed in a string.
     * @param str The String to check the pattern on, should not be empty. 
     * @param pattern The pattern to find in the string.
     * @return the number of times the pattern was placed in the string.
     */
    public static int count (String str, String pattern){
        // call to the overloading method with starting values.
        return count(str, pattern, 0, 0, 0);
    }

    // overloading method to recive more values .
    private static int count (String str, String pattern, int strPos, int patternPos, int counter){
        
        if (patternPos == pattern.length()){ // all the pattern latters were found. 
            counter ++;
            return counter;
        }
        else if (strPos == str.length()) // end of the string
            return counter;
        else  // start of recursive.
            if (str.charAt(strPos) == pattern.charAt(patternPos)){ // if the chars matched try call with pattern index +1 and call with same index.
                return (count(str, pattern, strPos + 1, patternPos, counter) + count(str, pattern, strPos + 1, patternPos + 1, counter));
            }
            else
                return (count(str, pattern, strPos + 1, patternPos, counter));
    }
    
    /**
     * Check the shortest path for the prince to get to the boss in a matrix.
     * The prince can advance to - right,left, up or down if the next valuse is within the range of -2 to +1 from his position. 
     * @param drm The matrix which represent "the buildings", should include positive numbers and one -1 ( the boss ).
     * @param i The starting index in the 1st part of the matrix, should be within the array limits.
     * @param j The starting index in the 2nd part of the matrix, should be within the array[i] limits.
     * @return The shortest path to the boss, -1 if not exist.
     */
    public static int prince(int[][] drm, int i, int j){
        return prince(drm, i, j, 1);
    }
    // overloading method to recive more values .
    private static int prince(int[][] drm, int i, int j, int counter){
            
        int jStep = 1;
        int jBack = 1;
        int iStep = 1;
        int iBack = 1;  // Setting the possible steps to take.
        
        if (drm[i][j] == -1) // got to the boss.
            return counter;
      
        if (j == drm[i].length -1)
            jStep = 0;
        else if (j == 0)
            jBack = 0;
        if (i == drm.length -1)  // if the indexs are the last in the array, cant advance.
            iStep = 0;
        else if (i == 0)
            iBack = 0;
        
        if (i >= drm.length && j >= drm[i].length || (i < 0 || j < 0) || drm[i][j] == -5) // checking the limits of the matrix, 
            return -1;                                                                    // and if we been there.
            
        if (drm[i][j+jStep] == -1 || drm[i][j-jBack] == -1 || drm[i+iStep][j] == -1 || drm[i-iBack][j] == -1){ // if the next value is the boss
            counter++;
            return counter;
        }
        else { 
            counter++;
            int saveValue = drm[i][j];

            // check the next possible steps that within the allowed range.
            if (jStep != 0 && !(drm[i][j+jStep] >= drm[i][j] -2 && drm[i][j+jStep] <= drm[i][j] +1)){
                jStep = 0;
            }
            if  (jBack != 0 && !(drm[i][j-jBack] >= drm[i][j] -2 && drm[i][j-jBack] <= drm[i][j] +1)){
                jBack = 0;
            }
            if  (iStep != 0 && !(drm[i+iStep][j] >= drm[i][j] -2 && drm[i+iStep][j] <= drm[i][j] +1)){
                iStep = 0;
            }
            if  (iBack != 0 && !(drm[i-iBack][j] >= drm[i][j] -2 && drm[i-iBack][j] <= drm[i][j] +1)){
                iBack = 0;
            }
            
            drm[i][j] = -5; // mark the value that we were in.

            // start of recursive, using a method to check the min on every option.
            int minSteps = minFind(prince(drm, i, j+jStep, counter),
                                   prince(drm, i, j-jBack, counter),
                                   prince(drm, i+iStep, j, counter),
                                   prince(drm, i-iBack, j, counter));
            drm[i][j] = saveValue;  // set the value to what it was.
            return minSteps; // return the min.
        }
    }
    // a method to find the min from the givin options.
    private static int minFind(int opt1, int opt2, int opt3, int opt4){
        int min1;
        int min2;
        
        if (opt1 == -1)
            min1 = opt2;
        else if (opt2 == -1)
            min1 = opt1;  // if one of the option are -1 save the other.
        else
            min1 = Math.min(opt1, opt2);
        if (opt3 == -1)
            min2 = opt4;
        else if (opt4 == -1)
            min2 = opt3;
        else
            min2 = Math.min(opt3, opt4);
        if (min1 == -1)
            return min2;
        else if (min2 == -1)
            return min1;
        else
            return(Math.min(min1, min2));
    }
}