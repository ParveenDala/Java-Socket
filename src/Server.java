import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
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
            handleRequest(serverSocket.accept());
            serverSocket.close();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    private void handleRequest(Socket socket) {
        try {
            System.out.println("Received request from client");
            BufferedReader brFromClient = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            String dataFromClient = brFromClient.readLine();
            System.out.println(dataFromClient);
            PrintWriter printWriter = new PrintWriter(new OutputStreamWriter(socket.getOutputStream()));
            printWriter.println("Hello from server");
            printWriter.flush();
            socket.close();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

}
