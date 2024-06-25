package UDP;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

public class Client {

    public static void main(String[] args) throws IOException {
        String host = "localhost";
        DatagramSocket socket = new DatagramSocket(); //sem numero de port
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));

        while (true) {
            byte[] bytes = new byte[1024]; //para ler o que enviam
            String line = bufferedReader.readLine(); //para escrever no console

            DatagramPacket send = new DatagramPacket(line.getBytes(), line.getBytes().length, InetAddress.getLocalHost(), 8000);
            socket.send(send);

            DatagramPacket receive = new DatagramPacket(bytes, bytes.length);
            socket.receive(receive);
            String message = new String(receive.getData(), 0, receive.getLength());
            System.out.println(message);
        }
    }
}