 import javax.net.ssl.SSLServerSocketFactory;
 import javax.swing.*;
 import java.io.*;
import java.net.*;
 import java.util.ArrayList;
 import java.util.Arrays;
 import java.util.List;

 /**
 * This module contains the application logic of an echo server
 * which uses a stream-mode socket for interprocess communication.
 * Unlike EchoServer2, this server services clients concurrently.
 * A command-line argument is required to specify the server port.
 * @author M. L. Liu
 */

public class EchoServer3 {
    //initialize Arraylists to hold users and messages on server
    public static ArrayList<String> users = new ArrayList<>();
    public static ArrayList<String> messages = new ArrayList<>();


    public static void main(String[] args) throws IOException {
      int serverPort = 1234;    // default port


      if (args.length == 1 ) {
          serverPort = Integer.parseInt(args[0]);
      }

      //add some sample users to arraylist
      users.add("JamesDowning");
      users.add("User1Password");
      users.add("User2Password");

    //create server socket
     ServerSocket serverSocket = new ServerSocket(serverPort);

      try {
          System.out.println("Echo server ready.");

          //while loop responsible for accepting connection requests from clients
          while (true) {
              System.out.println("Waiting for a connection.");
              MyStreamSocket myDataSocket = new MyStreamSocket(serverSocket.accept());
              System.out.println("Connection accepted");
              Thread SSLThread = new Thread(new EchoServerThread(myDataSocket));
              SSLThread.start();
              System.out.println("New Thread Started.");
          } //end while
      } // end try
      catch (Exception e) {
          e.printStackTrace( );
      } // end catch
    } //end main
} // end class
