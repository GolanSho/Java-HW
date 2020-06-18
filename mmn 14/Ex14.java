public class Ex14{
    public static void win(int [] arr){
        final int MAX_TURNS = arr.length / 2;
        int amirSum = 0;
        int tamarSum = 0;
        int currentCoin;
        int startPos = 0;
        int endPos = arr.length - 1;

        for (int i = 1; i <= MAX_TURNS; i++){
            currentCoin = testaHaed(arr, startPos, endPos, amirSum);
            System.out.println("Amir took " + currentCoin);
            amirSum += currentCoin;

            if (currentCoin == arr[startPos])
                startPos += 1;
            else
                endPos -= 1;

            currentCoin = Math.max(arr[startPos], arr[endPos]);
            System.out.println("Tamar took " + currentCoin);
            tamarSum += currentCoin;

            if (currentCoin == arr[startPos])
                startPos += 1;
            else
                endPos -= 1;
        }

        System.out.println("Final Score:\nAmir total " + amirSum + "\nTamar total " + tamarSum);
    }


    private static int testaHaed(int [] arr, int start, int end, int sum){
        int startNextMove;
        int endNextMove;

        if (start + 1 == end)
            return Math.max(arr[start], arr[end]);

        if (arr[start+1] >= arr[end])
            startNextMove = Math.max(arr[start+2], arr[end]);
        else
            startNextMove = Math.max(arr[start+1], arr[end-1]);
        
        if (arr[end-1] >= arr[start])
            endNextMove = Math.max(arr[start], arr[end-2]);
        else
            endNextMove = Math.max(arr[start+1], arr[end-1]);    
        
        if ((arr[start] + startNextMove + sum) >= (arr[end] + endNextMove + sum))
            return arr[start];
        else
            return arr[end];
    }

    public static int findTriplet (int [] arr){
        int biggestNum1 = 0;
        int biggestNum2 = 0;
        int smallestNum1 = 0;
        int smallestNum2 = 0;
        int biggestNum3 = 0;
        
        for (int i = 0; i < arr.length; i++){

            if ((arr[i] > 0) && (arr[i] > biggestNum3)){
                if (arr[i] > biggestNum2) {
                    if (arr[i] > biggestNum1){
                        biggestNum3 = biggestNum2;
                        biggestNum2 = biggestNum1;
                        biggestNum1 = arr[i];
                    }
                    else {
                        biggestNum3 = biggestNum2;
                        biggestNum2 = arr[i];
                    }
                }
                else
                    biggestNum3 = arr[i];
            }
            else if ((arr[i] < 0) && (arr[i] < smallestNum2)){
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

    public static int count (String str, String pattern){
        
        return count(str, pattern, 0, 0, 0);
    }

    private static int count (String str, String pattern, int strPos, int patternPos, int counter){
        
        if (patternPos == pattern.length()){
            counter ++;
            return counter;
        }
        else if (strPos == str.length())
            return counter;
        else
            if (str.charAt(strPos) == pattern.charAt(patternPos)){
                return (count(str, pattern, strPos + 1, patternPos, counter) + count(str, pattern, strPos + 1, patternPos + 1, counter));
            }
            else
                return (count(str, pattern, strPos + 1, patternPos, counter));
    }
    
    public static int prince(int[][] drm, int i, int j){
        return prince(drm, i, j, 1);
    }
    private static int prince(int[][] drm, int i, int j, int counter){
            
        int jStep = 1;
        int jBack = 1;
        int iStep = 1;
        int iBack = 1;
        
        if (drm[i][j] == -1)
            return counter;
      
        if (j == drm[i].length -1)
            jStep = 0;
        else if (j == 0)
            jBack = 0;
        if (i == drm.length -1)
            iStep = 0;
        else if (i == 0)
            iBack = 0;
        
        if (i >= drm.length && j >= drm[i].length || (i < 0 || j < 0) || drm[i][j] == -5)
            return -1;
            
        if (drm[i][j+jStep] == -1 || drm[i][j-jBack] == -1 || drm[i+iStep][j] == -1 || drm[i-iBack][j] == -1){
            counter++;
            return counter;
        }
        else {
            counter++;
            int saveValue = drm[i][j];
            
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
            
            drm[i][j] = -5;
            int minSteps = minFind(prince(drm, i, j+jStep, counter),
                                   prince(drm, i, j-jBack, counter),
                                   prince(drm, i+iStep, j, counter),
                                   prince(drm, i-iBack, j, counter));
            drm[i][j] = saveValue;
            return minSteps;
        }
    }
    private static int minFind(int opt1, int opt2, int opt3, int opt4){
        int min1;
        int min2;
        
        if (opt1 == -1)
            min1 = opt2;
        else if (opt2 == -1)
            min1 = opt1;
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