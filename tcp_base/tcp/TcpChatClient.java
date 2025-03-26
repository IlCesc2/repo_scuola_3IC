import java.io.*;
import java.net.*;

public class TcpChatClient {
    public static void main(String[] args) {
        try (Socket socket = new Socket("localhost", 6969)) {
            System.out.println("Connesso al server di chat!");
            ChatApp chatApp = new ChatApp(socket);

            new Writer(socket, chatApp);

        } catch (IOException e) {
            System.out.println("Errore: " + e.getMessage());
        }
    }
}

class Writer extends Thread {
    Socket socket;
    ChatApp chatApp;

    Writer(Socket socket, ChatApp chatApp) {
        this.socket = socket;
        this.chatApp= chatApp;
    }

    @Override
    public void run() {
        try {
            BufferedReader stdIn = new BufferedReader(new InputStreamReader(System.in));
            PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
            String input = "";

            // send login
            //System.out.println("Insert Login");
            String login = stdIn.readLine();
            out.println(login);
            chatApp.setLogin(login);

            Reader reader = new Reader(socket, chatApp);
            reader.start();

            /*
              while ((input = stdIn.readLine()) != null) {
                out.println("nome:" + login + ",messaggio:" + input);
            }
            */

          
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

    }

}

class Reader extends Thread {
    Socket socket;
    boolean isAuthed = false;
    ChatApp chatApp;

    Reader(Socket socket, ChatApp chat) {
        this.socket = socket;
        this.chatApp = chat;
    }

    @Override
    public void run() {
        try {
            BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            String response;
            while (socket.isConnected()) {
                response = in.readLine();
                if (!isAuthed) {
                    String code = response.split(":")[1];
                    if (code.equals("200")) {
                        isAuthed = true;
                        System.out.println("Connection successful");
                    } else if (code.equals("403")) {
                        System.out.println("Server Already has this name");
                        socket.close();
                    }
                } else {
                    String sender = response.split(",")[0].split(":")[1];
                    String message = response.split(",")[1].split(":")[1];
                    chatApp.receiveMessage(sender + ": " + message);
                    // System.out.println(sender + ": " + message);
                }
            }
        } catch (Exception e) {
            // TODO: handle exception
        }
    }
}