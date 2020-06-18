public class Tester {
    public static void main(String args[]){
    //    int [] arr = new int[8];
    //    int [] triple = {-4, 1, -8, 9, 6};
    //    int [] triple2 = {2, 10, -5, 4, -6, 0, -3, 5};
    //    int[] coins1 = {16,23,30,13,14,21,19,15};
    //    for (int i = 0; i < arr.length; i++){
    //        arr[i] = (int)(Math.random()*100);
    //    }
    //    Ex14.win(coins1);
        
    //    System.out.println(Ex14.findTriplet(triple2));

    //    System.out.println(Ex14.count("subsequence", "sue"));

        int[][] drm = { {2,0,1,2,3},
                        {2,3,5,5,4},
                        {8,-1,6,8,7},
                        {3,4,7,2,4},
                        {2,4,3,1,2}};
        for (int i = 0 ; i < 5 ; i++){
            for (int j = 0; j < 5; j++){
                System.out.print(drm[i][j]);
            }
            System.out.println("");
        }
        
        System.out.println(Ex14.prince(drm, 0, 0));
    }
}