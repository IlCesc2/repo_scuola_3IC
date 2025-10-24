// NOTE: it is recommended to use this even if you don't understand the following code.

import java.util.*;
import java.io.*;
import java.lang.*;

public class triangles {

    public static void main(String[] args) throws FileNotFoundException, IOException {
        Locale.setDefault(Locale.US);
        InputStream fin = System.in;
        OutputStream fout = System.out;
        // uncomment the two following lines if you want to read/write from files
        // fin = new FileInputStream("input.txt");
        // fout = new FileOutputStream("output.txt");

        BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(fout));
        reader = new BufferedReader(new InputStreamReader(fin));
        scn = new StringTokenizer(reader.readLine());

        int N = Integer.parseInt(next());
        int Q = Integer.parseInt(next());

        int[] X = new int[Q], Y = new int[Q];
        for (int i = 0; i < Q; ++i) {
            X[i] = Integer.parseInt(next());
            Y[i] = Integer.parseInt(next());
        }

        System.out.println(N);
        System.out.println(Q);

        long[] ans = new long[Q];


        // INSERT YOUR CODE HERE


        for (int i = 0; i < Q; ++i) {
            writer.write(String.valueOf(ans[i]));
            writer.write(' ');
        }
        writer.write('\n');

        writer.flush();
    }

    static String next() throws IOException {
        while (!scn.hasMoreTokens()) scn = new StringTokenizer(reader.readLine());
        return scn.nextToken();
    }

    static BufferedReader reader;
    static StringTokenizer scn;
}
