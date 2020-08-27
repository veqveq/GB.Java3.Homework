package root;

import root.GUI.ChatWindow;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;

public class MessageHistoryHandler {

    public static void writeHistory(ChatWindow.Client client, String message) {
        Path path = Paths.get(String.format("src\\root\\histories\\%s.txt", client.getName()));
        try {
            Files.write(path, (message+"\n").getBytes(), StandardOpenOption.APPEND);
        } catch (IOException e) {
            createHistoryFile(client);
            writeHistory(client,message);
        }
    }

    public static String readMessage(ChatWindow.Client client){
        File historyFile = new File(String.format("src\\root\\histories\\%s.txt", client.getName()));
        StringBuilder history = new StringBuilder();
        try {
            BufferedReader reader = new BufferedReader(new FileReader(historyFile));
            int i = 0;
            String val;
            while ((val = reader.readLine()) != null){
                if (i == 100) break;
                history.append(val).append("\n");
                i++;
            }
        } catch (FileNotFoundException e) {
            createHistoryFile(client);
            readMessage(client);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return history.toString();
    }

    private static void createHistoryFile(ChatWindow.Client client) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Files.createFile(Path.of(String.format("src\\root\\histories\\%s.txt", client.getName())));
                } catch (IOException e) {
                }
            }
        }).start();
    }
}
