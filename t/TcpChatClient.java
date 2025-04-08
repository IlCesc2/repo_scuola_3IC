import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.net.*;
import java.util.Arrays;

public class TcpChatClient {
    public static void main(String[] args) {
        try (Socket socket = new Socket("localhost", 6969)) {
            System.out.println("Connesso al server di chat!");

            ChatApp chatApp = new ChatApp();

            Writer writer = new Writer(socket, chatApp);
            writer.start();

            while (socket.isConnected() && writer.isAlive()) {   }
            chatApp.sendMessage("Chat is Closed :(");
            

        } catch (Exception e) {
            System.out.println("Errore: " + e.getMessage());
        }
    }
}

class Writer extends Thread {
    private Socket socket;
    private ChatApp chatApp;

    boolean isAuthed = false;
    boolean isInLobby = false;
    boolean needsPassword = false;
    boolean hasGuessedPassword = true;
    String login;

    Writer(Socket socket, ChatApp chatApp) {
        this.socket = socket;
        this.chatApp= chatApp;
    }

    @Override
    public void run() {
        try (
            BufferedReader stdIn = new BufferedReader(new InputStreamReader(System.in));
            PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
            BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()))
        ) {

            chatApp.sendButton.addActionListener(new ActionListener() {
                
                @Override
                public void actionPerformed(ActionEvent e) {
                    String message = chatApp.inputField.getText().trim();

                    if(message.isEmpty()) return;
                
                    if(!isInLobby){
                        out.println(message);
                    } else if(needsPassword && !hasGuessedPassword){
                        out.println("password:" + message);
                    } else if(!isAuthed){
                        login= message;
                        out.println(message);
                    } else{
                        out.println("nome:" + login + ",messaggio:" + message);
                        chatApp.sendMessage("You: " + chatApp.inputField.getText().trim());
                    }

                }
            });
            
            // LOBBY
            while (!isInLobby) {
                String response = in.readLine();
                if (response == null) break;

                String[] parsedResponse = response.split(":");
                
                if (parsedResponse[0].equals("lobby_names")) {
                    String[] lobbyNames = parsedResponse[1].split(",");
                    chatApp.sendMessage("Lobby Names: " + Arrays.toString(lobbyNames).replaceAll("[\\[\\]]", ""));
                } else if (parsedResponse[0].equals("403")) {
                    chatApp.sendMessage(parsedResponse[1]);
                    // socket.close();
                    // interrupt();
                    // return;

                } else if (parsedResponse[0].equals("104")) {
                    needsPassword = true;
                    hasGuessedPassword = false;
                    isInLobby = true;
                    chatApp.sendMessage(parsedResponse[1]);

                } else if (parsedResponse[0].equals("200")) {
                    isInLobby = true;
                    chatApp.sendMessage(parsedResponse[1]);

                } else{

                    chatApp.sendMessage("Insert Lobby name: ");
                }

            }

            // PASSWORD
            while (needsPassword && !hasGuessedPassword) {
                 chatApp.sendMessage("Insert Password: ");


                String response = in.readLine();
                if (response == null) break;

                String[] parsedResponse = response.split(":");
                if (parsedResponse[0].equals("403")) {
                    chatApp.sendMessage(parsedResponse[1]);
                    // socket.close();
                    // interrupt();
                    // return;
                } else if (parsedResponse[0].equals("200")) {
                    hasGuessedPassword = true;
                    needsPassword = false;
                    chatApp.sendMessage(parsedResponse[1]);
                } else {
                    chatApp.sendMessage("Unexpected password response: " + response);
                }

                
            }

            // LOGIN 

            while (!isAuthed) {
                chatApp.sendMessage("Insert Login: ");
                String response = in.readLine();
                if (response == null) break;

                String[] parsedResponse = response.split(":");
                if (parsedResponse[0].equals("200")) {
                    isAuthed = true;
                    chatApp.sendMessage(parsedResponse[1]);
                    

                } else if (parsedResponse[0].equals("403")) {
                    
                    chatApp.sendMessage(parsedResponse[1]);
                    // socket.close();
                    // interrupt();
                    // return;
                } else {
                    chatApp.sendMessage("Login error: " + response);
                }
            }

            // MESSAGE SENDING
            Reader reader = new Reader(socket,chatApp);
            reader.start();
            while (socket.isConnected()) {   } // else the writer closes

        } catch (IOException e) {
            chatApp.sendMessage("Connection error: " + e.getMessage());
        }
    }
}

class Reader extends Thread {
    private Socket socket;
    private ChatApp chatApp;

    Reader(Socket socket, ChatApp chatApp) {
        this.socket = socket;
        this.chatApp=chatApp;
    }

    @Override
    public void run() {
        try (
            BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()))
        ) {
            String response;
            while ((response = in.readLine()) != null) {
                if (response.contains(",") && response.contains("nome:") && response.contains("messaggio:")) {
                    try {
                        String[] parts = response.split(",");
                        String sender = parts[0].split(":")[1];
                        String message = parts[1].split(":")[1];
                        chatApp.sendMessage(sender + ": " + message);
                    } catch (Exception e) {
                        chatApp.sendMessage("Something wrong happened " + response);
                    }
                } else {
                    chatApp.sendMessage("Server: " + response);
                }
            }
        } catch (IOException e) {
            chatApp.sendMessage("Disconnected from server.");
        }
    }
}
