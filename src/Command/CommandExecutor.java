package Command;


import Auth.AuthResponse;
import ChunkManager.ChunkCreating;
import Command.Commands.Auth;
import Command.Commands.Info;
import Command.Commands.Register;
import Command.Commands.Show;
import server.FileManagment.ParserfromBD;
import server.Log;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.DatagramChannel;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.Scanner;
import java.util.logging.Level;

import static Command.Serializer.deserialize;
import static server.Connections.Connection.manager;
import static server.ServerMain.clientsDataPath;

public class CommandExecutor {
    private static DatagramChannel datagramChannel;
    private static CommandFactory factory = new CommandFactory();

    public static void setChannel(DatagramChannel datagramChannel) {
        CommandExecutor.datagramChannel = datagramChannel;
    }

    public static void execute(InetSocketAddress client, byte[] bytes) throws IOException, ClassNotFoundException {
        Object inputObject = deserialize(bytes);
        AuthResponse authResponse = (AuthResponse) inputObject;
        List<String> tempCommand = Arrays.stream(authResponse.getCommand().split(" ")).toList();
        CommandResponse command;
        if (tempCommand.size() == 2) {
            if (tempCommand.get(1).contains("\n")) {
                command = factory.getCommand(tempCommand.get(0), null, new Scanner(tempCommand.get(1)), true);
            } else {
                command = factory.getCommand(tempCommand.get(0), tempCommand.subList(1, tempCommand.size()).toArray(new String[0]), null, true);
            }
        } else if(tempCommand.size()==3){
            command = factory.getCommand(tempCommand.get(0), new String[]{tempCommand.get(1)}, new Scanner(tempCommand.get(2)), false);
        }
        else {
            command = factory.getCommand(tempCommand.get(0),null,null, true);
        }
        assert command != null;
        command.setCollectionManager(manager);
        AuthResponse response;
        if (authResponse.isAutorized() || command instanceof Auth || command instanceof Register || command instanceof Info || command instanceof Show) {
            Log.getLogger().log(Level.INFO, "Received command " + command + " from " + client);
            if (authResponse.isAutorized()) {
                command.setUser(authResponse.getUser());
            }
            command.execute();
            String output = command.getResponse().getOutput();
            response = new AuthResponse(output, authResponse.getUser(), authResponse.isAutorized());
            if (command instanceof Auth) {
                response = new AuthResponse(output, authResponse.getUser(), command.isSuccess());
            }

            if (command.isSuccess() && command.isBd()) {
                new ParserfromBD(manager).parseData();
            }
        }
        else{
            response = new AuthResponse("Cannot execute this command without getting authorized!",authResponse.getUser(), authResponse.isAutorized());
        }
        ChunkCreating chunkCreating = new ChunkCreating(Serializer.serialize(response));
        Log.getLogger().log(Level.INFO, "Sending " + chunkCreating.getCounting() + " chunks to " + client);
        Iterator<byte[]> keys = chunkCreating.getIterator();
        while (keys.hasNext()) {
            datagramChannel.send(ByteBuffer.wrap(keys.next()), client);
        }
    }
}
