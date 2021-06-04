package core;

import utils.Utils;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class Core {

    public static void main(String[] args) {

        try (ServerSocket serverSocket = new ServerSocket(5050)) {

            while (true) {
                Socket client = serverSocket.accept();
                System.out.println("ClientIpAddress: " + client.getInetAddress());

                var inputFromClient = new BufferedReader(new InputStreamReader(client.getInputStream()));
                while (true) {
                    String line = inputFromClient.readLine();
                    if (line == null||line.isEmpty()) {
                        break;
                    }
                    System.out.println(line);

                }
                PrintWriter outPutToClient = new PrintWriter(client.getOutputStream());
                outPutToClient.print("HTTP/1.1 404 Not Found\r\nContent-length: 0\r\n\r\n");


//                System.out.println(inputFromClient.readLine());
//                inputFromClient.lines().forEach(System.out::println);

                outPutToClient.flush();
                outPutToClient.close();
                inputFromClient.close();
                client.close();
            }

        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
