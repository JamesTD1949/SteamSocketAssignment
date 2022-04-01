import java.net.*;
import java.io.*;

/**
 * This class is a module which provides the application logic
 * for an Echo client using stream-mode socket.
 * @author M. L. Liu
 */

class EchoClientHelper2 {

   private MyStreamSocket mySocket;
   EchoClientHelper2(String hostName, String portNum) throws IOException
   {
      InetAddress serverHost = InetAddress.getByName(hostName);
      int serverPort = Integer.parseInt(portNum);
      //Instantiates a stream-mode socket and wait for a connection.
      this.mySocket = new MyStreamSocket(serverHost, serverPort);
      System.out.println("Connection request made");
   } // end constructor

   //method to handle login client functionality
   String login( String message) throws IOException{
      mySocket.sendMessage(message);
      return mySocket.receiveMessage();
   } // end getEcho

   //method to handle download client functionality
   String download( String message) throws IOException{
      String completeDownload;
      mySocket.sendMessage(message);
      // now receive the echo
      completeDownload = mySocket.receiveMessage();
      //for loop to iterate through messages arraylist and append each message to completeDownload String
      for(int i=0;i<EchoServer3.messages.size();i++)
      {
         completeDownload += mySocket.receiveMessage();
      }
      completeDownload +=  mySocket.receiveMessage();
      return completeDownload;
   }

   //method to handle log Out client functionality
   String logOut(String message) throws IOException {
      mySocket.sendMessage(message);
      return mySocket.receiveMessage();
   }

   //method to handle upload client functionality
   String upload(String message) throws IOException{
      mySocket.sendMessage(message);
      return mySocket.receiveMessage();
   }

   //method to handle closing client
   void done() throws IOException{
      mySocket.sendMessage("END");
      mySocket.close();
   }
} //end class
