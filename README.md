# SimpleChatJT

This repository contains the code for my Assignment 5 in my SEG2105 class.
For this assignment, I decided to expand off of my assignment one, where I implemented the OCSF framework of the directory: https://github.com/TimLethbridge/Lloseng
and modify it for it to become Simplechat in this directory: https://github.com/JonathanTurriff/Lloseng. I then modified it for it to become SimpleChatJT.

## Here are the updates made to the program:
- Initialized the program into a gradle builder.
- Created a GUI for the Client and Server interfaces of the ClientConsole.java and ServerConsole.java classes. 
- Changed the EchoServer.java Class to implement having a ChatIF class tied to it.
- Devised a new function in EchoServer Called sendClientList() that sends a list of all current clients to the client logging in.
- Designed start.fxml, console.fxml, login.fxml and chatBox.fxml to represent the GUIs for ClientConsole.java and ServerConsole.java.
- Conceptualized ChatBoxController.java, LoginController.java, ConsoleController.java and StartController.java to control and give action to the GUIs designed in fxml.
