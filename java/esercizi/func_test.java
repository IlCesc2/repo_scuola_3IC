public class func_test {
    public static void main(String[] args) {
        int[] arr = {1,2,3,4,5,6,7,8,9,10};

        System.out.println("Prima: ");
        for (int i : arr) {
            System.out.println(i);
        }
        reset(arr);
        System.out.println("Dopo: ");
        for (int i : arr) {
            System.out.println(i);
        }
        int inp = 10;
        System.out.println("Prima: "+ inp);
        reset(inp);
        System.out.println("Dopo: "+ inp);
    }
    public static void reset(int[] arr) {
        for (int i = 0; i < arr.length; i++) {
            arr[i] =0;
        }
    }
    public static void reset(int input) {
        input =0;
    }
}
