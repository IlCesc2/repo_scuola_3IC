import java.io.*;
import java.net.*;

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
    Socket socket;

    Writer(Socket socket) {
        this.socket = socket;
    }

    @Override
    public void run() {
        try {
            BufferedReader stdIn = new BufferedReader(new InputStreamReader(System.in));
            PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
            String input = "";

            // send login
            System.out.println("Insert Login");
            String login = stdIn.readLine();
            out.println(login);

            Reader reader = new Reader(socket);
            reader.start();

            while ((input = stdIn.readLine())!= null) {
                out.println("nome:"+login+",messaggio:"+input);
            }
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

    }
}

class Reader extends Thread {
    Socket socket;
    boolean isAuthed = false;

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
                if(!isAuthed){
                    if(response.equals("200")){
                        isAuthed= true;
                        System.out.println("Connection successful");
                    } else if (response.equals("403")){
                        System.out.println("Server Already has this name");
                        socket.close();
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