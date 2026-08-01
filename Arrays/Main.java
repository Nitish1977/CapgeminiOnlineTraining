package Arrays;

public class Main {

    // 1-D Arrays.
    // Arrays are a non-primitive data type
    // fixed in size
    // homogeneous
    // continuous memory allocation
    // starting index is 0.
    // last index = arr.length-1;

    public static void main(String[] args) {

        // declaration and initialization or creation of an array
        int[] arr = {1,2,3,4,5,3,2};
        int arr2[] = {2,2,4,5,6};


        //access an element from array
        System.out.println(arr[3]);

        //traversing array:
        System.out.println("=== For Loop ===");
        for(int i=0; i<arr.length; i++){
            System.out.println(arr[i]);
        }

        System.out.println("=== For Each Loop ===");
        for(int num: arr2){
            System.out.println(num);
        }



    }
}
