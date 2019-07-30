import java.io.*;
import java.net.Socket;

/*****
 * Parveen D
 * Java Socket
 */
public class Client {
    private Socket socket;
    private PrintWriter printWriter;
    BufferedReader brFromServer;

    public static void main(String[] args) {
        Client client = new Client();
        client.initSocket();
        client.sendMessage("Hello, from client\n");
        client.listenToServer();
        client.closeSocket();
    }

    private void initSocket() {
        try {
            String ipAddress = "localhost";
            int portNumber = 8989;
            socket = new Socket(ipAddress, portNumber);
            printWriter = new PrintWriter(new OutputStreamWriter(socket.getOutputStream()));
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    private void sendMessage(String message) {
        printWriter.write(message);
        printWriter.flush();
    }

    private void listenToServer() {
        try {
            brFromServer = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            String fromServer = null;
            fromServer = brFromServer.readLine();
            System.out.println(fromServer);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void closeSocket() {
        try {
            socket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
