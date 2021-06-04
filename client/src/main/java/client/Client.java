package client;

import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.Socket;

public class Client {

    public static void main(String[] args) {

        try {
            Socket socket = new Socket("localhost",5050);

            PrintWriter outPut = new PrintWriter(socket.getOutputStream());
            outPut.println("Hello From Client");
            outPut.flush();
            outPut.close();
//            OutputStream outPut = socket.getOutputStream();
//            System.out.println("Hello From Client"+outPut);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
