package TCP;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Server {
    static List<ClientHandler> clientHandlers;

    public Server() {
        this.clientHandlers = new CopyOnWriteArrayList<>();
    }


    //PSVM
    public static void main(String[] args) throws IOException {

        Server server = new Server();

        try {
            server.start();
        } catch (IOException e) {
            throw new RuntimeException(e);

        }


    }

    public void start() throws IOException {
        ExecutorService cachedPool = Executors.newCachedThreadPool();

        ServerSocket socket = new ServerSocket(8000);

        while (true) {
            cachedPool.submit(new ClientHandler(socket.accept()));
        }

    }

    public static class ClientHandler implements Runnable {
        Socket socket;

        public ClientHandler(Socket socket) {
            this.socket = socket;
            clientHandlers.add(this);
        }

        @Override
        public void run() {

            try {
                InputStream in = socket.getInputStream();
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(in));
                String line;

                while ((line = bufferedReader.readLine()) != null) {
                    if (line.equals("oi")) {
                        System.out.println("ola");
                        continue;
                    }
                    if (line.equals("yt")) {
                        System.out.println("ola");
                    }


                    System.out.println(line);
                }


            } catch (IOException e) {
                throw new RuntimeException(e);
            }

        }
    }
}
