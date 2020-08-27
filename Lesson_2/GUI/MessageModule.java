package root.GUI;

import javax.swing.*;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.EOFException;
import java.io.IOException;

public class MessageModule {

    private final ChatWindow chatWindow;
    private DataInputStream in;
    private DataOutputStream out;
    private final JTextArea chatHistory;
    private final ChatWindow.Client currentClient;
    private final UsersList userList;

    public MessageModule(ChatWindow chatWindow, DataInputStream in, DataOutputStream out, JTextArea chatHistory, ChatWindow.Client currentClient, UsersList userList) {
        this.chatWindow = chatWindow;
        this.in = in;
        this.out = out;
        this.chatHistory = chatHistory;
        this.currentClient = currentClient;
        this.userList = userList;
        getMessage();
    }

    public void getMessage() {
        StringBuilder txt = new StringBuilder();

        new Thread(new Runnable() {
            @Override
            public void run() {
                while (true) {
                    try {
                        String message = in.readUTF();
                        if (checkMessage(message)) {
                            String sender = getSender(message);
                            if (sender.equals(currentClient.getName())) {
                                txt.append(chatHistory.getText()).append("\n").append(message);
                                currentClient.setHistory(message);
                                chatHistory.setText(txt.toString());
                                txt.setLength(0);
                            } else {
                                userList.addMessageCounter(sender, message);
                            }
                        }
                    } catch (EOFException e) {
                        System.out.println("Вы ушли из чата! Всего доброго!");
                        return;
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
        }).start();
    }

    protected void sendMessage() {
        try {
            out.writeUTF(String.format("/%s /addr[%s]: %s", chatWindow.getChatTitle(), chatWindow.getName(), chatWindow.getMessage()));
            currentClient.setHistory(String.format("[%s]: %s", chatWindow.getName(), chatWindow.getMessage()));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    protected void sendMessage(String message) {
        try {
            out.writeUTF(message);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    protected boolean checkMessage(String message) {
        StringBuilder txt = new StringBuilder(message);
        if (message.startsWith("/userReg")) {
            txt.delete(0, 8);
            String[] name = txt.toString().split("|");
            userList.setUser(name[0], name[1]);
            userList.revalidate();
            return false;
        } else if (message.startsWith("/userOnline")) {
            txt.delete(0, 11);
            String[] name = txt.toString().split("|");
            chatWindow.getOnlineList().add(new ChatWindow.Client(name[0], name[1]));
            userList.removeUserList();
            return false;
        } else if (message.startsWith("/userOffline")) {
            txt.delete(0, 12);
            ChatWindow.Client removedClient = null;
            for (ChatWindow.Client c : chatWindow.getOnlineList()) {
                if (c.getName().equals(txt.toString())){
                    removedClient = c;
                }
            }
            chatWindow.getOnlineList().remove(removedClient);
            userList.removeUserList();
            return false;
        }
        return true;
    }

    private String getSender(String message) {
        StringBuilder sender = new StringBuilder(message);
        sender.deleteCharAt(0);
        sender.setLength(message.indexOf("]") - 1);
        return sender.toString();
    }
}