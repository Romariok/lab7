package client;

import java.net.InetAddress;
import java.net.UnknownHostException;


public class ClientMain {
    private static final InetAddress address;
    private static final int PORT = 9174;

    static{
        try{
            address = InetAddress.getLocalHost();
        }
        catch (UnknownHostException ex){
            throw new RuntimeException();
        }

    }

    public static void main(String[] args) {

        ClientBase base = new ClientBase(address, PORT);

        base.run();

    }
}
