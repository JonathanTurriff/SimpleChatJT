// This file contains material supporting section 3.7 of the textbook:
// "Object Oriented Software Engineering" and is issued under the open-source
// license found at www.lloseng.com

package src.client;

import src.clientGUI.AlertBox;
import src.ocsf.client.*;
import src.common.*;
import java.io.*;
import java.net.ConnectException;

/**
 * This class overrides some of the methods defined in the abstract
 * superclass in order to give more functionality to the client.
 *
 * @author Dr Timothy C. Lethbridge
 * @author Dr Robert Lagani&egrave;
 * @author Fran&ccedil;ois B&eacute;langer
 * @version July 2000
 */
public class ChatClient extends AbstractClient
{
  //Instance variables **********************************************

  /**
   * The interface type variable.  It allows the implementation of
   * the display method in the client.
   */
  ChatIF clientUI;

  //stores the loginID
  private String loginID;

  //Constructors ****************************************************

  /**
   * Constructs an instance of the chat client.
   *
   * @param host The server to connect to.
   * @param port The port number to connect on.
   * @param clientUI The interface type variable.
   */

  public ChatClient(String host, int port, ChatIF clientUI, String loginID)
    throws IOException, ConnectException
  {
    super(host, port); //Call the superclass constructor
    this.clientUI = clientUI;
    this.loginID = loginID;
    try{
      openConnection();
      sendToServer("#login "+loginID);
    }catch(IOException e){
      AlertBox.display("Error","Could not connect to server, try again.");
      clientUI.setError(true);
    }

  }

  //Instance methods ************************************************

  /**
   * This method handles all data that comes in from the server.
   *
   * @param msg The message from the server.
   */
  public void handleMessageFromServer(Object msg)
  {
    clientUI.display(msg.toString());
  }

  /**
   * This method handles all data coming from the UI
   *
   * @param message The message from the UI.
   */
  public void handleMessageFromClientUI(String message)
  {
    try
    {
      if(message.substring(0,1).equals("#")){
        handleClientCommand(message); //new command i made to handle the # commands to have the code clean
      }else if(message == "logoff"){
        closeConnection();
      }else{
      sendToServer(message);
    }
    }
    catch(IOException e)
    {
      clientUI.display
        ("Error: Could not send message to server.  Try again later.");
    }
  }

  /** method called each time the user inputs a command using the key character "#"
  *   this calls other commands such as setHost and others.
  **/
  public void handleClientCommand(String message) throws IOException{
    String[] command = message.split(" ");
    if(command[0].equals("#quit")){
      quit();
    }else if (command[0].equals("#sethost")){
      if(isConnected()){
        clientUI.display("Error: You can't change hosts if you're already connected to hort " + getHost());
      }else{
        try{
        setHost(command[1]);
          clientUI.display("You are now set to: "+ getHost());
      }catch(Exception e){
          clientUI.display("Error: Invalid arguments. Try again.");
      }
    }
    }else if (command[0].equals("#gethost")){
      clientUI.display("You're connected to host " + getHost());
    }else if (command[0].equals("#setport")){
      if(isConnected()){
        clientUI.display("Error: You can't change hosts if you're already connected to port " + getPort());
      }else{
        try{
        setPort(Integer.parseInt(command[1]));
          clientUI.display("You are now set to: "+ getPort());
      }catch(Exception e){
          clientUI.display("Error: Invalid arguments. Try again.");
      }
    }
  }else if(command[0].equals("#login")){
    if(isConnected()){
      sendToServer(message);
    }else{
      openConnection();
      sendToServer(message);
    }
  }else if (command[0].equals("#getport")){
      clientUI.display("You're connected to port " + getPort());
    }else if(command[0].equals("#logoff")){
      if(!isConnected()){
        clientUI.display("Error: You're already disconnected from all servers");
      }else{
        clientUI.display("Connection closed.  (Under NT, it will display Abnormal termination of connection.)");
        sendToServer("null");
        closeConnection();
      }
    }else{
      sendToServer(message);
    }

  }

  /** method called each time an exception is thrown by the client's
   * thread that is waiting for messages from the server.
   */
  protected void connectionClosed() {
    clientUI.display("You have been Logged off!");
 	}

  /**method called after a connection has been established. The default
   * implementation does nothing. It may be overridden by subclasses to do
   * anything they wish.
   */
  protected void connectionException(Exception exception) {
    clientUI.display("Error: Connection Lost, Awaiting command.");
	}

  /**
   * This method terminates the client.
   */

  public void quit()
  {
    try
    {
      closeConnection();
    }
    catch(IOException e) {}
    System.exit(0);
  }
}
//End of ChatClient class
