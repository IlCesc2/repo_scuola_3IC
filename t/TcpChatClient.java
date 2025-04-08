import java.io.*;
import java.net.*;
import java.util.Arrays;

public class TcpChatClient {
    public static void main(String[] args) {
        try (Socket socket = new Socket("localhost", 6969)) {
            System.out.println("Connesso al server di chat!");

            Writer writer = new Writer(socket);
            writer.start();

            while (socket.isConnected() && writer.isAlive()) {
                Thread.sleep(100); // Keeps main thread alive
            }

        } catch (Exception e) {
            System.out.println("Errore: " + e.getMessage());
        }
    }
}

class Writer extends Thread {
    private final Socket socket;

    boolean isAuthed = false;
    boolean isInLobby = false;
    boolean needsPassword = false;
    boolean hasGuessedPassword = true;

    Writer(Socket socket) {
        this.socket = socket;
    }

    @Override
    public void run() {
        try (
            BufferedReader stdIn = new BufferedReader(new InputStreamReader(System.in));
            PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
            BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()))
        ) {
            // LOBBY
            while (!isInLobby) {
                String response = in.readLine();
                if (response == null) break;

                String[] parsedResponse = response.split(":");

                if (parsedResponse[0].equals("lobby_names")) {
                    String[] lobbyNames = parsedResponse[1].split(",");
                    System.out.println("Lobby Names: " + Arrays.toString(lobbyNames).replaceAll("[\\[\\]]", ""));
                    System.out.print("Insert Lobby name: ");
                    String lobby = stdIn.readLine();
                    out.println(lobby);

                } else if (parsedResponse[0].equals("403")) {
                    System.out.println(parsedResponse[1]);
                    // socket.close();
                    // interrupt();
                    // return;

                } else if (parsedResponse[0].equals("104")) {
                    needsPassword = true;
                    hasGuessedPassword = false;
                    isInLobby = true;
                    System.out.println(parsedResponse[1]);

                } else if (parsedResponse[0].equals("200")) {
                    isInLobby = true;
                    System.out.println(parsedResponse[1]);

                } else {
                    System.out.println("Unknown lobby response: " + response);
                }
            }

            // PASSWORD
            while (needsPassword && !hasGuessedPassword) {
                System.out.print("Insert Password: ");
                String password = stdIn.readLine();
                out.println("password:" + password);

                String response = in.readLine();
                if (response == null) break;

                String[] parsedResponse = response.split(":");
                if (parsedResponse[0].equals("403")) {
                    System.out.println(parsedResponse[1]);
                    // socket.close();
                    // interrupt();
                    // return;
                } else if (parsedResponse[0].equals("200")) {
                    hasGuessedPassword = true;
                    System.out.println(parsedResponse[1]);
                } else {
                    System.out.println("Unexpected password response: " + response);
                }

                needsPassword = false;
            }

            // LOGIN 
            System.out.print("Insert Login: ");
            String login = stdIn.readLine();
            out.println(login);

            while (!isAuthed) {
                String response = in.readLine();
                if (response == null) break;

                String[] parsedResponse = response.split(":");
                if (parsedResponse[0].equals("200")) {
                    isAuthed = true;
                    System.out.println(parsedResponse[1]);
                } else if (parsedResponse[0].equals("403")) {
                    System.out.println(parsedResponse[1]);
                    // socket.close();
                    // interrupt();
                    // return;
                } else {
                    System.out.println("Login error: " + response);
                }
            }

            // MESSAGE SENDING
            Reader reader = new Reader(socket);
            reader.start();

            String input;
            while ((input = stdIn.readLine()) != null) {
                if (socket.isClosed()) break;
                if (!input.trim().isEmpty()) {
                    out.println("nome:" + login + ",messaggio:" + input);
                }
            }

        } catch (IOException e) {
            System.out.println("Connection error: " + e.getMessage());
        }
    }
}

class Reader extends Thread {
    private final Socket socket;

    Reader(Socket socket) {
        this.socket = socket;
    }

    @Override
    public void run() {
        try (
            BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()))
        ) {
            String response;
            while ((response = in.readLine()) != null) {
                System.out.println("Response: "+ response);
                if (response.contains(",") && response.contains("nome:") && response.contains("messaggio:")) {
                    try {
                        String[] parts = response.split(",");
                        String sender = parts[0].split(":")[1];
                        String message = parts[1].split(":")[1];
                        System.out.println(sender + ": " + message);
                    } catch (Exception e) {
                        System.out.println("Something wrong happened " + response);
                    }
                } else {
                    System.out.println("Server: " + response);
                }
            }
        } catch (IOException e) {
            System.out.println("Disconnected from server.");
        }
    }
}
