package core;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Core {


    public static List<String> billbord = new ArrayList<>();

    public static void main(String[] args) {

        ExecutorService executorService = Executors.newCachedThreadPool();

        try (ServerSocket serverSocket = new ServerSocket(5050)) {

            while (true) {
                Socket client = serverSocket.accept();

                executorService.submit(() -> haldleConnection(client));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    private static void haldleConnection(Socket client) {
        try {

            var inputFromClient = new BufferedReader(new InputStreamReader(client.getInputStream()));

            readRequest(inputFromClient);

            PrintWriter outPutToClient = new PrintWriter(client.getOutputStream());
            sendResponse(outPutToClient);

            outPutToClient.close();
            inputFromClient.close();
            client.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void sendResponse(PrintWriter outPutToClient) {
        synchronized (billbord) {
            for (String line : billbord) {
                outPutToClient.print(line + "\r\n");
            }
        }
        outPutToClient.print("\r\n");
        outPutToClient.flush();
    }

    private static void readRequest(BufferedReader inputFromClient) throws IOException {
        List<String> tempList = new ArrayList<>();
        while (true) {
            String line = inputFromClient.readLine();
            if (line == null || line.isEmpty()) {
                break;
            }


            billbord.add(line);
            System.out.println(line);

        }

        synchronized (billbord){
            billbord.addAll(tempList);
        }
    }
}
