package com.eecs3214;

import java.io.*;
import java.net.*;

public class Server {
    public static void main(String argv[]) throws Exception {
        String clientSentence;
        String capitalizedSentence;
        String reply;
        double cm;
        boolean flag = true;

        ServerSocket welcomeSocket = new ServerSocket(80);

        while (flag) {
            Socket connectionSocket = welcomeSocket.accept();
            System.out.println("Connected");// This will show up in the terminal when the connection is established

            BufferedReader inFromClient = new BufferedReader(new InputStreamReader(connectionSocket.getInputStream()));

            DataInputStream inputStream = new DataInputStream(connectionSocket.getInputStream());

            DataOutputStream outToClient = new DataOutputStream(connectionSocket.getOutputStream());
            outToClient.writeUTF("Welcome");

            //Read input from the client, then convert the result to centimeters and send back to client
            clientSentence = inFromClient.readLine();
            if (clientSentence.equals("quit"))
                flag = false;
//            cm = Double.parseDouble(clientSentence) * 2.54;
            capitalizedSentence = clientSentence.toUpperCase() + '\n';
            outToClient.writeBytes(capitalizedSentence);

            //Reply from the client after it has gotten the result
            reply = inputStream.readUTF();
            System.out.println(reply);

            //A farewell message after the server it has gotten the result
            outToClient.writeUTF("Thanks");
        }
    }
}
