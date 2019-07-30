import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;

/*****
 * Parveen D
 * Java Socket
 */
public class Server {

    private ServerSocket serverSocket;

    public static void main(String[] args) {
        Server server = new Server();
        server.startServer();
    }

    private void startServer() {
        try {
            System.out.println("Server started");
            int portNumber = 8989;
            serverSocket = new ServerSocket(portNumber);
            System.out.println("Waiting for client......");
            Socket socket = serverSocket.accept();
            System.out.println("Received request from client");
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            String dataFromClient = bufferedReader.readLine();
            System.out.println(dataFromClient);
            socket.close();
            serverSocket.close();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}
