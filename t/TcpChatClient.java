import java.io.*;
import java.net.*;
import java.util.Arrays;

// 

public class TcpChatClient {
    public static void main(String[] args) {
        try (Socket socket = new Socket("localhost", 6969)) {
            System.out.println("Connesso al server di chat!");

            Writer writer = new Writer(socket);

            writer.start();

            Listener listener = new Listener(socket);

            // listener.start();

            while (socket.isConnected()) {

            }
            writer.interrupt();

        } catch (IOException e) {
            System.out.println("Errore: " + e.getMessage());
        }
    }
}

class Writer extends Thread {
    private Socket socket;

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
                PrintWriter out = new PrintWriter(socket.getOutputStream(), true)) {
            BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));

            Reader reader = new Reader(socket);
            reader.start();

            // pw
            while (!isInLobby) {
                // lobby
                String response = in.readLine();

                String[] parsedResponse = response.split(":");

                if (parsedResponse[0].equals("lobby_names")) { // SHOWS LOBBY NAMES
                    String[] lobbyNames = parsedResponse[1].split(",");

                    System.out.println("Lobby Names: " + Arrays.toString(lobbyNames).replaceAll("\\[|\\]", ""));

                    System.out.print("Insert Lobby name: ");

                } else { // IS LOBBY IN SERVER
                    String lobby = stdIn.readLine();
                    out.println(lobby);

                    if (parsedResponse[0].equals("403")) {
                        System.out.println(parsedResponse[1]);
                        socket.close();
                        interrupt();
                    } else if (parsedResponse[0].equals("104")) {
                        needsPassword = true;
                        hasGuessedPassword = false;
                        isInLobby = true;
                        System.out.println(parsedResponse[1]);

                    } else if (parsedResponse[0].equals("200")) {
                        isInLobby = true;
                        System.out.println(parsedResponse[1]);
                    } else {
                        System.out.println("Something went wrong");
                    }
                }
            }
            while (needsPassword) {
                System.out.print("Insert Password: ");
                String password = stdIn.readLine();
                out.println("password:" + password);
                String response = in.readLine();
                String[] parsedResponse = response.split(":");

                if (parsedResponse[0].equals("403")) {
                    System.out.println(parsedResponse[1]);
                    socket.close();
                    interrupt();
                } else if (parsedResponse[0].equals("200")) {
                    hasGuessedPassword = true;

                    System.out.println(parsedResponse[1]);
                } else {
                    System.out.println("Something went wrong");
                }
                needsPassword = false;
            }

            // login
            System.out.print("Insert Login: ");
            String login = stdIn.readLine();
            out.println(login);

            // messages
            String input;
            while ((input = stdIn.readLine()) != null) {
                if (socket.isClosed())
                    break;
                if (!input.trim().isEmpty()) {
                    // System.out.println(login + ":" + input);
                    out.println("nome:" + login + ",messaggio:" + input);
                }
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

class Reader extends Thread {
    Socket socket;
    boolean isAuthed = false;
    boolean isInLobby = false;
    boolean needsPassword = false;
    boolean hasGuessedPassword = true;

    Reader(Socket socket) {
        this.socket = socket;
    }

    @Override
    public void run() {
        try {
            BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));

            String response;
            while (socket.isConnected()) {
                response = in.readLine();
                String[] parsedResponse = response.split(":");

                if (!isInLobby) {
                    if (parsedResponse[0].equals("lobby_names")) { // SHOWS LOBBY NAMES
                        String[] lobbyNames = parsedResponse[1].split(",");

                        System.out.println("Lobby Names: " + Arrays.toString(lobbyNames).replaceAll("\\[|\\]", ""));

                        System.out.print("Insert Lobby name: ");

                    } else { // IS LOBBY IN SERVER
                        if (parsedResponse[0].equals("403")) {
                            System.out.println(parsedResponse[1]);
                            socket.close();
                            interrupt();
                        } else if (parsedResponse[0].equals("104")) {
                            needsPassword = true;
                            hasGuessedPassword = false;
                            isInLobby = true;
                            System.out.println(parsedResponse[1]);

                        } else if (parsedResponse[0].equals("200")) {
                            isInLobby = true;
                            System.out.println(parsedResponse[1]);
                        } else {
                            System.out.println("Something went wrong");
                        }
                    }

                } else if (!hasGuessedPassword) { // PASSWORD
                    if (parsedResponse[0].equals("403")) {
                        System.out.println(parsedResponse[1]);
                        socket.close();
                        interrupt();
                    } else if (parsedResponse[0].equals("200")) {
                        hasGuessedPassword = true;
                        System.out.println(parsedResponse[1]);
                    } else {
                        System.out.println("Something went wrong");
                    }
                } else if (!isAuthed) { // LOGIN AUTH
                    if (parsedResponse[0].equals("200")) {
                        isAuthed = true;
                        System.out.println(parsedResponse[1]);
                    } else if (parsedResponse[0].equals("403")) {
                        System.out.println(parsedResponse[1]);

                        socket.close();
                        interrupt();
                    }
                } else { // READING MESSAGES ARRIVING FROM SERVER

                    System.out.println("MESSAGE ARRIVED");
                    String sender = response.split(",")[0].split(":")[1];
                    String message = response.split(",")[1].split(":")[1];
                    System.out.println(sender + ": " + message);
                }

            }
            System.out.println("OUT OF THE SOCKET");
        } catch (Exception e) {
            // TODO: handle exception
        }
    }
}

class Listener extends Thread {
    Socket socket;

    Listener(Socket socket) {
        this.socket = socket;
    }

    @Override
    public void run() {
        try {
            BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));

            String r;
            while ((r = in.readLine()) != null) {
                System.out.println(r);
            }
        } catch (Exception e) {

        }
    }
}