package day06;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;
import java.util.*;

public class ListServer {
    public static void main(String[] args) throws Exception {
        System.out.println("LIST SERVER INITIALISED");

        // create list server port number 8080
        // Integer port = Integer.parseInt(args[0]);
        Integer port = 8080;

        // creates a SERVER socket and bind to port
        // (Server is listening on port for requests)
        ServerSocket server = new ServerSocket(port);
        System.out.printf("Server listening on port %d\n", port);
        

        // accepts request from client, establish connection w client
        Socket clientSocket = server.accept();
        System.out.printf("New connection on port %s\n", clientSocket.getLocalPort());
        
        // input stream (Receiving data)
        InputStream is = clientSocket.getInputStream(); //
        BufferedInputStream bis = new BufferedInputStream(is); // make the process more efficient
        DataInputStream dis = new DataInputStream(bis);
        
        String messageIn = dis.readUTF(); // read input
        
        // converting message to array
        String[] inputParameters = messageIn.trim().split(" ");

        // generate array of random numbers
        String listOut = generateRandoms(inputParameters);

        // setup output stream (Sending data)
        OutputStream os = clientSocket.getOutputStream();
        BufferedOutputStream bos = new BufferedOutputStream(os);
        DataOutputStream dos = new DataOutputStream(bos);

        System.out.printf("Sending string over: %s\n", listOut);

        dos.writeUTF(listOut);
        dos.flush(); // what does flush do?

        // os.close();
        // bos.close();
        dos.close();
        dis.close();
        clientSocket.close();
        
    }

    // take input string array and generate n number random numbers
    public static String generateRandoms(String[] input) {
        Integer n = Integer.parseInt(input[0]);
        Integer min = 0;
        Integer max = Integer.parseInt(input[1]);

        String  randomList = "";
        String delimiter = " ";
        // creating random numbers and adding to arraylist
        for (int i = 0; i < n; i++) {
            Integer randomNumber = (int) Math.floor(Math.random()*(max - min + 1) + min);
            if (i == 0) {
                randomList = Integer.toString(randomNumber);
            } else {
                randomList = randomList + delimiter + randomNumber;
            }
        }

        return randomList;
    }
}
