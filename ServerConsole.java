// This file contains material supporting section 3.7 of the textbook:
// "Object Oriented Software Engineering" and is issued under the open-source
// license found at www.lloseng.com

import java.io.*;
import client.*;
import common.*;

/**
 * This class constructs the UI for a server console.  It implements the
 * chat interface in order to activate the display() method.
 * Warning: Some of the code here is cloned in ServerConsole
 *
 * @author Fran&ccedil;ois B&eacute;langer
 * @author Dr Timothy C. Lethbridge
 * @author Dr Robert Lagani&egrave;re
 * @version July 2000
 */
 public class ServerConsole implements ChatIF{
  //Class variables *************************************************

  /**
   * The default port to connect on.
   */
  final public static int DEFAULT_PORT = 5555;
  // Thought i could get rid of this but i needed it to implement ChatIF
  //Instance variables **********************************************

  /**
   * The instance of the client that created this ConsoleChat.
   */
  EchoServer server;


  //Constructors ****************************************************

  /**
   * Constructs an instance of the ClientConsole UI.
   *
   * @param host The host to connect to.
   * @param port The port to connect on.
   */
  public ServerConsole(EchoServer server){
    this.server = server;
  }


  //Instance methods ************************************************

  /**
   * This method waits for input from the console.  Once it is
   * received, it sends it to the client's message handler.
   */
  public void accept()
  {
    try
    {
      BufferedReader fromConsole = new BufferedReader(new InputStreamReader(System.in));
      String message;

      while (true)
      {
        message = fromConsole.readLine();
        server.handleMessageFromServer(message);
      }
    }
    catch (Exception ex)
    {
      display
        ("Unexpected error while reading from console!");
    }
  }

  /**
   * This method overrides the method in the ChatIF interface.  It
   * displays a message onto the screen.
   *
   * @param message The string to be displayed.
   */
  public void display(String message)
  {
    System.out.println("> " + message);
  }


  //Class methods ***************************************************

  /**
   * This method is responsible for the creation of the Client UI.
   *
   * @param args[0] The host to connect to.
   */
   public static void main(String[] args)
   {
     int port = 0; //Port to listen on

     try
     {
       port = Integer.parseInt(args[0]); //Get port from command line
     }
     catch(Throwable t)
     {
       port = DEFAULT_PORT; //Set port to 5555
     }
     //initialises the things nessesary for the ServerUI
     EchoServer serv = new EchoServer(port);
     ServerConsole chat = new ServerConsole(serv);
     try
     {
       serv.listen(); //Start listening for connections
     }
     catch (Exception ex)
     {
       System.out.println("ERROR - Could not listen for clients!");
     }
     //lets the user chat with the console
     chat.accept();

   }
 //of ServerConsole class

}
