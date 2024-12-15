package server;

public class Main {
    public static void main(String[] args) {
        BackendServerSokoban backend = new BackendServerSokoban(8080);
        backend.startServer();
    }
}
