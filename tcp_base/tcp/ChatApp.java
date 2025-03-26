import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;

public class ChatApp extends JFrame {
    private JTextArea chatArea;
    private JTextField inputField;
    private JButton sendButton;
    private Socket socket;
    private String login;
    private boolean isAuthed;
    

    public ChatApp(Socket socket) {
        this.socket= socket;
        setTitle("Chat Interface");
        setSize(400, 500);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        // Chat Display Area
        chatArea = new JTextArea();
        chatArea.setEditable(false);
        chatArea.setFont(new Font("Serif", Font.PLAIN, 16));
        JScrollPane chatScrollPane = new JScrollPane(chatArea);
        add(chatScrollPane, BorderLayout.CENTER);

        // Input Panel
        JPanel inputPanel = new JPanel(new BorderLayout());
        inputField = new JTextField();
        sendButton = new JButton("Send");

        inputPanel.add(inputField, BorderLayout.CENTER);
        inputPanel.add(sendButton, BorderLayout.EAST);
        add(inputPanel, BorderLayout.SOUTH);

        // Send Button Action
        sendButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                sendMessage();
            }
        });

        setVisible(true);
        appendMessage("Benvenuto! Scegli un nome");
    }

    public void setLogin( String login){
        this.login = login;
        this.isAuthed= true;
    }

    public void sendMessage() {
        try {
            PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
            String message = inputField.getText().trim();
            if (!isAuthed) {
                out.println(message);
                setLogin(message);
                return;
            };
            if (message.isEmpty()) return;
    
            appendMessage("You: " + message);
            inputField.setText("");
            out.println("nome:" + login + ",messaggio:" + message);
        } catch (IOException e) {
            appendMessage("Error: "+ e.getStackTrace());
        }
 
      
    }

    public void receiveMessage(String message) {
        SwingUtilities.invokeLater(() -> appendMessage(message));
    }

    private void appendMessage(String message) {
        chatArea.append(message + "\n");
    }

  
}
