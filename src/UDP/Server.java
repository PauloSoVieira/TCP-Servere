package UDP;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.util.Random;

public class Server {

    public static void main(String[] args) throws IOException {

        Random random = new Random();
        int magicNumber = random.nextInt(1, 10);
        String magicStringNumber = String.valueOf(magicNumber);

        DatagramSocket socket = new DatagramSocket(8000);
        System.out.println(magicStringNumber);

        //receber uma mensagem para pegar as informações de envio
        byte[] bytes = new byte[1024];

        DatagramPacket receive = new DatagramPacket(bytes, bytes.length);
        socket.receive(receive);
        String message = new String(receive.getData(), 0, receive.getLength());

        String hello = "Enter your name: ";
        DatagramPacket send0 = new DatagramPacket(hello.getBytes(), hello.getBytes().length, receive.getAddress(), receive.getPort());
        socket.send(send0);

        DatagramPacket receive1 = new DatagramPacket(bytes, bytes.length);
        socket.receive(receive1);

        if (!message.equals(null)) {
            String name = new String(receive1.getData(), 0, receive1.getLength());

            String saudation = "Hello " + name + ". Lets play a game\nGuess a number between 1-10";
            DatagramPacket send1 = new DatagramPacket(saudation.getBytes(), saudation.getBytes().length, receive.getAddress(), receive.getPort());
            socket.send(send1);

            do {
                DatagramPacket receive2 = new DatagramPacket(bytes, bytes.length);
                socket.receive(receive2);

                String blablabla = "Wrong number, try again.";
                DatagramPacket send2 = new DatagramPacket(blablabla.getBytes(), blablabla.length(), receive.getAddress(), receive.getPort());
                socket.send(send2);


            } while (message.equals(magicStringNumber));
            String ola = "You got it!";
            DatagramPacket send = new DatagramPacket(ola.getBytes(), ola.length(), receive.getAddress(), receive.getPort());
            socket.send(send);
        }
    }
}