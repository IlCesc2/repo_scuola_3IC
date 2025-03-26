import java.io.*;
import java.net.*;
import java.util.ArrayList;
import java.util.HashMap;

public class MultiThreadServer {
    public static void main(String[] args) {
        int PORT = 6969;
        HashMap<String, Socket> clients = new HashMap<>();
        ArrayList<Lobby> lobbies = new ArrayList<>();
        lobbies.add(new Lobby("a"));
        lobbies.add(new Lobby("b"));
        lobbies.add(new Lobby("c"));

        try (ServerSocket serverSocket = new ServerSocket(PORT)) {
            System.out.println("Server di chat multi-thread in ascolto sulla porta " + PORT + "...");
            while (true) {
                Socket clientSocket = serverSocket.accept();
                System.out.println("Nuovo client connesso!");

                new ClientHandler(clientSocket, lobbies).start();
            }
        } catch (IOException e) {
            System.out.println("Errore: " + e.getMessage());
        }
    }

    public static String[] lobbyList(ArrayList<Lobby> lobbies) {
        ArrayList<String> lobbyNames = new ArrayList<>();
        for (Lobby lobby : lobbies) {
            lobbyNames.add(lobby.getName());
        }
        return (String[]) lobbyNames.toArray();
    }
}

class ClientHandler extends Thread {
    private ArrayList<Lobby> lobbies;
    private Socket clientSocket;
    private Lobby lobbySelected;

    private boolean hasClientSelectedLobby;
    private boolean isClientAuthed;

    public ClientHandler(Socket socket,  ArrayList<Lobby> lobbies) {
        this.clientSocket = socket;
        this.lobbies = lobbies;
    }

    public void run() {
        try (BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
                PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true)) {

            String lobbyNames = "lobby_names:";
            for (Lobby lobby : lobbies) {
                lobbyNames += lobby.getName() + ",";
            }
            out.println(lobbyNames);
            String inputLine;
            while ((inputLine = in.readLine()) != null) {
                if (!hasClientSelectedLobby) {
                    // LOBBY SELECTION
                    int i = 0;
                    for (Lobby lobby : lobbies) {
                        if (lobby.getName().equals(inputLine)) {
                            lobbySelected = lobby;
                            
                            hasClientSelectedLobby = true;
                            out.println("code:200");
                            break;
                        }
                        i++;
                    }
                    
                    if (i < lobbies.size()) {
                        System.out.println(i+" bro");
                        out.println("code:403");
                    }
                } else if (!isClientAuthed) {
                    // LOGIN SELECTION
                    System.out.println("BRO" + inputLine);
                    if (lobbySelected.getClients().get(inputLine) == null) {
                        isClientAuthed = true;
                        out.println("code:200");

                        lobbySelected.addClient(clientSocket, inputLine);
                        System.out.println(inputLine + " entered lobby "+ lobbySelected.getName());
                    } else {
                        out.println("code:403");
                    }
                } else {
                    // SENDING MESSAGES

                    String sender = inputLine.split(",")[0].split(":")[1];
                    String message = inputLine.split(",")[1].split(":")[1];
                    System.out.println(sender + ":" + message);
                    
                    for (String client : lobbySelected.getClients().keySet()) {
                        System.out.println(client);
                        if (!client.equals(sender)) {
                            String msg = "nome:" + sender + ",messaggio:" + message;
                            System.out.println(msg);
                            PrintWriter clientOut = new PrintWriter(lobbySelected.getClients().get(client).getOutputStream(), true);

                            clientOut.println(msg);
                        }
                    }
                }

            }
        } catch (IOException e) {
            System.out.println("Errore di connessione con il client: " + e.getMessage());
        }
    }
}