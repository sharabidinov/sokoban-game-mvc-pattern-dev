package server;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

public class Client implements Runnable {

    private Socket socket;
    private Thread thread;

    public Client(Socket socket) {
        this.socket = socket;
        thread = new Thread(this);
    }

    public void run() {
        try (
                ObjectInputStream objectInputStream = new ObjectInputStream(socket.getInputStream());
                ObjectOutputStream outputStream = new ObjectOutputStream(socket.getOutputStream());) {
            String filePath = objectInputStream.readUTF();
            LevelChooserServer levelChooser = new LevelChooserServer();
            int[][] level = levelChooser.loadLevelFromFile(filePath);

            outputStream.writeObject(level);
        } catch (IOException ioe) {
            System.out.println("Exception occured: " + ioe.getMessage());
        }
    }

    public void go() {
        thread.start();
    }
}
