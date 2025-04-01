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
            while (socket.isConnected()) {

            }

            writer.interrupt();
            System.out.println("out bro");
        } catch (IOException e) {
            System.out.println("Errore: " + e.getMessage());
        }
    }
}

class Writer extends Thread {
    private final Socket socket;

    Writer(Socket socket) {
        this.socket = socket;
    }

    @Override
    public void run() {
        try (
                BufferedReader stdIn = new BufferedReader(new InputStreamReader(System.in));
                PrintWriter out = new PrintWriter(socket.getOutputStream(), true)) {
            Reader reader = new Reader(socket);
            reader.start();

            // lobby
            String lobby = stdIn.readLine();
            out.println(lobby);

            // pw
            while (!reader.isInLobby) {
                if (reader.needsPassword) {
                    System.out.print("Insert Password: ");
                    String password = stdIn.readLine();
                    out.println(password);
                    reader.needsPassword = false;
                }

            }

            // login
            System.out.println("Insert Login");
            String login = stdIn.readLine();
            out.println(login);

            // messages
            String input;
            while ((input = stdIn.readLine()) != null) {
                if (socket.isClosed())
                    break;
                if (!input.trim().isEmpty()) {
                    System.out.println(login + ":" + input);
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
                    if (parsedResponse[0].equals("lobby_names")) {
                        String[] lobbyNames = parsedResponse[1].split(",");

                        System.out.println("Lobby Names: " + Arrays.toString(lobbyNames).replaceAll("\\[|\\]", ""));
                        System.out.println("Insert Lobby name");

                    } else {
                        if (parsedResponse[0].equals("403")) {
                            System.out.println(parsedResponse[1]);
                            socket.close();
                            interrupt();
                        } else if (parsedResponse[0].equals("104")) {
                            System.out.println("Inserire Password:");
                            needsPassword = true;
                        } else if (parsedResponse[0].equals("200")) {
                            isInLobby = true;
                            System.out.println(parsedResponse[1]);
                        } else {
                            System.out.println("Something went wrong");
                        }
                    }

                } else if (!isAuthed) {
                    if (parsedResponse[0].equals("200")) {
                        isAuthed = true;
                        System.out.println(parsedResponse[1]);
                        // System.out.println("Connection successful");
                    } else if (parsedResponse[0].equals("403")) {
                        System.out.println(parsedResponse[1]);

                        // System.out.println("Lobby Already has this name");
                        socket.close();
                        interrupt();
                    }
                } else {
                    String sender = response.split(",")[0].split(":")[1];
                    String message = response.split(",")[1].split(":")[1];
                    System.out.println(sender + ": " + message);
                }
            }
        } catch (Exception e) {
            // TODO: handle exception
        }
    }
}