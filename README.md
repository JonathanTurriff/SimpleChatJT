# SimpleChatJT

This repository contains the code for my Assignment 5 in my SEG2105 class.
This assignment is to expanding off of , https://github.com/JonathanTurriff/Lloseng where I implemented the OCSF framework of the directory: https://github.com/TimLethbridge/Lloseng

It was then modified to become SimpleChatJT by doing changes to the way it runs and the way it looks, which was a lot of fun.

## Here are the updates made to the program:
- Initialized the program into a gradle builder.
- Created a GUI for the Client and Server interfaces of the ClientConsole.java and ServerConsole.java classes.
- Changed the EchoServer.java Class to implement having a ChatIF class tied to it.
- Devised a new function in EchoServer Called sendClientList() that sends a list of all current clients to the client logging in.
- Designed start.fxml, console.fxml, login.fxml and chatBox.fxml to represent the GUIs for ClientConsole.java and ServerConsole.java.
- Conceptualized ChatBoxController.java, LoginController.java, ConsoleController.java and StartController.java to control and give action to the GUIs designed in fxml.
- Added an error window called AlertBox that shows up if ever there is an error happening. it indicates it and stops what would've caused the error.
- Changed the way ChatClient works to communicate with the Error window.
