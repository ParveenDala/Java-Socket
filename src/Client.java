import java.io.*;
import java.net.Socket;

/*****
 * Parveen D
 * Java Socket
 */
public class Client {
    private Socket socket;

    public static void main(String[] args) {
        Client client = new Client();
        client.initSocket();
        new Thread(client.new SendToServer()).start();
        new Thread(client.new ListenToServer()).start();
    }

    private void initSocket() {
        try {
            String ipAddress = "localhost";
            int portNumber = 9999;
            socket = new Socket(ipAddress, portNumber);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    class SendToServer implements Runnable {

        @Override
        public void run() {
            try {
                PrintWriter printWriter = new PrintWriter(new OutputStreamWriter(socket.getOutputStream()));
                BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
                String dataToSend = null;
                while ((dataToSend = br.readLine()) != null) {
                    printWriter.write(dataToSend + "\n");
                    printWriter.flush();
                    if (dataToSend.equals("stop")) {
                        closeSocket();
                        break;
                    }
                }
                System.out.println("SendToServer Done");
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    class ListenToServer implements Runnable {

        @Override
        public void run() {
            try {
                BufferedReader brFromServer = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                String fromServer = null;
                while ((fromServer = brFromServer.readLine()) != null) {
                    System.out.println(fromServer);
                    if (fromServer.equals("stop")) {
                        closeSocket();
                        break;
                    }
                }
                System.out.println("ListenToServer Done");
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private void closeSocket() {
        try {
            socket.close();
            System.out.println("Closed");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
