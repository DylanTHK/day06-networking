package day06;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;

public class ListClient {
    public static void main(String[] args) throws Exception {
        // define port number and host name
        Integer num = 10;
        Integer limit = 100; // number required to generate random numbers
        Integer port = 8080;
        String host = "localhost";
        String clientOut = num.toString() + " " + limit.toString();

        // create client socket and bind to host
        Socket socket = new Socket(host, port);

        System.out.printf("Connected to %s: %d on %d\n", host, port, socket.getPort());
        
        // output stream (to send to server)
        OutputStream os = socket.getOutputStream();
        BufferedOutputStream bos = new BufferedOutputStream(os);
        DataOutputStream dos = new DataOutputStream(bos);

        System.out.println("Sending string to the ServerSocket");

        // send num = 10, limit = 100 to server
        dos.writeUTF(clientOut);
        dos.flush(); // send the message
        
        System.out.println("Closing socket and terminating program.");
        
        // setup input stream
        InputStream is = socket.getInputStream(); //check socket
        BufferedInputStream bis = new BufferedInputStream(is);
        DataInputStream dis = new DataInputStream(bis);
        
        // reading input
        String incomingData = dis.readUTF();
        System.out.printf("Data Read: %s\n", incomingData);
        
        // evaluate numbers and calculate average
        Integer averageValue = calculateAverage(incomingData);
        System.out.printf("The average of all random numbers is: %d\n", averageValue);

        // close output, input streams and clientSocket
        dos.close();
        dis.close();
        socket.close();
    }

    public static Integer calculateAverage(String input) {
        Integer count = 0;
        Integer total = 0;
        String[] randomNumbers = input.trim().split(" ");
        
        for (String num : randomNumbers) {
            total += Integer.parseInt(num);
            count++;
        }

        return total / count;
    }
}
