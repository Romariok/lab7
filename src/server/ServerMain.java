package server;


import DataStructure.CollectionManager;
import server.Connections.Connection;


public class ServerMain {
    public static String clientsDataPath;
    public static void main(String[] args) {
        int port = Integer.parseInt(args[1]);
        clientsDataPath = args[0];
        CollectionManager manager = new CollectionManager(clientsDataPath);
        Connection connection = new Connection(port, manager);
        connection.start();
    }
}
