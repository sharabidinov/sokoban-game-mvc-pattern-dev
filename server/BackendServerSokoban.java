package server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class BackendServerSokoban {

    private int serverPort;
    private ServerSocket serverSocket;
    private final Object lock;
    private static int count;

    public BackendServerSokoban(int serverPort) {
        this.serverPort = serverPort;
        count = 1;
        lock = new Object();
        try {
            serverSocket = new ServerSocket(this.serverPort);
        } catch (IOException ioe) {
            System.out.println("Error initializing server socket: " + ioe);
        }
    }

    public void startServer() {
        if (serverSocket == null) {
            System.out.println("Server socket was not initialized.");
            return;
        }

        while (true) {
            try {
                Socket clientSocket = serverSocket.accept();
                System.out.println(count + " : [ " + clientSocket + " ];");
                synchronized (lock) {
                    Client client = new Client(clientSocket);
                    client.go();
                    count = count + 1;
                }
            } catch (IOException ioe) {
                System.out.println("Error accepting client connection: " + ioe);
            }
        }
    }
}
