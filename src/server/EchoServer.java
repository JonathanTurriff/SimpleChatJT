package src.server;// This file contains material supporting section 3.7 of the textbook:
// "Object Oriented Software Engineering" and is issued under the open-source
// license found at www.lloseng.com
import java.io.*;

import com.sun.javafx.iio.ios.IosDescriptor;
import src.common.ChatIF;
import src.ocsf.server.*;

/**
 * This class overrides some of the methods in the abstract
 * superclass in order to give more functionality to the server.
 *
 * @author Dr Timothy C. Lethbridge
 * @author Dr Robert Lagani&egrave;re
 * @author Fran&ccedil;ois B&eacute;langer
 * @author Paul Holden
 * @author Jonathan Turriff
 * @version July 2000
 */
public class EchoServer extends AbstractServer
{
  //Class variables *************************************************

  /**
   * The default port to listen on.
   */
  final public static int DEFAULT_PORT = 5555;
  public ChatIF server;

  //I couldn't figure out how to determine if the server is closed or not because isClosed() isnt working so i just used booleans
  private boolean stopped;
  private boolean closed;

  //Constructors ****************************************************

  /**
   * Constructs an instance of the echo server.
   *
   * @param port The port number to connect on.
   */
  public EchoServer(int port, ChatIF server)
  {
    super(port);
    this.server = server;
    stopped = false;
    closed = false;
  }


  //Instance methods ************************************************

  /**
   * This method handles any messages received from the client.
   *
   * @param msg The message received from the client.
   * @param client The connection from which the message originated.
   */
  public void handleMessageFromClient(Object msg, ConnectionToClient client)
  { String message  = (String) msg;
    String[] command  = message.split(" ");
    try{
      if(client.getInfo("loginID") == null){
        client.setInfo("loginID", command[1]);
        sendToAllClients(client.getInfo("loginID") +" Has Logged in.");
        sendClientList(client);
        server.display("New Connection: " +client.getInfo("loginID"));
      }else if(command[0].equals("#login")){
        client.sendToClient("Error: You have already logged in");
        server.display("New Disconnection: " +client.getInfo("loginID"));
        sendToAllClients(client.getInfo("loginID")+" Has Disconnected.");
        client.close();
      }else{
        server.display("Message received: " + msg + " from " + client+", loginID: "+client.getInfo("loginID"));
      this.sendToAllClients(client.getInfo("loginID")+": "+msg);
      }
  }
    catch(IOException e){
      server.display("Error: Could not send message to server.");
    }
    }
  /**
   * This method handles any messages received from the Server Console.
   *
   * @param msg The message received from the client.
   */
  public void handleMessageFromServer(String msg ){
    if(msg.length() ==0){
      return;
    }
    try{
    if(msg.substring(0,1).equals("#")){
      handleServerCommands(msg);//new command i made to handle the # commands to have the code clean
    }else{
      server.display("SERVER MSG> "+msg);
      sendToAllClients("SERVER MSG> "+ msg);
    }
    }catch(IOException e){
      server.display("Error: Could not send message to server.");

    }
  }

  /**This is one of my modifications @Jonathan Turriff
   * This function sends to a client who has just joined the chat a list of who is present in the server
   * @param client is the variable where I send the information
   * @throws IOException when initializing new variables.
   */
  public void sendClientList(ConnectionToClient client) throws IOException {
    String cList = "Clients Connected: ";
    Thread[] list = getClientConnections();
    if(list.length == (1)){
      client.sendToClient("There are no other connections.");
      return;
    }
    try {
      for(int i = 0; i<list.length; i++) {
        ConnectionToClient C = (ConnectionToClient) getClientConnections()[i];
        if(cList.equals("Clients Connected: ")){
          cList = cList + C.getInfo("loginID");
        }else{
          cList = cList +" , "+ C.getInfo("loginID");
        }
      }
      client.sendToClient(cList);

    }catch(Exception e){
      return;
    }
  }

  /**
   * This method handles any command received from the Server Console.
   *
   * @param message The message received from the client.
   */

  public void handleServerCommands(String message) throws IOException{

    String[] command = message.split(" ");

    if(command[0].equals("#quit")){
      server.display("Closing ServerConsole...");
      sendToAllClients("SERVER MSG> Server is closing...");
      close();
      System.exit(0);
    }else if (command[0].equals("#stop")){
      stopped = true;
      stopListening();
    }else if (command[0].equals("#close")){
      sendToAllClients("SERVER MSG> Server is closing...");
      server.display("Server is now closed.");
      closed = true;
      stopped = true;
      close();
    }else if (command[0].equals("#setport")){
      if(!closed){
        server.display("Error: You can't change hosts if you're already connected to port " + getPort());
      }else{
        try{
        setPort(Integer.parseInt(command[1]));
          server.display("You are now set to: "+ getPort());
      }catch(Exception e){
          server.display("Error: Invalid arguments. Try again.");
      }
    }
    }else if (command[0].equals("#getport")){
      server.display("You're connected to host " + getPort());
    }else if(command[0].equals("#start")){
      if(stopped){
        listen();
        stopped = false;
        closed = false;
      }else{
        server.display("Server is already started.");
      }
    }else{
      server.display("SERVER MSG> "+message);
      sendToAllClients("SERVER MSG> "+ message);
    }
  }

  /**
   * This method overrides the one in the superclass.  Called
   * when the server starts listening for connections.
   */
  protected void serverStarted()
  {
    server.display("Server listening for connections on port " + getPort());
  }

  /**
   * This method overrides the one in the superclass.  Called
   * when the server stops listening for connections.
   */
  protected void serverStopped()
  {
    server.display
      ("Server has stopped listening for connections.");
  }

  //Class methods ***************************************************


  /**
   * Hook method called each time a new client connection is
   * accepted. The default implementation does nothing.
   * @param client the connection connected to the client.
   **/
  // protected void clientConnected(ConnectionToClient client) {
  //   System.out.println("New Connection: "+ client.getInfo("loginID"));
  // }

  /* Hook method called each time an exception is thrown in a
  * ConnectionToClient thread.
  * The method may be overridden by subclasses but should remains
  * synchronized.
  *
  * @param client the client that raised the exception.
  * @param Throwable the exception thrown.
   */
  @Override
  synchronized protected void clientException(
     ConnectionToClient client, Throwable exception){
       sendToAllClients( client.getInfo("loginID")+" Has disconnected");
       server.display( client.getInfo("loginID")+ " Has disconnected.");
    }
    synchronized protected void clientDisconnected(
       ConnectionToClient client) {
      server.display("New Disconnection: "+ client.getInfo("loginID"));
      }

//Got rid of Main in this class to implement it in ServerConsole.java
}
//End of EchoServer class
