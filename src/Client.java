import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.Socket;

/*****
 * Parveen D
 * Java Socket
 */
public class Client {
    private Socket socket;
    private PrintWriter printWriter;

    public static void main(String[] args) {
        Client client = new Client();
        client.initSocket();
        client.sendMessage("Hello");
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

    private void closeSocket() {
        try {
            socket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
