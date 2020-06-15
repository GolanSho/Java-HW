public class Tester {
    public static void main(String args[]){
        int [] arr = new int[8];
        int [] triple = {-4, 1, -8, 9, 6};
        int [] triple2 = {2, 10, -5, 4, -6, 0, -3, 5};
        int[] coins1 = {16,23,30,13,14,21,19,15};
    //    for (int i = 0; i < arr.length; i++){
    //        arr[i] = (int)(Math.random()*100);
    //    }
        //Ex14.win(coins1);
        System.out.println(Ex14.findTriplet(triple2));
    }
}