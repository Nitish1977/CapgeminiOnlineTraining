package Arrays;

public class TwoDArray {
    public static void main(String[] args) {

        //declaration and initialization
        int[][] arr = {   // internally it is storing array in 1-D
                {12,34,322,11,20},
                {43,22,16,54,33},
                {55,23,76,88,66}
        };

        // Traversal;

        for(int i=0; i<arr.length;i++){
            for(int j=0; j<arr.length; j++){
                System.out.println(arr[i][j]);
            }
        }
    }
}
