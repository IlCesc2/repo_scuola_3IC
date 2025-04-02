import java.io.*;
import java.net.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

public class MultiThreadServer {
    public static void main(String[] args) {
        int PORT = 6969;
        ArrayList<Lobby> lobbies = new ArrayList<>();
        ArrayList<Socket> r = new ArrayList<>();
        lobbies.add(new Lobby("alpha", "1234"));
        lobbies.add(new Lobby("beta"));

        try (ServerSocket serverSocket = new ServerSocket(PORT)) {
            System.out.println("Server di chat multi-thread in ascolto sulla porta " + PORT + "...");
            while (true) {
                Socket clientSocket = serverSocket.accept();
                System.out.println("Nuovo client connesso!");

                new ClientHandler(clientSocket, lobbies, r).start();
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
    static String LOG_FILE_PATH = "log.txt";

    private ArrayList<Lobby> lobbies;
    private Socket clientSocket;
    private Lobby lobbySelected;
    private static ArrayList<Socket> r;

    private boolean hasClientSelectedLobby;
    private boolean isClientAuthed;
    private boolean hasGuessedPassword = true;

    public ClientHandler(Socket socket, ArrayList<Lobby> lobbies, ArrayList<Socket> r) {
        this.clientSocket = socket;
        this.lobbies = lobbies;
        this.r = r;
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
                            break;
                        }
                        i++;
                    }

                    if (i == lobbies.size()) {
                        out.println("403: lobby non esistente");
                    } else if (lobbySelected.hasPassword()) {
                        
                        out.println("104: lobby disponibile con password");
                        hasGuessedPassword=false;
                    } else {
                        System.out.println(lobbySelected.hasPassword());
                        out.println("200: lobby disponibile");
                    }

                }  else if(!hasGuessedPassword){
                    String pw = inputLine.split(":")[1];
                     if (pw.equals(lobbySelected.getPassword())) {
                        
                        out.println("200: password corretta");
                        hasGuessedPassword=true;
                    } else {
                        out.println("403: password non corretta");
                    }
                } else if (!isClientAuthed) {
                    // LOGIN SELECTION
                    if (lobbySelected.getClients().get(inputLine) == null) {
                        isClientAuthed = true;
                        out.println("200: nome disponibile, benvenuto nella lobby");

                        lobbySelected.addClient(clientSocket, inputLine);
                        r.add(clientSocket);
                        System.out.println(inputLine + " entered lobby " + lobbySelected.getName());
                    } else {
                        out.println("403: nome non disponibile");
                    }
                } else {
                    // SENDING MESSAGES

                    String sender = inputLine.split(",")[0].split(":")[1];
                    String message = inputLine.split(",")[1].split(":")[1];
                    System.out.println(sender + ":" + message);
                    String msg = "nome:" + sender + ",messaggio:" + message;
                    writeToLog("From lobby " + lobbySelected.getName() +" "+ msg);

                    for (Socket client : r) {
                        System.out.println("C: "+ client +", S: "+ sender);
                        PrintWriter p = new PrintWriter(client.getOutputStream(), true);
                        p.println(message);
                    }
                }

            }
        } catch (IOException e) {
            System.out.println("Errore di connessione con il client: " + e.getMessage());
        }
    }

    public static void writeToLog(String message) {
        try (PrintWriter writer = new PrintWriter(new FileWriter(LOG_FILE_PATH, true))) {

            LocalDateTime now = LocalDateTime.now();
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
            String timestamp = now.format(formatter);

            writer.println(timestamp + " - " + message);
        } catch (IOException e) {
            System.err.println("Error writing to log file: " + e.getMessage());
        }
    }
}