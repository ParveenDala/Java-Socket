import java.io.*;
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
            int portNumber = 9999;
            serverSocket = new ServerSocket(portNumber);
            System.out.println("Waiting for client......");
            handleRequest(serverSocket.accept());
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    private void handleRequest(Socket socket) {
        try {
            System.out.println("Received request from client");
            new Thread(new ListenToClient(socket)).start();
            new Thread(new SendToClient(socket)).start();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    class SendToClient implements Runnable {
        private Socket socket;

        public SendToClient(Socket socket) {
            this.socket = socket;
        }

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
                        closeSocket(socket);
                        break;
                    }
                }
                System.out.println("SendToClient Done");
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    class ListenToClient implements Runnable {
        private Socket socket;

        public ListenToClient(Socket socket) {
            this.socket = socket;
        }

        @Override
        public void run() {
            try {
                BufferedReader brFromServer = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                String fromServer = null;
                while ((fromServer = brFromServer.readLine()) != null) {
                    System.out.println(fromServer);
                    if (fromServer.equals("stop")) {
                        closeSocket(socket);
                        break;
                    }
                }
                System.out.println("ListenToClient Done");
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private void closeSocket(Socket socket) {
        try {
            socket.close();
            System.out.println("Closed");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
