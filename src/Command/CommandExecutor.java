package Command;


import ChunkManager.ChunkCreating;
import Command.Commands.Auth;
import Command.Commands.Help;
import Command.Commands.Register;
import Auth.Session;
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
import static Command.Serializer.serialize;
import static server.Connections.Connection.collection;
import static server.Connections.Connection.manager;
import static server.ServerMain.clientsDataPath;

public class CommandExecutor {
    private static DatagramChannel datagramChannel;

    public static void setChannel(DatagramChannel datagramChannel) {
        CommandExecutor.datagramChannel = datagramChannel;
    }

    public static void execute(InetSocketAddress client, byte[] bytes) throws IOException, ClassNotFoundException {
        Object inputObject = deserialize(bytes);
        InputData inputData = (InputData) inputObject;
        CommandResponse command = inputData.getCommandResponse();
        command.setCollectionManager(manager);
        Session session = inputData.getSession();
        command.setSession(session);
        InputData inputData1 = new InputData();
        Log.getLogger().log(Level.INFO, "Received command " + command + " from " + client);
        byte[] output;
        if (inputData.getSession().isAuthorized() || command instanceof Auth || command instanceof Help||command instanceof Register) {
            command.execute();
        } else {
            command.setOutput("You can`t execute commands without logging in!");
        }
        inputData1.setSession(inputData.getSession());
        command.setCollectionManager(null);
        inputData1.setCommandResponse(command);
        /*
        String out = command.getOutput();
        if (command.getResponse().getOutput() != null) {
            out = command.getResponse().getOutput();
        }
        */

        ChunkCreating chunkCreating = new ChunkCreating(serialize(inputData1));

        Log.getLogger().log(Level.INFO, "Sending " + chunkCreating.getCounting() + " chunks to " + client);

        Iterator<byte[]> keys = chunkCreating.getIterator();
        while (keys.hasNext()) {
            datagramChannel.send(ByteBuffer.wrap(keys.next()), client);
        }
    }
}
