import java.io.*;
import java.net.*;
import java.util.ArrayList;
import java.util.HashMap;

public class MultiThreadServer {
    public static void main(String[] args) {
        int PORT = 6969;
        HashMap<String, Socket> clients = new HashMap<>();

        try (ServerSocket serverSocket = new ServerSocket(PORT)) {
            System.out.println("Server di chat multi-thread in ascolto sulla porta " + PORT + "...");
            while (true) {
                Socket clientSocket = serverSocket.accept();


                System.out.println("Nuovo client connesso!");
                new ClientHandler(clientSocket, clients).start();
            }
        } catch (IOException e) {
            System.out.println("Errore: " + e.getMessage());
        }
    }
 
}

class ClientHandler extends Thread {
    private Socket clientSocket;
    private boolean isClientAuthed;
    private HashMap<String, Socket> clients;

    public ClientHandler(Socket socket, HashMap<String, Socket> clients) {
        this.clientSocket = socket;
        this.clients = clients;
    }

    public void run() {
        try (BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
                PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true)) {

            String inputLine;
            while ((inputLine = in.readLine()) != null) {
                if (!isClientAuthed) {
                    if (clients.get(inputLine) == null) {
                        isClientAuthed = true;
                        out.println("code:200");
                        clients.put(inputLine, clientSocket);
                        System.out.println(inputLine + "in");
                    } else {
                        out.println("code:403");
                    }
                } else {
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