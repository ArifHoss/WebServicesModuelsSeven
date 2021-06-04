package core;

import utils.Utils;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Core {

    public static void main(String[] args) {

        ExecutorService executorService = Executors.newCachedThreadPool();

        try (ServerSocket serverSocket = new ServerSocket(5050)) {

            while (true) {
                Socket client = serverSocket.accept();
                //starta tråd/Start thread

                executorService.submit(() -> haldleConnection(client));

//                Thread thread = new Thread(() -> haldleConnection(client));
//                thread.start();
//                Thread thread = new Thread(){
//                    @Override
//                    public void run() {
//                        System.out.println(this.getClass().getName());
//                        System.out.println(Thread.currentThread().getName());
//                        try {
//                            haldleConnection(client);
//                        } catch (IOException e) {
//                            e.printStackTrace();
//                        }
//                    }
//                };
//                thread.start();

//                haldleConnection(client);
            }

        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    private static void haldleConnection(Socket client) {
        try {
            System.out.println(client.getInetAddress());
            System.out.println(Thread.currentThread().getName());

            var inputFromClient = new BufferedReader(new InputStreamReader(client.getInputStream()));
            while (true) {
                String line = inputFromClient.readLine();
                if (line == null || line.isEmpty()) {
                    break;
                }
                System.out.println(line);

            }
            PrintWriter outPutToClient = new PrintWriter(client.getOutputStream());
            outPutToClient.print("HTTP/1.1 404 Not Found\r\nContent-length: 0\r\n\r\n");

            outPutToClient.flush();
            outPutToClient.close();
            inputFromClient.close();
            client.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
