package server.Connections;


import ChunkManager.ChunkReceiving;
import server.Log;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.ClosedChannelException;
import java.nio.channels.DatagramChannel;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;


import static Command.CommandExecutor.setChannel;
import static server.Connections.Connection.BUFFER_SIZE;

public class SelectorManager{

    public static HashMap<InetSocketAddress, ChunkReceiving> chunkList = new HashMap<>();
    private static Selector selector = Connection.getSelector();
    private static final ExecutorService cachedPool = Executors.newCachedThreadPool();


    public static void run(){
        try{
            while (true) {
                selector.select();
                Iterator<SelectionKey> keys = selector.selectedKeys().iterator();
                while (keys.hasNext()) {
                    SelectionKey key = keys.next();
                    keys.remove();
                    if (!key.isValid()) {
                        continue;
                    }
                    if (key.isReadable()) {
                        DatagramChannel keyChannel = (DatagramChannel) key.channel();
                        setChannel(keyChannel);
                        ByteBuffer buffer = ByteBuffer.allocate(BUFFER_SIZE);
                        InetSocketAddress inetSocketAddress = (InetSocketAddress) keyChannel.receive(buffer);
                        cachedPool.execute(new ServerHandler(buffer, keyChannel, inetSocketAddress));
                    }
                }
            }
        }
        catch (IOException ex){
            Log.getLogger().warning(ex.getMessage());
        }

    }

    public synchronized static Map<InetSocketAddress, ChunkReceiving> getChunkList(){
        return chunkList;
    }
}
