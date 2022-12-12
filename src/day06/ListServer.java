package day06;

import java.net.ServerSocket;
import java.net.Socket;

import static day06.IOUtil.*;

public class ListServer {
    public static void main(String[] args) throws Exception {
        
        // create list server port number 8080
        // Integer port = Integer.parseInt(args[0]); 
        Integer port = 8080; // added port number for convenient connection

        // creates a SERVER socket (passive connection)
        ServerSocket serverSocket = new ServerSocket(port);
        System.out.printf("Server listening on port %d\n", port);
        
        // establish client Connection with server
        Socket clientSocket = serverSocket.accept(); // accept - passive connection from server
        System.out.printf("New connection on port %s\n", clientSocket.getLocalPort());
        
        // establish input stream and read data
        String inputData = IOUtil.read(clientSocket);
        
        // converting message to array
        String[] inputParameters = inputData.trim().split(" ");

        // generate array of random numbers
        String listOut = generateRandoms(inputParameters);
        System.out.printf("Sending string over: %s\n", listOut);

        // setup output stream (Sending data)
        IOUtil.write(clientSocket, listOut);


        serverSocket.close();
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
