/* SERVER - may be enhanced to work for multiple clients */
import java.io.*;
import java.net.*;

public class NetworkingServer {
    public static void main(String[] args) {

        ServerSocket server = null;
        Socket client;

        // Default port number we are going to use
        int portnumber = 5000;
        if (args.length >= 1) {
            portnumber = Integer.parseInt(args[0]);
        }

        // Create Server side socket
        try {
            server = new ServerSocket(portnumber);
        } catch (IOException ie) {
            System.out.println("Cannot open socket." + ie);
            System.exit(1);
        }
        System.out.println("ServerSocket is created." + server);

        // Wait for the data from the client and reply
        while(true) {
            try {
                // Listens for a connection to be made to
                // this socket and accpets it. The method blocks until
                // a connection is made
                System.out.println("Waiting for connection request....");
                client = server.accept();

                System.out.println("Connection request is accepted....");
                String clientHost = client.getInetAddress().getHostAddress();
                int clientPort = client.getPort();
                System.out.println("Client Host = " + clientHost + " Client Port = " + clientPort);

                // Read data from the client
                InputStream clientIn = client.getInputStream();
                BufferedReader br = new BufferedReader(new InputStreamReader(clientIn));
                String msgFromClient = br.readLine();
                System.out.println("Message recieved from client = " + msgFromClient);

                // Send response to the client
                if (msgFromClient != null && ! msgFromClient.equalsIgnoreCase("bye")) {
                    OutputStream clientOut = client.getOutputStream();
                    PrintWriter pw = new PrintWriter(clientOut, true);
                    String ansMsg = "Hello" + msgFromClient;
                    pw.println(ansMsg);
                }

                // Close sockets
                if (msgFromClient != null && msgFromClient.equalsIgnoreCase("bye")) {
                    server.close();

                    client.close();
                    break;
                }
            } catch (IOException ie) {
                // Skriv ett lämpligt error meddelande
            }
        }
    }
}