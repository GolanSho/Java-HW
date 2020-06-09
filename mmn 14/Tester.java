public class Tester {
    public static void main(String args[]){
        int [] arr = new int[8];
        
        for (int i = 0; i < arr.length; i++){
            arr[i] = (int)(Math.random()*100);
        }
        Ex14.win(arr);
    }
}