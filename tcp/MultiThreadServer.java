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

                new ClientHandler(clientSocket, clients, lobbies).start();
            }
        } catch (IOException e) {
            System.out.println("Errore: " + e.getMessage());
        }
    }
    public static String[] lobbyList(ArrayList<Lobby> lobbies){
        ArrayList<String> lobbyNames = new ArrayList<>();
        for (Lobby lobby : lobbies) {
            lobbyNames.add(lobby.getName());
        }
        return (String[]) lobbyNames.toArray();
    }
}

class ClientHandler extends Thread {
    private Socket clientSocket;
    private boolean hasClientSelectedLobby;
    private boolean isClientAuthed;
    private HashMap<String, Socket> clients;
    private ArrayList<Lobby> lobbies;
    private Lobby lobbySelected;

    public ClientHandler(Socket socket, HashMap<String, Socket> clients, ArrayList<Lobby> lobbies) {
        this.clientSocket = socket;
        this.clients = clients;
        this.lobbies= lobbies;
    }

    public void run() {
        try (BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
                PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true)) {
            
                    String lobbieNames="";
            for (Lobby lobby : lobbies) {
                lobbieNames += lobby.getName()+",";

            
            }
            out.println(lobbieNames);
            String inputLine;
            while ((inputLine = in.readLine()) != null) {
                if(!hasClientSelectedLobby){
                    int i =0;
                    for (Lobby lobby : lobbies) {
                        if (lobby.getName().equals(inputLine)) {
                            lobbySelected = lobby;
                            lobbySelected.addClient(clientSocket, inputLine);
                            hasClientSelectedLobby= true;
                            out.println("200");
                            break;
                        } 
                        i++;
                    }
                    if (i< lobbies.size()){
                        out.println("403");
                    }
                } else if (!isClientAuthed) {
                    if (clients.get(inputLine) == null) {
                        isClientAuthed = true;
                        out.println("200");
                        clients.put(inputLine, clientSocket);
                        System.out.println(inputLine + "in");
                    } else {
                        out.println("403");
                    }
                } else {
                    //System.out.println("Client: " + inputLine);
                    //out.println("Echo: " + inputLine);

                    // TODO: Broadcast message to all clients 
                    String sender = inputLine.split(",")[0].split(":")[1];
                    String message = inputLine.split(",")[1].split(":")[1];
                    System.out.println(sender+":"+message);
                    for (String client : clients.keySet()) {
                        if (!client.equals(sender)) {
                            String msg = "nome:" + sender + ",messaggio:" + message;
                            PrintWriter clientOut = new PrintWriter(clients.get(client).getOutputStream(), true);
                            
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