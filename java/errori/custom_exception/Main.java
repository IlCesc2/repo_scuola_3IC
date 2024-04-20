package custom_exception;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.UUID;

public class Main {
    static String PATH = "custom_exception\\zamdes.csv";
    static int MAX_LEN = User.class.getDeclaredFields().length;

    public static void main(String[] args) {
        int i = 0;
        File file = new File(PATH);

        Scanner fileReader = null;
        ArrayList<User> users = new ArrayList<User>();

        try {
            fileReader = new Scanner(file);

            while (fileReader.hasNextLine()) {
                if (i ==0) {
                    i++;
                    continue;
                }
                User user = reading(file, fileReader.nextLine(), i);
                users.add(user);
                i++;
            }
        } catch (CException e) {
            System.out.println(e.getMessage());
        } catch (FileNotFoundException e) {
            System.out.println("File non trovato");
        } finally {
            fileReader.close();
        }
        for (User user : users) {
            System.out.println(user.toString());
        }
    }

    public static User reading(File file, String currentLine, int i) throws CException, FileNotFoundException {
        try {

            String[] splitRow = currentLine.split(";");
            if (splitRow.length < MAX_LEN)
                throw new CException("Missing parameters");

            User user = new User(splitRow[0], splitRow[1], splitRow[2], splitRow[3], splitRow[4], splitRow[5],
                    splitRow[6], splitRow[7]);
            return user;
        } catch (CException e) {
            try {
                ArrayList<String> splitRow = new ArrayList<>(Arrays.asList(currentLine.split(";")));

                String id = "Aiuto!";
                String oneTimePW = genOneTimePW();

                splitRow.add(1, id);
                splitRow.add(2, oneTimePW);

                List<String> lines = Files.readAllLines(file.toPath());
                String out = splitRow.toString().replaceAll("[\\[\\]]", "").replaceAll(",", ";");

                lines.set(i, out);
                Files.write(file.toPath(), lines);
                User user = new User(splitRow.get(0), splitRow.get(1), splitRow.get(2), splitRow.get(3),
                        splitRow.get(4), splitRow.get(5),
                        splitRow.get(6), splitRow.get(7));
                return user;
            } catch (IOException err) {
                System.out.println("Error when trying to modify csv");
            }
        }
        return null;

    }

    public static String genOneTimePW() {
        UUID uuid = UUID.randomUUID();
        return uuid.toString().substring(0, 6);
    }
}
