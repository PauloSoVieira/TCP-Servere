package TCP;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class Client {
    private final String host = "localhost";
    private final int port = 8000;

    public static void main(String[] args) {
        Client client1 = new Client();

        try {
            client1.start();
        } catch (IOException e) {
            throw new RuntimeException(e);

        }
    }

//Ola aaaaa
    
    public void start() throws IOException {
        Socket clientSocket = new Socket(host, port);

        while (true) {
            PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true); //faz a conexão para enviar para fora
            BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
            BufferedReader stdIn = new BufferedReader(new InputStreamReader(System.in)); //escreve o que vai ser enviado para fora

            String readLine = stdIn.readLine();
            out.println(readLine);

            // String received = in.readLine();
            // System.out.println(received);
        }
    }

}
