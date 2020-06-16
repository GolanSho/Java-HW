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

    private static int count (String str, String pattern, int strPos, int patternPos, int count){
        
        int charMatched = 0;
        int patternPosAdd = 1;

        if (charMatched == pattern.length() -1){
            count += 1;
            return count;
        }
        else if (strPos == str.length() -1)
            return count;
        else
            if (str.charAt(strPos) == pattern.charAt(patternPos))
                charMatched += 1;
            if (patternPos == pattern.length() -1)
                patternPosAdd = 0;
            return (count(str, pattern, strPos + 1, patternPos, count) + count(str, pattern, strPos + 1, patternPos + patternPosAdd, count));
    }
    
    public static int prince(int[][] drm, int i, int j){
        return prince(drm, i, j, 0);
    }
    private static int prince(int[][] drm, int i, int j, int counter){

        if (i < 0 || i >= drm.length && j >= drm[i].length)
            return -1;
        else if (j >= drm[i].length)
            return prince(drm, i+1, 0, counter);
        else if (drm[i][j] == -1){
            counter++;
            return counter;
        }
        else
            if (j+1 < drm[i].length && ((drm[i][j] == drm[i][j+1]) || (drm[i][j] + 1 == drm[i][j+1]) || (drm[i][j] -1 == drm[i][j+1]) || (drm[i][j] -2 == drm[i][j+1]) || drm[i][j+1] == -1)){
                counter++;
                return prince(drm, i, j+1, counter);
            }
            else if  (i+1 < drm.length && ((drm[i][j] == drm[i+1][j]) || (drm[i][j] + 1 == drm[i+1][j]) || (drm[i][j] -1 == drm[i+1][j]) || (drm[i][j] -2 == drm[i+1][j]) || drm[i+1][j] == -1)){
                counter++;
                return prince(drm, i+1, j, counter);
            }
            else if  (j > 0 && ((drm[i][j] == drm[i][j-1]) || (drm[i][j] + 1 == drm[i][j-1]) || (drm[i][j] -1 == drm[i][j-1]) || (drm[i][j] -2 == drm[i][j-1]) || drm[i][j-1] == -1)){
                counter++;
                return prince(drm, i, j-1, counter);
            }
            else if  (i > 0 && ((drm[i][j] == drm[i-1][j]) || (drm[i][j] + 1 == drm[i-1][j]) || (drm[i][j] -1 == drm[i-1][j]) || (drm[i][j] -2 == drm[i-1][j]) || drm[i-1][j] == -1)){
                counter++;
                return prince(drm, i-1, j, counter);
            }
            else {
                counter++;
                return prince(drm, i, j+1, counter);
            }
    }

}