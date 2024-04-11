package custom_exception;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.Scanner;
import java.util.UUID;


public class Main {
    static String PATH = "custom_exception\\zamdes.csv";

    public static void main(String[] args) {
        try {
            reading();
        } catch (CException e) {
            System.out.println(e.getMessage());
        } catch (FileNotFoundException e) {
            System.out.println("File non trovato");
        }
    }

    public static void reading() throws CException, FileNotFoundException {
        File file = new File(PATH);
        Scanner fileReader = new Scanner(file);
        int MAX_LEN = User.class.getDeclaredFields().length;
        System.out.println(MAX_LEN);
        ArrayList<User> users = new ArrayList<User>();
        int i = 0;

        try {
            while (fileReader.hasNextLine()) {
                String row = fileReader.nextLine();

                String[] splitRow = row.split(";");
                if (splitRow.length < MAX_LEN)
                    throw new CException("Missing parameters");

                User user = new User(splitRow[0], splitRow[1], splitRow[2], splitRow[3], splitRow[4], splitRow[5],
                        splitRow[6], splitRow[7]);

                users.add(user);
                i++;
            }

        } catch (CException e) {
            try {
                String line = Files.readAllLines(Paths.get(PATH)).get(i);

                ArrayList<String> splitRow = new ArrayList<>(Arrays.asList(line.split(";")));

                String id = genRandId();
                String oneTimePW = genOneTimePW();

                splitRow.add(1, id);
                splitRow.add(2, oneTimePW);

                List<String> lines = Files.readAllLines(file.toPath());
                String out = splitRow.toString().replaceAll("[\\[\\]]", "").replaceAll(",", ";");

                lines.set(i, out);
                Files.write(file.toPath(), lines);

            } catch (IOException err) {
                System.out.println("Error when trying to modify csv");
            }
        } finally {
            fileReader.close();
        }
        for (User user : users) {
            System.out.println(user.toString());
        }
    }

    public static String genRandId() {
        Random rand = new Random();
        int int_random = rand.nextInt(9000);
        return int_random + "";

    }

    public static String genOneTimePW() {
        UUID uuid = UUID.randomUUID();
        return uuid.toString();
    }
}
