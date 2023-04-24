package server.Connections;

import java.io.*;
import java.net.*;
import java.nio.channels.DatagramChannel;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.util.LinkedList;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.logging.Level;

import Data.HumanBeing;
import DataStructure.CollectionManager;
import server.Log;

public class Connection {

    private final int PORT;
    private static Selector selector;
    public static final int BUFFER_SIZE = 1032;
    private static DatagramSocket serverSocket = null;
    private DatagramChannel datagramChannel;
    public static CopyOnWriteArrayList<HumanBeing> collection;
    public static CollectionManager manager;

    public Connection(int port, CollectionManager manager) {
        this.PORT = port;
        Connection.manager = manager;
        Connection.collection = manager.getConcurrentCollection();
    }

    public void start() {
        try {
            datagramChannel = DatagramChannel.open();
            datagramChannel.configureBlocking(false);
            serverSocket = datagramChannel.socket();
            serverSocket.bind(new InetSocketAddress(PORT));
            selector = Selector.open();

            datagramChannel.register(selector, SelectionKey.OP_READ);
            Log.getLogger().log(Level.INFO, "Server started datagram channel: " + datagramChannel.toString());
            SelectorManager.run();

        } catch (IOException ex) {
            Log.getLogger().warning(ex.getMessage());
        } finally {
            try {
                datagramChannel.close();
                selector.close();
            } catch (IOException ex) {
                Log.getLogger().warning(ex.toString());
            }
        }
    }

    public static Selector getSelector() {
        return selector;
    }
}


