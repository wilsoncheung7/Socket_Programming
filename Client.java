package com.eecs3214;

import java.io.*;
import java.net.*;

public class Client {
    public static void main(String[] argv) throws Exception {
        String sentence;
        String modifiedSentence;
        String msg;
        Boolean flag = true;

        while (flag) {
            Socket clientSocket = new Socket("127.0.0.1", 80);

            BufferedReader inFromUser = new BufferedReader(new InputStreamReader(System.in));
            DataOutputStream outToServer = new DataOutputStream(clientSocket.getOutputStream());
            BufferedReader inFromServer = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));

            //A welcome message that send from the server when the client connect to it
            DataInputStream inputStream = new DataInputStream(clientSocket.getInputStream());
            msg = inputStream.readUTF();
            System.out.println(msg);
//        System.out.println(argv[1]);

            //Input the data in the command line
            System.out.print("Enter any message: ");
            sentence = inFromUser.readLine();
            if (sentence.equalsIgnoreCase("quit"))
                flag = false;
            //Send the data to server
            outToServer.writeBytes(sentence + '\n');
            //Get the result that send back from the server
            modifiedSentence = inFromServer.readLine();
            System.out.println("FROM SERVER:" + modifiedSentence);
            //Reply message
            outToServer.writeUTF("Bye");
            msg = inputStream.readUTF();
            System.out.println(msg);
            clientSocket.close();
        }
    }
}

