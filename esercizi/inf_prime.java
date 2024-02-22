import java.util.Scanner;

public class inf_prime {
    public static void main(String[] args) {
        try (Scanner scan = new Scanner(System.in)) {
            int startNum;
            do {
                startNum = scan.nextInt();
            } while (startNum <0);

            long buff = 0;
            while(true) {
                
                for(int j = 2;  j < startNum/2; j++) {
                    if(startNum %j ==0) {
                        break;
                    }
                    buff = j;
                }
                if(buff == startNum/2 -1) {
                    System.out.println(startNum);
                }
                startNum++;
            }
        }    
    }
    
}
