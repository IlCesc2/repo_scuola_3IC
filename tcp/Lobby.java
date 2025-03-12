import java.net.Socket;
import java.util.HashMap;

public class Lobby {
    private String name;
    private String password;
    private HashMap<String, Socket> clients = new HashMap<>();

    public Lobby(String name, String password) {
        this.name = name;
        this.password = password;
    }
    public Lobby(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
    public String getPassword() {
        return password;
    }
    public HashMap<String, Socket> getClients() {
        return clients;
    }
    public void addClient(Socket socket, String name){
        this.clients.put(name, socket);
    }
    
    

}

