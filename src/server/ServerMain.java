package server;


import DataStructure.CollectionManager;
import server.Connections.Connection;


public class ServerMain {
    public static void main(String[] args) {
        int port = 7777;

        String clientsDataPath = args[0];

        CollectionManager manager = new CollectionManager(clientsDataPath);
        System.out.println(manager.getCollection());
        Connection connection = new Connection(port, manager);
        connection.start();
    }
}
