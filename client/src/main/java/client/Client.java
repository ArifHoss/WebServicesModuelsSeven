package client;

import java.io.*;
import java.net.Socket;

public class Client {

    public static void main(String[] args) {

        try {
            Socket socket = new Socket("localhost", 5050);

            PrintWriter outPut = new PrintWriter(socket.getOutputStream());
            outPut.println("Hello From Client Arif\r\n\r\n");
            outPut.flush();

            // läs svaret från server

            var inputFromServer = new BufferedReader(new InputStreamReader(socket.getInputStream()));

            while (true) {
                String line = inputFromServer.readLine();
                if (line == null || line.isEmpty()) {
                    break;
                }
                System.out.println(line);

            }
            inputFromServer.close();
            outPut.close();
            socket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
