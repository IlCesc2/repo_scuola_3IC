import javax.swing.*;
import java.awt.*;

public class ChatApp extends JFrame {
    private JTextArea chatArea;
     JTextField inputField;
     JButton sendButton;

    public ChatApp() {
        setTitle("Some sort of chat (idk)");
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

        setVisible(true);
        sendMessage("Welcome to the chat!");
    }

    public void sendMessage(String message) {
        if (message.trim().isEmpty()) return;
        inputField.setText("");

        appendMessage(message);
        
    }

    private void appendMessage(String message) {
        chatArea.append(message + "\n");
    }
}
