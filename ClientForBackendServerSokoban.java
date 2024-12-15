import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.net.UnknownHostException;

public class ClientForBackendServerSokoban {
    private Socket socket;

    public ClientForBackendServerSokoban(String host, int port) {
        try {
            socket = new Socket(host, port);
        } catch (UnknownHostException uhe) {
            System.out.println("Error " + uhe);
        } catch (IOException ioe) {
            System.out.println("Error " + ioe);

        }
    }

    public int[][] loadLevelFromServer(String filePath) {
        int[][] desktop = new int[0][];
        try (
                ObjectOutputStream objectOutputStream = new ObjectOutputStream(socket.getOutputStream());
                ObjectInputStream objectInputStream = new ObjectInputStream(socket.getInputStream());) {

            objectOutputStream.writeUTF(filePath);
            objectOutputStream.flush();

            desktop = (int[][]) objectInputStream.readObject();
        } catch (IOException ioe) {
            System.out.println("IOException occured: " + ioe.getMessage());
        } catch (ClassNotFoundException cnfe) {
            System.out.println("ClassNotFoundException occured: " + cnfe.getMessage());
        }
        return desktop;
    }
}