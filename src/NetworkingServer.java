/* SERVER - may be enhanced to work for multiple clients */

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class NetworkingServer {
    public static void main(String[] args) {

        ServerSocket server = null;
        Socket Client;

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

    }
}