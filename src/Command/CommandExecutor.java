package Command;


import ChunkManager.ChunkCreating;
import Command.Commands.Auth;
import DataStructure.Response;
import server.FileManagment.ParserXML;
import server.FileManagment.ParserXMLtoBD;
import server.Log;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.DatagramChannel;
import java.text.ParseException;
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
        Log.getLogger().log(Level.INFO, "Received command "+ command + " from "+ client);
        byte[] output;
        if(command.getSession()!=null || command instanceof Auth) {
            command.execute();
        }
        else{
            command.setOutput("You can`t execute commands without logging in!");
        }
        ChunkCreating chunkCreating = new ChunkCreating(command.getBytes());

        Log.getLogger().log(Level.INFO, "Sending " + chunkCreating.getCounting() + " chunks to "+ client);

        Iterator<byte[]> keys= chunkCreating.getIterator();
        while(keys.hasNext()){
            datagramChannel.send(ByteBuffer.wrap(keys.next()), client);
        }
        if(command.isSuccess()&&command.isBd()) {
            new ParserXMLtoBD(clientsDataPath,manager).parseData();
        }
    }
}
