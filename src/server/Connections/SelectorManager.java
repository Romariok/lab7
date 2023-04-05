package server.Connections;

import server.Log;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.DatagramChannel;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;


import static Command.CommandExecutor.setChannel;
import static Command.CommandExecutor.execute;
import static server.Connections.Connection.BUFFER_SIZE;

public class SelectorManager {
    private static final Selector selector = Connection.getSelector();
    public static void run() throws IOException {
        Map<InetSocketAddress, ByteArrayOutputStream> Streams = new HashMap<>();
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

                    ByteArrayOutputStream byteStream = Streams.get(inetSocketAddress);
                    if (byteStream == null) {
                        byteStream = new ByteArrayOutputStream();
                        Streams.put(inetSocketAddress, byteStream);
                    }
                    boolean hasNext = buffer.array()[buffer.limit() - 1] == 1;
                    byteStream.write(buffer.array(), 0, buffer.limit() - 1);
                    if (!hasNext) {
                        try {
                            execute(inetSocketAddress, byteStream.toByteArray());
                        } catch (Exception e) {
                            keyChannel.send(ByteBuffer.wrap("ERROR: Oompa Loompa".getBytes()), inetSocketAddress);
                            Log.getLogger().warning(e.toString());
                        }
                        Streams.remove(inetSocketAddress);
                    }
                }
            }
        }
    }
}
