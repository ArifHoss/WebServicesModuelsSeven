package core;

import utils.Utils;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class Core {

    public static void main(String[] args) {

            try (ServerSocket serverSocket = new ServerSocket(5050)) {
        while (true) {

                Socket acceptClient = serverSocket.accept();

                System.out.println("ClientIpAddress: " + acceptClient.getInetAddress());

                BufferedReader inputFromClient = new BufferedReader(new InputStreamReader(acceptClient.getInputStream()));

//                System.out.println(inputFromClient.readLine());
                inputFromClient.lines().forEach(System.out::println);

                inputFromClient.close();
                acceptClient.close();
            }

            } catch (IOException e) {
                e.printStackTrace();
            }

    }
}
