package day06;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.DataInputStream;
import java.io.DataOutput;
import java.io.DataOutputStream;
import java.io.EOFException;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.UTFDataFormatException;
import java.net.Socket;

public class IOUtil {
    
    public static String read(Socket socket) {
        try {
            // get input stream (is, bis, dis)
            InputStream is = socket.getInputStream(); // uses socket to get the inputstream
            BufferedInputStream bis = new BufferedInputStream(is);
            DataInputStream dis = new DataInputStream(bis);
            
            // read incoming data from source (UTF)
            String input = dis.readUTF();
            return input;

        } catch (IOException e) {
            e.getMessage();
        }
        
        // returns input
        return "null";
    }

    public static void write(Socket socket, String input) {
        try {
            // establish output stream (os, bos, dos)
            OutputStream os = socket.getOutputStream();
            BufferedOutputStream bos = new BufferedOutputStream(os);
            DataOutputStream dis = new DataOutputStream(bos);
            
            // write string to source (UTF)
            dis.writeUTF(input);
            // flush
            dis.flush();

        } catch (IOException e) {
            e.getMessage();
        }
    }
}
