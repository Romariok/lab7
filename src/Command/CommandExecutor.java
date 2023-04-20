package Command;


import ChunkManager.ChunkCreating;
import DataStructure.Response;
import server.FileManagment.ParserXML;
import server.Log;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.DatagramChannel;
import java.util.Iterator;
import java.util.logging.Level;

import static Command.Serializer.deserialize;
import static server.Connections.Connection.collection;
import static server.Connections.Connection.manager;
import static server.ServerMain.clientsDataPath;
public class CommandExecutor {
    private static DatagramChannel datagramChannel;

    public static void setChannel(DatagramChannel datagramChannel){
        CommandExecutor.datagramChannel = datagramChannel;
    }
    public static void execute(InetSocketAddress client, byte[] bytes) throws IOException, ClassNotFoundException {
        Object inputObject = deserialize(bytes);
        CommandResponse command = (CommandResponse) inputObject;
        assert command != null;
        command.setCollectionManager(manager);
//        TODO Сделать проверку на авторизованность пользователя
        Log.getLogger().log(Level.INFO, "Received command "+ command + " from "+ client);

        command.execute();
        byte[] output = command.getResponse().getOutput().getBytes();


        ChunkCreating chunkCreating = new ChunkCreating(output);

        Log.getLogger().log(Level.INFO, "Sending " + chunkCreating.getCounting() + " chunks to "+ client);

        Iterator<byte[]> keys= chunkCreating.getIterator();
        while(keys.hasNext()){
            datagramChannel.send(ByteBuffer.wrap(keys.next()), client);
        }

        new ParserXML(clientsDataPath).writeData(collection);
    }
}
