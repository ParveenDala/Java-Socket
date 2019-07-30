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
    private OutputStreamWriter outputStreamWriter;

    public static void main(String[] args) {
        Client client = new Client();
        client.initSocket();
        client.sendMessage("Hi");
    }

    private void initSocket() {
        try {
            String ipAddress = "localhost";
            int portNumber = 8989;
            socket = new Socket(ipAddress, portNumber);

            outputStreamWriter = new OutputStreamWriter(socket.getOutputStream());
            printWriter = new PrintWriter(outputStreamWriter);

        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    private void sendMessage(String messsage) {
        printWriter.write(messsage);
    }

}
