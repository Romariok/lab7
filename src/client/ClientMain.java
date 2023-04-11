package client;

import java.net.InetAddress;
import java.net.UnknownHostException;


public class ClientMain {
    private static final InetAddress address;
    private static int PORT = 0;
    public static String[] arg;

    static{
        try{
            address = InetAddress.getLocalHost();
        }
        catch (UnknownHostException ex){
            throw new RuntimeException();
        }

    }

    public static void main(String[] args) {
        PORT = Integer.parseInt(args[0]);
        arg = args;
        ClientBase base = new ClientBase(address, PORT);

        base.run();

    }
}
