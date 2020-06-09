public class Tester {
    public static void main(String args[]){
        int [] arr = new int[8];
        int[] coins1 = {16,23,30,13,14,21,19,15};
        for (int i = 0; i < arr.length; i++){
            arr[i] = (int)(Math.random()*100);
        }
        Ex14.win(coins1);
    }
}