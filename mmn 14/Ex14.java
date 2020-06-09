public class Ex14{
    public static void win(int [] arr){
        final int MAX_TURNS = arr.length / 2;
        int amirSum = 0;
        int tamarSum = 0;
        int currentCoin;
        int startPos = 0;
        int endPos = arr.length - 1;

        if (arr.length == 2){
            if (arr[startPos] >= arr[endPos]){
                amirSum = arr[startPos];
                tamarSum = arr[endPos];
                System.out.println("Amir took " + arr[startPos]);
                System.out.println("Tamar took " + arr[endPos]);
            }
            else{
                amirSum = arr[endPos];
                tamarSum = arr[startPos];
                System.out.println("Amir took " + arr[endPos]);
                System.out.println("Tamar took " + arr[startPos]);
            }
        }
        else {
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
        }

        System.out.println("Final Score:\nAmir total " + amirSum + "\nTamar total " + tamarSum);
    }


    private static int testaHaed(int [] arr, int start, int end, int sum){
        int startNextMove;
        int endNextMove;

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
}