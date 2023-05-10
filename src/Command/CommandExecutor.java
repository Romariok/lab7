package Command;


import Auth.AuthResponse;
import ChunkManager.ChunkCreating;
import Command.Commands.*;
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
        assert authResponse != null;
        List<String> tempCommand = Arrays.stream(authResponse.getCommand().split(" ")).toList();
        CommandResponse command;
        if(authResponse.getArgs().equals("")&&!authResponse.getObject().equals("")) {
            command = factory.getCommand(authResponse.getCommand(), new String[]{""}, new Scanner(authResponse.getObject()), true);
        } else if (authResponse.getObject().equals("")&&!authResponse.getArgs().equals("")) {
            command = factory.getCommand(authResponse.getCommand(), new String[]{authResponse.getArgs()},new Scanner(""), true);
        }
        else if(authResponse.getArgs().equals("") && authResponse.getObject().equals("")){
            command = factory.getCommand(authResponse.getCommand(), new String[]{""},new Scanner(""),true);
        }
        else{
            command = factory.getCommand(authResponse.getCommand(), new String[]{authResponse.getArgs()},new Scanner(authResponse.getObject()), true);
        }
        assert command!=null;
        command.setCollectionManager(manager);
        AuthResponse response;
        if (authResponse.isAutorized() || command instanceof Auth || command instanceof Register || command instanceof Info || command instanceof Help||command instanceof Execute_script) {
            Log.getLogger().log(Level.INFO, "Received command " + command + " from " + client);
            if (authResponse.isAutorized()) {
                command.setUser(authResponse.getUser());
            }
            command.execute();
            String output = command.getResponse().getOutput();
            response = new AuthResponse(output, authResponse.getUser(), authResponse.isAutorized(),"","");
            if (command instanceof Auth) {
                response = new AuthResponse(output, ((Auth) command).getSession().getUser(), ((Auth) command).getSession().isAuthoriazed(),"","");
            }

            if (command.isSuccess() && command.isBd()) {
                new ParserfromBD(manager).parseData();
            }
        }
        else{
            response = new AuthResponse("Cannot execute this command without getting authorized!",authResponse.getUser(), authResponse.isAutorized(),"","");
        }
        ChunkCreating chunkCreating = new ChunkCreating(Serializer.serialize(response));
        Log.getLogger().log(Level.INFO, "Sending " + chunkCreating.getCounting() + " chunks to " + client);
        Iterator<byte[]> keys = chunkCreating.getIterator();
        while (keys.hasNext()) {
            datagramChannel.send(ByteBuffer.wrap(keys.next()), client);
        }
    }
}
